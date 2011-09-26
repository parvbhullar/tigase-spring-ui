<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>个人中心</title>
<script>
function toBeDev()
{
	if(true==window.confirm("您确认要成为开发者吗?"))
	{
		jQuery.post("member.do?action=toBeDev",null,function(d){
			   var data=d;
			  window.location="/personal.do?action=index&platformType="+$("#platformType").val();
			});
	}
}
</script>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<form:hidden path="command.platformType" />
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<c:if test="${advertiserMessage=='ok'}">
	<div class="okBox">
	<div class="ok" style="display: none" id="okTip">认证成功。</div>
	</div>
	<script>
//让层5秒后消失
$("#okTip").show();
$("#okTip").animate({opacity: 1.0}, 5000).fadeOut("slow", function() {$(this).hide();});
<%request.getSession().removeAttribute("advertiserMessage");%>
</script>
</c:if>
<!-- 冻结提醒 --> <c:if test="${form.coreMemberInfo.forzen}">
	<div class="wa"><strong>您的角色已被冻结</strong> ，如有疑问请<a
		href="mailto:contact@airad.com">联系客服</a></div>
</c:if> <!--- 开发嵌入start---> <form:form action="" method="post"
	commandName="command">
</form:form>
<div class="leftCon" style="height: 136px; width: 578px">
<h1 style="margin-bottom: 15px" class="fl">你好，${form.coreMemberInfo.email
}</h1>
<!-- 身份认证提醒 --> <c:if
	test="${form.coreMemberInfo.role.general ||form.coreMemberInfo.role.dev}">
	<div class="fTip">您的广告在 <a
		href="/advertiser.do?action=authenticatePage"><strong>身份认证</strong></a>
	后才能发布。</div>
</c:if>
<div class="clean"></div>
<div class="fr mt"><a href="/rechargeHis.do?action=list">查询明细</a>
<span class="gray">|</span> <a href="javascript:void(0)"
	onclick="showPopDev();">在线充值</a></div>
账户余额: <c:if test="${empty moneylittle}">
	<strong class="imp"><sup>&yen;</sup><fmt:formatNumber
		type="number" value="0" pattern="###,##0.00" /></strong>
</c:if> <c:if test="${!empty moneylittle}">
	<strong class="imp"><sup>&yen;</sup><fmt:formatNumber
		type="number" value="${moneylittle}" pattern="###,##0.00" /></strong>
</c:if>
<div class="clean"
	style="margin: 10px 0; border-top: 1px solid #ccc; padding-top: 10px">
<a href="/ad.do?action=detailEdit" class="btnS fr"
	style="margin-top: 5px"><span>新建广告</span></a> 广告展示总次数: <strong
	class="imp">${adShowCount }</strong></div>
</div>
<div class="rightCon" style="width: 310px; position: relative;">
<div class="infoCon"><c:if
	test="${form.coreMemberInfo.role.general ||form.coreMemberInfo.role.advertisers}">
	<a href="javascript:toBeDev()" class="fr"
		style="margin: 10px 3px 0 0; color: #999"><img
		src="/images/ico_cl.gif" alt=" " />您是开发者？</a>
</c:if>
<h2>用户信息<c:if test="${command.platformType=='1'}">
	<a id="info_taobao" href="javascript:void(0)"><img width="16"
		height="16" style="margin: 0 3px -2px" src="/images/logo_taobao.gif"></a>
</c:if></h2>
<ul id="exist_box">
	<li>上次登录时间: <fmt:formatDate value="${form.userHis.loginTime}"
		type="both" pattern="yyyy-MM-dd HH:mm" /></li>
	<li>上次登录IP: ${form.userHis.loginIp}</li>
	<li>会员类型: ${form.coreMemberInfo.memberTypeShowName}</li>
	<li class="r"><a href="/member.do?action=updateLoginPassword">修改密码</a>
	<span class="gray">|</span> <a href="/member.do?action=queryMember">修改会员信息</a></li>
</ul>
<ul style="display: none;" id="info_box">
	<li>淘宝邮箱: <c:out value="${form.otherEmail}"></c:out></li>
	<li>淘宝昵称: ${form.coreMemberInfo.otherName}</li>
	<li>会员类型: ${form.coreMemberInfo.memberTypeShowName}</li>
	<li>&nbsp;</li>
</ul>
</div>
<div class="info_tit" id="info_tit" style="display: none;"></div>
</div>
<div class="clean" style="padding: 30px 0 0 0">
<h1 class="tit"><span class="fr" style="margin-top: 5px"><a
	href="/adGroup.do?action=list">查看更多广告组&raquo;</a></span>广告组概括 <span>广告相关数据每天4、10、14、18、22、24
点更新</span></h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabY" id="list">
		<colgroup><col width="20%"/><col width="9%"/><col width="9%"/><col width="9%"/><col width="8%"/><col width="8%"/><col width="8%"/><col width="7%"/><col width="8%"/>
  		</colgroup>
  		<tr>
    <th>广告组名称</th>
    <th>投放地区</th>
    <th>所属行业</th>
    <th>平台</th>
    <th style="text-align:right">
    <!-- 
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
	 -->
	 展示次数
    </th>
    <th style="text-align:right">
    <!-- 
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
	 -->
	 点击数
    </th>
    <th style="text-align:right">
    <!-- 
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
	 -->
	 点击率
    </th>
    <th style="text-align:right">
    <!-- 
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
	 -->
	 花费
    </th>
    <th>
<!-- 
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
	 -->
	 更新时间
    </th>
    </tr>
    <tbody>
    <tr>
				<td><a href="/ad.do?action=adList&amp;adGroupId=0">未分组</a><span class="gray">(${defaultAdCout})</span></td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td class="r">&nbsp;</td>
				<td class="r">&nbsp;</td>
				<td class="r">&nbsp;</td>
				<td class="r">&nbsp;</td>
				<td class="r">&nbsp;</td>
			</tr>
    <c:forEach items="${form.groupList}" var="CoreAdGroup" varStatus="s">
			<tr>
				<td>
					<input type="hidden" value="总个数:${CoreAdGroup.cnt},suspendY:${CoreAdGroup.suspendY},suspendN:${CoreAdGroup.suspendN}"/>

					<c:if test="${CoreAdGroup.suspendN==CoreAdGroup.cnt&&CoreAdGroup.cnt!=0}">
					<img width="16" height="16" align="absmiddle" title="${CoreAdGroup.suspendN}个停用" alt="广告组" src="images/ico_pic_stop.gif"/><a href="/ad.do?action=adList&adGroupId=${CoreAdGroup.adGroupId}"><airad:cutString size="10" value="${CoreAdGroup.adGroupName}" mark="..."/></a><span class="gray">(${CoreAdGroup.cnt})</span>
					</c:if>
					<c:if test="${CoreAdGroup.suspendY==CoreAdGroup.cnt&&CoreAdGroup.cnt!=0}">
					<img width="16" height="16" align="absmiddle" title="${CoreAdGroup.suspendN}个运行" alt="广告组" src="images/ico_pic_play.gif"/><a href="/ad.do?action=adList&adGroupId=${CoreAdGroup.adGroupId}"><airad:cutString size="10" value="${CoreAdGroup.adGroupName}" mark="..."/></a><span class="gray">(${CoreAdGroup.cnt})</span>
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
				<c:forEach items="${form.industryInvolved}" var="dictionary">
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
		<c:if test="${empty form.groupList}">
        	<tr><td colspan="9"><div class="c">暂无数据</div></td></tr>
    	</c:if>
  </tbody>
	</table>
<h1 class="tit" style="margin-top: 30px"><span class="fr"
	style="margin-top: 5px"><a
	href="/report.do?action=add&flag=0&currentPage=1">新建报告</a> <span
	class="gray">|</span> <a href="/report.do?action=listReport">查看更多报告&raquo;</a></span>我的报告</h1>
<table cellspacing="0" cellpadding="0" border="0" class="tabY">
	<col width="40%" />
	<col width="30%" />
	<col width="20%" />
	<col width="10%" />
	<tbody>
		<tr>
			<th>名称</th>
			<th>创建时间</th>
			<th>类型</th>
			<th>报表形式</th>
		</tr>
		<c:forEach items="${form.reportList}" var="report" varStatus="s">
			<tr>
				<td><a href="report.do?action=show&reportId=${report.reportId }"><airad:cutString size="20" value="${report.reportName}"
					mark="..." /></a></td>
				<td><fmt:formatDate value="${report.addTime }" type="both"
					pattern="yyyy-MM-dd" /></td>
				<c:if test="${report.reportType==0}">
					<td>活动报告</td>
				</c:if>
				<c:if test="${report.reportType==1}">
					<td>广告组报告</td>
				</c:if>
				<c:if test="${report.reportType==2}">
					<td>广告报告</td>
				</c:if>
				<c:if test="${report.reportType==3}">
					<td>应用报告</td>
				</c:if>
				<c:if test="${report.reportType==4}">
					<td>广告商报告</td>
				</c:if>
				<td><c:choose>
					<c:when test="${report.reportStatus==0}">汇总报告</c:when>
					<c:otherwise>详细报告</c:otherwise>
				</c:choose></td>
			</tr>
		</c:forEach>
		<c:if test="${empty form.reportList}">
			<tr>
				<td colspan="4">
				<div class="c">暂无数据</div>
				</td>
			</tr>
		</c:if>
	</tbody>
</table>
</div>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript"
	src="js/jquery.jmpopups-0.5.1.js"></script>
<script>
$(function(){
	$("#info_taobao").hover(
	function(){
	$("#info_tit").show();
	$("#info_box").show();
	$("#exist_box").hide();
	},
	function(){
	$("#info_tit").hide();
	$("#info_box").hide();
	$("#exist_box").show();
	});
	});
jQuery(document).ready(function(){
	addCss("advlist");
});
function showPopDev() {
    $.openPopupLayer( {
   name : 'popDiv',
   url : 'recharge_win.jsp',
   cache : false,
   width : '500'
 });
}
function closepop() {
 $.closePopupLayer('popDiv');
}
</script>
</body>
</html>



