<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
<title>提示信息</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><!-- 开发嵌入start-->
    <c:choose>
    <c:when test='${errMessage=="isLogon" }'>
    <div class="stop long">
<div>
<h2>抱歉，您已经登录</h2>
<ul>
<li>您可以<a href="/member.do?action=logon">返回会员中心</a></li>
<li>或者<a href="member.do?action=logout">退出登录</a></li>
</ul>
</div>
</div>
    </c:when>
    <c:otherwise>
    <div class="stop long">
<div>
<h2>抱歉，您没有权限浏览该内容</h2>
<ul>
<li>您可以<a href="javascript:history.go(-1)">返回上一页</a></li>
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