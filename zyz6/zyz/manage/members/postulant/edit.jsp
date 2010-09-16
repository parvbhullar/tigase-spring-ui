<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	checkradio($("#intentionid").val());
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
		return confirm("确认要删除志愿者[" + this.name + "]?");
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
								'关 闭' : function() {
									$(this).dialog('close');
								}
							}
					});
		
		
		
		
		
		$("#kkk1").click(function() {
			$('#kkk').dialog('open');
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
function validate()
{
	var regmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	var regdn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	var idcard =  /^\d{15}(\d{2}[X0-9])?$/;
	
						
	var phone =  /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/;
	
	if(document.insertForm.name.value=='')
	{
		alert('请填写姓名！');
		document.insertForm.name.focus();
		return false;
	}
	if(document.insertForm.dn.value=='')
	{
		alert('请填写手机号！');
		document.insertForm.dn.focus();
		return false;
	}
	if(!regdn.test(document.insertForm.dn.value)){
		alert("手机号码格式错误!");
		document.insertForm.dn.focus();
   		return false;
	}
	if(document.insertForm.email.value!='')
	{
		if(!regmail.test(document.insertForm.email.value)){
			alert("邮箱格式错误!");
			document.insertForm.email.focus();
			return false;
		}
	}
	if(document.insertForm.communityname.value=='')
	{
		alert('请选择志愿者所属社区!');
		return false;
	}
	if(document.insertForm.orgname.value=='')
	{
		alert('请选择服务意向和组织结构');
		return false;
	}
	if(document.insertForm.credtype.value=='')
	{
		alert('请选择身份类型！');
		return false;
	}
	if(document.insertForm.credcode.value=='')
	{
		alert('请填写证件号码！');
		document.insertForm.credcode.focus();
		return false;
	}
	if(!idcard.test(document.insertForm.credcode.value)){
		alert("身份证格式错误!");
		document.insertForm.credcode.focus();
		return false;
	}
	if(document.insertForm.phone.value!='')
	{
		if(!phone.test(document.insertForm.phone.value)){
			alert("电话号码格式错误!");
			document.insertForm.phone.focus();
			return false;
		}
	}
	if(document.insertForm.dn.value!=document.insertForm.dn1.value||document.insertForm.credcode.value!=document.insertForm.credcode1.value)
	{
		
		//唯一性判断
		$.ajax( {
				type : "post",
				url : "validate.action",
				data : {
					dn : document.insertForm.dn.value,
					credcode : document.insertForm.credcode.value
				},
				success : function(data) {
					
					if(data=='1'&&document.insertForm.dn.value!=document.insertForm.dn1.value)
					{
						alert('手机号重复!');
						return;
					}else if(data=='2'&&document.insertForm.credcode.value!=document.insertForm.credcode1.value)
					{
						alert('身份证号码重复');
						return;
					}else if(data=='3')
					{
						alert('手机号或者身份证重复');
						return;
					}
					else{
						document.insertForm.submit();
					}
				}
			});
	
	}
	else
	{
		document.insertForm.submit();
	}
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
<title></title>
</head>
<body>
			<form action="updatePostulant.action" name="insertForm" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">志愿者信息修改</div></td>  
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
					            					<td class="left_txt">当前位置：志愿者管理&nbsp;&gt;&gt;&nbsp;修改志愿者信息</td>
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
										                <span class="left_txt2">在这里，您可以修改志愿者的详细信息</span><br/>
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
									                		<input type="hidden" name="loginName" value="${postulant.loginName }">
															<input type="text" name="userName1" disabled="disabled" id="userName1" value="${postulant.loginName }" class="text ui-widget-content ui-corner-all" />
									                		<input type="hidden" name="id" value="${postulant.id }">
									                	</td>
									                	<td width="45%" height="30" bgcolor="#f2f2f2" class="left_txt">登录名称</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">姓名：</td>
									                	<td>&nbsp;</td>
									                	<td height="30">
									                		<input type="text" name="name" value="${postulant.name }" id="name" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
									                	</td>
									                	<td height="30" class="left_txt">姓名</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">电话：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<input type="text" name="phone" id="phone" value="${postulant.phone }" class="text ui-widget-content ui-corner-all" />
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">电话</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">密码： </td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<input type="text" name="password" id="password" value="${postulant.password }" class="text ui-widget-content ui-corner-all" />
									                	</td>
									                	<td height="30" class="left_txt">密码</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">手机号码：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<input type="text" name="dn" id="dn" value="${postulant.dn }" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
									                		<input type="hidden" name='dn1' value="${postulant.dn }"/>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">手机号码</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">EMAIL：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<input type="text" name="email" id="email" value="${postulant.email }" class="text ui-widget-content ui-corner-all" />
									                	</td>
									                	<td height="30" class="left_txt">EMAIL</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">性别：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<c:if test="${(postulant.sex) ==1 }">
																<input type="radio" value="1" checked="checked" name="sex">男
																<input type="radio" value="2" name="sex">女
															</c:if>
															<c:if test="${(postulant.sex) ==2 }">
																<input type="radio" value="1" name="sex">男
																<input type="radio" value="2" checked="checked"  name="sex">女
															</c:if>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">性别</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">生日：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<input type="text" name="birthday1" value="${postulant.birthday }" onfocus="WdatePicker();" class="text ui-widget-content ui-corner-all" />
									                		
									                	</td>
									                	<td height="30" class="left_txt">生日</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">学历：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<select name="education">
													          	<option value="" label=""></option>
													          	<c:forEach items="${xl}" var="current">
												        			<c:if test="${postulant.education==current.paramValue}">
													          			<option selected="selected" value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
													          		<c:if test="${postulant.education!=current.paramValue}">
													          			<option value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
												      			</c:forEach>
													          </select>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">学历</td>
									              	</tr>
									              	
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">职业：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<select name="profession">
													          	<option value="" label=""></option>
													          	<c:forEach items="${zy}" var="current">
												        			<c:if test="${postulant.profession==current.paramValue}">
													          			<option selected="selected" value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
													          		<c:if test="${postulant.profession!=current.paramValue}">
													          			<option value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
												      			</c:forEach>
													          </select>
									                	</td>
									                	<td height="30" class="left_txt">职业</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">所属社区：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<input type="hidden" name="communityid" id="communityid" value="${postulant.communityid }" class="text ui-widget-content ui-corner-all" /><br>
									                		<input type="hidden" name="communityname" id="communityname_1" value="${postulant.communityname }" class="text ui-widget-content ui-corner-all" />
									                	    <input type="text" name="communityname1" id="communityname" disabled="disabled" value="${ postulant.communityname}" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
															<a href="javascript:void(0)" id="kkk1">选择</a>&nbsp;&nbsp;
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">所属社区</td>
									              	</tr>
									              	
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">服务意向：<font color="red">*</font></td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<c:forEach items="${fwyx}" var="current">
									                			<c:if test="${postulant.intention==current.paramValue}">
									                				<input type="radio" checked="checked" onclick="checkradio(${current.paramValue})" value="<c:out value="${current.paramValue}"/>"  name="intention"><c:out value="${current.paramKey}"/>
												          			<input type="radio" checked="checked" title="${current.paramDesc}" id="org_<c:out value="${current.paramValue}"/>" value="<c:out value="${current.paramValue}"/>" name="orgid" disabled><c:out value="${current.paramDesc}"/><br>
									                			</c:if>
												          		<c:if test="${postulant.intention!=current.paramValue}">
									                				<input type="radio" onclick="checkradio(${current.paramValue})" value="<c:out value="${current.paramValue}"/>"  name="intention"><c:out value="${current.paramKey}"/>
												          			<input type="radio" title="${current.paramDesc}" id="org_<c:out value="${current.paramValue}"/>" value="<c:out value="${current.paramValue}"/>" name="orgid" disabled><c:out value="${current.paramDesc}"/><br>
									                			</c:if>
												          		
															</c:forEach>
									                		
															<br>
															<c:if test="${postulant.intention==4}">
									                				<div id="jnlabel" style="display:">
									                		</c:if>
									                		<c:if test="${postulant.intention!=4}">
									                				<div id="jnlabel" style="display:none">
									                		</c:if>
																<label>技能:</label><br>
																<input type="hidden" id="jnid" value="${postulant.jn }">
																<input type="hidden" id="intentionid" value="${postulant.intention }">
																<c:forEach items="${jn}" var="current">
																	<input type="checkbox" id="jn<c:out value='${current.paramId}'/>" value="<c:out value="${current.paramId}"/>" name="jn1""><c:out value="${current.paramKey}"/>
												          		</c:forEach>
															</div>
															<script type="text/javascript">
																function checkradio(id)
																{
																	
																	document.getElementById("org_"+id).checked='checked';
																	document.getElementById("orgname").value=document.getElementById("org_"+id).title;
																	
																	if(id==4)
																	{
																		$("#jnlabel").show();
																		var arrjn=$("#jnid").val().split(",");
																		for(var i=0;i<arrjn.length;i++)
																		{
																			$("#jn"+(arrjn[i])).attr("checked",true);
																		}
																	}
																	else
																	{
																		document.getElementById("jnlabel").style.display='none';
																		
																		$('input[name="jn1"]').attr("checked",false);
																	}
																}
															</script>
									                		<input type="hidden" name="orgname" id="orgname" value="${postulant.orgname}"/>
									                	</td>
									                	<td height="30" class="left_txt">服务意向</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">民族：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<select name="nation">
																
													          	<c:forEach items="${mz}" var="current">
													          		<c:if test="${postulant.nation==current.paramValue}">
													          			<option selected="selected" value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
													          		<c:if test="${postulant.nation!=current.paramValue}">
													          			<option value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
												        			
												      			</c:forEach>
															</select>
															<font color="red">*</font>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">民族</td>
									              	</tr>
									              	
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">证件类型：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<select name="credtype">
																<option value="" label=""></option>
													          	<c:forEach items="${zjlx}" var="current">
													          		<c:if test="${postulant.credtype==current.paramValue}">
													          			<option selected="selected" value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
													          		<c:if test="${postulant.credtype!=current.paramValue}">
													          			<option value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
												        			
												      			</c:forEach>
															</select>
															<font color="red">*</font>
									                	</td>
									                	<td height="30" class="left_txt">证件类型</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">证件号码：</td>
									                	<td bgcolor="#f2f2f2">&nbsp;</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt2">
									                		<input type="text" name="credcode" value="${postulant.credcode }" id="credcode" class="text ui-widget-content ui-corner-all" /><font color="red">*</font>
									                		<input type="hidden" name='credcode1' value="${postulant.credcode }"/>
									                	</td>
									                	<td height="30" bgcolor="#f2f2f2" class="left_txt">证件号码</td>
									              	</tr>
									              	<tr>
									                	<td height="30" align="right" class="left_txt2">兴趣爱好：</td>
									                	<td>&nbsp;</td>
									                	<td height="30" class="left_txt2">
									                		<textarea name="hobby" id="hobby" cols="50" rows="2" >${postulant.hobby }</textarea>
									                	</td>
									                	<td height="30" class="left_txt">兴趣爱好</td>
									              	</tr>

              										<tr>
                										<td height="17" colspan="4" align="right" >&nbsp;</td>
              										</tr>

            									</table>
            									<div align="right">
            										<input type="button" onclick="return validate()" value="提交">
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
			
			</form>
<div id="kkk" title="选择组织" style="display:none"></div>
		
		
		
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
	
	$("#communityid").val(selectOrgId);
	$("#communityname").val(selTree.currentNode.T);
	$("#communityname_1").val(selTree.currentNode.T);
	$("#kkk").dialog('close');
}
	</script>
</body>
</html>