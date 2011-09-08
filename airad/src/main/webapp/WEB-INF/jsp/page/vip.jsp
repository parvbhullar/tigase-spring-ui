<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>airAD VIP定制广告</title>
<%@ include file="/WEB-INF/jsp/page/header_meta.jsp"%>
<style>
<!--
body{background:#000}
#all{background:url(images/index/bg_headervip.jpg) top left repeat-x}
.headVip{width:100%;min-width:912px;height:55px;margin:auto;background:url(images/index/bg_headvip.jpg) center no-repeat;color:#f3c3c2}
.headVip a:link,.headVip a:visited{color:#f3c3c2}
.logoBtn{float:left;margin-left:25px;_margin-left:12px}
.vipup{height:157px;width:100%;min-width:912px;margin:auto;_margin-top:-1px;background:url(images/index/bg_headconvipup.jpg) center top no-repeat}
.vipdown{height:160px;width:100%;min-width:912px;margin:auto;background:url(images/index/bg_headconvipdown.jpg) center top no-repeat}
#mainVip{width:100%;min-width:912px;height:121px;margin:0 auto 10px auto;background:url(images/index/bg_headbottomvip.jpg) center top no-repeat}
.conVip{width:912px;margin:-77px auto 10px auto;padding-bottom:40px;background:#f4e1a9 url(images/index/bg_convip.gif) top left repeat-x;overflow:hidden;zoom:1;-moz-border-radius:0 0 12px 12px;-webkit-border-radius:0 0 12px 12px;border-radius:0 0 12px 12px;}
.conVip h2{margin:40px 0 5px 35px}
.flashBox{padding:5px 35px;overflow:hidden;zoom:1;margin:auto}
.flash{width:261px;height:195px;float:left;background:url(images/index/bg_flash.gif) center no-repeat;border:3px solid #dabf70}
.flashlist{float:left;width:550px}
.flashlist li{list-style:none;color:#4f3c06}
.titRed{background:url(images/index/btns.gif) -299px -76px no-repeat;padding-left:15px;color:#4f3c05}
.showPic{width:100%}
.showPic th{font-size:14px;text-align:left; padding:10px 0 0 0}
.showPic td{padding:5px 10px 0 0}
.showPic div{border:3px solid #dabf70;-moz-border-radius:10px;-webkit-border-radius:10px;border-radius:10px;}
#footer{color:#cdcdcd;border:0}
#footer a{color:#cdcdcd}
.pic_vip_p01{background:url(../images/index/pic_vip.jpg) 0 0 no-repeat;width:170px;height:100px}
.pic_vip_p02{background:url(../images/index/pic_vip.jpg) 0 -100px no-repeat;width:170px;height:100px}
.pic_vip_p03{background:url(../images/index/pic_vip.jpg) 0 -200px no-repeat;width:170px;height:100px}
.pic_vip_p04{background:url(../images/index/pic_vip.jpg) 0 -300px no-repeat;width:170px;height:100px}
.pic_vipStep{background:url(../../../images/index/vip_logo.gif) 0 0 no-repeat;width:520px;height:39px}
.pic_langshi{background:url(../../../images/index/vip_logo.gif) -111px -39px no-repeat;width:147px;height:41px}
.pic_bsd{background:url(../../../images/index/vip_logo.gif) 0 -39px no-repeat;width:111px;height:61px}
-->
</style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/page/page_header.jsp"%>
<div id="all">
<div class="vipup"></div>
<div class="vipdown"></div>
<div id="mainVip"></div>
<div class="conVip">
    <h2 class="titRed">成功案例</h2>
        <div class="flashBox">
        <div class="flash"><a href="/video/vip01.flv" style="display:block;width:261px;height:195px" id="player"></a></div>
        <ul class="flashlist">
        <li><div class="pic_langshi"></div></li>
        <li><p>朗诗集团股份有限公司创立于2001年12月24日，是一家从事绿色科技地产及相关产业的专业性房地产开发公司。公司致力于成为一家股权结构合理、战略定位清晰、组织能力适配、企业文化健康的现代股份企业；立志成为创造客户、员工、股东、社会价值的优秀企业公民。</p></li>
        </ul>
        </div>
        <div class="flashBox">
        <div class="flash"><a href="/video/vip02.flv" style="display:block;width:261px;height:195px" id="player2"></a></div>
        <ul class="flashlist">
        <li><div class="pic_bsd"></div></li>
        <li>
          <p>波司登，全称波司登国际控股有限公司，是中国最大的品牌羽绒生产商,主要从事自有羽绒服品牌组合的开发和管理，包括产品的研究、设计、开发、原材料采购、外包生产及市场营销和销售。核心品牌有"波司登"、"雪中飞"、"康博"、"冰洁"、"双羽"和"上羽"。它在1975年成立，公司创办人兼主席是高德康，总部设在上海。</p></li>
        </ul>
        </div>
    <h2 class="titRed">定制优势</h2>
    <div style="border:1px solid #dabf70;margin:0 35px;background:#fdf8e9;padding:20px 10px 20px 30px;-moz-border-radius:12px;-webkit-border-radius:12px;border-radius:12px;margin-top:15px">
    <table border="0" cellspacing="0" cellpadding="0" class="showPic">
<col width="25%" /><col width="25%" /><col width="25%" /><col />
  <tr>
    <td><div class="pic_vip_p01"></div></td>
    <td><div class="pic_vip_p02"></div></td>
    <td><div class="pic_vip_p03"></div></td>
    <td><div class="pic_vip_p04"></div></td>
  </tr>
  <tr>
    <th>更丰富的互动体验</th>
    <th>更夺人眼球的视觉效果</th>
    <th>最精心的内容策划</th>
    <th>最优质的推送渠道</th>
  </tr>
  <tr>
    <td>可以添加视频、游戏等到广告之中，获得与众不同的广告体验。</td>
    <td>拥有更炫的导航、过场动画等最新HTML5技术效果。</td>
    <td>可以单独定制页面，满足各种文案的需求，功能诉求准确。</td>
    <td>可以根据区域时段应用进行有限推送。</td>
  </tr>
    </table>
</div>
    <h2 class="titRed" style="margin-top:40px">如何定制</h2>
<div class="flashBox">
<div class="pic_vipStep"></div>
  <p>airAD 移动互联网广告平台是国内首家支持 HTML5 模板定制的移动互联网广告平台，"为原创埋单"也是我们一直的坚持。为了使广告形式更加丰富个性化，我们在模板定制的基础上推出了全新的移动互联网定制广告。在此之前我们已为波司登、朗诗地产等国内知名品牌制作了专属定制广告，得到了厂商与开发者的一致好评。</p>  <p>如果您需要制作专属定制广告，可以点击下方联系我们的按钮，通过邮件与我们取得联系。或者直接拨打 <strong>021-51623778</strong>，我们会有专属客户经理与您取得联系，双方对于定制广告的内容、投放区域时段进一步沟通，再交由我们优秀的设计团队进行制作。</p>
  <p style="color:#ae995d">有任何问题请点击右下角“在线客服”随时提问。</p>
  <div class="c" style="margin:60px auto 0 auto;overflow:hidden;width:299px"><a href="mailto:contact@airad.com" class="btn_vip fl"></a></div>
  </div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/page/page_footer.jsp"%>
<script type="text/javascript" src="video/flowplayer-3.2.6.min.js"></script>
<script>
$(document).ready(function(){
	flowplayer("player", "/video/flowplayer-3.2.7.swf",{
        onFullscreen :function(){
            return false;
        },
        	plugins: {
                controls: null
            },clip: {
		autoPlay: false, 
		autoBuffering: true
	}
    
    });
	flowplayer("player2", "/video/flowplayer-3.2.7.swf",{
        onFullscreen :function(){
            return false;
        },
        	plugins: {
                controls: null
            },clip: {
		autoPlay: false, 
		autoBuffering: true
	}
    });
	});
</script>
</body>
</html>