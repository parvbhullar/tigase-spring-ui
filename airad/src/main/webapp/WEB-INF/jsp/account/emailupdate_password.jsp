<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.web.form.AccountForm"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>安全密码重置</title>
<link   type="text/css" href="/style/main.css" rel="stylesheet"/>

</head>

<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
<%@ include file="/WEB-INF/jspf/errors_detail.jsp"%>
<%@ include file="/WEB-INF/jspf/success.jsp"%>
<div class="leftCon">
<h1>安全密码重置</h1>
 <form:form name="updateAccountPassword" id="updateAccountPassword" method="post"
	action="/account.do?action=doEmailUpdateAccountPassword" commandName="command">
	<form:hidden path="coreAccountInfo.accId"/>
	<form:hidden path="memberId"/>
	<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
		<col width="30%" />
		<col width="70%" /><tr>
			<th>新密码</th>
			<td><form:password path="newPassword" id="newPassword" cssClass="half" /></td>
		</tr>
		<tr>
			<th>确认新密码</th>
			<td><form:password path="confirmPassword" id="confirmPassword" cssClass="half" /></td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td >
			<div class="btnBox" >
			      <button type="submit" class="btnBY fl">提交</button></div>
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
<!-- <div id="footer">&copy;2011 米田科技有限公司 版权所有 备案证书号：<a
  href="javascript:void(0)">苏ICP备12345678号</a></div> -->
  <%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script>
$(document).ready(function(){
	addCss("queryWithdrawListByMemberId");
});
 </script>
 <script>
   function clearp(){
	  
	   document.getElementById("oldPassword").value="";
	   document.getElementById("newPassword").value="";
	   document.getElementById("confirmPassword").value="";
	   document.getElementById("oldPassword").focus();
	   
	   }
 </script>
</body>
</html>