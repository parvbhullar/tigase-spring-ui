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
			        		<td height="31"><div class="titlebt">帮扶者信息</div></td>  
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
					            					<td class="left_txt">当前位置：帮扶者管理&nbsp;&gt;&gt;&nbsp;帮扶者信息</td>
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
										                <span class="left_txt2">在这里，您可以查看帮扶者的详细信息</span><br/>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;帮扶者基本参数</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									              	<tr>
									                	<td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">姓名：</td>
									                	<td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
									                	<td width="32%" height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${assist.name }
									                	</td>
									                	<td width="45%" height="30" bgcolor="#f2f2f2" class="left_txt">姓名</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">手机：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		${assist.cellPhone }
									                	</td>
									                	<td height="30" class="left_txt">手机</td>
									              	</tr>
									              	<tr>
									                	<td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">社区：</td>
									                	<td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
									                	<td width="32%" height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${assist.volunorgname }
									                	</td>
									                	<td width="45%" height="30" bgcolor="#f2f2f2" class="left_txt">社区</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">性别： </td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<c:if test="${(assist.sex) ==1 }">
																男
															</c:if>
															<c:if test="${(assist.sex) ==2 }">
																女
															</c:if>
									                	</td>
									                	<td height="30" class="left_txt">性别</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">生日：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${assist.birthday }
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">生日</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">证件号码：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		${assist.credcode }
									                	</td>
									                	<td height="30" class="left_txt">证件号码</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">电话：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${assist.hometel }
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">电话</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">住址：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		${assist.address }
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