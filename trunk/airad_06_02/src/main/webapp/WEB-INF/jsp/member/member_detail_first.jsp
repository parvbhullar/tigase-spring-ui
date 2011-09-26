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
<%@ include file="/WEB-INF/jspf/errors_detail.jsp" %>
<div class="leftCon">
<h1 class="tit">完善个人信息 <small>建议您完善个人资料，以便我们更好的为您提供服务，以及提高账号的安全性。</small></h1>
<form:form name="memberDetail" id="memberDetail" method="post"
	action="/member.do?action=updateMemberInfo" commandName="command">
	<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
		<col width="30%" />
		<col width="70%" />
		<tr>
			<th>手机号码</th>
			<td><form:input path="coreMemberInfo.mobile" cssClass="short" maxlength="11" /></td>
		</tr>
		<tr>
			<th>通信地址</th>
			<td><form:input path="coreMemberInfo.address" cssClass="half" maxlength="50" /></td>
		</tr>
		<tr>
			<th>邮政编码</th>
			<td><form:input path="coreMemberInfo.zip" cssClass="half" maxlength="6" /></td>
		</tr>
		<tr>
			<th>QQ</th>
			<td><form:input path="coreMemberInfo.qq" cssClass="half"	maxlength="20" /></td>
		</tr>
		<tr>
			<th>MSN</th>
			<td><form:input path="coreMemberInfo.msn" cssClass="half" 	maxlength="50" /></td>
		</tr>
		<tr>
			<th>固定电话</th>
			<td><form:input path="coreMemberInfo.phone" cssClass="half" maxlength="20" /></td>
		</tr>
		<tr>
			<th>公司名称</th>
			<td><form:input path="coreMemberInfo.companyName"	cssClass="half" maxlength="50" /></td>
		</tr>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
		<col width="30%" />
		<col width="70%" />
		<tr>
			<th>&nbsp;</th>
			<td>
			<div class="btnBox">
			<button class="btnBY" onclick="submitform();" type="button">提交</button> <span class="gray">|</span> <a href="/member.do?action=changeMember">跳过此步骤</a>
			</div>
			</td>
		</tr>
	</table>
</form:form></div>
<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ol>
	<li>建议您完善个人资料，以便我们更好的为您提供服务，以及提高账号的安全性。</li>
	<li>您也可以跳过此页，以后完善。</li>
</ol>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script>
	function submitform() {
		document.memberDetail.submit();
	}
</script>
</body>
</html>