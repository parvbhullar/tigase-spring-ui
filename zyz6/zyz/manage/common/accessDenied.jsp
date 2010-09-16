<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head> 
		<title>访问被拒绝 </title> 
	</head>

	<body>
		<h1> 访问被拒绝 ! </h1>
		<!-- ${requestScope['SPRING_SECURITY_403_EXCEPTION'].message} -->
		你没有访问该页面的权限!
	</body>
</html>