<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<title>商户管理</title>
		<script type="text/javascript">
			$(document).ready(function(){

				var shopId=$("#shopid");
				var shops=$(".shop");
				var delbutt=$("#delall");
				
				$(".online").click(function(){
					return confirm("确认对商户:"+this.name+"进行上线设置?");
				});

				$(".offline").click(function(){
					return confirm("确认对商户:"+this.name+"进行下线设置?");
				});

				$(".delshop").click(function(){
					return confirm("确认删除商户:"+this.name+"?");
				});

				/*$("#shopid").click(function(){
					$("td > input").attr("checked",$(this).attr("checked"));
				});

				$("input[name]").click(function(){
					if(!($(this).attr("checked"))){
						$("#shopid").attr("checked",false);
					}
				});*/

				shopId.click(function(){//还有两个逻辑没有实现,1:全部选中时自动选中总选控件,全部不选中时按钮变灰 
					var _tmp=$(this).attr("checked");
					if(!_tmp){
						delbutt.attr("disabled","disabled");
					}else{
						delbutt.attr("disabled","");
					}
					shops.attr("checked",_tmp);
				});

				shops.click(function(){
					if(!($(this).attr("checked"))){
						shopId.attr("checked",false);
					}else{
						delbutt.attr("disabled","");
					}
				});

				delbutt.click(function(){
					if(confirm("确认进行本次批量删除商户操作?")){
						var allId;
						$.each(shops,function(i,n){
							if(allId){
								allId=n.value+","+allId;
							}else{
								allId=n.value;
							}
						});
						//alert(allId);
						$.ajax({
							type: "POST",
							url: "del.action",
							data: "allId="+allId,
							success: function( msg ){
								alert('操作成功!');
								window.location.reload();
							}
						});
					}
				});
			});
		</script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../../images/mail_leftbg.gif">
    				<img src="../../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">商户管理</div></td>
			      		</tr>
    				</table>
    			</td>
    			<td width="16" valign="top" background="../../images/mail_rightbg.gif">
    				<img src="../../images/nav-right-bg.gif" width="16" height="29" />
    			</td>
  			</tr>
  			
  			<tr>
    			<td height="71" valign="middle" background="../../images/mail_leftbg.gif">&nbsp;</td>
   				<td valign="top" bgcolor="#F7F8F9">
   					<table width="100%" height="138" border="0" cellpadding="0" cellspacing="0">
      					<!-- <tr>
        					<td height="4" valign="top"></td>
      					</tr> -->
      					<tr>
        					<td valign="top">
        						<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
					          		<tr>
					            		<td height="8">
					            			<table width="100%">
					            				<tr>
					            					<td class="left_txt">当前位置：商户管理&nbsp;>>&nbsp;商户列表</td>
					            					<td align="right" class="left_txt">页面处理时间:<c:out value="${(postTime-preTime)}"/>毫秒&nbsp;&nbsp;</td>
					            				</tr>
					            			</table>
					            		</td>
					          		</tr>
          							<tr>
            							<td height="10">
            								<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              									<tr><td></td></tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            								<table width="100%" height="55" border="0" cellpadding="0" cellspacing="0">
              									<tr>
									                <td width="10%" height="55" valign="middle">
									                	<img src="../../images/title.gif" width="54" height="55">
									                </td>
									                <td width="90%" valign="top">
										                <span class="left_txt2">在这里，您可以创建、修改、删除以及改变商户对象</span><br>
										                <span class="left_txt3">注意!</span>
										                <span class="left_txt3">商户的删除为物理删除,一旦删除数据将不可恢复.</span>
                									</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr><td>&nbsp;</td></tr>
          							<tr>
            							<td>
            								<table width="100%" height="25" border="0" cellpadding="0" cellspacing="0" class="nowtable">
              									<tr>
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;商户列表</td>
                									<td class="left_bt2" align="right">
                										<input type="button" name="delall" id="delall" disabled="disabled" value="删除">
														&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="<c:url value="add.action"/>">创建商户</a>&nbsp;&nbsp;&nbsp;&nbsp;
                									</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
           									<table width="100%" border="1" cellspacing="0" cellpadding="0">
           										<thead>
													<tr>
														<th width="4%"><input type="checkbox" id="shopid" /></th>
														<th width="14%" class="left_txt2">商户名称</th>
														<th width="42%" class="left_txt2">商户简介</th>
														<th width="8%" class="left_txt2">创建时间</th>
														<th width="7%" class="left_txt2">商户状态</th>
														<th width="10%" class="left_txt2">商户LOGO</th>
														<th width="15%" class="left_txt2">操作</th>
													</tr>
												</thead>
												
												<tbody>
													<c:forEach var="shop" items="${shopList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
														<tr height="10"  bgcolor="#f2f2f2" class="left_txt2">
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0 }">
														<tr height="10"  class="left_txt2">
													</c:if>
													<!-- <tr height="10"  bgcolor="#f2f2f2" class="left_txt2"> -->
														<td align="center"><input type="checkbox"  class="shop" name="shop${shop.id}" value="${shop.id}"/></td>
														<td align="left">${shop.name }</td>
														<td align="left">
															<c:set var="introduction" value="${shop.introduction }"/>
		        											<c:out value="${fn:substring(introduction,0,30)}..."/> 
														</td>
														<td align="center">${shop.createTime }</td>
														<c:if test="${shop.state==0}">
														<td align="center">新增</td>
														<td align="center"><img src="<c:url value="${shop.logo}"/>" height="30px"/></td>
														<td align="center">
															<a href="<c:url value="${shop.id}/shop.action"/>">查看</a>
															<a href="<c:url value="${shop.id}/edit.action"/>">修改</a>
															<a href="<c:url value="${shop.id}/del.action"/>" name="${shop.name}" class="delshop">删除</a>
															<a href="<c:url value="${shop.id}/1/change.action"/>" name="${shop.name}" class="online">上线</a>
														</td>
														</c:if>
														<c:if test="${shop.state==1}">
														<td align="center">在线</td>
														<td align="center"><img src="<c:url value="${shop.logo}"/>" height="30px"/></td>
														<td align="center">
															<a href="<c:url value="${shop.id}/shop.action"/>">查看</a>
															<a href="<c:url value="${shop.id}/edit.action"/>">修改</a>
															<a href="<c:url value="${shop.id}/del.action"/>" name="${shop.name}" class="delshop">删除</a>
															<a href="<c:url value="${shop.id}/2/change.action"/>" name="${shop.name}" class="offline">下线</a>
														</td>
														</c:if>
														<c:if test="${shop.state==2}">
														<td align="center">离线</td>
														<td align="center"><img src="<c:url value="${shop.logo}"/>" height="30px"/></td>
														<td align="center">
															<a href="<c:url value="${shop.id}/shop.action"/>">查看</a>
															<a href="<c:url value="${shop.id}/edit.action"/>">修改</a>
															<a href="<c:url value="${shop.id}/del.action"/>" name="${shop.name}" class="delshop">删除</a>
															<a href="<c:url value="${shop.id}/1/change.action"/>" name="${shop.name}" class="online">上线</a>
														</td>
														</c:if>
													</tr>
													</c:forEach>
													<script type="text/javascript">
												  	function goPage(page)
												  	{
												  	  	document.location.href='index.action?page='+page;
												  	}
											  		</script>
													<tr class="left_txt2">
													<td colspan="7" align="center">
														${toolNav}
													</td>
													</tr>
												</tbody>
           									</table>
            							</td>
          							</tr>
        						</table>
         					</td>
      					</tr>
    				</table>
    			</td>
    			<td background="../../images/mail_rightbg.gif">&nbsp;</td>
  			</tr>
  			<tr>
    			<td valign="middle" background="../../images/mail_leftbg.gif">
    				<img src="../../images/buttom_left2.gif" width="17" height="17" />
    			</td>
      			<td height="17" valign="top" background="../../images/buttom_bgs.gif">
      				<img src="../../images/buttom_bgs.gif" width="17" height="17" />
      			</td>
    			<td background="../../images/mail_rightbg.gif">
    				<img src="../../images/buttom_right2.gif" width="16" height="17" />
    			</td>
  			</tr>
		</table>
		
	</body>
</html>