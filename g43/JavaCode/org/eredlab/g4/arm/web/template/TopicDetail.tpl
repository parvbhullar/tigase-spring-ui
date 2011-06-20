<style type="text/css">
.myDiv {
	font-family: "宋体";
	font-size: 13px;
}
</style>

<div id="mainDiv_${topicVo.topicid}"></div>
<script type="text/javascript">

Ext.onReady(function() {

  var tb = new Ext.Toolbar();
  tb.add({
			text : '修改',
			iconCls : 'page_edit_1Icon',
			handler : function() {
				editTopicWindow.show();
				editTopicTypeCombo.setValue('${topicVo.topictype}');
				Ext.getCmp('contentEdit').setValue('${topicVo.content2}');
				titleTextField.setValue('${topicVo.title}');
			}
		}, {
			text : '删除',
			iconCls : 'deleteIcon',
			id:'id_btn_deltopic',
			handler : function() {
				deleteTopic();
			}
		});
		
			var editTopicTypeStore = new Ext.data.SimpleStore({
						fields : ['text', 'value'],
						data : [['建议|Bug提交', '2'], ['咨询|问题求助', '3'], ['G4知识库', '4'], ['G4灌水乐园', '5']]
					});

			var editTopicTypeCombo = new Ext.form.ComboBox({
						hiddenName : 'topictype',
						store : editTopicTypeStore,
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
			 var titleTextField = new Ext.form.TextField({
				fieldLabel : '标题',
				emptyText : '请输入主题帖标题(名字取好一点哦)',
				maxLength : 50,
				name : 'title',
				allowBlank : false,
				anchor : '100%'
			});

			var editTopicPanel = new Ext.form.FormPanel({
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
												items : [editTopicTypeCombo, titleTextField]
											}]
								}, {
									layout : 'fit',
									border : false,
									items : [{
												id : 'contentEdit',
												name : 'contentEdit',
												xtype : 'htmleditor',
												allowBlank : false,
												enableSourceEdit : false,
												anchor : '99%'
											}]

								}]
					});

			var editTopicWindow = new Ext.Window({
						title : '<span style="font-weight:normal">修改主题帖</span>', // 窗口标题
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
						items : [editTopicPanel], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								saveEdit();
							}
						}, {	// 窗口底部按钮配置
									text : '关闭', // 按钮文本
									iconCls : 'deleteIcon', // 按钮图标
									handler : function() { // 按钮响应函数
										editTopicWindow.hide();
									}
								}]
					});
			
				var editReplyPanel = new Ext.form.FormPanel({
						bodyStyle : 'padding:5 5 5 5',
						labelAlign : 'right', // 标签对齐方式
						frame : true,
						labelWidth : 35,
						items : [{
									layout : 'fit',
									border : false,
									items : [{
												id : '_id_reply_content',
												name : 'contentEdit',
												xtype : 'htmleditor',
												allowBlank : false,
												enableSourceEdit : false,
												anchor : '99%'
											},{
												id : '_id_edit_replyid',
												name : 'replyid',
												xtype : 'textfield',
												hidden: true,
												anchor : '99%'
											}]
								}]
					});
				
			var editReplyWindow = new Ext.Window({
						title : '<span style="font-weight:normal">修改回帖</span>', // 窗口标题
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
						items : [editReplyPanel], // 嵌入的表单面板
						buttons : [{ // 窗口底部按钮配置
							text : '保存', // 按钮文本
							iconCls : 'acceptIcon', // 按钮图标
							handler : function() { // 按钮响应函数
								saveEditReply();
							}
						}, {	// 窗口底部按钮配置
									text : '关闭', // 按钮文本
									iconCls : 'deleteIcon', // 按钮图标
									handler : function() { // 按钮响应函数
										editReplyWindow.hide();
									}
								}]
					});					

		
 var panel_top_${topicVo.topicid} = new Ext.Panel({
    id:'panel_top_${topicVo.topicid}',
	border : false,
	frame : false,
	renderTo : 'mainDiv_${topicVo.topicid}',
	items : [{
	    id:'id_panel_main_${topicVo.topicid}',
		title : '<span style="font-weight:normal"><span style="text-underline-position: above; text-decoration: underline; ">${topicVo.title}</span>&nbsp;&nbsp;作者:${topicVo.username}&nbsp;&nbsp;发帖时间:${topicVo.addtime}</span>',
		xtype : 'panel',
		tbar : tb,
		collapsible : true,
		border : false,
		titleCollapse : true,
		contentEl : 'id_div_main_${topicVo.topicid}'
    }
    #foreach($reply in $replyList)
    ,
    {
        id:'id_panel_reply_${reply.replyid}',
		title : '<span style="font-weight:normal">回贴人:${reply.username}&nbsp;&nbsp;回帖时间:${reply.replytime}</span>',
		xtype : 'panel',
		collapsible : true,
		border : false,
		tbar : [{
			text : '修改',
			iconCls : 'page_edit_1Icon',
			handler : function() {
				editReplyWindow.show();
				Ext.getCmp('_id_edit_replyid').setValue('${reply.replyid}');
				Ext.getCmp('_id_reply_content').setValue('${reply.replycontent2}');
			}
		}, {
			text : '删除',
			iconCls : 'deleteIcon',
			id:'id_btn_delreply_${reply.replyid}',
			handler : function() {
				deleteReply('${reply.replyid}');
			}
		}],
		titleCollapse : true,
		contentEl : 'id_div_reply_${reply.replyid}'
    }
    #end
    
    ]
});
#if(${flag1} == '2')
  tb.hide();
#else if(${flag1} == '1')
  Ext.getCmp('id_btn_deltopic').disable();
#end 
#foreach($reply in $replyList)
#if(${flag1} == '2')
  Ext.getCmp('id_panel_reply_${reply.replyid}').getTopToolbar().hide();
#else if(${flag1} == '1')
  Ext.getCmp('id_btn_delreply_${reply.replyid}').disable();
#end 
#end
	function saveEdit() {
		if (!editTopicPanel.getForm().isValid()) {
			return;
		}
		var value = Ext.getCmp('contentEdit').getValue();
		if (value.length < 1) {
			Ext.MessageBox.alert('提示', '主题内容不能为空');
			return;
		}
		editTopicPanel.form.submit({
					url : 'projectHome.ered?reqCode=editTopic',
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					params:{topicid:'${topicVo.topicid}'},
					success : function(form, action) { // 回调函数有2个参数
						// Ext.MessageBox.alert('提示',
						// action.result.msg);
						editTopicWindow.hide();
						//tabPanel.setActiveTab(0);
						//tabPanel.remove('tab${topicVo.topicid}');
						store.reload();
						tabPanel.getItem('tab${topicVo.topicid}').getUpdater().update({
						  url:'projectHome.ered?reqCode=previewTopicInit',
						  params:{topicid: '${topicVo.topicid}'}
						});
						//alert(tabPanel.getItem('tab${topicVo.topicid}'));
					},
					failure : function(form, action) {
						Ext.MessageBox.alert('提示', '数据保存失败');
					}
				});
	}
	
	function deleteTopic(){
	  		    showWaitMsg();
				Ext.Ajax.request({
							url : 'projectHome.ered?reqCode=deleteTopic',
							success : function(response) {
							    hideWaitMsg();
							    tabPanel.setActiveTab(0);
						        tabPanel.remove('tab${topicVo.topicid}');
								store.reload();
							},
							failure : function(response) {
								Ext.Msg.alert('提示', '删除失败');
							},
							params : {
								topicid : '${topicVo.topicid}'
							}
						});
	   }
	   
	function saveEditReply() {
		var value = Ext.getCmp('_id_reply_content').getValue();
		if (value.length < 1) {
			Ext.MessageBox.alert('提示', '内容不能为空');
			return;
		}
		editReplyPanel.form.submit({
					url : 'projectHome.ered?reqCode=editReply',
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					success : function(form, action) { // 回调函数有2个参数
						// Ext.MessageBox.alert('提示',
						// action.result.msg);
						editReplyWindow.hide();
						//tabPanel.setActiveTab(0);
						//tabPanel.remove('tab${topicVo.topicid}');
						//store.reload();
						tabPanel.getItem('tab${topicVo.topicid}').getUpdater().update({
						  url:'projectHome.ered?reqCode=previewTopicInit',
						  params:{topicid: '${topicVo.topicid}'}
						});
						//alert(tabPanel.getItem('tab${topicVo.topicid}'));
					},
					failure : function(form, action) {
						Ext.MessageBox.alert('提示', '数据保存失败');
					}
				});
	}
	
	function deleteReply(replyid){
	  		    showWaitMsg();
				Ext.Ajax.request({
							url : 'projectHome.ered?reqCode=deleteReply',
							success : function(response) {
							    hideWaitMsg();
						        //tabPanel.remove('tab${topicVo.topicid}');
						        var panel = Ext.getCmp('panel_top_${topicVo.topicid}');
						        panel.remove('id_panel_reply_' + replyid);
							},
							failure : function(response) {
								Ext.Msg.alert('提示', '删除失败');
							},
							params : {
								replyid : replyid
							}
						});
	   }
			
});
</script>

<div id="id_div_main_${topicVo.topicid}" class="myDiv" style="margin: 8px;">
 ${topicVo.content}
</div>
#foreach($reply in $replyList)
<div id="id_div_reply_${reply.replyid}" class="myDiv" style="margin: 8px;">${reply.replycontent}</div>
#end
