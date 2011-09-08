<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>活动添加</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>
<div id="main">
<div class="mainCon">
 <!-- 程序开始 -->
<div class="stepNew">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<col width="33%" />
	<col width="33%" />
	<col />
	<tr>
		<td class="now">1. 广告活动信息</td>
		<td>2. 设置广告组投放目标</td>
		<td>3. 设置广告并出价</td>
	</tr>
</table>
</div>
<div class="leftCon">
<h1>广告活动信息</h1>
<form:form action="campaign.do?action=doAdd" method="post"
	commandName="command">
	<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
		<col width="30%" />
		<col width="70%" />
		<tr>
			<th><span class="must">*</span>活动名称</th>
			<td><form:input path="campaign.campaignName" cssClass="long"
				onkeyup="onPreview()" maxlength="50" />
			${el:errorTip(command.errors,"campaign.campaignName") }
                <small style="display:block">请输入一个有助于您识别的广告活动名称，比如"五一促销活动"。</small>
				</td>
		</tr>
		<tr>
			<th><span class="must">*</span>开始时间</th>
			<td><form:input path="startTime" class="cal" readonly="true" />
			<form:select path="startHour"
				onchange="onPreview(this,'campaign_hs')">
				<c:forEach items="${form.hourList}" var="hour">
					<form:option value="${hour}" label="${hour}" />
				</c:forEach>
			</form:select> &nbsp;时 <form:select path="startMin"
				onchange="onPreview(this,'campaign_hs')">
				<form:option value="0" label="0" />
				<form:option value="15" label="15" />
				<form:option value="30" label="30" />
				<form:option value="45" label="45" />
			</form:select> &nbsp;分
			${el:errorTip(command.errors,"startTime") }
			</td>
		</tr>
		<tr>
			<th>是否有结束时间</th>
			<td><form:select path="timeFlag"
				onChange="showEndTime('endTime','endHour','endMin');">
				<form:option value="0" label="否" />
				<form:option value="1" label="是" />
			</form:select></td>
		</tr>
		<tr id="showEndTimeId" style="display: none">
			<th><span class="must">*</span>结束时间</th>
			<td><form:input path="endTime" class="cal" /> <form:select
				path="endHour" onchange="showDigest();">
				<c:forEach items="${form.hourList}" var="hour">
					<form:option value="${hour}" label="${hour}" />
				</c:forEach>
			</form:select> &nbsp;时 <form:select path="endMin" onchange="showDigest();">
				<form:option value="0" label="0" />
				<form:option value="15" label="15" />
				<form:option value="30" label="30" />
				<form:option value="45" label="45" />
			</form:select> &nbsp;分
			<div id="timeDiv" style="color: red;"></div>
			 ${el:errorTip(command.errors,"endTime") }
			</td>
		</tr>
		<!--  
		<tr>
			<th><span class="must">*</span>投放方式</th>
			<td><form:select path="campaign.pubKind">
				<form:option value="1" label="标准" />
				<form:option value="2" label="快速" />
			</form:select></td>
		</tr>
		-->
		<tr>
			<th><span class="must">*</span>每日预算</th>
			<td><sup>&yen;</sup> <form:input path="buggetDay"
				cssClass="tiny" onkeyup="onPreview(this,'campaign_day')"
				maxlength="8" />
      ${el:errorTip(command.errors,"buggetDay") }
<small style="display:block">此项可有效控制每日广告费用。当每日广告花费达到填入金额时，该活动中的广告将暂停投放，至次日再继续。但预算额度最低不可低于¥50。</small>
				</td>
		</tr>
		<tr>
			<th>活动说明</th>
			<td><form:textarea path="campaign.campaignState" cssClass="long" />
			${el:errorTip(command.errors,"campaign.campaignState") }
			</td>
		</tr>
		<tr>
			<th>&nbsp;</th>
			<td>
			<div class="btnBox">
			<button type="submit" type="button" class="btnBY fl">提交，进行下一步</button>
			<div class="moreBtn"><span class="gray">|</span> <a
				href="campaign.do?action=listCampaign">取消</a></div>
			</div>
			</td>
		</tr>
	</table>
</form:form></div>
<div class="rightCon">
<div class="infoCon">
<h2><img src="images/ico_act.gif" alt="活动" width="16" height="16"
	align="absmiddle" />活动摘要</h2>

<ul>
	<li><span id="campaign_name" class="fr"></span>名称</li>
	<li><span id="campaign_hs" class="fr sml"></span>开始时间</li>
	<li style="display: none" id="showEndTimeIdPreview"><span
		id="campaign_endTime" class="fr sml"></span>结束时间</li>
	<li><span id="campaign_day" class="fr sml"></span>每日预算</li>
</ul>
</div>

</div>

</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script>
	$(document).ready(function() {
		showDigest();
		showEndTime();
		$("#startTime").datepick( {
			onSelect : function(dates) {
				showDigest();
			}
		});
		$("#endTime").datepick( {
			onSelect : function(dates) {
				showDigest();
			}
		});
	});
</script>
<script>
	$(document).ready(function() {
		addCss("listCampaign");
	});
</script>
</body>
</html>