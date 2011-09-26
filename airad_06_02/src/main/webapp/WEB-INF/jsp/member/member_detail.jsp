<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.mitian.airad.web.form.MemberForm"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.mitian.airad.CommonDef.userRoleCon"%><html
	xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>会员信息</title>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
  <%@ include file="/WEB-INF/jspf/errors_detail.jsp" %>
<%@ include file="/WEB-INF/jspf/success.jsp"%>
<!--
<div class="wa">只有在您提供会员信息并通过审核之后，您的广告才可以被发布。</div> -->
<h1 class="tit">会员信息 </h1>
<div class="leftCon">
<h1>账号基本信息</h1>
<form:form name="memberDetail" id="memberDetail" method="post"
	action="/member.do?action=doUpdateMemberInfo" commandName="command">
	<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
		<col width="30%" />
		<col width="70%" />
		<tr>
			<th><span class="must">*</span>注册邮箱</th>
			<td>
			${command.coreMemberInfo.email}
		  </td>
		</tr>
		<tr>
			<th><span class="must"></span>手机号码</th>
			<td><form:input path="coreMemberInfo.mobile" cssClass="short" maxlength="11" />
			</td>
		</tr>
		<tr>
			<th><span class="must"></span>通信地址</th>
			<td><form:input path="coreMemberInfo.address" cssClass="half" maxlength="50"/></td>
		</tr>
		<tr>
			<th><span class="must"></span>邮政编码</th>
			<td><form:input path="coreMemberInfo.zip" cssClass="short" maxlength="6"/></td>
		</tr>
		<tr>
			<th>QQ</th>
			<td><form:input path="coreMemberInfo.qq" cssClass="short" maxlength="20"/></td>
		</tr>
		<tr>
			<th><span class="must"></span>MSN</th>
			<td><form:input path="coreMemberInfo.msn" cssClass="short" maxlength="50"/></td>
		</tr>
		<tr>
			<th>固定电话</th>
			<td><form:input path="coreMemberInfo.phone" cssClass="short" maxlength="20"/></td>
		</tr>
		<tr>
			<th>公司名称</th>
			<td><form:input path="coreMemberInfo.companyName" cssClass="half" maxlength="50"/></td>
		</tr>
		<tr>
			<th>会员类型</th>
			<td>${command.coreMemberInfo.memberTypeShowName}</td>
		</tr>
		<tr>
			<th>注册时间</th>
			<td>
			 <fmt:formatDate value="${command.coreMemberInfo.registerTime}" type="both"
				pattern="yyyy-MM-dd HH:mm" /></td>
		</tr>
		<tr>
			<th>上次登录时间</th>
			<td>
			<fmt:formatDate value="${command.userHis.loginTime}" type="both" pattern="yyyy-MM-dd HH:mm" />
			</td>
		</tr>
		<tr>
			<th>上次登录IP</th>
			<td>${command.userHis.loginIp}</td>
		</tr>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
		<col width="30%" />
		<col width="70%" />
		<tr>
			<th>&nbsp;</th>
			<td>
			<div class="btnBox">
			   <button type="submit" class="btnBY fl">提交</button>
		  </div>
			</td>
		</tr>
	</table>
</form:form></div>
<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ul>
<li>请您尽快完善个人资料，以便我们更好的为您提供服务，以及提高您账号的安全性。</li>
</ul>
</div>
</div>
</div>
</div>
<!-- 开发嵌入end-->
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script>
$(document).ready(function(){
  addCss("queryMember");
});
</script>
</body>
</html>