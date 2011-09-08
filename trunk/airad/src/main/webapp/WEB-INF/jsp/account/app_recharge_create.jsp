<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>应用资金转换</title>
  </head>
  <body>
  <%@ include file="/WEB-INF/jspf/header.jsp" %>
  <div id="main">
    <div class="mainCon">
      <%@ include file="/WEB-INF/jspf/errors.jsp" %>
<!-- 程序开始 -->    

<div class="leftCon">
<h1>资金转换（可转换金额：${accountInfoView.incomeBlance}）</h1>
<form:form action="account.do?action=doRecharge" method="post" commandName="command" name="myForm">
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <col width="20%" />
        <col width="80%" />
        <tr>
          <th>资金转换</th>
          <form:hidden path="memberId"/>
          <td>
            <form:input path="money"/>
            <div id="recharge"></div>
          </td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox">
              <button type="submit" class="btnBY fl">转换</button>
           <div class="moreBtn"><span class="gray">|</span> <a href="withdraw.do?action=queryWithdrawListByMemberId">取消</a></div>
           </div>
          </td>
        </tr>
      </table>
 </form:form>
</div>
<div class="rightCon">
<div class="infoCon">
<h2><img src="images/ico_act.gif" alt="活动" width="16" height="16" align="absmiddle" />应用摘要</h2>
<ul>
<li>资金转换</li>
</ul>
</div>
</div>
         
    </div>
  </div>
  <%@ include file="/WEB-INF/jspf/footer.jsp" %>
<script>
$(document).ready(function(){
  addCss("listAppIncome");
}); 
</script>
  </body>
</html>