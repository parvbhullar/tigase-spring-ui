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
		<script src="<c:url value="/manage/log/log.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jquery.treeTable.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.core.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.widget.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.position.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.dialog.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/tree/MzTreeView12-pack.js"/>" type="text/javascript"></script>
		
		<script type="text/javascript" src="<c:url value="/javascript/jstree/_lib/jquery.cookie.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/javascript/jstree/_lib/jquery.hotkeys.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/javascript/jstree/jquery.jstree.js"/>"></script>
		
		<script type="text/javascript" src="showpostulant.js"/>"></script>
		
		<script type="text/javascript">
		var arrayAreaCity = new Array();
		<c:forEach items="${orgarea}" var="current">
			<c:if test="${current.areaParentId==1}">
				arrayAreaCity[arrayAreaCity.length]='<option value='+<c:out value="${current.areaId}"/>+' label=<c:out value="${current.areaName}"/>><c:out value="${current.areaName}"/></option>';
			</c:if>
		</c:forEach>
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

	
	for(var i=0;i<arrayAreaCity.length;i++)
	{
		$("#loccitid").append(arrayAreaCity[i]);
	}

	$('#loccitid').change(function() {
		$('#locareid').find('option').remove();
		$.ajax({
			   type: "POST",
			   url: "/zyz/app/admin/reg/load.action",
			   data: { "areaid": $(this).val() },
			   success: function(data){
				   $("#locareid").append(data);
			   }
			 });
		//changeCity($(this).val());
	  //alert();
	});
		

		
	
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
					log(3);
					window.location.reload();
				}
			});
		}
	});

	
	$(".class_del").click(function() {
		if(confirm("确认要删除志愿者[" + this.name + "]?"))
		{
			log(2);
			return true;
		}
		else
		{
			return false;
		}
	});
	
	// 查询
	$("#search-postulant").dialog( {
		autoOpen : false,
		height : 350,
		width : 450,
		modal : true,
		buttons : {
			'确认' : function() {
				log(1);
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
			height : 550,
			width : 450,
			modal : true,
			buttons : {
				'确认' : function() {
					var regmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
					var regdn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
					var idcard =  /^\d{15}(\d{2}[X0-9])?$/;
					
										
					var phone =  /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/;
					
					if(document.insertForm.name.value=='')
					{
						alert('请填写姓名！');
						return;
					}
					if(document.insertForm.dn.value=='')
					{
						alert('请填写手机号！');
						return;
					}
					if(!regdn.test(document.insertForm.dn.value)){
						alert("手机号码格式错误!");
				   		return;
					}
					if(document.insertForm.email.value!='')
					{
						if(!regmail.test(document.insertForm.email.value)){
							alert("邮箱格式错误!");
					   		return;
						}
					}
					if(document.insertForm.communityid.value=='')
					{
						alert('请选择志愿者所属社区!');
						return;
					}
					if(document.insertForm.orgname.value=='')
					{
						alert('请选择组织机构');
						return;
					}
					if(document.insertForm.credtype.value=='')
					{
						alert('请选择身份类型！');
						return;
					}
					if(document.insertForm.credcode.value=='')
					{
						alert('请填写证件号码！');
						return;
					}
					if(!idcard.test(document.insertForm.credcode.value)){
						alert("身份证格式错误!");
				   		return;
					}
					if(document.insertForm.phone.value!='')
					{
						if(!phone.test(document.insertForm.phone.value)){
							alert("电话号码格式错误!");
					   		return;
						}
					}
					//唯一性判断
					$.ajax( {
							type : "post",
							url : "validate1.action",
							data : {
								dn : document.insertForm.dn.value,
								credcode : document.insertForm.credcode.value
							},
							success : function(data) {
								
								if(data=='1')
								{
									alert('手机号重复!');
									return;
								}
								if(data=='2')
								{
									alert('身份证号码重复');
									return;
								}
								if(data=='0')
								{
									document.insertForm.submit();
									$(this).dialog('close');
								}
							}
						});
					//document.insertForm.submit();
					//$(this).dialog('close');
				},
				'退出' : function() {
					document.insertForm.reset();
					$(this).dialog('close');
				}
			}
		});
		
		// 更改状态
		$("#verify-postulant")
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
								'通过' : function() {
									$.ajax( {
												type : "POST",
												url : "logoutagree.action",
												data : {
													id : $("#logoutid").val()
												},
												success : function(data) {
													if (data == 1){
														alert('注销成功!');
														location.reload();
													}
													else{
														alert("更新失败!");
													}
												}
											});
									$(this).dialog('close');
								}
								,
								'拒绝' : function() {
									$.ajax( {
												type : "POST",
												url : "verify1.action",
												data : {
													uid : $("#logoutid").val(),
													verify : 5
													
												},
												success : function(data) {
													if (data == 1){
														alert('更新成功!');
														location.reload();
													}
													else{
														alert("更新失败!");
													}
												}
											});
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
							open:  function() {  loadJstree(); },
							buttons : {
							'退出' : function() {
								$(this).dialog('close');
							}
							
						}
					});
		// 自动匹配树
		$("#kkk101")
				.dialog(
						{
							autoOpen : false,
							height : 450,
							width : 250,
							modal : true,
							open:  function() {  loadJstree1(); },
							buttons : {
							'退出' : function() {
								$(this).dialog('close');
							}
						}
					});
		
		// 导入志愿者
		$("#form-imppostulant")
				.dialog(
						{
							autoOpen : false,
							height : 500,
							width : 400,
							modal : true,
							buttons : {
							'退出' : function() {
								$(this).dialog('close');
							},
							'确认' : function() {
								if(document.impform.communityid.value=='')
								{
									alert('请选择社区');
									return;
								}
								if(document.impform.communityname.value=='')
								{
									alert('请选择社区');
									return;
								}
								if(document.impform.orgname.value=='')
								{
									alert('请选择服务意向和组织');
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
			$('#kkk101').dialog('open');
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
		$(".jstree-leaf").live("click", function(e) {
			i=$(this).find("a").html().lastIndexOf(">");
			var orgname = $(this).find("a").html().substring(i+1);
			$("#communityid").val(this.id);
			$("#communityname").val(orgname);
			$("#communityname101").val(orgname);
			$("#communityid1").val(this.id);
			$("#communityname1").val(orgname);
			$("#kkk").dialog('close');
			$("#kkk101").dialog('close');
			//alert($(this).find("a").html().after().html());
			//alert($(this).find("a INS").after().html());
			//alert(e+";"+e.target.currentTarget+":"+e.currentTarget.innerHTML);
		    //alert(e+";"+e.target+":"+e.target.innerHTML);
		 })
		 /*
		 $(".jstree-closed").live("click", function(e) {
				alert("关闭"+this.id);
			 })
			 
			 
		$(".jstree-open").live("click", function(e) {
				alert("张开"+this.id);
			 })	
		 */

	});
	function verify(id,resion)
	{
		document.getElementById("vlabel").innerHTML=resion;
		$("#logoutid").val(id);
		$('#verify-postulant').dialog('open');
		
	}
	function loadJstree()
	{
		$("#kkk").jstree({ 
			"themes" : {  

	        "theme" : "default",  

	        "dots" : true,  

	        "icons" : true 
	    	}, 
			"json_data" : {
	        "ajax" : {
	            "url" : "/zyz/app/admin/reg/treedataAllSubOrg.action?orgId="+$("#currentOrgId").val()
	        }
	    },
		"plugins" : [ "themes", "json_data" ]
		});
	
	}
	function loadJstree1()
	{
		$("#kkk101").jstree({ 
			"themes" : {  

	        "theme" : "default",  

	        "dots" : true,  

	        "icons" : true 
	    	}, 
			"json_data" : {
	        "ajax" : {
	            "url" : "/zyz/app/admin/reg/treedataAllSubOrg.action?orgId="+$("#currentOrgId").val()
	        }
	    },
		"plugins" : [ "themes", "json_data" ]
		});
	
	}
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
		<input type="hidden" name="currentOrgId" id="currentOrgId" value="${currentOrgId }"/>
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
			        		<td height="31"><div class="titlebt">志愿者管理</div></td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;志愿者列表</td>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;志愿者列表</td> 
                									<td class="left_bt2" align="right">
                									<button id="searchp">查询</button>&nbsp;&nbsp;
                									<button id="createactivity">增加志愿者</button>&nbsp;&nbsp;
                									
                									<button id="imppostulant">导入志愿者</button>&nbsp;&nbsp;
                									<button id="dels">删除</button>&nbsp;&nbsp;
                									<button id="download">模板下载</button>&nbsp;&nbsp;
                									<a href="../log/loglist.action">日志</a>&nbsp;&nbsp;
                									<a href="showpostulanthis.action">历史记录</a>&nbsp;&nbsp;
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
														
														<th width="10%" class="left_txt2">志愿者姓名</th>
														
														<th width="8%" class="left_txt2">手机号码</th>
														<th width="12%" class="left_txt2">EMAIL</th>
														<th width="5%" class="left_txt2">性别</th>
														
														<th width="15%" class="left_txt2">证件号码</th>
														<th width="15%" class="left_txt2">服务意向</th>
														
														<th width="8%" class="left_txt2">审核状态</th>
														<th width="15%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="postulant" items="${postulantList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
														<tr height="10" bgcolor="#f2f2f2" id="${active.actid}" class="child-of-${active.actid}">
														
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0}">
														<tr height="10" id="${active.actid}" class="child-of-${active.actid}">
													</c:if>
													<td align="center"><input type="checkbox" class="postulant" name="postulant${postulant.id }" value="${postulant.id }"/></td>
													
													<td align="center">${postulant.name }</td>
													
													<td align="center">${postulant.dn }</td>
													<td align="center">&nbsp;${postulant.email }</td>
													<td align="center">&nbsp;
													<c:if test="${(postulant.sex) ==1 }">
														男
													</c:if>
													<c:if test="${(postulant.sex) ==2 }">
														女
													</c:if>
													</td>
													<td align="center">${postulant.credcode }</td>
													<td align="center">&nbsp;
															<c:forEach items="${ntfwyx}" var="current">
											        			<c:if test="${postulant.intention==current.paramId}">
												          			${current.paramKey}
												          		</c:if>
												          		
											      			</c:forEach>
													</td>
													
													<td align="center">
													<c:if test="${(postulant.isverify) ==1 }">
														正常
													</c:if>
													<c:if test="${(postulant.isverify) ==2 }">
														注册待审核
													</c:if>
													<c:if test="${(postulant.isverify) ==3 }">
														注册审核不通过
													</c:if>
													<c:if test="${(postulant.isverify) ==4 }">
														注销待审核
													</c:if>
													<c:if test="${(postulant.isverify) ==5 }">
														注销审核不通过
													</c:if>
													</td>
													<td align="center">
	<c:if test="${(postulant.isverify) ==4 || (postulant.isverify) ==5 }">
		<a href="javascript:void(0)" onclick="verify('${postulant.id}','${postulant.outresion}')" id="verify">审批</a>
	</c:if>												
	<a href="edit.action?uid=${postulant.id}">修改</a>&nbsp;												
	<a href="view.action?uid=${postulant.id}">查看</a>&nbsp;
	<!-- 
	<c:if test="${(postulant.isverify) ==0 }">
		<a href="verify.action?uid=${postulant.id}&verify=1&page=${page}">审批</a>&nbsp;
	</c:if>
	<c:if test="${(postulant.isverify) ==1 }">
		<a href="verify.action?uid=${postulant.id}&verify=2&page=${page}">驳回</a>&nbsp;
	</c:if>
	<c:if test="${(postulant.isverify) ==2 }">
		<a href="verify.action?uid=${postulant.id}&verify=1&page=${page}">审批</a>&nbsp;
	</c:if>
	 -->
	<a href="delete.action?uid=<c:url value="${postulant.id}"/>&page=${page}" class="class_del" id="del" name="${postulant.name}">删除</a>&nbsp;
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
		<div id="form-createactivity" title="增加志愿者" style="width:100px;height:150px;display: none">
			<form action="savePostulant.action" name="insertForm" method="post">
			<label>姓名:</label>
					<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" /><font color="red">*</font><br>
			<label>密码:</label>
					<input type="text" value="123" name="password" id="password" class="text ui-widget-content ui-corner-all" /><br>
			<label>手机:</label>
					<input type="text" name="dn" id="dn" class="text ui-widget-content ui-corner-all" /><font color="red">*</font><br>
			<label>EMAIL:</label>
					<input type="text" name="email" id="email" class="text ui-widget-content ui-corner-all" /><br>
			<label>性别:</label>
					<input type="radio" checked="checked" value="1" name="sex">男<input type="radio" value="2" name="sex">女<br>
			<label>生日:</label>
					<input type="text" name="birthday1" onfocus="WdatePicker();" class="text ui-widget-content ui-corner-all" /><br>
			<label>民族:</label>
					<select name="nation">
          				<c:forEach items="${mz}" var="current">
       						<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
     		  				</c:forEach>
					</select><font color="#FF0000">*</font>
					<br>
			<label>学历:</label>
					<select name="education">
			          	<option value="" label=""></option>
			          	<c:forEach items="${xl}" var="current">
		        			<option value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
		      			</c:forEach>
			          </select>
					<br>
			<label>职业:</label>
					<select name="profession">
					<option value="" label=""></option>
	          		<c:forEach items="${zy}" var="current">
        				<option value="<c:out value="${current.paramValue}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
					</c:forEach>
	        		 </select>
					<br>
			<label>所属社区:</label>
					<input type="hidden" name="communityid" id="communityid" class="text ui-widget-content ui-corner-all" />
					<input type="hidden" name="communityname" id="communityname101" class="text ui-widget-content ui-corner-all" />
					<input type="text" name="communityname11" disabled="disabled" id="communityname" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
					<a href="javascript:void(0)" id="kkk1">选择</a>&nbsp;&nbsp;
					<br>
			<label>服务意向:</label><font color="red">*</font><br>
				<!--  
				<c:forEach items="${fwyx}" var="current">
	          		<input type="radio" onclick="checkradio(${current.paramId})" value="<c:out value="${current.paramValue}"/>"  name="intention"><c:out value="${current.paramKey}"/>
	          		<input type="radio" title="${current.paramDesc}" id="<c:out value="${current.paramId}"/>" value="<c:out value="${current.paramValue}"/>" name="orgid"><c:out value="${current.paramDesc}"/><br>
				</c:forEach>
				-->
				<c:forEach items="${zyzntfwyx}" var="current">
	          		<input type="radio" value="<c:out value="${current.paramValue}"/>" name="intention" id="v<c:out value="${current.paramValue}"/>"  onclick="setintentionid(this.value,this.id)"><c:out value="${current.paramKey}"/>
	          		<input type="radio" disabled value="<c:out value="${current.paramValue}"/>" id="i<c:out value="${current.paramValue}"/>" name="inte" alt="<c:out value="${current.paramDesc}"/>" onclick=setvolunorgid(this.value,this.alt)><c:out value="${current.paramDesc}"/><br>
				</c:forEach>
				
				<div id="jnlabel" style="display: none">
					<label>技能:</label><br>
					<c:forEach items="${jn}" var="current">
	          		<input type="checkbox" value="<c:out value="${current.paramId}"/>" name="jn1""><c:out value="${current.paramKey}"/>
	          		</c:forEach>
				</div>
				<br>
				
				<input type="hidden" name="orgname" id="orgname0" />
				<!-- 
			<label>机构ID:</label>
					<input type="text" name="volunorgid" id="volunorgid" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
					<a href="javascript:void(0)" id="kkk1">选择</a>&nbsp;&nbsp;
					<br>
			<label>机构名称:</label>
					<input type="text" name="volunorgname" id="volunorgname" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
					<br> -->
			<label>证件类型:</label>
					<select name="credtype">
						<option value="" label=""></option>
			          	<c:forEach items="${zjlx}" var="current">
		        			<option value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
		      			</c:forEach>
					</select><font color="red">*</font><br>
			<label>证件号码:</label>
					<input type="text" name="credcode" id="credcode" class="text ui-widget-content ui-corner-all" /><font color="red">*</font><br>
			<label>电话:</label>
					<input type="text" name="phone" id="phone" class="text ui-widget-content ui-corner-all" /><br>
			<label>地址:</label>
					<input type="text" name="address" id="address" class="text ui-widget-content ui-corner-all" /><br>
			<label>爱好特长:</label>
					<textarea name="hobby" id="hobby" cols="50" rows="2" > </textarea><br>
			</form>
		</div>
		
		<div id="search-postulant" title="查询" style="width:100px;height:150px;display: none">
			<form action="showpostulant.action" name="searchForm" method="post">
			<label>登录名:</label>
					<input type="text" name="username1" id="username1" value="${s_username }" class="text ui-widget-content ui-corner-all" /><br>
			<label>姓     名:</label>
					<input type="text" name="name1" id="name1" value="${s_name }" class="text ui-widget-content ui-corner-all" /><br>
			<label>身份证号:</label>
					<input type="text" name="credcode" id="credcode" value="${credcode }" class="text ui-widget-content ui-corner-all" /><br>
			<label>状         态:</label>
					<select name="state">
						<option value="">--请选择--
						<option value="2" >--待审核--
						<option value="1" >--审核通过--
						<option value="3">--审核不通过--
						<option value="4" >--注销待审核--
						<option value="5" >--注销不通过--
					</select>
					<br>
				    <input type="hidden" name="page" id="page" value="1"/><br>
			</form>
		</div>
		
		<div id="verify-postulant" title="审批" style="width:100px;height:150px;display: none">
			<label>注销原因:</label><br>
			<label id="vlabel"></label>
			<input type="hidden" name="logoutid" id="logoutid" />
		</div>
		
		<div id="form-imppostulant" title="导入志愿者" style="width:100px;height:150px;display: none">
			<form name="impform" action="imppostulant.action" method="post" enctype="multipart/form-data">
			<label>所属社区:</label>
					<input type="text" name="communityid" id="communityid1" class="text ui-widget-content ui-corner-all" /><font color="red">*</font><br>
					社区名称:<input type="text" name="communityname" id="communityname1" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
					<a href="javascript:void(0)" id="kkk2">选择</a>&nbsp;&nbsp;
					<br>
			<label>服务意向:</label><font color="red">*</font><br>
				<c:forEach items="${fwyx}" var="current">
	          		<input type="radio" onclick="checkradio1(${current.paramValue})" value="<c:out value="${current.paramValue}"/>"  name="intention"><c:out value="${current.paramKey}"/>
	          		<input type="radio" title="${current.paramDesc}" id="_<c:out value="${current.paramValue}"/>" value="<c:out value="${current.paramValue}"/>" name="orgid"><c:out value="${current.paramDesc}"/><br>
				</c:forEach>
				
				<div id="jnlabel1" style="display: none">
					<label>技能:</label><br>
					<c:forEach items="${jn}" var="current">
	          		<input type="checkbox" value="<c:out value="${current.paramId}"/>" name="jn2""><c:out value="${current.paramKey}"/>
	          		</c:forEach>
				</div>
				<br>
				<script type="text/javascript">
				//选择服务意向
				function setintentionid(intentionid,id)
				{
					//如果是民政局管的"社区志愿服务" 加技能
					if(intentionid==4)
					{
						$("#jnlabel").show();
					}
					else
					{
						$("#jnlabel").hide();
						$('input[name="jn1"]').attr("checked",false);
					}
					
					//$("#intentionidid").val(intentionid);
					$("#i"+id.substring(1,id.length)).attr("checked",true);
					$("#i"+id.substring(1,id.length)).click();

					
				}

				//选择服务意向后自动选择事件
				function setvolunorgid(volunorgid,orgname)
				{
					document.getElementById("orgname0").value=orgname;
				}
					function checkradio1(id)
					{
						document.getElementById("_"+id).checked='checked';
						document.getElementById("orgname1").value=document.getElementById("_"+id).title;
						if(id==4)
						{
							$("#jnlabel1").show();
						}
						else
						{
							document.getElementById("jnlabel1").style.display='none';
							$('input[name="jn2"]').attr("checked",false);
						}
					}
				</script>
				<input type="hidden" name="orgname" id="orgname1" />
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
		<div id="kkk" title="选择组织" style="display: none"></div>
		<div id="kkk101" title="选择组织" style="display: none"></div>
		
		
		


	
	
	</body>
</html>