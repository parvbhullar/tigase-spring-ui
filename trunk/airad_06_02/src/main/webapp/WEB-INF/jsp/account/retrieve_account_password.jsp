<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.web.form.AccountForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>财务密码找回</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="style/jquery.js"></script>


</head>
<%
AccountForm accountForm = (AccountForm)request.getAttribute("command");
%>
<body>

<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
<%@ include file="/WEB-INF/jspf/errors.jsp" %>
<%@ include file="/WEB-INF/jspf/success.jsp" %>
<div class="leftCon">
<h1>安全密码找回</h1>
<form:form commandName="command"  action="account.do?action=doRetrieveAccountPassword"
  method="post" name="myform" onsubmit="return false;"> 
 <form:hidden path="mail"/>
<table class="tabNF" cellpadding="0" cellspacing="0" border="0">
<col width="30%">
<col width="70%">
<tr>
<th>您的邮箱地址:</th>
<td><strong>${mail}</strong></td>
</tr>
<tr>
<td>&nbsp;</td>
<td>
<div class="btnBox">
 <button onclick="javascript:document.myform.submit();" class="btnBY"  >发送</button>
<span class="gray">|</span> <a href="account.do?action=updateMemberAccountInfo">取消</a>
</div>
</td>
</tr>
</table>
</form:form>

<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</div>
<script>
$(document).ready(function(){
	 addCss("queryWithdrawListByMemberId");//传action的值
	});
 </script>
</body>
</html>