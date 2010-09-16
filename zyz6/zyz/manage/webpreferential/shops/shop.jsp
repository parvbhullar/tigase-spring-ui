<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="../../../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<title>商户信息</title>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../../../images/mail_leftbg.gif">
    				<img src="../../../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../../../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">商户信息</div></td>  
			      		</tr>
    				</table>
    			</td>
    			<td width="16" valign="top" background="../../../images/mail_rightbg.gif">
    				<img src="../../../images/nav-right-bg.gif" width="16" height="29" />
    			</td>
  			</tr>

  			<tr>
    			<td height="71" valign="middle" background="../../../images/mail_leftbg.gif">&nbsp;</td>
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
					            					<td class="left_txt">当前位置：商户管理&nbsp;>>&nbsp;商户信息</td>
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
									                	<img src="../../../images/title.gif" width="54" height="55">
									                </td>
									                <td width="90%" valign="top">
										                <span class="left_txt2">在这里，您可以查看商户的详细信息</span><br/>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;商户基本参数</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            									<table width="100%" border="0" cellspacing="0" cellpadding="0">
									              	<tr>
									                	<td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">商户名称：</td>
									                	<td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
									                	<td width="32%" height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		${shop.name}
									                	</td>
									                	<td width="45%" height="30" bgcolor="#f2f2f2" class="left_txt">&nbsp;</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">门店图片：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<img id="imglogo" src="<c:url value="${shop.shopImage}"/>" height="50px"/>
									                	</td>
									                	<td height="30" class="left_txt">&nbsp;</td>
									              	</tr>
									              	<tr>
									                	<td bgcolor="#f2f2f2" height="30" align="right" class="left_txt2">商户LOGO：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" >
									                		<img id="imglogo" src="<c:url value="${shop.logo}"/>" height="50px"/>
									                	</td>
									                	<td bgcolor="#f2f2f2" height="30" class="left_txt">&nbsp;</td>
									              	</tr>
									              	
									              	<tr>
									                	<td height="30" align="right"  class="left_txt2">商户地址：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		${shop.address}
									                	</td>
									                	<td height="30" class="left_txt">&nbsp;</td>
									              	</tr>
									              	<tr>
									                	<td bgcolor="#f2f2f2" height="30" align="right" class="left_txt2">商户电话： </td>
									                	<td bgcolor="#f2f2f2" >&nbsp;</td>
									                	<td bgcolor="#f2f2f2" height="30" class="left_txt2">
									                		${shop.tel}
									                	</td>
									                	<td bgcolor="#f2f2f2" height="30" class="left_txt">&nbsp;</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">商户介绍：</td>
									                	<td>&nbsp;</td>
									                	<td height="30"  class="left_txt2">
									                		${shop.introduction}
									                	</td>
									                	<td height="30" class="left_txt">&nbsp;</td>
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
    			<td background="../../../images/mail_rightbg.gif">&nbsp;</td>
  			</tr>
  			<tr>
    			<td valign="middle" background="../../../images/mail_leftbg.gif">
    				<img src="../../../images/buttom_left2.gif" width="17" height="17" />
    			</td>
      			<td height="17" valign="top" background="../../../images/buttom_bgs.gif">
      				<img src="../../../images/buttom_bgs.gif" width="17" height="17" />
      			</td>
    			<td background="../../../images/mail_rightbg.gif">
    				<img src="../../../images/buttom_right2.gif" width="16" height="17" />
    			</td>
  			</tr>
		</table>
	</body>
</html>