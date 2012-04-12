<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="日程管理" uxEnabled="true">
<link rel='stylesheet' type='text/css' href='s/js/Multiselect.css'>
<eRedG4:ext.myux uxType="datatimefield"/>
<eRedG4:import src="/schedule/js/manageAllS.js" />
<eRedG4:ext.codeRender fields="SCHEURGENT,REMIND,SCHESTATUS,LOCKED"/>
<eRedG4:ext.codeStore fields="SCHEURGENT,REMIND,SCHESTATUS,LOCKED"/>
<eRedG4:body>
</eRedG4:body>
</eRedG4:html>