<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>airAD － 智慧广告 智慧生活</title>
<script type="text/javascript" src="style/jquery.js"></script>
<%@ include file="/WEB-INF/jsp/page/header_meta.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/page/page_header.jsp"%>
<div id="nmPic">
</div>
<div id="main">
<div class="mainCon">
<div class="leftCon">
<div class="clean"><a href="index.html">首页</a> &raquo; 新闻中心</div>
<h2 class="c">做有价值的APP - airAD移动智慧广告全国开发者沙龙<br />
<small>2011-09-05</small></h2>

<div class="con">
<div class="c">
<img src="../../../images/index/news_20110905_up.jpg" height="152" width="671" border="0" /><img src="../../../images/index/news_20110905_down.jpg" height="174" width="671" border="0" />
</div>
<p>在被称为移动互联网盛世的如今，谈论"<strong>如何生存</strong>"这个话题未免有点过于沉重，但是在面对百万APP大军激烈的竞争中，提出这样问题显得尤为重要。记得《硅谷海盗》中当盖茨看到老乔成功后反问自己的一句话，"<strong>怎样才能生存？你生存是因为他们需要你。</strong>"</p>
<p>这句话尖锐而明确的阐述了一个产品之所以能够出现并生存的原因，"存在的价值"！</p>
<p>"做有价值的APP"看似非常简单，但只有真正经历过波折的开发者才能真切的体会到其中的心酸与无奈。当看到一个个恶评与逐日下滑的下载量时，<strong>当看到彻夜辛苦Coding的APP不被认同时，你会怎么想？会怎么做？</strong>如果你没有答案或和大家一样心存困惑，那么不妨来这里听听。</p>
<p>"做有价值的APP - airAD移动智慧广告全国开发者沙龙"将邀请业内草根创业家与业内资深VC们就"如何做有价值的APP？"等议题逐一展开讨论与经验分享。</p>
<p>也许这个问题并没有所谓的标准答案，但我们相信"梦"正是因为其飘忽不定，才会让人更加忘我的追逐。</p>
<p>最后祝愿所有"逐梦人"都可以梦想成真，努力！奋斗！</p>
<h2 style="margin-top:25px">现在报名参加</h2>

<div id="suc" class="suc" style="display:none">
<div style="padding-bottom:10px"><h1>申请成功</h1></div>
</div>
<div id="tab" style="background:#fff;border:1px solid #ccc;padding:10px 20px;margin-bottom:10px;" class="clean">
<form:form name="myFrm" commandName="command"  id="regForm"
	action="member.do?action=doDevregister" method="post" >
<table border="0" cellspacing="0" cellpadding="0" class="tabNF" style="margin-top:10px">
<col width="30%" /><col />
  <tr>
    <th>姓名</th>
			<td>
				<form:input path="name" cssClass="half"/>
				<div id="nameDiv1" class="wrBox" style="display: none">
				<div id="nameDiv"  class="wr">
			</td>
  </tr>
  <tr>
    <th>公司</th>
    <td>
    	<form:input path="company" cssClass="half"/>
		<div id="companyDiv1" class="wrBox" style="display: none">
		<div id="companyDiv"  class="wr">
    </td>
  </tr>
  <tr>
    <th>职位</th>
    <td>
    	<form:input path="position" cssClass="half"/>
		<div id="positionDiv1" class="wrBox" style="display: none">
		<div id="positionDiv"  class="wr">
	</td>
  </tr>
  <tr>
    <th><span class="must">*</span>开发平台</th>
    <td>
		<form:input path="os" cssClass="half"/>
		<div id="osDiv1" class="wrBox" style="display: none">
		<div id="osDiv"  class="wr">
    </td>
  </tr>
  <tr>
    <th><span class="must">*</span>APP数量</th>
    <td>
    	<form:input path="appnumber" cssClass="half"/>
		<div id="appnumberDiv1" class="wrBox" style="display: none">
		<div id="appnumberDiv"  class="wr">
	</td>
  </tr>
  <tr>
    <th><span class="must">*</span>手机</th>
    <td>
    	<form:input path="cellphone" cssClass="half"/>
		<div id="cellphoneDiv1" class="wrBox" style="display: none">
		<div id="cellphoneDiv"  class="wr">
	</td>
  </tr>
  <tr>
    <th><span class="must">*</span>邮箱</th>
    <td>
    	<form:input path="email" cssClass="half"/>
		<div id="emailDiv1" class="wrBox" style="display: none">
        <div id="emailDiv"  class="wr">
    </td>
  </tr>
  <tr>
    <th><span class="must">*</span>QQ</th>
    <td>
    	<form:input path="qq" cssClass="half"/>
		<div id="qqDiv1" class="wrBox" style="display: none">
		<div id="qqDiv"  class="wr">
	</td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <!--  <td><div class="btnBox"><a href="#" class="btnY fl" id="submit"><span>提交</span></a></div></td>-->
    <td><div class="btnBox"><button type="submit"  class="btnBY" id="submit">提交</button></div></td>
  </tr>
</table>
</form:form>
</div>

<div class="r"><a href="index.html">返回&raquo;</a></div>
</div>
</div>
<div class="rightCon">
<div class="con">
<h2>相关链接</h2>
<ul>
<li><a href="index.html">首页&raquo;</a></li>
<li><a href="about-us.html">关于我们&raquo;</a></li>
<li><a href="policy.html">隐私协议&raquo;</a></li>
<li><a href="terms.html">服务条款&raquo;</a></li>
<li><a href="join-us.html">加入我们&raquo;</a></li>
<li><a href="help.html">帮助中心&raquo;</a></li>
</ul>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/page/page_footer.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#submit").click(function(){
									//$("#suc").show();
									//$("#tab").hide();
									})
})
</script>
</body>
</html>