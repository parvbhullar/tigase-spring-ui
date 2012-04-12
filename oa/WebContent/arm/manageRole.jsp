<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="角色管理与授权">
<eRedG4:import src="/arm/js/manageRole.js"/>
<eRedG4:ext.codeRender fields="ROLETYPE,LOCKED"  />
<eRedG4:ext.codeStore fields="LOCKED,ROLETYPE:3"/>
<eRedG4:body>
<eRedG4:div key="deptTreeDiv"></eRedG4:div>
</eRedG4:body>
<eRedG4:script>
   var root_deptid = '<eRedG4:out key="rootDeptid" scope="request"/>';
   var root_deptname = '<eRedG4:out key="rootDeptname" scope="request"/>';
   var login_account = '<eRedG4:out key="login_account" scope="request"/>';
</eRedG4:script>
</eRedG4:html>