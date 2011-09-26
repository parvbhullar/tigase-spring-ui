<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全密码重置</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="style/jquery.js"></script>
<style type="text/css">
<!--
.memInfo {
  display: none
}
-->
</style>
</head>

<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
<%@ include file="/WEB-INF/jspf/errors.jsp" %>
<div class="leftCon">
<h1 class="tit">安全密码重置</h1>
<form:form commandName="command" action="account.do?action=doResettingAccountPassword"
  method="post">
    <form:hidden path="coreAccountInfo.accId"/>
  <form:hidden path="coreAccountInfo.memberId" />
  <table border="0" cellspacing="0" cellpadding="0" class="tabNF reg">
    <tr>
      <th>新密码</th>
      <td><form:password path="newPassword" cssClass="half" />
      </td>
    </tr>
    <tr>
    <th>确认新密码</th><td><form:password path="confirmPassword" cssClass="half" /></td></tr>
    <tr>
      <th>&nbsp;</th>
      <td>
         <button type="submit" class="btnBY fl">确定</button>
      </td>
    </tr>
  </table>
</form:form></div>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script>
$(document).ready(function(){
  addCss("queryWithdrawListByMemberId");
}); 
</script>
</body>
</html>