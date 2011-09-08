<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>个人中心</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div style="display:none" id="notice">
<div class="notice">
<h2><img src="images/ico_popclose.gif" alt="关闭" id="closeNotice" class="fr">提示</h2>
<p style="text-indent:2em;padding:5px">为保证一致的广告展示效果，请使用<a href="http://firefox.com.cn/" target="_blank">火狐浏览器</a>或者<a href="http://www.google.com/chrome/" target="_blank" >Chrome浏览器</a>进行广告预览 </p>
</div>
</div>
<div class="showPage" id="Box" style="display:none;">
<h2><img src="images/ico_popclose.gif" alt="关闭" id="closePop" class="fr">预览</h2>
<iframe width="320px" height="54px" frameborder="0" style="float:left;margin:450px 0 0 5px"  id="banner_preview"></iframe>
<iframe width="320px" height="500px" frameborder="0"  style="float:right;margin:5px" id="ad_preview"></iframe>
</div>
<div class="popBg" id="Bg" style="display:none; height:700px"></div>



<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 冻结提醒 -->
<c:if test="${form.coreMemberInfo.forzen}">
<div class="wa"><strong>您的角色已被冻结</strong> ，如有疑问请<a href="mailto:contact@airad.com">联系客服</a></div>
</c:if>
<!--- 开发嵌入start--->

<div class="leftCon" style="height:66px;width:578px">
<h1 style="margin-bottom:15px"  class="fl">你好，${loginEmail}</h1>
<div class="gray fl" style="padding-left:10px">上次登录时间: <fmt:formatDate value="${command.userHis.loginTime}" type="both" pattern="yyyy-MM-dd HH:mm" />  上次登录IP: ${command.userHis.loginIp}</div>
<div class="clean">
广告数量 
<strong class="imp">${command.totalCount}</strong>
<div style="display:inline; padding-left:15px">广告累计展示次数: 
<strong class="imp">${command.adShowCount}</strong>
</div>
</div>
</div>

<div class="clean">
<div style="padding:15px 0 0 0" class="clean">
<h2 class="tit">广告概况</h2>
<div class="searchBox">
<form:form action="/personal.do" method="get" commandName="command">
<table cellspacing="0" cellpadding="0" border="0">
    <tr>
      <td class="search">广告名称 <form:input path="sw"/> </td>
        <td><span><input type="submit"  value="查询" /></span></td>
     </tr>
     <input type="hidden"  name="action" value="index" />
</table>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="tabY">
  <col width="11%">
  <col width="8%">
  <col width="8%">
  <col width="7%">
  <col width="10%">
  <col width="12%">
  <col width="7%">
  <col width="7%">
  <col width="6%">
    <tr>
      <th>广告名称</th>
      <th>开始时间</th>
      <th>结束时间 </th>
      <th>投放平台</th>
      <th>总展示数</th>
      <th>单点击数</th>
      <th>点击率</th>
      <th>状态</th>
      <th>查看广告预览</th>
    </tr>
    <c:choose>
    <c:when test="${not empty command.adList }">
    <c:forEach var="ad" items="${command.adList}">
    <tr>
      <td><airad:cutString size="6" value="${ad.adName}" mark="..."/></td>
      <td><fmt:formatDate value="${ad.startTime}" type="both" pattern="yyyy-MM-dd HH:mm" /></td>
      <td><fmt:formatDate value="${ad.endTime}"type="both" pattern="yyyy-MM-dd HH:mm" /></td>
      <td><c:out value="${ad.platformShowName}" /> </td>
      <td><fmt:formatNumber type="number" value="${ad.showNum}"/></td>
      <td><fmt:formatNumber type="number" value="${ad.clickNum}"/></td>
      <td><fmt:formatNumber type="number" value="${ad.clickRate*100}" pattern="###.##"/>%</td>
      <td>${ad.statusShowName }</td>
      <td><a  class="ad_preview"  id="${el:decode(ad.adId) }" href="#">预览</a></td>
    </tr>
    </c:forEach>
    </c:when>
    <c:otherwise>
   <tr><td colspan="9"><div class="c">暂无数据</div></td></tr>
    </c:otherwise>
    </c:choose>
</table>
<airad:pagination pageSize="10" 
href="/personal.do?action=index&currentPage=PAGENUM&sw=${command.sw}&desc=${command.desc}&keyWords=${command.keyWords}"
totalRecord="${command.totalCount}" currentPage="${command.currentPage}"></airad:pagination>
<form:hidden path="adPreviewUrl" id="url"/>
<form:hidden path="bannerPreviewUrl" id="banner_url"/>
</form:form>

<h2 class="tit">报表详情</h2>
<table cellspacing="0" cellpadding="0" border="0" class="tabY">
  <col width="30%">
  <col width="30%">
  <col width="20%">
  <col width="20%">
    <tr>
      <th>名称</th>
      <th>创建时间</th>
      <th>类型 </th>
      <th>报表形式</th>
    </tr>
    <c:forEach var="report" items="${command.reportList}">
    <tr>
      <td><a href="/report.do?action=shop&presetValue=${report.dateType }"><airad:cutString size="20" value="${report.reportName }" mark="..."/></a></td>
      <td><fmt:formatDate value="${report.addTime }" type="both"
						pattern="yyyy-MM-dd" /></td>
      <td>活动报告</td>
      <td>汇总报告</td>
    </tr>
    </c:forEach>
</table>
</div>
</div>
</div>
</div>
<!-- 开发嵌入end-->
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script>
jQuery(document).ready(function(){
	addCss("advlist");
	var adUrl=$('#url').val();
	var bannerUrl=$("#banner_url").val();
	$(".ad_preview").click(function(){
		  $("#Bg").height($(document).height()).show();
		if($.browser.msie){
			$("#notice").show();
			}else{
		$("#Bg").show();
		$("#Box").show();
		$("#ad_preview").attr("src",adUrl+"&id="+$(this).attr("id"));
		$("#banner_preview").attr("src",bannerUrl+"&id="+$(this).attr("id"));
				}
		});
/*关闭*/
	$("#closePop").click(function(){
		$("#Bg").hide();
		$("#Box").hide();
		});
	$("#closeNotice").click(function(){
    $("#notice").hide();
    $("#Bg").hide();
    });
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



