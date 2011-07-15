<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="Report:由报表组件驱动">
<eRedG4:body>
</eRedG4:body>
<script language="JavaScript">
window.onload = function(){
    window.location.href = '<%=request.getAttribute("dataUrl")%>';
}
</script>

</eRedG4:html>