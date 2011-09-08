<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>个人中心</title>
<script>
function toBeDev()
{
	if(true==window.confirm("您确认要成为开发者吗?"))
	{
		jQuery.post("member.do?action=toBeDev",null,function(d){
			   var data=d;
			   window.location="/personal.do?action=index";
			});
	}
}
</script>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<c:if test="${advertiserMessage=='ok'}">
<div class="okBox"><div class="ok" style="display:none" id="okTip">认证成功。</div></div>
<script>
//让层5秒后消失
$("#okTip").show();
$("#okTip").animate({opacity: 1.0}, 5000).fadeOut("slow", function() {$(this).hide();});
<%request.getSession().removeAttribute("advertiserMessage");%>
</script>
</c:if>
<div class="info" id="infoClose" style="cursor:pointer"><a href="javascript:void(0)" class="fr"><img src="/images/ico_infoc.gif" width="14" height="14" alt="展开" /></a><strong>我是广告主，我该怎样定制自己的广告？</strong><small>广告制作分三个步骤：1，建立广告活动；2，在广告活动下建立广告组；3，在广告组下定制个性广告。 </small>
</div>
<div class="info" style="display:none" id="infoOpen"><a href="javascript:void(0)" class="fr" id="infoBtn"><img src="/images/ico_infoo.gif" width="14" height="14" alt="展开" /></a><strong>我是广告主，我该怎样定制自己的广告？</strong>
<p>广告制作分三个步骤：1，建立广告活动；2，在广告活动下建立广告组；3，在广告组下定制个性广告。 </p>
<p>在广告活动中建立您想要投放的周期，名称，预算等概况；在广告组中建立您想投放的地区或者人群；最后在广告组下定制您的个性广告。如下图所示：</p>
<img src="/images/pic_step.gif" width="890" height="300" class="mt" />
<p class="r"><a href="/campaign.do?action=add"><strong>现在就去新建广告&raquo;</strong></a></p>
</div>
<!-- 冻结提醒 -->
<c:if test="${form.coreMemberInfo.forzen}">
<div class="wa"><strong>您的角色已被冻结</strong> ，如有疑问请<a href="mailto:contact@airad.com">联系客服</a></div>
</c:if>
<!--- 开发嵌入start--->
<form:form action="" method="post" commandName="command">
</form:form>
<div class="leftCon" style="height:136px;width:578px">
<h1 style="margin-bottom:15px"  class="fl">你好，${form.coreMemberInfo.email }</h1>
<!-- 身份认证提醒 -->
 <c:if test="${form.coreMemberInfo.role.general ||form.coreMemberInfo.role.dev}">
    <div class="fTip">您的广告在 <a href="/advertiser.do?action=authenticatePage"><strong>身份认证</strong></a> 后才能发布。</div>
 </c:if>
<div class="clean"></div>
<div class="fr mt">
<a href="/rechargeHis.do?action=list">查询明细</a> <span class="gray">|</span> 
<a href="javascript:void(0)" onclick="showPopDev();" >在线充值</a></div>
账户余额: 
<c:if test="${empty moneylittle}">
<strong class="imp"><sup>&yen;</sup><fmt:formatNumber type="number" value="0" pattern="###,##0.00"/></strong>
</c:if>
<c:if test="${!empty moneylittle}">
<strong class="imp"><sup>&yen;</sup><fmt:formatNumber type="number" value="${moneylittle}" pattern="###,##0.00"/></strong>
</c:if>
<div class="clean" style="margin:10px 0;border-top:1px solid #ccc;padding-top:10px">
<a href="/campaign.do?action=add" class="btnS fr" style="margin-top:5px"><span>新建广告</span></a>
广告展示总次数: <strong class="imp">${adShowCount }</strong>
</div>
</div>
<div class="rightCon" style="width:310px">
<div class="infoCon">
<c:if test="${form.coreMemberInfo.role.general ||form.coreMemberInfo.role.advertisers}">
<a href="javascript:toBeDev()" class="fr" style="margin:10px 3px 0 0;color:#999"><img src="/images/ico_cl.gif" alt=" "/>您是开发者？</a>
</c:if>
<h2>用户信息</h2>
<ul>
<li>上次登录时间: <fmt:formatDate value="${form.userHis.loginTime}" type="both" pattern="yyyy-MM-dd HH:mm" /></li>
<li>上次登录IP: ${form.userHis.loginIp}</li>
<li>会员类型: ${form.coreMemberInfo.memberTypeShowName}</li>
<li class="r"><a href="/member.do?action=updateLoginPassword">修改密码</a> <span class="gray">|</span> <a href="/member.do?action=queryMember">修改会员信息</a></li>
</ul>
</div>
</div>
<div class="clean" style="padding:30px 0 0 0">
<h1 class="tit"> <span class="fr" style="margin-top:5px"><a href="/campaign.do?action=listCampaign">查看更多广告活动&raquo;</a></span>广告活动概括 <span>广告相关数据每天4、10、14、18、22、24 点更新</span></h1>
<table cellspacing="0" cellpadding="0" border="0" class="tabY">
    <col width="20%"/>
    <col width="15%"/>
    <col width="10%"/>
    <col width="2%"/>
    <col width="12%"/>
    <col width="12%"/>
    <col width="10%"/>
    <col width="10%"/>

    <tbody>
      <tr>
        <th>活动名称</th>
        <th>开始时间</th>
        <th colspan="2">预算</th>
<!--        <th>平均每次点击费用</th>-->
        <th>展示次数</th>
        <th>点击数</th>
        <th>点击率</th>
        <th>成本</th>
        <th>广告数量</th>
      </tr>
      <c:forEach items="${form.campaignList}" var="corecampaign" varStatus="s">
        <tr>
          <td><airad:cutString size="20" value="${corecampaign.campaignName }" mark="..."/></td>
          <td><fmt:formatDate value="${corecampaign.startTime}"type="both" pattern="yyyy-MM-dd HH:mm" /></td>
          <td colspan="2"><sup>&yen;</sup><fmt:formatNumber type="number" value="${corecampaign.buggetDay}"/></td>
<!--          <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${corecampaign.avgAdOffer}" pattern="###,#0.00"/></td>-->
          <td><fmt:formatNumber type="number" value="${corecampaign.showNum}"/></td>
          <td><fmt:formatNumber type="number" value="${corecampaign.clickNum}"/></td>
          <td><fmt:formatNumber type="number" value="${corecampaign.clickRate*100}" pattern="###.##"/>%</td>
          <td><fmt:formatNumber type="number" value="${corecampaign.sumAdOffer}" pattern="###,#0.00"/></td>
          <td align="center">${corecampaign.count}</td>
        </tr>
      </c:forEach>
      <c:if test="${empty form.campaignList }">
        <tr><td colspan="9"><div class="c">暂无数据</div></td></tr>
      </c:if>
    </tbody>
  </table>
<h1 class="tit" style="margin-top:30px"><span class="fr" style="margin-top:5px"><a href="/report.do?action=add&flag=0&currentPage=1">新建报告</a> <span class="gray">|</span> <a href="/report.do?action=listReport">查看更多报告&raquo;</a></span>我的报告</h1>
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
          <td><airad:cutString size="20" value="${report.reportName}" mark="..."/></td>
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
  </table>
</div>
<!-- 开发嵌入end-->
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script>
jQuery(document).ready(function(){
	addCss("advlist");
});
function showPopDev() {
    $.openPopupLayer( {
   name : 'popDiv',
   url : 'recharge_win.jsp',
   cache : false,
   width : '500'
 });
}
function closepop() {
 $.closePopupLayer('popDiv');
}
$("#infoClose").click(function(){
									$("#infoOpen").show();
									$("#infoClose").hide();
									})
$("#infoBtn").click(function(){
									$("#infoOpen").hide();
									$("#infoClose").show();
									})
</script>
</body>
</html>



