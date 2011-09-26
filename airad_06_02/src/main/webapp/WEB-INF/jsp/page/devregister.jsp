<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>airAD － 智慧广告 智慧生活</title>
<script type="text/javascript" src="js/location.js"></script>

<%@ include file="/WEB-INF/jsp/page/header_meta.jsp"%>
<style>
	label .error{background:#ffe2e2;border:1px solid #dd0e0e;margin-bottom:10px;padding:0px 2px;float:left}
</style>
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
<small>2011-09-07</small></h2>

<div class="con">
<div class="c">
<img src="/images/index/news_20110905_up.jpg" height="152" width="671" border="0" /><img src="/images/index/news_20110905_down.jpg" height="174" width="671" border="0" />
</div>
<p>在被称为移动互联网盛世的如今，谈论“<strong>如何生存</strong>”这个话题未免有点过于沉重，但是在面对百万APP大军激烈的竞争中，提出这样问题显得尤为重要。记得《硅谷海盗》中当盖茨看到乔布斯成功后反问自己的一句话：“<strong>怎样才能生存？你生存是因为他们需要你。</strong>”</p>
<p>这句话尖锐而明确的阐述了一个产品之所以能够出现并生存的原因，“存在的价值”！</p>
<p>“做有价值的APP”看似非常简单，但只有真正经历过波折的开发者才能真切的体会到其中的心酸与无奈。当看到一个个恶评与逐日下滑的下载量时，<strong>当看到彻夜辛苦Coding的APP不被认同时，你会怎么想？会怎么做？</strong>如果你没有答案或和大家一样心存困惑，那么不妨来这里听听。</p>
<p>“做有价值的APP - airAD移动智慧广告全国开发者沙龙”将邀请业内草根创业家与业内资深VC们就“如何做有价值的APP？”等议题逐一展开讨论与经验分享。</p>
<p>也许这个问题并没有所谓的标准答案，但我们相信“梦”正是因为其飘忽不定，才会让人更加忘我的追逐。</p>
<p>最后祝愿所有“逐梦人”都可以梦想成真，努力！奋斗！</p>
<h2>9月24日 南京站 开始报名</h2>
<p><strong>特邀嘉宾：</strong>
<ul>
<li><strong>朱兴东</strong> 腾讯无线投资部经理</li>
<li><strong>张驰</strong> BlackBerry 中国区战略合作总监</li>
<li><strong>赵思思</strong> 创新工场应用汇客户经理</li>
<li><strong>黄飞飞</strong> 噢粑粑创始人</li>
</ul></p>
<p><strong>主持人：</strong>
<ul>
<li><strong>陆田</strong> airAD移动智慧广告CEO</li>
</ul></p>
<p><strong>活动时间：</strong><br />
2011-09-24(周六) <strong class="red">14:00-17:00</strong></p>
<p><strong>活动地点：</strong><br />
<strong class="red">雕刻时光咖啡馆</strong> 南京市鼓楼区汉口路47号 二楼 (南京大学正南门)<br />
<img src="/images/index/20110907maps.png" alt=" " width="300" height="218" style="border:1px solid #ccc;margin-top:10px" /> </p>
<h2 style="margin-top:25px">现在报名参加 <small>名额有限，报名成功后工作人员将与您取得联系。</small></h2>

<div id="suc" class="suc" style="display:none">
<div style="padding-bottom:10px"><h1>申请成功</h1></div>
</div>
<div id="tab" style="background:#fff;border:1px solid #ccc;padding:10px 20px;margin-bottom:10px;" class="clean">
<form name="myFrm" commandName="command"  id="regForm"
	action="member.do?action=doDevregister" method="post" >
<table border="0" cellspacing="0" cellpadding="0" class="tabNF" style="margin-top:10px">
<col width="30%" /><col />
  <tr>
    <th>姓名</th>
			<td>
				<input name="name" class="half"/>
			</td>

  </tr>
  <tr>
    <th>公司</th>
    <td>
    	<input name="company" class="half"/>
    </td>

  </tr>
  <tr>
    <th>职位</th>
    <td>
    	<input name="position" class="half"/>
	</td>

  </tr>
  <tr>
    <th><span class="must">*</span>开发平台</th>
    <td>
		<input id="os" name="os" size="40"  />
    </td>

  </tr>
  <tr>
    <th><span class="must">*</span>APP数量</th>
    <td>
		<input id="appnumber" name="appnumber" size="5"  />
	</td>

  </tr>
  <tr>
  	<th><span class="must">*</span>地区</th>
  	<td>
  	<div id="ChinaArea">
     <select id="province" 	name="province" style="width: 100px;" class="required"/>&nbsp;
     <select id="city" 		name="city" style="width: 100px;"/>&nbsp;
     <select id="county" 	name="country" style="width: 120px;"/>
     <input id="provinceName" name="provinceName" type="hidden"/>
     <input id="cityName" name="cityName" type="hidden"/>
</div>
  	</td></tr>
  <tr>
    <th><span class="must">*</span>手机</th>
    <td>
    	<input id="cellphone" name="cellphone" size="25" style="float: left" />
	</td>

  </tr>
  <tr>
    <th><span class="must">*</span>邮箱</th>
    <td>
    	<input id="cemail" name="email" size="25"  />
    </td>

  </tr>
  <tr>
    <th><span class="must">*</span>QQ</th>
    <td>
		<input id="qq" name="qq" size="25"  />
	</td>
  </tr>
  <tr>
    <th>&nbsp;</th>
    <td><button type="submit"  class="btnBY" id="submit">提交</button></td>
  </tr>
</table>
</form>
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
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.form.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#ChinaArea").jChinaArea();
	jQuery.validator.addMethod('cellphone',function(value, element) {
		var length = value.length;
	     var mobile = /^(((1[0-9]{2})|(15[0-9]{1}))+\d{8})$/;
	     return this.optional(element) || (length == 11 && mobile.test(value));
	}, 'please make a selection');
	$("#province").prepend("<option value=''>请选择</option>");
	$("#county").empty();
	$("#county").prepend("<option value=''>请选择</option>");
	$("#province").val(0);
	$("#province ").get(0).selectedIndex=0;
	$("#regForm").validate({
		rules: {
			email: "required email",
			qq: "required",
			cellphone: "required cellphone",
			appnumber:"required",
			os:"required",
			username: {
				required: true,
				minlength: 2
			},
			password: {
				required: true,
				minlength: 5
			},
			confirm_password: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			topic: {
				required: "#newsletter:checked",
				minlength: 2
			},
			agree: "required"
		},
		messages: {
			cellphone: "请输入正确的手机号码",
			lastname: "Please enter your lastname",
			username: {
				required: "Please enter a username",
				minlength: "Your username must consist of at least 2 characters"
			},
			password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			confirm_password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long",
				equalTo: "Please enter the same password as above"
			},
			os: "请输入操作平台",
			appnumber: "请输入APP数量",
			email: "请输入正确的邮箱地址",
			qq: "请输入QQ",
			province:"请输入区域"

		}
	});


	$("#submit").click(function(){
		$("#provinceName").val($("#province").find("option:selected").text());
		$("#cityName").val($("#county").find("option:selected").text());
	})

})
</script>
<script type="text/javascript">

(function($) {
	$.fn.jChinaArea = function(o) {
		o = $.extend({
			aspnet:false,
			s1:null,
			s2:null,
			s3:null
    }, o || {});
        var wrap=$(this);
		var sel=$("select",wrap);
		var sProvince=sel.eq(0);
		var sCity=sel.eq(1);
		var sCounty=sel.eq(2);
		var loc	= new Location();

		sProvince.empty();
		sCity.empty();
		sCounty.empty();
		loc.fillOption(sProvince , '0',o.s1);
		loc.fillOption(sCity , '0,'+sProvince.val(),o.s2);
		loc.fillOption(sCounty , '0,' + sProvince.val() + ',' + sCity.val(),o.s3);

		if(o.aspnet){
			var input=$("input",wrap);
			var tProvince=input.eq(0);
			var tCity=input.eq(1);
			var tCounty=input.eq(2);
			writeInput();
		}

		sProvince.change(function() {
		sCity.empty();
		loc.fillOption(sCity , '0,'+sProvince.val());
		sCounty.empty();
		loc.fillOption(sCounty , '0,' + sProvince.val() + ',' + sCity.val());
		if(o.aspnet){
			writeInput();
		}
	})

	sCity.change(function() {
		sCounty.empty();
		loc.fillOption(sCounty , '0,' + sProvince.val() + ',' + sCity.val());
		if(o.aspnet){
			writeInput();
		}
	})
	sCounty.change(function(){
		if(o.aspnet){
			writeInput();
		}
	})

	function writeInput(){
			tProvince.val($(":selected",sProvince).text());
			tCity.val($(":selected",sCity).text());
			tCounty.val($(":selected",sCounty).text());
	}
	};

})(jQuery);
</script>
</body>
</html>