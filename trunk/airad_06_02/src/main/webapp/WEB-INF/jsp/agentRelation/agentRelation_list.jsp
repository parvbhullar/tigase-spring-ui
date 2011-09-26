<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告商管理</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 开发嵌入start-->
<div class="mainCon">
<form:form action="agentRelation.do?action=agencyList" commandName="command" method="post" name="agentSortform">
<form:hidden path="currentPage"/>
<form:hidden path="coreAgentRelation.email"/>
<form:hidden path="asce"/>
</form:form>

<form:form name="myfrm" id="myfrm"  method="post">
<form:hidden path="currentPage"/>
<div align="right" style="padding-right: 10px;"><a href="javascript:sureInvitationCode();">邀请码生成</a>
</div >
</form:form>
<h1>代理商 <span>» 广告商列表</span> </h1> 

<div class="searchBox">
  <div class="timeBox">
  <form:form action="agentRelation.do?action=agencyList" commandName="command" name="agentStatisticform"  method="post">
   <form:hidden path="timeSlotFlag"/>
   <form:hidden path="currentPage"/>
    <table cellspacing="0" cellpadding="0" border="0">
      <tbody><tr>
        <td><form:input path="startTime" class="cal" readonly="true"/>
        - <form:input  path="endTime" class="cal" readonly="true"/></td>
        <td><a class="btnS fl" href="javascript:agentTimeSlotSubmit();" ><span>查询</span></a></td>
      </tr>
    </tbody></table>
    </form:form>
  </div>

  <div class="search">
    <form:form action="/agentRelation.do?action=agencyList" method="post" name="agentSearchform" commandName="command">
      <form:hidden path="currentPage"/>
      <form:input path="coreAgentRelation.email"/>
    </form:form>
  </div>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="tabYH">
    <col width=""/>
    <col width="15%"/>
    <col width="15%"/>
    <col width="15%"/>
    <col width="10%"/>
    <tbody>
        <tr>
            <th>广告商名称</th>
            <c:if test="${null==flag}">
            <th><a href="javascript:agentDesc();" id="descId">广告商创建时间<img alt="降序" src="/images/ico_sortza.gif"></a>
            </th>
            </c:if>
            <c:if test="${null!=flag}">
            <th><a href="javascript:agentAsce();" id="asceId">广告商创建时间<img alt="升序" src="/images/ico_sortaz.gif"></a>
            </th>
            </c:if>
            <th>广告商广告展示次数</th>
            <th>广告商广告点击次数</th>
            <th>代理商收入</th>
        </tr>
            <c:forEach items="${p.memberInfoList}" var="CoreMemberInfo" varStatus="s">
            <tr onmouseover="showButton('d${s.count}');" onmouseout="closeButton('d${s.count}');">
                <td>
                    <img height="16" width="16" align="absmiddle" alt="广告商" src="images/ico_act.gif"/>
                    <airad:cutString size="10" value="${CoreMemberInfo.email}" mark="..."/>
                    <div class="ctl" id="d${s.count}" style="display: none">
                      <a class="btnS" href="javascript:deleteAgentRelation('${CoreMemberInfo.relationId}');"><span>关系解除</span></a>
                    </div>
                </td>
                <td><fmt:formatDate value="${CoreMemberInfo.activeTime}"
                    type="both" pattern="yyyy-MM-dd HH:mm" /></td>
                <td>${CoreMemberInfo.showNum}</td>
                <td>${CoreMemberInfo.clickNum}</td>
                <td><fmt:formatNumber type="number" value="${CoreMemberInfo.offer}" pattern="#,##0.00"/></td>
            </tr>
        </c:forEach>
    </tbody>
</table> 
<airad:pagination 
pageSize="${p.pageSize}"
 href="/agentRelation.do?action=agencyList&currentPage=PAGENUM&coreAgentRelation.email=${email}&desc=${desc}&asce=${asce}&timeSlotFlag=${p.timeSlotFlag}&startTime=${p.startTime}&endTime=${p.endTime}"
totalRecord="${p.totalCount}"
 currentPage="${p.currentPage}">
 </airad:pagination>


</div> 
<!-- 开发嵌入end--> 
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script type="text/javascript" src="/js/agentRelation.js"></script>
<script>
document.onkeypress=agentSearchSubmit;
</script>
</body>
</html>
