<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<%@ include file="/common/google.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
com.linkage.app.gqt.webpreferential.index.entitys.User user = (com.linkage.app.gqt.webpreferential.index.entitys.User)session.getAttribute("userinfo");
String dn="";
if(user!=null)
	dn = user.getDn();
%>
<html>
	<head>
		<title>家教超市——海量资讯，任您选择</title>
		<meta name="keywords" content="家长网校,家庭教育,网校,家校通,家教超市" /> 
		<meta name="description" content="家教超市——海量资讯，任您选择" />
        <LINK rel=stylesheet type=text/css href="images/index.css">
		<script language="javascript" src="images/flash.js" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
        <script type="text/javascript">
			function download(id,shopId,message){
				if('<%=user%>'=='null'){
					alert('请登录');
					return;
				}	
				$.ajax({
					type: "POST",
					url: "download.action",
					data: "id="+id+"&shopId="+shopId+"&message="+message+"&dn=<%=dn%>",
					success: function(msg){
						alert( "下载成功!" );
					},
					error:function(msg){
						alert("下载失败!");
					}
				});
			}
			function addfavorite(href,title){
				if (document.all){
					window.external.addFavorite(href,title);
				}else if (window.sidebar){
					window.sidebar.addPanel(title, href, "");
				}
			}
			function checkjump(){
				var pwd=$("#passwd").val();
				if(pwd){pwd=jQuery.trim(pwd);}
				var un=$("#username").val();
				if(un){un=jQuery.trim(un);}
				if(!pwd){
					alert("请输入登录密码!");
					return false;
				}
				if(!un){
					alert("请输入登录名称!");
					return false;
				}
				$("#jumpform").submit();
			}
		</script>
	</head>

	<body>
		<table width="922" align="center" cellpadding="0" cellspacing="0">
	  		<tr>
	    		<td>
	    			<iframe id="top" src="include/head.html" style="width:922px; height:111px " frameborder="0"  scrolling="no"></iframe>
	    		</td>
	  		</tr>
		</table>

		<table cellspacing="0" cellpadding="0" width="100%" border="0">
  			<tr>
    			<td valign="top">
      				<table width="922" border="0" align="center" cellpadding="0" cellspacing="0">
          				<tr>
            				<td  bgcolor="#DAF0FD">
            					<!-- 静态图片及登录框开始 -->
            					<table width="100%" align="center" cellpadding="0" cellspacing="0">
              						<tr>
              					    	<td width="620" height="200" align="center">
              					    		<c:if test="${sad.pic==null}">
                    							<img src="images/ad02.jpg" width="590" height="230">
                    						</c:if>
                    						<c:if test="${sad.pic!=null}">
                    							<img src="<c:url value="${sad.pic}"/>" width="590" height="230">
                    						</c:if>
              					    	</td>
              					    	<td width="320" valign="top">
              					    		<table width="241" align="center" cellpadding="0" cellspacing="0">
              					      			<tr>
              					        			<td width="239" class="login_bg">
              					        				<form id="jumpform" method="post" action="/ecommerce/app/jump.jsp">
              					          					<table width="259" cellspacing="0" cellpadding="0">
					              					            <tr>
					              					              	<td height="25" colspan="2">&nbsp;</td>
					           					                </tr>
					              					            <tr>
					              					              	<td width="67" height="30" align="right"><label>用户名：</label></td>
					              					              	<td height="30"><input type="text" id="username" name="username" title="用户名" /></td>
					           					                </tr>
					              					            <tr>
					              					              	<td align="right"><label>密码：</label></td>
					              					              	<td height="30"><input type="password" size="22" id="passwd" name="passwd" title="密码" /></td>
					           					                </tr>
              					            					<tr>
              					            						<td></td>
              					              						<td height="20" align="right">
              					              							<!-- <input name="status" type="checkbox" value="记住登录状态" checked="checked" style=" border:none;"/>
              					                						<label>记住登录状态</label>&nbsp;-->
              					                						<label style="margin-right: 40px;"><input type='submit' onclick="return checkjump();" value="登&nbsp;&nbsp;录"></input></label><br/>
                                              							<label style="margin-right: 40px;"><a href="#">[找回密码]</a></label>
                                              						</td>
           					                					</tr>
					              					            <!-- <tr>
					              					              	<td height="30" colspan="2" align="center">
					              					              		<span>建议在公用电脑上取消该选项</span>
					              					              	</td>
					           					                </tr>-->
              					            					<tr>
					              					              	<td height="20" align="center">
					              					              		<img src="images/key.jpg" width="41" height="56">
					              					              	</td>
					              					              	<td height="20" align="center">
					              					              		<a href="http://221.130.6.212:1288/justtwo/regPhone.jsp"><img id="jumpsubmit" src="images/register-member.jpg" width="178" height="22"/></a>
					              					              	</td>
           					                					</tr>
           					              					</table>
              					        				</form>
              					        			</td>
           					          			</tr>
            					      		</table> 
                                    	</td>
           					      	</tr>
           					  	</table>
            					<!-- 静态图片及登录框结束 -->
              					<table width="100%" cellspacing="0" cellpadding="0">
                					<tr>
                						<!-- 最新优惠开始 -->
                  						<td width="50%" height="250" align="center">
                  							<table cellpadding="0" border=0 cellspacing="0">
                    							<tr>
                      								<td width="20">&nbsp;</td>
                      								<td width="392" class="menubg03" align="left"><img src="images/jifenbg2.gif" width="9" height="9"/> 最新优惠</td>
                    							</tr>
                    							<tr>
                      								<td colspan="2" bgcolor="#FFFFFF"><img src="images/line03.jpg" width="426" height="7"></td>
                    							</tr>
                    							<tr>
                      								<td colspan="2" bgcolor="#FFFFFF">
                      									<table align="center" cellpadding="0" cellspacing="5">
						                        			<tr>
						                          				<td bgcolor="#FFFFFF">
						                          					<table width="410" height="120" >
						                          						<tr>
						                          							<td>
							                          							<!--  
							                          							<table width="410" height="150">
							                          								<c:forEach var="cou" items="${news}">
						                          									<tr>
								                          								<td>${cou.name }</td>
								                          								<td></td>
							                          								</tr>
						                          								</c:forEach>
						                          								-->          
																				<input id="jsonlist" type="hidden" value="<c:out value="${jsonlist }"/>" />  
																				<script type="text/javascript">
																				var jsonObj = eval("("+$("#jsonlist").val()+")");
																				var count = jsonObj.length;
																				var obj;
																				var info = '';
																				var image = '';
																				var url = '';
																				for(var i = 0; i < count; i++) {
																					if(i<5){
																					    obj = jsonObj[i];
																					    if(i+1==count||i==4){
																						    image = image + "<%=request.getContextPath()%>"+obj.url;
																						    //imgtext[i+1]=obj.content;
																						    url = url + "coupons/coupon.action?id="+obj.id;
																						   
																						    info = info + obj.title;
																					    }
																					    else
																					    {
																					    	image = image + "<%=request.getContextPath()%>"+obj.url+"|";
																						    //imgtext[i+1]=obj.content;
																						    url = url + "coupons/coupon.action?id="+obj.id+"|";
																						   
																						    info = info + obj.title+"|";
																						}
																					}
																				 }
																				 var focus_width=410;
																				 var focus_height=130;
																				 var text_height=18;
																				 var swf_height = focus_height+text_height;
																				 document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
																				 document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="images/focus1.swf"><param name="quality" value="high"><param name="bgcolor" value="#F0F0F0">');
																				 document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
																				 document.write('<param name="FlashVars" value="pics='+image+'&links='+url+'&texts='+info+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'">');
																				 document.write('</object>');
																				 </script>
																			</td>
																		</tr>
																	</table>
                          										</td>
                          									</tr>
							                        		<tr>
							                          			<td width="410" bgcolor="#FFFFFF"></td>
							                          		</tr>
							                        	</table>
							                        </td>
                    							</tr>
                    						</table>
                    					</td>
                    					<!-- 最新优惠结束 -->
                    					<!-- 热门优惠开始 -->
                  						<td align="center">
                  							<table cellpadding="0" cellspacing="0">
					                    		<tr>
					                      			<td width="20">&nbsp;</td>
					                      			<td width="392" class="menubg03" align="left"><img src="images/jifenbg2.gif" width="9" height="9"/> 热门优惠</td>
					                    		</tr>
					                    		<tr>
					                      			<td colspan="2" bgcolor="#FFFFFF"><img src="images/line03.jpg" width="426" height="7"></td>
					                    		</tr>
					                    		<tr>
					                      			<td colspan="2" bgcolor="#FFFFFF">
					                      				<table align="center" cellpadding="0" cellspacing="5">
					                        				<tr>
					                          					<td bgcolor="#FFFFFF">
					                          						<table width="410" height="150">
					                          							
					                          							<tr>
							                          						<td>
																				<input id="jsonhotlist" type="hidden" value="<c:out value="${jsonhotlist }"/>" />   
																				<script type="text/javascript">
																					var jsonObj = eval("("+$("#jsonhotlist").val()+")");
																					var count = jsonObj.length;
																					var obj;
																					var info = '';
																					var image = '';
																					var url = '';
																					for(var i = 0; i < count; i++) {
																						if(i<5){
																						    obj = jsonObj[i];
																						    if(i+1==count||i==4){
																							    image = image + "<%=request.getContextPath()%>"+obj.url;
																							    //imgtext[i+1]=obj.content;
																							    url = url + "coupons/coupon.action?id="+obj.id;
																							   
																							    info = info + obj.title;
																						    }
																						    else
																						    {
																						    	image = image + "<%=request.getContextPath()%>"+obj.url+"|";
																							    //imgtext[i+1]=obj.content;
																							    url = url + "coupons/coupon.action?id="+obj.id+"|";
																							   
																							    info = info + obj.title+"|";
																							}
																						}
																					 }
																					 //alert(image);
																					 var focus_width=410;
																					 var focus_height=120;
																					 var text_height=18;
																					 var swf_height = focus_height+text_height;
																					 document.write('<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,0,0" width="'+ focus_width +'" height="'+ swf_height +'">');
																					 document.write('<param name="allowScriptAccess" value="sameDomain"><param name="movie" value="images/focus1.swf"><param name="quality" value="high"><param name="bgcolor" value="#F0F0F0">');
																					 document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
																					 document.write('<param name="FlashVars" value="pics='+image+'&links='+url+'&texts='+info+'&borderwidth='+focus_width+'&borderheight='+focus_height+'&textheight='+text_height+'">');
																					 document.write('</object>');
																				 </script>
							                          						</td>
						                          						</tr>
						                          					</table>
					                          					</td>
					                          				</tr>
					                        				<tr>
					                          					<td width="410" bgcolor="#FFFFFF"></td>
					                          				</tr>
					                        			</table>
					                        		</td>
                    							</tr>
                    						</table>
                    					</td>
                    					<!-- 热门优惠结束 -->
                					</tr>
              					</table>
              				</td>
              			</tr>
              			<!-- 广告推荐开始 -->
        				<tr>
                			<td height="86" valign="middle">
                				<table width="100%" cellspacing="0" cellpadding="0">
                  					<tr>
                  					<c:if test="${cad.pic==null}">
                    						<img src="images/ad01.jpg" width="922" height="80">
                    					</c:if>
                    					<c:if test="${cad.pic!=null}">
                    						<a href="coupons/coupon.action?id=${cad.couponId}"><img src="<c:url value="${cad.pic}"/>" width="922" height="80"></a>
                    					</c:if>
                  					</tr>
                  				</table>
                  			</td>
                  		</tr> 
                  		<!-- 广告推荐结束 -->
                  		<!-- 优惠券推荐开始 -->                                                  
        				<tr>
                    		<td height="86" valign="top" bgcolor="#DAF0FD">
                    			<table width="922" cellspacing="0" cellpadding="0">
                      				<tr>
                        				<td width="696" nowrap></td>
                        				<td width="224" rowspan="3" valign="top">
                        					<table width="84%" align="center" cellpadding="0" cellspacing="0">
                          						<tr>
                            						<td height="40" align="center">
                            							<a href="javascript:void(0);" class="add" >
                            								<img src="images/button_add.jpg" width="200" height="32" onclick="addfavorite(document.location.href,document.title)" >
                            							</a>
                            						</td>
                          						</tr>
                          						<tr>
	                            					<td height="132" align="center" bgcolor="#FFFFFF" class="bg01">
<!--	                            						<form name="form1" method="post" action="">-->
				                              				<p>
				                                				<!-- <label>
				                                					<span class="txt_profileB">
				                                						<img src="images/icon_arrowR.gif" width="3" height="5"> 优惠信息收藏 <br><br>
				                                					</span>
				                                  					<input name="textfield" type="text" class="form01" id="textfield" value="请输入手机号码" size="20" maxlength="11">
				                                				</label>
				                                				<input type="submit" name="button" id="button" value="订 阅">
				                                				-->
				                                				<iframe src="include/sharing.html" style="width:200px; height:130px " frameborder="0"  scrolling="no"></iframe>
				                              				</p>
<!--	  													</form>-->
<!--	                              						<p>&nbsp;</p>-->
	                              					</td>
                          						</tr>
                          					</table>
                          				</td>
                      				</tr>
                      				<tr>
                        				<td height="32" valign="bottom" nowrap style="background-image:url(images/menu_bg03.jpg)">
                        					<table width="227" height="26" cellpadding="0" cellspacing="0">
                          						<tr>
                            						<td width="225" height="32" class="menu_title_bg">最热优惠券</td>
                          						</tr>
                          					</table>
                          				</td>
                      				</tr>
                      				<tr>
                        				<td height="42" valign="top" nowrap bgcolor="#FFFFFF">
                        					<table width="100%" cellspacing="4" cellpadding="0" border="0">
                         						<tr>
                          							<c:forEach items="${tuijian}" var="coupon" begin="0" end="4" step="1">
                          	 						<td height="41" align="center">
                          	 							<p><img src="<c:url value="${coupon.pic}"/>" width="120" height="80"></p>
                          	 							<p><a href="javascript:void(0);"><img onclick="download(${coupon.id},${coupon.shopId },'${coupon.message }');" src="images/button_down.gif" width="80" height="22"></a></p>
                          	 							<p><a target="_blank" href="coupons/print.jsp?pic=${coupon.pic}"><img src="images/button_prt.gif" width="80" height="22"></a></p>
                          	 						</td>
                          							</c:forEach>
                          						</tr>
                          					</table>
                          				</td>
                      				</tr>
                     			</table>
                     		</td>
                     	</tr>
                     	<!-- 优惠券推荐结束 -->
                     	<!-- 店铺列表开始 -->
        				<tr>
                        	<td height="100" valign="top" bgcolor="#DAF0FD"><br>
                        		<table width="900" align="center" cellpadding="0" cellspacing="0">
                            		<tr>
                              			<td height="121" bgcolor="#FFFFFF">
                                			<!-- 按钮控制滚动图片开始 -->
                                			<div class="rollBox">
                                  				<div class="LeftBotton" onMouseDown="ISL_GoUp()" onMouseUp="ISL_StopUp()" onMouseOut="ISL_StopUp()"></div>
                                  				<div class="Cont" id="ISL_Cont">
                                    				<div class="ScrCont">
                                      					<div id="List1">
                                        					<!-- 图片列表 begin -->
                                        					<c:forEach items="${shopList}" var="shop">
                                        						<div class="pic">
                                        						<a href="<c:url value="shops/shop.action?id=${shop.id}&page=1"/>" target="_blank">
                                        							<img src="<c:url value="${shop.logo}"/>" width="80" height="60" />
                                        						</a>
                                        						<a href="javascript:void(0);">${shop.name }</a>
                                        					</div>
                                        					</c:forEach>
                                      					</div>
                                      					<div id="List2"></div>
                                    				</div>
                                  				</div>
                                  				<div class="RightBotton" onMouseDown="ISL_GoDown()" onMouseUp="ISL_StopDown()" onMouseOut="ISL_StopDown()"></div>
                                			</div>
		                                	<script language="JavaScript" type="text/javascript">
											<!--//--><![CDATA[//><!--
											//图片滚动列表 mengjia 070816
											var Speed = 10; //速度(毫秒)
											var Space = 5; //每次移动(px)
											var PageWidth = 528; //翻页宽度
											var fill = 0; //整体移位
											var MoveLock = false;
											var MoveTimeObj;
											var Comp = 0;
											var AutoPlayObj = null;
											GetObj("List2").innerHTML = GetObj("List1").innerHTML;
											GetObj('ISL_Cont').scrollLeft = fill;
											GetObj("ISL_Cont").onmouseover = function(){clearInterval(AutoPlayObj);};
											GetObj("ISL_Cont").onmouseout = function(){AutoPlay();};
											AutoPlay();
											function GetObj(objName){
												if(document.getElementById){
													return eval('document.getElementById("'+objName+'")');
												}else{
													return eval('document.all.'+objName);
												}
											}
											function AutoPlay(){ //自动滚动
												clearInterval(AutoPlayObj);
												AutoPlayObj = setInterval('ISL_GoDown();ISL_StopDown();',3000); //间隔时间
											}
											function ISL_GoUp(){ //上翻开始
												if(MoveLock) return;
												clearInterval(AutoPlayObj);
												MoveLock = true;
												MoveTimeObj = setInterval('ISL_ScrUp();',Speed);
											}
											function ISL_StopUp(){ //上翻停止
												clearInterval(MoveTimeObj);
												if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0){
												 	Comp = fill - (GetObj('ISL_Cont').scrollLeft % PageWidth);
												 	CompScr();
												}else{
													MoveLock = false;
												}
												AutoPlay();
											}
											function ISL_ScrUp(){ //上翻动作
												if(GetObj('ISL_Cont').scrollLeft <= 0){
													GetObj('ISL_Cont').scrollLeft = GetObj('ISL_Cont').scrollLeft + GetObj('List1').offsetWidth;
												}
												GetObj('ISL_Cont').scrollLeft -= Space ;
											}
											function ISL_GoDown(){ //下翻
												clearInterval(MoveTimeObj);
												if(MoveLock) return;
												clearInterval(AutoPlayObj);
												MoveLock = true;
												ISL_ScrDown();
												MoveTimeObj = setInterval('ISL_ScrDown()',Speed);
											}
											function ISL_StopDown(){ //下翻停止
												clearInterval(MoveTimeObj);
												if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0 ){
													Comp = PageWidth - GetObj('ISL_Cont').scrollLeft % PageWidth + fill;
													CompScr();
												}else{
													MoveLock = false;
												}
												AutoPlay();
											}
											function ISL_ScrDown(){ //下翻动作
												if(GetObj('ISL_Cont').scrollLeft >= GetObj('List1').scrollWidth){
													GetObj('ISL_Cont').scrollLeft = GetObj('ISL_Cont').scrollLeft - GetObj('List1').scrollWidth;
													}
												GetObj('ISL_Cont').scrollLeft += Space ;
											}
											function CompScr(){
												var num;
												if(Comp == 0){MoveLock = false;return;}
												if(Comp < 0){ //上翻
													if(Comp < -Space){
														Comp += Space;
														num = Space;
													}else{
											   			num = -Comp;
											   			Comp = 0;
											  		}
											  		GetObj('ISL_Cont').scrollLeft -= num;
											  		setTimeout('CompScr()',Speed);
											 	}else{ //下翻
											  		if(Comp > Space){
											   			Comp -= Space;
											   			num = Space;
											  		}else{
											   			num = Comp;
											   			Comp = 0;
											  		}
											  		GetObj('ISL_Cont').scrollLeft += num;
											  		setTimeout('CompScr()',Speed);
											 	}
											}
											//--><!]]>
		                					</script>
		                                	<!-- 按钮控制滚动图片结束 -->                          
                              			</td>
                            		</tr>
                          		</table><br>                        
							</td>
						</tr>
						<!-- 店铺列表结束 -->
					</table>
				</td>
			</tr>
		</table>
	
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table width="922" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="208" rowspan="2" align="center">
								<img height="48" src="images/logo_s.gif" width="99" alt="(图片来源：江苏家长网校)" />
							</td>
							<td width="714"></td>
						</tr>
						<tr>
							<td>
								<table width="714"  border="0" align="center" cellpadding="0" cellspacing="0" >  
								  	<tr>
								    	<td><div align="center"><a href="http://www.miibeian.gov.cn/" target=_blank>增值电信业务经营许可证：B2-20060483</a>&nbsp;&nbsp;访问累计 <font color=red></font> 次</div></td>
								  	</tr>
								  	<tr>
								    	<td><div align="center">主办单位：72CH.COM&nbsp;&nbsp;江苏省网上家长学校&nbsp;&nbsp;&nbsp;&nbsp;技术支持：南京联创科技股份有限公司</div></td>
								  	</tr>
								  	<tr>
								    	<td><div align="center">Copyright&copy; 72CH.COM 2002-2008 All Rights Reserved.</div></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div align="center">页面处理时间:<c:out value="${(postTime-preTime)}"/>毫秒</div>
	</body>
</html>