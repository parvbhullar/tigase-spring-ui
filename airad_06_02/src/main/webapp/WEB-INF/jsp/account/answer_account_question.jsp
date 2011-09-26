<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.web.form.MemberForm"%>
<%@page import="com.mitian.airad.web.form.AccountForm"%>
<%@page import="com.mitian.airad.model.CoreAccountInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>安全密码问题</title>
<link   type="text/css" href="/style/main.css" rel="stylesheet"/>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
<div class="wa">只有在您提供会员信息并通过审核之后，您的广告才可以被发布。</div>
<h1 class="tit">财务账户信息</h1>
<div class="leftCon">
<h1>安全密码问题</h1>
<%@ include file="/WEB-INF/jspf/errors.jsp"%>
<% AccountForm accountForm =(AccountForm)request.getAttribute("command");
      %>
 <form:form name="answerAccountQuestion" id="answerAccountQuestion" method="post"
  action="/account.do?action=doAnswerAccountQuestion" commandName="command">
  <form:hidden path="coreAccountInfo.accId"/>
  <form:hidden path="coreAccountInfo.memberId"/>
   <form:hidden path="resettingId"/>
  <table border="0" cellspacing="0" cellpadding="0" class="tabNF">
    <col width="30%" />
    <col width="70%" />    
    <tr>
      <th>密码提示问题：</th>
      <td>
      <%=accountForm.getSecurityProblem() %></td>
    </tr>
    <tr>
      <th>密码提示答案：</th>
      <td><form:input path="securityAnswer" cssClass="half" /></td>
    </tr>
    <tr>
      <th>&nbsp;</th>
      <td>
      <div class="btnBox">
        <button type="submit" class="btnBY fl">提交</button>
         <div class="moreBtn">
         <span class="gray">|</span>
        <a href="javascript:void(0)"
        onclick="window.location.href='member.do?action=queryMember'"><span>取消</span></a>
        </div>
        </div>
      </td>
    </tr>
  </table>
</form:form></div>
<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ul>
  <li>广告主需要提供营业执照号码来验证账户的真实性。</li>
  <li>请您提供真实有效的信息，这样更有助于广告的发布和推广。</li>
  <li>如果您需要修改登录邮箱，从这里进入 <a href="javascript:void(0)">修改登录邮箱
  &raquo;</a></li>
</ul>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp" %>
</body>
</html>