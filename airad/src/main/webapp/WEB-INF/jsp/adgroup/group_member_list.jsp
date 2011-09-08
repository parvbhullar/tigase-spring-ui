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
<%
CoreCampaign coreCampaign=(CoreCampaign)request.getAttribute("campaign");
String startTime="";
if(null!=coreCampaign){
if(null!=coreCampaign.getStartTime()){
    startTime =DateFormatUtils.format(coreCampaign.getStartTime(),"yyyy-MM-dd HH:mm");
}}
%>
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
<div class="more"></div>
<a href="add.php"><strong>新建广告</strong></a> | <a href="add-group.php"><strong>新建广告组</strong></a><span style="display: none;" id="btns"> | <a href="javascript:void(0)">修改投放地区</a> | <a href="javascript:void(0)">修改所属行业</a> | <a id="btn01" href="javascript:void(0)">修改投放平台</a> | <a href="javascript:void(0)">运行</a> | <a href="javascript:void(0)">停用</a> | <a href="javascript:void(0)">删除</a></span>
</div>
<div class="timeBox">
<table cellspacing="0" cellpadding="0" border="0">
  <tbody><tr>
    <td class="search"><input></td>
    <td><a class="btnS fl" href="javascript:void(0)"><span>查询</span></a></td>
  </tr>
</tbody></table>
</div>
</div>
	<table border="0" cellspacing="0" cellpadding="0" class="tabYH">
		<colgroup><col width="3%"><col><col width="9%"><col width="8%"><col width="11%"><col width="10%"><col width="9%"><col width="7%"><col width="7%"><col width="10%">
  		</colgroup>
  		<tbody><tr>
    <th><input type="checkbox" id="checkbox" name="checkbox"></th>
    <th>广告组名称</th>
    <th>地理位置</th>
    <th>所属行业</th>
    <th>平台</th>
    <th class="r"><a href="javascript:void(0)">展示次数<img alt="排序" src="images/ico_sort.gif"></a></th>
    <th class="r"><a href="javascript:void(0)">点击数<img alt="排序" src="images/ico_sort.gif"></a></th>
    <th class="r"><a href="javascript:void(0)">点击率<img alt="排序" src="images/ico_sort.gif"></a></th>
    <th class="r"><a href="javascript:void(0)">花费<img alt="排序" src="images/ico_sort.gif"></a></th>
    <th><a href="javascript:void(0)">更新时间<img alt="排序" src="images/ico_sortza.gif"></a></th>
    </tr>
  <tr>
    <td>&nbsp;</td>
    <td><img width="16" height="16" align="absmiddle" alt="广告组" src="images/ico_act.gif"><a href="list.php">未分组</a><span class="gray">(1)</span></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td class="r">&nbsp;</td>
    <td class="r">&nbsp;</td>
    <td class="r">&nbsp;</td>
    <td class="r">&nbsp;</td>
    <td>2011-08-06</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="checkbox2" name="checkbox2"></td>
    <td><img width="16" height="16" align="absmiddle" title="4个运行" alt="广告组" src="images/ico_play.gif"><a href="list.php">宣传米田科技有限公司的...</a><span class="gray">(4)</span></td>
    <td>全国</td>
    <td>艺术收藏</td>
    <td>Android, iPhone</td>
    <td class="r">13,433,219</td>
    <td class="r">324,548</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  <tr class="over">
    <td><input type="checkbox" id="checkbox3" name="checkbox3"></td>
    <td><img width="16" height="16" align="absmiddle" title="4个运行" alt="广告组" src="images/ico_play.gif"><a href="list.php">在应用中植入airAD关于苏宁...</a><span class="gray">(2)</span></td>
    <td>精确到区</td>
    <td>生活服务</td>
    <td>Android, iPhone</td>
    <td class="r">12,987</td>
    <td class="r">324</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="checkbox4" name="checkbox4"></td>
    <td><img width="16" height="16" align="absmiddle" title="4个运行" alt="广告组" src="images/ico_play.gif"><a href="list.php">苏宁地产2011年投放广告</a><span class="gray">(3)</span></td>
    <td>精确到区</td>
    <td>影音娱乐</td>
    <td>Android, iPhone</td>
    <td class="r">13,433,219</td>
    <td class="r">324,548</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="checkbox5" name="checkbox5"></td>
    <td><img width="16" height="16" align="absmiddle" title="4个运行" alt="广告组" src="images/ico_play.gif"><a href="list.php">在应用中植入airAD关于苏宁...</a><span class="gray">(2)</span></td>
    <td>精确到区</td>
    <td>资讯阅读</td>
    <td>Android</td>
    <td class="r">13,433,219</td>
    <td class="r">324,548</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="checkbox6" name="checkbox6"></td>
    <td><img width="16" height="16" align="absmiddle" title="3个停用" alt="广告组" src="images/ico_pic_stop.gif"><a href="list.php">苏宁地产2011年投放广告</a><span class="gray">(1)</span></td>
    <td>全国</td>
    <td>艺术收藏</td>
    <td>Android, iPhone</td>
    <td class="r">12,987</td>
    <td class="r">324,548</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="checkbox7" name="checkbox7"></td>
    <td><img width="16" height="16" align="absmiddle" title="3个停用" alt="广告组" src="images/ico_pic_stop.gif"><a href="list.php">在应用中植入airAD关于苏宁...</a><span class="gray">(3)</span></td>
    <td>精确到区</td>
    <td>生活服务</td>
    <td>Android, iPhone</td>
    <td class="r">13,433,219</td>
    <td class="r">324,548</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="checkbox8" name="checkbox8"></td>
    <td><img width="16" height="16" align="absmiddle" title="13个运行" alt="广告组" src="images/ico_play.gif"><a href="list.php">苏宁地产2011年投放广告</a><span class="gray">(3)</span></td>
    <td>精确到区</td>
    <td>影音娱乐</td>
    <td>iPhone</td>
    <td class="r">12,987</td>
    <td class="r">324,548</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="checkbox9" name="checkbox9"></td>
    <td><img width="16" height="16" align="absmiddle" title="1个运行, 1个停用" alt="广告组" src="images/ico_pau.gif"><a href="list.php">在应用中植入airAD关于苏宁...</a><span class="gray">(5)</span></td>
    <td>精确到区</td>
    <td>资讯阅读</td>
    <td>Android, iPhone</td>
    <td class="r">13,433,219</td>
    <td class="r">324,548</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="checkbox10" name="checkbox10"></td>
    <td><img width="16" height="16" align="absmiddle" title="13个运行" alt="广告组" src="images/ico_play.gif"><a href="list.php">在应用中植入airAD关于苏宁...</a><span class="gray">(2)</span></td>
    <td>全国</td>
    <td>影音娱乐</td>
    <td>Android, iPhone</td>
    <td class="r">13,433,219</td>
    <td class="r">324,548</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  <tr>
    <td><input type="checkbox" id="checkbox11" name="checkbox11"></td>
    <td><img width="16" height="16" align="absmiddle" title="13个运行" alt="广告组" src="images/ico_play.gif"><a href="list.php">苏宁地产2011年投放广告</a><span class="gray">(3)</span></td>
    <td>全国</td>
    <td>资讯阅读</td>
    <td>Android, iPhone</td>
    <td class="r">13,433,219</td>
    <td class="r">324,548</td>
    <td class="r">3%</td>
    <td class="r"><sup>¥</sup>1.00</td>
    <td>2011-08-06</td>
    </tr>
  </tbody>
	</table>

	 <airad:pagination pageSize="${p.pageSize}" href="/adGroup.do?action=selectAll&currentPage=PAGENUM&campaignId=${campaign.campaignId}
	 &timeSlotFlag=${p.timeSlotFlag}&startTime=${p.startTime}&endTime=${p.endTime}&adGroupName=${p.adGroupName}"
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
<li><strong>展示次数：</strong>本组广告被展示的总次数；<strong>点击数：</strong>活动中，本组广告获得点击的总次数；<strong>成本：</strong>该广告组实际消费的广告费用。</li>
<li><strong>点击率：</strong>点击数/广告展示次数*100%。</li>
<!--<li><strong>平均每次点击金额：</strong>您为每次点击支付的平均价格。例如，如果您在应用程序投放的广告获得两次点击，一次费用为 &yen;0.20，一次为 &yen;0.40，则平均费用为 &yen;0.30。</li>-->
</ul>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script type="text/javascript">
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
	$("#btn01").click(function(){
									$(".popBg").show();
									$("#popDiv").fadeIn();
									})
	$("#closePop,#btn02").click(function(){
									$(".popBg").hide();
									$("#popDiv").hide();
									})
	$("#btn02").click(function(){
									$(".okBox").show();
									})
})
</script>
<script>
document.onkeypress=adGroupStatistic;
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
</body>
</html>
