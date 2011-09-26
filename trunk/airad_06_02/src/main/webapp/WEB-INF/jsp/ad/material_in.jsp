<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="airad" uri="http://www.airad.com/tlds/airad-html-common"%>
<!-- JS遮罩层 -->
<div id="fullbg"></div>
<div class="popBg collapsed"></div>
<!-- 文字 -->
<div id="uploadBannerImgDiv" class="popDiv collapsed" style="width:360px">
<h2><img src="images/ico_popclose.gif" id="closeBannerImage" alt="关闭"
	class="fr">上传图片</h2>
	<div class="popCon">
	<input type="hidden" id="bannerImgId" name="bannerImgId" ></input>
	<input type="file" name="upLoadBannerImage" id="upLoadBannerImage" />
    <small id="imageSize">上传图片大小为<span id="imgSizeSpan"></span>，格式为<span id="imgPatternSpan"></span></small>
<!--    <small>上传图片大小为38*142，格式为jpg、png。</small>-->
	<div id="uploadImgDiv" style="padding:10px 0 ;display:none" align="center">
    <img id="bannerImageData" style="border:3px solid #dfdfdf" />
    </div>
    <div id="uploadImgBannerButtonDiv" class="c" style="display:none"><button type="button" id="uploadImgBannerButton" class="btnBY" >提交</button></div>
	</div>
</div>
<!--预览-->
<div id="preview" class="popShow collapsed">
<h2><img src="images/ico_popclose.gif" alt="关闭"
	class="fr previewColse">预览</h2>
<div><iframe id="previewPage" frameborder=0 width=320px
	height=480px marginheight=0 marginwidth=0 scrolling=no
	src="javascript:void(0)"></iframe></div>
</div>
<div id="navigationPreviewPage" class="popShow collapsed">
<h2><img src="images/ico_popclose.gif" alt="关闭"
	class="fr navigationPreviewColse">预览</h2>
<div id="backGroupContent"></div>
</div>
<!--  审核提示 -->
<div id="leadToAuthPage" class="popShow collapsed" >
<h2>提示</h2>
<div id="backGroupContent"><p style="padding:5px">您的身份尚未验证，本广告已保存为草稿，请进行身份验证</p></div>
<div style="float:right;margin:10px"><button type="button" class="btnBY" id="btnLeadToAuth">立即验证</button></div>
</div>
<!--  子页面模版 -->
<!--  style="width: 520px;" -->
<div style="width: 490px;" id="popDiv2" class="popDiv collapsed ">

<h2><img class="fr" id="closePop2" alt="关闭" src="images/ico_popclose.gif">子页面模板选择</h2>
<div class="popCon">
<table
	cellspacing="0"
	cellpadding="0"
	border="0"
	style="margin: 0pt;"
	class="tabNF">
	<col width="45%" />
	<col />
	<tr>
		<td colspan="2">
		<div class="selectPicBox" id="adModels">
		<div class="outBox">
        <a class="demoType" href="javascript:void(0)" rev="1" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='1' />">
		<span class="pBoxCon">纯文字</span>
		<img alt="文字" src="images/pic_paper_01.gif">
		</a>
		<c:if test="${!baseRole.ossSales}">
        <span class="yuan">&yen; <airad:sysConfig type='SHOW_TYPE' flag='out' key='1' /></span>
		</c:if>
        </div>
        
		<div class="outBox">
		<a class="demoType" href="javascript:void(0)" rev="2" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='2' />">
		<span class="pBoxCon">图文</span>
		<img alt="图文" src="images/pic_paper_02.gif">
		</a>
		<c:if test="${!baseRole.ossSales}">
        <span class="yuan">&yen; <airad:sysConfig type='SHOW_TYPE' flag='out' key='2' /></span>
		</c:if>
        </div>
        
		<div class="outBox">
		<a class="demoType" href="javascript:void(0)" rev="3" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='3' />">
		<span class="pBoxCon">相册</span>
		<img alt="相册" src="images/pic_paper_03.gif">
		</a>
		<c:if test="${!baseRole.ossSales}">
        <span class="yuan">&yen; <airad:sysConfig type='SHOW_TYPE' flag='out' key='3' /></span>
		</c:if>
        </div>
        
		<div class="outBox">
		<a class="demoType" href="javascript:void(0)" rev="4" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='4' />">
		<span class="pBoxCon">地图</span>
		<img alt="地图" src="images/pic_paper_04.gif">
		</a>
		<c:if test="${!baseRole.ossSales}">
        <span class="yuan">&yen; <airad:sysConfig type='SHOW_TYPE' flag='out' key='3' /></span>
		</c:if>
        </div>
        
		<div class="outBox">
		<a class="demoType" href="javascript:void(0)" rev="5" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='5' />">
		<span class="pBoxCon">淘宝商品</span>
		<img alt="淘宝商品 - 展示3个淘宝商品" src="images/pic_paper_05.gif">
		</a>
		<c:if test="${!baseRole.ossSales}">
        <span class="yuan">&yen; <airad:sysConfig type='SHOW_TYPE' flag='out' key='5' /></span>
		</c:if>
        </div>
<!--		<a class="demoType" href="javascript:void(0)" rev="5" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='5' />">-->
<!--		<span class="pBoxCon">淘宝商品<span class="sml"><sup>&yen;</sup><airad:sysConfig type='SHOW_TYPE' flag='out' key='5' /></span></span>-->
<!--		<img alt="拨打电话" src="images/pic_paper_07.gif">-->
<!--		</a>-->
 		
		<div class="outBox">
		<a class="demoType"   href="javascript:void(0)" rev="6" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='6' />">
		<span class="pBoxCon">淘宝直链</span>
		<img alt="淘宝直链 - 展示您填写的淘宝店铺" src="images/pic_paper_06.gif" />
		</a>
		<c:if test="${!baseRole.ossSales}">
        <span class="yuan">&yen; <airad:sysConfig type='SHOW_TYPE' flag='out' key='6' /></span>
		</c:if>
        </div> 		
		<div class="outBox">
		<a class="demoType" href="javascript:void(0)" rev="7" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='7' />">
		<span class="pBoxCon">安卓下载</span>
		<img alt="安卓应用" src="images/pic_paper_09.gif">
		</a>
		<c:if test="${!baseRole.ossSales}">
        <span class="yuan">&yen; <airad:sysConfig type='SHOW_TYPE' flag='out' key='7' /></span>
		</c:if>
        </div>
		<div class="outBox">
		<a class="demoType" href="javascript:void(0)" rev="8" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='8' />">
		<span class="pBoxCon">iPhone下载
		</span>
		<img alt="iPhone应用 " src="images/pic_paper_08.gif">
		</a>
		<c:if test="${!baseRole.ossSales}">
        <span class="yuan">&yen; <airad:sysConfig type='SHOW_TYPE' flag='out' key='8' /></span>
		</c:if>
		</div>
		<div class="outBox collapsed">
		<a class="demoType collapsed" href="javascript:void(0)" rev="9" rel="<airad:sysConfig type='SHOW_TYPE' flag='out' key='9' />">
			<span class="pBoxCon">电话直拨</span>
			<img title="电话直拨" src="images/pic_paper_07.gif">
		</a>
		<c:if test="${!baseRole.ossSales}">
        <span class="yuan">&yen; <airad:sysConfig type='SHOW_TYPE' flag='out' key='9' /></span>
		</c:if>
        </div>
 </div>
		</td>
	</tr>
	<tr>
		<th>&nbsp;</th>
		<td>
		<div class="btnBox"><a id="btn12" href="javascript:void(0)" class="btnY fl"><span>提交</span></a></div>
		</td>
	</tr>
</table>
</div>

</div>

<!--  素材库-->
<div
	style="top: 300px; left: 150px;"
	id="popDiv"
	class="popDiv collapsed">
<h2><img
	class="fr"
	id="closePop"
	alt="关闭"
	src="images/ico_popclose.gif">Banner 素材库</h2>
<div class="popCon">
<div class="tag"><a
	class="now"
	href="javascript:void(0)">黑色模版</a><a href="javascript:void(0)">红色模版</a><a href="javascript:void(0)">蓝色模版</a><a href="javascript:void(0)">绿色模版</a><a href="javascript:void(0)">黑色模版</a><a href="javascript:void(0)">自定义模版</a></div>
<div
	style="overflow-y: scroll; height: 300px;"
	class="picBox">
<div><img
	width="320"
	height="48"
	alt=" "
	src="javascript:void(0)"></div>
<div>
<div class="over">
<div><a
	id="btn03"
	href="javascript:void(0)"><strong>使用</strong></a></div>
<img
	width="320"
	height="48"
	alt=" "
	src="javascript:void(0)"></div>
<div>
</div>
</div>
</div>
</div>
</div>


