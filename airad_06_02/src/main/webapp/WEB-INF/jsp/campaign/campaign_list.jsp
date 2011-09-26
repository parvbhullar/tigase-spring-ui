<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>活动列表</title>
</head>
<body>
<div class="okBox"><div class="ok" style="display:none" id="okTip">保存成功</div></div>
<%String sucInfo=(String)request.getSession().getAttribute("sucInfo");%>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>
<div id="main">
<div class="mainCon">
<div class="info" id="infoClose" style="cursor:pointer"><a href="javascript:void(0)" class="fr"><img src="/images/ico_infoc.gif" width="14" height="14" alt="展开" /></a><strong>我是广告主，我该怎样定制自己的广告？</strong><small>广告制作分三个步骤：1，建立广告活动；2，在广告活动下建立广告组；3，在广告组下定制个性广告。 </small>
</div>
<div class="info" style="display:none" id="infoOpen"><a href="javascript:void(0)" class="fr" id="infoBtn"><img src="/images/ico_infoo.gif" width="14" height="14" alt="展开" /></a><strong>我是广告主，我该怎样定制自己的广告？</strong>
<p>广告制作分三个步骤：1，建立广告活动；2，在广告活动下建立广告组；3，在广告组下定制个性广告。 </p>
<p>在广告活动中建立您想要投放的周期，名称，预算等概况；在广告组中建立您想投放的地区或者人群；最后在广告组下定制您的个性广告。如下图所示：</p>
<img src="/images/pic_step.gif" width="890" height="300" class="mt" />
<p class="r"><a href="/campaign.do?action=add"><strong>现在就去新建广告&raquo;</strong></a></p>
</div>
<%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!--- 开发嵌入start--->
<a class="btnY fr" href="/campaign.do?action=add"><span>新建广告活动</span></a>
<h1>广告活动管理 </h1>
<div class="searchBox">
	<form:form action="/campaign.do?action=listCampaign" method="post" name="statisticform" commandName="command">
   <form:hidden path="timeSlotFlag"/>
   <form:hidden path="currentPage"/>
   <form:hidden path="asce"/>
   <input type="hidden" name="suspendTypeTemp" id="suspendTypeTemp"></input>
   <input type="hidden" name="campaignIdTemp" id="campaignIdTemp"></input>
		<table cellspacing="0" cellpadding="0" border="0">
		  <tr>
          <td class="search"><form:input path="campaign.campaignName" /></td>
		    <td><form:input path="startTime" class="cal" readonly="true"/>
		    - <form:input  path="endTime" class="cal" readonly="true"/></td>
<!--		    <td><a class="btnS fl" href="javascript:statisticform.submit();" ><span>查询</span></a></td>-->
		    <td><a class="btnS fl" href="javascript:document.statisticform.submit();" ><span>查询</span></a></td>
		  </tr>
		</table>
	</form:form>
</div>

	<table cellspacing="0" cellpadding="0" border="0" class="tabYH">
    <col width="20%"/>
    <col width="15%"/>
    <col width="10%"/>
    <col width="2%"/>
    <col width="12%"/>
    <col width="12%"/>
    <col width="10%"/>
    <col width="11%"/>
		<tbody>
			<tr>
				<th>活动名称</th>
				<c:if test="${null==flag}">
					<th><a href="#"
						onclick="campaignDesc('${p.currentPage}','${campaignName}');"
						id="descId">开始时间<img alt="降序" src="/images/ico_sortza.gif"></a>
					</th>
				</c:if>
				<c:if test="${null!=flag}">
					<th><a href="#"
						onclick="campaignAsce('${p.currentPage}','${campaignName}');"
						id="asceId">开始时间<img alt="升序" src="/images/ico_sortaz.gif"></a>
					</th>
				</c:if>
				<th colspan="2">预算</th>
<!--				<th>平均每次点击费用</th>-->
				<th>展示次数</th>
				<th>点击数</th>
				<th>点击率</th>
				<th>成本</th>
				<th>广告数量</th>
			</tr>
			<c:forEach items="${p.listBean}" var="corecampaign" varStatus="s">
				<tr onmouseover="showButton('d${s.count}')"
					onmouseout="closeButton('d${s.count}');" id="tr${s.count}">
					<td><a
						href="/adGroup.do?action=selectAll&campaignId=${corecampaign.campaignId}"><img
						height="16" width="16" align="absmiddle" alt="活动"
						src="images/ico_act.gif"/><airad:cutString size="8" value="${corecampaign.campaignName }" mark="..."/></a>
						<!-- show('light${s.count}',); -->
          <div class="ctl" id="d${s.count}" style="display: none">
          <a class="btnS" href="/campaign.do?action=update&campaignId=${corecampaign.campaignId}&currentPage=${p.currentPage}"><span>编辑</span></a>
          <!-- 
					<a class="btnS" href="javascript:showPopDev('${corecampaign.campaignId}','${corecampaign.campaignName}');"><span>复制</span></a>
					 -->
					<span class="gray">|</span> 
					<a href="javascript:doSuspend(${corecampaign.campaignId},1,${corecampaign.count});">
                          运行
          </a>
          <a href="javascript:doSuspend(${corecampaign.campaignId},0,${corecampaign.count});">
                          停用
          </a>
					<a href="javascript:doDel('${corecampaign.campaignId}');">删除</a>

					</div>
					</td>
					<td><fmt:formatDate value="${corecampaign.startTime}"type="both" pattern="yyyy-MM-dd HH:mm" /></td>
					<td colspan="2"><sup>&yen;</sup><fmt:formatNumber type="number" value="${corecampaign.buggetDay}"/></td>
<!--					<td><sup>&yen;</sup><fmt:formatNumber type="number" value="${corecampaign.avgAdOffer}" pattern="###,##0.00"/></td>-->
	        <td><fmt:formatNumber type="number" value="${corecampaign.showNum}"/></td>
	        <td><fmt:formatNumber type="number" value="${corecampaign.clickNum}"/></td>
	        <td><fmt:formatNumber type="number" value="${corecampaign.clickRate*100}" pattern="###.##"/>%</td>
				  <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${corecampaign.sumAdOffer}" pattern="###,##0.00"/></td>
					<td align="center" id="image${s.count}"><span onmousemove="adDetial(${corecampaign.campaignId},0,'image${s.count}')">${corecampaign.count}</span></td>
				</tr>
			</c:forEach>
			<c:if test="${empty p.listBean }">
			  <tr><td colspan="9"><div class="c">暂无数据</div></td></tr>
			</c:if>
		</tbody>
	</table>
	<airad:pagination pageSize="${p.pageSize}"
		href="/campaign.do?action=listCampaign&currentPage=PAGENUM&campaign.campaignName=${campaignName}&desc=${desc}&asce=${asce}&timeSlotFlag=${p.timeSlotFlag}&startTime=${p.startTime}&endTime=${p.endTime}"
		totalRecord="${p.totalCount}" currentPage="${p.currentPage}"></airad:pagination>
<!-- 开发嵌入end-->
<div class="tipBox">
<ul>
<li><strong>预算：</strong>活动每日最大投放金额。</li>
<!--<li><strong>平均每次点击费用：</strong>您为每次点击支付的平均价格。例如，如果您在应用程序投放的广告获得两次点击，一次费用为 &yen;0.20，一次为 &yen;0.40，则平均费用为 &yen;0.30。</li>-->
<li><strong>展示次数：</strong>所有广告被展示的总次数；<strong>点击数：</strong>所有广告获得点击的总次数；<strong>点击率：</strong>点击数/展示次数*100%；<strong>成本：</strong>该活动实际消费的广告费用。</li>
</ul>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script>
document.onkeypress=searchSubmit;
var cid="";
var cname="";
function showPopDev(id,name){
    $.openPopupLayer({
        name:'popDiv',
        url:'campaign_pop.jsp',
        cache:false,
        width:'500',
        success:function(){
            $("#campaignName_copy").val(name+"_copy");
            cid=id;
            cname=name;
       }
      });
}
function closepop(){
	  $.closePopupLayer('popDiv');
}
//时间控件
  $(document).ready(function(){
       $("#startTime").datepick();
       $("#endTime").datepick();

       <%if("sucInfo".equals(sucInfo)){ %>
       //让层5秒后消失
       $("#okTip").show();
       $("#okTip").animate({
           opacity: 1.0
       }, 5000).fadeOut("slow", function() {
           $(this).hide();
       });
     <%request.getSession().setAttribute("sucInfo","");}%>
  });
</script>
       <script>
  $(document).ready(function(){
    addCss("listCampaign");
    });
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



