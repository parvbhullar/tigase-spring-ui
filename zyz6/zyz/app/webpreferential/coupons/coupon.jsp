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
		<link href="../css/style.css" rel="stylesheet" type="text/css" ></link>
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<title>${coupon.name }-优惠券信息</title>
		<script type="text/javascript">
			function download(id,shopId,message){
				if('<%=user%>'=='null')
				{
					alert('请登录');
					return;
				}else{
					$.ajax({
						type: "post",
						url: "../download.action",
						data: "id="+id+"&shopId="+shopId+"&message="+message+"&dn=<%=dn%>",
						success: function(msg){
							alert( "下载成功!" );
						},
						error:function(msg){
							alert("下载失败!");
						}
					});
				}
			}
			function addfavorite(href,title){
			   if (document.all){
			      window.external.addFavorite(href,title);
			   }else if (window.sidebar){
			      window.sidebar.addPanel(title, href, "");
			   }
			}		
		</script>
	</head>
	<body>
		<div id="header">
			<iframe id="top" src="../include/head.html" style="width:960px; height:111px " frameborder="0"  scrolling="no"></iframe>
		</div>
		<div id="main" class="recommend">
  			<div class="bannartp">
  			
  					<!-- 广告image尺寸805*86 -->
  					<c:if test="${ad.pic==null}">
                  		<img src="../images/ad01.jpg" width="805" height="86">
                  	</c:if>
                 	<c:if test="${ad.pic!=null}">
                 		<a href="coupon.action?id=${ad.couponId}"><img src="<c:url value="${ad.pic}"/>" width="805" height="86"></a>
                 	</c:if>
  			</div>
  			<div class="wapper">
    			<div class="tp"></div>
    			<div class="box6">
	      			<div class="btn">
	      				<a href="javascript:void(0);" onclick="download(${coupon.id},${coupon.shopId },'${coupon.message}');">
	      					<img src="../images/btn_download.gif" />
	      				</a>
	      				<a target="_blank" href="print.jsp?pic=${coupon.pic}"><img src="../images/btn_print.gif" /></a>
	      				<a href="javascript:void(0);"><img src="../images/btn_sign.gif" /></a>
	      				<span>好友推荐
	      					<a title="分享本页到QQ空间" href="javascript:window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+encodeURIComponent(location.href)+'&rcontent=','_blank','scrollbars=no,width=600,height=470,left=75,top=20,status=no,resizable=yes'); void(0)">
	      						<IMG alt=分享到QQ空间 src="http://qzone.qq.com/favicon.ico" align=Middle border=0>
<!--	      						&nbsp;QQ空间-->
	      					</a>
	      					<a title="分享本页到新浪微博" href="javascript:window.open('http://v.t.sina.com.cn/share/share.php?title='+encodeURIComponent(document.title.substring(0,76))+'&url='+encodeURIComponent(location.href)+'&rcontent=','_blank','scrollbars=no,width=620,height=450,left=75,top=20,status=no,resizable=yes'); void(0)">
	      						<img alt=分享噢贝比obabys到QQ书签 src="http://timg.sjs.sinajs.cn/miniblog2style/images/toolbar/s_red.gif" align=Middle border=0"/>
<!--	      						&nbsp;新浪微博-->
	      					</a>
	      					<a title="分享本页到开心网" href="javascript:window.open('http://www.kaixin001.com/repaste/share.php?rtitle='+encodeURIComponent(document.title.substring(0,76))+'&rurl='+encodeURIComponent(location.href)+'&rcontent=','_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes'); void(0)">
	      						<IMG alt=分享噢贝比obabys到开心网 src="http://img1.kaixin001.com.cn/i/favicon.ico" align=Middle border=0>
<!--	      						&nbsp;开心网-->
	      					</a>
	      					<a title="分享本页到人人网" href="javascript:window.open('http://share.renren.com/share/buttonshare.do?title='+encodeURIComponent(document.title.substring(0,76))+'&link='+encodeURIComponent(location.href)+'&content=','_blank','scrollbars=no,width=600,height=436,left=75,top=20,status=no,resizable=yes'); void(0)">
	      						<IMG alt=分享噢贝比obabys到人人网 src="http://s.xnimg.cn/favicon-rr.ico" align=Middle border=0/>
<!--	      						&nbsp;人人网-->
	      					</a>
							<a title="分享本页到豆瓣网" href="javascript:window.open('http://www.douban.com/recommend/?title='+encodeURIComponent(document.title)+'&url='+encodeURIComponent(document.title.substring(0,76))+'&link='+encodeURIComponent(location.href)+'&content=','_blank','scrollbars=no,width=450,height=330,left=75,top=20,status=no,resizable=yes'); void(0)">
								<IMG alt=分享噢贝比obabys到豆瓣网 src="http://img2.douban.com/pics/fw2douban_s.png" align=Middle border=0/>
<!--								&nbsp;豆瓣网-->
							</a>
							<a title="分享本页到白社会" href="javascript:window.open('http://bai.sohu.com/share/blank/addbutton.do?from=tudou&title='+encodeURIComponent(document.title.substring(0,76))+'&link='+encodeURIComponent(location.href)+'&content=','_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes'); void(0)">
								<IMG alt=分享噢贝比obabys到白社会 src="http://s2.bai.itc.cn/r/i/favicon.ico" align=Middle border=0>
<!--								&nbsp;白社会-->
							</a>
							<a title="分享本页到QQ书签" href="javascript:window.open('http://shuqian.qq.com/post?from=3&title='+encodeURIComponent(document.title)+'&uri='+encodeURIComponent(document.location.href)+'&jumpback=2&noui=1','favit','width=930,height=470,left=50,top=50,toolbar=no,menubar=no,location=no,scrollbars=yes,status=yes,resizable=yes');void(0)">
								<IMG alt=分享噢贝比obabys到QQ书签 src="http://shuqian.qq.com/img/add.gif" align=Middle border=0>
<!--								&nbsp;QQ书签-->
    						</a>
    						<a title="分享本页到百度搜藏" href="javascript:window.open('http://cang.baidu.com/do/add?it='+encodeURIComponent(document.title.substring(0,76))+'&iu='+encodeURIComponent(location.href)+'&fr=ien#nw=1','_blank','scrollbars=no,width=740,height=450,left=75,top=20,status=no,resizable=yes'); void(0)">
    							<IMG border=0 alt=分享噢贝比obabys到百度搜藏 align=Middle src="http://cang.baidu.com/-/remote/fav1.jpg">
<!--    							&nbsp;百度搜藏-->
    						</a>
						    <a title="分享本页到雅虎收藏" href="#" onclick="window.open('http://myweb.cn.yahoo.com/popadd.html?url='+encodeURIComponent(document.location.href)+'&title='+encodeURIComponent(document.title), 'Yahoo','scrollbars=yes,width=440,height=440,left=80,top=80,status=yes,resizable=yes');">
						    	<img alt=分享噢贝比obabys到雅虎收藏 src="http://cn.yimg.com/i/fav/add2myweb.gif" border=0>
<!--						    	&nbsp;雅虎收藏-->
						    </a> 
						    <a href="#" onclick="window.open('http://bookmark.hexun.com/post.aspx?url='+encodeURIComponent(document.location.href)+'&title='+encodeURIComponent(document.title), 'PostBookmark','scrollbars=no,width=600,height=450,left=80,top=80,status=no,resizable=yes');">
						    	<img src="http://bookmark.hexun.com/img/icon-cross.gif" border=0/>
<!--						    	和讯转帖-->
						    </a>
						    <a title="分享本页到新浪ViVi" href="javascript:d=document;t=d.selection?(d.selection.type!='None'?d.selection.createRange().text:''):(d.getSelection?d.getSelection():'');void(vivi=window.open('http://vivi.sina.com.cn/collect/icollect.php?title='+escape(d.title)+'&url='+escape(d.location.href)+'&desc='+escape(t),'vivi','scrollbars=no,width=480,height=480,left=75,top=20,status=no,resizable=yes'));vivi.focus();">
						    	<IMG alt=分享噢贝比obabys到新浪ViVi src="http://vivi.sina.com.cn/favicon.ico"  align=Middle border=0/>
<!--						    	新浪ViVi-->
						    </a>
      						<a href="javascript:void(0);" class="add" onclick="addfavorite(document.location.href,document.title)"  >加入收藏</a>
      					</span>
	      			</div>
	      			<div class="warp">
	        			<div class="left">
		        			<!-- Image尺寸560*397 -->
		        			<a href="javascript:void(0);">
		        				<img width="560" src="<c:url value="${coupon.pic }"/>" />
		        			</a>
		          			<ul class="down">
					            <li><span></span></li>
					            <li>本优惠券已被下载${downs }次。</li>
					            <!-- 
					            <li>手机尾号为×××的用户×××在2010/02/10下载了一张优惠券。</li>
					            <li>手机尾号为×××的用户×××在2010/02/10下载了一张优惠券。</li>
					             -->
		          			</ul>
	        			</div>
	        			<div class="right">
	          				<div class="companyName">
		          				<!-- logo尺寸62*63 -->
		          				<img src="<c:url value="${coupon.shop.logo}"/>" />
		            			<h4>${coupon.shopName }</h4>
		            			<p>发布时间：${coupon.createTime }</p>
		          			</div>
				          	<ul class="boxgray">
				            	<li><span>活动名称：</span> ${coupon.name } </li>
				            	<li><span>活动时间：</span> ${coupon.startTimeWithFm1 }-${coupon.endTimeWithFm1 } </li>
				            	<li><span>活动地区：</span> 全国有此产品供应的肯德基餐厅 </li>
				            	<li><span>活动介绍：</span> 三重升级 惊喜不断 限时尝鲜价12.5元起 </li>
				          	</ul>
		          			<ul class="boxgray">
		            			<li><span>使用说明： </span> </li>
		            			<li>${coupon.introduction }</li>
		          			</ul>
		        		</div>
	      			</div>
    			</div>
    			<div class="bt"></div>
  			</div>
		</div>
		<div id="footer"><img src="../images/footer.gif" /></div>
		<div align="center">页面处理时间:<c:out value="${(postTime-preTime)}"/>毫秒</div>
	</body>
	<%@ include file="/common/google.jsp"%>
</html>