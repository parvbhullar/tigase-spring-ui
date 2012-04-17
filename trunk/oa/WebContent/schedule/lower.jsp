<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<head>
<META   HTTP-EQUIV= "pragma "   CONTENT= "no-cache "> 
<META   HTTP-EQUIV= "Cache-Control "   CONTENT= "no-cache,   must-revalidate "> 
<META   HTTP-EQUIV= "expires "   CONTENT= "Wed,   26   Feb   1997   08:21:57   GMT "> 

</head>
<eRedG4:html title="人员管理与授权">
<eRedG4:import src="/resource/jquery/jquery-1.3.2.min.js"/>
<eRedG4:import src="/resource/jquery/jquery.form.js"/>
<eRedG4:import src="/resource/jquery/json2.js"/>
<eRedG4:import src="/schedule/js/lower.js"/>
<eRedG4:ext.codeRender fields="SEX,LOCKED,USERTYPE"/>
<eRedG4:ext.codeStore fields="SEX,LOCKED,USERTYPE:3"/>
<eRedG4:body>
<eRedG4:div key="deptTreeDiv"></eRedG4:div>
<eRedG4:ext.myux uxType="swfupload"/>
<eRedG4:ext.myux uxType="htmleditor"/>
<eRedG4:ext.myux uxType="datatimefield"/>
<eRedG4:ext.uiGrant/>	
</eRedG4:body>
<eRedG4:script>
   var userid = '<eRedG4:out key="userid" scope="request"/>';
   var username = '<eRedG4:out key="username" scope="request"/>';
   var login_account = '<eRedG4:out key="login_account" scope="request"/>';
</eRedG4:script>
</eRedG4:html>