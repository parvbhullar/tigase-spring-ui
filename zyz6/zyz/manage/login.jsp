<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>网站管理员登陆</title>
		<link href="images/skin.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			<!--
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
				background-color: #1D3647;
			}
			-->
		</style>
		<c:if test="${param.error==2}">
			<script type="text/javascript">
				alert("验证码错误！");
			</script>
		</c:if>
		<c:if test="${param.error==1}">
			<script type="text/javascript">
				alert("非法用户！");
			</script>
		</c:if>
	</head>
	<body>
		<table width="100%" height="166" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td height="42" valign="top">
    				<table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
      					<tr>
					        <td width="1%" height="21">&nbsp;</td>
					        <td height="42">&nbsp;</td>
					        <td width="17%">&nbsp;</td>
					    </tr>
    				</table>
    			</td>
  			</tr>
  			<tr>
    			<td valign="top">
    				<table width="100%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg">
      					<tr>
        					<td width="49%" align="right">
        						<table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
            						<tr>
              							<td height="138" valign="top">
              								<table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
								                <tr>
								                  	<td height="149">&nbsp;</td>
								                </tr>
								                <tr>
								                  	<td height="80" align="right" valign="top"><img src="images/logo.png" width="279" height="68"></td>
								                </tr>
                								<tr>
                  									<td height="198" align="right" valign="top">
                  										<table width="100%" border="0" cellpadding="0" cellspacing="0">
										                    <tr>
										                      	<td width="35%">&nbsp;</td>
										                      	<td height="25" colspan="2" class="left_txt"><p></p></td>
										                    </tr>
										                    <tr>
										                      	<td>&nbsp;</td>
										                      	<td height="25" colspan="2" class="left_txt"><p></p></td>
										                    </tr>
										                    <tr>
										                      	<td>&nbsp;</td>
										                      	<td height="25" colspan="2" class="left_txt"><p></p></td>
										                    </tr>
										                    <tr>
										                      	<td width="35%">&nbsp;</td>
										                      	<td height="25" colspan="2" class="left_txt"><p></p></td>
										                    </tr>
										                    <tr>
										                      	<td>&nbsp;</td>
										                      	<td height="25" colspan="2" class="left_txt"><p></p></td>
										                    </tr>
										                    <tr>
										                      	<td>&nbsp;</td>
										                      	<td height="25" colspan="2" class="left_txt"><p></p></td>
										                    </tr>
										                    <!--
										                    <tr>
										                      	<td>&nbsp;</td>
										                      	<td width="30%" height="40"><img src="images/icon-demo.gif" width="16" height="16"><a href="javascript:void(0)"  class="left_txt3"> 使用说明</a> </td>
										                      	<td width="35%"><img src="images/icon-login-seaver.gif" width="16" height="16"><a href="javascript:void(0)" class="left_txt3"> 技术支持</a></td>
										                    </tr>
										                    -->
										                    <tr>
										                      	<td>&nbsp;</td>
										                      	<td height="25" colspan="2" class="left_txt"><p></p></td>
										                    </tr>
										                    <tr>
										                      	<td>&nbsp;</td>
										                      	<td height="10" colspan="2" class="left_txt"><p></p></td>
										                    </tr>
										                    <tr>
										                      	<td>&nbsp;</td>
										                      	<td height="25" colspan="2" class="left_txt"><img src="images/bq.png" width="279" height="68"></td>
										                    </tr>
										                </table>
										            </td>
                								</tr>
              								</table>
              							</td>
            						</tr>
        						</table>
        					</td>
        					<td width="1%" >&nbsp;</td>
        					<td width="50%" valign="bottom">
        						<table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
						            <tr>
						              	<td width="4%">&nbsp;</td>
						              	<td width="96%" height="38"><span class="login_txt_bt">后台管理</span></td>
						            </tr>
            						<tr>
              							<td>&nbsp;</td>
              							<td height="21">
              								<table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
                  								<tr>
                    								<td height="164" colspan="2" align="center">
                    									<form name='f' action='${ctx}/manage/j_spring_security_check' method='POST'>
                        									<table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
								                          		<tr>
								                          			<td width="33%" height="38" class="top_hui_text"><span class="login_txt">登录名称：&nbsp;&nbsp; </span></td>
								                          			<td height="38" colspan="2" class="top_hui_text" align="left">
								                          				<input name="j_username" class="editbox4" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" size="20">
								                          			</td>
								                          		</tr>
								                          		<tr>
								                            		<td width="33%" height="35" class="top_hui_text"><span class="login_txt">登录密码： &nbsp;&nbsp; </span></td>
								                            		<td height="35" colspan="2" class="top_hui_text" align="left">
								                            			<input class="editbox4" type="password" size="20" name="j_password">
								                              			<img src="images/luck.gif" width="19" height="18">
								                              		</td>
								                          		</tr>
								                          		<tr>
								                            		<td width="33%" height="35" ><span class="login_txt">验&nbsp;&nbsp;证&nbsp;&nbsp;码：</span></td>
								                            		<td height="35" colspan="2" class="top_hui_text" align="left">
								                            			<input type="text" name="j_captcha" style="width:34px;font-size:10px" onkeydown="javascript:if(event.keyCode==13){doLogin();}">
									                            		<img id="captchaImage" src="${ctx}/manage/security/jcaptcha.jpg" width="60px" height="18px" align="middle" onclick="onImageClick(this);">
								                              		</td>
								                          		</tr>
								                          		<tr>
								                            		<td height="35" align="right" valign="top">
								                            			<input name="button_login" type="button" class="button" id="button_login" value="登 陆" onclick="this.blur();doLogin();">
								                            		</td>
								                            		<td width="33%" height="35" align="left">
								                            			&nbsp;
								                            		</td>
								                            		<td width="33%" class="top_hui_text" align="left" valign="top">
								                            			<input name="reset" type="reset" class="button" id="reset" value="取 消" ">
								                            		</td>
								                          		</tr>
								                        	</table><br>
                    									</form>
                    								</td>
                  								</tr>
							                  	<tr>
							                    	<td width="433" height="164" align="right" valign="bottom">
							                    		<img src="images/login-wel.gif" width="242" height="138">
							                    	</td>
							                    	<td width="57" align="right" valign="bottom">&nbsp;</td>
							                  	</tr>
              								</table>
              							</td>
            						</tr>
          						</table>
          					</td>
      					</tr>
    				</table>
    			</td>
  			</tr>
  			<tr>
    			<td height="20">
    				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
      					<tr>
        					<td align="center"><span class="login-buttom-txt">Copyright &copy; 2009-2010</span></td>
      					</tr>
    				</table>
    			</td>
  			</tr>
		</table>
		<script type="text/javascript">
		function doLogin(){
			var flag=valid();
			if(flag){
				document.f.submit();
			}
		}
		
		function valid(){
			if(!(document.f.j_username.value)||document.f.j_username.value.length<1){
				alert("请输入合法的登陆名称！");
				return false;
			}
			if(!(document.f.j_password.value)||document.f.j_password.value.length<1){
				alert("请输入合法的登陆密码！");
				return false;
			}
			if(!(document.f.j_captcha.value)||document.f.j_captcha.value.length!=4){
				alert("请输入验证码！");
				return false;
			}
			return true;
		}
		function onImageClick(o){
			o.src = "/ecommerce/manage/security/jcaptcha.jpg?update=" + Math.random();   
		}
		</script>
	</body>
</html>