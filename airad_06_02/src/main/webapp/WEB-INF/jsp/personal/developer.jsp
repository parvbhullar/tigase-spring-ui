<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>个人中心</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<form:form action="" method="post" commandName="command">
</form:form>
<!--- 开发嵌入start--->
<c:if test="${advertiserMessage=='ok'}">
<div class="okBox"><div class="ok" style="display:none" id="okTip">认证成功。</div></div>
<script>
//让层5秒后消失
$("#okTip").show();
$("#okTip").animate({opacity: 1.0}, 5000).fadeOut("slow", function() {$(this).hide();});
<%request.getSession().removeAttribute("advertiserMessage");%>
</script>
</c:if>
<!-- 冻结提醒 -->
<c:if test="${form.coreMemberInfo.forzen}">
<div class="wa"><strong>您的角色已被冻结</strong> ，如有疑问请<a href="mailto:contact@airad.com">联系客服</a></div>
</c:if>
<div class="leftCon" style="height:136px;width:578px">
<h1 style="margin-bottom:15px" class="fl">你好，${form.coreMemberInfo.email }
</h1>
<div class="clean"></div>
<div class="fr mt">
<a href="/withdraw.do?action=queryWithdrawListByMemberId">查阅明细</a> <span class="gray">|</span> <a href="/withdraw.do?action=createWithdraw">申请提现</a> <span class="gray">|</span> <a href="javascript:showPopDev();">资金转换</a>
</div>
账户余额: 
<c:if test="${empty form.accountInfoView.incomeBlance}">
<strong class="imp"><sup>&yen;</sup><fmt:formatNumber type="number" value="0" pattern="###,##0.00"/></strong>
</c:if>
<c:if test="${!empty form.accountInfoView.incomeBlance}">
<strong class="imp"><sup>&yen;</sup><fmt:formatNumber type="number" value="${form.accountInfoView.incomeBlance}" pattern="###,##0.00"/></strong>
</c:if>
<small>(提现中金额: <c:if test="${empty form.accountInfoView.acceptCash}">
<sup>&yen;</sup><fmt:formatNumber type="number" value="0" pattern="###,##0.00"/></c:if><c:if test="${!empty form.accountInfoView.acceptCash}"><sup>&yen;</sup><fmt:formatNumber type="number" value="${form.accountInfoView.acceptCash}" pattern="###,##0.00"/></c:if>)
</small>

<div class="clean" style="margin:10px 0;border-top:1px solid #ccc;padding-top:10px">
累积收入: 
<c:if test="${empty form.accountInfoView.income}">
<strong class="imp"><sup>&yen;</sup><fmt:formatNumber type="number" value="0" pattern="###,##0.00"/> </strong>
</c:if>
<c:if test="${!empty form.accountInfoView.income}">
<strong class="imp"><sup>&yen;</sup><fmt:formatNumber type="number" value="${form.accountInfoView.income}" pattern="###,##0.00"/> </strong>
</c:if>
</div>
</div>
<div class="rightCon" style="width:310px">
<div class="infoCon">
<h2>用户信息</h2>
<ul>
<li>上次登录时间: <fmt:formatDate value="${form.userHis.loginTime}" type="both" pattern="yyyy-MM-dd HH:mm" /></li>
<li>上次登录IP: ${form.userHis.loginIp}</li>
<li>会员类型: ${form.coreMemberInfo.memberTypeShowName }</li>
<li class="r"><a href="/member.do?action=updateLoginPassword">修改密码</a> <span class="gray">|</span> <a href="/member.do?action=queryMember">修改会员信息</a></li>
</ul>
</div>
</div>
<div class="clean" style="padding:30px 0 0 0">
<h1 class="tit"><span class="fr" style="margin-top:5px"><a href="/app.do?action=add&currentPage=1">新建应用程序</a> <span class="gray">|</span> <a href="/app.do?action=listApp">查看更多应用程序&raquo;</a></span>应用程序概况 <span>应用相关数据每天8、12、16、20、24 点更新</span></h1>
<table cellspacing="0" cellpadding="0" border="0" class="tabY">
  <col />
  <col width="8%"/>
  <col width="10%"/>
  <col width="8%"/>
  <col width="8%"/>
  <col width="8%"/>
  <col width="8%"/>
  <col width="8%"/>
  <col width="10%"/>
  <col width="8%"/>
  <tbody>
    <tr>
      <th>应用名称</th>
      <th>适用平台</th>
      <th>添加时间 </th>
      <th>收入</th>
      <th>请求数</th>
      <th>展示数</th>
      <th>点击数</th>
      <th>点击率</th>
      <th>广告投放率</th>
      <th>状态</th>
    </tr>
    <c:forEach items="${form.appList}" var="CoreApp" varStatus="s">
      <tr>
        <td><a href="app.do?action=edit&appId=${CoreApp.appId }"><airad:cutString size="20" value="${CoreApp.appName}" mark="..."/></a></td>
        <c:if test="${CoreApp.appPlatformType=='0'}">
        <td>iPhone</td>
        </c:if>
        <c:if test="${CoreApp.appPlatformType=='1'}">
        <td>Android</td>
        </c:if>
        <td><fmt:formatDate  value="${CoreApp.addTime}" type="both" pattern="yyyy-MM-dd" /></td>
        <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${CoreApp.offer}" pattern="###,#0.00"/></td>
        <td><fmt:formatNumber type="number" value="${CoreApp.maxNum}"/></td>
        <td><fmt:formatNumber type="number" value="${CoreApp.showNum}"/></td>
        <td><fmt:formatNumber type="number" value="${CoreApp.clickNum}"/></td>
        <td><fmt:formatNumber type="number" value="${CoreApp.clickRate*100}" pattern="###.##"/>%</td>
        <td><fmt:formatNumber type="number" value="${CoreApp.putRate*100}" pattern="###.##"/>%</td>
      <c:if test="${CoreApp.appStatus==0 and CoreApp.blocking==0}">
           <td><span class="red">停用</span></td>
        </c:if>
        <c:if test="${CoreApp.appStatus==1 and CoreApp.blocking==0}">
           <td><span class="green">运行中</span></td>
        </c:if>
        <c:if test="${CoreApp.blocking==1}">
           <td><span class="red">冻结</span></td>
        </c:if>
      </tr>
      </c:forEach>
      <c:if test="${empty form.appList}">
      <td colspan="10">
        <div class="c">暂无数据</div>
      </td>
      </c:if>
  </tbody>
</table>
<h1 class="tit" style="margin-top:30px"><span class="fr" style="margin-top:5px"><a href="/report.do?action=add&flag=1&currentPage=1">新建报告</a> <span class="gray">|</span> <a href="/report.do?action=listReport">查看更多报告&raquo;</a></span>我的报告</h1>
<table cellspacing="0" cellpadding="0" border="0" class="tabY">
    <col width="40%" />
    <col width="30%"/>
    <col width="20%"/>
    <col width="10%"/>
    <tbody>
      <tr>
        <th>名称</th>
        <th>创建时间</th>
        <th>类型</th>
        <th>报表形式</th>
      </tr>
      <c:forEach items="${form.reportList}" var="report" varStatus="s">
        <tr>
          <td><a href="report.do?action=show&reportId=${report.reportId }"><airad:cutString size="20" value="${report.reportName}"
          mark="..." /></a></td>
          <td><fmt:formatDate value="${report.addTime }" type="both" pattern="yyyy-MM-dd" /></td>
          <c:if test="${report.reportType==0}">
            <td>活动报告</td>
          </c:if>
          <c:if test="${report.reportType==1}">
            <td>广告组报告</td>
          </c:if>
          <c:if test="${report.reportType==2}">
            <td>广告报告</td>
          </c:if>
          <c:if test="${report.reportType==3}">
            <td>应用报告</td>
          </c:if>
          <c:if test="${report.reportType==4}">
            <td>广告商报告</td>
          </c:if> 
          <td>
            <c:choose>
              <c:when test="${report.reportStatus==0}">汇总报告</c:when>
              <c:otherwise>详细报告</c:otherwise>
            </c:choose>
          </td>
        </tr>
      </c:forEach>
     <c:if test="${empty form.reportList}">
     <tr>
      <td colspan="4"><div class="c">暂无数据</div></td>
     </tr>
     </c:if>
    </tbody>
  </table></div>
<!-- 开发嵌入end-->
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script>
jQuery(document).ready(function(){
	  addCss("developlist");
	});
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
</script>
</body>
</html>



