  <%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
<title>找回密码</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
<!-- 开发嵌入start-->
<div id="styleDiv" class="leftCon">
  <h1 class="tit">找回密码</h1>
  <form:form name="smyfrm" commandName="command" method="post"
    action="member.do?action=sendResettingPasswordEmail">
    <table border="0" cellspacing="0" cellpadding="0" class="tabNF reg">
      <tr>
        <th>电子邮箱</th>
        <td><form:input path="email" cssClass="half" onblur="validatePasswordRetrieve(this)" /><br /><small>请输入您注册的邮箱</small> 
         <div id="resettingErrDiv" style="display: none;">
        <c:forEach items="${command.errors}" var="errorinfo">
          <div class="wrBox">
          <div class="wr" id="errMagessDiv">
          ${errorinfo.value}
          </div>
          </div>
          <script>
          document.getElementById("resettingErrDiv").style.display="";
          </script>
        </c:forEach>
       </div>
        </td>
      </tr>
      <tr>
        <th></th>
        <td>
        <div class="btnBox">
        <button class="btnBY" type="submit" >发送邮件</button> <span class="gray">|</span> <a href="/member.do?action=logon">返回登录</a></div></td>
      </tr>
    </table>
  </form:form>
</div>
<!-- 开发嵌入end-->
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>