<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.model.CoreCampaign"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告组添加</title>
<link href="/js/tree/tree.css" type="text/css" rel="stylesheet" />
<link href="style/lay.css" type="text/css" rel="stylesheet" />
<link href="js/area/css3buttons/stylesheets/css3buttons.css" type="text/css" rel="stylesheet" />
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<script type="text/javascript" src="/js/jquery.blockUI.js"></script>
<!--
<script type="text/javascript" src="/js/area/popupselector_cpd.js"></script>
-->
</head>

<body>
<div id="main">
<div class="mainCon"><!-- 开发嵌入start-->

<div class="leftCon">
<h1>添加广告组</h1>
<form:form name="myfrm" action="adGroup.do?action=add"
	commandName="command" method="post">
	<form:hidden path="exact" />
	<form:hidden path="adLoclInfo" />
	<form:hidden path="addFlag" />
	<form:hidden path="editFlagCheck" />
	<form:hidden path="adTagSp" />
	<form:hidden path="coreAdGroup.adGroupId" />
	<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
		<col width="25%" />
		<col width="75%" />
		<tr>
			<th><span class="must">*</span>广告组名称</th>
			<td><form:input path="coreAdGroup.adGroupName" cssClass="long"
				onkeyup="valueAlert(this,'adGroupNamesDiv')" /><input type="hidden"
				name="coreAdGroup.campaignId" value="${command.campaignId }" /><input
				type="hidden" name="campaignId" value="${command.campaignId }" />
			${el:errorTip(command.errors,"coreAdGroup.adGroupName") } <small
				style="display: block">请输入一个有助于您识别该广告组的名称，比如：长三角地区人群</small></td>
		</tr>
		<tr><th></th><td><div id="areaId">地区选择</div></td></tr>
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
		<!--
    <tr>
      <th>人群性别</th>
      <td><form:radiobutton path="coreAdGroup.adTagSex" value="1" />男性为主
      <form:radiobutton path="coreAdGroup.adTagSex" value="2" />女性为主 <form:radiobutton
        path="coreAdGroup.adTagSex" value="0" checked="checked" />不分性别</td>
    </tr>
    <tr>
      <th>人群年龄段</th>
      <td><form:select path="coreAdGroup.adTagAge">
        <form:option value="0">全年龄</form:option>
        <form:option value="1">18岁以下</form:option>
        <form:option value="2">18-24</form:option>
        <form:option value="3">25-34</form:option>
        <form:option value="4">35-44</form:option>
        <form:option value="5">45-54</form:option>
        <form:option value="6">55-64</form:option>
        <form:option value="7">65以上</form:option>
      </form:select></td>
    </tr>
     -->
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
		<!--
  <tr>
    <th>流量信息</th>
    <td>
    <div class="selectBox">
    <form:radiobutton path="coreAdGroup.adFlowInfo" value="0" />针对所有流量&nbsp&nbsp
    <form:radiobutton path="coreAdGroup.adFlowInfo" value="1" />仅针对 Wi-Fi 流量
    </div>
    </td>
  </tr>
  <tr>
    <th>运营商</th>
    <td>
    <div class="selectBox">
    <form:radiobutton path="coreAdGroup.changceInfo" value="0" />联通&nbsp&nbsp
    <form:radiobutton path="coreAdGroup.changceInfo" value="1" />移动&nbsp&nbsp
    <form:radiobutton path="coreAdGroup.changceInfo" value="2" />电信&nbsp&nbsp
    <form:radiobutton path="coreAdGroup.changceInfo" value="3" />其他
    </div>
    </td>
  </tr>
   -->
		<tr>
			<th>&nbsp;</th>
			<td>
			<div class="btnBox">
			<button class="btnBY fl" type="button"
				onclick=
	registerAdGroupPageSubmti();;
>提交</button>
			<div class="moreBtn"><span class="gray">|</span> <a
				href="adGroup.do?action=list">取消</a></div>
			</div>
			</td>
		</tr>
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
<div class="alert_lay sech_lay lm lay_wls" id="pslayer" style="display: none;">
            <!--背景圆角上-->
            <div class="alert_t">
            </div>
            <div class="box">
                <h1>
                    <span id="psHeader">请选择地区</span><a id="imgClose" class="butn3" href="javascript:void(0);">
                    </a>
                </h1>
                <div class="blk">
                    <div class="sech_layt" id="divSelecting" style="display: none;">
                        <h3>
                            <span id="selectingHeader">您选择的地区是</span><b class="btn_fst">
                            	<input type="button" value="确定" class="fst" name="" id="lnkOK">
                                <input type="button" class="butdef_n" value="清空" disabled="" name="" id="lnkEmpty">
                                </b>
                        </h3>
                        <ul id="selecting"></ul>
                    </div>
                    <div class="sech_layt btn_fst" id="noSelectedCat" style="display: none;">
                        <h3>
                            <span>提示：</span><b>
                                <input type="button" value="确定" class="fst" name="" id="btnOk">
                                <input type="button" class="butdef_n" value="清空" disabled="" name=""></b>
                        </h3>
                        <p>
                            当您直接选择职位类别时，您将会获得更多的搜索结果<br>
                            当您选择＂<img alt="" src="http://st.mychinahr.com/a/sjob6.0/style/image/ico1.gif">＂下的具体职位时，将会获得更为准确的搜索结果<br>
                            您最多可以选择5个职位类别
                        </p>
                    </div>
                    <div class="sech_layt btn_fst" id="noSelectedLoc" style="display: block;">
                        <h3>
                            <span>提示：</span><b>
                                <a href="#" class="button" id="btnOkLoc">确定</a>
                                <a href="#" class="button" id="btnOkLoc">清空</a>
                                </b>
                        </h3>
                        <p>
                            当您选择＂<img alt="" src="http://st.mychinahr.com/a/sjob6.0/style/image/ico1.gif">＂下的具体城市时，将会获得更为准确的搜索结果<br>
                            您最多可以选择5个工作地点
                        </p>
                    </div>
                    <div class="sech_layb">
                        <h2 id="subHeader1"><span>所有省市：</span></h2>
<ol id="allItems1">
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="27" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>

	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li><li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>

	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>
	<li style="list-style-type: none;"><a href="javascript:void(0);"><input type="checkbox" onclick="javascript:void(0)" value="" >天津市</a></li>

</ol>
</div>
                </div>
            </div>
            <!--背景圆角下-->

        </div>

        <div class="alert_lay sech_lay2 lay_ws lm" id="subItems" style="display: none;">
            <div id="subBox" class="box">
			<ol>
			</ol>
            </div>
        </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>

<script type="text/javascript"
	src="/js/tree/js/jquery.simple.tree.self.js"></script>
<script>
	showAdGroupTree();

	$(document).ready(function() {
		$(function() {
			addCss("adGroup.do?action=list");
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
			$("#proId").find(":input[name=ck]").each(function() {
				for ( var i = 0; i < idarray.length; i++) {
					var ckval = $(this).val();
					if (ckval == idarray[i]) {
						$("#image" + ckval).attr("src", "images/ico_op.gif");
						loadData($(this));
						break;
					}
				}
			});
		}
		addCss("listCampaign");
	});
</script>
</body>
</html>