<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
			ul{margin-left:0px}
			ul li{ list-style:none}
		</style>
		<title>志愿者信息</title>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">志愿者信息</div></td>  
			      		</tr>
    				</table>
    			</td>
    			<td width="16" valign="top" background="../images/mail_rightbg.gif">
    				<img src="../images/nav-right-bg.gif" width="16" height="29" />
    			</td>
  			</tr>

  			<tr>
    			<td height="71" valign="middle" background="../images/mail_leftbg.gif">&nbsp;</td>
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
					            					<td class="left_txt">当前位置：志愿者管理&nbsp;&gt;&gt;&nbsp;志愿者信息</td>
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
									                	<img src="../images/title.gif" width="54" height="55">
									                </td>
									                <td width="90%" valign="top">
										                <span class="left_txt2">在这里，您可以查看志愿者的详细信息</span><br/>
                									</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr><td>&nbsp;</td></tr>
          							<tr>
            							<td>
            								<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="nowtable">
              									<tr>
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;志愿者基本参数</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									              	<tr>
									                	<td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">登录名称：</td>
									                	<td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
									                	<td width="32%" height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${postulant.loginName }
									                	</td>
									                	<td width="45%" height="30" bgcolor="#f2f2f2" class="left_txt">登录名称</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">姓名：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		${postulant.name }
									                	</td>
									                	<td height="30" class="left_txt">姓名</td>
									              	</tr>
									              	
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">密码： </td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		${postulant.password }
									                	</td>
									                	<td height="30" class="left_txt">密码</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">手机号码：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${postulant.dn }
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">手机号码</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">EMAIL：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		${postulant.email }
									                	</td>
									                	<td height="30" class="left_txt">EMAIL</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">性别：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<c:if test="${(postulant.sex) ==1 }">
																男
															</c:if>
															<c:if test="${(postulant.sex) ==2 }">
																女
															</c:if>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">性别</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">生日：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		${postulant.birthday }
									                	</td>
									                	<td height="30" class="left_txt">生日</td>
									              	</tr>
									              	
									              	
									              	
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">学历：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<c:forEach items="${xl}" var="current">
											        			<c:if test="${postulant.education==current.paramValue}">
												          			${current.paramKey}
												          		</c:if>
												          		
											      			</c:forEach>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">学历</td>
									              	</tr>
									              	
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">职业：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<c:forEach items="${zy}" var="current">
											        			<c:if test="${postulant.profession==current.paramValue}">
												          			${current.paramKey}
												          		</c:if>
												          		
											      			</c:forEach>
									                	</td>
									                	<td height="30" class="left_txt">职业</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">所属社区：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${postulant.communityname }
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">所属社区</td>
									              	</tr>
									              	
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">服务意向：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<c:forEach items="${ntfwyx}" var="current">
											        			<c:if test="${postulant.intention==current.paramId}">
												          			${current.paramKey}
												          		</c:if>
												          		
											      			</c:forEach>
									                		
									                	</td>
									                	<td height="30" class="left_txt">服务意向</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">机构名称：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${postulant.orgname}
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">机构名称</td>
									              	</tr>
									              	<c:if test="${jns!='0'}">
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">技能：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<c:forEach items="${jn}" var="current">
									                			<c:forEach items="${jns}" var="j">
												        			<c:if test="${j==current.paramId}">
													          			${current.paramKey}&nbsp;
													          		</c:if>
												          		
											      				</c:forEach>
											        			
												          		
											      			</c:forEach>
									                	</td>
									                	<td height="30" class="left_txt">技能</td>
									              	</tr>
									              	</c:if>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">民族：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<c:forEach items="${mz}" var="current">
											        			<c:if test="${postulant.nation==current.paramValue}">
												          			${current.paramKey}
												          		</c:if>
												          		
											      			</c:forEach>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">民族</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">证件类型：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<c:forEach items="${zjlx}" var="current">
											        			<c:if test="${postulant.credtype==current.paramValue}">
												          			${current.paramKey}
												          		</c:if>
												          		
											      			</c:forEach>
									                	</td>
									                	<td height="30" class="left_txt">证件类型</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">证件号码：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${postulant.credcode }
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">证件号码</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">兴趣爱好：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		${postulant.hobby }
									                	</td>
									                	<td height="30" class="left_txt">兴趣爱好</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">电话：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${postulant.phone }
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">电话</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">住址：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		${postulant.address }
									                	</td>
									                	<td height="30" class="left_txt">住址</td>
									              	</tr>
									              	

              										<tr>
                										<td height="17" colspan="4" align="right" >&nbsp;</td>
              										</tr>

            									</table>
            									<div align="right">
													<input type="button" name="goback" value="返回" onclick="history.go(-1)"/>&nbsp;&nbsp;&nbsp;&nbsp;
												</div>
            							</td>
          							</tr>
        						</table>
         					</td>
      					</tr>
    				</table>
    			</td>
    			<td background="../images/mail_rightbg.gif">&nbsp;</td>
  			</tr>
  			<tr>
    			<td valign="middle" background="../images/mail_leftbg.gif">
    				<img src="../images/buttom_left2.gif" width="17" height="17" />
    			</td>
      			<td height="17" valign="top" background="../images/buttom_bgs.gif">
      				<img src="../images/buttom_bgs.gif" width="17" height="17" />
      			</td>
    			<td background="../images/mail_rightbg.gif">
    				<img src="../images/buttom_right2.gif" width="16" height="17" />
    			</td>
  			</tr>
		</table>
	</body>
</html>