<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.web.form.WithdrawForm"%>
<%@page import="com.mitian.airad.model.CoreWithdraw"%>
<%@page import="java.util.*"%>
<%@page import="com.mitian.airad.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html  xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>账务账本</title>
</head>
<body style="width:100%;">
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
<%@ include file="/WEB-INF/jspf/errors.jsp" %>
<%@ include file="/WEB-INF/jspf/success.jsp" %>
<h1 class="tit">账务账本 </h1>
<div class="leftConTra">
<div class="info">
<h1 class="fl">账户余额：
<c:if test="${empty form.accountInfoView.incomeBlance}">
<strong class="orange"><sup>&yen;</sup><fmt:formatNumber type="number" value="${form.accountInfoView.incomeBlance}" pattern="###,##0.00"/> </strong>
</c:if>
<c:if test="${!empty form.accountInfoView.incomeBlance}">
<strong class="orange"><sup>&yen;</sup><fmt:formatNumber type="number" value="${form.accountInfoView.incomeBlance}" pattern="###,##0.00"/> </strong>
</c:if></h1>

<div class="fTip">金额超过&yen;100才可以提现。</div>
<div class="clean mb" style="padding:5px 0">广告主账户余额：<c:if test="${empty form.accountInfoView.acceptBlance}">
<strong class="orange"><sup>&yen;</sup><fmt:formatNumber type="number" value="0" pattern="###,##0.00"/> </strong></h1>
</c:if>
<c:if test="${!empty form.accountInfoView.acceptBlance}">
<strong class="orange"><sup>&yen;</sup><fmt:formatNumber type="number" value="${form.accountInfoView.acceptBlance}" pattern="###,##0.00"/></strong></h1>
</c:if> <small>若您同时是广告主，可以通过&ldquo;资金转换&rdquo;把金额直接转到广告主余额账户。</small></div>

<div class="ctl clean"><a href="withdraw.do?action=createWithdraw" class="btnS"><span>我要提款</span></a>
<a class="btnS" onclick="showPopDev();" href="javascript:void(0)"><span>资金转换</span></a>
<span class="gray">|</span><a href="account.do?action=updateMemberAccountInfo"><span>提款设置</span></a>
  </div>
</div>
<h1 class="tit" style="margin-top: 20px">账户交易记录</h1>
<form:form action=""
  commandName="command" method="post" name="hisName">
  <form:hidden path="status"/>
  <form:hidden path="desc"/>
<div class="tag">
<a href="javascript:recDrowRecord();" class="<c:if test="${'null'==form.status}">now</c:if>" >提现记录</a>
<a href="javascript:recDrawRecords('6');" class="<c:if test="${'null'!=form.status}">now</c:if>">活动收益</a>
</div>
<table border="0" cellspacing="0" cellpadding="0" class="tabY">
  <col width="20%" />
  <col width="15%"/>
  <col width="15%"/>
  <col width="15%"/>
  <tbody>
<c:if test="${'null'!=form.status}">
<%@ include file="/WEB-INF/jsp/withdraw/withdraw_listPlatform.jsp" %>
</c:if>
<c:if test="${'null'==form.status}">
  <tr>
    <th>会员提现流水号ID</th>
    <th>提现额度</th>
    <th>受理状态</th>
    <c:if test="${null!=flag}">
        <th><a href="#" onclick="reqTimeDesc('${withdrawForm.currentPage}');"
          id="descId">申请时间<img alt="降序" src="/images/ico_sortza.gif"></img></a>
        </th>
    </c:if>
    <c:if test="${null==flag}">
        <th><a href="#" onclick="reqTimeAsce('${withdrawForm.currentPage}');"
          id="asceId">申请时间<img alt="升序" src="/images/ico_sortaz.gif"></img></a>
        </th>
    </c:if>
  </tr>
  <c:if  test="${!empty form.coreWithdrawList}">
    <c:forEach items="${form.coreWithdrawList}" var="CoreWithdraw">
  <tr>
    <td>${CoreWithdraw.drawMoneySerialId}</td>
    <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${CoreWithdraw.drawMoney}" pattern="###,##0.00"/></td>
    <td>
      <c:if test="${CoreWithdraw.status==0}">
                    受理中
      </c:if>
      <c:if test="${CoreWithdraw.status==1}">
                    已支付
      </c:if>
      <c:if test="${CoreWithdraw.status==2}">
                    支付失败
      </c:if>
    </td>
    <td><fmt:formatDate
      value="${CoreWithdraw.drawTime}" type="both"
      pattern="yyyy-MM-dd HH:mm" /></td>
  </tr>
      </c:forEach>
      </c:if>
      <c:if  test="${empty form.coreWithdrawList}">
      <tr>
          <td colspan="4" align="center">暂无数据</td>
      </tr>
  </c:if>
  </c:if> 
  </tbody>
</table>
<div class="r">
<c:if test="${'null'!=form.status}">
<a href="/rechargeHis.do?action=exportXML&status=${form.status}"><span>导出xml</span></a>
<span class="gray">|</span>
<a href="/rechargeHis.do?action=exportCSV&status=${form.status}"><span>导出csv</span></a>
</c:if>
<c:if test="${'null'==form.status}">
<a href="/withdraw.do?action=exportXML"><span>导出xml</span></a>
<span class="gray">|</span>
<a href="/withdraw.do?action=exportCSV"><span> 导出csv</span></a>
</c:if>

</div>
</form:form>
<c:if test="${'null'!=form.status}">
<airad:pagination pageSize="${form.pageSize}"
  href="/withdraw.do?action=listRecord&currentPage=PAGENUM&desc=${form.desc}&status=${form.status}"
  totalRecord="${form.totalCount}"
  currentPage="${form.currentPage}"></airad:pagination>
</c:if>
<c:if test="${'null'==form.status}">
<airad:pagination pageSize="${withdrawForm.pageSize}"
  href="/withdraw.do?action=queryWithdrawListByMemberId&currentPage=PAGENUM"
  totalRecord="${withdrawForm.totalCount}"
  currentPage="${withdrawForm.currentPage}"></airad:pagination>
</c:if>
</div>
<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ol>
  <li>金额超过&yen;100才可以提现。</li>
  <li>资金转换最低为&yen;50。</li>
  <li>如果您同时是广告主，可以通过<strong>&ldquo;资金转换&rdquo;</strong>将金额直接转到广告主余额账户。</li>
</ol>
</div>
</div>
</div>
</div>
<div class="popBg collapsed" style="display: none;"></div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%></body>
<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script type="text/javascript">
  document.onkeypress = searchSubmit;
  function showPopDev() {
       $.openPopupLayer( {
      name : 'popDiv',
      url : 'withdraw.do?action=queryAgentRelation',
      cache : false,
      width : '500'
    });
  }
  function closepop() {
    $.closePopupLayer('popDiv');
  }
  onload=function(){
      addCss("queryWithdrawListByMemberId");
    }
</script>
<script type="text/javascript">
$(document).ready(function(){
  addCss("queryWithdrawListByMemberId");
  $("#btn01").click(function(){
        $("#popDiv").fadeIn();
        $(".popBg").show();
        });
$("#closePop").click(function(){
        $(".popBg").hide();
        $("#popDiv").hide();
        });
});
function recDrawRecords(str){
	$("#status").val(str);
document.hisName.action = "withdraw.do?action=listRecord";
    document.hisName.submit();
}
function recDrowRecord(){
	  $("#status").val('null');
	document.hisName.action = "withdraw.do?action=queryWithdrawListByMemberId";
	    document.hisName.submit();
	}
function recDrawRecordDesc(flag)
{
    var desc=document.getElementById('descId');
    $("#desc").val(flag);
    document.hisName.action = "withdraw.do?action=listRecord";
    document.hisName.submit();
}
</script>
</html>