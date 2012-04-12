<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="eRedG4.Report:由易道报表组件驱动" urlSecurity2="false">
<eRedG4:body>
    <%-- 为了兼容各种浏览器applet标签的属性被配置2次 --%>
	<APPLET CODE="org.eredlab.g4.rif.report.jasper.applet.EmbeddedViewerApplet.class" CODEBASE="<%=request.getContextPath()%>/resource/jasper/applets"
		ARCHIVE="eredg4-report-applet.jar,jasperreports-applet-3.7.0.jar,commons-logging-1.0.4.jar,commons-collections-2.1.1.jar"
		WIDTH="100%" HEIGHT="100%">
		<PARAM NAME=CODE VALUE="org.eredlab.g4.rif.report.jasper.applet.EmbeddedViewerApplet.class">
		<PARAM NAME=CODEBASE VALUE="<%=request.getContextPath()%>/resource/jasper/applets">
		<PARAM NAME=ARCHIVE
			VALUE="eredg4-report-applet.jar,jasperreports-applet-3.7.0.jar,commons-logging-1.0.4.jar,commons-collections-2.1.1.jar">
		<PARAM NAME="type" VALUE="application/x-java-applet;version=1.2.2">
		<PARAM NAME="scriptable" VALUE="false">
		<PARAM NAME="REPORT_URL"
			VALUE="<%=request.getAttribute("dataUrl") %>">
	</APPLET>
</eRedG4:body>
</eRedG4:html>