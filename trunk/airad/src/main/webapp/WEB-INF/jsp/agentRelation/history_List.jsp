<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告商充值记录管理</title> 
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>
<div id="main">
<%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 开发嵌入start-->
<div class="mainCon">
<!-- 开发嵌入end--> 
<div class="mainCon"> 
<h1>代理商 <span>»广告商充值列表</span> </h1> 
<form:form action="agentRelation.do?action=hisList" commandName="command"  method="post" name="myform">
<div class="searchBox">
  <div class="search"><form:input path="coreAgentRelation.email"/></div>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="tabYH">
    <col width="20%"/>
    <col width="20%"/>
    <col width="20%"/>
    <tbody>
        <tr>
            <th>广告商名称</th>
            <th>为该广告商充值的总金额</th>
            <th>为该广告商充值金额的剩余金额</th>
        </tr>
        <c:forEach items="${p.listBean}" var="AccountInfoView">
            <tr>
                <td>
                <img height="16" width="16" align="absmiddle" alt="广告商" src="images/ico_act.gif"/>
                <airad:cutString size="10" value="${AccountInfoView.email}" mark="..."/>
                </td>
                <td><fmt:formatNumber type="number" value="${AccountInfoView.countAgent}" pattern="#,##0.00"/></td>
                <td><fmt:formatNumber type="number" value="${AccountInfoView.agentBlance}" pattern="#,##0.00"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<airad:pagination 
pageSize="${p.pageSize}"
 href="/agentRelation.do?action=hisList&currentPage=PAGENUM&coreRechargeHis.email=${emails}"
totalRecord="${p.totalCount}"
 currentPage="${p.currentPage}">
 </airad:pagination>
</form:form> 
</div>
<!-- 开发嵌入end-->
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script>
document.onkeypress=agentRechargeHistorySearchSubmit;
$(document).ready(function(){
	  addCss("hisList");
}); 
</script>
</body>
</html>
