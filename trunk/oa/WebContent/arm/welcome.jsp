<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html showLoading="false" urlSecurity2="false">
<eRedG4:import src="/arm/js/portal.js" />
<form action='./k.xzb?reqCode=queryDocsForManage' id="indexPortalForm">
		<input type="hidden" name="start" 		id="start" 	value="0"></input>
		<input type="hidden" name='limit' 		id="limit"	value="10"/>
		<input type="hidden" name="postType" value="1"></input>
</form>
<eRedG4:body>
</eRedG4:body>
</eRedG4:html>