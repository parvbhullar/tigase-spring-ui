<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.Constants"%>
<%@page import="com.mitian.airad.web.form.AccountForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
<title>操作结果</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><!-- 开发嵌入start-->
<div class="suc long">
<div>
<%
AccountForm accountForm = (AccountForm)request.getAttribute("command");
%>
<c:if test="${command.operateType==Constants.OperateStatus.ACCOUNT_SEND_EMAIL_URL_ABATE.getOperateType()}">
	<h2>发送邮件异常</h2>
	<ul>
	</ul>
	<ul>
		<form:form name="myFrm"
			action="member.do?action=sendResettingPasswordEmail"
			commandName="command">			
		</form:form>
		<li>点 <a href="account.do?action=retrieveAccountPassword" target="_blank"><strong>这里</strong></a>填写邮箱地址，
		再次发送财务密码重置邮件，点击邮件中的链接，完成密码重置。</li>
	</ul>
</c:if>



 


</div>
</div>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>