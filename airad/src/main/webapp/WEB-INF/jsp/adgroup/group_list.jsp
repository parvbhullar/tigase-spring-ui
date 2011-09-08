<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.mitian.airad.model.CoreCampaign"%>
<%@page import="org.apache.commons.lang.time.DateFormatUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告组列表</title>
<%
CoreCampaign coreCampaign=(CoreCampaign)request.getAttribute("campaign");
String startTime="";
if(null!=coreCampaign){
if(null!=coreCampaign.getStartTime()){
    startTime =DateFormatUtils.format(coreCampaign.getStartTime(),"yyyy-MM-dd HH:mm");
}}
%>
<style>
.a11 {
  cursor: pointer;
  color: blue;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>
<div id="main">
<div class="mainCon">
<%@ include file="/WEB-INF/jspf/errors.jsp"%>
	<a href="adGroup.do?action=addPage&campaignId=${command.campaignId }" class="btnY fr"><span>新建广告组</span></a>
	<h1>
	<span>
	<a href="campaign.do?action=listCampaign">广告活动管理</a> &raquo;
	<img src="images/ico_act.gif" alt="活动" width="16" height="16" align="absmiddle" />[广告活动]</span><airad:cutString size="10" value="${campaign.campaignName}" mark="..."/><span>
		<a href="javascript:showDocket('1')" id="btn01">显示摘要</a>
		<a href="javascript:showDocket('2')" id="btn02" style="display:none">隐藏摘要</a>
		</span></h1>
	<div class="info" id="con01" style="display: none">
	<table border="0" cellspacing="0" cellpadding="0" class="tabN">
		<col width="12%"/>
		<col width="38%"/>
		<col width="12%"/>
		<col width="38%"/>
		<tr>
			<th>名称:</th>
			<td><airad:cutString size="10" value="${campaign.campaignName}" mark="..."/></td>
			<th>生效时间:</th>
			<td><%=startTime %></td>
		</tr>
		<tr>
			<th>预算:</th>
			<td><sup>&yen;</sup>${campaign.buggetDay}</td>
			<th>&nbsp;</th>
			<td>&nbsp;
			</td>
		</tr>
	</table>
	</div>
	<div class="searchBox">
	
	<form:form action="/adGroup.do?action=selectAll" method="post"
  commandName="command" name="sform">
<form:hidden path="timeSlotFlag"/>
<form:hidden path="currentPage"/>
<form:hidden path="campaignId"/>
  <form:hidden path="adGroupIdTemp"/>
	<table border="0" cellpadding="0" cellspacing="0">
			<tr>
            <td class="search"><form:input path="adGroupName"/> </td>
				<td><form:input path="startTime" class="cal" readonly="true"/>
            - <form:input  path="endTime" class="cal" readonly="true"/></td>
<!--				<td><a href="javascript:adGroupTimeSlotSubmit();" class="btnS fl"><span>查询</span></a></td>-->
            <td><a href="javascript:document.sform.submit();" class="btnS fl"><span>查询</span></a></td>
			</tr>
	</table>
	</form:form>
	</div>
	<table border="0" cellspacing="0" cellpadding="0" class="tabYH">
		<col width="35%" />
		<col width="17%" />
		<col width="17%" />
		<col width="10%" />
		<col width="2%" />
		<col width="11%"/>
		<tr>
			<th>广告组名称</th>
			<th>展示次数</th>
			<th>点击数</th>
			<th>点击率</th>
			<th colspan="2">成本</th>
<!--			<th>平均每次点击费用</th>-->
			<th>广告数量</th>
		</tr>
		<!-- 遍历取得的广告组数据并显示出来 -->
		<c:forEach items="${p.groupList}" var="CoreAdGroup" varStatus="s">
			<tr onmouseover="showButton('d${s.count}');"
          onmouseout="closeButton('d${s.count}');">
				<td><a
					href="ad.do?action=adList&adGroupId=${CoreAdGroup.adGroupId}&campaignId=${campaign.campaignId }">
					<img height="16" align="absmiddle" width="16" alt="广告组" src="images/ico_gup.gif"/>
				<airad:cutString size="10" value="${CoreAdGroup.adGroupName}" mark="..."/></a>
				<div id="d${s.count}" style="display: none" class="ctl">
				<a href="/adGroup.do?action=editPage&adGroupId=${CoreAdGroup.adGroupId}" class="btnS"><span>编辑</span></a>
				<!--  
					<a href="javascript:showPopDev('${CoreAdGroup.adGroupId}','${CoreAdGroup.adGroupName}');" class="btnS"><span>复制</span></a> 
					-->
					<span class="gray">|</span>
					<a href="javascript:stopandsendAdGroup('${CoreAdGroup.adGroupId}','1',${CoreAdGroup.cnt});">运行</a>
					<a href="javascript:stopandsendAdGroup('${CoreAdGroup.adGroupId}','0',${CoreAdGroup.cnt});">停止</a>
					<a href="javascript:deleteGroup('${CoreAdGroup.adGroupId}','${CoreAdGroup.campaignId}');">删除</a></div>
				</td>
				<td><fmt:formatNumber type="number" value="${CoreAdGroup.showNum}"/></td>
				<td><fmt:formatNumber type="number" value="${CoreAdGroup.clickNum}"/></td>
				<td><fmt:formatNumber type="number" value="${CoreAdGroup.clickRate*100}" pattern="###.##"/>%</td>
				<td colspan="2"><sup>&yen;</sup><fmt:formatNumber type="number" value="${CoreAdGroup.cost}" pattern="###,##0.00"/></td>
<!--				<td><sup>&yen;</sup><fmt:formatNumber type="number" value="${CoreAdGroup.avgClick}" pattern="###,#0.00"/></td>-->
				<td align="center" id="image${s.count}"><span onmousemove="adDetial(${CoreAdGroup.adGroupId},1,'image${s.count}')">${CoreAdGroup.cnt}</span></td>
			</tr>
		</c:forEach>
		<c:if test="${empty p.groupList }">
        <tr><td colspan="9"><div class="c">暂无数据</div></td></tr>
    </c:if>
	</table>
	
	 <airad:pagination pageSize="${p.pageSize}" href="/adGroup.do?action=selectAll&currentPage=PAGENUM&campaignId=${campaign.campaignId}
	 &timeSlotFlag=${p.timeSlotFlag}&startTime=${p.startTime}&endTime=${p.endTime}&adGroupName=${p.adGroupName}"
totalRecord="${p.totalCount}" currentPage="${p.currentPage}"></airad:pagination>
	<!-- 开发嵌入end-->
	<script type="text/javascript">

	function deleteGroup(id,campaignId)
	{
		  if(window.confirm("确定删除广告组？"))
		  {
			  document.sform.action = "adGroup.do?action=deletePage&adGroupId="+id;
			  document.sform.submit();
			}
	}
	
	function setFormAction(prm) {
		document.myfrm.action = prm;
		document.myfrm.submit();
	}
</script>
<div class="tipBox">
<ul>
<li><strong>展示次数：</strong>本组广告被展示的总次数；<strong>点击数：</strong>活动中，本组广告获得点击的总次数；<strong>成本：</strong>该广告组实际消费的广告费用。</li>
<li><strong>点击率：</strong>点击数/广告展示次数*100%。</li>
<!--<li><strong>平均每次点击金额：</strong>您为每次点击支付的平均价格。例如，如果您在应用程序投放的广告获得两次点击，一次费用为 &yen;0.20，一次为 &yen;0.40，则平均费用为 &yen;0.30。</li>-->
</ul>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script>
document.onkeypress=adGroupStatistic;
var cid="";
var cname="";
function showPopDev(id,name){
    $.openPopupLayer({
        name:'popDiv',
        url:'group_pop.jsp',
        cache:false,
        width:'500',
        success:function(){
            $("#adGroupName_copy").val(name+"_copy");
            cid=id;
            cname=name;
        }
      });
}
function closepop(){
    $.closePopupLayer('popDiv');
}
$(document).ready(function(){
    $("#startTime").datepick();
    $("#endTime").datepick();
    addCss("listCampaign");
});
</script>
</body>
</html>
