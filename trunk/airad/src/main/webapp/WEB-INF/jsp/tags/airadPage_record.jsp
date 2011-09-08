<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@ taglib prefix="airad" uri="http://www.airad.com/tlds/airad-html-common"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>活动列表</title>
    <script type="text/javascript" src="js/Calendar/Calendar.js"></script>
     <script type="text/javascript">
       function select(){
           document.myform.submit();
      }
     </script>
     <style>
/*Page*/
.pager{margin:20px 0;left:50%;float:left;position:relative;height:25px;font-weight:bold}
.pager div{left:-50%;position:relative}* html .pager div{float:left}/*forCenter*/
.pager a,.pager .curP,.pager .sumP{float:left;font-size:12px;height:18px;line-height:18px}
.pager a,.pager .curP{margin:0 3px;overflow:hidden;padding:0px 5px;text-align:center;vertical-align:middle}
.pager a{border:1px solid #CCC}
.pager a:hover{text-decoration:none;border:1px solid red}
.pager .sumP,.pager .ppre ,.pager .pnext{font-size:12px;background-repeat:no-repeat}
.pager .sumP{padding-top:2px;font-weight:normal}
.pager strong{padding:0px 5px}
.pager .ppre{background-image:url(/images/ico_pager_pre.gif);background-position:left center;padding-left:13px}
.pager .pnext{background-image:url(/images/ico_pager_next.gif);background-position:right center;padding-right:13px}
.pager .curP{color:#FFF;background:#ff4b01;border:1px solid red;cursor:default}
.pager .moreP{float:left;padding:0 6px}
</style>
    <%@ include file="/WEB-INF/jspf/js_and_css.jsp"%>
  </head>
  <body>
  <%@ include file="/WEB-INF/jspf/header.jsp" %>
  <div id="main">
    <div class="mainCon">
      <%@ include file="/WEB-INF/jspf/errors.jsp" %>
      <!-- 开发嵌入start-->
<div class="mainCon">
<div class="wa">只有在您提供会员信息并通过审核之后，您的广告才可以被发布。请 <a href="#"><strong>完善会员信息</strong></a> 。</div>
<a class="btnY fr" href="/campaign.do?action=add"><span>新建广告活动</span></a>
<h1>广告管理 <span>» 活动管理</span></h1>
<form action="/campaign.do?action=querybycontion" method="post" name="myform">
<div class="searchBox">
<div class="timeBox">
<table cellspacing="0" cellpadding="0" border="0">
  <tbody><tr>
    <td>时间 <input type="text" name="start" id="start" onFocus="showdate(this);" class="cal"/> - 
    <input type="text" name="end" id="end" onFocus="showdate(this);" class="cal"/></td>
    <td><a class="btnS fl" href="javascript:void(0)" onClick="select();"><span>查询</span></a></td>
  </tr>
</tbody></table>
</div>
<div class="search">名称 <input name="campaignName" id="campaignName"></div>
</div>
<table cellspacing="0" cellpadding="0" border="0" class="tabYH">
<!--  
<tr>
<td>活动名称</td><td colspan="2"><input /></td>
  
<td colspan="2">创建时间区间</td>
<td colspan="3">
  <select name="">
    <option value="">---请选择---</option>
    <option value="">今天</option>
    <option value="">昨天</option>
    <option value="">过去7天</option>
    <option value="">过去30天</option>
    <option value="">上一个月</option>
    <option value="">上一个季度</option>
    <option value="">本月开始到现在</option>
    <option value="">本季开始到现在</option>
  </select>
</td>
<td>开始时间</td><td><input type="text" name="start" id="start" onfocus="c.showMoreDay = false;c.show(this);" /></td>
<td>结束时间</td><td><input type="text" name="end" id="end" onfocus="c.showMoreDay = false;c.show(this);" /></td>
<td colspan="2"><input type="submit" value="查询"/></td>
</tr> -->
<col><col width="12%"><col width="20%"><col width="8%"><col width="12%"><col width="10%"><col width="10%"><col width="12%"><col width="10%">
  <tbody><tr>
    <th>活动名称</th>
    <th>创建时间</th>
    <th>广告组数量</th>
    <th>预算</th>
    <th>平均每次点击费用（CPC）</th>
    <th>广告展示次数</th>
    <th>点击数</th>
    <th>点击率（CTR）</th>
    <th>成本</th>
    </tr>
    <c:forEach items="${p.list}" var="corecampaign">
  <tr>
    <td><a href="#"><img height="16" width="16" align="absmiddle" alt="活动" src="images/ico_act.gif">${corecampaign.campaignName }</a>
    <div class="ctl">
    <a class="btnS" href="/campaign.do?action=update&campaignId=${corecampaign.campaignId}"><span>编辑</span></a>
    <a class="btnS" href="/campaign.do?action=doCopy&campaignId=${corecampaign.campaignId}"><span>复制</span></a><span class="gray">|</span>
    <a href="/campaign.do?action=doSuspend&campaignId=${corecampaign.campaignId}">停用</a>
    <a href="/campaign.do?action=doDel&campaignId=${corecampaign.campaignId}">删除</a></div>
    </td>
    <td><fmt:formatDate  value="${corecampaign.addTime }" type="both" pattern="yyyy-MM-dd HH:mm" /></td>
    <td>0</td>
    <td><sup>¥</sup>${corecampaign.buggetDay==null?" - ":corecampaign.buggetDay }</td>
    <td><sup>¥</sup>0.00</td>
    <td>0</td>
    <td>0</td>
    <td>0.0% </td>
    <td>0</td>
  </tr>
  </c:forEach>  
  </tbody></table>
<airad:pagination pageSize="${p.pageSize}" href="/page.do?page=index&currentPage=PAGENUM"
totalRecord="${p.totalCount}" currentPage="${p.currentPage}"></airad:pagination>
</form>

</div>
</div>
      <!-- 开发嵌入end-->      
    </div>
  </div>
  <%@ include file="/WEB-INF/jspf/footer.jsp" %>
  </body>
</html>
