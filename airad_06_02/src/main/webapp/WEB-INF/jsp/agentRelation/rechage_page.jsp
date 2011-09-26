<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告商列表</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%> 
<!-- 开发嵌入start-->
 <h1>充值记录</h1>
<form:form action="agentRelation.do?action=addRechange" commandName="command"  method="post">
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
    <col width="25%" />
    <col width="75%" />
    <tr>
        <th>充值金额：</th>
        <td><form:input path="coreRechargeHis.money" cssClass="long" />
            <form:hidden path="coreRechargeHis.memberId"  />
            <form:hidden path="coreRechargeHis.agentAdderId"  /> 
        </td>
    </tr> 
  <tr>
    <td align="center" colspan="2">
    <input type="submit" value="确定" style="width:80px;height: 27px;"/>&nbsp&nbsp&nbsp&nbsp<input type="reset" style="width:80px;height: 27px;" value="取消" />
    </td>
  </tr>
</table>
</form:form>
<!-- 开发嵌入end-->
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>
