<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/jstl_contexpath.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>前台登陆</title>
</head>
<body>

<form name="loginForm" action="../reg/login.action" method="post">
<input type="text" name="name"/><br>
<input type="password" name="password"/><br>
<input type="submit" value="登陆">
</form>
</body>
</html>