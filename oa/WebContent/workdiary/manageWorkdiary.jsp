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

<eRedG4:import src="/workdiary/js/Extensible-config.js"/>
<eRedG4:import src="/workdiary/js/lib/extensible-all.js"/>
<eRedG4:import src="/workdiary/js/calendar/data/events.js"/>
<eRedG4:import src="/workdiary/js/calendar/basic.js"/>
<eRedG4:import src="/workdiary/js/calendar/MemoryEventStore.js"/>


<eRedG4:ext.codeRender fields="SEX,LOCKED,USERTYPE"/>
<eRedG4:ext.codeStore fields="SEX,LOCKED,USERTYPE:3"/>
<eRedG4:body>
<p id="errChk"><label for="forceError"><input type="checkbox" name="forceError" id="forceError"> Simulate server errors</label></p>

<div id="sample-overview">
	<!-- 
        <h1>Basic Calendar Examples</h1>
        <p>An assortment of basic, standalone CalendarPanels with different configurations. Note that unless otherwise indicated the calendars
        all share the same underlying data store, so changes in one calendar will update the others.</p>
        
        <p class="view-src"><a target="_blank" href="basic.js">View the source</a></p>
    </div>
    
    <h2>Simplest Example</h2>
    <p>This is an example of the most basic CalendarPanel configuration with all default options.  It does not have multiple calendar support
    by default (unless you provide a calendar store) and so all events simply use a deafult color.</p>
     -->
    <div id="simple" class="sample-ct"></div>
    
    <!-- 
    <div id="panel" class="sample-ct"></div>
	 -->
</eRedG4:body>

</eRedG4:html>