<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>应用收益列表</title>
<script type="text/javascript" src="js/Calendar/Calendar.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 开发嵌入start-->
<div class="wa">只有在您提供会员信息并通过审核之后，您的广告才可以被发布。请 <a
	href="member.do?action=queryMember"><strong>完善会员信息</strong></a> 。</div>
<h1>应用程序管理 <span>» 应用收益</span></h1>
<form:form action="" method="post"
	commandName="command" name="myform">
<table cellspacing="0" cellpadding="0" border="0" class="tabYH">
	<col width="15%"/>
	<col width="15%"/>
	<tbody>
		<tr>
			<th>应用名称</th>
			<th>收益金额</th>
		</tr>
		
		    <c:forEach items="${p.listBean}" var="AccIncome" varStatus="s">
        <tr>
		    <td>${AccIncome.appName}</td>
		    <td>${AccIncome.sumOffer}</td>
		   </tr>
		  </c:forEach>
	</tbody>
</table>
<airad:pagination pageSize="${p.pageSize}" href="/appIncome.do?action=listAppIncome&currentPage=PAGENUM&app.appName=${appName}&desc=${desc}&asce=${asce}"
totalRecord="${p.totalCount}" currentPage="${p.currentPage}"></airad:pagination>
</form:form>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script>
$(document).ready(function(){
  addCss("listAppIncome");
}); 
</script>
</body>
</html>
