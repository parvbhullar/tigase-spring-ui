<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="获取服务器信息及内存CPU实时监控" fcfEnabled="true">
<eRedG4:import src="/arm/js/serverInfo.js" />
<eRedG4:body>
	<eRedG4:flashReport type="L" dataVar="xmlString" id="jvmMemChart" style="margin-top:50px"
		width="680" height="250" align="center" visible="false" />
</eRedG4:body>
<eRedG4:script>
var jsonInfo = <eRedG4:out key="jsonInfo" scope="request" />;
</eRedG4:script>
</eRedG4:html>