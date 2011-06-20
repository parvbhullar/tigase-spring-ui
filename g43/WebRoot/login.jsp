<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="南京地铁" showLoading="false" exportParams="true">
<eRedG4:import src="/arm/js/login.js" />
<eRedG4:body>
	<div id="hello-win" class="x-hidden">
		<div id="hello-tabs">
			<img border="0" width="450" height="70" src="<%=request.getAttribute("bannerPath") == null ? request.getContextPath() + "/resource/image/login_banner.png" : request.getAttribute("bannerPath")  %>" />
		</div>
	</div>
	<div id="aboutDiv" class="x-hidden"
		style='color: black; padding-left: 10px; padding-top: 10px; font-size: 12px'>
		
		
	</div>
	<div id="infoDiv" class="x-hidden"
		style='color: black; padding-left: 10px; padding-top: 10px; font-size: 12px'>
		请注册后登录...<br> <br> 做最厚道的开源项目,将开源进行到底!<br> 
	</div>
</eRedG4:body>
</eRedG4:html>