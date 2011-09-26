<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告管理</title>

<style type="text/css">
#fullbg {
	background-color: Gray;
	display: none;
	z-index: 3;
	position: absolute;
	left: 0px;
	top: 0px;
	filter: Alpha(Opacity =   30);
	/* IE */
	-moz-opacity: 0.4;
	/* Moz + FF */
	opacity: 0.4;
}
</style>
<script type="text/javascript">
	function deleteAd(adId) {
		if (window.confirm("确定要删除此广告？")) {
			document.statisticform.action = "ad.do?action=deleteAd&adIds="
					+ adId;
			document.statisticform.submit();
		}
	}
	function stopAd(adId) {
		if (window.confirm("确定要暂停此广告？")) {
			document.statisticform.action = "ad.do?action=stopAd&adIds=" + adId;
			document.statisticform.submit();
		}
	}
	function sendAd(adId) {
		if (window.confirm("确定要运行此广告？")) {
			document.statisticform.action = "ad.do?action=issue&adIds=" + adId;
			document.statisticform.submit();
		}
	}

	/*
	function goPage(pageNum) {
	//  if (window.confirm("确定要运行此广告？")) {
	        document.statisticform.currentPage = pageNum;
	       // statisticform
	        document.statisticform.submit();
	//  }
	}
	 */
</script>

</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>


<div id="main">


<div class="popDiv collapsed" id="popDiv2"
	style="top: 100px; left: 200px; width: 400px;">
<h2><img src="images/ico_popclose.gif" alt="关闭" id="closePop2"
	class="fr" />移至其他组</h2>

<div class="popCon">
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	class="tabNF" style="margin-bottom: 0">
	<col width="35%">
	<col width="65%">
	<tr>
		<th>选择广告组</th>
		<td><select name="select" id="selectGroupId">


			<option value="">请选择..</option>

			<c:forEach items="${groupList}" var="grouItem">
			<c:if test="${p.adGroupId != grouItem.adGroupId}">
				<option value="${grouItem.adGroupId}"><airad:cutString
					size="10" value="${grouItem.adGroupName}" mark="..." /></option>
					</c:if>
			</c:forEach>
		</select></td>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<td>
		<button class="btnBY" id="btn04">提交</button>
		</td>
	</tr>
</table>

</div>
</div>
<div id="fullbg"></div>
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 开发嵌入start--> <%--
<form name="MyFrm1" method="post">
</form>
  <a href="ad.do?action=detailEdit&adGroupId=${cag.adGroupId}" class="btnY fr"><span>新建广告</span></a>
<form:form action="ad.do?action=adList&adGroupId=${cag.adGroupId}" method="post" commandName="command">
   --%>
<h1><%--
  <span>
  <a href="campaign.do?action=listCampaign">广告活动管理</a>
  &raquo; <img src="images/ico_act.gif" alt="活动" width="16" height="16" align="absmiddle" />
  <span>[广告活动]</span>
    <a href="/adGroup.do?action=selectAll"><airad:cutString size="10" value="${campaign.campaignName}" mark="..."/></a> &raquo; <img src="images/ico_gup.gif" alt="广告组" width="16"
    height="16" align="absmiddle" />[广告组]</span>
     --%> <img src="images/ico_act.gif" align="absmiddle" title="广告组"
	width="16" height="16"></img> <c:if test="${not (p.adGroupId=='0')}">


	<airad:cutString size="10" value="${cag.adGroupName}" mark="..." />
</c:if> <c:if test="${p.adGroupId=='0'}">
未分组
</c:if> <span> <c:if test="${not (p.adGroupId=='0')}">


	<a href="javascript:showDocket('1')" id="btn01">显示详细 </a>
	<a href="javascript:showDocket('2')" id="btn02" style="display: none">隐藏详细
	</a> |
  <a href="/adGroup.do?action=editPage&adGroupId=${p.adGroupId}">修改广告组</a> |
     </c:if> <a href="/adGroup.do?action=list">返回»</a> </span></h1>
<c:if test="${not (p.adGroupId=='0')}">
	<div style="display: none;" id="con01" class="info">
	<table cellspacing="0" cellpadding="0" border="0" class="tabN">
		<col width="12%" />
		<col width="38%" />
		<col width="12%" />
		<col width="38%" />
		<tbody>
			<tr>
				<th>名称：</th>
				<td><airad:cutString size="10" value="${cag.adGroupName}"
					mark="..." /></td>
				<th>所属行业：</th>
				<td><c:forEach items="${industryInvolved}"
					var="dictionaryTypes">
					<c:if test="${cag.adTagSoftType==dictionaryTypes.dictKey}">
            ${dictionaryTypes.dictVal}
            </c:if>
				</c:forEach></td>
			</tr>
			<tr>
				<th>平台：</th>
				<td><c:forEach items="${command.adGroup.platforms}"
					var="coreAd" varStatus="sta">
					<airad:dictString valueKey="${coreAd}" typeKey="TAG_SP" />
					<c:if test="${not sta.last}">,</c:if>
				</c:forEach></td>

				<th>地理位置：</th>
				<td><c:choose>
					<c:when test="${cag.adLoclType==0}">全国</c:when>
					<c:when test="${cag.adLoclType=='2'}">精确到区</c:when>
					<c:otherwise>常用地区</c:otherwise>
				</c:choose></td>
			</tr>
		</tbody>
	</table>
	</div>
</c:if> <%--
</form:form>
 --%>
<div class="searchBox">
<div class="newBtn"><a
	href="/ad.do?action=detailEdit&adGroupId=${p.adGroupId}"><strong>新建广告</strong></a>
<div style="display: none" class="right_btn" id="btns"><span>|</span>
<a id="btn03" href="javascript:void(0)">移至其他组</a><span>|</span> <c:if
	test="${not (p.adGroupId=='0')}">
	<a href="javascript:runCheckedIdS(1)">运行</a>
	<span>|</span>
	<a href="javascript:runCheckedIdS(2)">停用</a>
	<span>|</span>
</c:if> <a href="javascript:runCheckedIdS(3)">删除</a></div>
<div class="more"></div>
</div>
<div class="timeBox"><form:form action="ad.do?action=adList"
	method="post" name="statisticform" commandName="command">
	<form:hidden path="timeSlotFlag" />
	<form:hidden path="currentPage" />
	<form:hidden path="adGroupId" />
	<form:hidden path="desc" />

	<table cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td class="search"><input type="text" id="adName" name="adName" /></td>
				<td><a class="btnS fl"
					href="javascript:getDetailChart(1,$('#desc').val(),$('#timeSlotFlag').val());"><span>查询</span></a></td>
			</tr>
		</tbody>
	</table>
</form:form></div>
</div>

<!---->
<div id="list"></div>

<ul>
	<li><strong>展示次数：</strong>本广告被展示的总次数；<strong>点击数：</strong>本广告获得点击的总次数；<strong>花费：</strong>该广告实际消费的广告费用。</li>
	<li><strong>点击率：</strong>点击数/展示次数*100%。</li>
	<!--<li><strong>平均每次点击价格：</strong>您为每次点击支付的平均价格。例如，如果您在应用程序投放的广告获得两次点击，一次费用为 &yen;0.20，一次为 &yen;0.40，则平均费用为 &yen;0.30。</li>-->
</ul>
</div>
</div>
<!-- JS遮罩层 -->

<!-- 开发嵌入end-->

<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script type="text/javascript" src="/js/ad.js"></script>
<script type="text/javascript" src="/js/ad/thickbox.js"></script>
<script>
	//document.onkeypress=searchAdSubmit;
	$('.popBg,#popDiv2').bind('mousewheel', function(event, delta) {
		if (delta == 0) {
			center(".popBg")
			center("#popDiv2")
		}
	});

	//

	//提交
	$("#btn04").click(function() {
		$(".popBg").hide();
		//$(".popBg").show();
			$("#popDiv2").hide();
			$("#fullbg").hide();

		      if($("#selectGroupId").val()==null ||$("#selectGroupId").val()==""){
		          // alert('请选择广告组');
		           showTips('请选择广告组',true);
		           return ;
		       }
			runCheckedIdS(0);
		})

	//移动到其他组
	$("#btn03").click(
			function() {
		    if(${empty groupList}){
		      //   alert('');
		         showTips('没有可用的广告组，请先创建广告组',true);
		         return;
		    }
				if (!validateBox()) {
					return;
				}
				//var isIE6 = $.browser.msie && ($.browser.version == "6.0")
				//		&& !$.support.style;
				//if (isIE6) {
				//	$("#popDiv2").show();
				//} else {
					showBg("#popDiv2");
				//}

				$("#fullbg").show();
				$(".popBg").show();
				//    $("#popDiv2").show();
			})

	$("#closePop2").click(function() {
		$(".popBg").hide();
		$("#popDiv2").hide();
		$("#fullbg").hide();
	})

	$(document).ready(function() {
		$("#startTime").datepick();
		$("#endTime").datepick();
	//	addCssByURL("selectAllByMemberId");
		addCssByURL("adGroup.do?action=list");
		//  addCss("adList");
			$(".more").live("click", function() {
				$(".more").addClass("less");
				$(".more").removeClass("more");
				$("#btns").show();
			});
			$(".less").live("click", function() {
				$(".less").addClass("more");
				$(".less").removeClass("less");
				$("#btns").hide();
			});

			getDetailChart(1, 'desc', 6);
		});
</script>
</body>
</html>
