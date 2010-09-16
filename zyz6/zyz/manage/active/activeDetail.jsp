<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>活动详情</title>
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/javascript/jquery-1.4.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/fckeditor/fckeditor.js"></script>
		<script src="<c:url value="/javascript/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
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
					            					<td class="left_txt">当前位置：活动管理&nbsp;&gt;&gt;&nbsp;活动详情</td>
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
										                <span class="left_txt2">在这里，您可以查看活动的详细信息</span><br/>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;活动基本参数</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
											<table style="width:100%">
												<tr>
													<td style="width:15%" align="right" class="left_txt2">活动主题：</td>
													<td style="width:35%">${active.acttitle}</td>
													<td style="width:15%" align="right" class="left_txt2">活动地点：</td>
													<td style="width:35%">${active.actaddr}</td>
												</tr>
												<tr bgcolor="#f2f2f2">
													<td style="width:15%" align="right" class="left_txt2">开始日期：</td>
													<td style="width:35%">${active.startdate}</td>
													<td style="width:15%" align="right" class="left_txt2">结束日期：</td>
													<td style="width:35%">${active.enddate}</td>
												</tr>
												<tr>
													<td style="width:15%" align="right" class="left_txt2">时间储蓄：</td>
													<td style="width:35%">${active.acthours}&nbsp;小时</td>
													<td style="width:15%" align="right" class="left_txt2">活动人数：</td>
													<td style="width:35%">${active.actnum}</td>													
												</tr>
												<tr bgcolor="#f2f2f2">
													<td style="width:15%" align="right" class="left_txt2">活动系数：</td>
													<td style="width:35%">
														${active.ratio}%
													</td>
													<td style="width:15%" align="right" class="left_txt2">服务意向：</td>
													<td style="width:35%">
														${active.intention}
													</td>
												</tr>
												<tr>
													<td style="width:15%" align="right" class="left_txt2">活动摘要：</td>
													<td style="width:35%" colspan=3>
														${active.summary}
													</td>
												</tr>
												<tr bgcolor="#f2f2f2">
													<td style="width:15%" align="right" class="left_txt2">活动描述：</td>
													<td style="width:35%" colspan=3>
														${active.actdesc}						
													</td>
												</tr>
											</table>
											<div align="center">
												<input type="button" name="btnBack" onclick="history.go(-1)" value="返 回">				
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>