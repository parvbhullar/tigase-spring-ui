<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="UI组件角色授权">
<eRedG4:import src="/arm/js/rolePart.js" />
<eRedG4:body>
	<eRedG4:div key="deptTreeDiv"></eRedG4:div>
	<eRedG4:ext.codeRender fields="CMPTYPE" />
	<eRedG4:ext.codeStore fields="CMPTYPE" />
	<eRedG4:ext.codeRender fields="PARTAUTHTYPE" />
	<eRedG4:ext.codeStore fields="PARTAUTHTYPE" />
</eRedG4:body>
<eRedG4:script>
   var root_deptid = '<eRedG4:out key="rootDeptid" scope="request" />';
   var root_deptname = '<eRedG4:out key="rootDeptname" scope="request" />';
   var root_menuname = '<eRedG4:out key="rootMenuName" scope="request" />';
</eRedG4:script>
</eRedG4:html>