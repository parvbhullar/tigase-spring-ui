<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="UI组件托管" uxEnabled="true">
<eRedG4:import src="/arm/js/managePart.js"/>
<eRedG4:body>
<eRedG4:div key="menuTreeDiv" />
<eRedG4:ext.codeRender fields="CMPTYPE"/>
<eRedG4:ext.codeStore fields="CMPTYPE"/>

</eRedG4:body>
<eRedG4:script>
   var root_menuname = '<eRedG4:out key="rootMenuName" scope="request"/>';
</eRedG4:script>
</eRedG4:html>