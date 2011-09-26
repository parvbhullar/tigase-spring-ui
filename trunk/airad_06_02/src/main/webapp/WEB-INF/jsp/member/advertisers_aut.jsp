<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告主身份验证</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 开发嵌入start--> 
<div class="leftCon">
<h1 class="tit">广告主身份验证</h1>
<form:form name="myFrm" commandName="command" action="/advertiser.do?action=authenticate" method="post" enctype="multipart/form-data">
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
	<col width="30%" />
	<col width="70%" />
	<tr>
		<th><span class="must">*</span>身份证号码</th>
		<td><input size="28" id="cardId" name="coreAdvertiser.cardId" onblur="validateCardIdValue(this)" class="half" /><br /><small>最后一位如果是字母，必须大写</small>
		<div id="cardIdDiv1" class="wrBox" style="display: none">
          <div id="cardIdDiv"  class="wr">
          </div>
          </div></td>
	</tr>
	<tr>
		<th>身份证复印件</th>
		<td><input type="file" id="photo" name="photo" style="width:140px;"/>
		<div id="photoDiv1" class="wrBox" style="display: none">
          <div id="photoDiv"  class="wr">
          </div>
          </div></td>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<td>
        <div class="btnBox">
        <button type="button" onclick="advertisersAutSubmti()" class="btnBY">提交</button>
		<span class="gray">|</span> <a href="personal.do?action=advlist">返回</a>
        </div>
        </td>
	</tr>
</table>
</form:form>
</div>
<!-- 开发嵌入end--> <script type="text/javascript">
  function setFormAction(prm) {
    document.myfrm.action = prm;
    document.myfrm.submit();
  }
</script></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script>
$(document).ready(function(){
	  addCss("authenticatePage");
}); 
</script>
</body>
</html>
