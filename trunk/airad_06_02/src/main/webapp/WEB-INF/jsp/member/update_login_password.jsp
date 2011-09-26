<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.mitian.airad.web.form.MemberForm"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>密码修改</title>
<link   type="text/css" href="/style/main.css" rel="stylesheet"/>
</head>

<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
  <%@ include file="/WEB-INF/jspf/errors_detail.jsp" %>
<%@ include file="/WEB-INF/jspf/success.jsp"%> 
<h1 class="tit">密码修改</h1>
<div class="leftCon">
<%
	java.text.SimpleDateFormat sdfIn = new java.text.SimpleDateFormat(
			"yyyy-mm-dd HH:mm");
	MemberForm member = new MemberForm();
	member = (MemberForm) request.getAttribute("command");
%> <form:form name="updateLoginPassword" id="updateLoginPassword" method="post"
	action="/member.do?action=doUpdateLoginPassword" commandName="command">
	<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
		<col width="30%" />
		<col width="70%" />
		<tr>
			<th>旧密码</th>
			<td><form:password path="oldPassword" cssClass="half" /></td>
		</tr>
		<tr>
			<th>新密码</th>
			<td><form:password path="newPassword" cssClass="half" /></td>
		</tr>
		<tr>
			<th>确认新密码</th>
			<td><form:password path="confirmPassword" cssClass="half" /></td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td>
			<div class="btnBox">
			   <button type="submit" class="btnBY fl">提交</button>
				</div>

			</td>
		</tr>
	</table>
</form:form></div>
<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ul>
	<li>密码请输入6-20位。</li>
</ul> 
</div>
</div>

</div>
</div>
	<%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script>
document.onkeypress=updateLoginPasswordSubmit;
$(document).ready(function(){
	addCss("updateLoginPassword");
}); 
 </script>
</body>
</html>