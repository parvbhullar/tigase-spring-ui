/**
 * G4社区
 * 
 * @author XiongChun
 * @since 2010-12-24
 */
var store, tabPanel;
Ext.onReady(function() {
	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), {
				header : '主题编号',
				dataIndex : 'topicid',
				hidden : true
			}, {
				header : '用户ID',
				dataIndex : 'userid',
				hidden : true
			}, {
				header : '主题分类',
				dataIndex : 'topictype',
				hidden : true
			}, {
				header : '是否可回帖',
				dataIndex : 'replyable',
				hidden : true
			}, {
				id : 'title',
				header : '标题',
				dataIndex : 'title',
				renderer : titleRender
			}, {
				header : '回复/浏览',
				dataIndex : 'replyview',
				width : 80
			}, {
				header : '作者',
				dataIndex : 'username',
				width : 80
			}, {
				header : '最后更新时间',
				dataIndex : 'updatetime',
				width : 130
			}]);

	/**
	 * 数据存储
	 */
	store = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : 'projectHome.ered?reqCode=queryTopics'
						}),
				reader : new Ext.data.JsonReader({
							totalProperty : 'TOTALCOUNT',
							root : 'ROOT'
						}, [{
									name : 'topicid'
								}, {
									name : 'userid'
								}, {
									name : 'topictype'
								}, {
									name : 'replyable'
								}, {
									name : 'title'
								}, {
									name : 'replyview'
								}, {
									name : 'username'
								}, {
									name : 'updatetime'
								}])
			});

	// 翻页排序时带上查询条件
	store.on('beforeload', function() {
				this.baseParams = {
					topictype : topicTypeCombo.getValue(),
					username : Ext.getCmp('username').getValue(),
					title : Ext.getCmp('title').getValue()
				};
			});

	var pagesize_combo = new Ext.form.ComboBox({
				name : 'pagesize',
				hiddenName : 'pagesize',
				typeAhead : true,
				triggerAction : 'all',
				lazyRender : true,
				mode : 'local',
				store : new Ext.data.ArrayStore({
							fields : ['value', 'text'],
							data : [[10, '10条/页'], [20, '20条/页'],
									[50, '50条/页'], [100, '100条/页'],
									[250, '250条/页'], [500, '500条/页']]
						}),
				valueField : 'value',
				displayField : 'text',
				value : '50',
				editable : false,
				width : 85
			});
	var number = parseInt(pagesize_combo.getValue());
	pagesize_combo.on("select", function(comboBox) {
				bbar.pageSize = parseInt(comboBox.getValue());
				number = parseInt(comboBox.getValue());
				store.reload({
							params : {
								start : 0,
								limit : bbar.pageSize
							}
						});
			});

	var bbar = new Ext.PagingToolbar({
				pageSize : number,
				store : store,
				displayInfo : true,
				displayMsg : '显示{0}条到{1}条,共{2}条',
				plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
				// emptyMsg
				// :
				// "没有符合条件的记录",
				items : ['-', '&nbsp;&nbsp;', pagesize_combo]
			});

	var topicTypeStore = new Ext.data.SimpleStore({
				fields : ['text', 'value'],
				data : [['显示全部', '0'], ['项目最新动态', '1'], ['建议|Bug提交', '2'],
						['咨询|问题求助', '3'], ['G4知识库', '4'], ['G4灌水乐园', '5']]
			});

	var topicTypeCombo = new Ext.form.ComboBox({
				// id : 'topictype',
				hiddenName : 'topictype',
				store : topicTypeStore,
				mode : 'local',
				triggerAction : 'all',
				valueField : 'value',
				displayField : 'text',
				fieldLabel : '主题分类',
				emptyText : '主题分类',
				value : '0',
				forceSelection : false,
				editable : false,
				typeAhead : true,
				width : 130
			});

	topicTypeCombo.on('select', function(combo) {
				var value = combo.getValue();
				queryTopics();
			});

	var grid = new Ext.grid.GridPanel({
				title : '<span style="font-weight:normal">G4社区主题列表</span>',
				iconCls : 'user_commentIcon',
				store : store,
				loadMask : {
					msg : '正在加载表格数据,请稍等...'
				},
				stripeRows : true,
				// frame : true,
				autoExpandColumn : 'title',
				cm : cm,
				tbar : [{
							text : '发表新帖',
							iconCls : 'message_editIcon',
							handler : function() {
								newTopic();
							}
						}, '-', {
							text : '阅读帖子',
							iconCls : 'book_previousIcon',
							handler : function() {
								var record = grid.getSelectionModel()
										.getSelected();
								if (Ext.isEmpty(record)) {
									Ext.MessageBox.alert('提示', '请先选中一个主题!');
									return;
								}
								previewInit(record);
							}
						}, '->', topicTypeCombo, '-', new Ext.form.TextField({
									id : 'username',
									name : 'username',
									emptyText : '作者',
									enableKeyEvents : true,
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryTopics();
											}
										}
									},
									width : 80
								}), '-', new Ext.form.TextField({
									id : 'title',
									name : 'title',
									emptyText : '标题',
									enableKeyEvents : true,
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												queryTopics();
											}
										}
									},
									width : 180
								}), {
							text : '查询',
							iconCls : 'previewIcon',
							handler : function() {
								queryTopics();
							}
						}, '-', {
							text : '刷新',
							iconCls : 'arrow_refreshIcon',
							handler : function() {
								store.reload();
							}
						}],
				bbar : bbar
			});

	grid.on("celldblclick", function(grid, rowIndex, columnIndex, e) {
				var store = grid.getStore();
				var record = store.getAt(rowIndex);
				previewInit(record);
			});

	// 可能是表格嵌套到了Tab里面,第一次加载数据不会出现阴影遮罩，故做此处理
	grid.on("render", function() {
				setTimeout(function() {
							store.load({
										params : {
											start : 0,
											limit : bbar.pageSize
										}
									});
						}, 1);
			});

	var contextmenu = new Ext.menu.Menu({
				id : 'theContextMenu',
				items : [{
							text : '阅读帖子',
							iconCls : 'book_previousIcon',
							handler : function() {
								var record = grid.getSelectionModel()
										.getSelected();
								previewInit(record);
							}
						}, {
							text : '发表新贴',
							iconCls : 'message_editIcon',
							handler : function() {
								newTopic();
							}
						}]
			});

	grid.on("rowcontextmenu", function(grid, rowIndex, e) {
				e.preventDefault();
				grid.getSelectionModel().selectRow(rowIndex);
				contextmenu.showAt(e.getXY());
			});

	var newTopicTypeStore = new Ext.data.SimpleStore({
				fields : ['text', 'value'],
				data : [['建议|Bug提交', '2'], ['咨询|问题求助', '3'], ['G4知识库', '4'],
						['G4灌水乐园', '5']]
			});

	var newTopicTypeCombo = new Ext.form.ComboBox({
				hiddenName : 'topictype',
				store : newTopicTypeStore,
				mode : 'local',
				triggerAction : 'all',
				valueField : 'value',
				displayField : 'text',
				fieldLabel : '分类',
				emptyText : '请选择主题分类',
				forceSelection : false,
				allowBlank : false,
				editable : false,
				typeAhead : true,
				anchor : '35%'
			});

	var newTopicPanel = new Ext.form.FormPanel({
				bodyStyle : 'padding:5 5 5 5',
				labelAlign : 'right', // 标签对齐方式
				frame : true,
				labelWidth : 35,
				items : [{
							layout : 'column',
							border : false,
							items : [{
										columnWidth : .99,
										layout : 'form',
										defaultType : 'textfield',
										border : false,
										items : [newTopicTypeCombo, {
													fieldLabel : '标题',
													emptyText : '请输入主题帖标题(名字取好一点哦)',
													maxLength : 50,
													name : 'title',
													allowBlank : false,
													anchor : '100%'
												}]
									}]
						}, {
							layout : 'fit',
							border : false,
							items : [{
										id : 'contentAdd',
										name : 'contentAdd',
										xtype : 'htmleditor',
										allowBlank : false,
										enableSourceEdit : false,
										anchor : '99%'
									}]

						}]
			});

	var newTopicWindow = new Ext.Window({
				title : '<span style="font-weight:normal">发表新主题帖</span>', // 窗口标题
				layout : 'fit', // 设置窗口布局模式
				iconCls : 'message_editIcon',
				modal : true,
				width : 650, // 窗口宽度
				height : 380, // 窗口高度
				closable : true, // 是否可关闭
				collapsible : false, // 是否可收缩
				maximizable : true, // 设置是否可以最大化
				closeAction : 'hide', // 关闭策略
				border : false, // 边框线设置
				constrain : true, // 设置窗口是否可以溢出父容器
				animateTarget : Ext.getBody(),
				pageY : 50, // 页面定位Y坐标
				pageX : document.body.clientWidth / 2 - 650 / 2, // 页面定位X坐标
				items : [newTopicPanel], // 嵌入的表单面板
				buttons : [{ // 窗口底部按钮配置
					text : '保存', // 按钮文本
					iconCls : 'acceptIcon', // 按钮图标
					handler : function() { // 按钮响应函数
						saveNewTopic();
					}
				}, {	// 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								newTopicWindow.hide();
							}
						}]
			});

	var replyPanel = new Ext.form.FormPanel({
				// bodyStyle : 'padding:5 5 5 5',
				labelAlign : 'right', // 标签对齐方式
				frame : true,
				labelWidth : 1,
				items : [{
							id : 'contentReply',
							name : 'contentReply',
							xtype : 'htmleditor',
							allowBlank : false,
							enableSourceEdit : false,
							anchor : '100%'
						}, {
							id : 'topicidReply',
							name : 'topicidReply',
							xtype : 'hidden'
						}]
			});

	var replyWindow = new Ext.Window({
				title : '<span style="font-weight:normal">回帖,参与讨论</span>', // 窗口标题
				layout : 'fit', // 设置窗口布局模式
				iconCls : 'edit1Icon',
				modal : true,
				width : 650, // 窗口宽度
				height : 380, // 窗口高度
				closable : true, // 是否可关闭
				collapsible : false, // 是否可收缩
				maximizable : true, // 设置是否可以最大化
				closeAction : 'hide', // 关闭策略
				border : false, // 边框线设置
				constrain : true, // 设置窗口是否可以溢出父容器
				animateTarget : Ext.getBody(),
				pageY : 50, // 页面定位Y坐标
				pageX : document.body.clientWidth / 2 - 650 / 2, // 页面定位X坐标
				items : [replyPanel], // 嵌入的表单面板
				buttons : [{ // 窗口底部按钮配置
					text : '保存', // 按钮文本
					iconCls : 'acceptIcon', // 按钮图标
					handler : function() { // 按钮响应函数
						replyTopic();
					}
				}, {	// 窗口底部按钮配置
							text : '关闭', // 按钮文本
							iconCls : 'deleteIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								replyWindow.hide();
							}
						}]
			});

	tabPanel = new Ext.TabPanel({
				activeTab : 0,
				region : 'center',
				border : false,
				items : [grid]

			});

	/**
	 * 布局
	 */
	var viewport = new Ext.Viewport({
				layout : 'border',
				items : [tabPanel]
			});

	/**
	 * 查询事件
	 */
	function queryTopics() {
		store.load({
					params : {
						start : 0,
						limit : bbar.pageSize,
						topictype : topicTypeCombo.getValue(),
						username : Ext.getCmp('username').getValue(),
						title : Ext.getCmp('title').getValue()
					}
				});
	}

	/**
	 * 新主题
	 */
	function newTopic() {
		newTopicPanel.form.reset();
		newTopicWindow.show();
	}

	function saveNewTopic() {
		if (!newTopicPanel.getForm().isValid()) {
			return;
		}
		var value = Ext.getCmp('contentAdd').getValue();
		if (value.length < 1) {
			Ext.MessageBox.alert('提示', '请先输入您的主题内容后再提交');
			return;
		}
		newTopicPanel.form.submit({
					url : 'projectHome.ered?reqCode=saveNewTopic',
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					success : function(form, action) { // 回调函数有2个参数
						// Ext.MessageBox.alert('提示',
						// action.result.msg);
						store.reload();
						newTopicWindow.hide();
					},
					failure : function(form, action) {
						Ext.MessageBox.alert('提示', '数据保存失败');
					}
				});
	}

	function replyTopic() {
		var value = Ext.getCmp('contentReply').getValue();
		if (value.length < 1 || value.trim() == '<br>') {
			Ext.MessageBox.alert('提示', '请先输入您的回帖内容后再提交');
			return;
		}
		replyPanel.form.submit({
					url : 'projectHome.ered?reqCode=saveReplyTopic',
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					success : function(form, action) {
						// Ext.MessageBox.alert('提示',
						// action.result.msg);
						// store.reload();
						replyWindow.hide();
						var topicid = Ext.getCmp('topicidReply').getValue()
						var topPanel = Ext.getCmp('panel_top_' + topicid);
						var value = Ext.getCmp('contentReply').getValue();
						tabPanel.getItem('tab' + topicid).getUpdater().update({
									url : 'projectHome.ered?reqCode=previewTopicInit',
									params : {
										topicid : topicid
									}
								});
						/*
						 * topPanel.add({ title : '<span
						 * style="font-weight:normal">我刚刚回复</span>', xtype :
						 * 'panel', collapsible : true, border : false,
						 * titleCollapse : true, html : '<div class="myDiv"
						 * style="margin: 8px;">' + value + '</div>' });
						 * topPanel.doLayout();
						 */
					},
					failure : function(form, action) {
						Ext.MessageBox.alert('提示', '回帖数据保存失败');
					}
				});
	}

	function previewInit(rd) {
		var title = rd.get('title');
		if (title.length > 12) {
			title = title.substring(0, 12) + '...';
		}
		tabPanel.add({
					id : 'tab' + rd.get('topicid'),
					title : title,
					closable : true,
					autoScroll : true,
					tbar : [{
						text : '回帖,参与讨论',
						iconCls : 'edit1Icon',
						handler : function() {
							replyPanel.getForm().reset();
							Ext.getCmp('topicidReply').setValue(rd
									.get('topicid'));
							replyWindow.show();
							// Ext.getCmp('contentReply').focus();
						}
					}],
					autoLoad : {
						url : 'projectHome.ered?reqCode=previewTopicInit',
						text : '<span class="font12">南京地铁模板引擎正在驱动页面,请等待...</span>',
						scripts : true,
						params : {
							topicid : rd.get('topicid')
						}
					}
				});
		tabPanel.setActiveTab('tab' + rd.get('topicid'));
	}

	function titleRender(value, cellMetaData, record) {
		var topictype = record.data.topictype;
		var type = '';
		if (topictype == '1')
			type = '[项目最新动态]';
		else if (topictype == '2')
			type = '[建议|Bug提交]';
		else if (topictype == '3')
			type = '[咨询|问题求助]';
		else if (topictype == '4')
			type = '[G4知识库]';
		else if (topictype == '5')
			type = '[G4灌水乐园]';
		value = '<span style="color:blue">' + type + '</span>&nbsp;' + value;
		return value;
	}

});