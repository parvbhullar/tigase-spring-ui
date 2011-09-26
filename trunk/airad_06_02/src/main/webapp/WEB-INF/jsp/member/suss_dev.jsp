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

<div id="main">
<div class="mainCon"><!-- 开发嵌入start-->
<div class="suc long" id="styleDiv"><form method="post" action="" name="jumpfrm" id="command"></form><div><h2>提交成功</h2>
	<form method="post" action="member.do?action=registerActivation" name="myFrm" id="command">
	</form>
	<br />
</div>
</div>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>