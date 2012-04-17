/**
 * 用户管理与授权
 * 
 * @author XiongChun
 * @since 2010-04-20
 */
Ext.onReady(function() {
	/**
	 * 右侧文档开始
	 */
	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),{
		header : '执行人',
		dataIndex : 'execfounder',
		sortable : true
	}, {
		header : '标题',
		dataIndex : 'schetitle'
	}, {
		header : '紧急程度',
		dataIndex : 'scheurgent',
		width : 60
		//renderer : SCHEURGENTRender
	}, {
		header : '开始时间',
		dataIndex : 'begindate'
	}, {
		header : '结束时间',
		dataIndex : 'enddate'
	}, {
		header : '内容',
		dataIndex : 'content'
	}, {
		header : '状态',
		dataIndex : 'schestatus',
		width : 60
		//renderer : SCHESTATUSRender
	}, {
		header : '主键ID',
		dataIndex : 'scheid',
		hidden : true
	}]);

	/**
	 * 数据存储
	 */
	var store = new Ext.data.Store({
				// 获取数据的方式
				proxy : new Ext.data.HttpProxy({
							url : 's.xzb?reqCode=queryLowerScheduleList'
						}),
				// 数据读取器
				reader : new Ext.data.JsonReader({
							totalProperty : 'TOTALCOUNT', // 记录总数
							root : 'ROOT' // Json中的列表数据根节点
						}, [{
									name : 'execfounder'
								}, {
									name : 'schetitle'
								}, {
									name : 'scheurgent'
								}, {
									name : 'begindate'
								}, {
									name : 'enddate'
								}, {
									name : 'content'
								}, {
									name : 'schestatus'
								}, {
									name : 'scheid'
								}])
			});

	// 翻页排序时带上查询条件
	
	// 每页显示条数下拉选择框
	var pagesize_combo = new Ext.form.ComboBox({
				name : 'pagesize',
				triggerAction : 'all',
				mode : 'local',
				store : new Ext.data.ArrayStore({
							fields : ['value', 'text'],
							data : [[10, '10条/页'], [20, '20条/页'], [50, '50条/页'], [100, '100条/页'], [250, '250条/页'], [500, '500条/页']]
						}),
				valueField : 'value',
				displayField : 'text',
				value : '20',
				editable : false,
				width : 85
			});

	// 改变每页显示条数reload数据
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
	var number = parseInt(pagesize_combo.getValue());
	// 分页工具栏
	var bbar = new Ext.PagingToolbar({
				pageSize : number,
				store : store,
				displayInfo : true,
				displayMsg : '显示{0}条到{1}条,共{2}条',
				plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
				emptyMsg : "没有符合条件的记录",
				items : ['-', '&nbsp;&nbsp;', pagesize_combo]
			});

	// 表格实例
	var grid = new Ext.grid.GridPanel({
				region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
				// collapsible : true,
				border : false,
				// 表格面板标题,默认为粗体，我不喜欢粗体，这里设置样式将其格式为正常字体
				title : '<span class="commoncss">日程列表</span>',
				// height : 500,
				autoScroll : true,
				frame : true,
				store : store, // 数据存储
				stripeRows : true, // 斑马线
				//autoExpandColumn : 'schetitle',
				cm : cm,// 列模型
				bbar : bbar,// 分页工具栏
				viewConfig : {
					// 不产横向生滚动条, 各列自动扩展自动压缩, 适用于列数比较少的情况
					forceFit : true
				},
				loadMask : {
					msg : '正在加载表格数据,请稍等...'
				}
			});
	
	var addScheFormPanel = new Ext.form.FormPanel({
		id : 'addScheFormPanel',
		name : 'addScheFormPanel',
		defaultType : 'textfield',
		labelAlign : 'right',
		labelWidth : 65,
		frame : false,
		bodyStyle : 'padding:5 5 0',
		items : [{
					fieldLabel : '标题',
					name : 'schetitle',
					allowBlank : false,
					labelStyle : micolor,
					anchor : '99%'
				},{
					xtype : 'datetimefield',
					fieldLabel : '开始时间',
					id : 'begind',
					name : 'begindate', // name:后台根据此name属性取值
					allowBlank : false,
					anchor : '99%'
				},  {
					xtype : 'datetimefield',
					fieldLabel : '结束时间',
					id : 'endd',
					name : 'enddate', // name:后台根据此name属性取值
					allowBlank : false,
					anchor : '99%'
				}, {
					fieldLabel : '内容',
					name : 'content',
					xtype : 'textarea',
					allowBlank : false,
					height:20,
					anchor : '99%'
				},{
					fieldLabel : '',
					
					id:'exppeopleId',
					xtype : 'textfield',						
					hidden:true,
					anchor : '99%'
				} ,{
					id : 'windowmode',
					name : 'windowmode',
					hidden : true
				}, {
					id : 'userid',
					name : 'userid',
					hidden : true
				}, {
					id : 'remind',
					name : 'remind',
					hidden : true
				}, {
					id : 'updatemode',
					name : 'updatemode',
					hidden : true
				}, {
					id : 'scheid',
					name : 'scheid',
					hidden : true
				}]
	});
	
	var addScheWindow = new Ext.Window({
		layout : 'fit',
		id:'addScheWindow',
		width : 400,
		height : 450,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		modal : true,
		title : '<span class="commoncss">新增日程</span>',
		// iconCls : 'page_addIcon',
		collapsible : true,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [addScheFormPanel],
		buttons : [{ 
			text : '关闭',
			iconCls : 'deleteIcon',
			handler : function() {
				addScheWindow.hide();
			}
		}]
	});
	
	
	grid.on('rowdblclick', function(grid, rowIndex, event) {
		editInit();
	});

	function editInit() {
		var record = grid.getSelectionModel().getSelected();
		if (Ext.isEmpty(record)) {
			Ext.MessageBox.alert('提示', '请先选择要修改的项目!');
			return;
		}
		addScheFormPanel.getForm().loadRecord(record);
		/**
		var ptime = record.get('prompttime');
		addScheFormPanel.getForm().findField('prompttime').setValue(ptime);
		var remind = record.get('remind');
		addScheFormPanel.getForm().findField('remindcheckbox').setValue({2:false,3:false});
		if(remind == '2'){
			addScheFormPanel.getForm().findField('remindcheckbox').setValue({2:true});
		}else if(remind == '3'){
			addScheFormPanel.getForm().findField('remindcheckbox').setValue({3:true});
		}else if(remind == '23'){
			addScheFormPanel.getForm().findField('remindcheckbox').setValue({2:true,3:true});
		}*/
		addScheWindow.show();
		addScheWindow.setTitle('<span class="commoncss">修改日程</span>');
		
	}
	
	/**
	 * 右侧文档结束
	 */
	
	/**
	 * 左侧目录树开始
	 */

	var root = new Ext.tree.AsyncTreeNode({
				text : username,
				expanded : true,
				id : userid
			});
	var dirTree = new Ext.tree.TreePanel({
				loader : new Ext.tree.TreeLoader({
							baseAttrs : {},
							dataUrl : './s.xzb?reqCode=lowerTree'
						}),
				root : root,
				title : '', 
				applyTo : 'deptTreeDiv',
				autoScroll : false,
				animate : false,
				useArrows : false,
				border : false
			});
	dirTree.root.select();
	dirTree.on('click', function(node) {
				var userid = node.attributes.id;
				var dirText=node.attributes.text;
				
				if(dirText=='下属成员')
					{
						return;
					}
				
				store.load({
							params : {
								start : 0,
								limit : bbar.pageSize,
								userid : userid
							}
						});
			});
	

	/**
	 * 布局
	 */
	var viewport = new Ext.Viewport({
				layout : 'border',
				items : [{
							title : '<span class="commoncss">文档目录</span>',
							iconCls : 'chart_organisationIcon',
							tools : [{
										id : 'refresh',
										handler : function() {
											dirTree.root.reload()
										}
									}],
							collapsible : true,
							width : 210,
							minSize : 160,
							maxSize : 280,
							split : true,
							region : 'west',
							autoScroll : true,
							// collapseMode:'mini',
							items : [dirTree]
						}, {
							region : 'center',
							layout : 'fit',
							border : false,
							items : [grid]
						}]
			});

	/**
	 * 左侧目录树结束
	 */	
});