<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>充值成功</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
   function  tiaozhuan(){
       document.formSubmit.submit();
   }
</script>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="sCon">
<h1 class="tit">账户充值</h1>
<form:form name="formSubmit" id="formSubmit" commandName="command" method="post" action="rechargeHis.do?action=list">
<div class="info">
向您的帐户充值：<strong class="imp">${f.money}</strong>
</div>
</form:form>
<div class="suc">
<div>
<h2>支付成功</h2>
<p>您已经成功充值 <strong class="orange">${f.money}</strong>。</p>
<p>您可以在"<a href="#" onclick="tiaozhuan();">账务账本 </a>"栏目中查看充值记录。</p>
</div>
</div>
<div class="buttonL r" style="padding-top:0">
<a href="#" onclick="tiaozhuan();" >返回账务账本  &raquo;</a></div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>