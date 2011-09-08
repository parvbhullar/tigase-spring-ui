<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.web.auth.roles.*"%>
<%@page import="com.mitian.airad.*"%>
<%@page import="com.mitian.airad.common.auth.*"%>
<%@page import="com.mitian.airad.common.auth.context.SecurityContext"%>
<%@page import="com.mitian.airad.common.auth.context.SecurityContextHolder"%>
<%@page import="com.mitian.airad.web.form.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员信息</title>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery.js"></script>
</head>
<body>
<%
String loginEmail="";
SecurityContext context=SecurityContextHolder.getContext();
if(context!=null){
    loginEmail=context.getMemberInfo().getEmail();
  }
%>
<div id="header" style="height:35px;"><div class="headCon">
  <div class="memInfo">
      Hi, <strong><%=loginEmail%></strong> <span>|</span>  
      <a href="member.do?action=logout">[退出]</a>
    </div>
<div class="logo"><a href="index.html"><img height="33" width="124" alt="airAD" src="images/logo.gif"/></a></div></div></div>
<div id="main">
<div class="mainCon">
<h1 class="tit">您的角色</h1>
<div id="con01" style="margin-right:17px;width:600px" class="halfCon">
<div class="fr"><img height="220" width="220" title="我是广告主" src="/images/intro_owner.jpg"/></div>
<h1 class="orange">我是广告主</h1>
<ul>
<li>自由订制投放时间地点的高维度营销。</li>
<li>针对目标群体的精准投放。</li>
<li>丰富的模板式制作与广告托管服务，真正做到 0 技术起点。</li>
<li>海量数据分析报告与市场趋势分析。</li>
<li><strong>在您发布广告之前，您需要进行身份认证。</strong></li>
</ul>
<div class="btnBox">
<a class="btnY fl" href="advertiser.do?action=authenticatePage"><span>现在进行身份认证，成为广告主</span></a><div class="moreBtn"><span class="gray">|</span> <a href="/personal.do?action=advlist">稍后认证，进入会员中心</a></div>
</div>
</div>
<div id="con02" class="halfCon" style="width:210px;height:220px">
<h1 class="orange">我是开发者</h1>
<ul>
<li>智慧 SDK 支持各种个性化设置。</li>
<li>HTML5 富媒体广告。</li>
<li>国内最高 CPC 让 eCPM 倍增。</li>
<li>详尽的数据报表与分析报告。</li>
</ul>
<div class="btnBox">
<a class="btnY fl" href="member.do?action=changeDevMember"><span>成为开发者</span></a>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script>
$(document).ready(function(){
	 $("#con01").mouseover(function(){
	 $("#con01").addClass("conNow");
	 });
	 $("#con01").mouseout(function(){
	$("#con01").removeClass("conNow");
	 });
	 $("#con02").mouseover(function(){
	 $("#con02").addClass("conNow");
	 });
	 $("#con02").mouseout(function(){
	 $("#con02").removeClass("conNow");
	 });
});
</script>
</body>
</html>