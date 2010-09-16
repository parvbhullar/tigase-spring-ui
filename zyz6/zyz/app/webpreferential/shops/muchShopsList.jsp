<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<c:if test="${empty shopList}">
	暂无最多优惠商家
</c:if>
<c:if test="${not empty shopList}">
	<c:forEach var="shop" items="${shopList}">
		<li><a href="<c:url value="shop.action?id=${shop.id}&page=1"/>" target="_blank">${shop.name }</a></li>
	</c:forEach>
</c:if>