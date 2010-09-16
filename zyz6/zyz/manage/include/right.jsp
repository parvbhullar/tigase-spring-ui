<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="authentication"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
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
			        		<td height="31"><div class="titlebt">欢迎界面</div></td>
			      		</tr>
    				</table>
    			</td>
    			<td width="16" valign="top" background="../images/mail_rightbg.gif">
    				<img src="../images/nav-right-bg.gif" width="16" height="29" />
    			</td>
  			</tr>
  			<tr>
    			<td valign="middle" background="../images/mail_leftbg.gif">&nbsp;</td>
    			<td valign="top" bgcolor="#F7F8F9">
    				<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      					<tr>
					        <td colspan="2" valign="top">&nbsp;</td>
					        <td>&nbsp;</td>
					        <td valign="top">&nbsp;</td>
      					</tr>
      					<tr>
        					<td colspan="2" valign="top"><span class="left_bt">感谢您使用 志愿者后台管理系统程序</span><br><br>
            					<span class="left_txt">
            						&nbsp;<img src="../images/ts.gif" width="16" height="16"> 提示：<br>
          						</span>
							</td>
        					<td width="7%">&nbsp;</td>
        					<td width="40%" valign="top">
        						<table width="100%" height="144" border="0" cellpadding="0" cellspacing="0" class="line_table">
						          	<tr>
						            	<td width="7%" height="27" background="../images/news-title-bg.gif">
						            		<img src="../images/news-title-bg.gif" width="2" height="27">
						            	</td>
						            	<td width="93%" background="../images/news-title-bg.gif" class="left_bt2">最新动态</td>
						          	</tr>
						          	<tr>
						            	<td height="102" valign="top">&nbsp;</td>
						            	<td height="102" valign="top"></td>
						          	</tr>
						          	<tr>
						            	<td height="5" colspan="2">&nbsp;</td>
						          	</tr>
        						</table>
        					</td>
      					</tr>
				      	<tr>
				        	<td colspan="2">&nbsp;</td>
				        	<td>&nbsp;</td>
				        	<td>&nbsp;</td>
				      	</tr>
      					<tr>
        					<td colspan="2" valign="top">
        					<!--JavaScript部分-->
              				<SCRIPT language=javascript>
								function secBoard(n){
									for(i=0;i<secTable.cells.length;i++)
										secTable.cells[i].className="sec1";
									secTable.cells[n].className="sec2";
									for(i=0;i<mainTable.tBodies.length;i++)
										mainTable.tBodies[i].style.display="none";
									mainTable.tBodies[n].style.display="block";
								}
          					</SCRIPT>
              				<!--HTML部分-->
              					<TABLE width=72% border=0 cellPadding=0 cellSpacing=0 id=secTable>
                					<TBODY>
                  						<TR align=center height=20>
						                    <TD align="center" class=sec2 onclick=secBoard(0)>审核信息</TD>
						                    <TD align="center" class=sec1 onclick=secBoard(1)>统计信息</TD>
						                    <TD align="center" class=sec1 onclick=secBoard(2)>系统参数</TD>
						                    <TD align="center" class=sec1 onclick=secBoard(3)>版权说明</TD>
						                </TR>
                					</TBODY>
              					</TABLE>
          						<TABLE class=main_tab id=mainTable cellSpacing=0 cellPadding=0 width=100% border=0>
                					<!--关于TBODY标记-->
                					<TBODY style="DISPLAY: block">
                  						<TR>
                    						<TD vAlign=top align=center>
                    							<TABLE width=98% height="133" border=0 align="center" cellPadding=0 cellSpacing=0>
                        							<TBODY>
						                          		<TR>
						                            		<TD height="5" colspan="3"></TD>
						                          		</TR>
						                          		<TR>
						                            		<TD width="4%" height="28" background="../images/news-title-bg.gif"></TD>
						                            		<TD height="25" colspan="2" background="../images/news-title-bg.gif" class="left_txt"> 
						                            			亲爱的管理员：<font color="#FFFFFF" class="left_ts"><b>${authentication.username}您好！</b></font>
						                            		</TD>
						                          		</TR>
						                          		<TR>
						                            		<TD bgcolor="#FAFBFC">&nbsp;</TD>
						                            		<TD align="left" width="42%" height="25" bgcolor="#FAFBFC">
						                            			<span class="left_txt">您有未审核的志愿者信息：0 </span>
																<span class="left_ts"> </span>
															</TD>
						                            		<TD align="left" width="54%" height="25" bgcolor="#FAFBFC">
						                            			<span class="left_txt">您有未审核的帮扶对象信息：0 </span>
						                                		<span class="left_ts"> </span>
						                                	</TD>
						                          		</TR>
						                          		<TR>
						                            		<TD bgcolor="#FAFBFC">&nbsp;</TD>
						                            		<TD width="42%" height="25" bgcolor="#FAFBFC">
						                            			<span class="left_txt"> </span>
																<span class="left_ts"> </span>
															</TD>
						                            		<TD width="54%" height="25" bgcolor="#FAFBFC">
						                            			<span class="left_txt"> </span>
						                                		<span class="left_ts"> </span>
						                                	</TD>
						                          		</TR>
						                          		<TR>
						                            		<TD bgcolor="#FAFBFC">&nbsp;</TD>
						                            		<TD width="42%" height="25" bgcolor="#FAFBFC">
						                            			<span class="left_txt"> </span>
																<span class="left_ts"> </span>
															</TD>
						                            		<TD width="54%" height="25" bgcolor="#FAFBFC">
						                            			<span class="left_txt"> </span>
						                                		<span class="left_ts"> </span>
						                                	</TD>
						                          		</TR>
						                          		<TR>
						                            		<TD bgcolor="#FAFBFC">&nbsp;</TD>
						                            		<TD width="42%" height="25" bgcolor="#FAFBFC">
						                            			<span class="left_txt"> </span>
																<span class="left_ts"> </span>
															</TD>
						                            		<TD width="54%" height="25" bgcolor="#FAFBFC">
						                            			<span class="left_txt"> </span>
						                                		<span class="left_ts"> </span>
						                                	</TD>
						                          		</TR>
						                          		<TR>
						                            		<TD height="5" colspan="3"></TD>
						                          		</TR>
						                        	</TBODY>
                    							</TABLE>
                    						</TD>
                  						</TR>
                					</TBODY>
                					<!--关于cells集合-->
                					<TBODY style="DISPLAY: none">
                  						<TR>
                    						<TD vAlign=top align=center>
                    							<TABLE width=98% height="133" border=0 align="center" cellPadding=0 cellSpacing=0>
                        							<TBODY>
						                          		<TR>
						                            		<TD height="5" colspan="3"></TD>
						                          		</TR>
							                          	<TR>
							                            	<TD width="4%" height="28" background="../images/news-title-bg.gif"></TD>
							                            	<TD colspan="2" background="../images/news-title-bg.gif" class="left_txt">
							                            		现有志愿者：167名&nbsp;&nbsp; 
							                            	</TD>
							                          	</TR>
							                          	<TR>
							                            	<TD bgcolor="#FAFBFC">&nbsp;</TD>
							                            	<TD align="left" width="42%" height="25" bgcolor="#FAFBFC">
							                            		<span class="left_txt">平台发布团体活动： </span>
							                            		<span class="left_txt">个</span>
							                            	</TD>
							                            	<TD align="left" width="54%" bgcolor="#FAFBFC">
							                            		<span class="left_txt">平台发布帮扶活动： </span>
							                            		<span class="left_txt">个</span>
							                            	</TD>
							                          	</TR>
							                          	<TR>
							                            	<TD bgcolor="#FAFBFC">&nbsp;</TD>
							                            	<TD align="left" height="25" bgcolor="#FAFBFC">
							                            		<span class="left_txt">平台现有志愿者： </span>
							                            		<span class="left_txt">人</span>
							                            	</TD>
							                            	<TD align="left" height="25" bgcolor="#FAFBFC">
							                            		<span class="left_txt">平台现有帮扶对象： </span>
							                            		<span class="left_txt">人</span>
							                            	</TD>
							                          	</TR>
							                          	<TR>
							                            	<TD bgcolor="#FAFBFC">&nbsp;</TD>
							                            	<TD height="25" bgcolor="#FAFBFC">
							                            		<span class="left_txt"></span>
							                            		<span class="left_txt"></span>
							                            	</TD>
							                            	<TD height="25" bgcolor="#FAFBFC">
							                            		<span class="left_txt"></span>
							                            		<span class="left_txt"></span>
							                            	</TD>
							                          	</TR>
							                          	<TR>
							                            	<TD bgcolor="#FAFBFC">&nbsp;</TD>
							                            	<TD height="25" bgcolor="#FAFBFC">
							                            		<span class="left_txt"> </span>
							                            		<span class="left_txt"></span>
							                            	</TD>
							                            	<TD height="25" bgcolor="#FAFBFC">
							                            		<span class="left_txt"></span>
							                            		<span class="left_txt"></span>
							                            	</TD>
							                          	</TR>
							                          	<TR>
							                            	<TD height="5" colspan="3"></TD>
							                          	</TR>
                        							</TBODY>
                    							</TABLE>
                    						</TD>
                  						</TR>
                					</TBODY>
                					<!--关于tBodies集合-->
                					<TBODY style="DISPLAY: none">
                  						<TR>
                    						<TD vAlign=top align=center>
                    							<TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                        							<TBODY>
							                          	<TR>
							                            	<TD colspan="3"></TD>
							                          	</TR>
							                          	<TR>
							                            	<TD height="5" colspan="3"></TD>
							                          	</TR>
                          								<TR>
                            								<TD width="4%" height="25" background="../images/news-title-bg.gif"></TD>
                            								<TD align="left" height="25" colspan="2" background="../images/news-title-bg.gif" class="left_txt">
                            									<span class="TableRow2">服务器IP:127.0.0.1</span>&nbsp;&nbsp;&nbsp;&nbsp;系统版本：Linux AS4
                            								</TD>
                            								 
                          								</TR>
                          								<TR>
								                            <TD height="25" bgcolor="#FAFBFC">&nbsp;</TD>
								                            <TD align="left" width="42%" height="25" bgcolor="#FAFBFC">
								                            	<span class="left_txt">服务器类型：</span>
								                            	<span class="left_ts">
								                            		<b style="color:blue">HP DL360</b>
								                            	</span>
								                            </TD>
								                            <TD align="left" width="54%" height="25" bgcolor="#FAFBFC">
								                            	<span class="left_txt">脚本解释引擎：</span>
								                            	<span class="left_ts">
								                            		<b style="color:blue">ECMAScript</b>
								                            	</span>
								                            </TD>
								                        </TR>
								                        <TR>
								                            <TD height="25" bgcolor="#FAFBFC">&nbsp;</TD>
								                            <TD align="left" height="25" colspan="2" bgcolor="#FAFBFC">
								                            	<span class="left_txt">站点物理路径：</span>
								                            </TD>
								                        </TR>
								                        <TR>
								                            <TD height="25" bgcolor="#FAFBFC">&nbsp;</TD>
								                            <TD align="left" height="25" bgcolor="#FAFBFC">
								                            	<span class="left_txt">缓存及版本：</span>
								                            	<span class="left_ts">
								                            		<b style="color:blue">MEMCACHED</b>
								                            	</span>
								                            </TD>
								                            <TD align="left" height="25" bgcolor="#FAFBFC">
								                            	<span class="left_txt">数据库及版本：</span>
								                            	<span class="left_ts">
								                            		<b style="color:blue">ORACLE</b>
								                            	</span>
								                            </TD>
                          								</TR>  
							                          	<TR>
							                            	<TD height="5" colspan="3"></TD>
							                          	</TR>
                        							</TBODY>
                    							</TABLE>
                    						</TD>
                  						</TR>
                					</TBODY>
                					<!--关于display属性-->
                					<TBODY style="DISPLAY: none">
                  						<TR>
                    						<TD vAlign=top align=center>
                    							<TABLE width=98% border=0 align="center" cellPadding=0 cellSpacing=0>
                        							<TBODY>
							                          	<TR>
							                            	<TD colspan="3"></TD>
							                          	</TR>
							                          	<TR>
							                            	<TD height="5" colspan="3"></TD>
							                          	</TR>
							                          	<TR>
							                            	<TD width="4%" background="../images/news-title-bg.gif"></TD>
							                            	<TD align="left" width="91%" background="../images/news-title-bg.gif" class="left_ts">程序说明：</TD>
							                            	<TD width="5%" background="../images/news-title-bg.gif" class="left_txt">&nbsp;</TD>
							                          	</TR>
							                          	<TR>
							                            	<TD bgcolor="#FAFBFC">&nbsp;</TD>
							                            	<TD align="left" bgcolor="#FAFBFC" class="left_txt"><span class="left_ts">1、</span><b>版权所有：南通市精神文明建设指导委员会</b>  </TD>
							                            	<TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
							                          	</TR>
							                          	<TR>
							                            	<TD bgcolor="#FAFBFC">&nbsp;</TD>
							                            	<TD align="left" bgcolor="#FAFBFC" class="left_txt"><span class="left_ts">4、</span><b>技术支持：中国移动通信集团江苏有限公司</b></TD>
							                            	<TD bgcolor="#FAFBFC" class="left_txt">&nbsp;</TD>
							                          	</TR>
							                          	<TR>
							                            	<TD height="5" colspan="3"></TD>
							                          	</TR>
							                        </TBODY>
                    							</TABLE>
                    						</TD>
                  						</TR>
                					</TBODY>
            					</TABLE>
            				</td>
        					<td>&nbsp;</td>
        					<td valign="top">
        						<table width="100%" height="144" border="0" cellpadding="0" cellspacing="0" class="line_table">
					          		<tr>
					            		<td width="7%" height="27" background="../images/news-title-bg.gif">
					            			<img src="../images/news-title-bg.gif" width="2" height="27">
					            		</td>
					            		<td width="93%" background="../images/news-title-bg.gif" class="left_bt2">程序说明</td>
					          		</tr>
          							<tr>
						            	<td height="102" valign="top">&nbsp;</td>
						            	<td height="102" valign="top">
						            		<label></label>
              								<label>
              									<textarea name="textarea" cols="48" rows="8" class="left_txt"></textarea>
              								</label>
              							</td>
          							</tr>
						          	<tr>
						            	<td height="5" colspan="2">&nbsp;</td>
						          	</tr>
        						</table>
        					</td>
      					</tr>
      					<tr>
        					<td height="40" colspan="4">
        						<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
          							<tr>
            							<td></td>
          							</tr>
        						</table>
        					</td>
      					</tr>
      					<tr>
        					<td width="2%">&nbsp;</td>
        					<td width="51%" class="left_txt">
        						<img src="../images/icon-mail2.gif" width="16" height="11"> <font color="#FFFFFF" class="left_ts"><b>版权所有：南通市精神文明建设指导委员会 </b></font><br>
              					<img src="../images/icon-phone.gif" width="17" height="14"> <font color="#FFFFFF" class="left_ts"><b>技术支持：中国移动通信集团江苏有限公司</b></font>
              				</td>
					        <td>&nbsp;</td>
					        <td>&nbsp;</td>
      					</tr>
    				</table>
    			</td>
    			<td background="../images/mail_rightbg.gif">&nbsp;</td>
  			</tr>
  			<tr>
    			<td valign="bottom" background="../images/mail_leftbg.gif">
    				<img src="../images/buttom_left2.gif" width="17" height="17" />
    			</td>
    			<td background="../images/buttom_bgs.gif">
    				<img src="../images/buttom_bgs.gif" width="17" height="17">
    			</td>
    			<td valign="bottom" background="../images/mail_rightbg.gif">
    				<img src="../images/buttom_right2.gif" width="16" height="17" />
    			</td>
  			</tr>
		</table>
	</body>
</html>