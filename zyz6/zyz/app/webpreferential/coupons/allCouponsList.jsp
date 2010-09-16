<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../css/style.css" rel="stylesheet" type="text/css" ></link>
		<title>优惠券列表</title>
	</head>
	<body>
	<div id="header">
		<iframe id="top" src="../include/head.html" style="width:960px; height:111px " frameborder="0"  scrolling="no"></iframe>
	</div>
		<div id="main" class="coupon">
  			<div  id="left">
  				<!-- 优惠券展示div开始 -->
				<c:forEach var="coupon" items="${coupons}">
				<div class="box2">
     	 			<div class="pic">
      				<!-- image尺寸269*144 -->
      					<a href="coupon.action?id=${coupon.id}" target="_blank"><img width="269" height="144" src="<c:url value="${coupon.pic }"/>" /></a>
      				</div>
      				<div class="con">
       	 				<!-- <ul>
          					<li>1、本电子优惠券有效期：${coupon.startTimeWithFm1 }-${coupon.endTimeWithFm1 };</li>
          					<li>${coupon.introduction }
       			 		</ul> -->
       			 		${coupon.introduction}
       			 		<a href="coupon.action?id=${coupon.id}" class="fr" target="_blank"><img src="../images/redmore.gif" /></a>
        				<p><strong>发布商家:</strong> ${coupon.shopName }</p>
         				<p><strong>发布日期:</strong>${coupon.createTime }</p>
      				</div>
  				</div>
				</c:forEach>
  				<!-- 优惠券展示Div结束 -->
  
  				<!-- 分页导航开始 -->
  				<script type="text/javascript">
				  	function goPage(page){
				  		if(page==''){
						  	page=1;
					  	}
				  	  	document.location.href='index.action?page='+page;
				  	}
  				</script>
  				<div class="pagination">${toolNav }</div>
			</div>
			
			<div id="right">
  				<!-- 最近加入优惠券开始 -->
  				<div class="box">
    				<h3  id="new">最新加入优惠券</h3>
    				<ul>
      					<c:import url="/app/webpreferential/coupons/newsCouponList.action?returnObjectNum=10"></c:import>
    				</ul>
  				</div>
  				<!-- 最近加入优惠券结束 -->
  				<!-- 最热下载优惠券开始 --> 
  				<div class="box">
    				<h3  id="download">最热下载优惠券</h3>
    				<ul>
    					<c:import url="/app/webpreferential/coupons/hotCouponList.action?returnObjectNum=10"></c:import>
    				</ul>
  				</div>
  				<!-- 最热下载优惠券结束 --> 
			</div>
		</div>
		<div id="footer"><img src="../images/footer.gif" /></div>
		<div align="center">页面处理时间:<c:out value="${(postTime-preTime)}"/>毫秒</div>
	</body>
	<%@ include file="/common/google.jsp"%>
</html>
