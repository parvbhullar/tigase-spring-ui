<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.linkage.framework.util.BASE64"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String param = BASE64.encode("0|王家乐|15077855677|13776657341|123456");
%>
<form name='param' action="interfaceIndex.action" method="post">
<input type="text" name="param" id="param" value="<%=param%>">
<input type="submit" value="连接">
</form>
