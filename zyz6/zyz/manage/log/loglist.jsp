<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<%@ page language="java" import="com.linkage.app.gqt.backstage.active.controller.ActiveController" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>志愿者管理</title>
		<link href="../../images/skin.css" rel="stylesheet" type="text/css" />
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
		<script type="text/javascript">
		//设置发送短信的人员ID
		function showsel()
		{
			
			var es=document.getElementsByName("sel");
			
			var out="";
			for(var i=0;i<es.length;i++)
			{
				if (es[i].checked&&('u'==es[i].value.substring(es[i].value.length-1))) out+=es[i].value.substring(0,(es[i].value.length-1))+",";
			}
			
			$("#sendUuidId").val(out);
		}

function getInputNum() {
var inputLen = $("#messageId").text().length;
if (inputLen <= 140) {
var contentStr = "您还可以输入 <span>" + (140-inputLen) +"</span> 字 &nbsp; &nbsp; &nbsp;";
$("#inputNumDiv").html(contentStr);
} else {
var contentStr = "已超出 <span>" + (inputLen - 140) +"</span> 字 &nbsp; &nbsp; &nbsp;";
$("#inputNumDiv").html(contentStr);
}
}

function manualSel(actId)
{
$("#actIdId").val(actId);
$('#kkk').dialog('open');
}

function autoSel(actId)
{
$("#actIdId").val(actId);
$('#sendMessage').dialog('open');
showsel();
}

$(document).ready(function() {
	
	var postulantid=$("#postulantid");
	var postulants=$(".postulant");

	postulantid.click(function(){//还有两个逻辑没有实现,1:全部选中时自动选中总选控件,全部不选中时按钮变灰 
		var _tmp=$(this).attr("checked");
		postulants.attr("checked",_tmp);
	});
	

	postulants.click(function(){
		if(!($(this).attr("checked"))){
			postulantid.attr("checked",false);
		}
	});

	$("#dels").click(function(){
		if(confirm("确认进行本次批量删除志愿者操作?")){
			var allId='';
			$.each(postulants,function(i,n){
				if(n.checked){
					if(allId){
						allId=n.value+","+allId;
					}else{
						allId=n.value;
					}
				}
			});
			if(allId=='')
			{
				alert('请选择要删除的志愿者');
				return;
			}
			
			$.ajax({
				type: "POST",
				url: "dels.action",
				data: "allId="+allId,
				success: function(msg){
					alert('操作成功!');
					window.location.reload();
				}
			});
		}
	});

	
	$(".class_del").click(function() {
		return confirm("确认要删除帮扶者[" + this.name + "]?");
	});
	
	// 查询
	$("#search-postulant").dialog( {
		autoOpen : false,
		height : 350,
		width : 450,
		modal : true,
		buttons : {
			'确认' : function() {
				document.searchForm.submit();
				$(this).dialog('close');
			},
			'退出' : function() {
				$(this).dialog('close');
			}
		}
	});
	// 增加志愿者
		$("#form-createactivity").dialog( {
			autoOpen : false,
			height : 450,
			width : 400,
			modal : true,
			buttons : {
				'确认' : function() {
					alert(document.insertForm.userName.value);
					document.insertForm.submit();
					$(this).dialog('close');
				},
				'退出' : function() {
					$(this).dialog('close');
				}
			}
		});

		// 更改状态
		$("#form-changestate")
				.dialog(
						{
							autoOpen : false,
							height : 200,
							width : 300,
							modal : true,
							buttons : {
								'退出' : function() {
									$(this).dialog('close');
								},
								'确认' : function() {
									$.ajax( {
												type : "POST",
												url : "changeState.action",
												data : {
													status : $(
															"input[name='state'][type='radio'][checked]")
															.val(),
													actId : $("#actId")
															.val()
												},
												success : function(data) {
													if (data == 1)
														location
																.reload();
													else
														alert("更新失败!");
												}
											});
									$(this).dialog('close');
								}

							}
						});

		// 自动匹配树
		$("#kkk")
				.dialog(
						{
							autoOpen : false,
							height : 450,
							width : 250,
							modal : true,
							buttons : {
							'退出' : function() {
								$(this).dialog('close');
							},
							'确认' : function() {
								showsel();
								/*
								$.ajax( {
											type : "POST",
											url : "changeState.action",
											data : {
												status : $(
														"input[name='state'][type='radio'][checked]")
														.val(),
												actId : $("#actId")
														.val()
											},
											success : function(data) {
												if (data == 1)
													location.reload();
												else
													alert("更新失败!");
											}
										});
									*/
								$(this).dialog('close');
								$('#sendMessage').dialog('open');
							}
						}
					});
		
		// 发送短消息
		$("#sendMessage")
				.dialog(
						{
							autoOpen : false,
							height : 250,
							width : 350,
							modal : true,
							buttons : {
							'退出' : function() {
								$(this).dialog('close');
							},
							'确认' : function() {
								$.ajax( {
									type : "POST",
									url : "sendMessage.action",
									data : {
										sendUuid : $("#sendUuidId").val(),
										message:$("#messageId").val(),
										actId:$("#actIdId").val()
									},
									success : function(data) {
										if (data == 1)
											location.reload();
										else
											alert("更新失败!");
									}
								});
								$(this).dialog('close');
							}
						}
					});
		// 导入志愿者
		$("#form-imppostulant")
				.dialog(
						{
							autoOpen : false,
							height : 200,
							width : 350,
							modal : true,
							buttons : {
							'退出' : function() {
								$(this).dialog('close');
							},
							'确认' : function() {
								var filename = $("#postulantfile").val();
								if(filename=='')
								{
									alert('请选择要导入的文件!');
									return;
								}
								var suffix = filename.substring(filename.lastIndexOf(".")+1,filename.length).toLowerCase();
							    if(suffix!='xls' && suffix!='xlsx'){
							    	alert('导入文件必须是Excel格式');
							    	return;
								}
							    document.impform.submit();
								$(this).dialog('close');
							}
						}
					});
		
		$("#createactivity").click(function() {
			$('#form-createactivity').dialog('open');
		});
		$("#imppostulant").click(function() {
			$('#form-imppostulant').dialog('open');
		});
		
		$("#kkk1").click(function() {
			$('#kkk').dialog('open');
		});

		$("#searchp").click(function() {
			$('#search-postulant').dialog('open');
		});
		

		$(".class_changestate").click(
				function() {
					$("#actId").val($(this).attr("actId"));
					$("input[name='state'][type='radio'][value='1']")
							.attr("checked", 'checked');
					$('#form-changestate').dialog('open');
				});

		$(".class_tree").click(function() {
			alert(this.value());
			$('#kkk').dialog('open');
		});
		
		$(".class_audit").click(function() {
			$("#actId").val($(this).attr("actId"));
			$('#form-createorgtype').dialog('open');
		});

	});
		</script>
		<style>
body {font:normal 12px 宋体}
a.MzTreeview /* TreeView 链接的基本样式 */ { cursor: hand; color: #000080; margin-top: 5px; padding: 2 1 0 2; text-decoration: none; }
.MzTreeview a.select /* TreeView 链接被选中时的样式 */ { color: highlighttext; background-color: highlight; }
#kkk input {
vertical-align:middle;
}
.MzTreeViewRow {border:none;width:500px;padding:0px;margin:0px;border-collapse:collapse}
.MzTreeViewCell0 {border-bottom:1px solid #CCCCCC;padding:0px;margin:0px;}
.MzTreeViewCell1 {border-bottom:1px solid #CCCCCC;border-left:1px solid #CCCCCC;width:200px;padding:0px;margin:0px;}
</style>
	</head>
	<body>
		<input type="hidden" name="create_orgTypeParentId" id="actId" value=""/>
		<input type="hidden" name="create_orgTypeParentId" id="actIdd" value=""/>
		<input type="hidden" name="sendUuid" id="sendUuidId" value=""/>
		<input type="hidden" name="actId" id="actIdId" value=""/>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">帮扶者管理</div></td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;日志列表</td>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;日志列表</td> 
                									<td class="left_bt2" align="right">
                									<button id="searchp">查询</button>&nbsp;&nbsp;
                									
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
													    <th width="5%"><input type="checkbox" id="postulantid" /></th>
														<th width="20%" class="left_txt2">用户登录名</th>
						
														<th width="20%" class="left_txt2">用户昵称</th>
														<th width="20%" class="left_txt2">操作类型</th>
														<th width="20%" class="left_txt2">操作日期</th>
														<th width="15%" class="left_txt2">操作IP</th>
														
														
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="log" items="${logs}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
														<tr height="10" bgcolor="#f2f2f2" id="${log.logid}" class="child-of-${log.logid}">
														
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0}">
														<tr height="10" id="${log.logid}" class="child-of-${log.logid}">
													</c:if>
													<td align="center"><input type="checkbox" class="postulant" name="postulant${log.logid }" value="${log.logid }"/></td>
													<td align="center">${log.loginname }</td>
													<td align="center">${log.nickname }</td>
													<td align="center">${log.tname }</td>
													<td align="center">${log.datestr }</td>
													<td align="center">${log.logip }</td>
													<td align="center">
													
													</td>
													
													
													
														</tr>
													</c:forEach>
												</tbody>
           									</table>
           									<table>
           										<tr>
           											<td>${toolNav }</td>
           										</tr>
           									</table>
            							</td>
          							</tr>
        						</table>
         					</td>
      					</tr>
    				</table>
    			</td>
    			<td background="../images/mail_rightbg.gif">&nbsp;</td>
  			</tr>
  			<script>
  				function goPage(page)
  				{
  	  				document.getElementById("page").value=page;
  	  				document.searchForm.submit();
  	  			}
  			</script>
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
		
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		
		<div id="search-postulant" title="查询" style="width:100px;height:150px">
			<form action="loglist.action" name="searchForm" method="post">
			<label>登录名:</label>
					<input type="text" name="loginname" id="loginname" value="${loginname }" class="text ui-widget-content ui-corner-all" /><br>
			<label>操作名:</label>
					<input type="text" name="typename" id="typename" value="${typename }" class="text ui-widget-content ui-corner-all" /><br>
			<label>昵     称:</label>
					<input type="text" name="nickname" id="nickname" value="${nickname }" class="text ui-widget-content ui-corner-all" /><br>
					
				    <input type="hidden" name="page" id="page" value="1"/><br>
			</form>
		</div>
		
		
		
		
		
		
	
	
	</body>
</html>