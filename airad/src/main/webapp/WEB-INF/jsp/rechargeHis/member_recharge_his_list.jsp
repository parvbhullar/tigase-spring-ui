<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>充值历史记录查看</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="style/jquery.js"></script>
<style type="text/css">
<!--
.white_content {
  display: none;
  position: absolute;
  top: 165px;
  left: 50%;
  margin-left: -250px;
  background-color: #FAE6C7;
  z-index: 1002;
  overflow: auto;
}

.black_overlay {
  display: none;
  position: absolute;
  top: 0%;
  left: 0%;
  width: 100%;
  height: 100%;
  z-index: 1001;
  -moz-opacity: 0.8;
  opacity: .80;
  filter: alpha(opacity =   0);
}
-->
</style>

</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>

<div id="main">
<div class="mainCon">
<%@ include file="/WEB-INF/jspf/errors.jsp" %>
<h1 class="tit">账务账本 </h1>
<div class="leftConTra">
<div class="info">
<h1 class="fl">账户余额：<strong class="orange"><sup>&yen;</sup><fmt:formatNumber
  type="number" value="${moneylittle}" pattern="###,##0.00" /></strong></h1>
<div class="fTip">请确认您的账户中有足够的金额，当余额不足时，广告将停止发布。</div>
<div class="ctl clean"><a href="javascript:void(0)" onclick="showPopDev()";
class="btnS" id="" ><span>立即充值</span></a></div>
</div>
<h1 class="tit" style="margin-top: 20px">账户充值记录</h1>

<form:form action="rechargeHis.do?action=list"
  commandName="command" method="post" name="hisName">
  <form:hidden path="status"/>
  <form:hidden path="currentPage"/>
  <form:hidden path="desc"/>
  <div class="tag">
  <a href="javascript:recHisRecord('1');" class="<c:if test="${f.status eq '1'}">now</c:if>"
            id="recHisRecord1">充值成功记录</a>
  <a href="javascript:recHisRecord('0');"  class="<c:if test="${f.status eq '0'}">now</c:if>"
            id="recHisRecord0">等待支付记录</a>
  <a href="javascript:recHisRecord('2');" class="<c:if test="${f.status eq '2'}">now</c:if>"
            id="recHisRecord2">已取消支付记录</a>
     </div>       
  <table cellspacing="0" cellpadding="0" border="0" class="tabY">
    <col /><col width="20%" /><col width="20%" /><col width="20%" />
      <tr>
        <th>会员充值流水号</th>
        <th>充值金额</th>
        <c:if test="${''!=f.desc}">
          <th><a href="javascript:recHisDesc('');"
            id="descId">充值时间<img alt="降序" src="/images/ico_sortza.gif"></img></a>
          </th>
        </c:if>
        <c:if test="${''==f.desc}">
          <th><a href="javascript:recHisDesc('2');"
            id="asceId">充值时间<img alt="升序" src="/images/ico_sortaz.gif"></img></a>
          </th>
        </c:if>
        <th>充值类型</th>
        <c:choose>
              <c:when test="${f.status eq '1'}">
                <th>备注<th>
              </c:when>
              <c:when  test="${f.status eq '0'}">
              <th>操作<th>
              </c:when>
            </c:choose>
      </tr>
      <c:if test="${not empty f.hisList}">
        <c:forEach items="${f.hisList}" var="list">
          <tr>
            <td>${list.serialNum}<input type="hidden" value="${list.serialNum}" id="${list.serialNum}"/></td>
            <td><sup>&yen;</sup>${list.money}<input type="hidden" value="${list.money}" id="${list.id}"/></td>
            <td><fmt:formatDate value="${list.rechargeTime}" type="both"
              pattern="yyyy-MM-dd HH:mm" /></td>
            <c:choose>
              <c:when test="${list.rechargeType eq '0'}">
                <td>银联充值</td>
              </c:when>
              <c:when  test="${list.rechargeType eq '1'}">
              <td>代理商代充值</td>
              </c:when>
              <c:when  test="${list.rechargeType eq '2'}">
              <td>收益金转换</td>
              </c:when>
              <c:otherwise>
                <td>平台充值 </td>
              </c:otherwise>
            </c:choose>
            <c:choose>
              <c:when test="${f.status eq '1'}">
                <td>${list.remark}</td>
              </c:when>
              <c:when  test="${f.status eq '0'}">
              <td><a href="/rechargeHis.do?action=completepay&serialNum=${list.serialNum}">支付<span class="gray">&nbsp;|&nbsp;</span></a><a href="/rechargeHis.do?action=cancel&serialNum=${list.serialNum}">取消</a></td>
              </c:when>
            </c:choose>
          </tr>
        </c:forEach>
      </c:if>
      <c:if test="${empty f.hisList}">
        <tr>
          <td colspan="6" align="center">暂无数据</td>
        </tr>
      </c:if>
  </table>
 <div class="r">
<a href="/rechargeHis.do?action=exportCSV&status=${f.status}">导出 CVS</a> <span class="gray">|</span> <a href="/rechargeHis.do?action=exportXML&status=${f.status}">导出 XML</a></div>
  <airad:pagination pageSize="${f.pageSize}"
    href="/rechargeHis.do?action=list&currentPage=PAGENUM&desc=${f.desc}&status=${f.status}"
    totalRecord="${f.totalCount}" currentPage="${f.currentPage}">
  </airad:pagination>
</form:form></div>
<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ol>
  <li>您可以通过&ldquo;<strong>立即充值</strong>&rdquo;增加您的账户金额。</li>
  <li>当账户金额不足时，广告将停止发布，直到您的账户有足够的金额。</li>
  <li>银行转账每天最多<sup>&yen;</sup>200,000，充值不限制次数。</li>
</ol>
</div>
</div>
</div>
</div>
<div class="popDiv collapsed" id="popDiv"
  style="top: 300px; left: 300px; width: 400px">
</div>
<div class="popBg collapsed"></div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript"
  src="js/jquery.jmpopups-0.5.1.js"></script>
<script type="text/javascript">
  function showPopDev() {
       $.openPopupLayer( {
      name : 'popDiv',
      url : 'recharge_win.jsp',
      cache : false,
      width : '500'

    });
  }
  function editPopDev(numId,moneyId) {
	  var money = $("#"+moneyId).val();
      $.openPopupLayer( {
     name : 'popDiv',
     url : 'recharge_edit.jsp?editMoney='+money+'&serNum='+numId,
     cache : false,
     width : '500'

   });
 }
  function closepop() {
    $.closePopupLayer('popDiv');
  }
  onload=function(){
      addCss("queryMemberRechargeHis");
    }
</script>

</body>
</html>