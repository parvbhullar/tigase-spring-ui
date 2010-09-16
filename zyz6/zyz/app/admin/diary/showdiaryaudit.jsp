<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>活动管理</title>
		<link href="../../../manage/images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<link href="/zyz/javascript/jqueryUI-1.8.2/themes/ui-lightness/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" />
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jquery.treeTable.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.core.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.widget.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.position.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.dialog.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/tree/MzTreeView12-pack.js"/>" type="text/javascript"></script>
		<script src="showactive.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(document).ready(function() {
			//审核用户
		    $("#form-createorgtype").dialog({
				autoOpen: false,
				height: 200,
				width: 350,
				modal: true,
				buttons: {
						'通过': function() {
		    	$.ajax({
					   type: "POST",
					   url: "auditDiary.action",
					   data: { recid:$("#recid").val(),isverify: 1 },
					   success: function(data){
						   if(data==1)
							   location.reload();
						   else
							   alert("更新失败!");
					   }
					 	});
							$(this).dialog('close');
					},
					'不通过': function() {
						$.ajax({
							   type: "POST",
							   url: "auditDiary.action",
							   data: { recid:$("#recid").val(),isverify: 2 },
							   success: function(data){
								   if(data==1)
									   location.reload();
								   else
									   alert("更新失败!");
							   }
							 });
						$(this).dialog('close');
				},
					'退出': function() {
						$(this).dialog('close');
					}
				}
			});
			
			$(".class_audit").click(function(){
				$("#recid").val($(this).attr("recid"));
				$('#form-createorgtype').dialog('open');
			});	
		});
		</script>
	</head>
	<body>
		<input type="hidden" id="recid"/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../../../manage/images/mail_leftbg.gif">
    				<img src="../../../manage/images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../../../manage/images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">活动管理</div></td>
			      		</tr>
    				</table>
    			</td>
    			<td width="16" valign="top" background="../../../manage/images/mail_rightbg.gif">
    				<img src="../../../manage/images/nav-right-bg.gif" width="16" height="29" />
    			</td>
  			</tr>
  			
  			<tr>
    			<td height="71" valign="middle" background="../../../manage/images/mail_leftbg.gif">&nbsp;</td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;活动列表</td>
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
									                	<img src="../../../manage/images/title.gif" width="54" height="55">
									                </td>
									                <td width="90%" valign="top">
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;活动列表</td> 
                									<td class="left_bt2" align="right">
                									</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
           									<table width="100%" border="1" cellspacing="0" cellpadding="0" id="orgtypetabletree">
           										<thead>
													<tr>
														<th width="20%" class="left_txt2">日志标题</th>
														<th width="10%" class="left_txt2">日志内容</th>
														<th width="10%" class="left_txt2">发送时间</th>
														<th width="10%" class="left_txt2">状态</th>
														<th width="50%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="diary" items="${diaryList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
														<tr height="10" bgcolor="#f2f2f2" id="${diary.recid}" class="child-of-${diary.recid}">
														
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0}">
														<tr height="10" id="${diary.recid}" class="child-of-${diary.recid}">
													</c:if>
													<td align="center">${diary.title }</td>
													<td align="center">${diary.content}</td>
													<td align="center">${diary.sendtime}</td>
													<td align="center">
													<c:if test="${(diary.isverify) ==0 }">
														待审核
													</c:if>
													<c:if test="${(diary.isverify) ==1 }">
														通过
													</c:if>
													<c:if test="${(diary.isverify) ==2 }">
														不通过
													</c:if>
													</td>
													<td align="center">
													<a href="javascript:void" class="class_audit" recid="${diary.recid}">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
														</tr>
													</c:forEach>
												</tbody>
           									</table>
            							</td>
          							</tr>
        						</table>
         					</td>
      					</tr>
    				</table>
    			</td>
    			<td background="../../../manage/images/mail_rightbg.gif">&nbsp;</td>
  			</tr>
  			<tr>
    			<td valign="middle" background="../../../manage/images/mail_leftbg.gif">
    				<img src="../../../manage/images/buttom_left2.gif" width="17" height="17" />
    			</td>
      			<td height="17" valign="top" background="../../../manage/images/buttom_bgs.gif">
      				<img src="../../../manage/images/buttom_bgs.gif" width="17" height="17" />
      			</td>
    			<td background="../../../manage/images/mail_rightbg.gif">
    				<img src="../../../manage/images/buttom_right2.gif" width="16" height="17" />
    			</td>
  			</tr>
		</table>
		<div id="form-createorgtype" title="审核">
			<p class="validateTips"></p>
			<form>
			</form>
		</div>
	</body>
</html>