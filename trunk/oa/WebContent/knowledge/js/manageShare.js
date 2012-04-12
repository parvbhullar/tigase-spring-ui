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
				dataIndex : 'share',
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
							url : './k.xzb?reqCode=queryDocsForManage_'
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
						},{
							name : 'share'
						}])
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
        autoheight:true,
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
			name : 'shareId',
			id : 'shareId',
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
		constrain : true,
		items : [ addDocFormPanel,panel,addDocFormPanelDown ],
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
						//var mode = Ext.getCmp('windowmode').getValue();
						var shareId=Ext.getCmp('shareId').getValue();
						if(shareId=='1')
							{
								alert('你只有查看的权限，不能修改');
								return;
							}else if(shareId=='2')
								{
									updateDocItem();
								}
						
							
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
			alert(122323232131);
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
			url : './k.xzb?reqCode=saveDocItem',
			waitTitle : '提示',
			method : 'POST',
			waitMsg : '正在处理数据,请稍候...',
			success : function(form, action) {
				addDocWindow.hide();
				store.reload();
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
		limitisWindow.show();
		Ext.getCmp('limitisDocId').setValue(strChecked);
		
	}
	
	
	/**
	 * 修改文档初始化
	 */
	function editDocInit() {
		var record = grid.getSelectionModel().getSelected();		
		addDocFormPanel.getForm().loadRecord(record);
		addDocWindow.show();
		addDocWindow.setTitle('<span class="commoncss">修改文档</span>');
		var selectModel = dirTree.getSelectionModel();
		var selectNode = selectModel.getSelectedNode();
		Ext.getCmp('docDirId').setValue(selectNode.attributes.id);
		Ext.getCmp('docDirId').hide();
		Ext.getCmp('docTitle').setValue(record.get('doctitle'));
		Ext.getCmp('shareId').setValue(record.get('share'));
		Ext.getCmp('docContentT').setValue(record.get('doccontent'));
		Ext.getCmp('docId').setValue(record.get('docid'));
		Ext.getCmp('attaId').setValue(record.get('attaid'));

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

	var root = new Ext.tree.AsyncTreeNode({
				text : root_deptname,
				expanded : true,
				id : root_deptid
			});
	var dirTree = new Ext.tree.TreePanel({
				loader : new Ext.tree.TreeLoader({
							baseAttrs : {},
							dataUrl : './k.xzb?reqCode=dirTreeInitShare&loginuserid='+login_userid+''
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
				var share=node.attributes.share;
				if(dirId=='001003001')
					{
						return;
					}
				
				store.load({
							params : {
								start : 0,
								limit : bbar.pageSize,
								dirId : dirId,
								share:share
								
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