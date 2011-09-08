<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员登录</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
<div class="leftCon">
<h1 class="tit">会员登录</h1>

<form:form name="myFrm" commandName="command" action="member.do?action=doLogon"
  method="post">
  <table border="0" cellspacing="0" cellpadding="0" class="tabNF reg">
    <col width="30%" />
    <col width="70%" />
    <tr>
      <th>电子邮箱</th>
      <div class="e_tip" id="e_tip"></div>
      <td><form:input path="email" cssClass="half" autocomplete="off" />
      </td>
           </tr>
    <tr>
      <th>密码</th>
      <td><form:password path="password" cssClass="half" /></td>
    </tr>
   <c:choose>
      <c:when test='${logonNum>=5 }'>
        <tr id="validateTr">
      </c:when>
      <c:otherwise>
       <tr style="display: none" id="validateTr">
      </c:otherwise>
     </c:choose>
      <th>验证码</th>
      <td>
      <table border="0" cellspacing="0" cellpadding="0"
        style="width: 260px" class="tabIn">
        <col />
        <col width="120px" />
        <col width="90px" />
        <tr>
          <td><form:input path="verifyCode" size="4"  /></td>
          <td class="c"><img src="/canpanta.png" alt="canpanta" id="validataImg" /></td>
          <td><a href="javascript:void(0)" onclick="getValidataImg()" >看不清，换一张</a></td>
         </tr>
      </table>
      </td>
    </tr>
    <tr>
      <th>&nbsp;</th>
      <td>
      <div class="wrBox" id="logonErrDiv" style="display: none">
          <div class="wr">
         <c:choose>
  <c:when test="${not empty command.errors }">
              <c:forEach items="${command.errors}" var="errorinfo">
               ${errorinfo.value}
              </c:forEach>
              <script> document.getElementById("logonErrDiv").style.display="block"; </script>
  </c:when>
</c:choose>
          </div>
          </div>
      <div class="btnBox">
      <button type="submit" class="btnBY fl">登录</button>
     <div class="moreBtn"><span class="gray">|</span>
       <a href="member.do?action=sendResettingPasswordEmailPage">找回密码</a></div>
       </div></td>
    </tr>

  </table>
</form:form></div>
<div class="rightCon">
<div class="infoCon">
<h2 style="margin-bottom: 0pt;"><a href="member.do?action=register">没有帐号？一步免费注册 »</a></h2>
</div>
</div>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script type="text/javascript" src="/js/mail_completer.js"></script>
<script>
$(document).ready(function(){
$("#email").autoComplete();
	 });
</script>
</body>
</html>