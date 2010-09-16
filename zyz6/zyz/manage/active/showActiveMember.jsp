<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<%@ page language="java" import="com.linkage.app.gqt.backstage.active.controller.ActiveController" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>活动管理</title>
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
		<script src="<c:url value="/javascript/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/tree/MzTreeView12-pack.js"/>" type="text/javascript"></script>
		<script src="showactive.js" type="text/javascript"></script>
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
		<input type="hidden" name="create_orgTypeParentId" id="actId" value=""/>
		<input type="hidden" name="create_orgTypeParentId" id="actIdd" value=""/>
		<input type="hidden" name="sendUuid" id="sendUuidId" value=""/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">活动管理</div></td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;活动列表</td>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;活动列表</td> 
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
           									<table width="100%" border="1" cellspacing="0" cellpadding="0" id="orgtypetabletree">
           										<thead>
													<tr>
														<th width="20%" class="left_txt2">活动主题</th>
														<th width="10%" class="left_txt2">开始日期</th>
														<th width="10%" class="left_txt2">结束日期</th>
														<th width="10%" class="left_txt2">活动状态</th>
														<th width="50%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="active" items="${activeList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
														<tr height="10" bgcolor="#f2f2f2" id="${active.actId}" class="child-of-${active.actId}">
														
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0}">
														<tr height="10" id="${active.actId}" class="child-of-${active.actId}">
													</c:if>
													<td align="center">${active.actTitle }</td>
													<td align="center">${active.startDate }</td>
													<td align="center">${active.endDate }</td>
													<td align="center">
													<c:if test="${(active.actStatus) ==0 }">
														结束
													</c:if>
													<c:if test="${(active.actStatus) ==1 }">
														在线
													</c:if>
													<c:if test="${(active.actStatus) ==2 }">
														停止
													</c:if>
													</td>
													<td align="center">
	<a href="javascript:void(0)" class="class_changestate" id="changestate" actId="${active.actId}" actIdd="${active.actStatus}">更改状态</a>&nbsp;&nbsp;&nbsp;&nbsp;													
	<a href="javascript:void(0)" class="class_tree" id="del" name="${active.actTitle}">自动匹配</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(0)" class="class_tree" id="del" name="${active.actTitle}">手工匹配</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="delete.action?actId=<c:url value="${active.actId}"/>" class="class_del" id="del" name="${active.actTitle}">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
														</tr>
													</c:forEach>
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
		
		<div id="form-createactivity" title="发布活动" style="width:100px;height:150px">
			<form>
			<label for="name">主题:</label>
					<input type="text" name="actTitle" id="actTitleId" class="text ui-widget-content ui-corner-all" /><br>
			<label for="name">描述:</label>
					<input type="text" name="actDesc" id="actDescId" class="text ui-widget-content ui-corner-all" /><br>					
			<label for="name">开始日期:</label>
					<input type="text" id="startDateId" onfocus="WdatePicker();" class="text ui-widget-content ui-corner-all" /><br>
			<label for="name">结束日期:</label>
					<input type="text" id="endDateId" onfocus="WdatePicker();" class="text ui-widget-content ui-corner-all" />
			</form>
		</div>
		
		<div id="form-changestate" title="更改状态" style="width:100px;height:150px">
			<form>
			<input type="radio" name="state" value = "0">结束<br>

			<input type="radio" name="state" value = "1">在线<br>

			<input type="radio" name="state" value = "2">停止<br>
			</form>
		</div>
		<div id="kkk" title="选择人员"></div>
		
		<div id="sendMessage" title="发送短信">
			<div id="inputNumDiv"></div>
					<textarea name="message" id="messageId" cols="50" rows="5" onKeyUp="javasrript:getInputNum()"> </textarea>  
					
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
	<c:forEach items="${treeList}" var="current">
		tree.N['<c:out value="${current.actTitle}"/>'] = '<c:out value="${current.actAddr}"/>'
	</c:forEach>
	<c:forEach items="${treeList2}" var="current">
		tree.N['<c:out value="${current.actTitle}"/>'] = '<c:out value="${current.actAddr}"/>'
	</c:forEach>
	tree.setURL("/");
	tree.wordLine = false;
	tree.setTarget("main");
	document.getElementById("kkk").innerHTML=tree.toString();
	tree.expandAll();
	
	</script>
	</body>
</html>