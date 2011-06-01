<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
session.invalidate();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/login_2.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="wrap">
<div id="header"></div>
<!--登录 begin-->
<form method="post" action="login" id="loginform"><input type="hidden" value="YXV0b2xvZ2luI2ludmlzaWJsZQ==" name="__PROCESSINPUTVIEWSTATE">
<div class="denglu">
<div class="denglubox">
  <ul>
<li>用 户：<input type="text" class="dl_font"value="请输入您的用户名" onfocus="if(value=='请输入您的用户名') {value=''}" onblur="if (value=='') {value='请输入您的用户名'}"/></li>
<li>密 码：</span><input type="password"  class="dl_font" value="请输入您的密码" onfocus="if(value=='请输入您的密码') {value=''}" onblur="if (value=='') {value='请输入您的密码'}"/> <a href="#">忘记密码？</a></li>
<li style="text-align:center; padding:5px;"><input name="" type="checkbox" value="" /> 下次自动登录</li></ul>

<div class="denglubox1">
<ul>
<li style=" padding:10px 30px;"><a href="#" title="登录"><img src="images/btn_login01.gif" alt="登录" /></a></li>
<li style=" padding:10px 30px;"><a href="#" title="注册"><img src="images/btn_login02.gif" alt="注册" /></a></li>
</ul></div>
</div>
</div>
</form>
</div>

<!--登录 end-->

<!-- footer begin-->
<div id="footer">

<h2><span class="marginright20" title="统一通信平台">统一通信平台</span><span title="客服电话400 799 7279">客服电话：<span class="red bold">400 799 7279</span></span></h2>
<h3>京ICP备08105201号</h3>
<h4>
<a href="http://js.cyberpolice.cn/webpage/index.jsp" target="_blank"><img src="images/cyberpolice1.gif" style="padding-right:10px;" /></a>
<a href="http://js.cyberpolice.cn/webpage/index.jsp" target="_blank"><img src="images/cyberpolice2.gif" style="padding-right:10px;"  /></a>
</h4>
</div>
<!-- footer end-->
</body>
</html>
