<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
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
<c:choose>
	<c:when test='${advertiserMessage == "ok" }'>
<div class="suc long">
<div>
	<h2>认证成功</h2>
	<ul>
		<li>
		<!-- <a href="member.do?action=logon">返回主页</a> -->
		<a href="campaign.do?action=listCampaign">返回主页</a>
		</li>
	</ul>
 </div>
</div>
	</c:when>
	<c:otherwise>
<div class="stop long">
<div>
	<h2>认证失败</h2>
  <ul>
  <c:forEach items="${command.errors}" var="errorinfo">
  <li>${errorinfo.value}</li>
  </c:forEach>
    <li><a href="advertiser.do?action=authenticatePage">返回认证页面</a></li>
  </ul>
 </div>
</div>
	</c:otherwise>
</c:choose>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>