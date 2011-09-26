<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.model.CoreCampaign"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告组设置</title>
<link href="/js/tree/tree.css" type="text/css" rel="stylesheet" />
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
</head>

<body>

<div id="fullbg">
<iframe width="100%" height="100%" frameborder="0"></iframe>  
</div>
<!--  审核提示 -->
<div id="leadToAuthPage" class="popShow collapsed" >
<h2>提示</h2>
<div id="backGroupContent"><p style="padding:5px">您的身份尚未验证，本广告已保存为草稿，请进行身份验证</p></div>
<div style="float:right;margin:10px"><button type="button" class="btnBY" id="btnLeadToAuth">立即验证</button></div>

</div>

<div id="main">
<%@ include file="/WEB-INF/jspf/header.jsp"%>





<div class="mainCon"><!-- 开发嵌入start-->

<div class="stepNew sp2">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<col width="50%" />
	<col />
	<tr>
		<td>1. 广告制作</td>
		<td class="now">2. 广告组设置</td>
	</tr>
</table>
</div>
<div class="leftCon">
<h1>广告组设置</h1>
<form:form name="myfrm" action="adGroup.do?action=add"
	commandName="command" method="post">
	<form:hidden path="exact" />
	<form:hidden path="adLoclInfo" />
	<form:hidden path="addFlag" />
	<form:hidden path="editFlagCheck" />
	<form:hidden path="adTagSp" />
	<form:hidden path="adId" />
	<form:hidden path="adGroupId" />
	<form:hidden path="flag" />
	<form:hidden path="desc" />
	<form:hidden path="submitStatus" />
	
	<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
		<col width="20%" />
		<col width="80%" />
		<tr>
			<th><span class="must">*</span>选择广告组</th>
			<td><form:select id="selectAdGroup" path="coreAdGroup.adGroupId">
			</form:select></td>
		</tr>
	</table>

	<div style="display: none;" id="showAdGroup" class="info">
	<table cellspacing="0" cellpadding="0" border="0" class="tabN">
		<colgroup>
			<col width="12%" />
			<col width="38%" />
			<col width="12%" />
			<col width="38%" />
		</colgroup>
		<tbody>
			<tr>
				<th>名称：</th>
				<td id="groupName">苏宁地产2011年投放广告</td>
				<th>所属行业：</th>
				<td id="industry">影音娱乐</td>
			</tr>
			<tr>
				<th>平台：</th>
				<td id="os">Android, iPhone</td>
				<th>地理位置：</th>
				<td id="position">全国</td>
			</tr>
		</tbody>
	</table>
	</div>

	<div id="addAdGroup">
	<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
		<colgroup>
			<col width="20%" />
			<col width="80%" />
		</colgroup>
		<tbody>
			<tr>
				<th><span class="must">*</span>广告组名称</th>
				<td><form:input path="coreAdGroup.adGroupName" cssClass="long"
					onkeyup="valueAlert(this,'adGroupNamesDiv')" /><input
					type="hidden" name="coreAdGroup.campaignId"
					value="${command.campaignId }" /><input type="hidden"
					name="campaignId" value="${command.campaignId }" />
				${el:errorTip(command.errors,"coreAdGroup.adGroupName") } <small
					style="display: block">请输入一个有助于您识别该广告组的名称，比如：长三角地区人群</small></td>
			</tr>
			<tr>
				<th><span class="must">*</span>投放地区</th>
				<td><form:radiobutton path="coreAdGroup.adLoclType" value="0"
					onclick="showDetial();" checked="checked" />全国 <form:radiobutton
					path="coreAdGroup.adLoclType" value="2" onclick="showDetial();" />
				精确到区 <form:radiobutton path="coreAdGroup.adLoclType" value="1"
					onclick="showAdLoclInfoSp(this,true)" />常用地区选择
				<div id="adLoclInfoShowSp" style="display: none"><form:checkbox
					path="coreAdGroup.adLoclInfo" value="0" title="长三角" label="长三角"
					onclick="showAdLoclInfoSp(this,false)" /> <form:checkbox
					path="coreAdGroup.adLoclInfo" value="1" title="珠三角" label="珠三角"
					onclick="showAdLoclInfoSp(this,false)" /> <form:checkbox
					path="coreAdGroup.adLoclInfo" value="2" title="环渤海" label="环渤海"
					onclick="showAdLoclInfoSp(this,false)" /></div>
				<div
					style="height: 150px; width: 460px; overflow: auto; display: none"
					class="selectBox" id="proId"><c:forEach
					items="${form.proListBean}" var="DictionaryGlobalRegion"
					varStatus="statu">
					<div>
					<div><input type="hidden"
						value="${DictionaryGlobalRegion.regionId}" name="ck" /> <span
						style="cursor: pointer;"
						onclick="showData('${DictionaryGlobalRegion.regionId}')"><img
						src="images/ico_cl.gif"
						id="image${DictionaryGlobalRegion.regionId}" />${DictionaryGlobalRegion.regionName}</span>
					</div>
					<div id="d${DictionaryGlobalRegion.regionId}" style="display: none">
					<ul id="${DictionaryGlobalRegion.regionId}" class="simpleTree"></ul>
					</div>
					</div>
				</c:forEach></div>
				${el:errorTip(command.errors,"coreAdGroup.adLoclType") } <small
					style="display: block">请选择该广告组投放的地区。</small></td>
			</tr>
			<tr>
				<th><span class="must">*</span>所属行业</th>
				<td><form:select path="coreAdGroup.adTagSoftType">
					<c:forEach items="${form.industryInvolved}" var="dictionarySps">
						<form:option value="${dictionarySps.dictKey}"
							label="${dictionarySps.dictVal}" />
					</c:forEach>
				</form:select></td>
			</tr>
			<tr>
				<th><span class="must">*</span>平台</th>
				<td>
				<div class="selectBox" id="tagSpDiv" onclick=
	showTagSp();;
><c:forEach
					items="${form.arr}" var="dictionary">
					<form:checkbox path="coreAdGroup.adTagSp"
						value="${dictionary.dictKey}" label="${dictionary.dictVal}" />
				</c:forEach></div>
				${el:errorTip(command.errors,"coreAdGroup.adTagSp") } <small
					style="display: block">请选择该广告组适用的平台及版本。为了达到最佳的广告投放效果，建议选择全部。</small>
				</td>
			</tr>
		</tbody>
	</table>
	</div>



	<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
		<colgroup>
			<col width="20%" />
			<col width="80%" />
		</colgroup>
		<tbody>
			<tr>
				<th>&nbsp;</th>
				<td>
				<div class="btnBox">
				<button class="btnBY fl" type="button"
					onclick=
	setAdGroupPageSubmti();;
>提交，完成广告制作</button>
				<div class="moreBtn"><span class="gray">|</span> <a
					href="/adGroup.do?action=list">取消</a></div>
				</div>
				</td>
			</tr>
		</tbody>
	</table>
</form:form></div>
<div class="rightCon">
<div class="infoCon">

<h2><img src="images/ico_gup.gif" alt="活动" width="16" height="16"
	align="absmiddle" />广告组摘要</h2>
<ul>
	<li><span id="adGroupNamesDiv" class="fr"></span>名称</li>
	<li><span id="geographyDiv" class="fr"></span>投放地区</li>
	<li><span id="tagSpShowDiv" class="fr"> </span>平台</li>
</ul>
</div>
</div>
<!-- 开发嵌入end--></div>

</div>
<!-- JS遮罩层 -->



<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript"
	src="/js/tree/js/jquery.simple.tree.self.js"></script>

<script type="text/javascript" src="/js/cookie.js"></script>
<script type="text/javascript" src="/js/ad/thickbox.js"></script>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script>
	showAdGroupTree();
	var adGroupData = null;

	$(document).ready(function() {
		var json = "";
		//初始化广告组下拉框
			$.ajax( {
				type : "POST",
				url : "adGroup.do?action=selectAllByMemberIdJSON",
				success : function(data) {
					adGroupData = data;
					$("#selectAdGroup").append(
							"<option value='0'>请选择...</option>");
					$("#selectAdGroup").append(
							"<option value='-1'>新建分组...</option>");
					for ( var i = 0; i < data.length; i++) {
						var name = data[i].adGroupName;
						if (name != "" && name.length > 20) {
							name = name.substr(1, 15) + "...";
						}
						$("#selectAdGroup").append(
								"<option value='" + data[i].adGroupId
										+ "' title='" + data[i].adGroupName
										+ "'>" + name + "</option>");
					}
					//默认选中分组
			        setTimeout(function(){
			          $("#selectAdGroup").change(change);
			          $("#selectAdGroup").val( $("#adGroupId").val());
			          change();
			        },0);
				}
			});

			showDetial();
			var ids = document.getElementById("exact");
			/*
			 var s= $("#proId").find(":checkbox[name=ck]:checked").each(function(){
			        loadData($(this));
			    });*/
			if (ids.value != "") {
				var idReplace = ids.value.replace(new RegExp(";", 'g'), ",");
				idReplace = idReplace.substring(0, idReplace.length - 1);
				var idarray = idReplace.split(",");
				$("#proId").find(":input[name=ck]").each(
						function() {
							for ( var i = 0; i < idarray.length; i++) {
								var ckval = $(this).val();
								if (ckval == idarray[i]) {
									$("#image" + ckval).attr("src",
											"images/ico_op.gif");
									loadData($(this));
									break;
								}
							}
						});
			}
			addCss("/adGroup.do?action=list");

		     $("#btnLeadToAuth").click(function(){
		         $.cookie('_adauth${form.adId}', null);
		         window.location="/advertiser.do?action=authenticatePage";
		       });

		       var value = $.cookie('_adauth${form.adId}');
		       if(value==1){
 

		         if (isIE6) {
		             showBg("#fullbg");
		              resetBg($("#fullbg"));
		             showBg("#leadToAuthPage");
		         } else {
		             showBg("#fullbg1");
		              resetBg($("#fullbg"));
		             showBg("#leadToAuthPage");
		         }
		       }
		});

	function change() {
		var checkValue = $("#selectAdGroup").val();
		if ("-1" == checkValue) {
			$("#addAdGroup").show();
			$("#showAdGroup").hide();
		} else if (checkValue == 0) {
			$("#addAdGroup").hide();
			$("#showAdGroup").hide();
		} else {
			$("#addAdGroup").hide();
			$("#showAdGroup").show();
			getGroupInfo(checkValue);
		}
	}

	function getGroupInfo(groupId) {
		for ( var i = 0; i < adGroupData.length; i++) {
			if (adGroupData[i].adGroupId == groupId) {
				$("#groupName").html(adGroupData[i].adGroupName);
				$("#industry").html(adGroupData[i].adTagSoftType);
				$("#os").html(adGroupData[i].adTagSp);
				$("#position").html(adGroupData[i].adLoclType);
				return;
			}
		}
	}



	//下是初始化页面的效果
	//因为要用到jstl标签 所以无法放到js文件中
	//按照广告类型初始化页面


</script>

</body>
</html>