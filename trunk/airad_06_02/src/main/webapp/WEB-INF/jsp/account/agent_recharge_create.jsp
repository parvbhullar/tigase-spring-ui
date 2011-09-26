<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>代理商资金转换</title>
  </head>
  <body>
  <%@ include file="/WEB-INF/jspf/header.jsp" %>
  <div id="main">
    <div class="mainCon">
      <%@ include file="/WEB-INF/jspf/errors.jsp" %>
<!-- 程序开始 -->    

<div class="leftCon">
<h1>资金转换（可转换金额：${accountInfoView.incomeBlance}）
<c:if test="${form.coreMemberInfo.role.agents}">
<a href="account.do?action=advRecharge">代理商给广告商充值</a>
</c:if>
</h1>
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
           <a href="javascript:document.myForm.submit();" class="btnY fl"><span>转换</span></a><div class="moreBtn"><span class="gray">|</span> <a href="withdraw.do?action=queryWithdrawListByMemberId">取消</a></div>
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