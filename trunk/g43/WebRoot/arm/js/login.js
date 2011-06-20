/**
 * 登陆页面
 * 
 * @author XiongChun
 * @since 2010-01-13
 */
Ext
		.onReady(function() {
			var panel = new Ext.Panel(
					{
						el : 'hello-tabs',
						autoTabs : true,
						deferredRender : false,
						border : false,
						items : {
							xtype : 'tabpanel',
							activeTab : 0,
							height : 180,
							border : false,
							items : [
									{
										title : "身份认证",
										xtype : 'form',
										id : 'loginForm',
										defaults : {
											width : 260
										},
										bodyStyle : 'padding:20 0 0 50',
										defaultType : 'textfield',
										labelWidth : 40,
										labelSeparator : '：',
										items : [
												{
													fieldLabel : '帐&nbsp;号',
													name : 'account',
													id : 'account',
													cls : 'user',
													blankText : '帐号不能为空,请输入!',
													maxLength : 30,
													maxLengthText : '账号的最大长度为30个字符',
													allowBlank : false,
													listeners : {
														specialkey : function(
																field, e) {
															if (e.getKey() == Ext.EventObject.ENTER) {
																Ext
																		.getCmp(
																				'password')
																		.focus();
															}
														}
													}
												},
												{
													fieldLabel : '密&nbsp;码',
													name : 'password',
													id : 'password',
													cls : 'key',
													inputType : 'password',
													blankText : '密码不能为空,请输入!',
													maxLength : 20,
													maxLengthText : '密码的最大长度为20个字符',
													allowBlank : false,
													listeners : {
														specialkey : function(
																field, e) {
															if (e.getKey() == Ext.EventObject.ENTER) {
																login();
															}
														}
													}
												},
												{
													id : 'id_reg_panel',
													xtype : 'panel',
													border : false,
													hidden : true,
													html : '<br><div id="id_reg_div" style="font-size:12px;color:blue; margin-left:105px">[点偶,1o秒钟注册新帐户]</div>'
												} ]
									}, {
										title : '信息公告',
										contentEl : 'infoDiv',
										defaults : {
											width : 230
										}
									}, {
										title : '关于',
										contentEl : 'aboutDiv',
										defaults : {
											width : 230
										}
									} ]
						}
					});

			// 清除按钮上下文菜单
			var mainMenu = new Ext.menu.Menu({
				id : 'mainMenu',
				items : [
						{
							text : '清除记忆',
							iconCls : 'status_awayIcon',
							handler : function() {
								clearCookie('eredg4.login.account');
								var account = Ext.getCmp('loginForm').findById(
										'account');
								Ext.getCmp('loginForm').form.reset();
								account.setValue('');
								account.focus();
							}
						}, {
							text : '切换到全屏模式',
							iconCls : 'imageIcon',
							handler : function() {
								window.location.href = './fullScreen.htm';
							}
						} ]
			});

			var win = new Ext.Window(
					{
						title : LOGIN_WINDOW_TITLE,
						renderTo : Ext.getBody(),
						layout : 'fit',
						width : 460,
						height : 300,
						closeAction : 'hide',
						plain : true,
						modal : true,
						collapsible : true,
						titleCollapse : true,
						maximizable : false,
						draggable : false,
						closable : false,
						resizable : false,
						animateTarget : document.body,
						items : panel,
						buttons : [
								{
									text : '&nbsp;登录',
									iconCls : 'acceptIcon',
									handler : function() {
										if (Ext.isIE6) {
											top.Ext.MessageBox
													.alert('温馨提示',
															'G4演示系统拒绝IE6客户端登录<br>我们强烈建议您立即升级IE或者切换到<b>FireFox</b>、<b>GoogleChrome</b>浏览器');
											return;
										}
										login();
									}
								}, {
									text : '&nbsp;选项',
									iconCls : 'tbar_synchronizeIcon',
									menu : mainMenu
								} ]
					});

			win.show();

			win.on('show', function() {
				setTimeout(
						function() {
							var account = Ext.getCmp('loginForm').findById(
									'account');
							var password = Ext.getCmp('loginForm').findById(
									'password');
							var c_account = getCookie('eredg4.login.account');
							account.setValue(c_account);
							if (Ext.isEmpty(c_account)) {
								account.focus();
							} else {
								password.focus();
							}
						}, 200);
			}, this);

			Ext.get('id_reg_div').on('click', function() {
				addUserFormPanel.getForm().reset();
				addUserWindow.show();
			});

			var addUserFormPanel = new Ext.form.FormPanel(
					{
						id : 'addUserFormPanel',
						name : 'addUserFormPanel',
						defaultType : 'textfield',
						labelAlign : 'right',
						labelWidth : 65,
						bodyStyle : 'padding:5 5 5 5',
						frame : false,
						items : [
								{
									fieldLabel : '登录帐户',
									name : 'account',
									allowBlank : false,
									emptyText : '请使用Email作为G4帐户',
									regex : /^([\w]+)(.[\w]+)*@([\w-]+\.){1,5}([A-Za-z]){2,4}$/,
									regexText : '请以电子邮箱地址作为G4帐户',
									maxLength : 30,
									anchor : '99%'
								}, {
									fieldLabel : '姓名/昵称',
									name : 'username',
									allowBlank : false,
									anchor : '99%'
								}, {
									fieldLabel : '密码',
									name : 'password',
									inputType : 'password',
									allowBlank : false,
									anchor : '99%'
								}, {
									fieldLabel : '确认密码',
									name : 'password1',
									inputType : 'password',
									allowBlank : false,
									anchor : '99%'
								} ]
					});

			var addUserWindow = new Ext.Window({
				layout : 'fit',
				width : 280,
				height : 185,
				resizable : false,
				draggable : false,
				closeAction : 'hide',
				title : '<span style="font-weight:normal">注册新帐户</span>',
				iconCls : 'group_addIcon',
				modal : true,
				collapsible : false,
				maximizable : false,
				buttonAlign : 'right',
				border : false,
				animCollapse : true,
				animateTarget : Ext.getBody(),
				constrain : true,
				items : [ addUserFormPanel ],
				buttons : [ {
					text : '保存',
					iconCls : 'acceptIcon',
					handler : function() {
						regAccount();
					}
				}, {
					text : '重置',
					id : 'btnReset',
					iconCls : 'tbar_synchronizeIcon',
					handler : function() {
						clearForm(addUserFormPanel.getForm());
					}
				} ]
			});

			/**
			 * 提交登陆请求
			 */
			function login() {
				if (Ext.getCmp('loginForm').form.isValid()) {
					Ext.getCmp('loginForm').form
							.submit({
								url : 'login.ered?reqCode=login',
								waitTitle : '提示',
								method : 'POST',
								waitMsg : '正在验证您的身份,请稍候.....',
								success : function(form, action) {
									var account = Ext.getCmp('loginForm')
											.findById('account');
									setCookie("eredg4.login.account", account
											.getValue());
									window.location.href = 'index.ered?reqCode=indexInit';
								},
								failure : function(form, action) {
									var errmsg = action.result.msg;
									var errtype = action.result.errorType;
									Ext.Msg.alert('提示', errmsg, function() {
										var account = Ext.getCmp('loginForm')
												.findById('account');
										var password = Ext.getCmp('loginForm')
												.findById('password');
										if (errtype == '1') {
											win.getComponent('loginForm').form
													.reset();
											account.focus();
											account.validate();
										} else if (errtype == '2') {

											password.focus();
											password.setValue('');
										} else if (errtype == '3') {
											account.focus();
										}
									});
								}
							});
				}
			}

			/**
			 * 注册新帐户
			 */
			function regAccount() {
				if (!addUserFormPanel.form.isValid()) {
					return;
				}
				var values = addUserFormPanel.getForm().getValues();
				if (values.password1 != values.password) {
					Ext.Msg.alert('提示', '两次输入的密码不匹配,请重新输入!');
					Ext.getCmp('password').setValue('');
					Ext.getCmp('password1').setValue('');
					return;
				}
				addUserFormPanel.form.submit({
					url : 'login.ered?reqCode=regAccount',
					waitTitle : '提示',
					method : 'POST',
					waitMsg : '正在处理数据,请稍候...',
					success : function(form, action) {
						addUserWindow.hide();
						Ext.MessageBox.alert('提示', '帐户注册成功,点击[登录]按钮进入系统!');
						var password = Ext.getCmp('loginForm').findById(
								'password');
						var account = Ext.getCmp('loginForm').findById(
								'account');
						password.setValue(values.password);
						account.setValue(values.account);
					},
					failure : function(form, action) {
						Ext.MessageBox.alert('提示', action.result.msg);
					}
				});
			}

			// 演示模式
			if (runMode == '0') {
				// Ext.getCmp('account').setValue('developer');
				// Ext.getCmp('password').setValue('111111');
				Ext.getCmp('id_reg_panel').show();
			}

			setTimeout(
					function() {
						if (Ext.isIE) {
							top.Ext.MessageBox
									.alert(
											'温馨提示',
											'系统监测到您正在使用基于MSIE内核的浏览器<br>我们强烈建议你立即切换到<b>FireFox</b>或者<b>GoogleChrome</b>浏览器体验飞一般的感觉!')
						}
					}, 500);
		});
