<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="日程管理" uxEnabled="true">
<eRedG4:ext.myux uxType="datatimefield"/>
<eRedG4:import src="/schedule/js/manageS.js" />
<eRedG4:ext.codeRender fields="SCHEURGENT,DEL,SCHESTATUS,LOCKED"/>
<eRedG4:ext.codeStore fields="SCHEURGENT,DEL,SCHESTATUS,LOCKED"/>

<eRedG4:import src="/schedule/js/scheduleShare.js"/>
<eRedG4:import src="/schedule/js/knowledgeShare_.js"/>
<eRedG4:body>
</eRedG4:body>
</eRedG4:html>