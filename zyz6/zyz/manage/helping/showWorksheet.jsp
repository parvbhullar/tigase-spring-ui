<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<%@ page language="java" import="com.linkage.app.gqt.backstage.helping.controller.HelpingController" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>帮扶管理</title>
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
	<script>
		// 结束帮扶

		function finishWorksheet(id){
			if(confirm("确定结束该帮扶信息？")){
				$.ajax({
					type: "post",
					url: "/zyz/manage/helping/finishWorksheet.action",
					data: "id="+id,
					success: function(msg){
						alert("操作成功!");
						window.location = "/zyz/manage/helping/showWorksheet.action";
					},
					error:function(msg){
						alert("操作失败!");
					}
				});
			}
		}		
		
		
		// 删除活动
	
		function deleteWorksheet(id){
			if(confirm("确定删除该条信息？")){
				$.ajax({
					type: "post",
					url: "/zyz/manage/helping/deleteWorksheet.action",
					data: "id="+id,
					success: function(msg){
						alert("删除成功!");
						window.location = "/zyz/manage/helping/showWorksheet.action";					},
					error:function(msg){
						alert("删除失败!");
					}
				});
			}
		}		
		
		
		
		
		
		// 分页
	  	function goPage(page){
	  	  	document.location='/zyz/manage/helping/showWorksheet.action?page='+page;
	  	}
	</script>
	<body>
		<input type="hidden" name="sendUuid" id="sendUuidId" value=""/>
		<input type="hidden" name="actId" id="actIdId" value=""/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">帮扶管理</div></td>
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
      					<tr>
        					<td valign="top">
        						<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
					          		<tr>
					            		<td height="8">
					            			<table width="100%">
					            				<tr>
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;帮扶列表</td>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;工单列表</td> 
                									<td class="left_bt2" align="right">
                										&nbsp;
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
														<th width="20%" class="left_txt2">主题</th>
														<th width="10%" class="left_txt2">开始日期</th>
														<th width="10%" class="left_txt2">结束日期</th>
														<th width="10%" class="left_txt2">状态</th>
														<th width="50%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="worksheet" items="${worksheetList}" varStatus="status">
													
													<c:if test="${(status.count-1) % 2 ==0 }">
														<tr height="10" bgcolor="#f2f2f2" id="${worksheet.worksheetid}" class="child-of-${worksheet.worksheetid}">
														
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0}">
														<tr height="10" id="${worksheet.worksheetid}" class="child-of-${worksheet.worksheetid}">
													</c:if>
													<td align="center">${worksheet.HTitle }</td>
													<td align="center">${worksheet.HStartdate }</td>
													<td align="center">${worksheet.HOverdate }</td>
													<td align="center">													
														发布
													</td>
													<td align="center">
														<a href="javascript:finishWorksheet('${worksheet.worksheetid}')">结束工单</a>&nbsp;&nbsp;&nbsp;&nbsp;													
														<a href="/zyz/manage/helping/editWorksheet.action?id=<c:url value="${worksheet.worksheetid}"/>" class="class_edit" id="edit" name="${worksheet.HTitle}">修改</a>&nbsp;&nbsp;&nbsp;&nbsp;														
														<a href="javascript:deleteWorksheet('${worksheet.worksheetid}')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="/zyz/manage/helping/worksheetDetail.action?id=<c:url value="${worksheet.worksheetid}"/>" class="class_query" id="query" name="${worksheet.HTitle}">查看</a>&nbsp;&nbsp;&nbsp;&nbsp;
													
													
													
													
													
													</td>
													

	
													</tr>
													</c:forEach>
													<tr>
														<td colspan=5 align="right">${toolNav}</td>
													</tr>
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
		
		
		

		

</body>
</html>