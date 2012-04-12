/**
 * 日程管理
 * 
 * @author XiongChun
 * @since 2010-11-20
 */
Ext.onReady(function() {
			var qForm = new Ext.form.FormPanel({
						region : 'north',
						title : '<span class="commoncss">查询条件<span>',
						collapsible : true,
						border : true,
						labelWidth : 50, // 标签宽度
						// frame : true, //是否渲染表单面板背景色
						labelAlign : 'right', // 标签对齐方式
						bodyStyle : 'padding:3 5 0', // 表单元素和表单面板的边距
						buttonAlign : 'center',
						height : 120,
						items : [{
							layout : 'column',
							border : false,
							items : [{
										columnWidth : .33,
										layout : 'form',
										labelWidth : 60, // 标签宽度
										defaultType : 'textfield',
										border : false,
										items : [{
													fieldLabel : '标题',
													name : 'schetitle',
													id : 'schetitle',
													anchor : '100%'
												}]
									}, {
										columnWidth : .33,
										layout : 'form',
										labelWidth : 60, // 标签宽度
										defaultType : 'textfield',
										border : false,
										items : [{
													fieldLabel : '提交人', // 标签
													id : 'schefounder',
													name : 'schefounder', // name:后台根据此name属性取值
													// 添加一个监听事件
//													listeners : {
//														'focus' : function(obj) {
//													personAddInit();
//														}
//													},
													anchor : '100%' // 宽度百分比
												}]
									}, {
										columnWidth : .33,
										layout : 'form',
										labelWidth : 60, // 标签宽度
										defaultType : 'textfield',
										border : false,
										items : [new Ext.form.ComboBox({
													hiddenName : 'schestatus',
													fieldLabel : '状态',
													emptyText : '请选择',
													triggerAction : 'all',
													store : new Ext.data.SimpleStore({
																fields : ['name',
																		'code'],
																		data : [['待办', '1'],
																				['结束', '2']]
															}),
													displayField : 'name',
													valueField : 'code',
													mode : 'local',
													forceSelection : false, // 选中内容必须为下拉列表的子项
													editable : false,
													typeAhead : true,
													// value:'0002',
													resizable : true,
													anchor : '100%'
												})]
									}]
						}, {
							layout : 'column',
							border : false,
							items : [{
								columnWidth : .33,
								layout : 'form',
								labelWidth : 60, // 标签宽度
								defaultType : 'textfield',
								border : false,
								items : [{
									xtype : 'datetimefield',
									fieldLabel : '开始时间', // 标签
									name : 'begindate', // name:后台根据此name属性取值
									anchor : '100%' // 宽度百分比
								}]
							}, {
								columnWidth : .33,
								layout : 'form',
								labelWidth : 60, // 标签宽度
								defaultType : 'textfield',
								border : false,
								items : [{
									xtype : 'datetimefield',
									fieldLabel : '结束时间', // 标签
									name : 'enddate', // name:后台根据此name属性取值
									anchor : '100%' // 宽度百分比
										}]
							}, {
								columnWidth : .33,
								layout : 'form',
								labelWidth : 60, // 标签宽度
								defaultType : 'textfield',
								border : false,
								items : []
							}]
						}],

						buttons : [{
									text : '查询',
									iconCls : 'previewIcon',
									handler : function() {
										queryBalanceInfo(qForm.getForm());
									}
								}, {
									text : '重置',
									iconCls : 'tbar_synchronizeIcon',
									handler : function() {
										qForm.getForm().reset();
									}
								}]
					});

			// 定义自动当前页行号
			var rownum = new Ext.grid.RowNumberer({
						header : 'NO',
						width : 28
					});

			// 定义列模型
			var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(),{
				header : '提交人',
				dataIndex : 'schefounder',
				sortable : true
			}, {
				header : '标题',
				dataIndex : 'schetitle'
			}, {
				header : '紧急程度',
				dataIndex : 'scheurgent',
				width : 60,
				renderer : SCHEURGENTRender
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
				width : 60,
				renderer : SCHESTATUSRender
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
									url : 's.xzb?reqCode=queryAllScheduleList'
								}),
						// 数据读取器
						reader : new Ext.data.JsonReader({
									totalProperty : 'TOTALCOUNT', // 记录总数
									root : 'ROOT' // Json中的列表数据根节点
								}, [{
											name : 'schefounder'
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
			store.on('beforeload', function() {
						this.baseParams = qForm.getForm().getValues();
					});
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
			
			// 布局
			var viewport = new Ext.Viewport({
						layout : 'border',
						items : [qForm, grid]
					});

			// 查询表格数据
			function queryBalanceInfo(pForm) {
				var params = pForm.getValues();
				params.start = 0;
				params.limit = bbar.pageSize;
				store.load({
							params : params
						});
			}
			
			//鼠标双击
			grid.on('rowdblclick', function(grid, rowIndex, event) {
				editInit();
			});
			
			grid.on('sortchange', function() {
				// grid.getSelectionModel().selectFirstRow();
			});
			
			var scheUrgentCombo = new Ext.form.ComboBox({
				name : 'scheurgent',
				hiddenName : 'scheurgent',
				store : SCHEURGENTStore,
				mode : 'local',
				triggerAction : 'all',
				valueField : 'value',
				displayField : 'text',
				value : '1',
				fieldLabel : '紧急程度',
				emptyText : '请选择...',
				allowBlank : false,
				labelStyle : micolor,
				forceSelection : true,
				editable : false,
				typeAhead : true,
				anchor : "99%"
			});
			
			var remindCombo = new Ext.form.ComboBox({
				name : 'remind',
				hiddenName : 'remind',
				store : REMINDStore,
				mode : 'local',
				triggerAction : 'all',
				valueField : 'value',
				displayField : 'text',
				value : '1',
				fieldLabel : '提醒方式',
				emptyText : '请选择...',
				allowBlank : false,
				labelStyle : micolor,
				forceSelection : true,
				editable : false,
				typeAhead : true,
				anchor : "99%"
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
						}, scheUrgentCombo, {
							xtype : 'datetimefield',
							fieldLabel : '开始时间',
							name : 'begindate', // name:后台根据此name属性取值
							allowBlank : false,
							anchor : '99%'
						},  {
							xtype : 'datetimefield',
							fieldLabel : '结束时间',
							name : 'enddate', // name:后台根据此name属性取值
							allowBlank : false,
							anchor : '99%'
						}, {
							fieldLabel : '内容',
							name : 'content',
							xtype : 'textarea',
							allowBlank : false,
							height:200,
							anchor : '99%'
						}, {
							id : 'windowmode',
							name : 'windowmode',
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
				width : 700,
				height : 490,
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
					text : '保存',
					id : 'btnSave',
					iconCls : 'acceptIcon',
					handler : function() {
						if (runMode == '0') {
							Ext.Msg.alert('提示',
									'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
							return;
						}
						updateScheItem(1);
					}
				}, {
					text : '关闭',
					iconCls : 'deleteIcon',
					handler : function() {
						addScheWindow.hide();
					}
				}]
			});
			
	
			
			/**
			 * 修改日程初始化
			 */
			function editInit() {
				var record = grid.getSelectionModel().getSelected();
				//clearForm(addScheFormPanel.getForm());
				addScheFormPanel.getForm().loadRecord(record);
				addScheWindow.show();
				addScheWindow.setTitle('<span class="commoncss">查看日程</span>');
				Ext.getCmp('windowmode').setValue('edit');
				Ext.getCmp('btnSave').hide();
			}

			/**
			 * 修改日程数据
			 */
			function updateScheItem(type) {
				if (!addScheFormPanel.form.isValid()) {
					return;
				}
				begind = Ext.getCmp('begind').getValue();
				endd = Ext.getCmp('endd').getValue();
				if (begind > endd) {
					Ext.Msg.alert('提示', '结束时间必须大于开始时间!');
					return;
				}
					Ext.Msg.confirm('确认', '确认修改日程？',
							function(btn, text) {
								if (btn == 'yes') {
									if(type == '1'){
										Ext.getCmp('updatemode').setValue('save');
									}
									if(type == '2'){
										Ext.getCmp('updatemode').setValue('finish');
									}
									if(type == '3'){
										Ext.getCmp('updatemode').setValue('delete');
									}
									update();
								} else {
									return;
								}
							});
			}

			/**
			 * 更新日程
			 */
			function update() {
				addScheFormPanel.form.submit({
							url : './s.ered?reqCode=updateScheItem',
							waitTitle : '提示',
							method : 'POST',
							waitMsg : '正在处理数据,请稍候...',
							success : function(form, action) {
								addScheWindow.hide();
								store.reload();
								form.reset();
								Ext.MessageBox.alert('提示', action.result.msg);
							},
							failure : function(form, action) {
								var msg = action.result.msg;
								Ext.MessageBox.alert('提示', '日程数据修改失败:<br>' + msg);
							}
						});
			}
			
						
			/**
			 * 选择人员初始化
			 */
			function personAddInit() {
				addScheWindow.show();
				addScheWindow.setTitle('<span class="commoncss">选择人员</span>');
			}

		});