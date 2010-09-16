<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/common/jstl_contexpath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>帮扶统计</title>
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
			a.online {font-size:14px;}
			a.online:hover {color:red;font-weight:bold;text-dec}
		</style>
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<!-- 树形表格 -->
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<!-- 树形表格 -->
		<script src="<c:url value="/javascript/jquery.treeTable.js"/>" type="text/javascript"></script>
		<!-- 日期控件 -->
		<script src="<c:url value="/javascript/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
		</head>



		<script type="text/javascript">
			$(document).ready(function() {
				//树形表格
			    $("#orgtypetabletree").treeTable({
			     // expandable: false
			    });
			    
	

			});
		</script>

	<script>
		function saveIssueActive(){

			// 开始日期
			var HStartdate = document.getElementById('startDate').value.trim();
			// 结束日期
			var HOverdate = document.getElementById('overDate').value.trim();
				
			
			// 开始日期不能为空
			if(HStartdate == ""){
				alert("开始日期不能为空！");
		   		return false;
			}

		   	// 结束日期不能为空
			if(HOverdate == ""){
				alert("结束日期不能为空！");
		   		return false;
			}
			// 结束日期 >= 开始日期
			if(HOverdate < HStartdate){
				alert("结束日期必须大于等于开始日期！");
		   		return false;
			}

			document.issueForm.action="helpingCount.action";
			document.issueForm.submit();
		}
		
		
			function helpingList(orgid, startdate, overdate){
				window.location = "findHelpingList.action?orgid=" + orgid + "&startdate=" + startdate + "&overdate=" + overdate;
			}
		
			String.prototype.trim = function(){ 
				return this.replace(/^[\s|\u3000]+|[\s|\u3000]+$/g,"");
			}
		</script>	
		
		
		
		
	<body>

		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">帮扶统计</div></td>
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
          							<tr><td>
          							
          							<form name="issueForm" method="post" enctype="multipart/form-data">
          							<table >
          							
          								<tr>
          								<td style="width:15%" style="text-align:right">开始日期：</td>
										<td style="width:25%"><input type="text" name="startDate" id="startDate" onfocus="WdatePicker();"/></td>
										<td style="width:15%" style="text-align:right">结束日期：</td>
										<td style="width:25%"><input type="text" name="overDate" id="overDate" onfocus="WdatePicker();"/></td>
          								<td style="width:20%"><input type="button" name="save" value="统 计" onClick="saveIssueActive()"></td>
          								</tr>          							
          							</table>
          							</form>
          							</td></tr>
          							
          							
          							<tr><td style="height:20px;"></td></tr>
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
														<th width="55%" class="left_txt2">机构名称</th>
														<th width="10%" class="left_txt2">开始日期</th>
														<th width="10%" class="left_txt2">结束日期</th>
														<th width="15%" class="left_txt2">帮扶数目</th>
													</tr>
												</thead>
													
												<tbody>
													<c:forEach var="org" items="${orgList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
													<c:if test="${org.level >1 }">
														<tr height="10" bgcolor="#f2f2f2" id="${org.orgId}" class="child-of-${org.parentOrgId}">
													</c:if>
													<c:if test="${org.level ==1 }">
														<tr height="10" bgcolor="#f2f2f2" id="${org.orgId}" >
													</c:if>
													</c:if>
													
													<c:if test="${(status.count-1) % 2 !=0}">
													<c:if test="${org.level >1 }">
														<tr height="10" id="${org.orgId}" class="child-of-${org.parentOrgId}">
													</c:if>
													<c:if test="${org.level ==1 }">
														<tr height="10" id="${org.orgId}">
													</c:if>
													</c:if>
														<td align="left" class="liuyang" style="padding-left:40px;">${org.orgName }</td>
												
													
														<td align="center">${startDate}</td>
														<td align="center">${overDate}</td>
														<td align="center">
															<a href="#" class="online" onClick="helpingList('${org.orgId}','${startDate}', '${overDate}')">${org.num }</a>&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														
														
													</tr>
													</c:forEach>
												</tbody>													
													
													
													
													
												</thead>	
												
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