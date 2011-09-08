<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- 子页面1 -->
<div style="display: none; zoom: 1" class="demoCon22">

<h4>
<div class="fr">
<button class="pageDraftSave" type="button" style="cursor:pointer"><span
	style="display: none"></span>保存此广告页</button>
<span class="gray">|</span> <a href="javascript:void(0)"
	class="exhibitRich" style="display: none"><img src="/images/ico_infoc.gif" alt="展开" width="14" height="14" />展开</a><a
	href="javascript:void(0)" class="closeRich"><img src="/images/ico_infoo.gif" alt="收起" width="14" height="14" />收起</a> <span class="gray">|</span>
<a class="previewRichShow" href="javascript:void(0)">预览</a></span></div>
广告内容制作 - 子页面<span class="pnum"></span> <%
     //隐藏hidden
 %> <input type="hidden" class="showType"><input type="hidden"
	class="richId"> <input type="hidden" class="free richFree"
	value="0.12" /></h4>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
	<col width="20%" />
	<col width="80%" />
	<tr>
		<th>页面名称</th>
		<td><input class="half richMediaTitle" name="richMediaTitle"
			maxlength="25"></input></td>
	</tr>
	<tr>
		<th>模板选择</th>
		<td><a class="btn11" href="javascript:void(0)">选择模板&raquo;</a>
		<div class="selectPicBox"></div>
		</td>
	</tr>
	<tbody class="trWord collapsed">
		<tr>
			<th>文本编辑</th>
			<td><textarea class='htmlBox'></textarea></td>
		</tr>
		<tr>
			<th>联系电话</th>
			<td><input name="phone" class="wordPhone" type="text" class="small" maxlength="20" /></td>
		</tr>
	</tbody>
	<tbody class="trPiceWord collapsed">
		<tr>
			<th>排版方式</th>
			<td><select class="formatType">
				<option value="1">上图下文</option>
				<option value="2">左图右文</option>
				<option value="3">右图左文</option>
			</select></td>
		</tr>
		<tr>
			<th>图片上传</th>
			<td><!-- begin upload -->

			<div id="uploadWordPic"></div>
			<input type="file" class="uploadWordPicImg" /> <input
				class="uploadWordPicPath" type="hidden" /><small>图片宽高比为1:1，建议用120*120的图片。</small>
			<br />
			<!-- end upload --> 
			<img src="images/pic_nav_02.gif" alt=" " 
				class="picImg collapsed" width="120px" height="120px" /><input
				type="hidden" class="imgId" /></td>
		</tr>
		<tr>
			<th>文字内容</th>
			<td><textarea class='picHtmlBox' oncopy='return false'
				onpaste='return false'></textarea></td>
		</tr>
		<tr>
			<th>联系电话</th>
			<td><input name="phone" class="wordPicPhone" type="text" class="small" maxlength="20" /></td>
		</tr>
	</tbody>
	<tr class="trPice collapsed">
		<th>图片上传</th>
		<td><!-- begin upload --><input type="hidden" class="picId">
		<div id="uploadPic"></div>
		<input type="file" class="uploadPicImg"></input><small>上传推荐宽高为320px*480px，或于此等比例图片。最多上传5张图片。</small>
		<div class="clean">
		<div class="norPicBox fl collapsed"><a class="delImg"
			href="javascript:void(0)"><img src="/images/ico_close_r.gif"
			alt="删除" /></a> <img src="images/pic_nav_02.gif" alt="" class="picImg"
			width="80px" height="120px" /> <input type="hidden" class="imgId"></input>
		</div>
		</div>
		<!-- end upload --></td>
	</tr>
	<tr class="trMap collapsed">
		<th><input type="hidden" class="mapCenter" /><input type="hidden" class="mapId" /> 地图设置</th>
		<td>
		<div class="mb">
		<button class="addPoint" type="button"><span
			style="display: none">eeeee</span>添加坐标</button>
		<small>点击按钮添加您需要在地图上显示的坐标点，最多添加5个。</small></div>
		<div class="map" style="width: 320px; height: 480px"></div>
		</td>
	</tr>
	<!-- begin taobao -->
	<tr class="trTaobaoDemo collapsed">
		<th>淘宝商品ID</th>
		<td><input class="short numIID" maxlength="15" /> <a
			href="javascript:void(0)" class="addTaobao">提交</a><img
			align="absmiddle" alt="loading" src="images/ico_loading.gif"
			style="display: none" /><span class="taobaoInfo onError"
			style="display: none"></span>
            <small style="display:block">请输入您想要添加的淘宝商品ID</small>
            <span class="taobaoDesc"></span>
            
            </td>
	</tr>
	<!-- end taobao -->
	<!-- begin taobao -->
	<tr class="trTaobao2Demo collapsed">
		<th>请输入淘宝网址</th>
		<td>http:// <input class="long taobaoUrl" onblur="cleanUrl(this,'http://')" maxlength="250" /> <input class="long taobaoId" type="hidden"/> 
		<br /><small>输入的网址只能为 taobao.com/ 以及 tmall.com/ 相关的链接。</small>
		</td>
	</tr>
	<!-- end taobao -->
	<!-- begin mrket -->
	<tbody class="trMarket collapsed">	<tr><th>应用图片</th><td>
		<!-- begin upload -->
		<input type="file" class="uploadMarketImg"></input><small>上传推荐宽高为150px*150px，或于此等比例图片。</small>
		<br />
		 <img src="images/pic_nav_02.gif" alt="" class="MarketImg collapsed"
			width="150px" height="150px" /> <input type="hidden" class="marketImgId"></input>
		
		<!-- end upload -->
	</td></tr>
	<tr><th>应用名称</th><td>
	<input type="hidden" class="marketId"></input><input type="text" class="marketName" maxlength="20"></input>
	<small>最多只能输入7个中文字。</small>
	<br />
	</td></tr>
	<tr class="trMarketAndroid collapsed">
		<th>Android 应用地址<input type="hidden" class="marketAndroidId"></input></th>
		<td>market://<input class="long marketAndroidUrl" onblur="cleanUrl(this,'market://')" maxlength="250" /> <input class="long taobaoId" type="hidden"/> 
		<br /><small>输入的网址只能为details?id=  相关的链接。例如market://details?id=com.example.admob.lunarlander</small>
		</td>
	</tr>
	<tr class="trMarketIphone collapsed">
		<th>iPhone  应用地址<input type="hidden" class="marketIponeId"></th>
		<td>http://<input class="long marketIphoneUrl" onblur="cleanUrl(this,'http://')" maxlength="250" /> <input class="long taobaoId" type="hidden"/> 
		<br /><small>输入的网址只能为itunes.apple.com/ 相关的链接。例如：itunes.apple.com/us/app/color-it/id315457074?mt=8</small>
		</td>
	</tr>
	</tbody>
	<!-- end market -->
	
</table>
</div>