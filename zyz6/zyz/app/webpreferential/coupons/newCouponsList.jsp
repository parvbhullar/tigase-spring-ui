<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<c:if test="${empty news}">
	暂无最新加入优惠券
</c:if>
<c:if test="${not empty news}">
	<c:forEach var="coupon" items="${news}">
		<li><a href="../coupons/coupon.action?id=${coupon.id}" target="_blank">${coupon.name }(${coupon.shopName })</a></li>
	</c:forEach>
</c:if>