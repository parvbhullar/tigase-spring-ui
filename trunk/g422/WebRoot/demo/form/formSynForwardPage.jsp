<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/include/taglib.jsp"%>
<eRedG4:html title="表单同步提交后跳转的页面" >
<eRedG4:body>
页面一提交的值为：<br>
<%=request.getAttribute("value") %>
</eRedG4:body>
</eRedG4:html>