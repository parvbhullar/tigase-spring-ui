<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>应用程序列表</title>
<script type="text/javascript" src="script/Calendar/Calendar.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>
<div id="main">
<div class="mainCon">
<div class="okBox">
<div class="ok" id="okTip" style="display: none">
操作成功
</div>
</div>
<%@ include file="/WEB-INF/jspf/errors.jsp"%>
<a class="btnY fr" href="/app.do?action=add&currentPage=${p.currentPage}"><span>新建应用程序</span></a>
<h1>应用程序管理
<small> <a href="${p.iphoneDownLoad}">iPhone SDK下载</a> <span class="gray">|</span> <a href="${p.androidDownLoad}">Android SDk下载</a>
    </small></h1>
<div class="searchBox">
<form:form action="app.do?action=listApp" method="post"
	commandName="command" name="myform" id="myform">
<form:hidden path="timeSlotFlag"/>
<form:hidden path="currentPage"/>
<form:hidden path="asce" />
        <table cellspacing="0" cellpadding="0" border="0">
        <tr>
        <td class="search"> <form:input path="app.appName" /></td>
        <td class="timeBox"><form:input path="startTime" class="cal" readonly="true"/>
            - <form:input  path="endTime" class="cal" readonly="true"/></td>
            <td><a class="btnS fl" href="javascript:document.myform.submit();"><span>查询</span></a></td>
        </tr>
      </table>
</form:form>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="tabYH">
	<col />
	<col width="8%"/>
	<col width="10%"/>
	<col width="8%"/>
	<col width="8%"/>
	<col width="8%"/>
  <col width="8%"/>
  <col width="8%"/>
  <col width="10%"/>
  <col width="8%"/>
	<tbody>
		<tr>
			<th>应用名称</th>
			<th>适用平台</th>
			<!--  
			<th>应用状态</th>
			-->
			<c:if test="${null==flag}">
			<th>
			 <a href="javascript:appDesc();" id="descId">添加时间<img alt="降序" src="/images/ico_sortza.gif"></a>
			</th>
			</c:if>
			<c:if test="${null!=flag}">
			<th>
       <a href="javascript:appAsce();"  id="asceId">添加时间<img alt="升序" src="/images/ico_sortaz.gif"></a>
      </th>
      </c:if>
			<th>收入</th>
			<th>请求数</th>
			<th>展示数</th>
			<th>点击数</th>
			<th>点击率</th>
			<th>广告投放率</th>
			<th>状态</th>
		</tr>
		<c:if test="${not empty p.listBean}">

    <c:forEach items="${p.listBean}" var="CoreApp" varStatus="s">
		  <tr onmouseover="showButton('d${s.count}')" onmouseout="closeButton('d${s.count}');">
		    <td><a href="/app.do?action=edit&appId=${CoreApp.appId}&currentPage=${p.currentPage}">
		    <img height="16" width="16" align="absmiddle" alt="应用" src="images/ico_act.gif"/>
		    <airad:cutString size="20" value="${CoreApp.appName}" mark="..."/>
       </a>
		    <div class="ctl" id="d${s.count}" style="display:none">
		    <c:if test="${CoreApp.blocking!=1}">
		    <a class="btnS" href="/app.do?action=edit&appId=${CoreApp.appId}&currentPage=${p.currentPage}"><span>编辑</span></a>
		    <span class="gray">|</span>
		    <!-- 1发布，0暂停 -->
		      <c:if test="${CoreApp.appStatus==0}">
		        <a href="javascript:stopandsendApp('${CoreApp.appId}','1');">运行</a>
		      </c:if>
		      <c:if test="${CoreApp.appStatus==1}">
		        <a href="javascript:stopandsendApp('${CoreApp.appId}','0');">停用</a>
		      </c:if>
		     </c:if>
		    </div>
		    
		    </td>
		    
		    <c:if test="${CoreApp.appPlatformType=='0'}">
        <td>iPhone</td>
        </c:if>
        <c:if test="${CoreApp.appPlatformType=='1'}">
        <td>Android</td>
        </c:if>
		    <td><fmt:formatDate  value="${CoreApp.addTime}" type="both" pattern="yyyy-MM-dd" /></td>
		    <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${CoreApp.offer}" pattern="###,##0.00"/></td>
		    <td><fmt:formatNumber type="number" value="${CoreApp.maxNum}"/></td>
		    <td><fmt:formatNumber type="number" value="${CoreApp.showNum}"/></td>
		    <td><fmt:formatNumber type="number" value="${CoreApp.clickNum}"/></td>
		    <td><fmt:formatNumber type="number" value="${CoreApp.clickRate*100}" pattern="###.##"/>%</td>
		    <td><fmt:formatNumber type="number" value="${CoreApp.putRate*100}" pattern="###.##"/>%</td>
		    
		    <c:if test="${CoreApp.appStatus==0 and CoreApp.blocking==0}">
           <td><span class="red">停用</span></td>
        </c:if>
		    <c:if test="${CoreApp.appStatus==1 and CoreApp.blocking==0}">
           <td><span class="green">运行中</span></td>
        </c:if>
        <c:if test="${CoreApp.blocking==1}">
           <td><span class="red">冻结</span></td>
        </c:if>
		  </tr>
		  </c:forEach>
		 </c:if>
		  <c:if test="${empty p.listBean}">
		  <td colspan="9">
		    <div class="c">暂无数据</div>
		  </td>
		  </c:if>
	</tbody>
</table>
<airad:pagination pageSize="${p.pageSize}" href="/app.do?action=listApp&currentPage=PAGENUM&app.appName=${p.appName}&desc=${desc}&asce=${asce}&timeSlotFlag=${p.timeSlotFlag}
&startTime=${p.startTime}&endTime=${p.endTime}"
totalRecord="${p.totalCount}" currentPage="${p.currentPage}"></airad:pagination>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script type="text/javascript"">
	document.onkeypress=key;
	function showSuccess(){
		//让层5秒后消失
		  $("#okTip").show();
		  $("#okTip").animate({
		      opacity: 1.0
		  }, 5000).fadeOut("slow", function() {
		      $(this).hide();
		  });
	}
	  $(document).ready(function(){
	       $("#startTime").datepick();
	       $("#endTime").datepick();
	    	 addCss("listApp");
	  });
</script>
</body>
</html>
