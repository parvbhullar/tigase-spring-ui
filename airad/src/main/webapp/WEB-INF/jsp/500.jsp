<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>错误页面</title>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jspf/errors.jsp"%>
<div id="main">
<div class="mainCon">
<div class="infoTip long">
<h1>服务器错误。</h1>
<p>您现在可以：</p>
<ul>
  <li><a href="/">网站首页。</a></li>
  <li><a href="member.do?action=queryMember">回到会员中心。</a></li>
</ul>
<p>如果遇到问题，欢迎联系我们。</p>
</div>
</div>
</div>
 <%@ include file="/WEB-INF/jspf/footer.jsp" %>
 </body>
</html>
