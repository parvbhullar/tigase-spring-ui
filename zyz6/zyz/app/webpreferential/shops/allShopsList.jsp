<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value="../css/style.css"/>" rel="stylesheet" type="text/css" ></link>
		<title>商户列表</title>
	</head>
	<body>
		<div id="header">
			<iframe id="top" src="../include/head.html" style="width:960px; height:111px " frameborder="0"  scrolling="no"></iframe>
		</div>
		<div id="main">
	  		<div  id="left">
	  			<c:forEach var="shop" items="${shopCouponlist}">
	    		<div class="box1">
	    			<!-- 店铺图片的大小为 196*148 -->
	      			<div class="pic"><img src="<c:url value="${shop.shopImage}"/>" width="196"/></div>
	      			<div class="con">
		        		<h2>商家信息</h2>
		        			<c:set var="introduction" value="${shop.introduction }"/>
		        			<c:out value="${fn:substring(introduction,0,90)}......" escapeXml="false"/> 
				        <div class="list">
				        	<!-- 优惠券图片的大小为 71*61 -->
				        	<c:forEach var="coupon" items="${shop.couponsList}" begin="0" end="3" step="1">
				          	<div class="spic">
				          		<a href="<c:url value="../coupons/coupon.action?id=${coupon.id}"/>" target="_blank">
				          			<img src="<c:url value="${coupon.pic}"/>" width="71" height="61"/>
				          		</a>
				          		<span></span>
				          	</div>
				          	</c:forEach>
				          	<div class="more">
				          		<a href="<c:url value="shop.action?id=${shop.id}&page=1"/>" target="_blank">
				          			<img src="../images/more.jpg" />
				          		</a>
				          	</div>
				        </div>
	      			</div>
	    		</div>
	   			</c:forEach>
	   			<!-- 分页标签开始 -->
				<script type="text/javascript">
			  	function goPage(page){
			  		if(page=='')
				  	{
					  	page=1;
				  	}
			  	  	document.location.href='index.action?page='+page;
			  	}
			  	</script>
	   			<div class="pagination">
	   				${toolNav }
	   			</div>
	   			<!-- 分页标签结束 -->
	  		</div>
	  		
	  		<div id="right">
	    		<div class="box">
	      			<h3  id="new">最新加入商家</h3>
			      	<ul>
				        <c:import url="/app/webpreferential/shops/newShopsList.action?returnObjectNum=10"></c:import>
			      	</ul>
	    		</div>
	        	<div class="box">
	      			<h3  id="preferential">最多优惠商家商家</h3>
			      	<ul>
			      		<c:import url="/app/webpreferential/shops/muchShopsList.action?returnObjectNum=10"></c:import>
			        </ul>
	    		</div>
	        	<div class="box">
	      			<h3  id="download">最热下载商家</h3>
			      	<ul>
			      		<c:import url="/app/webpreferential/shops/hotShopsList.action?returnObjectNum=10"></c:import>
			      	</ul>
	    		</div>
	  		</div>
		</div>
		<div id="footer"><img src="../images/footer.gif" /></div>
		<div align="center">页面处理时间:<c:out value="${(postTime-preTime)}"/>毫秒</div>
	</body>
	<%@ include file="/common/google.jsp"%>
</html>