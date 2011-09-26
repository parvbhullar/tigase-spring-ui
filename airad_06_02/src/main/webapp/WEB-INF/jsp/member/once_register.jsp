<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>注册</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<div class="leftCon">
<h1 class="tit">快速用户注册 <small> 只需一步就可以成为我们的会员</small></h1>
<dir id="pageErrDiv"></dir>
<form:form name="myFrm" commandName="command"  id="regForm"
  action="member.do?action=doOnceRegister" method="post" >
  <input type="hidden" name="loginType" value="1"></input>
  <form:hidden path="uid"/>
  <table border="0" cellspacing="0" cellpadding="0" class="tabNF reg">
    <col width="30%" />
    <col width="70%" />
    <tr>
      <th>电子邮箱</th>
      <div class="e_tip" id="e_tip"></div>
       <form:hidden path="inviteCode"/>
      <td><c:out value="${command.email}"></c:out><form:hidden path="email" cssClass="half" onblur="validateEmailValue()" autocomplete="off" />
      <div id="emailDiv1" class="wrBox" style="display: none">
          <div id="emailDiv"  class="wr">
          </div>
          </div>
      </td>
    </tr>
    <tr>
      <th>设定密码</th>

      <td><form:password path="password" cssClass="half"
        onblur="validatePasswordValue()" />
      <div id="passwordDiv1" class="wrBox" style="display: none">
          <div id="passwordDiv"  class="wr">
          </div>
          </div>
      </td>
    </tr>
    <tr>
      <th>确认密码</th>
      <td><form:password path="passwords" cssClass="half"
        onblur="validatePasswordsValue()" />
      <div id="passwordsDiv1"  class="wrBox" style="display: none">
          <div id="passwordsDiv"  class="wr">
          </div>
          </div>
      </td>
    </tr>
    <tr>
      <th>&nbsp;</th>
      <td>
      <div class="btnBox">
          <button type="submit"  class="btnBY" id="_regSub">同意以下协议，提交注册信息</button>
      </div>
      <div style="padding-left:10px;">阅读 airAD <a href="/policy.html" target="_blank">隐私协议</a> 和 <a href="/terms.html" target="_blank">服务条款</a></div>
      </td>
    </tr>
  </table>
</form:form></div>
<div class="rightCon">
<div class="infoCon">
<h2 style="margin-bottom: 0pt;"><a href="member.do?action=logon">已经有帐号？登录 »</a></h2>
</div>
</div>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script type="text/javascript" src="/js/mail_completer.js"></script>
<script>
$(document).ready(function(){
$("#email").autoComplete();
//注册页面提交
  $('#regForm').submit(function(){
     var errNum = 0;
    errNum = errNum + validateEmailValue();
    errNum = errNum + validateVerifyCodeValue();
    errNum = errNum + validatePasswordsValue();
    errNum = errNum + validatePasswordValue();
    var validateEmail = document.getElementById("emailDiv").innerHTML;
      if (errNum == 0 && validateEmail == "") {
      $('#_regSub').attr('disabled','disabled').text('正在提交注册信息....');
       obj.disabled="disabled";
       obj.value="正在提交注册信息....";
       return true;
    }else{
      return false;
    }
  });
});
</script>
</body>
</html>