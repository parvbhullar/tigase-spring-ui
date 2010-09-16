<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>系统参数</title>
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<link href="/zyz/javascript/jqueryUI-1.8.2/themes/ui-lightness/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" />
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jquery.treeTable.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.core.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.widget.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.position.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.dialog.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="show.js"/>" type="text/javascript"></script>
	</head>
	<body>
		<input type="hidden" id="paramid">
		<input type="hidden" id="dialogtitle">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">系统参数</div></td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;系统参数列表</td>
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
                									</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr><td>&nbsp;</td></tr>
          							<tr>
            							<td>
            								<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" class="nowtable">
              									<tr>
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;系统参数列表</td> 
                									<td class="left_bt2" align="right">
													<a href="javascript:void(0)" id="createSysparam">新建参数</a>&nbsp;&nbsp;&nbsp;&nbsp;
                									</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
           									<table width="100%" border="1" cellspacing="0" cellpadding="0" id="orgtypetabletree">
           										<thead>
													<tr>
														<th width="10%" class="left_txt2">序号</th>
														<th width="10%" class="left_txt2">编码</th>
														<th width="20%" class="left_txt2">键</th>
														<th width="10%" class="left_txt2">值</th>
														<th width="10%" class="left_txt2">状态</th>
														<th width="40%" class="left_txt2">描述</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="sysparam" items="${sysparamList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
														<tr height="10" bgcolor="#f2f2f2" id="${sysparam.paramid}" class="child-of-${sysparam.paramid}">
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0}">
														<tr height="10" id="${sysparam.paramid}" class="child-of-${sysparam.paramid}">
													</c:if>
													<td align="center">${status.count }</td>
													<td align="center">${sysparam.paramcode }</td>
													<td align="center">${sysparam.paramkey }</td>
													<td align="center">${sysparam.paramvalue }</td>
													<td align="center">
													<c:if test="${(sysparam.paramstate) ==0 }">
														待审核
													</c:if>
													<c:if test="${(sysparam.paramstate) ==1 }">
														通过
													</c:if>	
													<c:if test="${(sysparam.paramstate) ==2 }">
														不通过
													</c:if>
													</td>
													<td align="center">
	<a href="javascript:void(0)" class="class_audit" id="audit" paramid="${sysparam.paramid}">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;													
	<a href="delete.action?paramid=<c:url value="${sysparam.paramid}"/>" class="class_del" id="del" name="${sysparam.paramkey}">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(0)" class="class_edit" id="edit" name="${sysparam.paramkey}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
														</tr>
													</c:forEach>
													<script type="text/javascript">
												  	function goPage(page)
												  	{
												  	  	document.location.href='showsysparam.action?page='+page;
												  	}
											  		</script>
													<tr><td colspan="6" align="right">${toolNav}</td></tr>
												</tbody>
           									</table>
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
		
		<div id="dialog-addSysparam" title="创建系统参数" style="width:100px;height:150px">
			<form>
			<label>系统编码:</label>
			<input type="text"  	id="paramcodeId" 	class="text ui-widget-content ui-corner-all" /><br>
			<label>系统参数键:</label>
			<input type="text"   	id="paramkeyId"		class="text ui-widget-content ui-corner-all" /><br>
			<label>系统参数值:</label>
			<input type="text"  	id="paramvalueId" 	class="text ui-widget-content ui-corner-all" /><br>
			<label>系统参数描述:</label>
			<input type="text"  	id="paramdescId"	class="text ui-widget-content ui-corner-all" /><br>
			</form>
		</div>
		
		<div id="dialog-auditSysparam" title="审核">
			<p class="validateTips"></p>
			<form>
			</form>
		</div>					
	</body>
</html>