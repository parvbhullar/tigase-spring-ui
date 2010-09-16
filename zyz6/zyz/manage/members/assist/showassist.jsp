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
		if(confirm("确认进行本次批量删除帮扶对象操作?")){
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
				alert('请选择要删除的帮扶对象');
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
					var regdn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
					var idcard =  /^\d{15}(\d{2}[X0-9])?$/;
					
										
					var phone =  /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/;
					
					if(document.insertForm.name.value=='')
					{
						alert('请输入姓名!');
						return;
					}
					if(document.insertForm.cellPhone.value!='')
					{
						if(!regdn.test(document.insertForm.cellPhone.value)){
							alert("手机号码格式错误!");
					   		return;
						}
					}
					if(document.insertForm.credtype.value=='')
					{
						alert('请选择证件类型!');
						return;
					}
					if(document.insertForm.credcode.value=='')
					{
						alert('请输入证件号码!');
						return;
					}
					if(!idcard.test(document.insertForm.credcode.value)){
						alert("身份证格式错误!");
				   		return;
					}
					if(document.insertForm.volunorgname1.value=='')
					{
						alert('请选择社区!');
						return;
					}
					if(document.insertForm.hometel.value!='')
					{
						if(!phone.test(document.insertForm.hometel.value)){
							alert("电话号码格式错误!");
					   		return;
						}
					}
					if(document.insertForm.address.value=='')
					{
						alert('请填写家庭地址!');
						return;
					}
					document.insertForm.submit();
					$(this).dialog('close');
				},
				'退出' : function() {
					document.insertForm.reset();
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

								var es=document.getElementsByName("sel");
								
								var out="";
								var oid;
								for(var i=0;i<es.length;i++)
								{
									
									if(es[i].checked)
									{
										oid=es[i].value;
									}
									//if (es[i].checked&&('u'==es[i].value.substring(es[i].value.length-1))) out+=es[i].value.substring(0,(es[i].value.length-1))+",";
								}
								
								$('#volunorgid').val(oid);
								$(this).dialog('close');
							}
						}
					});
		// 自动匹配树
		$("#kkk11")
				.dialog(
						{
							autoOpen : false,
							height : 450,
							width : 250,
							modal : true,
							buttons : {
							'退出' : function() {
								$(this).dialog('close');
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
								var vid = $("#volunorgid1").val();
								if(vid=='')
								{
									alert('请选择社区');
									return;
								}
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
		$("#kkk2").click(function() {
			$('#kkk11').dialog('open');
		});

		$("#searchp").click(function() {
			$('#search-postulant').dialog('open');
		});
		$("#download").click(function() {
			document.location.href='downtemplate.action';
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;帮扶者列表</td>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;帮扶者列表</td> 
                									<td class="left_bt2" align="right">
                									<button id="searchp">查询</button>&nbsp;&nbsp;
                									<button id="createactivity">新增</button>&nbsp;&nbsp;
                									
                									<button id="imppostulant">导入</button>&nbsp;&nbsp;
                									<button id="dels">删除</button>&nbsp;&nbsp;
                									<button id="download">模板下载</button>&nbsp;&nbsp;
                									
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
														
														<th width="5%" class="left_txt2">用户姓名</th>
														
														<th width="8%" class="left_txt2">手机号码</th>
														<th width="8%" class="left_txt2">社区</th>
														<th width="5%" class="left_txt2">性别</th>
														<th width="10%" class="left_txt2">证件号码</th>
														<th width="10%" class="left_txt2">生日</th>
														<th width="8%" class="left_txt2">审核状态</th>
														<th width="15%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="assist" items="${assistList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
														<tr height="10" bgcolor="#f2f2f2" id="${assist.aid}" class="child-of-${assist.aid}">
														
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0}">
														<tr height="10" id="${assist.aid}" class="child-of-${assist.aid}">
													</c:if>
													<td align="center"><input type="checkbox" class="postulant" name="postulant${assist.aid }" value="${assist.aid }"/></td>
										
													<td align="center">${assist.name }</td>
													
													<td align="center">&nbsp;${assist.cellPhone }</td>
													<td align="center">${assist.volunorgname }</td>
													<td align="center">&nbsp;
													<c:if test="${(assist.sex) ==1 }">
														男
													</c:if>
													<c:if test="${(assist.sex) ==2 }">
														女
													</c:if>
													</td>
													<td align="center">${assist.credcode }</td>
													<td align="center">&nbsp;${assist.birthday }</td>
													<td align="center">
													<c:if test="${(assist.isverify) ==0 }">
														待审核
													</c:if>
													<c:if test="${(assist.isverify) ==1 }">
														审核通过
													</c:if>
													<c:if test="${(assist.isverify) ==2 }">
														审核不通过
													</c:if>
													
													</td>
													<td align="center">
	<a href="edit.action?aid=${assist.aid}">修改</a>&nbsp;												
	<a href="view.action?aid=${assist.aid}">查看</a>&nbsp;
	<!-- 
	<c:if test="${(assist.isverify) ==0 }">
		<a href="verify.action?aid=${assist.aid}&verify=1&page=${page}">审批</a>&nbsp;
	</c:if>
	<c:if test="${(assist.isverify) ==1 }">
		<a href="verify.action?aid=${assist.aid}&verify=2&page=${page}">驳回</a>&nbsp;
	</c:if>
	<c:if test="${(assist.isverify) ==2 }">
		<a href="verify.action?aid=${assist.aid}&verify=1&page=${page}">审批</a>&nbsp;
	</c:if>
	 -->
	<a href="delete.action?aid=<c:url value="${assist.aid}"/>&page=${page}" class="class_del" id="del" name="${assist.name}">删除</a>&nbsp;
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
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<div id="form-createactivity" title="增加帮扶对象" style="width:100px;height:150px;display: none">
			<form action="saveAssist.action" name="insertForm" method="post">
			
			<label>姓名:</label>
					<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" /><font color="red">*</font><br>
			<label>手机:</label>
					<input type="text" name="cellPhone" id="cellPhone" class="text ui-widget-content ui-corner-all" /><br>
			<label>性别:</label>
					<input type="radio" value="1" checked="checked" name="sex">男<input type="radio" value="2" name="sex">女<br>
			<label>生日:</label>
					<input type="text" name="birthday1" onfocus="WdatePicker();" class="text ui-widget-content ui-corner-all" /><br>
			<label>证件类型:</label>
					<select name="credtype">
						<option value="" label=""></option>
			          	<c:forEach items="${zjlx}" var="current">
		        			<option value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
		      			</c:forEach>
					</select><font color="red">*</font><br>
			<label>证件号码:</label>
					<input type="text" name="credcode" id="credcode" class="text ui-widget-content ui-corner-all" />
					<font color="red">*</font>
					<br>
			
					<input type="hidden" name="volunorgid" id="volunorgid" class="text ui-widget-content ui-corner-all" />
					<input type="hidden" name="volunorgname" id="volunorgname_1" class="text ui-widget-content ui-corner-all" />
			<label>社区名称:</label>
					<input type="text" disabled="disabled" name="volunorgname1" id="volunorgname" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
					<a href="javascript:void(0)" id="kkk1">选择</a>&nbsp;&nbsp;
					<br>
			<label>家庭电话:</label>
					<input type="text" name="hometel" id="hometel" class="text ui-widget-content ui-corner-all" /><br>
			<label>家庭地址:</label>
					<input type="text" name="address" id="address" class="text ui-widget-content ui-corner-all" /><font color="red">*</font><br>
			</form>
		</div>
		
		<div id="search-postulant" title="查询" style="width:100px;height:150px;display: none">
			<form action="showassist.action" name="searchForm" method="post">
			<label>姓         名:</label>
					<input type="text" name="name1" id="name1" value="${s_name }" class="text ui-widget-content ui-corner-all" /><br>
			<label>身份证号:</label>
					<input type="text" name="credcode" id="credcode" value="${credcode }" class="text ui-widget-content ui-corner-all" /><br>
					
				    <input type="hidden" name="page" id="page" value="1"/><br>
			</form>
		</div>
		
		<div id="form-imppostulant" title="导入帮扶对象" style="width:100px;height:150px;display: none">
			<form name="impform" action="impassist.action" method="post" enctype="multipart/form-data">
			<label>社区ID:</label>
					<input type="text" name="volunorgid" id="volunorgid1" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
					<br>
			<label>社区名称:</label>
					<input type="text" name="volunorgname" id="volunorgname1" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
					<a href="javascript:void(0)" id="kkk2">选择</a>&nbsp;&nbsp;
					<br>
			<label>选择文件:</label>
					<input type="file" name="impfile" id="postulantfile" class="text ui-widget-content ui-corner-all" /><br>
			</form>
		</div>
		
		<div id="form-changestate" title="更改状态" style="width:100px;height:150px;display: none">
			<form>
			<input type="radio" name="state" value = "0">结束<br>

			<input type="radio" name="state" value = "1">在线<br>

			<input type="radio" name="state" value = "2">停止<br>
			</form>
		</div>
		<div id="kkk" title="选择社区" style="display: none;"></div>
		<div id="kkk11" title="选择社区" style="display: none;"></div>
		
		
		
<script language="javascript" type="text/javascript">
window.selTree = new MzTreeView("selTree");
selTree.setIconPath("../../javascript/tree/images/"); //可用相对路径

selTree.N['<c:out value="${province}"/>'] = 'T:<c:out value="${currentOrgName}"/>;C:selOrgId()'
<c:forEach items="${selOrgList}" var="current">
	<c:if test="${current.orgTypeId==100}">
	selTree.N['<c:out value="${current.orgName}"/>'] = 'T:<c:out value="${current.orgContactor}"/>;C:selOrgId()'
	</c:if>
</c:forEach>
// selTree.setURL("/");
selTree.wordLine = false;
selTree.setTarget("main");
document.getElementById("kkk").innerHTML=selTree.toString();
selTree.expandAll();
/******************* 选择组织 的组织树 结束********************/
function selOrgId(){
	// 获取当前节点的组织ID
	var selectOrgId = selTree.currentNode.sourceIndex.split("_")[1];
	
	$("#volunorgid").val(selectOrgId);
	$("#volunorgname").val(selTree.currentNode.T);
	$("#volunorgname_1").val(selTree.currentNode.T);
	$("#kkk").dialog('close');
}
</script>
<script language="javascript" type="text/javascript">
window.selTree1 = new MzTreeView("selTree1");
selTree1.setIconPath("../../javascript/tree/images/"); //可用相对路径

selTree1.N['<c:out value="${province}"/>'] = 'T:<c:out value="${currentOrgName}"/>;C:selOrgId1()'
<c:forEach items="${selOrgList}" var="current">
	<c:if test="${current.orgTypeId==100}">
	selTree1.N['<c:out value="${current.orgName}"/>'] = 'T:<c:out value="${current.orgContactor}"/>;C:selOrgId1()'
	</c:if>
</c:forEach>
// selTree.setURL("/");
selTree1.wordLine = false;
selTree1.setTarget("main");
document.getElementById("kkk11").innerHTML=selTree1.toString();
selTree1.expandAll();
/******************* 选择组织 的组织树 结束********************/
function selOrgId1(){
	// 获取当前节点的组织ID
	var selectOrgId1 = selTree1.currentNode.sourceIndex.split("_")[1];
	
	$("#volunorgid1").val(selectOrgId1);
	$("#volunorgname1").val(selTree1.currentNode.T);
	$("#kkk11").dialog('close');
}
	
	</script>		
	
	</body>
</html>