<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="com.mitian.airad.common.utils.StringUtils"%><html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
<title>提示信息</title>
<script>
	function editDivStyle() {
		document.getElementById("styleDiv").className = "stop long";
	}

	function jumpPage(url) {
		document.jumpfrm.action = url;
		document.jumpfrm.submit();
	}
</script>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%
String email="";
try{
    SecurityContext contextSecurityContext = SecurityContextHolder.getContext();
    email=contextSecurityContext.getMemberInfo().getEmail();
}catch(Exception e){
}
%>
<div id="main">
<div class="mainCon"><!-- 开发嵌入start-->
<div id="styleDiv" class="suc long"><form:form name="jumpfrm"
	commandName="command" method="post" action=""></form:form>
<div><c:if test="${command.memberMessage=='inviteCodeNullity'}">
	<h2>注册失败</h2>
	<ul>
		<c:forEach items="${command.errors}" var="errorinfo">
			<li>${errorinfo.value}</li>
		</c:forEach>
		<li>从这里<a href="javascript:jumpPage('member.do?action=logon')">进入登录页。</a></li>
	</ul>
	<script>
        editDivStyle();
      </script>
</c:if>
 <c:if test="${command.memberMessage=='passwordResult'}">
		<c:if test="${fn:length(command.errors)<1 }">
		<h2>密码重置成功</h2>
      <ul>
			<li><a href="javascript:jumpPage('member.do?action=logon')"><strong>返回登录</strong></a>。</li>
			</ul>
		</c:if>
		<c:forEach items="${command.errors}" var="errorinfo">
		<h2>密码重置失败</h2>
			<script>
        editDivStyle();
      </script>
      <ul>
			<li>${errorinfo.value}</li>
			<form:form name="myFrm"
				action="member.do?action=sendResettingPasswordEmail"
				commandName="command">
				<form:hidden path="email" />
			</form:form>
			<li><a href="javascript:jumpPage('member.do?action=logon')">返回登录</a></li>
			<li>或者<a href="javascript:if(clckimg(this)){document.myFrm.submit();}">再次发送密码重置邮件</a>，点击邮件中的链接，完成密码重置。</li>
		</ul>
		</c:forEach>
</c:if> 
<c:if test="${command.memberMessage=='logonRegister'}">
	<h2>登录失败</h2>
	<script>
	editDivStyle();
</script>
	<form:form name="myFrm" action="member.do?action=registerActivation"
		commandName="command">
		<%if(StringUtils.isBlank(email)){%>
		       <form:hidden path="email" />
		    <%}else{ %>
		    <input type="hidden" name="email" value="<%=email %>" />
		    <%} %>
	</form:form>
	<ul>
		<c:forEach items="${command.errors}" var="errorinfo">
			<li>${errorinfo.value}</li>
		</c:forEach>
	</ul>
	<ul>
		<li>您的账号未激活，请到您的邮箱 <strong>${login.email }</strong> 收取激活邮件，点击激活链接激活账号</li>
		<li><a href="javascript:jumpPage('member.do?action=logout')">退出登录</a>。
		若没有收到激活邮件再次<a href="javascript:document.myFrm.submit();">发送激活邮件</a>
		</li>
	</ul>
</c:if> 
 <c:if test="${command.memberMessage=='sendEmail'}">
	<c:if test="${fn:length(command.errors)<1 }">
		<h2>邮件发送成功</h2>
		<ul>
			<li><a href="javascript:jumpPage('member.do?action=logon')">现在登录</a></li>
		</ul>
	</c:if>
	<c:forEach items="${command.errors}" var="errorinfo">
		<h2>邮件发送失败</h2>
		<script>
	editDivStyle();
</script>
		<ul>
			<li>${errorinfo.value}</li>
			<c:choose>
				<c:when test="${errorinfo.value=='每天至多发送3封密码重置邮件' }">
					<li><a href='javascript:jumpPage("/member.do?action=logon")'>返回登录页面</a>。</li>
				</c:when>
				<c:otherwise>
					<li><a href='javascript:jumpPage("/member.do?action=sendResettingPasswordEmailPage")'>返回发送页面</a>。</li>
				</c:otherwise>
			</c:choose>

		</ul>
	</c:forEach>
</c:if> <c:if test="${command.memberMessage=='merberRegister'}">
	<h2>注册成功</h2>
	<form:form name="myFrm" action="member.do?action=registerActivation"
		commandName="command">
		<%if(StringUtils.isBlank(email)){%>
		       <form:hidden path="email" />
		    <%}else{ %>
		    <input type="hidden" name="email" value="<%=email %>" />
		    <%} %>
	</form:form>
	<ul>
		<li>请<strong>登录您的电子邮箱收取验证邮件</strong>，点击邮件中的链接，完成邮箱验证。</li>
		<li><strong>邮件有可能被误判为垃圾邮件，请留意。</strong>为避免这种情况 ，您可以把发件人添加为您的联系人。</li>
		<li>若没有收到激活邮件，点击<a href="javascript:if(clckimg(this)){document.myFrm.submit();}">再次发送</a>。</li>
	</ul>
</c:if> <c:if test="${command.memberMessage=='regActiveResult'}">
	<c:if test="${fn:length(command.errors)<1 }">
		<h2>激活成功</h2>
		<ul>
			<li><a href="javascript:jumpPage('member.do?action=logon')">立刻登录</a></li>
		</ul>
	</c:if>
	<c:forEach items="${command.errors}" var="errorinfo">
		<h2>激活失败</h2>
		<script>
	editDivStyle();
</script>
		<ul>
			<li>${errorinfo.value}</li>
		</ul>
	</c:forEach>
	<c:if test="${not empty command.errors }">
		<ul>
			<form:form name="myFrm" action="member.do?action=registerActivation"
				commandName="command">
				<form:hidden path="email" />
			</form:form>
			<li><a href="/">返回主页</a></li>
		</ul>
	</c:if>
</c:if> <c:if test="${command.memberMessage=='errorResult'}">
	<script>
	editDivStyle();
</script>
		<c:forEach items="${command.errors}" var="errorinfo">
			<h2>${errorinfo.value}</h2>
		</c:forEach>
		<ul>
		<li><a href="/">返回主页</a></li>
	</ul>
</c:if></div>
</div>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>