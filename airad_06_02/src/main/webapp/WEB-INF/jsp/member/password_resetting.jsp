<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>密码重置</title>
</head>

<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
<%@ include file="/WEB-INF/jspf/errors.jsp" %>
<div class="leftCon">
<h1 class="tit">密码重置</h1>
<form:form commandName="command" action="member.do?action=resettingPassword"
  method="post" name="myFrm">
  <table border="0" cellspacing="0" cellpadding="0" class="tabNF reg">
    <col width="30%" />
    <col width="70%" />
    <tr>
      <th>新密码</th>
      <td><form:password path="password" cssClass="half" onblur="validatePasswordValue()" />
          <div id="passwordDiv1" class="wrBox" style="display: none">
          <div id="passwordDiv"  class="wr">
          </div>
          </div>
          <input type="hidden" name="email" class="half" value='<%=request.getParameter("email") %>' />
          <input type="hidden" name="date" class="half" value='<%=request.getParameter("date") %>' />
      </td>
    </tr>
    <tr>
    <th>确认新密码</th><td><form:password path="passwords" cssClass="half" onblur="validatePasswordsValue()" />
    <div id="passwordsDiv1"  class="wrBox" style="display: none">
          <div id="passwordsDiv"  class="wr">
          </div>
          </div></td></tr>
    <tr>
    <th></th>
      <td>
      <div class="btnBox">
       <a class="btnY fl" onclick="registerPwdPageSubmti(this)"><span>确定</span></a>
       </div>
      </td>
    </tr>
  </table>
</form:form></div>
<!--  
<div class="rightCon">
<div class="infoCon">

<h2 style="margin-bottom: 0"><a
  href="member.do?action=register">没有帐号？一步免费注册 &raquo;</a></h2>
<h2>提示</h2>

<ul>
  <li>广告主需要提供营业执照号码来验证账户的真实性。</li>
  <li>请您提供真实有效的信息，这样更有助于广告的发布和推广。</li>
</ul>
</div>
</div>
-->
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>