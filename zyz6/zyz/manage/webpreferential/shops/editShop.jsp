<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../../../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<title>修改商户</title>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/javascript/jquery-1.4.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/fckeditor/fckeditor.js"></script>
		<script type="text/javascript">
			function FCKeditor_OnComplete(editorInstance) {
				window.status = editorInstance.Description;
			}
		</script>
		<script type="text/javascript">
			function relogo(but){
				if(but.name=="rupload"){
					document.getElementById("rupload").value="取消上传LOGO";
					document.getElementById("rupload").name="cupload";
					document.getElementById("logo").style.display="";
				}else if(but.name=="cupload"){
					document.getElementById("rupload").value="重新上传LOGO";
					document.getElementById("logo").outerHTML="<input type='file' name='logo' id='logo' value='' />";
					document.getElementById("rupload").name="rupload";
					document.getElementById("logo").style.display="none";
				}
			}
			function reshop(but){
				if(but.name=="ruploadshop"){
					document.getElementById("ruploadshop").value="取消上传门店图片";
					document.getElementById("ruploadshop").name="cuploadshop";
					document.getElementById("shopImage").style.display="";
				}else if(but.name=="cuploadshop"){
					document.getElementById("ruploadshop").value="重新上传LOGO";
					document.getElementById("shopImage").outerHTML="<input type='file' name='shopImage' id='shopImage' value='' />";
					document.getElementById("ruploadshop").name="ruploadshop";
					document.getElementById("shopImage").style.display="none";
				}
			}
		</script>
		<script type="text/javascript">
			function check(){
				if($('#name').val()==''){
					alert('请填写商户名字!');
					return false;
				}
				var pname = $('#logo').val();
				if(pname!=''){
					var endPhoto = pname.substring(pname.lastIndexOf(".")+1,pname.length).toLowerCase();
				    if(endPhoto!='jpg' && endPhoto!='gif'&& endPhoto!='jpeg'&& endPhoto!='bmp'&& endPhoto!='png'){
				    	alert('上传图片必须是jpg、gif、jpeg、bmp、png格式');
				    	return false;
					}
				}
				var _pname = $('#shopImage').val();
				if(_pname!=''){
					var _endPhoto = _pname.substring(_pname.lastIndexOf(".")+1,_pname.length).toLowerCase();
				    if(_endPhoto!='jpg' && _endPhoto!='gif'&& _endPhoto!='jpeg'&& _endPhoto!='bmp'&& _endPhoto!='png'){
				    	alert('上传图片必须是jpg、gif、jpeg、bmp、png格式');
				    	return false;
					}
				}
				if($('#address').val()==''){
					alert('请填写商户地址!');
					return false;
				}
				if($('#tel').val()==''){
					alert('请填写商户电话!');
					return false;
				}
				if(!$('#tel').val().match(new RegExp(/^[0-9]{3,4}\-[0-9]{7,8}$/))){
				     alert('电话格式不正确');
			         return false;
			       }
			    var introduction = FCKeditorAPI.GetInstance("introduction").GetXHTML(true);			
				if(introduction==''){
					alert('请填写商户介绍!');
					return false;
				}
			}
		</script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../../../images/mail_leftbg.gif">
    				<img src="../../../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../../../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">修改商户</div></td>  
			      		</tr>
    				</table>
    			</td>
    			<td width="16" valign="top" background="../../../images/mail_rightbg.gif">
    				<img src="../../../images/nav-right-bg.gif" width="16" height="29" />
    			</td>
  			</tr>
  			
  			
  			<tr>
    			<td height="71" valign="middle" background="../../../images/mail_leftbg.gif">&nbsp;</td>
   				<td valign="top" bgcolor="#F7F8F9">
   					<table width="100%" height="138" border="0" cellpadding="0" cellspacing="0">
      					<!-- <tr>
        					<td height="4" valign="top"></td>
      					</tr> -->
      					<tr>
        					<td valign="top">
        						<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
					          		<tr>
					            		<td height="8">
					            			<table width="100%">
					            				<tr>
					            					<td class="left_txt">当前位置：商户管理&nbsp;>>&nbsp;修改商户</td>
					            					<td align="right" class="left_txt">页面处理时间:<c:out value="${(postTime-preTime)}"/>毫秒&nbsp;&nbsp;</td>
					            				</tr>
					            			</table>
					            		</td>
					          		</tr>
          							<tr>
            							<td height="10">
            								<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              									<tr><td></td></tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            								<table width="100%" height="55" border="0" cellpadding="0" cellspacing="0">
              									<tr>
									                <td width="10%" height="55" valign="middle">
									                	<img src="../../../images/title.gif" width="54" height="55">
									                </td>
									                <td width="90%" valign="top">
										                <span class="left_txt2">在这里，您可以修改商户对象</span><br/>
										                <span class="left_txt3">注意!</span>
										                <span class="left_txt3">该操作不会改变商户的状态.</span>
                									</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr><td>&nbsp;</td></tr>
          							<tr>
            							<td>
            								<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="nowtable">
              									<tr>
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;商户基本参数设置</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            								<form action="<c:url value="../update.action"/>" method="post" enctype="multipart/form-data">
            									<input type="hidden" name="id" value="${shop.id}">
												<input type="hidden" name="_method" value="put">
												<input type="hidden" name="oldlogo" value="${shop.logo}">
												<input type="hidden" name="oldshopimage" value="${shop.shopImage}">
            									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									              	<tr>
									                	<td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">商户名称：</td>
									                	<td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
									                	<td width="42%" height="30" bgcolor="#f2f2f2">
									                		<input type="text" id='name' name="name" value="${shop.name}"/>
									                	</td>
									                	<td width="35%" height="30" bgcolor="#f2f2f2" class="left_txt">商户名称[必填]</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">门店图片：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<img id="imgShop" src="<c:url value="${shop.shopImage}"/>" height="30px"/>
															<input type="button" name="ruploadshop" id="ruploadshop" value="重新上传门店图片" onclick="reshop(this);"/>
															<input type="file" name="shopImage" id="shopImage" value="" style="display:none"/><br/>
									                	</td>
									                	<td height="30" class="left_txt">门店图片[必填](图片大小:196*148)</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">商户LOGO：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<img id="imglogo" src="<c:url value="${shop.logo}"/>" height="30px"/>
															<input type="button" name="rupload" id="rupload" value="重新上传LOGO" onclick="relogo(this);"/>
															<input type="file" name="logo" id="logo" value="" style="display:none"/><br/>
									                	</td>
									                	<td height="30" class="left_txt">商户LOGO[必填](图片大小:62*63)</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">商户地址：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2">
									                		<input type="text" id='address' name="address" value="${shop.address}"/>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">商户地址[必填]</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">商户电话： </td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<input type="text" id='tel' name="tel" value="${shop.tel}"/>
									                	</td>
									                	<td height="30" class="left_txt">商户电话[必填](格式:区号-电话)</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">商户介绍：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2">
									                		<FCK:editor instanceName="introduction" toolbarSet="ecommerce">
																<jsp:attribute name="value">
																	${shop.introduction}
																</jsp:attribute>
																<jsp:body>
																	<FCK:config SkinPath="skins/office2003/"/>
																</jsp:body>
															</FCK:editor>
									                		<!-- <textarea rows="4" cols="40" name="introduction" id="introduction">${shop.introduction}</textarea> -->
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">商户介绍[必填]</td>
									              	</tr>

              										<tr>
                										<td height="17" colspan="4" align="right" >&nbsp;</td>
              										</tr>

            									</table>
            									<div align="right">
            										<input type="submit" name="create" onclick="return check()" value="修改"/>&nbsp;&nbsp;
													<input type="reset" name="reset" value="重置"/>&nbsp;&nbsp;
													<input type="button" name="goback" value="返回" onclick="history.go(-1)"/>&nbsp;&nbsp;&nbsp;&nbsp;
												</div>
            								</form>
            							</td>
          							</tr>
        						</table>
         					</td>
      					</tr>
    				</table>
    			</td>
    			<td background="../../../images/mail_rightbg.gif">&nbsp;</td>
  			</tr>
  			<tr>
    			<td valign="middle" background="../../../images/mail_leftbg.gif">
    				<img src="../../../images/buttom_left2.gif" width="17" height="17" />
    			</td>
      			<td height="17" valign="top" background="../../../images/buttom_bgs.gif">
      				<img src="../../../images/buttom_bgs.gif" width="17" height="17" />
      			</td>
    			<td background="../../../images/mail_rightbg.gif">
    				<img src="../../../images/buttom_right2.gif" width="16" height="17" />
    			</td>
  			</tr>
		</table>
	</body>
</html>