<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<head>
<META   HTTP-EQUIV= "pragma "   CONTENT= "no-cache "> 
<META   HTTP-EQUIV= "Cache-Control "   CONTENT= "no-cache,   must-revalidate "> 
<META   HTTP-EQUIV= "expires "   CONTENT= "Wed,   26   Feb   1997   08:21:57   GMT "> 

</head>
<eRedG4:html title="人员管理与授权">
<script>
var cp;
</script>
<eRedG4:import src="/resource/jquery/jquery-1.3.2.min.js"/>
<eRedG4:import src="/resource/jquery/jquery.form.js"/>
<eRedG4:import src="/resource/jquery/json2.js"/>

<eRedG4:import src="/workdiary/js/Extensible-config.js"/>
<eRedG4:import src="/workdiary/js/lib/extensible-all.js"/>
<eRedG4:import src="/workdiary/js/calendar/data/events.js"/>
<eRedG4:import src="/workdiary/js/calendar/basic.js"/>
<eRedG4:import src="/workdiary/js/calendar/MemoryEventStore.js"/>
<eRedG4:body>

    <div id="simple" class=""></div>
    
  
</eRedG4:body>
<eRedG4:ext.codeRender fields="SEX,LOCKED,USERTYPE"/>
<eRedG4:ext.codeStore fields="SEX,LOCKED,USERTYPE:3"/>
</eRedG4:html>