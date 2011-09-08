<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>制作广告</title>
<%@ include file="/WEB-INF/jsp/page/header_meta.jsp"%>
<script src="helpvido/standard.js" type="text/javascript"></script>
<style type="text/css">
<!--
*{outline:none;margin:0; padding:0}
.main{width:1003px;margin:2px auto;zoom:1;overflow:hidden}
img{border:0}
-->
</style>
</head>
<body style="background:#000">
<center>
<div class="main">
<div style="text-align:right">
<a href="help.html"><img src="images/btn_close.gif" height="21" width="21"></a>
</div>
<div id="CaptivateContent">
</div>
</div>
<script type="text/javascript">
   var so = new SWFObject("helpvido/makeAd.swf", "Captivate", "1004", "580", "8", "#CCCCCC");
	so.addParam("quality", "high");
	so.addParam("name", "Captivate");
	so.addParam("id", "Captivate");
	so.addParam("wmode", "window");
	so.addParam("bgcolor","#F5F4F1");
	so.addParam("menu", "false");
	so.addVariable("variable1", "value1");
	so.setAttribute("redirectUrl", "http://www.adobe.com/shockwave/download/download.cgi?P1_Prod_Version=ShockwaveFlash");
	so.write("CaptivateContent");
</script>
<script type="text/javascript">
    document.getElementById('Captivate').focus();
    document.Captivate.focus();
</script>
</center>
</body>
</html>