<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<html>
<head>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
<title>Email Abate</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><!-- 开发嵌入start-->
<div id="styleDiv" class="suc long">
<form:form name="form" commandName="command" method="post" action="member.do?action=logon">
<form:hidden path="memberId"/>
</form:form>
邮件失效！<a href="javaScript:document.form.submit()">登录Airad平台重新发送邮件</a>

</div>
<!-- 开发嵌入end-->
</div></div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>