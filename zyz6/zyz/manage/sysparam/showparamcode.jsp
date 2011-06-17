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
		<script src="<c:url value="/javascript/tree/MzTreeView12-pack.js"/>" type="text/javascript"></script>
		<script src="<c:url value="showparamcode.js"/>" type="text/javascript"></script>
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
	</head>
	<body>
		<input type="hidden" id="paramid1">
		<input type="hidden" id="paramorder1">
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
													<a href="javascript:manualSel(${sysparam.paramid},${sysparam.paramorder})">选择对应组织</a>&nbsp;&nbsp;&nbsp;&nbsp;
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
		
		<div id="kkk" title="选择组织"></div>
		
		<div id="dialog-auditSysparam" title="审核">
			<p class="validateTips"></p>
			<form>
			</form>
		</div>		
		<script language="javascript" type="text/javascript">
	var MzTreeViewTH="<table class='MzTreeViewRow'><tr><td class='MzTreeViewCell0'>";
	var MzTreeViewTD="\"</td><td class='MzTreeViewCell1'>\"+ sid +\"</td></tr></table>\"";
	window.tree = new MzTreeView("tree");
	/*
	tree.icons["property"] = "property.gif";
	tree.icons["css"] = "collection.gif";
	tree.icons["event"] = "collection.gif";
	tree.icons["book"]  = "book.gif";
	tree.iconsExpand["book"] = "bookopen.gif"; //展开时对应的图片
	*/
	tree.setIconPath("../../javascript/tree/images/"); //可用相对路径
	

	tree.N['<c:out value="${province}"/>'] = 'ctrl:sel;checked:1;T:<c:out value="${currentOrgName}"/>'

		
	<c:forEach items="${newList}" var="current">
		tree.N['<c:out value="${current.orgName}"/>'] = '<c:out value="${current.orgContactor}"/>'
	</c:forEach>
		
	tree.setURL("/");
	tree.wordLine = false;
	tree.setTarget("main");
	document.getElementById("kkk").innerHTML=tree.toString();
	tree.expandAll();
</script>			
	</body>
</html>