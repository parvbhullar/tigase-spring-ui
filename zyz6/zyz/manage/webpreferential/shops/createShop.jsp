<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/javascript/jquery-1.4.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/fckeditor/fckeditor.js"></script>
		<script type="text/javascript">
			function FCKeditor_OnComplete(editorInstance) {
				window.status = editorInstance.Description;
			}
		</script>
		<script type="text/javascript">
			function check(){
				if($('#name').val()=='')
				{
					alert('请填写商户名字!');
					return false;
				}
				if($('#address').val()=='')
				{
					alert('请填写商户地址!');
					return false;
				}
				if($('#tel').val()=='')
				{
					alert('请填写商户电话!');
					return false;
				}
				if(!$('#tel').val().match(new RegExp(/^[0-9]{3,4}\-[0-9]{7,8}$/))){
				     alert('电话格式不正确');
			         return false;
			       }
			    var introduction = FCKeditorAPI.GetInstance("introduction").GetXHTML(true);			
				if(introduction=='')
				{
					alert('请填写商户介绍!');
					return false;
				}
				var pname = $('#logo').val();
				if(pname=='')
				{
					alert('请选择商户logo!');
					return false;
				}
				var _pname = $('#shopImage').val();
				if(_pname=='')
				{
					alert('请选择门店图片!');
					return false;
				}
			    var endPhoto = pname.substring(pname.lastIndexOf(".")+1,pname.length).toLowerCase();
			    if(endPhoto!='jpg' && endPhoto!='gif'&& endPhoto!='jpeg'&& endPhoto!='bmp'&& endPhoto!='png'){
			    	alert('上传图片必须是jpg、gif、jpeg、bmp、png格式');
			    	return false;
				}
			    var _endPhoto = _pname.substring(_pname.lastIndexOf(".")+1,_pname.length).toLowerCase();
			    if(_endPhoto!='jpg' && _endPhoto!='gif'&& _endPhoto!='jpeg'&& _endPhoto!='bmp'&& _endPhoto!='png'){
			    	alert('上传图片必须是jpg、gif、jpeg、bmp、png格式');
			    	return false;
				}
			}
		</script>
		<title>创建商户</title>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../../images/mail_leftbg.gif">
    				<img src="../../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">创建商户</div></td>
			      		</tr>
    				</table>
    			</td>
    			<td width="16" valign="top" background="../../images/mail_rightbg.gif">
    				<img src="../../images/nav-right-bg.gif" width="16" height="29" />
    			</td>
  			</tr>
  			
  			
  			<tr>
    			<td height="71" valign="middle" background="../../images/mail_leftbg.gif">&nbsp;</td>
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
					            					<td class="left_txt">当前位置：商户管理&nbsp;>>&nbsp;创建商户</td>
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
									                	<img src="../../images/title.gif" width="54" height="55">
									                </td>
									                <td width="90%" valign="top">
										                <span class="left_txt2">在这里，您可以创建新的商户对象</span><br/>
										                <span class="left_txt3">注意!</span>
										                <span class="left_txt3">创建完成后需要进行"上线"操作后,才可以生效.</span>
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
            								<form action="<c:url value="create.action"/>" method="post" enctype="multipart/form-data">
            									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									              	<tr>
									                	<td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">商户名称：</td>
									                	<td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
									                	<td width="42%" height="30" bgcolor="#f2f2f2">
									                		<input type="text" id='name' name="name" value=""/>
									                	</td>
									                	<td width="35%" height="30" bgcolor="#f2f2f2" class="left_txt">商户名称[必填]</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">门店图片：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<input type="file" id='shopImage' name="shopImage" value=""/>
									                	</td>
									                	<td height="30" class="left_txt">门店图片[必填](图片大小:196*148)</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">商户LOGO：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td bgcolor="#f2f2f2" height="30">
									                		<input type="file" id='logo' name="logo" value=""/>
									                	</td>
									                	<td bgcolor="#f2f2f2" height="30" class="left_txt">商户LOGO[必填](图片大小:62*63)</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">商户地址：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<input type="text" id='address' name="address" value=""/>
									                	</td>
									                	<td height="30" class="left_txt">商户地址[必填]</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">商户电话： </td>
									                	<td bgcolor="#f2f2f2" >&nbsp;</td>
									                	<td bgcolor="#f2f2f2" height="30">
									                		<input type="text" id='tel' name="tel" value=""/>
									                	</td>
									                	<td bgcolor="#f2f2f2" height="30" class="left_txt">商户电话[必填](格式:区号-电话)</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">商户介绍：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" >
									                		<FCK:editor instanceName="introduction" toolbarSet="ecommerce">
																<jsp:attribute name="value">
																</jsp:attribute>
																<jsp:body>
																	<FCK:config SkinPath="skins/office2003/"/>
																</jsp:body>
															</FCK:editor>
									                		<!-- <textarea rows="4" cols="40" name="introduction" id="introduction"></textarea> -->
									                	</td>
									                	<td height="30" class="left_txt">商户介绍[必填]</td>
									              	</tr>

              										<tr>
                										<td height="17" colspan="4" align="right" >&nbsp;</td>
              										</tr>

            									</table>
            									<div align="right">
            										<input type="submit" name="create" onclick="return check()" value="创建"/>&nbsp;&nbsp;
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
    			<td background="../../images/mail_rightbg.gif">&nbsp;</td>
  			</tr>
  			<tr>
    			<td valign="middle" background="../../images/mail_leftbg.gif">
    				<img src="../../images/buttom_left2.gif" width="17" height="17" />
    			</td>
      			<td height="17" valign="top" background="../../images/buttom_bgs.gif">
      				<img src="../../images/buttom_bgs.gif" width="17" height="17" />
      			</td>
    			<td background="../../images/mail_rightbg.gif">
    				<img src="../../images/buttom_right2.gif" width="16" height="17" />
    			</td>
  			</tr>
		</table>
	</body>
</html>