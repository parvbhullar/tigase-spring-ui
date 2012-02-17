<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<h1>
 
Login</h1><div id="login-error">
 
 
${error}</div><form action="../../j_spring_security_check" method="post" >
 
 
 
<p>
 
 
<label for="j_username">Username</label>
<input id="j_username" name="j_username" type="text" />
</p><p>
 
 
<label for="j_password">Password</label>
<input id="j_password" name="j_password" type="password" />
</p><input  type="submit" value="Login"/>        
 
</form>
<ul>
	<h4>账号及登录对应页面</h4>
	<li>john/admin <a href="../main/admin"> 管理员admin页面 </a> </li>
	<li>jane/user  <a href="../main/common">普通用户页面    </a></li>
</ul>
 <ul>
 	<h4>security 规则</h4>
 	<li>以普通用户登录,访问<a href="../main/admin">管理员admin页面 </a>的话会被拒绝</li>
 	<li>不登录访问<a href="../main/admin">管理员admin页面 </a>,<a href="../main/common">普通用户页面    </a>都被跳转到登录页面</li>
 </ul>
</body>
</html>