<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<html>
<head>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
<title>adGroup suss</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><!-- 开发嵌入start-->
<div id="styleDiv" class="suc long">
<form:form name="jumpfrm" commandName="command" method="get" action="adGroup.do?action=selectAll">
  <form:hidden path="campaignId"/>
</form:form>
操作异常！返回<a href="javaScript:document.jumpfrm.submit()">广告组列表</a>

</div>
<!-- 开发嵌入end-->
</div></div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>