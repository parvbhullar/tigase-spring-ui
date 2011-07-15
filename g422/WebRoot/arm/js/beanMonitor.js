/**
 * SpringBean切面监控
 * 
 * @author XiongChun
 * @since 2010-09-20
 */
Ext
		.onReady(function() {
			var expander = new Ext.grid.RowExpander(
					{
						tpl : new Ext.Template(
								'<p style=margin-left:70px;><span style=color:Teal;>方法</span><br><span>{methodname}</span></p>',
								'<p style=margin-left:70px;><span style=color:Teal;>类名</span><br><span>{clazz}</span></p>',
								'<p style=margin-left:70px;><span style=color:Teal;>活动时间</span><br><span>{activetime}</span></p>',
								'<p style=margin-left:70px;><span style=color:Teal;>耗时</span><br><span>{costtime}</span></p>',
								'<p style=margin-left:70px;><span style=color:Teal;>异常信息</span><br><span style=color:red;>{exception}</span></p>'),
						// 屏蔽双击事件
						expandOnDblClick : true
					});
			var sm = new Ext.grid.CheckboxSelectionModel();
			var cm = new Ext.grid.ColumnModel( [ new Ext.grid.RowNumberer(),
					sm, expander, {
						header : '监控编号',
						dataIndex : 'monitorid',
						hidden : true,
						width : 120,
						sortable : true
					}, {
						header : '切入点',
						dataIndex : 'pointcuttype',
						sortable : true,
						width : 60,
						hidden : true,
						renderer : function(value) {
							if (value == '1')
								return 'BPO';
							else if (value == '2')
								return 'DAO';
							else
								return value;
						}
					}, {
						header : '激活时间',
						dataIndex : 'activetime',
						sortable : true,
						width : 130
					}, {
						header : '方法',
						dataIndex : 'methodname',
						width : 160,
						sortable : true
					}, {
						header : '耗时',
						dataIndex : 'costtime',
						width : 80,
						sortable : true
					}, {
						header : '类名',
						dataIndex : 'clazz',
						width : 350,
						sortable : true
					}, {
						header : '通知类型',
						dataIndex : 'advisetype',
						width : 80,
						sortable : true,
						renderer : function(value) {
							if (value == '1')
								return '方法调用';
							else if (value == '2')
								return '异常捕获';
							else
								return value;
						}
					}, {
						header : '异常信息',
						dataIndex : 'exception',
						sortable : true,
						width : 200
					}, {
						id : '_blank',
						dataIndex : '_blank'
					} ]);

			/**
			 * 数据存储
			 */
			var store = new Ext.data.Store( {
				proxy : new Ext.data.HttpProxy( {
					url : 'beanMonitor.ered?reqCode=queryMonitorDatas'
				}),
				reader : new Ext.data.JsonReader( {
					totalProperty : 'TOTALCOUNT',
					root : 'ROOT'
				}, [ {
					name : 'monitorid'
				}, {
					name : 'pointcuttype'
				}, {
					name : 'advisetype'
				}, {
					name : 'methodname'
				}, {
					name : 'activetime'
				}, {
					name : 'costtime'
				}, {
					name : 'clazz'
				}, {
					name : 'exception'
				} ])
			});

			// 翻页排序时带上查询条件
			store.on('beforeload', function() {
				var ksrq = Ext.getCmp('ksrq').getValue();
				if (!Ext.isEmpty(ksrq)) {
					ksrq = ksrq.format('Y-m-d').toString();
				}
				var jsrq = Ext.getCmp('jsrq').getValue();
				if (!Ext.isEmpty(jsrq)) {
					jsrq = jsrq.format('Y-m-d').toString();
				}
				this.baseParams = {
					keyword : Ext.getCmp('keyword').getValue(),
					jsrq : jsrq,
					ksrq : ksrq,
					advisetype : advisetypeCombo.getValue(),
					pointcuttype : pointcuttypeCombo.getValue()
				};
			});

			var pagesize_combo = new Ext.form.ComboBox( {
				name : 'pagesize',
				hiddenName : 'pagesize',
				typeAhead : true,
				triggerAction : 'all',
				lazyRender : true,
				mode : 'local',
				store : new Ext.data.ArrayStore( {
					fields : [ 'value', 'text' ],
					data : [ [ 10, '10条/页' ], [ 20, '20条/页' ], [ 50, '50条/页' ],
							[ 100, '100条/页' ], [ 250, '250条/页' ],
							[ 500, '500条/页' ] ]
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
				store.reload( {
					params : {
						start : 0,
						limit : bbar.pageSize
					}
				});
			});

			var bbar = new Ext.PagingToolbar( {
				pageSize : number,
				store : store,
				displayInfo : true,
				displayMsg : '显示{0}条到{1}条,共{2}条',
				plugins : new Ext.ux.ProgressBarPager(), // 分页进度条
				emptyMsg : "没有符合条件的记录",
				items : [ '-', '&nbsp;&nbsp;', pagesize_combo ]
			});

			var pointcuttypeStore = new Ext.data.SimpleStore( {
				fields : [ 'value', 'text' ],
				data : [ [ '1', '1 BPO切入点' ], [ '2', '2 DAO切入点' ] ]
			});
			var pointcuttypeCombo = new Ext.form.ComboBox( {
				name : 'pointcuttype',
				hiddenName : 'pointcuttype',
				store : pointcuttypeStore,
				mode : 'local',
				triggerAction : 'all',
				valueField : 'value',
				displayField : 'text',
				// value : '0',
				fieldLabel : '选择切入点',
				emptyText : '选择切入点',
				allowBlank : true,
				forceSelection : true,
				editable : true,
				typeAhead : true,
				width : 100
			});
			var advisetypeStore = new Ext.data.SimpleStore( {
				fields : [ 'value', 'text' ],
				data : [ [ '1', '1 方法调用' ], [ '2', '2 异常捕获' ] ]
			});
			var advisetypeCombo = new Ext.form.ComboBox( {
				name : 'advisetype',
				hiddenName : 'advisetype',
				store : advisetypeStore,
				mode : 'local',
				triggerAction : 'all',
				valueField : 'value',
				displayField : 'text',
				// value : '0',
				fieldLabel : '选择通知类型',
				emptyText : '选择通知类型',
				allowBlank : true,
				forceSelection : true,
				editable : true,
				typeAhead : true,
				width : 100
			});
			var grid = new Ext.grid.GridPanel(
					{
						title : '<span style="font-weight:normal">Service监控</span>',
						renderTo : 'beanMonitorGridDiv',
						height : 500,
						// width:600,
						iconCls : 'pluginIcon',
						autoScroll : true,
						region : 'center',
						store : store,
						loadMask : {
							msg : '正在加载表格数据,请稍等...'
						},
						stripeRows : true,
						frame : true,
						autoExpandColumn : '_blank',
						cm : cm,
						sm : sm,
						plugins : expander,
						tbar : [
								{
									text : '删除',
									iconCls : 'page_delIcon',
									handler : function() {
										if (runMode == '0') {
											Ext.Msg
													.alert('提示',
															'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
											return;
										}
										deleteMonitorDatas('del');
									}
								},
								'-',
								{
									text : '重置',
									iconCls : 'tbar_synchronizeIcon',
									handler : function() {
										if (runMode == '0') {
											Ext.Msg
													.alert('提示',
															'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
											return;
										}
										deleteMonitorDatas('reset');
									}
								},
								'->',
								{
									id : 'ksrq',
									name : 'ksrq',
									xtype : 'datefield',
									emptyText : '开始日期',
									format : 'Y-m-d',
									width : 100
								},
								'-',
								{
									id : 'jsrq',
									name : 'jsrq',
									xtype : 'datefield',
									emptyText : '结束日期',
									format : 'Y-m-d',
									width : 100
								},
								'-',
								advisetypeCombo,
								'-',
								new Ext.form.TextField(
										{
											id : 'keyword',
											name : 'keyword',
											emptyText : '关键字过滤',
											enableKeyEvents : true,
											listeners : {
												specialkey : function(field, e) {
													if (e.getKey() == Ext.EventObject.ENTER) {
														queryMonitorDatas();
													}
												}
											},
											width : 100
										}), {
									text : '查询',
									iconCls : 'previewIcon',
									handler : function() {
										queryMonitorDatas();
									}
								}, '-', {
									text : '刷新',
									iconCls : 'arrow_refreshIcon',
									handler : function() {
										store.reload();
									}
								} ],
						bbar : bbar
					});
			store.load( {
				params : {
					start : 0,
					limit : bbar.pageSize
				}
			});
			grid.on('sortchange', function() {
				grid.getSelectionModel().selectFirstRow();
			});

			bbar.on("change", function() {
				grid.getSelectionModel().selectFirstRow();
			});

			/**
			 * 布局
			 */
			var viewport = new Ext.Viewport( {
				layout : 'border',
				items : [ grid ]
			});

			/**
			 * 查询事件
			 */
			function queryMonitorDatas() {
				var ksrq = Ext.getCmp('ksrq').getValue();
				if (!Ext.isEmpty(ksrq)) {
					ksrq = ksrq.format('Y-m-d').toString();
				}
				var jsrq = Ext.getCmp('jsrq').getValue();
				if (!Ext.isEmpty(jsrq)) {
					jsrq = jsrq.format('Y-m-d').toString();
				}
				store.load( {
					params : {
						start : 0,
						limit : bbar.pageSize,
						keyword : Ext.getCmp('keyword').getValue(),
						jsrq : jsrq,
						ksrq : ksrq,
						advisetype : advisetypeCombo.getValue(),
						pointcuttype : pointcuttypeCombo.getValue()
					}
				});
			}

			/**
			 * 删除事件
			 */
			function deleteMonitorDatas(type) {
				var rows = grid.getSelectionModel().getSelections();
				if (Ext.isEmpty(rows)) {
					Ext.Msg.alert('提示', '请先选中要删除的项目!');
					return;
				}
				var strChecked = jsArray2JsString(rows, 'monitorid');
				var msg = '确认删除选中的记录吗?';
				if (type == 'reset')
					msg = '确认清空所有监控记录吗?';
				Ext.Msg
						.confirm(
								'请确认',
								msg,
								function(btn, text) {
									if (btn == 'yes') {
										showWaitMsg();
										Ext.Ajax
												.request( {
													url : 'beanMonitor.ered?reqCode=deleteMonitorDatas',
													success : function(response) {
														var resultArray = Ext.util.JSON
																.decode(response.responseText);
														store.reload();
														Ext.Msg
																.alert(
																		'提示',
																		resultArray.msg);
													},
													failure : function(response) {
														var resultArray = Ext.util.JSON
																.decode(response.responseText);
														Ext.Msg
																.alert(
																		'提示',
																		resultArray.msg);
													},
													params : {
														strChecked : strChecked,
														type : type
													}
												});
									}
								});
			}

			if (runMode == '0') {
				Ext.Msg.alert('提示', '为保障演示系统高速运行,监控功能已关闭.您看到的数据是历史监控数据!');
			}

		});