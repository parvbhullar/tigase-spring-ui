<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<link href="/zyz/javascript/jqueryUI-1.8.2/themes/ui-lightness/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" />
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>


<script type="text/javascript">
		
function validate()
{
	if(document.updateForm.paramcode.value=='')
	{
		alert('请填写编码！');
		document.updateForm.paramcode.focus();
		return false;
	}
	if(document.updateForm.paramkey.value=='')
	{
		alert('请填写键值！');
		document.updateForm.paramkey.focus();
		return false;
	}
	if(document.updateForm.paramvalue.value=='')
	{
		alert('请填写积分！');
		document.updateForm.paramvalue.focus();
		return false;
	}
	
	var num =  /^(\d+)$/;
	if(!num.test(document.updateForm.paramvalue.value))
	{
		alert('积分必须是大于0的数字!');
		document.updateForm.paramvalue.focus();
		return false;
	}

		
	document.updateForm.submit();
}

		</script>
		<style>
body {font:normal 12px 宋体}
a.MzTreeview /* TreeView 链接的基本样式 */ { cursor: hand; color: #000080; margin-top: 5px; padding: 2 1 0 2; text-decoration: none; }
.MzTreeview a.select /* TreeView 链接被选中时的样式 */ { color: highlighttext; background-color: highlight; }
#kkk input {
vertical-align:middle;
}
.MzTreeViewRow {border:none;width:500px;padding:0px;margin:0px;border-collapse:collapse}
.MzTreeViewCell0 {border-bottom:1px solid #CCCCCC;padding:0px;margin:0px;}
.MzTreeViewCell1 {border-bottom:1px solid #CCCCCC;border-left:1px solid #CCCCCC;width:200px;padding:0px;margin:0px;}
</style>
<title></title>
</head>
<body>
			<form action="update.action" name="updateForm" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">系统参数信息修改</div></td>  
			      		</tr>
    				</table>
    			</td>
    			<td width="16" valign="top" background="../images/mail_rightbg.gif">
    				<img src="../images/nav-right-bg.gif" width="16" height="29" />
    			</td>
  			</tr>

  			<tr>
    			<td height="71" valign="middle" background="../images/mail_leftbg.gif">&nbsp;</td>
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
					            					<td class="left_txt">当前位置：系统参数管理&nbsp;&gt;&gt;&nbsp;修改系统参数</td>
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
									                	<img src="../images/title.gif" width="54" height="55">
									                </td>
									                <td width="90%" valign="top">
										                <span class="left_txt2">在这里，您可以修改系统参数</span><br/>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;系统参数基本参数</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									              	<tr>
									                	<td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">编码：</td>
									                	<td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
									                	<td width="32%" height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		
															<input type="text" name="paramcode" disabled="disabled" id="paramcode" value="${sysparam.paramcode }" class="text ui-widget-content ui-corner-all" />
															
									                		<input type="hidden" name="paramid" value="${sysparam.paramid }">
									                	</td>
									                	<td width="45%" height="30" bgcolor="#f2f2f2" class="left_txt">编码</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">键：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<input type="text" name="paramkey" value="${sysparam.paramkey }" id="paramkey" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
									                	</td>
									                	<td height="30" class="left_txt">键</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">积分：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<input type="hidden" name="oldparamvalue" value="${sysparam.paramvalue }">
									                		<input type="text" name="paramvalue" id="paramvalue" value="${sysparam.paramvalue }" class="text ui-widget-content ui-corner-all" />
									                		<font color="red">*</font>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">积分</td>
									              	</tr>
									              	
									              	
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">排序：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<input type="text" name="paramorder" id="paramorder" value="${sysparam.paramorder }" class="text ui-widget-content ui-corner-all" />
									                	</td>
									                	<td height="30" class="left_txt">排序</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">状态：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<select name="paramstate">
									                			<c:if test="${sysparam.paramstate==0}">
									                				<option value="0" selected="selected">无效</option>
									                				<option value="1">有效</option>
									                			</c:if>
									                			<c:if test="${sysparam.paramstate==1}">
									                				<option value="0">无效</option>
									                				<option value="1" selected="selected">有效</option>
									                			</c:if>
									                		</select>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">状态</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">说明：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<textarea name="paramdesc" id="paramdesc" cols="50" rows="2" >${sysparam.paramdesc }</textarea>
									                	</td>
									                	<td height="30" class="left_txt">说明</td>
									              	</tr>
									              	
									              	

              										<tr>
                										<td height="17" colspan="4" align="right" >&nbsp;</td>
              										</tr>

            									</table>
            									<div align="right">
            										<input type="button" onclick="return validate()" value="保存">
													<input type="button" name="goback" value="返回" onclick="history.go(-1)"/>&nbsp;&nbsp;&nbsp;&nbsp;
												</div>
            							</td>
          							</tr>
        						</table>
         					</td>
      					</tr>
    				</table>
    			</td>
    			<td background="../images/mail_rightbg.gif">&nbsp;</td>
  			</tr>
  			<tr>
    			<td valign="middle" background="../images/mail_leftbg.gif">
    				<img src="../images/buttom_left2.gif" width="17" height="17" />
    			</td>
      			<td height="17" valign="top" background="../images/buttom_bgs.gif">
      				<img src="../images/buttom_bgs.gif" width="17" height="17" />
      			</td>
    			<td background="../images/mail_rightbg.gif">
    				<img src="../images/buttom_right2.gif" width="16" height="17" />
    			</td>
  			</tr>
		</table>
			
			</form>
		
</body>
</html>