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
	var dirId='';
	var sm = new Ext.grid.CheckboxSelectionModel();
	var cm = new Ext.grid.ColumnModel([new Ext.grid.RowNumberer(), sm, {
				header : '编辑',
				dataIndex : 'docid',
				renderer : function(value, cellmeta, record) {
					if (login_account == parent.DEFAULT_DEVELOP_ACCOUNT
							|| login_account == parent.DEFAULT_ADMIN_ACCOUNT) {
						if (record.data['usertype'] == '1') {
							return '<img src="./resource/image/ext/edit2.png"/>';
						}
					}
					return '<a href="javascript:void(0);"><img src="./resource/image/ext/edit1.png"/></a>';
				},
				width : 35
			},{
				header : '编号',
				dataIndex : 'docid',
				hidden : false,
				hidden : false,
				width : 80,
				sortable : true
			},{
				header : '标题',
				dataIndex : 'doctitle',
				width : 140,
				sortable : true
			}, {
				header : '上传人',
				dataIndex : 'docauthor',
				width : 100,
				sortable : true
			}, {
				header : '时间',
				dataIndex : 'updatetime',
				width : 140,
				sortable : true
			},{
				dataIndex : 'doccontent',
				hidden	  : true
			},{
				dataIndex : 'attaid',
				hidden	  : true
			},{
				dataIndex : 'username',
				hidden	  : true	
			},{
				dataIndex : 'deptname',
				hidden	  : true
			}]);
	
	/**
	 * windowmode用于判断编辑或新增
	 */
	var addUserFormPanel = new Ext.form.FormPanel({
		id : 'addUserFormPanel',	
		name : 'addUserFormPanel',
		defaultType : 'textfield',
		labelAlign : 'right',
		items : [{
					id : 'windowmode',
					name : 'windowmode',
					hidden : true
				}]
	});

	/**
	 * 数据存储
	 */
	var store = new Ext.data.Store({
				proxy : new Ext.data.HttpProxy({
							url : './k.xzb?reqCode=queryDocsForManage&person='+person+'&loginuserid='+login_userid+''
						}),
				reader : new Ext.data.JsonReader({
							totalProperty : 'TOTALCOUNT',
							root : 'ROOT'
						}, [{
							name:	'docid'
						},{
							name : 'doctitle'
						},{
							name : 'doccontent'
						},{
							name : 'docauthor'
						}, {
							name : 'updatetime'
						},{
							name : 'attaid'
						},{name:'username'}
						,{name:'deptname'}])
			});

	// 翻页排序时带上查询条件
	/*修改后这个地方有BUG暂时注释掉
	store.on('beforeload', function() {
				this.baseParams = {
					queryParam : Ext.getCmp('queryParam').getValue()
				};
			});
			
	*/		
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
				value : '10',
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
				emptyMsg : "没有符合条件的记录",
				items : ['-', '&nbsp;&nbsp;', pagesize_combo]
			});
	
	//SWFUpload窗口
	
	// 表格工具栏
	var tbar = new Ext.Toolbar({
		items : [ {
					text : '新增',
					id:'grid_add',
					iconCls : 'page_addIcon',
					handler : function() {
						var selectModel = dirTree.getSelectionModel();
						var selectNode = selectModel.getSelectedNode();
						var dirId=selectNode.attributes.id;
						if('001003001'==dirId){
							Ext.Msg.alert('根目录 不可新建文档!');
							return;
						}else{
						
							addDocInit();
						}
					}
		}, '-', {
			text : '删除',
			id:'grid_del',
			iconCls : 'deleteIcon',
			handler : function() {
				deleteDocItems();
			}
		},'->', {
			xtype : 'textfield',
			id : 'filetitle',
			name : 'filetitle',
			emptyText : '请输入文件标题',
			width : 150,
			enableKeyEvents : true,
			// 响应回车键
			listeners : {
				specialkey : function(field, e) {
					if (e.getKey() == Ext.EventObject.ENTER) {
						queryDocs();
					}
				}
			}
		}, {
			text : '查询',
			iconCls : 'page_findIcon',
			handler : function() {
				queryDocs();
			}
		}, {
			text : '刷新',
			iconCls : 'page_refreshIcon',
			handler : function() {
				store.reload();
			}
		} ]
	});
		
	
	var limitisTbar=new Ext.Toolbar({
		items : [ {
			text : '部门',
			iconCls : 'page_addIcon',
			handler : function() {
				var dep=limitisPanel.getForm().findField("dep").getValue();
				Ext.getCmp('depLimitis').setValue(dep);
				
				Ext.getCmp('limitisType').setValue('1');
				Ext.getCmp('peopleLimitis').setValue('');
				if(dep=='')
					{	
						Ext.Msg.alert('提示','选择部门类型');
						return;
					}
							var formItemSelector = new Ext.Panel({ 
								labelWidth: 3,
								width:600,
								items:[{
									xtype:"itemselector",
									name:"itemselector",
									fieldLabel:"",
									msWidth:250,
									msHeight:450,
									valueField:"id",
									displayField:"text",
									leftTreeRoot:'雪中豹集成与开发平台',
									leftTreeId:'001',
									imagePath:"./knowledge/js",
									//switchToFrom:true,
									toLegend:"权限列表",
									fromLegend:"选择权限",
									dataUrl : './organization.xzb?reqCode=departmentTreeInit',//部门树初始化
									saveLimitisUrl:'./k.xzb?reqCode=saveLimitis'//保存的路径
									
								}]
							});
							
							var test=new Ext.Window({
								layout : 'fit',
								width : 500,
								height : 500,
								resizable : false,
								draggable : true,
								closeAction : 'hide',
								title : '<span class="commoncss">修改目录</span>',
								// iconCls : 'page_addIcon',
								modal : true,
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
								items : [formItemSelector],
								buttons : [{
									text : '关闭',
									iconCls : 'deleteIcon',
									handler : function() {
										test.close();
									}
								}]
								
							});
				test.show();
				
				
			}
}, '-', {
	text : '人员',
	iconCls : 'page_addIcon',
	handler : function() {
		var people=limitisPanel.getForm().findField("people").getValue();
		Ext.getCmp('peopleLimitis').setValue(people);
		Ext.getCmp('limitisType').setValue('2');
		Ext.getCmp('depLimitis').setValue('');
		var limitisDocId=limitisPanel.getForm().findField("limitisDocId").getValue();
		if(people=='')
			{
					Ext.Msg.alert('提示','选择人员类型');
					return;
			}
		
		var formItemSelector = new Ext.Panel({ 
			labelWidth: 3,
			width:600,
			items:[{
				xtype:"itemselector",
				name:"itemselector",
				fieldLabel:"",
				msWidth:250,
				msHeight:450,
				valueField:"id",
				displayField:"text",
				leftTreeRoot:'雪中豹集成与开发平台',
				leftTreeId:'001',
				imagePath:"./knowledge/js",
				//switchToFrom:true,
				toLegend:"权限列表",
				fromLegend:"选择权限",
				dataUrl : './organization.xzb?reqCode=departmentTreeInit_',
				saveLimitisUrl:'./k.xzb?reqCode=saveLimitis'//保存的路径
			}]
		});
		
		var test=new Ext.Window({
			layout : 'fit',
			width : 500,
			height : 500,
			resizable : false,
			draggable : true,
			closeAction : 'hide',
			title : '<span class="commoncss">分配权限</span>',
			// iconCls : 'page_addIcon',
			modal : true,
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
			items : [formItemSelector],
			buttons : [{
				text : '关闭',
				iconCls : 'deleteIcon',
				handler : function() {
					test.close();
				}
			}]
			
		});
test.show();
		
	}
}]
});

	
	
	
	var depCombo = new Ext.form.ComboBox({
		name : 'dep',
		hiddenName : 'dep',
		store:new Ext.data.SimpleStore({
			fields:['value','text'],
			data:[
			      ['1','修改'],['2','查看']
			      ]
			
		}),
		mode : 'local',
		triggerAction : 'all',
		valueField : 'value',
		displayField : 'text',
		value : '',
		fieldLabel : '部门选择类型',
		emptyText : '请选择...',
		labelStyle : micolor,
		allowBlank : true,
		forceSelection : true,
		editable : false,
		typeAhead : true,
		anchor : "99%"
	});
	
	var peopleCombo = new Ext.form.ComboBox({
		name : 'people',
		hiddenName : 'people',
		store:new Ext.data.SimpleStore({
			fields:['value','text'],
			data:[
			      ['1','修改'],['2','查看']
			      ]
			
		}),
		mode : 'local',
		triggerAction : 'all',
		valueField : 'value',
		displayField : 'text',
		value : '',
		fieldLabel : '人员选择设置',
		emptyText : '请选择...',
		labelStyle : micolor,
		allowBlank : true,
		forceSelection : true,
		editable : false,
		typeAhead : true,
		anchor : "99%"
	});
	
	var limitisPanel=new Ext.form.FormPanel({
		id:'limtisPanel',
		name:'limtisPanel',
		height:300,
		tbar:[limitisTbar],
		//defaultType:'textfield',
		bodyStyle : 'padding:5 5 5 5',
		items:[
		       {
				fieldLabel : '',
				name : 'limitisDocId',
				id : 'limitisDocId',
				xtype:'textfield',
				hidden:true
		}, {
			fieldLabel : '',
			name : 'limitisType',
			id : 'limitisType',
			xtype:'textfield',
			hidden:true
	},{
			fieldLabel : '',
			name : 'dep',
			id : 'depLimitis',
			xtype:'textfield',
			hidden:true
	},{
		fieldLabel : '',
		name : 'people',
		id : 'peopleLimitis',
		xtype:'textfield',
		hidden:true
},{fieldLabel : '',
	name : 'DoclimitisType',
	id : 'DoclimitisType',
	xtype:'textfield',
	hidden:true
	},{fieldLabel : '',
		name : 'DirlimitisType',
		id : 'DirlimitisType',
		xtype:'textfield',
		hidden:true
		}]
			
	});
	
	var limitisWindow=new Ext.Window({
		layout : 'fit',
		width : 400,
		height : 300,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		modal : true,
		title : '<span class="commoncss">选择共享</span>',
		// iconCls : 'page_addIcon',
		collapsible : true,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 200 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [limitisPanel],
		buttons : [{
			text : '关闭',
			iconCls : 'deleteIcon',
			handler : function() {
				limitisWindow.hide();
			}
		}]
	});
	
	/**
	 * 
	 */
	
var selectPanel=new Ext.form.FormPanel({
		
		id : 'selectPanel',
		name : 'selectPanel',
		defaultType : 'textfield',
		labelAlign : 'right',
		readOnly:true,
		labelWidth : 65,
		frame : false,
		bodyStyle : 'padding:5 5 0',
		items : [{
	    	   id:'selectDeps',
	    	   width:600,
	    	   readOnly:true,
	    	   fieldLabel:'选择部门',
	    	   listeners: {
	    		      focus: function() {
	    		    	var typeOfRadio=  Ext.getCmp('radioForm').getValue();
	    		    	Ext.getCmp('DoclimitisType').setValue('0');//代表部门
	    		       if(typeOfRadio=='2')
	    		    	   {
	    		    	   var formItemSelector = new Ext.Panel({ 
								labelWidth: 3,
								width:600,
								items:[{
									xtype:"knowledgeShare",
									name:"itemselector",
									fieldLabel:"",
									msWidth:250,
									msHeight:450,
									valueField:"id",
									displayField:"text",
									leftTreeRoot:'雪中豹集成与开发平台',
									leftTreeId:'001',
									imagePath:"./knowledge/js",
									//switchToFrom:true,
									toLegend:"权限列表",
									fromLegend:"选择权限",
									dataUrl : './organization.xzb?reqCode=departmentTreeInit',//部门树初始化
									saveLimitisUrl:'./k.xzb?reqCode=saveLimitis'//保存的路径
									
								}]
							});
							
							var test=new Ext.Window({
								layout : 'fit',
								id:'selectDeptsWindow',
								width : 500,
								height : 500,
								resizable : false,
								draggable : true,
								closeAction : 'close',
								title : '<span class="commoncss">部门分配</span>',
								// iconCls : 'page_addIcon',
								modal : true,
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
								items : [formItemSelector],
								buttons : [{
									text : '关闭',
									iconCls : 'deleteIcon',
									handler : function() {
										test.close();
									}
								}]
								
							});
				test.show();
	    		    	   		
	    		    	   }
	    		      }
	    		    }
	       },{
	    	   id:'selectPersons',
	    	   width:600,
	    	   readOnly:true,
	    	   fieldLabel:'选择人员 ',
	    	   listeners: {
	    		      focus: function() {
	    		    	  var typeOfRadio=  Ext.getCmp('radioForm').getValue();
	    		    	  Ext.getCmp('DoclimitisType').setValue('1');//代表人员
		    		      
		    		       if(typeOfRadio=='2')
		    		    	   {
		    		    	   var formItemSelector = new Ext.Panel({ 
									labelWidth: 3,
									width:600,
									items:[{
										xtype:"knowledgeShare",
										name:"itemselector",
										fieldLabel:"",
										msWidth:250,
										msHeight:450,
										valueField:"id",
										displayField:"text",
										leftTreeRoot:'雪中豹集成与开发平台',
										leftTreeId:'001',
										imagePath:"./knowledge/js",
										//switchToFrom:true,
										toLegend:"权限列表",
										fromLegend:"选择权限",
										dataUrl : './organization.xzb?reqCode=departmentTreeInit_',//部门树初始化
										saveLimitisUrl:'./k.xzb?reqCode=saveLimitis'//保存的路径
										
									}]
								});
								
								var test=new Ext.Window({
									layout : 'fit',
									id:'selectPersonsWindow',
									width : 500,
									height : 500,
									resizable : false,
									draggable : true,
									closeAction : 'close',
									title : '<span class="commoncss">人员分配</span>',
									// iconCls : 'page_addIcon',
									modal : true,
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
									items : [formItemSelector],
									buttons : [{
										text : '关闭',
										iconCls : 'deleteIcon',
										handler : function() {
											test.close();
										}
									}]
									
								});
					test.show();
		    		    	   		
		    		    	   }

	    		    	  
	    		    	  
	    		    	  
	    		      }
	    		    }
	       }]
	       
		
		
	});
	
	
	
	var radioPanel=new Ext.form.FormPanel({
		id:'',
		height:50,
		items:[
		       {
		    	 xtype:'radiogroup',
		    	 id:'checkRadio',
		    	 //defaultType:'radiogroup',
		    	// layout: 'form',
		    	 hideLabels:true,//checked:true
		    	 items:[
		    	        {boxLabel:'不共享',id:'state1',name:'radio',inputValue:'0',checked:false,handler:function(){
		    	        	
		    	        	 var a=Ext.getCmp('state1');
		    	        	 if(a.checked==true)
		    	        		 {
				    	        	 Ext.getCmp('radioForm').setValue(a.inputValue);			    	        	
				    	        	// selectPanel.hide();
				    	        	// addDocWindow.body.update();
		    	        		 }
		    	        	 
		    	        }},
		    	        {boxLabel:'共享所有人',id:'state2',name:'radio',inputValue:'1',checked:false,handler:function(){
		    	        	
		    	        	var a=Ext.getCmp('state2');
		    	        	 if(a.checked==true)
		    	        		 {
					    	        	Ext.getCmp('radioForm').setValue(a.inputValue);					    	        					    	
					    	        	// selectPanel.hide();
					    	        
					    	        	
		    	        		 }
		    	        }},
		    	        {boxLabel:'部分共享',id:'state3',name:'radio',checked:false,inputValue:'2',handler:function(){
		    	        	//limitisWindow.show();
		    	        	/**
		    	        	if(Ext.getCmp('radiotype').getValue()=='dir')
		    	        		{
		    	        			Ext.getCmp('limitisDocId').setValue(Ext.getCmp('radioDirId').getValue());
		    	        		}else if(Ext.getCmp('radiotype').getValue()=='doc')
		    	        			{
		    	        			Ext.getCmp('limitisDocId').setValue(Ext.getCmp('radioDocId').getValue());
		    	        			}
							*/
		    	        	var a=Ext.getCmp('state3');
		    	        	 if(a.checked==true)
		    	        		 {
		    	        		 	Ext.getCmp('radioForm').setValue(a.inputValue);
		    	        		 	//limitisWindow.show();		    	        		 	
		    	        		 	// selectPanel.show();		    	        		 	
		    	        		 
		    	        		 	
		    	        		 }
		    	        		 
		    	        	
		    	        	  
		    	        }}
		    	        
		    	        ] ,
		    	
		       },{
		    	   xtype:'textfield',
		    	   id:'radioForm',
		    	   hidden:true
		       },{
		    	   xtype:'textfield',
		    	   id:'radioDirId',
		    	   hidden:true
		    	   
		       },{
		    	   xtype:'textfield',
		    	   id:'radioDocId',
		    	   hidden:true
		    	   
		       },{
		    	   xtype:'textfield',
		    	   id:'radiotype',
		    	   hidden:true
		    	   
		       }
		       ]
	});
	
	var radioPanel_=new Ext.form.FormPanel({
		id:'',
		items:[
		       {
		    	 xtype:'fieldset',
		    	 id:'checkRadio',
		    	 defaultType:'radio',
		    	 hideLabels:true,//checked:true
		    	 items:[
		    	        {boxLabel:'不共享，（默认情况下）',id:'state1_',name:'radio',inputValue:'0',checked:false,handler:function(){
		    	        	
		    	        	 var a=Ext.getCmp('state1_');
		    	        	 if(a.checked==true)
		    	        	 Ext.getCmp('radioForm').setValue(a.inputValue);
		    	        	
		    	        	 
		    	        }},
		    	        {boxLabel:'共享所有人',id:'state2_',name:'radio',inputValue:'1',checked:false,handler:function(){
		    	        	
		    	        	var a=Ext.getCmp('state2_');
		    	        	 if(a.checked==true)
		    	        		 Ext.getCmp('radioForm').setValue(a.inputValue);
		    	        	 
		    	        	  	
		    	        }},
		    	        {boxLabel:'选择部门',id:'state4_',name:'radio',inputValue:'2',checked:false,handler:function(){
		    	        	var a=Ext.getCmp('state4_');
		    	        	
		    	        	 if(a.checked==true)
		    	        		 {
		    	        		 Ext.getCmp('DirlimitisType').setValue('0');//目录分配部门
		    	        		 	Ext.getCmp('radioForm').setValue(a.inputValue);
		    	        		 	 var formItemSelector = new Ext.Panel({ 
		 								labelWidth: 3,
		 								width:600,
		 								items:[{
		 									xtype:"knowledgeShare_",
		 									name:"itemselector",
		 									fieldLabel:"",
		 									msWidth:250,
		 									msHeight:450,
		 									valueField:"id",
		 									displayField:"text",
		 									leftTreeRoot:'雪中豹集成与开发平台',
		 									leftTreeId:'001',
		 									imagePath:"./knowledge/js",
		 									//switchToFrom:true,
		 									toLegend:"权限列表",
		 									fromLegend:"选择权限",
		 									dataUrl : './organization.xzb?reqCode=departmentTreeInit',//部门树初始化
		 									saveLimitisUrl:'./k.xzb?reqCode=saveLimitis'//保存的路径
		 									
		 								}]
		 							});
		 							
		 							var test=new Ext.Window({
		 								layout : 'fit',
		 								id:'selectDeptsWindow_',
		 								width : 500,
		 								height : 500,
		 								resizable : false,
		 								draggable : true,
		 								closeAction : 'hide',
		 								title : '<span class="commoncss">目录部门共享</span>',
		 								// iconCls : 'page_addIcon',
		 								modal : true,
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
		 								items : [formItemSelector],
		 								buttons : [{
		 									text : '关闭',
		 									iconCls : 'deleteIcon',
		 									handler : function() {
		 										Ext.getCmp('selectDeptsWindow_').close();
		 									}
		 								}]
		 								
		 							});
		 				test.show();
		    	        		 	
		    	        		 	
		    	        		 	
		    	        		 }
		    	        	 
		    	        	  	
		    	        }},
		    	      
		    	        {boxLabel:'选择人员',id:'state3_',name:'radio',checked:false,inputValue:'2',handler:function(){
		    	        	//limitisWindow.show();						
		    	        	var a=Ext.getCmp('state3_');
		    	        	
		    	        	 if(a.checked==true)
		    	        		 {
		    	        		 Ext.getCmp('DirlimitisType').setValue('1');//目录分配人员
		    	        		 	Ext.getCmp('radioForm').setValue(a.inputValue);		    	        		 	
		    	        		 	 var formItemSelector = new Ext.Panel({ 
		 								labelWidth: 3,
		 								width:600,
		 								items:[{
		 									xtype:"knowledgeShare_",
		 									name:"itemselector",
		 									fieldLabel:"",
		 									msWidth:250,
		 									msHeight:450,
		 									valueField:"id",
		 									displayField:"text",
		 									leftTreeRoot:'雪中豹集成与开发平台',
		 									leftTreeId:'001',
		 									imagePath:"./knowledge/js",
		 									//switchToFrom:true,
		 									toLegend:"权限列表",
		 									fromLegend:"选择权限",
		 									dataUrl : './organization.xzb?reqCode=departmentTreeInit_',//部门树初始化
		 									saveLimitisUrl:'./k.xzb?reqCode=saveLimitis'//保存的路径
		 									
		 								}]
		 							});
		 							
		 							var test=new Ext.Window({
		 								layout : 'fit',
		 								id:'selectPersonsWindow_',
		 								width : 500,
		 								height : 500,
		 								resizable : false,
		 								draggable : true,
		 								closeAction : 'hide',
		 								title : '<span class="commoncss">目录人员共享</span>',
		 								// iconCls : 'page_addIcon',
		 								modal : true,
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
		 								items : [formItemSelector],
		 								buttons : [{
		 									text : '关闭',
		 									iconCls : 'deleteIcon',
		 									handler : function() {
		 										Ext.getCmp('selectPersonsWindow_').close();
		 									}
		 								}]
		 								
		 							});
		 				test.show();
		    	        		 	
		    	        		 	
		    	        		 	
		    	        		 }
		    	        	  
		    	        }}
		    	        
		    	        ] ,
		    	
		       },{
		    	   xtype:'textfield',
		    	   id:'radioForm',
		    	   hidden:true
		       },{
		    	   xtype:'textfield',
		    	   id:'radioDirId_',
		    	   hidden:true
		    	   
		       },{
		    	   xtype:'textfield',
		    	   id:'radioDocId',
		    	   hidden:true
		    	   
		       },{
		    	   xtype:'textfield',
		    	   id:'radiotype',
		    	   hidden:true
		    	   
		       }
		       ],
		
		
		
	});
	
	
	
	var radiowindow=new Ext.Window({
		layout : 'fit',
		width : 400,
		height : 200,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		modal : true,
		title : '<span class="commoncss">共享设置</span>',
		// iconCls : 'page_addIcon',
		collapsible : true,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 620 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,
		items : [radioPanel_],
		buttons : [{
			text : '保存',
			iconCls : 'acceptIcon',
			handler : function() {
				var shareType=Ext.getCmp('radioForm').getValue();
				if(shareType=='')
					{
						alert("选择一种共享类型");
						return;
					}
				var dirId=Ext.getCmp('radioDirId_').getValue();
				//var type=Ext.getCmp('radiotype').getValue();
				//var docId=Ext.getCmp('radioDocId').getValue();
				
				Ext.Ajax
				.request( {
					url : './k.xzb?reqCode=updateShareType',
					success : function(response) {
						var resultArray = Ext.util.JSON
								.decode(response.responseText);
						
						Ext.Msg.alert('提示',
								resultArray.msg);
					},
					failure : function(response) {
						var resultArray = Ext.util.JSON
								.decode(response.responseText);
						Ext.Msg.alert('提示',
								resultArray.msg);
					},
					params : {
						shareType:shareType,
						dirId:dirId
						
					}
				});
			
			
			
			}
		},{
			text : '关闭',
			iconCls : 'deleteIcon',
			handler : function() {
				radiowindow.hide();
			}
		}]
		
		
		
		
	});
	
	
	
	
	
	
	
	/**
	 * 
	 */
	var grid = new Ext.grid.GridPanel({
		title : '<span class="commoncss">文件列表</span>',
		height : 500,
		frame : true,
		autoScroll : true,
		region : 'center', // 和VIEWPORT布局模型对应，充当center区域布局
		store : store, // 数据存储
		stripeRows : true, // 斑马线
		cm : cm, // 列模型
		sm : sm, // 复选框
		tbar : tbar, // 表格工具栏
		bbar : bbar,// 分页工具栏
		viewConfig : {
		// 不产横向生滚动条, 各列自动扩展自动压缩, 适用于列数比较少的情况
		// forceFit : true
		},
		loadMask : {
			msg : '正在加载表格数据,请稍等...'
		}
	});
	store.load({
		params : {
			start : 0,
			limit : bbar.pageSize,
			firstload : 'true'
		}
	});
	
	grid.on('rowdblclick', function(grid, rowIndex, event) {
		editDocInit();
	});

	bbar.on("change", function() {
				// grid.getSelectionModel().selectFirstRow();
			});
	
	var panel = new Ext.form.FormPanel({
		defaultType : 'textfield',
		layout : 'fit',
		frame : true,
		height:250,
		items : [{
			id : 'docContentT',
			name : 'docContentT',
			xtype : 'htmleditor',
			plugins : [new Ext.ux.form.HtmlEditor.Link(),
					new Ext.ux.form.HtmlEditor.Image(),
					new Ext.ux.form.HtmlEditor.Table(),
					new Ext.ux.form.HtmlEditor.HR(),
					new Ext.ux.form.HtmlEditor.Word(),
					new Ext.ux.form.HtmlEditor.RemoveFormat()]
		}]
	});
	
	var addDocFormPanel = new Ext.form.FormPanel( {
		defaultType : 'textfield',
        id     : 'panel2',
       // autoheight:true,
        height:30,
        frame : true,
        items : [ {
			fieldLabel : '标题 ',
			name : 'docTitle',
			id : 'docTitle',
			allowBlank : false,
			labelStyle: micolor,
			anchor : '99%'
		} , {
			name : 'docDirId',
			id : 'docDirId',
			allowBlank : false,
			hidden:	'true'

		},{
			name : 'attaId',
			id : 'attaId',
			hidden:	'true'

		},{
			name : 'docId',
			id : 'docId',
			hidden:	'true'

		},{
			name : 'typeOfDoc',
			id : 'typeOfDoc',
			hidden:	'true'

		} ]
	});
	
	var addDocFormPanelDown = new Ext.form.FormPanel( {
        id     : 'addDocFormPanelDown',
        autoheight:true,
        frame : true,
        items : [ {
        	  xtype:"label",
	          fieldLabel: '附件', 
	          id:'attaName', 
	          name: '附件', 
	          width:275, 
	          html : ''
	        } ]
	});

	var addDocWindow = new Ext.Window( {
		width : 700,
//		height : 470,
		autoheight:true,
		resizable : false,
		draggable : true,
		closable : true,
		closeAction : 'hide',
		title : '<span class="commoncss">新增文档</span>',
		// iconCls : 'page_addIcon',
		collapsible : true,
		modal : true,
		titleCollapse : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		animateTarget : Ext.getBody(),
		constrain : true,//,radioPanel,selectPanel
		items : [ addDocFormPanel,panel,addDocFormPanelDown,radioPanel,selectPanel],
		buttons : [
				{
					text : '保存',
					iconCls : 'acceptIcon',
					id : 'btn_id_save_update',
					handler : function() {
						if (runMode == '0') {
							Ext.Msg.alert('提示',
									'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
							return;
						}
						var mode = Ext.getCmp('windowmode').getValue();
						if (mode == 'add')
							{
							
								saveDocItem();
							}
						else
							updateDocItem();
					}
				}, {
					text : '上传附件',
					id : 'uploadIcon',
					iconCls : 'uploadIcon',
					handler : function() {
						var selectModel = dirTree.getSelectionModel();
						var selectNode = selectModel.getSelectedNode();
						dirId=selectNode.attributes.id;
						/**
						var swfu = new SWFUpload({
							xtype : 'uploadpanel',
							uploadUrl : webContext + '/demo/otherDemo.xzb?reqCode=doUploadBasedFlah',
							filePostName : 'swfUploadFile',
							flashUrl : webContext + '/resource/myux/uploadpanel/swf/swfupload.swf',
							fileSize : '200MB',
							border : false,
							fileTypes : '*.*', // 在这里限制文件类型:'*.jpg,*.png,*.gif'
							fileTypesDescription : '所有文件',
							postParams : {
								postType 	: 1,
								dirId		:dirId
							// path : 'upload\\' //这只是个参数,由后台来读取;也可以直接由后台来分配路径
							}
						});
						*/
						
						var win_swfupload = new Ext.Window({
							id	  : 'swfupload',
							title : '<span class="commoncss">上传</span>',
							width : 500,
							height : 350,
							resizable : false,
							layout : 'fit',
							constrain : true,
							closeAction : 'close',
							maximizable : true,
							modal:true,
							listeners : { 
							    'close' : function(obj) {
//							      store.reload();
							      var docName=$("#docAttaName").val();
							      var dirName=$("#docDirName").val();
							      //console.info("关闭上传框dirName=");
							      $("#attaName").html('<a href=download?path='+encodeURI(encodeURI(dirName))+encodeURI(encodeURI(docName))+'>'+docName+'</a>');
							    }
							},
							items : [ {
								xtype : 'uploadpanel',
								uploadUrl : webContext + '/demo/otherDemo.xzb?reqCode=doUploadBasedFlah',
								filePostName : 'swfUploadFile',
								flashUrl : webContext + '/resource/myux/uploadpanel/swf/swfupload.swf',
								fileSize : '200MB',
								border : false,
								fileTypes : '*.*', // 在这里限制文件类型:'*.jpg,*.png,*.gif'
								fileTypesDescription : '所有文件',
								postParams : {
									postType 	: 1,
									dirId		:dirId,
									loginuserid:login_userid,
									person:person
								// path : 'upload\\' //这只是个参数,由后台来读取;也可以直接由后台来分配路径
								}
							} ]
						});
						win_swfupload.show();
						//每次上传前清空原来的
						$(".db-icn-trash").parent().click();
					}
				},{
					text:'FTP上传',
					id:'ftpUpLoad',
					iconCls:'uploadIcon',
					handler:function(){
						
						function deleteFile(){
							alert(233234);
						}
						
						var firstForm = new Ext.form.FormPanel({
							id : 'firstForm',
							name : 'firstForm',
							fileUpload : true, // 一定要设置这个属性,否则获取不到上传对象的
							labelWidth : 60,
							defaultType : 'textfield',
							labelAlign : 'right',
							bodyStyle : 'padding:5 5 5 5',
							items : [ {
								fieldLabel : '选择文件',
								id : 'file1',
								name : 'file1', // 必须为file1/file2/file3/file4/file5.目前Web标准上传模式支持最多5个文件的批量上传
								xtype : 'fileuploadfield', // 上传字段
								allowBlank : false,
								anchor : '100%'
							}, {
								fieldLabel : '文件标题',
								id : 'title',
								name : 'title',
								//disabled : true,
								fieldClass : 'x-custom-field-disabled',
								anchor : '100%'
							}, {
								fieldLabel : '文件描述',
								id : 'remark',
								name : 'remark',
								xtype : 'textarea',
								anchor : '100%'
							} ]
						});
						
						var firstWindow = new Ext.Window({
							title : '<span class="commoncss">上传[FTP协议]</span>', // 窗口标题
							layout : 'fit', // 设置窗口布局模式
							width : 500, // 窗口宽度
							height : 200, // 窗口高度
							closable : true, // 是否可关闭
							collapsible : true, // 是否可收缩
							maximizable : true, // 设置是否可以最大化
							closeAction : 'hide',
							animCollapse : true,
							animateTarget : Ext.getBody(),
							border : false, // 边框线设置
							modal:true,
							id:'ftpWindow',
							constrain : true, // 设置窗口是否可以溢出父容器
							// pageY : 20, // 页面定位X坐标
							pageX : document.body.clientWidth / 2 - 500 / 2, // 页面定位Y坐标
							
							items : [ firstForm ], // 嵌入的表单面板
							buttons : [ { // 窗口底部按钮配置
								text : '上传', // 按钮文本
								iconCls : 'uploadIcon', // 按钮图标
								handler : function() { // 按钮响应函数
									submitTheForm();
								}
							}, { // 窗口底部按钮配置
								text : '重置', // 按钮文本
								iconCls : 'tbar_synchronizeIcon', // 按钮图标
								handler : function() { // 按钮响应函数
									firstForm.form.reset();
								}
							} ]
						});
						firstWindow.show();
						
					}
					
				},{
					text:'删除附件',
					id:'ftpUpLoad',
					iconCls:'deleteIcon',
					handler:function(){
						$("#attaName").html('');
						var attid=Ext.getCmp('attaId').getValue();//Ext.getCmp("attid").getValue();
						Ext.Ajax
						.request( {
							url : './k.xzb?reqCode=deleteAtt',
							success : function(response) {
								var resultArray = Ext.util.JSON
										.decode(response.responseText);
								
								$("#attaName").html('');
								Ext.Msg.alert('提示',
										resultArray.msg);
							},
							failure : function(response) {
								var resultArray = Ext.util.JSON
										.decode(response.responseText);
								Ext.Msg.alert('提示',
										resultArray.msg);
							},
							params : {
								attaId:attid
							}
						});
						
						
						
					}
					
					
					
					
					
					
					
					
				},{
					text : '重置',
					id : 'btnReset',
					iconCls : 'tbar_synchronizeIcon',
					handler : function() {
						clearForm(addDocFormPanel.getForm());
					}
				}, {
					text : '关闭',
					iconCls : 'deleteIcon',
					handler : function() {
						addDocWindow.hide();
					}
				} ]
	});
	
	function uploadComplete(){
		alert("finished");
	}
	
	function addDocInit() {
		Ext.getCmp('btnReset').hide();
		clearForm(addDocFormPanel.getForm());
		clearForm(panel.getForm());
		var flag = Ext.getCmp('windowmode').getValue();
		//console.info("新增或编辑标志位="+flag);
		if($("#docAttaId").val()!=''){
			alert("234ff");
			$("#attaName").html('');
		}
		if('add'==flag){
			
			$("#attaName").html('');
		}
		if (typeof (flag) != 'undefined') {
			addDocFormPanel.form.getEl().dom.reset();
			panel.form.getEl().dom.reset();
			
		} else {
			clearForm(addDocFormPanel.getForm());
		}
		var selectModel = dirTree.getSelectionModel();
		var selectNode = selectModel.getSelectedNode();
		//Ext.getCmp('docDirId').setValue(selectNode.attributes.text);
		Ext.getCmp('docDirId').setValue(selectNode.attributes.id);
		Ext.getCmp('docDirId').hide();
		
		$("#docDirId").val(selectNode.attributes.id);
		/**
		var options = { 
			    success:    function(data) { 
			    	$("#docDirName").val(eval("("+data+")").filepath);
			    } 
			}; 
		$('#dirForm').ajaxForm(options);
		$("#dirForm").submit();
		*/
		
		selectPanel.hide();
		radioPanel.hide();
		//addDocWindow.doLayout();
		addDocWindow.show();
		
		addDocWindow
				.setTitle('<span class="commoncss">新增文件</span>');
		Ext.getCmp('windowmode').setValue('add');
		//Ext.getCmp('btnReset').show();
	}
	
	/**
	 * 保存文档
	 */
	function saveDocItem() {
		if (!addDocFormPanel.form.isValid()) {
			return;
		}
		addDocFormPanel.add({
			xtype: 'hidden',
			id: 'docContent',
			name: 'docContent',
			value: Ext.getCmp("docContentT").getValue()
			});
		addDocFormPanel.doLayout();
		addDocFormPanel.form.submit( {
			url : './k.xzb?reqCode=saveDocItem&typeOfDoc='+person+'',
			waitTitle : '提示',
			method : 'POST',
			waitMsg : '正在处理数据,请稍候...',
			success : function(form, action) {
				addDocWindow.hide();
				store.reload();
				window.location.reload();
				//refreshNode(Ext.getCmp('parentid').getValue());
				form.reset();
				Ext.MessageBox.alert('提示', action.result.msg);
			},
			failure : function(form, action) {
				var msg = action.result.msg;
				Ext.MessageBox.alert('提示', '部门数据保存失败:<br>' + msg);
			}
		});
	}
	
	/**
	 * 删除文档
	 */
	function deleteDocItems() {
		var rows = grid.getSelectionModel().getSelections();
		var fields = '';
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].get('Doctype') == '3') {
				fields = fields + rows[i].get('Docname') + '<br>';
			}
		}
		if (fields != '') {
			Ext.Msg.alert('提示', '<b>您选中的文档中包含如下系统内置的只读文档</b><br>' + fields
							+ '<font color=red>系统内置文档不能删除!</font>');
			return;
		}
		if (Ext.isEmpty(rows)) {
			Ext.Msg.alert('提示', '请先选中要删除的文档!');
			return;
		}
		var strChecked = jsArray2JsString(rows, 'docid');
		Ext.Msg
				.confirm(
						'请确认',
						'<span style="color:red"><b>提示:</b>删除文档将同时删除和文档相关的权限信息,请慎重.</span><br>继续删除吗?',
						function(btn, text) {
							if (btn == 'yes') {
								if (runMode == '0') {
									Ext.Msg
											.alert('提示',
													'系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
									return;
								}
								showWaitMsg();
								Ext.Ajax.request({
									url : './k.xzb?reqCode=deleteDocItems',
									success : function(response) {
										var resultArray = Ext.util.JSON
												.decode(response.responseText);

										store.reload();
										Ext.Msg.alert('提示', resultArray.msg);
									},
									failure : function(response) {
										var resultArray = Ext.util.JSON
												.decode(response.responseText);
										Ext.Msg.alert('提示', resultArray.msg);
									},
									params : {
										strChecked : strChecked
									}
								});
							}
						});
	}
	
	/**
	 * 授权
	 */
	function limitisDocItems(){
		
		var rows = grid.getSelectionModel().getSelections();
		var fields = '';
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].get('Doctype') == '3') {
				fields = fields + rows[i].get('Docname') + '<br>';
			}
		}
		if (fields != '') {
			Ext.Msg.alert('提示', '<b>您选中的文档中包含如下系统内置的只读文档</b><br>' + fields
							+ '<font color=red>系统内置文档不能删除!</font>');
			return;
		}
		if (Ext.isEmpty(rows)) {
			Ext.Msg.alert('提示', '请先选中要删除的文档!');
			return;
		}
		var strChecked = jsArray2JsString(rows, 'docid');
		radiowindow.show();
		Ext.getCmp('radioDocId').setValue(strChecked);
		Ext.getCmp('radiotype').setValue('doc');
		radiowindow.show();
		
	}
	
	
	/**
	 * 修改文档初始化
	 */
	function editDocInit() {
		var record = grid.getSelectionModel().getSelected();		
		addDocFormPanel.getForm().loadRecord(record);
		selectPanel.show();
		radioPanel.show();

		addDocWindow.show();
		addDocWindow.setTitle('<span class="commoncss">修改文档</span>');
		var selectModel = dirTree.getSelectionModel();
		var selectNode = selectModel.getSelectedNode();
		Ext.getCmp('docDirId').setValue(selectNode.attributes.id);
		Ext.getCmp('docDirId').hide();
		Ext.getCmp('docTitle').setValue(record.get('doctitle'));
		Ext.getCmp('docContentT').setValue(record.get('doccontent'));
		Ext.getCmp('docId').setValue(record.get('docid'));
		Ext.getCmp('attaId').setValue(record.get('attaid'));
		Ext.getCmp('selectDeps').setValue(record.get('deptname'));
		Ext.getCmp('selectPersons').setValue(record.get('username'));

		$("#docDirId").val(selectNode.attributes.id);
		$("#docAttaId").val(record.get('attaid'));
		var options = { 
			    success:    function(data) { 
			    	var dirName=eval("("+data+")").filepath;
			    	var docName=eval("("+data+")").docName;
			    	//console.info("docName="+docName);
			    	$("#attaName").html('');
			    	if(record.get('attaid')!=''){
			    		//console.info("attaid="+record.get('attaid')+';'+(record.get('attaid')!='')+"有附件");
			    		$("#attaName").html('<a href=download?path='+encodeURI(encodeURI(dirName))+encodeURI(encodeURI(docName))+'>'+docName+'</a>');
			    	}
			    	//console.info('$("#attaName").html()='+$("#attaName").html());
			    } 
			}; 
		$('#dirForm').ajaxForm(options);
		if(record.get('attaid')!=''){//有附件ID才取
			$("#dirForm").submit();
		}else{
			//console.info("无附件");
    		$("#attaName").html('');
		}
		Ext.getCmp('windowmode').setValue('edit');
		/*
		Ext.getCmp('deptid_old').setValue(record.get('deptid'));
		Ext.getCmp('password').setValue('@@@@@@');
		Ext.getCmp('password1').setValue('@@@@@@');
		Ext.getCmp('Docid').setValue(record.get('Docid'));
		*/
		Ext.getCmp('btnReset').hide();
	}
	
	/**
	 * 修改
	 */
	function updateDocItem() {
		if (!addUserFormPanel.form.isValid()) {
			return;
		}
		addDocFormPanel.add({
			xtype: 'hidden',
			id: 'docContent',
			name: 'docContent',
			value: Ext.getCmp("docContentT").getValue()
			});
		addDocFormPanel.add({
			xtype:'hidden',
			id:'radioShare',
			name:'radioShare',
			value:Ext.getCmp("radioForm").getValue()
		});
		//console.info("修改时附件ID="+Ext.getCmp("attaId").getValue());
		addDocFormPanel.doLayout();
		addDocFormPanel.form.submit({
			url : './k.xzb?reqCode=updateDocItem',
			waitTitle : '提示',
			method : 'POST',
			waitMsg : '正在处理数据,请稍候...',
			success : function(form, action) {
				addDocWindow.hide();
				store.reload();
				form.reset();
				Ext.MessageBox.alert('提示', action.result.msg);
			},
			failure : function(form, action) {
//				var msg = action.result.msg;
				Ext.MessageBox.alert('提示', '文档数据修改失败:<br>' );
			}
		});
	
	}
	
	/**
	 * 更新
	 */
	/*
	function update() {
		addUserFormPanel.form.submit({
					url : './user.xzb?reqCode=updateUserItem',
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					success : function(form, action) {
						addUserWindow.hide();
						store.reload();
						form.reset();
						Ext.MessageBox.alert('提示', action.result.msg);
					},
					failure : function(form, action) {
						var msg = action.result.msg;
						Ext.MessageBox.alert('提示', '人员数据修改失败:<br>' + msg);
					}
				});
	}
	*/
	
	/**
	 * 查询文档
	 */
	function queryDocs()
	{
		var filetitle=Ext.getCmp("filetitle").getValue();
		store.load({
			params : {
				start : 0,
				limit : bbar.pageSize,
				docTitle : filetitle
			}
		});
	}
	
	/**
	 * 右侧文档结束
	 */
	
	/**
	 * 左侧目录树开始
	 */
	/**
	 * 全局变量
	 */
	var addRoot = new Ext.tree.AsyncTreeNode({
		text : root_deptname,
		expanded : true,
		id : root_deptid
	});
	
	var modifyRoot = new Ext.tree.AsyncTreeNode({
		text : root_deptname,
		expanded : true,
		id : root_deptid
	});
	var addDeptTree = new Ext.tree.TreePanel( {
		loader : new Ext.tree.TreeLoader( {
			baseAttrs : {},
			dataUrl : './k.xzb?reqCode=dirTreeInit&person='+person+'&loginuserid='+login_userid+''
		}),
		root : addRoot,
		autoScroll : true,
		animate : false,
		useArrows : false,
		border : false
	});
	
	
	var addModifyTree = new Ext.tree.TreePanel( {
		loader : new Ext.tree.TreeLoader( {
			baseAttrs : {},
			dataUrl : './k.xzb?reqCode=dirTreeInit&person='+person+'&loginuserid='+login_userid+''
		}),
		root : modifyRoot,
		autoScroll : true,
		animate : false,
		useArrows : false,
		border : false
	});
	// 监听下拉树的节点单击事件
	
	

	var comboxWithTree = new Ext.form.ComboBox(
			{
				id : 'parentdirname',
				store : new Ext.data.SimpleStore( {
					fields : [],
					data : [ [] ]
				}),
				editable : false,
				value : ' ',
				emptyText : '请选择...',
				fieldLabel : '上级部门',
				anchor : '100%',
				mode : 'local',
				triggerAction : 'all',
				labelStyle: micolor,
				maxHeight : 390,
				// 下拉框的显示模板,addDeptTreeDiv作为显示下拉树的容器
				tpl : "<tpl for='.'><div style='height:390px'><div id='addDeptTreeDiv'></div></div></tpl>",
				allowBlank : false,
				onSelect : Ext.emptyFn
			});
	// 监听下拉框的下拉展开事件
	comboxWithTree.on('expand', function() {
		// 将UI树挂到treeDiv容器
			addDeptTree.render('addDeptTreeDiv');
			// addDeptTree.root.expand(); //只是第一次下拉会加载数据
			addDeptTree.root.reload(); // 每次下拉都会加载数据

		});
	
	/**
	 * 修改目录时，上级目录
	 */
	
	var comboxModifyTree = new Ext.form.ComboBox(
			{
				id : 'modifyparentdirname',
				store : new Ext.data.SimpleStore( {
					fields : [],
					data : [ [] ]
				}),
				editable : false,
				value : ' ',
				emptyText : '请选择...',
				fieldLabel : '上级部门',
				anchor : '100%',
				mode : 'local',
				triggerAction : 'all',
				labelStyle: micolor,
				maxHeight : 390,
				// 下拉框的显示模板,addDeptTreeDiv作为显示下拉树的容器
				tpl : "<tpl for='.'><div style='height:390px'><div id='addDirTreeDiv'></div></div></tpl>",
				allowBlank : false,
				onSelect : Ext.emptyFn
			});
	// 监听下拉框的下拉展开事件
	comboxModifyTree.on('expand', function() {
		// 将UI树挂到treeDiv容器
		addModifyTree.render('addDirTreeDiv');
			// addDeptTree.root.expand(); //只是第一次下拉会加载数据
		addModifyTree.root.reload(); // 每次下拉都会加载数据

		});
	
	
	
	var addNode=null;
	var root = new Ext.tree.AsyncTreeNode({
				text : root_deptname,
				expanded : true,
				id : root_deptid
			});
	var dirTree = new Ext.tree.TreePanel({
				loader : new Ext.tree.TreeLoader({
							baseAttrs : {},
							dataUrl : './k.xzb?reqCode=dirTreeInit&person='+person+'&loginuserid='+login_userid+''
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
				var dirId = node.attributes.id;
				var dirText=node.attributes.text;
				if(person=='1' && dirId=='001003001')
					{
						dirId=login_userid;
					}
				
				if(dirText=='根目录')
					{
					dirId="";
					store.load({
						params : {
							start : 0,
							limit : bbar.pageSize
						}
					});
						return;
					}
					
				store.load({
							params : {
								start : 0,
								limit : bbar.pageSize,
								dirId : dirId
								
							}
						});
			});
	var contextMenu = new Ext.menu.Menu({
				id : 'deptTreeContextMenu',
				items : [{
							text : '新增目录',
							iconCls : 'page_addIcon',
							id:'dir_add',
							handler : function() {
								addDir();
								var selectModel = dirTree.getSelectionModel();
								var selectNode = selectModel.getSelectedNode();
								selectNode.reload();
								
							}
						}, {
							text : '修改目录',
							iconCls : 'page_edit_1Icon',
							id:'dir_modify',
							handler : function() {
								var selectModel = dirTree.getSelectionModel();
								var selectNode = selectModel.getSelectedNode();
								var selectNodeId=selectNode.attributes.id;
								if(selectNodeId=='001003001')
								{
									Ext.MessageBox.alert('提示','这是根目录，不允许修改');
									return;
								
								}else{
									
									modifyDir();
								}
								
							}
						},
						{
							text : '删除目录',
							iconCls : 'page_delIcon',
							id:'dir_delete',
							handler : function() {
								var selectModel = dirTree.getSelectionModel();
								var selectNode = selectModel.getSelectedNode();
								var selectNodeName=selectNode.attributes.text;							
								if(selectNodeName=='根目录')
									{
										Ext.MessageBox.alert('提示','这是根目录，不允许删除');
										return;
									
									}else{
										var parentid=selectNode.parentNode.attributes.id;
										delDir(parentid,selectNode.attributes.id,selectNodeName);
									}
								
								
								delDir(parentid,selectNode.attributes.id,selectNodeName);
							}
						},{
							text : '目录共享',
							iconCls : 'page_addIcon',
							id:'dir_share',
							handler : function() {
								var selectModel = dirTree.getSelectionModel();
								var selectNode = selectModel.getSelectedNode();
								Ext.getCmp('radioDirId_').setValue(selectNode.attributes.id);
								
								//Ext.getCmp('radiotype').setValue('dir');
								radiowindow.show();
								}
						},
						{
							text : '刷新节点',
							iconCls : 'page_refreshIcon',
							id:'dir_refresh',
							handler : function() {
								var selectModel = dirTree.getSelectionModel();
								var selectNode = selectModel.getSelectedNode();
								if (selectNode.attributes.leaf) {
									selectNode.parentNode.reload();
								} else {
									selectNode.reload();
								}
							}
						}]
			});
	dirTree.on('contextmenu', function(node, e) {
				e.preventDefault();
				dirId = node.attributes.id;
				if(person=='1' && dirId=='001003001')
				{
					dirId=login_userid;
				}
				store.load({
							params : {
								start : 0,
								limit : bbar.pageSize,
								dirId : dirId
							},
							callback : function(r, options, success) {
								for (var i = 0; i < r.length; i++) {
									var record = r[i];
									var deptid_g = record.data.deptid;
									if (deptid_g == deptid) {
										grid.getSelectionModel().selectRow(i);
									}
								}
							}
						});
				node.select();
				contextMenu.showAt(e.getXY());
			});

	/**
	 * 新增目录的formpanel
	 * 
	 * 
	 */
	
	var addDirFormPanel=new Ext.form.FormPanel({
		id : 'addUserFormPanel',
		name : 'addUserFormPanel',
		defaultType : 'textfield',
		labelAlign : 'right',
		labelWidth : 65,
		frame : false,
		bodyStyle : 'padding:5 5 0',
		items : [{
					fieldLabel : '目录名称',
					name : 'dirname',
					id : 'dirname',
					allowBlank : false,
					labelStyle : micolor,
					anchor : '99%'
				},comboxWithTree,{
					fieldLabel : '备注',
					name : 'remark',
					allowBlank : true,
					anchor : '99%'
				},{
					fieldLabel : '排序号',
					name : 'sortNo',
					allowBlank : true,
					anchor : '99%'
				},{
					id : 'parentDirId',
					name : 'parentDirId',
					hidden : true
				},{
					id : 'person',
					name : 'person',
					hidden : true,
					value:person
				},{
					id : 'loginuserid',
					name : 'loginuserid',
					hidden : true,
					value:login_userid
				}]
	});
	
	/**
	 * 修改目录菜单
	 */
	var modifyMenuFormPanel=new Ext.form.FormPanel({
		id : 'modifyMenuFormPanel',
		name : 'modifyMenuFormPanel',
		defaultType : 'textfield',
		labelAlign : 'right',
		labelWidth : 65,
		frame : false,
		bodyStyle : 'padding:5 5 0',
		items : [{
					fieldLabel : '菜单名称',
					name : 'modifyMenuName',
					id : 'modifyMenuName',
					allowBlank : false,
					labelStyle : micolor,
					anchor : '99%'
				},comboxModifyTree,{
					fieldLabel : '备注',
					name : 'modify_remark',
					id : 'modify_remark',
					allowBlank :true,
					labelStyle : micolor,
					anchor : '99%'
				},{
					fieldLabel : '排序',
					name : 'modify_sortNo',
					id : 'modify_sortNo',
					allowBlank :true,
					labelStyle : micolor,
					anchor : '99%',
					regex:/[1-9]?$/,
					regexText:"排序号选择1-9"
				},{
					id : 'modifyMenuid',
					name : 'modifyMenuid',
					hidden : true
				},{
					id : 'parentMenuid',
					name : 'parentMenuid',
					hidden : true
				},{
					id : 'parentMenuid_',
					name : 'parentMenuid_',
					hidden : true
				},{
					id : 'person_',
					name : 'person_',
					hidden : true,
					value:person
				}]
	});
	
	addDeptTree.on('click', function(node) {
		comboxWithTree.setValue(node.text);
		Ext.getCmp('parentDirId').setValue(
				node.attributes.id);
		comboxWithTree.collapse();
	});
	
	addModifyTree.on('click', function(node) {
		comboxModifyTree.setValue(node.text);
		Ext.getCmp('parentMenuid_').setValue(
				node.attributes.id);
		comboxModifyTree.collapse();
	});
	
	/**
	 * 新增目录窗口
	 * 
	 */
	var addDirWindow=new Ext.Window({
		layout : 'fit',
		width : 400,
		height : 200,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		modal : true,
		title : '<span class="commoncss">新增目录</span>',
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
		items : [addDirFormPanel],
		buttons : [{
			text : '保存',
			iconCls : 'acceptIcon',
			handler : function() {
				
				saveDirItem();//保存函数
			}
		},{
			text : '关闭',
			iconCls : 'deleteIcon',
			handler : function() {
				addDirWindow.hide();
			}
		}]
	});
	
	/**
	 * 
	 */
	
	
	
	
	
	
	/**
	 * 修改目录窗口
	 */
	var modifyDirWindow= new Ext.Window({
		layout : 'fit',
		width : 420,
		height : 285,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span class="commoncss">修改目录</span>',
		// iconCls : 'page_addIcon',
		modal : true,
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
		items : [modifyMenuFormPanel],
		buttons : [{
			text : '修改',
			iconCls : 'acceptIcon',
			handler : function() {
				modifyDirItems();   
				
			}
		},{
			text : '关闭',
			iconCls : 'deleteIcon',
			handler : function() {
				modifyDirWindow.hide();
			}
		}]
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
	 * 上传ftp协议
	 */
	function submitTheForm() {
		if (!Ext.getCmp("firstForm").form.isValid()) {
			return;
		}
		if (runMode == '0') {
			Ext.Msg.alert('提示', '系统正处于演示模式下运行,您的操作被取消!该模式下只能进行查询操作!');
			return;
		}
		
		  var requesturl = webContext + '/demo/otherDemo.xzb?reqCode=doUploadByFTP&loginuserid='+login_userid+'';
		
		Ext.getCmp("firstForm").form.submit({
			url : requesturl,
			waitTitle : '提示',
			method : 'POST',
			waitMsg : '正在上传文件,请稍候...',
			timeout: 60000, // 60s
			success : function(form, action) {
				
				Ext.getCmp("firstForm").form.reset();
				Ext.getCmp('ftpWindow').hide();
				Ext.getCmp('attaId').setValue(action.result.fileid);
				$("#attaName").html('');
				$("#attaName").html('<a href=download?path='+encodeURI(encodeURI(action.result.dirname))+encodeURI(encodeURI(action.result.filename))+'&upLoadType='+action.result.upLoadType+'>'
						+action.result.filename+'</a>'+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
				Ext.MessageBox.alert('提示', action.result.msg);

			},
			failure : function(response) {
				Ext.MessageBox.alert('提示', '文件上传失败');
			}
		});
	}
	
	
	/**
	 * 新增目录
	 */
	function addDir(){
		var selectModel = dirTree.getSelectionModel();
		var selectNode = selectModel.getSelectedNode();
		//Ext.getCmp('modifyMenuName').setValue(selectNode.attributes.text);
		Ext.getCmp('parentDirId').setValue("");
		Ext.getCmp('parentDirId').setValue(selectNode.attributes.id);
		Ext.getCmp('parentdirname').setValue(selectNode.attributes.text);
		addDirWindow.show();
		
	}
	
	/**
	 * 保存目录
	 */
	function saveDirItem()
	{
		
		addDirFormPanel.form.submit({
			url : './k.xzb?reqCode=saveDirItem',
			waitTitle : '提示',
			method : 'POST',
			waitMsg : '正在处理,请稍候...',
			success : function(form, action) {
				
				addDirWindow.hide();
				var nodeid=Ext.getCmp('parentDirId').getValue();
				var node1 = dirTree.getNodeById(nodeid);
				dirTree.root.reload();
				node1.reload();
				
				store.reload();
				form.reset();
				Ext.MessageBox.alert('提示',action.result.msg);
			},
			failure : function(form, action) {
				var msg = action.result.msg;
				Ext.MessageBox.alert('提示', '人员数据保存失败:<br>' + msg);
			}
		});
		
		
	}
	/**
	 * 修改目录
	 */
	function modifyDir()
	{
		var selectModel = dirTree.getSelectionModel();
		var selectNode = selectModel.getSelectedNode();
		Ext.getCmp('modifyMenuName').setValue(selectNode.attributes.text);		
		Ext.getCmp('modifyMenuid').setValue(selectNode.attributes.id);
		Ext.getCmp('modifyparentdirname').setValue(selectNode.parentNode.attributes.text);		
		Ext.getCmp('parentMenuid').setValue(selectNode.parentNode.attributes.id);
		Ext.getCmp('modify_remark').setValue(selectNode.attributes.remark);
		Ext.getCmp('modify_sortNo').setValue(selectNode.attributes.sortno);
		modifyDirWindow.show();
		
		
	}
	
	function modifyDirItems()
	{
			Ext.Msg.confirm('确认', '修改之前请将该目录下的文件先关闭! 继续保存吗?',
					function(btn, text) {
						if (btn == 'yes') {
							updateDirName();
						} else {
							return;
						}
					});
			return;
		
		
		
		
	}
	/**
	 * 修改目录名称
	 */
	function updateDirName() {
		modifyMenuFormPanel.form.submit({
					url : './k.xzb?reqCode=updateDirItem',
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					success : function(form, action) {
						modifyDirWindow.hide();
						dirTree.root.reload();
						store.reload();
						form.reset();
						Ext.MessageBox.alert('提示', action.result.msg);
						
					},
					failure : function(form, action) {
						var msg = action.result.msg;
						Ext.MessageBox.alert('提示', '人员数据修改失败:<br>' + msg);
					}
				});
		
	}
	/**
	 * 删除目录
	 */
	function delDir(parentid,nodeId,selectNodeName)
	{
		
		/**
		 * 几个内置的目录不能删除
		 
		if(selectNodeName=="公告"||selectNodeName=="共享文档"||selectNodeName=="资源"||selectNodeName=="人事")
			{
				Ext.MessageBox.alert('提示','内置目录，不能删除');
				return;
			
			}
			
		*/
		
		
		
		/**
		 * 先只对右边的树操作
		 */
		
		
		
		Ext.Msg
		.confirm(
				'请确认',
				'<span style="color:red"><b>提示:</b></span><br>继续删除吗?',
				function(btn, text) {
					if (btn == 'yes') {
						Ext.Ajax
								.request( {
									url : './k.xzb?reqCode=deleteDirItems',
									success : function(response) {
										var resultArray = Ext.util.JSON
												.decode(response.responseText);
										var node=dirTree.getNodeById(parentid);
										node.reload();
										store.reload();
										
										Ext.Msg.alert('提示',
												resultArray.msg);
									},
									failure : function(response) {
										var resultArray = Ext.util.JSON
												.decode(response.responseText);
										Ext.Msg.alert('提示',
												resultArray.msg);
									},
									params : {
										parentid:parentid,
										dirid : nodeId
									}
								});
					}
				});
		
		
		
		
		
	}
	/**
	 * 左侧目录树结束
	 */	
});