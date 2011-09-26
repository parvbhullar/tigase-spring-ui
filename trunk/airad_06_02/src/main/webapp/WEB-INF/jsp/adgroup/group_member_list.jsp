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
<link href="/js/tree/tree.css"  type="text/css" rel="stylesheet"/>
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

	<h1>所有广告组</h1>
	<div class="searchBox">
<div class="newBtn">

<a href="/ad.do?action=detailEdit"><strong>新建广告</strong></a><span>|</span><a href="/adGroup.do?action=newAddPage"><strong>新建广告组</strong></a><div style="display: none;" id="btns" class="right_btn"><span>|</span><a href="javascript:void(0)" id="btnModLoclType">修改投放地区</a><span>|</span><a href="javascript:void(0)" id="btnModIndustry">修改所属行业</a><span>|</span><a href="javascript:void(0)" id="btnModOs">修改投放平台</a><span>|</span><a href="#" id="btnRun">运行</a><span>|</span><a href="#" id="btnStop">停用</a><span>|</span><a href="#" id="btnDel">删除</a></div>
<div class="more" id="moreId"></div>
<!--
<a href="/ad.do?action=adList"><strong>新建广告</strong></a> | <a href="/adGroup.do?action=newAddPage"><strong>新建广告组</strong></a><span style="display: none;" id="btns"> | <a href="javascript:void(0)" id="btnModArea">修改投放地区</a> | <a href="javascript:void(0)" id="btnModIndustry">修改所属行业</a> | <a id="btnModOs" href="javascript:void(0)" id="btnModOs">修改投放平台</a> | <a href="javascript:void(0)" id="btnRun">运行</a> | <a href="javascript:void(0)" id="btnStop">停用</a> | <a href="javascript:void(0)" id="btnDel">删除</a></span>
-->
</div>
<div class="timeBox">
<form:form action="/adGroup.do?action=list" method="post"
  commandName="command" name="sform">
	<form:hidden path="sortCol"/>
	<form:hidden path="desc"/>
	<form:hidden path="asce"/>
	<form:hidden path="currentPage"/>
	<form:hidden path="campaignId"/>
  	<form:hidden path="adGroupIdTemp"/>



<table cellspacing="0" cellpadding="0" border="0">
  <tbody><tr>
    <td class="search"><form:input path="adGroupName" /> </td>
    <td><a href="javascript:document.sform.submit();" class="btnS fl"><span>查询</span></a></td>
  </tr>
</tbody></table>
</form:form>
</div>
</div>
	<table border="0" cellspacing="0" cellpadding="0" class="tabY" id="list">
		<colgroup><col width="3%"/><col width="20%"/><col width="9%"/><col width="9%"/><col width="9%"/><col width="8%"/><col width="8%"/><col width="8%"/><col width="8%"/><col width="8%"/>
  		</colgroup>
  		<tr>
    <th><input type="checkbox" id="checkboxswitch" alt="全选"/></th>
    <th>广告组名称</th>
    <th>投放地区</th>
    <th>所属行业</th>
    <th>平台</th>
    <th style="text-align:right">
    	<c:if test="${'SHOW_NUM'==p.sortCol}">
			<c:if test="${(1==p.desc)}">
				<a href="#"
						onclick="showNumAsce('${p.currentPage}','SHOW_NUM');"
						id="descId">展示次数<img alt="降序" src="/images/ico_sortza.gif"/></a>

			</c:if>
			<c:if test="${(1==p.asce)}">
				<a href="#"
						onclick="showNumDesc('${p.currentPage}','SHOW_NUM');"
						id="descId">展示次数<img alt="升序" src="/images/ico_sortaz.gif"/></a>

			</c:if>

	</c:if>

	<c:if test="${'SHOW_NUM'!=p.sortCol}">
					<a href="#"
						onclick="showNumDesc('${p.currentPage}','SHOW_NUM');"
						id="descId">展示次数<img alt="排序" src="/images/ico_sort.gif"/></a>

	</c:if>
    </th>
    <th style="text-align:right">
    	<c:if test="${'CLICK_NUM'==p.sortCol}">
			<c:if test="${(1==p.desc)}">
				<a href="#"
						onclick="showNumAsce('${p.currentPage}','CLICK_NUM');"
						id="descId">点击数<img alt="降序" src="/images/ico_sortza.gif"/></a>

			</c:if>
			<c:if test="${(1==p.asce)}">
				<a href="#"
						onclick="showNumDesc('${p.currentPage}','CLICK_NUM');"
						id="descId">点击数<img alt="升序" src="/images/ico_sortaz.gif"/></a>

			</c:if>

	</c:if>

	<c:if test="${'CLICK_NUM'!=p.sortCol}">
					<a href="#"
						onclick="showNumDesc('${p.currentPage}','CLICK_NUM');"
						id="descId">点击数<img alt="排序" src="/images/ico_sort.gif"/></a>

	</c:if>
    </th>
    <th style="text-align:right">
    	<c:if test="${'CLICK_RATE'==p.sortCol}">
			<c:if test="${(1==p.desc)}">
				<a href="#"
						onclick="showNumAsce('${p.currentPage}','CLICK_RATE');"
						id="descId">点击率<img alt="降序" src="/images/ico_sortza.gif"/></a>

			</c:if>
			<c:if test="${(1==p.asce)}">
				<a href="#"
						onclick="showNumDesc('${p.currentPage}','CLICK_RATE');"
						id="descId">点击率<img alt="升序" src="/images/ico_sortaz.gif"/></a>

			</c:if>

	</c:if>

	<c:if test="${'CLICK_RATE'!=p.sortCol}">
					<a href="#"
						onclick="showNumDesc('${p.currentPage}','CLICK_RATE');"
						id="descId">点击率<img alt="排序" src="/images/ico_sort.gif"/></a>

	</c:if>
    </th>
    <th style="text-align:right">
    	<c:if test="${'COST'==p.sortCol}">
			<c:if test="${(1==p.desc)}">
				<a href="#"
						onclick="showNumAsce('${p.currentPage}','COST');"
						id="descId">花费<img alt="降序" src="/images/ico_sortza.gif"/></a>

			</c:if>
			<c:if test="${(1==p.asce)}">
				<a href="#"
						onclick="showNumDesc('${p.currentPage}','COST');"
						id="descId">花费<img alt="升序" src="/images/ico_sortaz.gif"/></a>

			</c:if>

	</c:if>

	<c:if test="${'COST'!=p.sortCol}">
					<a href="#"
						onclick="showNumDesc('${p.currentPage}','COST');"
						id="descId">花费<img alt="排序" src="/images/ico_sort.gif"/></a>

	</c:if>
    </th>
    <th>

	<c:if test="${'UPD_TIME'==p.sortCol}">
			<c:if test="${(1==p.desc)}">
				<a href="#"
						onclick="showNumAsce('${p.currentPage}','UPD_TIME');"
						id="descId">更新时间<img alt="降序" src="/images/ico_sortza.gif"/></a>

			</c:if>
			<c:if test="${(1==p.asce)}">
				<a href="#"
						onclick="showNumDesc('${p.currentPage}','UPD_TIME');"
						id="descId">更新时间<img alt="升序" src="/images/ico_sortaz.gif"/></a>

			</c:if>

	</c:if>

	<c:if test="${'UPD_TIME'!=p.sortCol}">
					<a href="#"
						onclick="showNumDesc('${p.currentPage}','UPD_TIME');"
						id="descId">更新时间<img alt="排序" src="/images/ico_sort.gif"/></a>

	</c:if>
    </th>
    </tr>
    <tbody>
    <tr>
				<td>
				</td>
				<td><a href="/ad.do?action=adList&amp;adGroupId=0">未分组</a><span class="gray">(${defaultAdCout})</span></td>
				<td>
				</td>
				<td></td>
				<td></td>
				<td class="r"></td>
				<td class="r"></td>
				<td class="r"></td>
				<td class="r"></td>
				<td class="r"></td>
			</tr>
    <c:forEach items="${p.groupList}" var="CoreAdGroup" varStatus="s">
			<tr>
				<td>
					<input type="checkbox" id="checkbox${s.count}" name="checkbox1" alt="${CoreAdGroup.adGroupId}" class="checkboxadid"/>
				</td>
				<td>
					<input type="hidden" value="总个数:${CoreAdGroup.cnt},suspendY:${CoreAdGroup.suspendY},suspendN:${CoreAdGroup.suspendN}"/>

					<c:if test="${CoreAdGroup.suspendN==CoreAdGroup.cnt&&CoreAdGroup.cnt!=0}">
					<img width="16" height="16" align="absmiddle" title="${CoreAdGroup.suspendN}个停用" alt="广告组" src="images/ico_pic_stop.gif"/><a href="/ad.do?action=adList&adGroupId=${CoreAdGroup.adGroupId}"><airad:cutString size="10" value="${CoreAdGroup.adGroupName}" mark="..."/></a><span class="gray">(${CoreAdGroup.cnt})</span>
					</c:if>
					<c:if test="${CoreAdGroup.suspendY==CoreAdGroup.cnt&&CoreAdGroup.cnt!=0}">
					<img width="16" height="16" align="absmiddle" title="${CoreAdGroup.suspendY}个运行" alt="广告组" src="images/ico_pic_play.gif"/><a href="/ad.do?action=adList&adGroupId=${CoreAdGroup.adGroupId}"><airad:cutString size="10" value="${CoreAdGroup.adGroupName}" mark="..."/></a><span class="gray">(${CoreAdGroup.cnt})</span>
					</c:if>
					<c:if test="${CoreAdGroup.suspendN!=CoreAdGroup.cnt&&CoreAdGroup.suspendY!=CoreAdGroup.cnt&&CoreAdGroup.cnt!=0}">
					<img width="16" height="16" align="absmiddle" title="${CoreAdGroup.suspendN}个停用,${CoreAdGroup.suspendY}个运行" alt="广告组" src="images/ico_pic_pau.gif"/><a href="/ad.do?action=adList&adGroupId=${CoreAdGroup.adGroupId}"><airad:cutString size="10" value="${CoreAdGroup.adGroupName}" mark="..."/></a><span class="gray">(${CoreAdGroup.cnt})</span>
					</c:if>
					<c:if test="${CoreAdGroup.cnt==0}">
					<img width="16" height="16" align="absmiddle" title="没有广告" alt="没有广告" src="images/ico_act.gif"/><a href="/ad.do?action=adList&adGroupId=${CoreAdGroup.adGroupId}"><airad:cutString size="10" value="${CoreAdGroup.adGroupName}" mark="..."/></a><span class="gray">(${CoreAdGroup.cnt})</span>
					</c:if>
				</td>
				<td>
				<c:if test="${CoreAdGroup.adLoclType==0}">
					全国
				</c:if>
				<c:if test="${CoreAdGroup.adLoclType==1}">
					常用地区
				</c:if>
				<c:if test="${CoreAdGroup.adLoclType==2}">
					精确到区
				</c:if>
				</td>
				<td>
				<c:forEach items="${p.industryInvolved}" var="dictionary">
						<c:if test="${dictionary.dictKey==CoreAdGroup.adTagSoftType}">${dictionary.dictVal}</c:if>
			     </c:forEach>
				</td>
				<td>
				<c:if test="${'1,0'==(CoreAdGroup.adTagSp)}">
					iPhone,Android
				</c:if>
				<c:if test="${'1'==(CoreAdGroup.adTagSp)}">
					Android
				</c:if>
				<c:if test="${CoreAdGroup.adTagSp=='0'}">
					iPhone
				</c:if>
				</td>
				<td class="r"><fmt:formatNumber type="number" value="${CoreAdGroup.showNum}"/></td>
				<td class="r"><fmt:formatNumber type="number" value="${CoreAdGroup.clickNum}"/></td>
				<td class="r"><fmt:formatNumber type="number" value="${CoreAdGroup.clickRate*100}" pattern="###.##"/>%</td>
				<td class="r"><sup>&yen;</sup><fmt:formatNumber type="number" value="${CoreAdGroup.cost}" pattern="###,##0.00"/></td>
				<td class="r"><fmt:formatDate value="${CoreAdGroup.updTime}"type="both" pattern="yyyy-MM-dd" /></td>
			</tr>
		</c:forEach>
		<c:if test="${empty p.groupList}">
        	<tr><td colspan="9"><div class="c">暂无数据</div></td></tr>
    	</c:if>
  </tbody>
	</table>
	 <airad:pagination pageSize="${p.pageSize}" href="/adGroup.do?action=list&currentPage=PAGENUM&adGroupName=${p.adGroupName}"
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
<li><strong>展示次数：</strong>本组广告被展示的总次数；<strong>点击数：</strong>活动中，本组广告获得点击的总次数；<strong>花费：</strong>该广告组实际消费的广告费用。</li>
<li><strong>点击率：</strong>点击数/广告展示次数*100%。</li>
<!--<li><strong>平均每次点击金额：</strong>您为每次点击支付的平均价格。例如，如果您在应用程序投放的广告获得两次点击，一次费用为 &yen;0.20，一次为 &yen;0.40，则平均费用为 &yen;0.30。</li>-->
</ul>
</div>
</div>
</div>
<div style="top:100px;left:200px;width:400px" id="popDiv" class="popDiv collapsed">
<h2><img class="fr" id="closePop" alt="关闭" src="images/ico_popclose.gif">修改投放平台</h2>
<div class="popCon">
<form method="post" action="adGroup.do?action=batchEdit" name="myfrm" id="command">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" style="margin-bottom:0" class="tabNF">
	<colgroup><col width="35%">
	<col width="65%">
	  </colgroup><tbody><tr>
	    <th>投放平台</th>
	    <td>
	    <c:forEach items="${p.arr}" var="arr">
			<label><input type="checkbox" id="CheckboxGroup1_1" name="coreAdGroup.adTagSp" value="${arr.dictKey}"/> ${arr.dictVal}</label>
		</c:forEach>
	  </tr>
	  <tr>
	    <th>&nbsp;</th>
	    <td><button id="btnModOsSubmit" class="btnBY">提交</button></td>
	  </tr>
	</tbody></table>
</form>
</div>
</div>

<div style="top:100px;left:200px;width:400px" id="popIndustryDiv" class="popDiv collapsed">
<h2><img class="fr" id="closeIndustryPop" alt="关闭" src="images/ico_popclose.gif">修改所属行业</h2>
<div class="popCon">
<form method="post" action="adGroup.do?action=batchModIndustry" name="modIndustryForm" id="modIndustryId">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" style="margin-bottom:0" class="tabNF" id="industryTable">
	<col width="15%"><col width="65%">
      <tr>
	    <th>所属选择</th>
	    <td>
	    <c:forEach items="${p.industryInvolved}" var="dictionary">
						<c:if test="${dictionary.dictKey==CoreAdGroup.adTagSoftType}">${dictionary.dictVal}</c:if>
						<label class="fl" style="height:20px;"><input type="radio" name="coreAdGroup.adTagSoftType" value="${dictionary.dictKey}"> ${dictionary.dictVal}</input></label>
		</c:forEach>
	  	</td>
	  </tr>
	  <tr>
	    <th>&nbsp;</th>
	    <td><button id="btnModIndustrySubmit" class="btnBY">提交</button></td>
	  </tr>
</table>
</form>
</div>
</div>

<div style="top:100px;left:200px;width:600px" id="popLoclTypeDiv" class="popDiv collapsed">
<h2><img class="fr" id="closeLoclTypePop" alt="关闭" src="images/ico_popclose.gif">修改投放地区</h2>
<div class="popCon">
<form:form method="post" action="adGroup.do?action=batchModLoclType" name="modLoclTypeForm" id="modLoclTypeId">
	<input id="exact" name="exact" type="hidden" />
	<table width="100%" cellspacing="0" cellpadding="0" border="0" style="margin-bottom:0" class="tabNF" id="loclTypeTable">
	<col width="15%"><col width="65%">
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
				items="${p.proListBean}" var="DictionaryGlobalRegion"
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
	    <th>&nbsp;</th>
	    <td><button id="btnModLoclTypeSubmit" class="btnBY">提交</button></td>
	  </tr>
</table>
</form:form>
</div>
</div>


<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script language="javascript" type="text/javascript" src="js/group.js"></script>
<script type="text/javascript">
$(function(){
    $(document).keydown(function(event){
      if(event.keyCode == 13){
    	  if($("#popLoclTypeDiv").attr("style").indexOf("display: block") >-1){
        	  return $("#btnModLoclTypeSubmit").click();
        }else if($("#popIndustryDiv").attr("style").indexOf("display: block")>-1){
        	return $("#btnModIndustrySubmit").click();
        }else if($("#popDiv").attr("style").indexOf("display: block")>-1){
        	return $("#btnModOsSubmit").click();
        }
      }
    });
});

$(document).ready(function(){
	$(".more").live("click",function(){
									$(".more").addClass("less");
									$(".more").removeClass("more");
									$("#btns").show();
									})
	$(".less").live("click",function(){
									$(".less").addClass("more");
									$(".less").removeClass("less");
									$("#btns").hide();
									})
	/*
	$("#checkbox2").click(function(){
			   var isc=$(this).attr('checked');
			   if(isc){
									$(".more").addClass("less");
									$(".more").removeClass("more");
									$("#btns").show();
									}else{
									$(".less").addClass("more");
									$(".less").removeClass("less");
									$("#btns").hide();
				   		}
						});
									*/
	$("#btn01").click(function(){
									$(".popBg").show();
									$("#popDiv").fadeIn();
									})
	$("#closePop,#btn02").click(function(){
									$(".popBg").hide();
									$("#popDiv").hide();
									})
	$("#closeLoclTypePop").click(function(){
									$(".popBg").hide();
									$("#popLoclTypeDiv").hide();
									})

	$("#closeIndustryPop").click(function(){
									$(".popBg").hide();
									$("#popIndustryDiv").hide();
									});



	$("#btn02").click(function(){
									$(".okBox").show();
									})
})
</script>
<script>
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
<script type="text/javascript"
	src="/js/tree/js/jquery.simple.tree.self.js"></script>
</body>
</html>
