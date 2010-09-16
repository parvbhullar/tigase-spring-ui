<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>活动统计</title>
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
			.liuyang{padding-left:10px;backgroud:red}
		</style>
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<!-- 树形表格 -->
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<!-- 树形表格 -->
		<script src="<c:url value="/javascript/jquery.treeTable.js"/>" type="text/javascript"></script>
		<!-- 日期控件 -->
		<script src="<c:url value="/javascript/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				//树形表格
			    $("#orgtypetabletree").treeTable({
			     // expandable: false
			    });
			});
			// 初始化
			function init(){
				if(${startdate == "" || startdate == null}){
					document.getElementById("startdate").value = getDate();
				}
				if(${enddate == "" || enddate == null}){
					document.getElementById("enddate").value = getDate();
				}
			}
			// 统计
			function createOrgActiveNum(){
				// 开始日期
				var startdate = document.getElementById('startdate').value.trim();				
				// 结束日期
				var enddate = document.getElementById('enddate').value.trim();
				if(startdate == ""){
					alert("开始日期不能为空！");
					return false;
				}
				if(enddate == ""){
					alert("结束日期不能为空！");
					return false;
				}
				// 开始日期 <= 结束日期
				if(startdate > enddate){
					alert("开始日期不能大于结束日期!");
					return false;
				}
				window.location = "../active/activeCount.action?startdate=" + startdate + "&enddate=" + enddate;
			}
			//活动数目链接
			function activeList(orgid, startdate, enddate){
				window.location = "../active/findActiveList.action?orgid=" + orgid + "&startdate=" + startdate + "&enddate=" + enddate;
			}
			// 获取系统日期 
			function getDate(){
			   var date = new Date();
			   var y = date.getFullYear();
			   var m = date.getMonth() + 1;
			   var d = date.getDate();
			   return "" + y + (m<10?"-0":"-")+ m +(d<10?"-0":"-")+ d;
			}
			//trim 的函数(去除前后空格)
			String.prototype.trim = function(){ 
				return this.replace(/^[\s|\u3000]+|[\s|\u3000]+$/g,"");
			}
		</script>
	</head>
	<body onload="init()">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">活动统计</div></td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;统计管理</td>
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
										                <span class="left_txt2">在这里，您可以查看各级组织的活动信息</span><br>
                									</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
	         							<td>
	         								<table style="width:100%" cellspacing="0" cellpadding="0">
												<tr>
													<td style="width:15%" style="text-align:right">开始日期：</td>
													<td style="width:35%">
														<input type="text" name="startdate" id="startdate" onfocus="WdatePicker();" style="width:40%" value="${startdate}"/>
													</td>
													<td style="width:15%" style="text-align:right">结束日期：</td>
													<td style="width:35%">
														<input type="text" name="enddate" id="enddate" onfocus="WdatePicker();" style="width:40%" value="${enddate}"/>
													</td>
												</tr>
												<tr>
													<td colspan=4 align="right"><input type="button" value="统 计" onClick="createOrgActiveNum()"></td>																								
												</tr>												
											</table>
										</td>
									</tr>
          							<tr><td>&nbsp;</td></tr>
          							<tr>
            							<td>
            								<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" class="nowtable">
              									<tr>
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;组织机构列表</td> 
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
														<th width="40%" class="left_txt2">机构名称</th>
														<th width="15%" class="left_txt2">开始日期</th>
														<th width="15%" class="left_txt2">结束日期</th>
														<th width="27%" class="left_txt2">活动数目</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="active" items="${orgActiveNum}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
													<c:if test="${active.orglevel >1 }">
														<tr height="10" bgcolor="#f2f2f2" id="${active.orgid}" class="child-of-${active.parentorgid}">
													</c:if>
													<c:if test="${active.orglevel ==1 }">
														<tr height="10" bgcolor="#f2f2f2" id="${active.orgid}" >
													</c:if>
													</c:if>
													
													<c:if test="${(status.count-1) % 2 !=0}">
													<c:if test="${active.orglevel >1 }">
														<tr height="10" id="${active.orgid}" class="child-of-${active.parentorgid}">
													</c:if>
													<c:if test="${active.orglevel ==1 }">
														<tr height="10" id="${active.orgid}">
													</c:if>
													</c:if>
														<td align="left" class="liuyang" style="padding-left:40px;">${active.orgname}</td>
														<td align="center" class="liuyang">${startdate}&nbsp;</td>
														<td align="center" class="liuyang">${enddate}&nbsp;</td>
														<td align="center" class="liuyang">
															<c:if test="${active.activenum !=0 }">
																<a href="#" onClick="activeList(${active.orgid},'${startdate}', '${enddate}')" style="text-decoration: underline">${active.activenum}</a>
															</c:if>
															<c:if test="${active.activenum ==0 }">
																<a href="#" onClick="return false" style="cursor:default">${active.activenum}</a>
															</c:if>
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
	</body>
</html>