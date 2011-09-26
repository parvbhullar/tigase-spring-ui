<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>活动报表添加</title>
  </head>
  <body>
  <%@ include file="/WEB-INF/jspf/header.jsp" %>
  <div id="main">
    <div class="mainCon">
      <%@ include file="/WEB-INF/jspf/errors.jsp" %>
<!-- 程序开始 -->    
<div class="leftCon">
<h1>活动报告</h1>
<form:form action="campaign.do?action=doAdd" method="post" commandName="command">
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <col width="40%" />
        <col width="60%" />
       <tr>
          <td><form:radiobutton value="0" path="report.reportType" label="活动报告 *" checked="true"/>
          <form:radiobutton value="1" path="report.reportType" label="广告组报告 *"/>
          <form:radiobutton value="2" path="report.reportType" label="广告报告"/>
          </td>
          <td>
           <input type="radio" value="preset" name="date_type" checked="">
				    <form:select path="presetValue">
				      <form:option value="" label="选择日期范围"/>
				      <form:option value="today" label="今天"/>
				      <form:option value="yesterday" label="昨天"/>
				      <form:option value="last7" label="过去7天"/>
				      <form:option value="last30" label="过去 30天"/>
				      <form:option value="prevmonth" label="上一个月"/>
				      <form:option value="prevquarter" label="上一季度"/>
				      <form:option value="ytd" label="本月开始到现在"/>
				      <form:option value="qtd" label="本季开始到现在"/>
				    </form:select>
          </td>
        </tr>
        <tr>
          <td>
          <form:input path="campaignName"/>
          </td>
          <td>
           <label>选择日期范围:</label>
				    <form:input path="report.reportStartTime" onfocus="showdate(this);" class="cal"/>-
				    <form:input path="report.reportEndTime" onfocus="showdate(this);" class="cal"/>
          </td>
        </tr>
        <tr>
          <td>
          <div style="height: 250;OVERFLOW-Y:auto">
            <c:forEach items="${form.reportList}" var="CoreCampaign">
              <c:if test="${CoreCampaign.delFlag!=1}">
              <form:checkbox path="reportListId" value="${CoreCampaign.campaignId}" label="${CoreCampaign.campaignName}"/><br>
              </c:if>
              <c:if test="${CoreCampaign.delFlag==1}">
              <form:checkbox path="reportListId" value="${CoreCampaign.campaignId}" label="${CoreCampaign.campaignName}(已删除)"/><br>
              </c:if>
            </c:forEach>
          </div>
          </td>
          <td>
          <label class="evenlySpace" for="object_dimension">报告类型:</label>
           <form:select path="report.reportStatus">
            <form:option value="0" label="汇总报告"/>
            <form:option value="1" label="详细报告" selected="selected"/>
           </form:select>
          </td>
        </tr>
        <tr>
        <td>
          <form:checkbox path="report.reportFlag" value="0" label="显示已删除的活动、广告组和广告" checked="checked"/>
        </td>
        <td></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>
          <div class="btnBox">
           <a href="javascript:document.forms[0].submit()" class="btnY fl"><span>创建报表</span></a><div class="moreBtn"><span class="gray">|</span> <a href="javascript:history.go(-1)">取消</a></div>
           </div>
          </td>
        </tr>
      </table>
 </form:form>
</div>


</div>
         
    </div>
  </div>
  <%@ include file="/WEB-INF/jspf/footer.jsp" %>
  </body>
</html>