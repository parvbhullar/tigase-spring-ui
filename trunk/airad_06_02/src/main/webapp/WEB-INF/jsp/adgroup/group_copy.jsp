<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@page import="com.mitian.airad.web.form.AdGroupForm"%>
<%@page import="com.mitian.airad.model.CoreCampaign"%>
<%@page import="org.apache.commons.lang.time.DateFormatUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告组复制</title>
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
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 开发嵌入start-->
<form:form action="adGroup.do?action=copy" commandName="command"  method="post">
<h1><span>广告管理 &raquo; <a href="campaign.do?action=listCampaign">活动管理</a> &raquo;
<a href="adGroup.do?action=selectAll&campaignId=${campaign.campaignId }"><img src="images/ico_act.gif" alt="活动"
  width="16" height="16" align="absmiddle" />${campaign.campaignName }</a> &raquo;</span> <img
  src="images/ico_gup.gif" alt="广告组" width="16" height="16"
  align="absmiddle" />${command.coreAdGroup.adGroupName}</h1>
<div class="leftCon">
<h1>复制广告组<span class="fr">(此功能暂未实现广告复制)</span></h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
  <col width="25%" />
  <col width="75%" />
  <tr>
    <th>广告组名称</th>
    <td><input name="coreAdGroup.adGroupName" class="long" value="${command.coreAdGroup.adGroupName }_copy" onkeyup="valueAlert(this,'adGroupNamesDiv')" />
    <span style="color: red;">&nbsp;*&nbsp;</span>
    <form:hidden path="coreAdGroup.adGroupId" />
    <form:hidden path="coreAdGroup.adTagType" />
    <form:hidden path="coreAdGroup.campaignId" />
    <input type="hidden" name="campaignId" value="${command.coreAdGroup.campaignId }" />
    </td>
  </tr>
    <tr>
    <th>地理位置</th>
    <td><form:radiobutton path="coreAdGroup.adLoclType" value="0"  onclick="valueAlert1(this,'adLoclInfoDiv')"  />全国
    <form:radiobutton path="coreAdGroup.adLoclType" value="2" onclick="valueAlert1(this,'adLoclInfoDiv')"  /> 精确到区
    <form:radiobutton path="coreAdGroup.adLoclType" value="1" onclick="showAdLoclInfoSp(this,true)" />快捷方式选择
    <div id="adLoclInfoShowSp" style="display: none">
   <label>地区：</label>
   <form:checkbox path="coreAdGroup.adLoclInfo" value="0" title="长三角" label="长三角" onclick="showAdLoclInfoSp(this,false)" />
   <form:checkbox path="coreAdGroup.adLoclInfo" value="1" title="珠三角" label="珠三角" onclick="showAdLoclInfoSp(this,false)" />
   <form:checkbox path="coreAdGroup.adLoclInfo" value="2" title="环渤海" label="环渤海" onclick="showAdLoclInfoSp(this,false)" />
    </div>
    </td>
  </tr>
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
  <tr>
    <th>投放软件类型</th>
    <td>
      <form:select path="coreAdGroup.adTagSoftType">
      <form:option value="0">游戏</form:option>
      <form:option value="1">多媒体</form:option>
      <form:option value="2">生活</form:option>
      <form:option value="3">阅读</form:option>
      <form:option value="4">工具</form:option>
      <form:option value="5">通讯</form:option>
      <form:option value="6">系统管理</form:option>
      <form:option value="7">娱乐休闲</form:option>
      <form:option value="8">新闻资讯</form:option>
      <form:option value="9">图片壁纸</form:option>
    </form:select>
    </td>
  </tr>
  <tr>
    <th>平台/设备</th>
    <td>
    <div class="selectBox">
    <table>
      <tr>
        <td>iphone 操作系统</td>
        <td>最低&nbsp<select name="select1" style="width: 100px">
          <option selected="selected">2.0</option>
          <option>3.0</option>
        </select></td>
      </tr>
      <tr>
        <td>Android 操作系统</td>
        <td>最低&nbsp<select name="select2" style="width: 100px">
          <option selected="selected">2.0</option>
          <option>3.0</option>
        </select></td>
      </tr>
      <tr>
        <td>Windows Phone操作系统</td>
        <td>最低&nbsp<select name="select3" style="width: 100px">
          <option selected="selected">Windows7</option>
        </select></td>
      </tr>
    </table>
    </div>
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
    <td align="center" colspan="2">
    <input type="submit" value="复制" />&nbsp&nbsp&nbsp&nbsp<input type="button" onclick="javascript:history.go(-1)" value="取消" />
    </td>
  </tr>
</table>
</div>
<div class="rightCon">
<div class="infoCon">
<h2><img src="images/ico_act.gif" alt="活动" width="16" height="16"
  align="absmiddle" />活动摘要</h2>
<ul>
  <li>名称<span class="fr">${campaign.getCampaignName() }</span></li>
  <li><span class="fr sml"><%=startTime %></span>开始时间</li>
  <li><span class="fr sml">${campaign.getBuggetDay() } <sup>&yen;</sup></span>每日预算</li>
  <li><span class="fr sml"><c:choose><c:when test="${campaign.pubKind }=='2'">加速</c:when><c:otherwise>正常</c:otherwise> </c:choose>  </span>投放方式</li>
</ul>
<h2><img src="images/ico_gup.gif" alt="活动" width="16" height="16"
  align="absmiddle" />广告组摘要</h2>
<ul>
  <li><span class="fr"><div style="word-wrap: break-word; word-break: overflow : hidden;"
    id="adGroupNamesDiv">${command.coreAdGroup.adGroupName}</div></span>
  名称</li>
  <li><span class="fr">4个操作系统/8个设备</span>平台/设备<br />
  <span></span></li>
  <li><span class="fr"><div align="center" style="word-wrap:break-word;word-break:overflow:hidden;" id="adLoclInfoDiv">
<c:choose><c:when test="${command.coreAdGroup.adLoclType=='0' }">全国</c:when>
<c:when test="${command.coreAdGroup.adLoclType=='1' }">快捷方式选择</c:when>
<c:when test="${command.coreAdGroup.adLoclType=='2' }">精确到区</c:when></c:choose>
</div></span>地理位置</li>
</ul>
</div>
</div>
</form:form>
</div>
<!-- 开发嵌入end--></div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>