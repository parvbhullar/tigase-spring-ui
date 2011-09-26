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

  
</script>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div id="main">
<div class="mainCon"><!-- 开发嵌入start-->
<div id="styleDiv" class="suc long">
<div>
  <h2>安全密码修改成功</h2>
   <c:if test="${ null!=tag}">
   <form  name="myform" method="post" action="http://www.airad.com/member.do?action=logon">
   <ul>
   <li><a href="javaScript:document.myform.submit();">登录Airad平台</a></li>
   </ul>
   </form> 
   </c:if>
   <c:if test="${null==tag}">
    <form  name="myform" method="post" action="member.do?action=logon">
    <ul>
   <li><a href="javaScript:document.myform.submit();">登录Airad平台</a></li>
   </ul>
   </form>
   </c:if>
  
  
 </div>
</div>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>