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
		<title>编辑优惠券广告</title>
		<script type="text/javascript">
			function FCKeditor_OnComplete(editorInstance) {
				window.status = editorInstance.Description;
			}
		</script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/javascript/jquery-1.4.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/fckeditor/fckeditor.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/javascript/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
			function relogo(but){
				if(but.name=="rupload"){
					document.getElementById("rupload").value="取消上传LOGO";
					document.getElementById("rupload").name="cupload";
					document.getElementById("uploadPhoto").style.display="";
				}else if(but.name=="cupload"){
					document.getElementById("rupload").value="重新上传LOGO";
					document.getElementById("uploadPhoto").outerHTML="<input type='file' name='photo' id='uploadPhoto' value='' />";
					document.getElementById("rupload").name="rupload";
					document.getElementById("uploadPhoto").style.display="none";
				}
			}
		</script>
		<script type="text/javascript">
			function check()
			{
				
				var pname = $('#uploadPhoto').val();
				if(pname!=''){
				    var endPhoto = pname.substring(pname.lastIndexOf(".")+1,pname.length).toLowerCase();
				    if(endPhoto!='jpg' && endPhoto!='gif'&& endPhoto!='jpeg'&& endPhoto!='bmp'&& endPhoto!='png')
				    {
				    	alert('上传图片必须是jpg、gif、jpeg、bmp、png格式');
				    	return false;
					}
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
			        		<td height="31"><div class="titlebt">修改静态广告</div></td>  
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
					            					<td class="left_txt">当前位置：首页管理&nbsp;&gt;&gt;&nbsp;修改静态广告</td>
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
										                <span class="left_txt2">在这里，您可以修改静态广告对象</span><br/>
										                <span class="left_txt3">注意!</span>
										                <span class="left_txt3">该操作不改变缓存状态.</span>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;广告基本参数设置</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            								<form action="<c:url value="../update.action"/>" method="post" enctype="multipart/form-data">
											<input type="hidden" name="id" value="${ad.id}">
											<input type="hidden" name="_method" value="put">
											<input type="hidden" name="oldlogo" value="${ad.pic}">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
									              	
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">广告图片：</td>
									                	<td>&nbsp;</td>
									                	
									                	<td height="30">
									                		<img id="imglogo" src="<c:url value="${ad.pic}"/>" height="30px"/>
															<input type="button" name="rupload" id="rupload" value="重新上传图片" onClick="relogo(this);"/>
															<input type="file" name="photo" id="uploadPhoto" value="" style="display:none"/><br/>
									                	</td>
									                	<td height="30" class="left_txt">广告图片(590*230)</td>
									              	</tr>
									              	
									              	
									              	
									              	
									              	
									              	
              										<tr>
                										<td height="17" colspan="4" align="right" >&nbsp;</td>
              										</tr>

            									</table>
											<div align="right">
											<input type="submit" name="create" onclick="return check()" value="修改"/>
											<input type="reset" name="reset" value="重置"/>
											<input type="button" name="goback" value="返回" onclick="history.go(-1)"/>
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