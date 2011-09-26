<%@ page contentType="text/html; charset=GBK"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <%@ include file="../jspf/errors.jsp" %>
	<form action="/demo.do?action=doAdd" method="post">
	   姓名：<input name="name" /><br/>
	 email：<input name="email" /><br/>
	 下属姓名：<input name="user.name" /><br/>
	 下属email：<input name="user.email" /><br/>
	 下属年纪：<input name="user.age" /><br/>
	 下属生日：<input name="user.birthDay" /><br/>
	 <input type="submit" value="提交">  
	</form>
  </body>
</html>
