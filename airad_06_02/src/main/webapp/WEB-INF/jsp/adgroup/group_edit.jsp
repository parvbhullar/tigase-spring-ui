<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.web.form.AdGroupForm"%>
<%@page import="com.mitian.airad.model.CoreCampaign"%>
<%@page import="org.apache.commons.lang.time.DateFormatUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告组修改</title>
<link href="/js/tree/tree.css" type="text/css" rel="stylesheet" />
</head>
<%
CoreCampaign coreCampaign=(CoreCampaign)request.getAttribute("campaign");
String startTime="";
if(null!=coreCampaign){
if(null!=coreCampaign.getStartTime()){
    startTime =DateFormatUtils.format(coreCampaign.getStartTime(),"yyyy-MM-dd HH:mm");
}}
%>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
<!-- 开发嵌入start-->
<form:form name="myfrm" action="adGroup.do?action=edit" commandName="command"  method="post">
<form:hidden path="exact" />
<form:hidden path="adGroupId"/>
<form:hidden path="editErrorFlag"/>
<form:hidden path="adLoclInfo"/>
<form:hidden path="editFlagCheck"/>
<form:hidden path="adTagSp"/>
<h1> <img src="images/ico_gup.gif" alt="广告组" width="16" height="16" align="absmiddle" /><airad:cutString size="10" value="${command.coreAdGroup.adGroupName}" mark="..."/></h1>
<div class="leftCon">
<h1>编辑广告组</h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
  <col width="25%" />
  <col width="75%" />
  <tr>
    <th><span class="must">*</span>广告组名称</th>
    <td><form:input path="coreAdGroup.adGroupName" cssClass="long" onkeyup="valueAlert(this,'adGroupNamesDiv')"  />
    <form:hidden path="coreAdGroup.adGroupId" />
<!--      <form:hidden path="coreAdGroup.campaignId" />-->
	<!--
    <input type="hidden" name="campaignId" value="${command.coreAdGroup.campaignId }" />-->
    <small style="display:block">请输入一个有助于您识别该广告组的名称，比如：长三角地区人群</small>
    ${el:errorTip(command.errors,"coreAdGroup.adGroupName") }
    </td>
  </tr>
    <tr>
    <th><span class="must">*</span>地理位置</th>
    <td><form:radiobutton path="coreAdGroup.adLoclType" value="0"  onclick="showDetial();"  />全国
    <form:radiobutton path="coreAdGroup.adLoclType" value="2" onclick="showDetial();"  /> 精确到区
	<div id="areaId" style="display: block">地区选择</div>
    <form:radiobutton path="coreAdGroup.adLoclType" value="1" onclick="showAdLoclInfoSp(this,true)" />快捷方式选择
    <div id="adLoclInfoShowSp" style="display: none">
   <label>地区：</label>
   <form:checkbox path="coreAdGroup.adLoclInfo" value="0" title="长三角" label="长三角" onclick="showAdLoclInfoSp(this,false)" />
   <form:checkbox path="coreAdGroup.adLoclInfo" value="1" title="珠三角" label="珠三角" onclick="showAdLoclInfoSp(this,false)" />
   <form:checkbox path="coreAdGroup.adLoclInfo" value="2" title="环渤海" label="环渤海" onclick="showAdLoclInfoSp(this,false)" />
    </div>
   <div style="height: 150px;width:460px; overflow: auto;display: none" class="selectBox"
        id="proId"><c:forEach items="${form.proListBean}"
        var="DictionaryGlobalRegion">
        <div>
        <div><input type="hidden" value="${DictionaryGlobalRegion.regionId}" name="ck" />
         <span style="cursor: pointer;" onclick="showData('${DictionaryGlobalRegion.regionId}')"><img src="images/ico_cl.gif" id="image${DictionaryGlobalRegion.regionId}"/>${DictionaryGlobalRegion.regionName}</span>
        </div>
        <div id="d${DictionaryGlobalRegion.regionId}" style="display: none">
        <ul id="${DictionaryGlobalRegion.regionId}" class="simpleTree"></ul>
        </div>
        </div>
      </c:forEach></div>
      ${el:errorTip(command.errors,"coreAdGroup.adLoclType") }
       <small style="display:block">请选择该广告组投放的地区。</small>
    </td>
  </tr>
  <!--
  <tr>
    <th>人群性别</th>
    <td><form:radiobutton path="coreAdGroup.adTagSex" value="1"/>男性为主
        <form:radiobutton path="coreAdGroup.adTagSex" value="2"/>女性为主
        <form:radiobutton path="coreAdGroup.adTagSex" value="0"/>不分性别</td>
  </tr>
  <tr>
    <th>人群年龄段</th>
    <td>
    <form:select path="coreAdGroup.adTagAge">
      <form:option value="0">全年龄</form:option>
      <form:option value="1">18岁以下</form:option>
      <form:option value="2">18-24</form:option>
      <form:option value="3">25-34</form:option>
      <form:option value="4">35-44</form:option>
      <form:option value="5">45-54</form:option>
      <form:option value="6">55-64</form:option>
      <form:option value="7">65以上</form:option>
    </form:select>
    </td>
  </tr>
  -->
  <tr>
    <th><span class="must">*</span>所属行业</th>
    <td>
       <form:select path="coreAdGroup.adTagSoftType">
            <c:forEach items="${form.industryInvolved}" var="dictionarySps">
            <form:option value="${dictionarySps.dictKey}" label="${dictionarySps.dictVal}"/>
            </c:forEach>
       </form:select>
    </td>
  </tr>
  <tr>
    <th><span class="must">*</span>平台</th>
    <td>
     <div class="selectBox" id="tagSpDiv" onclick="showTagSp();">
       <c:forEach items="${form.arr}" var="dictionary">
         <form:checkbox path="coreAdGroup.adTagSp" value="${dictionary.dictKey}" label="${dictionary.dictVal}"/>
       </c:forEach>
     </div>
     ${el:errorTip(command.errors,"coreAdGroup.adTagSp") }
    <small style="display:block">请选择该广告组适用的平台及版本。为了达到最佳的广告投放效果，建议选择全部。</small>
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
      <button class="btnBY fl" onclick="registerAdGroupPageSubmti();">提交</button>
        <div class="moreBtn"><span class="gray">|</span> <a
        href="ad.do?action=adList&adGroupId=${form.adGroupId }">取消</a></div>
      </div>
      </td>
    </tr>
</table>
</div>
<div class="rightCon">
<div class="infoCon">
<!--
<h2><img src="images/ico_act.gif" alt="活动" width="16" height="16"
  align="absmiddle" />活动摘要</h2>
<ul>
  <li><span class="fr"><airad:cutString size="8" value="${campaign.campaignName}" mark="..."/></span>名称</li>
  <li><span class="fr sml"><%=startTime%></span>开始时间</li>
  <c:if test="${!empty campaign.endTime}">
  <li><span class="fr sml"><fmt:formatDate value="${campaign.endTime}"type="both" pattern="yyyy-MM-dd HH:mm" /></span>结束时间</li>
  </c:if>
  <li><span class="fr sml"><sup>&yen;</sup>${campaign.buggetDay}</span>每日预算</li>
  <li><span class="fr sml"><c:choose><c:when test="${campaign.pubKind}=='2'">加速</c:when><c:otherwise>正常</c:otherwise> </c:choose>  </span>投放方式</li>
</ul>
-->
<h2><img src="images/ico_gup.gif" alt="活动" width="16" height="16"
  align="absmiddle" />广告组摘要</h2>
<ul>
  <li><span id="adGroupNamesDiv"  class="fr"><airad:cutString size="8" value="${form.coreAdGroup.adGroupName}" mark="..."/></span>名称
  </li>
  <li>
  <span id="geographyDiv"  class="fr"></span>地理位置
  </li>
  <li>
  <span id="tagSpShowDiv"  class="fr">
  </span>平台
  </li>
</ul>
</div>
</div>
</form:form>
</div>
<!-- 开发嵌入end--></div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script type="text/javascript" src="/js/tree/js/jquery.simple.tree.self.js"></script>
<script type="text/javascript" src="/js/area/area.js"></script>
<link href="style/lay.css" type="text/css" rel="stylesheet" />
<script>

    $(document).ready(function() {
      showDetial();
      var ids = document.getElementById("exact");
      /*
       var s= $("#proId").find(":checkbox[name=ck]:checked").each(function(){
              loadData($(this));
          });*/
          if (ids.value != "") {
              var idReplace = ids.value.replace(new RegExp(";", 'g'), ",");
              var idarray=idReplace.split(",");
                $("#proId").find(":input[name=ck]").each(function() {
                  for ( var i = 0; i < idarray.length; i++) {
                    var ckval = $(this).val();
                      if (ckval == idarray[i]) {
                    	  $("#image"+ckval).attr("src","images/ico_op.gif");
                              loadData($(this));
                              break;
                      }
                  }
                });
            }

      showAdGroupTree();
      addCss("adGroup.do?action=list");
    });
</script>

<div class="alert_lay sech_lay lm lay_wls" id="pslayer" style="display: none;position: absolute;">
            <!--背景圆角上-->
            <div class="alert_t">
            </div>
            <div class="box">
                <h1>
                    <span id="psHeader">请选择地区</span><a id="imgClose" class="butn3" href="javascript:void(0);">
                    </a>
                </h1>
                <div class="blk">
                    <div class="sech_layt btn_fst" id="divSelecting" >
                        <h3>
                            <span id="selectingHeader">您选择的地区是</span><b class="btn_fst">

                            	<a href="#" class="button" id="btnOkLoc">确定</a>
                                <a href="#" class="button" id="lnkEmpty">清空</a>
                                </b>
                        </h3>
                        <ul id="selecting"></ul>
                    </div>
                    <!--
                    <div class="sech_layt btn_fst" id="noSelectedLoc" style="display: block;">
                        <h3>
                            <span>提示：</span><b>
                                <a href="#" class="button" id="btnOkLoc">确定</a>
                                <a href="#" class="button" id="btnOkLoc">清空</a>
                                </b>
                        </h3>
                        <p>
                            	您最多可以选择5个地点
                        </p>
                    </div>
                    -->
                    <div class="sech_layb" id="sech_layb_id">
                        <h2 id="subHeader1"><span>所有省市：</span></h2>
<ol id="allItems">
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx2" onclick="changeBgColor(this,1)" value="2@北京市" />北京市</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx25" onclick="changeBgColor(this,1)" value="25@上海市" />上海市</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx27" onclick="changeBgColor(this,1)" value="27@天津市" />天津市</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx32" onclick="changeBgColor(this,1)" value="32@重庆市" />重庆市</a></li>

	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx6" onclick="changeBgColor(this,1)" value="6@广东省" />广东省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx16" onclick="changeBgColor(this,1)" value="16@江苏省" />江苏省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx31" onclick="changeBgColor(this,1)" value="31@浙江省" />浙江省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx3" onclick="changeBgColor(this,1)" value="3@安徽省" />安徽省</a></li>

	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx4" onclick="changeBgColor(this,1)" value="4@福建省" />福建省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx5" onclick="changeBgColor(this,1)" value="5@甘肃省" />甘肃省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx7" onclick="changeBgColor(this,1)" value="7@广西" />广西</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx8" onclick="changeBgColor(this,1)" value="8@贵州省" />贵州省</a></li>

	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx9" onclick="changeBgColor(this,1)" value="9@海南省" />海南省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx10" onclick="changeBgColor(this,1)" value="10@河北省" />河北省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx11" onclick="changeBgColor(this,1)" value="11@河南省" />河南省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx12" onclick="changeBgColor(this,1)" value="12@黑龙江省" />黑龙江省</a></li>

	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx13" onclick="changeBgColor(this,1)" value="13@湖北省" />湖北省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx14" onclick="changeBgColor(this,1)" value="14@湖南省" />湖南省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx15" onclick="changeBgColor(this,1)" value="15@吉林省" />吉林省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx17" onclick="changeBgColor(this,1)" value="17@江西省" />江西省</a></li>

	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx18" onclick="changeBgColor(this,1)" value="18@辽宁省" />辽宁省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx19" onclick="changeBgColor(this,1)" value="19@内蒙古" />内蒙古</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx20" onclick="changeBgColor(this,1)" value="20@宁夏" />宁夏</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx21" onclick="changeBgColor(this,1)" value="21@青海省" />青海省</a></li>

	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx22" onclick="changeBgColor(this,1)" value="22@山东省" />山东省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx23" onclick="changeBgColor(this,1)" value="23@山西省" />山西省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx24" onclick="changeBgColor(this,1)" value="24@陕西省" />陕西省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx26" onclick="changeBgColor(this,1)" value="26@四川省" />四川省</a></li>

	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx28" onclick="changeBgColor(this,1)" value="28@西藏" />西藏</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx29" onclick="changeBgColor(this,1)" value="29@新疆" />新疆</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx30" onclick="changeBgColor(this,1)" value="30@云南省" />云南省</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx33" onclick="changeBgColor(this,1)" value="33@香港" />香港</a></li>

	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx34" onclick="changeBgColor(this,1)" value="34@澳门" />澳门</a></li>
	<li ><a href="javascript:void(0);"><input type="checkbox" id="pcbx35" onclick="changeBgColor(this,1)" value="35@台湾" />台湾</a></li>
</ol>
</div>
                </div>
            </div>
            <!--背景圆角下-->
        </div>
</body>
</html>