<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>

<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
	<col width="30%" />
    <col width="70%" />
  <c:if test="${not empty form.report.reportId}">
  <tr>
    <th>报表名称</th>
    <td><form:input path="report.reportName" maxlength="40" id="reportName"/></td>
  </tr>
  </c:if>
  <tr>
    <th>类型</th>
    <td><!-- 活动 ，广告组，广告-->
       <c:choose>
          <c:when test="${form.flag==0}">
          <c:if test="${empty form.report.reportId}">
              <form:radiobutton value="0" path="report.reportType" label="活动报告 " checked="true" onclick="loadData(this)"/>
          </c:if>
          <c:if test="${not empty form.report.reportId}">
            <form:radiobutton value="0" path="report.reportType" label="活动报告 " onclick="loadData(this)"/>
          </c:if>
			      <form:radiobutton value="1" path="report.reportType" label="广告组报告 " onclick="loadData(this)"/>
			      <form:radiobutton value="2" path="report.reportType" label="广告报告" onclick="loadData(this)"/>
          </c:when>
          <c:when test="${form.flag==1}">
              <!-- 应用程序 -->
              <form:radiobutton value="3" path="report.reportType" label="应用程序报告" checked="true" />
          </c:when>
          <c:when test="${form.flag==2}">
             <!-- 代理商 -->
             <form:radiobutton value="4" path="report.reportType" label="广告商报告 " checked="true"/>
          </c:when>
       </c:choose></td>
  </tr>
  <tr>
    <th rowspan="2">时间</th>
    <td><form:radiobutton path="report.dateType" value="1" checked="true" onclick="chooseDate(this);"/>
        <form:select path="presetValue">
        <!-- <form:option value="1" label="今天"/>  不统计当天的-->
          <form:option value="9" label="今日统计"/>
          <form:option value="2" label="昨天"/>
          <form:option value="3" label="过去7天" />
          <form:option value="4" label="过去 30天"/>
          <form:option value="5" label="上一个月"/>
          <form:option value="6" label="上一季度"/>
          <form:option value="7" label="本月开始到现在"/>
          <form:option value="8" label="本季开始到现在"/>
        </form:select></td></tr>
    <tr><td><form:radiobutton path="report.dateType" value="0" onclick="chooseDate(this);" />
       日期范围:
        <form:input path="reportStartTime" class="cal" readonly="true"/>-
        <form:input path="reportEndTime" class="cal"  readonly="true"/>
        <div class="wrBox" style="display:none" id="tstime">
          <div class="wr" id="tscnttime"></div>
       </div>
        </td></tr>
  <tr>
    <th class="evenlySpace" for="object_dimension">报表形式</th>
    <td><form:select path="report.reportStatus">
        <form:option value="0" label="汇总报告" />
        <form:option value="1" label="详细报告"/>
       </form:select></td>
  </tr>
  <c:choose>
      <c:when test="${form.flag==0}">
      <tr>
       <th>&nbsp;</th>
       <td><form:checkbox path="report.reportFlag" value="0" label="显示已删除的活动、广告组和广告" checked="true" onclick="filterDel(this)"/></td>
      </tr>
      </c:when>
       <c:when test="${form.flag==2}">
	       <tr>
	       <th>&nbsp;</th>
	       <td><form:checkbox path="report.reportFlag" value="0" label="显示已删除的广告商" checked="true" onclick="filterDel(this)"/></td>
	      </tr>
      </c:when>
    </c:choose>
  <tr>
  <c:choose>
  <c:when test="${form.flag==0}">
     <th><span class="must">*</span><span id="chooseType">选择活动</span></th>
  </c:when>
  <c:when test="${form.flag==1}">
     <th><span class="must">*</span>选择应用</th>
  </c:when>
  <c:otherwise>
     <th><span class="must">*</span>选择广告商</th>
  </c:otherwise>
  </c:choose>
    <td>
    <div class="selectBox" style="max-height:200px;overflow:auto" id="csstree">
       <c:choose>
          <c:when test="${form.flag==0}">
          <!-- 活动 ，广告组，广告-->
		      <ul id="tree0" class="simpleTree"></ul>
		      <ul id="tree1" class="simpleTree"></ul>
		      <ul id="tree2" class="simpleTree"></ul>
          </c:when>
          <c:when test="${form.flag==1}">
          <!-- 应用程序 -->
          <ul id="tree3" class="simpleTree"></ul>
          </c:when>
          <c:when test="${form.flag==2}">
          <!-- 代理商 -->
          <ul id="tree4" class="simpleTree"></ul>
          </c:when>
       </c:choose></div>
 <div class="wrBox" style="display:none" id="ts">
       <div class="wr" id="tscnt"></div>
       </div>
       </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
    <div class="btnBox"><c:choose>
         <c:when test="${empty form.report.reportId}">
            <a href="javascript:createReportNext();" class="btnY fl"><span>预览此报表</span></a><div class="moreBtn"><span class="gray">|</span> <a href="report.do?action=listReport">取消</a></div>
         </c:when>
         <c:otherwise>
            <a href="javascript:savOrUpd(this);" class="btnY fl"><span>提交</span></a><div class="moreBtn"><span class="gray">|</span> <a href="report.do?action=listReport">取消</a></div>
         </c:otherwise>
      </c:choose></div></td>
  </tr>
</table>
<script>
  $(document).ready(function(){
	  var isie=$.browser.msie;
     if(isie){//ie处理
         //width:300px;height: 350px;overflow-y: scroll;overflow-x: scroll;
         $("#csstree").css("width","400px").css("height","350px").css("overflow-y","scroll").css("overflow-x","scroll");   
      }
	});
</script>