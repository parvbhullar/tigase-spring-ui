<%@ page contentType="text/html; charset=UTF-8"%><%@ include file="/WEB-INF/jsp/ad/ad_head.jsp"%>
<h1>设置广告并出价</h1>
<form action="/ad.do?action=adSave" method="post" class="ppt.ad_adSave_zh" name="adform" id="adform">
<form:hidden path="command.adId" /> <form:hidden path="command.adGroupId" /> <form:hidden path="command.campaignId" />
<form:hidden path="command.adType" /> <input type="hidden" id="delRichId" name="delRichId"></input> 
<input type="hidden" name="showTypev" id="showTypev" />
<input type="hidden"  id="draftStatus" value="0" /><input type="hidden"  id="submitStatus" value="0" /><input type="hidden"  id="initStatus" />
<table cellspacing="0" cellpadding="0" border="0" class="tabNF"
	id="adTitleTable">
	<col width="20%" />
	<col width="80%" />
	<tr>
		<th><span class="must">*</span>广告名称</th>
		<td><form:input path="command.adName" cssClass="long"
			maxlength="50" /> <form:hidden path="command.marketType" cssClass="long"
			maxlength="50" />
		<button id="adDraftSave" type="button" style="display: none">设置广告标题</button>
		<small style="display: block">请输入一个有助于您识别该广告的名称。</small></td>
	</tr>
</table>
<%@ include file="/WEB-INF/jsp/ad/banner_in.jsp" %>
<h4><div class="fr"><a class="collapsed" id="previewNavigation"href="javascript:void(0)">广告背景预览</a>&nbsp;&nbsp;&nbsp;<a class="collapsed" id="delBackgroundPhoto"href="javascript:void(0)">删除背景图片</a></div>广告内容制作 - 导航页</h4>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
	<col width="20%" />
	<col width="80%" />
	<tr>
		<th>模板选择</th>
		<td>
		<div class="selectPicBox"><a id="pic02"
			href="javascript:void(0)"><span class="pBoxCon">单页面 </span><img
			title="单页面价格取决于您选择的广告内容模版价格" alt="单页面" src="images/pic_nav_04.gif" /></a>

		<a id="pic03" href="javascript:void(0)"
			rel="<airad:sysConfig type='NAVIGATION' flag='out' key='1' />"><span
			class="pBoxCon">普通导航
			<c:if test="${!baseRole.ossSales}">
			<span class="sml"><sup>&yen;</sup><airad:sysConfig
			type='NAVIGATION' flag='out' key='1' /></span>
			</c:if>
			</span><img title="普通导航" alt="普通导航"
			src="images/pic_nav_01.gif" /></a> <a id="pic04"
			href="javascript:void(0)"
			rel="<airad:sysConfig type='NAVIGATION' flag='out' key='2' />"><span
			class="pBoxCon">特效导航
				<c:if test="${!baseRole.ossSales}">
			<span class="sml"><sup>&yen;</sup><airad:sysConfig
			type='NAVIGATION' flag='out' key='2' /></span>
				</c:if>
			</span><img title="特效导航" alt="特效导航"
			src="images/pic_nav_02.gif" /></a> <a id="pic01"
			href="javascript:void(0)"
			rel="<airad:sysConfig type='WAP' flag='out' key='1' />"><span
			class="pBoxCon">WAP页
				<c:if test="${!baseRole.ossSales}">
			<span class="sml"><sup>&yen;</sup><airad:sysConfig
			type='WAP' flag='out' key='1' /></span>
			</c:if>
			</span><img title="WAP页" alt="WAP页"
			src="images/pic_nav_03.gif" /></a></div>
		</td>
	</tr>
	<tr id="adBackground" style="display: none;">
		<th>上传背景图片</th>
		<td><form:hidden path="command.background" /><input type="file"
			name="navigationBackground" id="navigationBackground" /><small>上传推荐宽高为320px*480px，或于此等比例图片。</small>
		</td>
	</tr>
	<tr style="display: none;" id="con20">
		<th>页面数量</th>
		<td><select name="adChildNum" id="adChildNum">
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
		</select></td>
	</tr>
</table>

 <%@ include file="/WEB-INF/jsp/ad/wap_in.jsp"%>
 
<%@ include file="/WEB-INF/jsp/ad/rich_child_in.jsp"%>
<h4>价格</h4>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
	<col width="20%" />
	<col width="80%" />
	<tbody>
	<c:choose>
	<c:when test="${baseRole.ossSales}">
		<tr>
			<th>每千次展示付费</th>
			<td><sup>¥</sup> <input
				class="short" name="adOffer" id="adOffer" type="text"
				value="<fmt:formatNumber value="${command.adOffer }" pattern="##.##" minFractionDigits="2" />" />元</td>
		</tr>
	</c:when>
	<c:otherwise>
		<tr>
			<th>点击单价</th>
			<td><sup>¥</sup> <span id="htmlAdFree"><fmt:formatNumber
				value="${command.adOffer }" pattern="##.##元" minFractionDigits="2" /></span><input
				class="short" name="adOffer" id="adOffer" type="hidden"
				value="<fmt:formatNumber value="${command.adOffer }" pattern="##.##" minFractionDigits="2" />" /></td>
		</tr>
	</c:otherwise>
	</c:choose>
	</tbody>
</table>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
	<col width="20%" />
	<col width="80%" />
	<tr>
		<th>&nbsp;</th>
		<td>
		<div class="btnBox">
		<button class="btnBY" id="saveForm" style="cursor:pointer">提交，完成广告制作</button>
		<span class="gray">|</span> <a id="saveAd" href="javascript:void(0)">保存为草稿</a>
		<span class="gray">|</span> <a id="adReturn"
			href="ad.do?action=adList&adGroupId=${command.adGroupId}&campaignId=${command.campaignId}">取消</a>
		</div>
		</td>
	</tr>
</table>
</form>
</div>
</div>

<!-- JS遮罩层 -->
<div id="fullbg"></div>
<%
    /**
     *其它的页面 
     *1.右边页面
     */
%>
<%@ include file="/WEB-INF/jsp/ad/ad_right_in.jsp"%>
</div>
<%@ include file="/WEB-INF/jsp/ad/material_in.jsp"%>
<input type="hidden" id="isNeedNotifyWhenChange2Wap" name="isNeedNotifyWhenChange2Wap" value="false"/>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<%@ include file="/WEB-INF/jsp/ad/ad_footer.jsp"%>
</body>
