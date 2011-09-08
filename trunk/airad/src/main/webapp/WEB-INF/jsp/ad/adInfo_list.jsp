<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告管理</title>
<script type="text/javascript">
	function deleteAd(adId) {
		if (window.confirm("确定要删除此广告？")) {
			 document.statisticform.action= "ad.do?action=deleteAd&coreAd.adId="+adId;
			 document.statisticform.submit();
		}
	}
	function stopAd(adId) {
		if (window.confirm("确定要暂停此广告？")) {
            document.statisticform.action = "ad.do?action=stopAd&coreAd.adId="+adId;
            document.statisticform.submit();
		}
    }
	function sendAd(adId) {
		if (window.confirm("确定要运行此广告？")) {
            document.statisticform.action = "ad.do?action=issue&coreAd.adId="+adId;
            document.statisticform.submit();
		}
    }

</script>

</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 开发嵌入start-->
<form name="MyFrm1" method="post">
</form>
	<a href="ad.do?action=detailEdit&adGroupId=${cag.adGroupId}&campaignId=${command.campaignId }" class="btnY fr"><span>新建广告</span></a>
<form:form action="ad.do?action=adList&adGroupId=${cag.adGroupId}&campaignId=${command.campaignId }" method="post" commandName="command">
	<h1><span><a href="campaign.do?action=listCampaign">广告活动管理</a>
	<h1><span><a href="campaign.do?action=selectAllByMemberId">广告活动管理</a>


	&raquo; <img src="images/ico_act.gif" alt="活动" width="16" height="16" align="absmiddle" /><span>[广告活动]</span>
		<a href="/adGroup.do?action=selectAll&campaignId=${command.campaignId }""><airad:cutString size="10" value="${campaign.campaignName}" mark="..."/></a> &raquo; <img src="images/ico_gup.gif" alt="广告组" width="16"
		height="16" align="absmiddle" />[广告组]</span> <airad:cutString size="10" value="${cag.adGroupName}" mark="..."/><span>
	<a href="javascript:showDocket('1')" id="btn01">显示摘要</a> <a href="javascript:showDocket('2')" id="btn02" style="display: none">隐藏摘要</a>
	</span></h1>
	<div style="display: none;" id="con01" class="info">
	<table cellspacing="0" cellpadding="0" border="0" class="tabN">
		<col width="12%" />
		<col width="38%" />
		<col width="12%" />
		<col width="38%" />
		<tbody>
			<tr>
				<th>名称：</th>
				<td><airad:cutString size="10" value="${cag.adGroupName}" mark="..."/></td>
				<th>所属行业：</th>
				<td>
				   <c:forEach items="${industryInvolved}" var="dictionaryTypes">
            <c:if test="${cag.adTagSoftType==dictionaryTypes.dictKey}">
            ${dictionaryTypes.dictVal}
            </c:if>
          </c:forEach>
        </td>
			</tr>
			<tr>
				<th>平台：</th>
				<td>${p.adGroup.platformNumber}个平台</td>
				<th>地理位置：</th>
				<td>
				  <c:choose>
				  <c:when test="${cag.adLoclType==0}">全国</c:when>
				  <c:when test="${cag.adLoclType=='2'}">精确到区</c:when>
				  <c:otherwise>常用地区</c:otherwise> </c:choose>
				</td>
			</tr>
		</tbody>
	</table>
	</div>
</form:form>

	<div class="searchBox">
		<form:form action="ad.do?action=adList" method="post"
  name="statisticform" commandName="command">
   <form:hidden path="timeSlotFlag"/>
   <form:hidden path="currentPage"/>
   <form:hidden path="adGroupId" />
   <form:hidden path="campaignId" />
   <form:hidden path="asce" />
    <table cellspacing="0" cellpadding="0" border="0">
      <tr>
      <td class="search"><form:input path="adName"/> </td>
        <td><form:input path="startTime" class="cal" readonly="true"/>
        - <form:input  path="endTime"  class="cal" readonly="true"/></td>
        <td><a class="btnS fl" href="javascript:document.statisticform.submit();" ><span>查询</span></a></td>
      </tr>
    </table>

    </form:form>
    </div>
	<table border="0" cellspacing="0" cellpadding="0" class="tabYH">
		<col width="20%" />
		<col width="10%" />
		<col width="10%" />
		<col width="10%" />
		<col width="10%" />
		<col width="8%" />
		<col width="2%" />
		<col width="8%" />
		<tr>

			<th>广告名称</th>
			<c:if test="${null==flag}">
          <th><a href="javascript:adDesc();"
            id="descId">创建时间<img alt="降序" src="/images/ico_sortza.gif"></a>
          </th>
        </c:if>
        <c:if test="${null!=flag}">
       <th><a href="javascript:adAsce();" id="asceId">创建时间<img alt="升序" src="/images/ico_sortaz.gif"></a>
          </th>
      </c:if>
			<th>展示次数</th>
			<th>出价</th>
			<th>点击数</th>
			<th>点击率</th>
			<th colspan="2">成本</th>
<!--			<th>平均每次点击价格</th>-->
			<th>状态</th>
		</tr>
		<c:forEach items="${p.adList}" var="coreAd" varStatus="s">
			<tr onmouseover="showButton('d${s.count}');"
          onmouseout="closeButton('d${s.count}');">
				<td><img src="images/ico_single.gif" alt="广告" width="16"
					height="16" align="absmiddle" />
					<airad:cutString size="10" value="${coreAd.adName}" mark="..."/>
				<div id="d${s.count}" style="display: none" class="ctl">
				<a href="ad.do?action=detailEdit&adId=${coreAd.adId }&adGroupId=${coreAd.adGroupId }&campaignId=${command.campaignId }" class="btnS"><span>编辑</span></a>
				<span class="gray">|</span>
				<c:if test="${coreAd.suspendType==1}">
        <a href="javascript:sendAd(${coreAd.adId });">运行</a>
        </c:if>
        <c:if test="${coreAd.suspendType==0}">
        <a href="javascript:stopAd(${coreAd.adId })">停止</a>
        </c:if>
				<a href="javascript:deleteAd(${coreAd.adId})">删除</a>
				</div>
				</td>
				<td><fmt:formatDate value="${coreAd.addTime}"type="both" pattern="yyyy-MM-dd HH:mm" /></td>
				<td><fmt:formatNumber type="number" value="${coreAd.showNum}"/></td>
				<td><sup>&yen;</sup><fmt:formatNumber type="number" value="${coreAd.adOffer}" pattern="###,##0.00"/></td>
        <td><fmt:formatNumber type="number" value="${coreAd.clickNum}"/></td>
        <td><fmt:formatNumber type="number" value="${coreAd.clickRate*100}" pattern="###.##"/>%</td>
        <td colspan="2"><sup>&yen;</sup><fmt:formatNumber type="number" value="${coreAd.cost}" pattern="###,##0.00"/></td>
<!--        <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${coreAd.avgClick}" pattern="###,#0.00"/></td>-->
        <td>
         <c:choose>
               <c:when test="${coreAd.blocking==1}">
                   <span class="red">冻结  </span>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.suspendType==1}">
                  <span class="red">停用</span>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.flag==0 and coreAd.suspendType==0}">
                  <span>草稿</span>
               </c:when>
                 <c:when test="${coreAd.blocking==0 and coreAd.flag==1 and coreAd.suspendType==0}">
                  <span class="orange">待审核</span>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.flag==2 and coreAd.suspendType==0}">
                  <span>审核通过</span>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.flag==3 and coreAd.suspendType==0}">
                  <span class="red">审核不通过</span>
               </c:when>
        </c:choose>
        </td>
        <!--
				<td><c:choose>
					<c:when test="${coreAd.suspendType==0}">运行</c:when>
					<c:when test="${coreAd.suspendType==1}">暂停</c:when>
					<c:otherwise>   无效状态  </c:otherwise>
				</c:choose></td>
				<td><c:choose>
					<c:when test="${coreAd.flag==0}"> 草稿</c:when>
					<c:when test="${coreAd.flag==1}"> 待审核</c:when>
					<c:when test="${coreAd.flag==2}">审核通过 </c:when>
					<c:when test="${coreAd.flag==3}">审核不通过 </c:when>
					<c:when test="${coreAd.flag==4}"> 发布</c:when>
					<c:otherwise>无效状态  </c:otherwise>
				</c:choose></td>
				-->
			</tr>
		</c:forEach>
		  <c:if test="${empty p.adList}">
        <tr><td colspan="9"><div class="c">暂无数据</div></td></tr>
    </c:if>
	</table>
	<airad:pagination pageSize="${p.pageSize}"
		href="/ad.do?action=adList&currentPage=PAGENUM&adGroupId=${adGroupId}&campaignId=${p.campaignId}
		&adName=${p.adName}&desc=${p.desc}&asce=${p.asce}&timeSlotFlag=${p.timeSlotFlag}&startTime=${p.startTime}&endTime=${p.endTime}"
		totalRecord="${p.totalCount}" currentPage="${p.currentPage}"></airad:pagination>
        <div class="tipBox">
<ul>
<li><strong>展示次数：</strong>活动中，本广告被展示的总次数；<strong>点击数：</strong>活动中，本广告获得点击的总次数；<strong>成本：</strong>该广告实际消费的广告费用。</li>
<li><strong>点击率：</strong>点击数/展示次数*100%。</li>
<!--<li><strong>平均每次点击价格：</strong>您为每次点击支付的平均价格。例如，如果您在应用程序投放的广告获得两次点击，一次费用为 &yen;0.20，一次为 &yen;0.40，则平均费用为 &yen;0.30。</li>-->
</ul>
</div>
</div>
<!-- 开发嵌入end-->
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script type="text/javascript" src="/js/ad.js"></script>
<script>
   document.onkeypress=searchAdSubmit;
   $(document).ready(function(){
       $("#startTime").datepick();
       $("#endTime").datepick();
       addCss("listCampaign");
  });
</script>
</body>
</html>
