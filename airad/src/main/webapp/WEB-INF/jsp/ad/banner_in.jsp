<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="airad" uri="http://www.airad.com/tlds/airad-html-common"%>
<input type="hidden" class="free bannerFree" value="0.11"/>
<h4>
<div class="fr"><button id="bannerDraftSave" type="button" style="cursor:pointer">保存Banner</button> <span class="gray collapsed">|</span> <a href="javascript:void(0)" id="exhibitBanner" style="display:none"><img width="14" height="14" alt="展开" src="/images/ico_infoc.gif">展开</a><a href="javascript:void(0)" id="closeBanner" ><img width="14" height="14" alt="收起" src="/images/ico_infoo.gif">收起</a></div>
广告 Banner 制作
<span id="ls" style="display:none;"></span>
</h4>
<form:hidden  path="command.bannerType"  />
<form:hidden  path="command.bannerColor"  />
<form:hidden  path="command.bannerModelName"  />
<form:hidden  path="command.bannerGrain"  />
<form:hidden path="command.bannerId" />
<form:hidden path="command.bannerIconCon" />
<form:hidden path="command.bannerBgCon" />
<table cellspacing="0" cellpadding="0" border="0" class="tabNF" id="bannerTable">
	<col width="20%" />
	<col width="80%" />
	<tr>
		<th>Banner 设置</th>
		<td>
		<a href="javascript:void(0)" id="bannerSelect">请选择 Banner 模版</a>
		</td>
	</tr>
	<tr class="trBannerDemo" style="display:none">
		<th></th>
		<td >
		 <div id="bannerDesign" >
		  <div style="width: 320px; height: 54px" class="expl" id="bannerDemo">
           <c:if test="${command.bannerTypeDeprecated}">
		  <%@ include file="old_banner.jsp"%>
		  <div style="position: absolute; bottom: 0; right: 1px; padding: 0; font-size: 9px; color: #b5aaa6">airAD.com</div>
		</c:if>
		</div>
		</div>
		<div id="outKey"></div>
        <div class="mt"><small><span id="bannerDesc"></span></small></div>
		</td>
	</tr>
</table>


<div style="top: 300px; left: 150px;" id="bannerModel" class="popDiv collapsed">
<h2><img class="fr" id="bannerClose" alt="关闭" src="images/ico_popclose.gif">Banner 模版</h2>
<div class="popCon">
<div class="tag">
<a
	class="now selectModelByType" tag="5"
	color="black"
	href="javascript:void(0)">纯文字</a><a href="javascript:void(0)" tag="6" color="red" class="selectModelByType">小图加文字</a> <a href="javascript:void(0)" tag="7" color="red" class="selectModelByType">大图加文字</a><a href="javascript:void(0)" color="blue" tag="8" class="selectModelByType">小图加动态文字</a><a href="javascript:void(0)" tag="9" color="green" class="selectModelByType">用户自定义</a>
     </div>
<div class="colorBox">
<c:if test="${!baseRole.ossSales}">
<div class="fr">Banner 价格: <sup>&yen;</sup><span id="selectedBannerPrice">0.1</span></div><span style="display:none;"><airad:sysConfig type='BANNER_TYPE' id="bannerPriceSelect"/></span>
</c:if>
<div class="fl" id="chooseColorDiv">
<span class="fl">颜色选择:</span>
<div class="colorBtn">
<div class="btnBlack selectModelByColor now" color="black"></div>
<div class="btnRed selectModelByColor" color="red"></div>
<div class="btnBlue selectModelByColor" color="blue"></div>
<div class="btnGreen selectModelByColor" color="green"></div>
</div>
</div>
</div>
   
    
<div id="bannerConetnt" class="picBox" style="height:190px">
</div>
</div>
</div>