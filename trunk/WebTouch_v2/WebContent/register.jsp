<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0"
	prefix="i18n"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% 
String ctxindex = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>
	<script type="text/javascript" src="<%=ctxindex%>/js/jqueryui/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="register.js"></script>
	<link href="css/index/register.css" rel="stylesheet" type="text/css" />
</head>

<body >
<div id="wrap2">
<div class="main_760">
<!--创建活动内容 begin-->
<div class="tex_c"><img src="images/welcome.gif" /></div>
<div class="register">
<div class="JHtittle" style="MARGIN-TOP: 0px; PADDING-TOP: 0px"><span class="floatright nomarlh3">如果您已注册，请点此 <a href="login.jsp" class="red">登录</a></span>机构注册</div>
<form id="organizationForm" name="organizationForm" method="post">
<div class="JHcontent">
<div id="name">
<div class="txt">名称：</div>
<div class="redstar">*</div>
</div>
<div id="inputtb">
	<input type="text" class="text" id="orgname" name="orgname" maxlength="20" /><div class="message" id="nameMessage" style="color:red"></div>
</div>
</div>

<div class="JHcontent">
<div id="name">
<div class="txt">机构登录名：</div>
<div class="redstar">*</div>
</div>
<div id="inputtb">
	<input type="text" class="text" id="orglogname" name="orglogname" maxlength="20" /><div class="message" id="lognameMessage" style="color:red"></div>
</div>
<div id="inputtb">机构登录名字符只允许输入英文和数字！</div>
</div>

<div class="JHcontent">
<div id="name">
<div class="txt">密 码 ：</div>
<div class="redstar">*</div>
</div>
<div id="inputtb">
	<input type="password" class="text" id="orgpassword" name="orgpassword" maxlength="20" /><div class="message" id="passwordMessage" style="color:red"></div>
</div>
<div id="inputtb">密码只允许输入英文和数字！</div>
</div>

<div class="JHcontent">
<div id="name">
<div class="txt">密码确认：</div>
<div class="redstar">*</div>
</div>
<div id="inputtb">
	<input type="password" class="text" id="resurePassword" name="resurePassword" maxlength="20" /><div class="message" id="resurePasswordMessage" style="color:red"></div>
</div>
</div>

<div class="JHcontent">
<div id="name">
<div class="txt">邮件：</div>
<div class="redstar">*</div>
</div>
<div id="inputtb">
	<input type="text" class="text" id="email" name="email" /><div class="message" id="emailMessage" style="color:red"></div>
</div>
</div>


<div class="xieyi">
<input type="checkbox" id="agree" name="agree" value="1"   />
已阅读并同意《<a target="blank" href="#"  >webtouch用户协议</a>》</div>
<div class="reg_btn">
<input type="button" value="同意服务条款，提交申请信息" name="registerOrgBtn" id="registerOrgBtn" />
</div>

<div class="clear"></div>

</form>
</div>
</div>
<!--创建活动内容 end-->
<!-- footer begin-->
<div id="footer">
<h2><span class="marginright20" title="webtouch版权所有 2010-2013 本站所有内容，未经许可，均不得转载">webtouch版权所有 2010-2013 本站所有内容，未经许可，均不得转载</span><span title="客服电话400 799 7279">客服电话：<span class="org bold">123 123 123</span></span></h2>
<h3>苏ICP备09096821号</h3>
</div>
<!-- footer end-->
</div>
</body>
</html>
