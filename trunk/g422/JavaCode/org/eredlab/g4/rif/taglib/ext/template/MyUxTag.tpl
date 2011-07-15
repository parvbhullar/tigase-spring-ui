<!-- Datatimefield扩展组件所需的资源 -->
#if(${uxType} == "datatimefield")
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/myux/datatimefield/css/Spinner.css"/>
<script type="text/javascript" src="${contextPath}/resource/myux/datatimefield/Spinner.js"></script>
<script type="text/javascript" src="${contextPath}/resource/myux/datatimefield/SpinnerField.js"></script>
<script type="text/javascript" src="${contextPath}/resource/myux/datatimefield/DateTimeField.js"></script>
#end 

<!-- GridSummary扩展组件所需的资源 -->
#if(${uxType} == "gridsummary")
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/myux/gridsummary/css/Ext.ux.grid.GridSummary.css"/>
<script type="text/javascript" src="${contextPath}/resource/myux/gridsummary/Ext.ux.grid.GridSummary.js"></script>
#end

<!-- SWFUpload扩展组件所需的资源 -->
#if(${uxType} == "swfupload")
<link rel="stylesheet" type="text/css" href="${contextPath}/resource/myux/uploadpanel/css/uploadPanel.css"/>
<script type="text/javascript" src="${contextPath}/resource/myux/uploadpanel/js/swfupload.js"></script>
<script type="text/javascript" src="${contextPath}/resource/myux/uploadpanel/js/uploadPanel.js"></script>
#end
