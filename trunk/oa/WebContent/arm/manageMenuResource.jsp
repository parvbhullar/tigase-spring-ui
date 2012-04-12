<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="菜单资源管理">
<eRedG4:import src="/arm/js/manageMenuResource.js"/>
<eRedG4:ext.codeRender fields="MENUTYPE,LEAF,EXPAND"/>
<eRedG4:ext.codeStore fields="EXPAND"/>
<eRedG4:body>
<eRedG4:div key="menuTreeDiv"></eRedG4:div>
</eRedG4:body>
<eRedG4:script>
   var root_menuname = '<eRedG4:out key="rootMenuName" scope="request"/>';
</eRedG4:script>
</eRedG4:html>