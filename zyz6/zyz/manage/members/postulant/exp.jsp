<%@page import="java.io.File"%><%@ page language="java" contentType="application/x-msdownload; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.linkage.framework.util.ExcelUtil" %>
<%
	java.io.File file = (java.io.File)request.getAttribute("downloadfile");
	ExcelUtil.exportFile(response,file,false);
	out.clear(); 
	out = pageContext.pushBody();	
%>