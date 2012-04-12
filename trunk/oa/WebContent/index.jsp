<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="${sysTitle}" showLoading="false" exportParams="true" isSubPage="false"
	exportUserinfo="true">
<eRedG4:import src="/resource/commonjs/extTabCloseMenu.js" />
<eRedG4:import src="/arm/js/index.js" />
<eRedG4:ext.codeStore fields="SEX"/>
<eRedG4:body>
	<eRedG4:div key="themeTreeDiv" cls="x-hidden"></eRedG4:div>
	<eRedG4:div key="previewDiv" cls="x-hidden">
		<img src="./resource/image/theme/default.jpg" />
	</eRedG4:div>
	<eRedG4:arm.Viewport northTitle="${sysTitle}" westTitle="${westTitle}" />
</eRedG4:body>
</eRedG4:html>