<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>申请提现</title>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp" %>
  <div id="main">
    <div class="mainCon">
    <%@ include file="/WEB-INF/jspf/errors_detail.jsp" %>
     <%@ include file="/WEB-INF/jspf/success.jsp" %>
<!-- 程序开始 -->
<div class="leftCon">
<h1>我要提款<span class="fr"><a href="account.do?action=updateMemberAccountInfo">提款设置</a></span></h1>
<form:form name="addWithdraw" id="addWithdraw" method="post"
  action="/withdraw.do?action=doCreateWithdraw" commandName="command">
<form:hidden path="coreAccountInfo.realName"/>
<form:hidden path="coreAccountInfo.openingBank"/>
<form:hidden path="coreAccountInfo.unionpayId"/>
<form:hidden path="coreAccountInfo.memberId"/>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
  <col width="30%" />
  <col width="70%" />
  <tr>
    <th><span class="must">*</span>提现金额</th>
    <td><form:input path="drawMoney" cssClass="short"  maxlength="10"/><small>提现金额为整数，最低 <sup>&yen;</sup>100.00</small>
     <div id="wMoney1" class="wrBox" style="display: none">
          <div id="wMoney"  class="wr">
          </div>
          </div>
    </td>
  </tr>
  <tr>
    <th>提现备注</th>
    <td><form:input path="coreWithdraw.drawRemark" cssClass="half"  maxlength="80"/></td>
  </tr>
   <tr>
    <th><span class="must">*</span>银行名称</th>
    <td><c:out value="${form.coreAccountInfo.openingBank}"></c:out></td>
  </tr>
   <tr>
    <th><span class="must">*</span>银行帐号</th>
    <td>${form.coreAccountInfo.unionpayId}</td>
  </tr>
   <tr>
    <th><span class="must">*</span>账户户名</th>
    <td>${form.coreAccountInfo.realName}</td>
  </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
  <col width="30%" />
  <col width="70%" />
  <tr>
    <th>&nbsp;</th>
    <td>
    <div class="btnBox"><a href="#"
      class="btnY fl" onclick="withdrawmoney();"  ><span>提交</span></a><div class="moreBtn"><span class="gray">|</span>
            <a href="/withdraw.do?action=queryWithdrawListByMemberId">取消</a>
            </div></div>
    </td>
  </tr>
</table>
</form:form>
</div>

<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ul>
  <li>提现金额最低<sup>&yen;</sup>100.00</li>   
</ul>

</div>
</div>
</div>
</div>
 <%@ include file="/WEB-INF/jspf/footer.jsp" %>
 <script >

       function  withdrawmoney(){
         
        var drawMoney = document.getElementById('drawMoney').value;
        var te =/^[1-9]{1}[0-9]{2,11}[.]{0,1}[0]{0,2}$/;
        var objExp=new RegExp(te);
        if(objExp.test(drawMoney)){
          document.getElementById('addWithdraw').submit();
        }else{
        
          document.getElementById("wMoney1").style.display = "block";
          document.getElementById("wMoney").innerHTML = "请根据提示信息,输入正确的金额";
          document.getElementById('drawMoney').focus();
          return ;
        }

       }
 </script>
<script>
$(document).ready(function(){
	addCss("queryWithdrawListByMemberId");
	if(!document.getElementById('_errordrawMoney')){
		 jQuery('#drawMoney').after('<br/>');
	}
});
 


</script>
</body>
