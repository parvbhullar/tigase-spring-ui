<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/javascript/jquery-1.4.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/javascript/My97DatePicker/WdatePicker.js"></script>
		<link href="../../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<title>创建优惠券</title>
		<script type="text/javascript">
			function FCKeditor_OnComplete(editorInstance) {
				window.status = editorInstance.Description;
			}
		</script>
		<script type="text/javascript">
			function check(){
				if($('#name').val()=='')
				{
					alert('请填写优惠券名字!');
					return false;
				}
				var pname = $('#uploadPhoto').val();
				if(pname=='')
				{
					alert('请选择照片!');
					return false;
				}
			    var endPhoto = pname.substring(pname.lastIndexOf(".")+1,pname.length).toLowerCase();
			    if(endPhoto!='jpg' && endPhoto!='gif'&& endPhoto!='jpeg'&& endPhoto!='bmp'&& endPhoto!='png'){
			    	alert('上传图片必须是jpg、gif、jpeg、bmp、png格式');
			    	return false;
				}
				if($('#startTimeStr').val()=='')
				{
					alert('请选择开始时间!');
					return false;
				}
				if($('#endTimeStr').val()=='')
				{
					alert('请选择结束时间!');
					return false;
				}
				 var introduction = FCKeditorAPI.GetInstance("introduction").GetXHTML(true);
				if(introduction=='')
				{
					alert('请填写优惠券介绍!');
					return false;
				}
				if(introduction.length>225)
				{
					alert('优惠券介绍最多输入225个字');
					return false;
				}
				 var help = FCKeditorAPI.GetInstance("help").GetXHTML(true);
				if(help=='')
				{
					alert('请填写优惠券使用帮助!');
					return false;
				}
				if($('#message').val()=='')
				{
					alert('请输入短信内容!');
					return false;
				}
			}
		</script>
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
			        		<td height="31"><div class="titlebt">创建优惠券</div></td>
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
					            					<td class="left_txt">当前位置：优惠券管理&nbsp;>>&nbsp;创建优惠券</td>
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
										                <span class="left_txt2">在这里，您可以创建新的优惠券对象</span><br/>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;优惠券基本参数设置</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            								<form action="<c:url value="saveCoupon.action"/>" method="post" enctype="multipart/form-data">
            									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									              	<tr>
									                	<td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">优惠券名称：</td>
									                	<td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
									                	<td width="42%" height="30" bgcolor="#f2f2f2">
									                		<input type="text" id="name" name="name" value=""/>
									                	</td>
									                	<td width="35%" height="30" bgcolor="#f2f2f2" class="left_txt">优惠券名称</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">优惠券图片：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<input type="file" name="photo" id="uploadPhoto" value=""/>
									                	</td>
									                	<td height="30" class="left_txt">优惠券图片(图片大小:269*144)</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">优惠券类别：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2">
									                		<select name="categoryId">
																<option value="77999">77999
															</select>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">优惠券类别</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">归属商户： </td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<select name="shopId">
																<c:forEach var="shop" items="${shopList}">
																	<option value="${shop.id }">${shop.name }</option>
																</c:forEach>
															</select>
									                	</td>
									                	<td height="30" class="left_txt">归属商户</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">开始时间：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2">
									                		<input class="Wdate" type="text" id='startTimeStr' name="startTimeStr" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">开始时间</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">结束时间：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<input class="Wdate" type="text" id='endTimeStr' name="endTimeStr" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									                	</td>
									                	<td height="30" class="left_txt">结束时间</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">优惠券介绍：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2">
									                		<FCK:editor instanceName="introduction" toolbarSet="ecommerce">
																<jsp:attribute name="value">
																</jsp:attribute>
																<jsp:body>
																	<FCK:config SkinPath="skins/office2003/"/>
																</jsp:body>
															</FCK:editor>
									                		<!-- <textarea rows="8" cols="80" name="introduction"></textarea> -->
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">优惠券介绍<br>(<B>注意:最多输入6项，每项最多输入27个字</B>)</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">使用说明：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<FCK:editor instanceName="help" toolbarSet="ecommerce">
																<jsp:attribute name="value">
																</jsp:attribute>
																<jsp:body>
																	<FCK:config SkinPath="skins/office2003/"/>
																</jsp:body>
															</FCK:editor>
									                		<!-- <textarea rows="3" cols="80" name="help"></textarea> -->
									                	</td>
									                	<td height="30" class="left_txt">使用说明</td>
									              	</tr>
													<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">短信内容：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2">
									                		
									                		<textarea rows="8" cols="80" id='message' name="message"></textarea>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">短信内容</td>
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