<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="airad" uri="http://www.airad.com/tlds/airad-html-common"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tags/fmt.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tags/c.tld"%>
<div class="rightCon">
<div class="infoCon">
<%--
<h2><img
	width="16"
	height="16"
	alt="活动"
	src="images/ico_act.gif">活动摘要</h2>
<ul>
	<li><span class="fr "><airad:cutString size="10" value="${command.campaign.campaignName }" mark="..."/></span>名称</li>
	<li><span class="fr sml"><fmt:formatDate value="${command.campaign.startTime }" pattern="yyyy-MM-dd HH:mm"/></span>开始时间</li>
	<c:if test="${command.campaign.endTime!=null }"><li><span class="fr sml"><fmt:formatDate value="${command.campaign.endTime }" pattern="yyyy-MM-dd HH:mm"/></span>结束时间</li></c:if>
	<li><span class="fr sml"><sup>&yen;</sup>${command.campaign.budgetDay}</span>每日预算</li>
</ul>
 --%>
<h2><img
	width="16"
	height="16"
	align="absmiddle"
	alt="活动"
	src="images/ico_gup.gif">广告组摘要</h2>
<ul>
	<li>
	<c:if test="${not (command.adGroupId=='0')}">
	<span class="fr">
	<airad:cutString size="10" value="${command.adGroup.adGroupName}" mark="..."/></span>
	</c:if>

  <c:if test="${(command.adGroupId=='0')}">
    <span class="fr">
  未分组</span>
  </c:if>
	
	名称</li>
	<c:if test="${not (command.adGroupId=='0')}">
		<li><span class="fr">
<!--		${command.adGroup.platformNumber}个平台-->
		
		        <c:forEach items="${command.adGroup.platforms}" var="coreAd" varStatus="sta">
          <airad:dictString valueKey="${coreAd}" typeKey="TAG_SP"/>
          
          <c:if test="${not sta.last}">,</c:if>
        </c:forEach>
		</span>平台<br> <span></span>
		</li>
		<li><span class="fr">${command.adGroup.adLoclTypeTitle}</span>地理位置</li>
	</c:if>
<!--	<li><span class="fr">联通</span>运营商</li>-->
</ul>

<c:if test="${!baseRole.ossSales}">
<h2><img
	width="16"
	height="16"
	align="absmiddle"
	alt="广告"
	src="images/ico_single.gif">广告摘要</h2>
<ul class="ulFree">
	<li><span class="fr spanAdName"><airad:cutString size="10" value="${command.adName}" mark="..."/></span>名称</li>
	<li><span class="fr"><sup>&yen;</sup><span class="spanAdBanner singlefree">
	 
	 <c:choose>
	 <c:when test="${empty command.bannerType}">0
	 </c:when>
	 <c:otherwise>
	 <airad:sysConfig type='BANNER_TYPE' key="${command.bannerType}" valDefault="0" flag='out'/>
	 </c:otherwise>
	 </c:choose>
	 
	 
	 </span></span>Banner</li>
	
	<li style="display: none"><span class="fr"><sup>&yen;</sup><span class="spanAdNavigation singlefree">0</span></span>已选导航</li>
	<li style="display: none"><span class="fr"><sup>&yen;</sup><span class="spanAdWap singlefree">0</span></span>Wap价格</li>
	 
	<li><span class="fr"><sup>&yen;</sup><span class="spanAdFree"><fmt:formatNumber value="${command.adOffer }" pattern="##.##" minFractionDigits="2" /></span></span>点击单价</li>
</ul>
</c:if>
</div>
</div>