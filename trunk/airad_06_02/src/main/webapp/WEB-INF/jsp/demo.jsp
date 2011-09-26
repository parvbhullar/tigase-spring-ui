<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>demo</title>
    <%@ include file="/WEB-INF/jspf/js_and_css.jsp"%>
  </head>
  <body>
  <%@ include file="/WEB-INF/jspf/header.jsp" %>
  <div id="main">
    <div class="mainCon">
      <%@ include file="/WEB-INF/jspf/errors.jsp" %>
      <!-- 开发嵌入start-->
<div class="wa">只有在您提供会员信息并通过审核之后，您的广告才可以被发布。</div>
<h1 class="tit">会员信息 <span class="orange">[未通过审核]</span></h1>
<div class="leftCon">
<h1>个人信息</h1>
<form:form action="/demo.do?action=doAdd" commandName="command" method="post">
	<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
	<col width="30%"><col width="70%">
	  <tbody>
	  <tr>
	    <th><span class="must">*</span>姓名</th>
	    <td><form:input path="name" cssClass="half" /></td>
	  </tr>
	  <tr>
	    <th><span class="must">*</span>性别</th>
	    <td>
				<form:select path="sex">
				<form:option value="0" label="男"/>
				<form:option value="1" label="女"/>
				</form:select>	    
	    </td>
	  </tr>
	  <tr>
	    <th>email</th>
	    <td><form:input path="email" cssClass="half" /></td>
	    </tr>
	  <tr>
	    <th><span class="must">*</span>年龄</th>
	    <td><form:input path="age" cssClass="half" /></td>
	    </tr>
	  <tr>
	    <th>生日</th>
	    <td><form:input path="birthDay" cssClass="half" /></td>
	    </tr>
	  <tr>
	    <th><span class="must">*</span>下属姓名</th>
	    <td><form:input id="userName" path="user.name" cssClass="half" /></td>
	  </tr>
	  <tr>
	    <th><span class="must">*</span>下属email</th>
	    <td><form:input path="user.email" cssClass="half" /></td>
	  </tr>      
	</tbody>
	</table>
</form:form>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
<col width="30%"><col width="70%">
  <tbody><tr>
    <th>&nbsp;</th>
    <td><div class="btnBox"><a class="btnY fl" href="javascript:document.forms[0].submit()"><span>提交</span></a>
    <div class="moreBtn"><span class="gray">|</span>
            <a href="#">取消</a>
            </div></div></td>
  </tr>
</tbody></table>
</div>
<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ol>
<li>广告主需要提供营业执照号码来验证账户的真实性。</li>
<li>请您提供真实有效的信息，这样更有助于广告的发布和推广。</li>
<li>如果您需要修改登录邮箱，从这里进入 <a href="javascript:void(0)">修改登录邮箱 »</a></li>
</ol>
</div>
</div>
      <!-- 开发嵌入end-->      
         
    </div>
  </div>
  <%@ include file="/WEB-INF/jspf/footer.jsp" %>
  </body>
</html>
