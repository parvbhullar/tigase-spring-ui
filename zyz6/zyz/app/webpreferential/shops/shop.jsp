<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
com.linkage.app.gqt.webpreferential.index.entitys.User user = (com.linkage.app.gqt.webpreferential.index.entitys.User)session.getAttribute("userinfo");
String dn="";
if(user!=null)
	dn = user.getDn();
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<c:url value="../css/style.css"/>" rel="stylesheet" type="text/css" ></link>
		<title>商家信息</title>
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<script type="text/javascript">
			function download(id,shopId,message){
				
				if('<%=user%>'=='null')
				{
					alert('请登录');
					return;
				}	
				$.ajax({
					type: "post",
					url: "../download.action",
					data: "id="+id+"&shopId="+shopId+"&message="+message+"&dn=<%=dn%>",
					success: function(msg){
						alert("下载成功!");
					},
					error:function(msg){
						alert("下载失败!");
					}
				});
			}
		</script>
	</head>
	<body>
		<div id="header">
			<iframe id="top" src="../include/head.html" style="width:960px; height:111px " frameborder="0"  scrolling="no"></iframe>
		</div>
		<div id="main" class="business">
			<div id="topCon">
				<c:if test="${!empty shop }">
				<div class="box1">
					<!-- 店铺图片的大小为 151*114 -->
					<div class="cpic">
						<a href="javascript:void(0);">
							<img src="<c:url value="${shop.shopImage}"/>" width="151"/>
						</a>
					</div>
					<div class="con">
        				<h2 class="tit">商家信息</h2>
        				${shop.introduction}
           			</div>
           			<!-- 店铺发布优惠券数量图片的大小为 151*130 -->
           			<div align="center">
           				<font size="4">已发布优惠券信息：</font><br/><br/>
           				<c:set var="len1" value="${shop.numberOfcoupon}a"/>
           				<c:set var="len" value="${fn:length(len1)}"/>
           				<c:forEach var="i" begin="0" end="${len-2}">
           					<img src="../images/<c:out value="${fn:substring(len1, i, i+1)}.gif"/>"/>
						</c:forEach>
           			</div>
           		</div>
           		</c:if>
           	</div>
           	
  			<div  id="left">
  				<div class="tp"></div>
  				<div class="box4">
  					<h2>优惠券列表</h2>
  					
  						<!-- 优惠券图片的大小为 296*144 -->
  						<c:forEach var="coupon" items="${shopCoupons}">
  						<div class="item">
  						<div class="pic">
  							<a href="<c:url value="../coupons/coupon.action?id=${coupon.id}"/>">
  								<img width="269" height="144" src="<c:url value="${coupon.pic}"/>">
  							</a>
  						</div>
      					<div class="con">
<!--      						<ul>-->
<!--      							<li>-->
      							${coupon.introduction}
<!--      							</li>-->
<!--      						</ul>-->
        					<div class="fl">
        						<strong>发布日期：${coupon.createTime}</strong><br />
        						<strong class="orange">
        							<a href="<c:url value="../coupons/coupon.action?id=${coupon.id}"/>">查看详细>></a>
        						</strong>
        					</div>
           					<div class="fr">
           						<p><a href="javascript:void(0)" onclick="download(${coupon.id},${coupon.shopId },'${coupon.message}');"><img src="../images/btn_download.gif"></a></p>
           						<p><a target="_blank" href="../coupons/print.jsp?pic=${coupon.pic}"><img src="../images/btn_print.gif"></a></p>
           					</div>
      					</div></div>
      					</c:forEach>
      				
      				<!-- 分页标签开始 -->
      				<script type="text/javascript">
			  		function goPage(page){
			  			if(page==''){
						  	page=1;
					  	}
			  	  		document.location.href='shop.action?id=${shop.id}&page='+page;
			  		}
			  		</script>
      				<div class="pagination">
      					${toolNav }
      				</div>
      				<!-- 分页标签结束 -->
      			</div>
      			<div class="bt"></div>
			</div>

			<div id="right">
  				<div class="box">
    				<h3 id="new">最新加入优惠券</h3>
    				<ul>
     					<c:import url="/app/webpreferential/coupons/newsCouponList.action?returnObjectNum=10"></c:import>
    				</ul>
  				</div>
  				<div class="box">
    				<h3 id="download">最热下载优惠券</h3>
    				<ul>
      					<c:import url="/app/webpreferential/coupons/hotCouponList.action?returnObjectNum=10"></c:import>
          			</ul>
  				</div>
			</div>
		</div>
		<div id="footer"><img src="../images/footer.gif" /></div>
		<div align="center">页面处理时间:<c:out value="${(postTime-preTime)}"/>毫秒</div>
	</body>
	<%@ include file="/common/google.jsp"%>
</html>