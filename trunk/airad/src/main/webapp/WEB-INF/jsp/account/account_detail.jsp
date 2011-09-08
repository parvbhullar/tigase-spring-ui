<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.web.form.AccountForm"%>
<%@page import="com.mitian.airad.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>提款设置</title>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/errors_detail.jsp" %>
<%@ include file="/WEB-INF/jspf/success.jsp" %>
<div id="main">
<div class="mainCon">
<div class="leftCon">
<h1>
<c:if test="${not empty coreAccountInfo.accId }"><span class="fr"><a href="#" onclick="searchPassword();">找回安全密码 </a><span class="gray">|</span> <a href="#" onclick="editPassword();">修改安全密码</a></span></c:if>
提款设置
</h1>
<form:form method="post" action="/account.do?action=doUpdateMemberAccountInfo" commandName="command" name="accountInfo">
  <form:hidden path="coreAccountInfo.accId"/>
  <form:hidden path="coreAccountInfo.memberId" />
  <table border="0" cellspacing="0" cellpadding="0" class="tabNF">
    <col width="30%" />
    <col width="70%" />
    <tr>
      <th><span class="must">*</span>真实姓名</th>
      <td><form:input path="coreAccountInfo.realName" cssClass="half" maxlength="30"/></td>
    </tr>
    <tr>
     <th><span class="must">*</span>身份证</th>
      <td><form:input path="coreAccountInfo.idCard" cssClass="half"   maxlength="19"/></td>
    </tr>
    <tr>
      <th><span class="must">*</span>开户行</th>
      <td>
      <form:input path="coreAccountInfo.openingBank" cssClass="half"   maxlength="80"/><br />
      <small>精确到支行，如：南京建设银行中山支行</small></td>
    </tr>
    <tr>
      <th><span class="must">*</span>开户行账号</th>
      <td><form:input path="coreAccountInfo.unionpayId" cssClass="half"  maxlength="30"/></td>
    </tr>
    <tr>
      <th><span class="must">*</span>账户类型</th>
      <td><form:select path="coreAccountInfo.accType">
      <%
      for (Constants.AccountType rhs : Constants.AccountType.values()) {
          %>
          <form:option value="<%=rhs.getTypeNumber() %>"><%=rhs.getTypeName() %></form:option>
      <% } %>
      </form:select>
      </td>
    </tr>
    <tr>
      <th><span class="must">*</span>安全密码</th>
      <td><form:password path="coreAccountInfo.password" maxlength="20"/>
      <br />
      <small>请输入安全密码以完成修改设置；安全密码初始值是您的会员登录密码。</small>
      </td>
    </tr>

  </table>
  <table border="0" cellpadding="0" cellspacing="0" class="tabNF">
    <col width="30%" />
    <col width="70%" />
    <tr>
      <th>&nbsp;</th>
      <td>
      <div class="btnBox">
           <button type="submit" class="btnBY fl" onclick="">提交</button>
            <div class="moreBtn"><span class="gray">|</span>
            <a href="withdraw.do?action=queryWithdrawListByMemberId">取消</a>
            </div></div>
      </td>
    </tr>
  </table>
</form:form></div>

<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ol>
  <li>请务必正确填写您的提款信息。</li>
  <li>安全密码初始值是您的会员登录密码。</li>

</ol>

</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>

<script>
$(document).ready(function(){
  addCss("queryWithdrawListByMemberId");
});
</script>
</body>
</html>