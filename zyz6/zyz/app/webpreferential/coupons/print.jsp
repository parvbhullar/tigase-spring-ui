<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String pic = request.getContextPath()+request.getParameter("pic");
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css" ></link>
		<title>打印优惠券</title>
	</head>
	<script type="text/javascript">
		window.print();
		//window.close();
	</script>
	<body>
		<img src="<%=pic %>" />
	</body>
</html>