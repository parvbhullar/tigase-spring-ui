<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    String info = (String) request.getSession().getAttribute(
					"savereport");
%>
<head>
<title>报告列表</title>
</head>
<body>
<div class="okBox">
<div class="ok" style="display: none" id="okTip">保存成功</div>
</div>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 开发嵌入start--> <c:if test='${p.coreMemberInfo.role.agents}'>
	<a href="/report.do?action=add&flag=2&currentPage=${p.currentPage}"
		class="btnY fr"><span>新建报表</span></a>
</c:if> <c:if test='${p.coreMemberInfo.role.dev}'>
	<a href="/report.do?action=add&flag=1&currentPage=${p.currentPage}"
		class="btnY fr"><span>新建报表</span></a>
</c:if> <c:if test='${p.coreMemberInfo.role.advAndDev}'>
	<c:choose>
		<c:when test="${isCurrentDevRole}">
			<a href="/report.do?action=add&flag=1&currentPage=${p.currentPage}"
				class="btnY fr"><span>新建报表</span></a>
		</c:when>
		<c:otherwise>
			<a href="/report.do?action=add&flag=0&currentPage=${p.currentPage}"
				class="btnY fr"><span>新建报表</span></a>
		</c:otherwise>
	</c:choose>
</c:if> <c:if
	test='${p.coreMemberInfo.role.advertisers || p.coreMemberInfo.role.general}'>
	<a href="/report.do?action=add&flag=0&currentPage=${p.currentPage}"
		class="btnY fr"><span>新建报表</span></a>
</c:if>
<h1 class="tit">报表管理</h1>
<form:form action="" method="post" commandName="command">
	<form:hidden path="currentPage" />
	<c:if test="${not empty p.listBean}">
		<div class="ctl"><a href="#" onclick="deleteReport();"
			class="btnS f1"><span>删除</span></a></div>
	</c:if>
	<table cellspacing="0" cellpadding="0" border="0" class="tabY">
		<col width="40%" />
		<col width="30%" />
		<col width="20%" />
		<col width="10%" />
		<tbody>
			<tr>
				<th>名称</th>
				<c:if test="${null==sortFlag}">
					<th><a href="#"
						onclick="reportDesc('${p.currentPage}','${reportName}');"
						id="descId">创建时间<img alt="降序" src="/images/ico_sortza.gif"></a>
					</th>
				</c:if>
				<c:if test="${null!=sortFlag}">
					<th><a href="#"
						onclick="reportAsce('${p.currentPage}','${reportName}');"
						id="asceId">创建时间<img alt="升序" src="/images/ico_sortaz.gif"></a>
					</th>
				</c:if>
				<th>类型</th>
				<th>报表形式</th>
			</tr>
			<c:if test="${not empty p.listBean}">
				<c:forEach items="${p.listBean}" var="report" varStatus="s">
					<tr>
						<td><form:checkbox path="deleteList"
							value="${report.reportId}" /> <c:if
							test="${report.reportType!=0}">
							<a href="report.do?action=show&reportId=${report.reportId}">
							<airad:cutString size="20" value="${report.reportName }"
								mark="..." /></a>
						</c:if> <c:if test="${report.reportType==0}">
							<airad:cutString size="20" value="${report.reportName }"
								mark="..." />
						</c:if></td>
						<td><fmt:formatDate value="${report.addTime }" type="both"
							pattern="yyyy-MM-dd" /></td>
						<c:if test="${report.reportType==0}">
							<td>活动报告</td>
						</c:if>
						<c:if test="${report.reportType==1}">
							<td>广告组报告</td>
						</c:if>
						<c:if test="${report.reportType==2}">
							<td>广告报告</td>
						</c:if>
						<c:if test="${report.reportType==3}">
							<td>应用报告</td>
						</c:if>
						<c:if test="${report.reportType==4}">
							<td>广告商报告</td>
						</c:if>
						<td><c:choose>
							<c:when test="${report.reportStatus==0}">汇总报告</c:when>
							<c:otherwise>详细报告</c:otherwise>
						</c:choose></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty p.listBean}">
				<tr>
					<td colspan="4">
					<div class="c">暂无数据</div>
					</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<airad:pagination pageSize="${p.pageSize}"
		href="/report.do?action=listReport&currentPage=PAGENUM&desc=${desc}&asce=${asce}"
		totalRecord="${p.totalCount}" currentPage="${p.currentPage}"></airad:pagination>
</form:form></div>
</div>
<!-- 开发嵌入end-->

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script type="text/javascript" src="/js/report.js"></script>
<script>
$(document).ready(function(){
	addCss("listReport");
	<%if ("ok".equals(info)) {%>
	  //让层5秒后消失
    $("#okTip").show();
    $("#okTip").animate({
        opacity: 1.0
    }, 5000).fadeOut("slow", function() {
        $(this).hide();
    });
	<%request.getSession().setAttribute("savereport", "");
			}%>
}); 
</script>
</body>
</html>
