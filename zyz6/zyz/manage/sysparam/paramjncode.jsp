<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>系统参数</title>
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
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
		<script src="<c:url value="/javascript/tree/MzTreeView12-pack.js"/>" type="text/javascript"></script>

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
		<script type="text/javascript">
		$(document)
		.ready(function() {
			// 添加系统参数
				$("#dialog-addSysparam").dialog( {
					autoOpen : false,
					height : 350,
					width : 400,
					modal : true,
					buttons : {
						'确认' : function() {

						if($("#paramcodeId").val()=='')
						{
							alert('请填写系统编码!');
							return;
						}
						if($("#paramkeyId").val()=='')
						{
							alert('请填写系统参数键!');
							return;
						}
						if($("#paramvalueId").val()=='')
						{
							alert('请填写系统参数值!');
							return;
						}
						var num =  /^(\d+)$/;
						if(!num.test($("#paramvalueId").val()))
						{
							alert('积分必须是数字!');
							return;
						}
						
						$.ajax( {
							type : "POST",
							url : "createjn.action",
							data : {
							paramcode 	: 	$("#paramcodeId").val(),
							paramkey 	: 	$("#paramkeyId").val(),
							paramvalue 	:	$("#paramvalueId").val(),
							paramdesc 	: 	$("#paramdescId").val()
							
							},
							success : function(data) {
								if (data == 1)
									location.reload();
								else
									alert("更新失败!");
							}
						});
						$(this).dialog('close');
							
							
						},
						'退出' : function() {
							
							$("#paramkeyId").val('');
							$("#paramvalueId").val('');
							$("#paramdescId").val('');
							
							$(this).dialog('close');
						}
					}
				});
				
				// 查询系统参数
				$("#dialog-editSysparam").dialog( {
					autoOpen : false,
					height : 200,
					width : 350,
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
		
				//更改状态
			    $("#dialog-auditSysparam").dialog({
					autoOpen: false,
					height: 200,
					width: 350,
					modal: true,
					buttons: {
							'有效': function() {
			    	$.ajax({
						   type: "POST",
						   url: "audit.action",
						   data: { paramid:$("#paramid").val(),paramstate: 1 },
						   success: function(data){
							   if(data==1)
								   location.reload();
							   else
								   alert("更新失败!");
						   }
						 	});
								$(this).dialog('close');
						},
						'无效': function() {
							$.ajax({
								   type: "POST",
								   url: "audit.action",
								   data: { paramid:$("#paramid").val(),paramstate: 0 },
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

				$("#createSysparam").click(function() {
					$('#dialog-addSysparam').dialog('open');
				});
				
				$(".class_audit").click(function(){
					$("#paramid").val($(this).attr("paramid"));
					$('#dialog-auditSysparam').dialog('open');
				});
				
				$(".class_del").click(function(){
					return confirm("确认对该系统参数["+this.name+"]进行删除操作?");
				});
				
				$("#searchSysparam").click(function(){
					$('#dialog-editSysparam').dialog('open');
				});
						

				$(".class_tree").click(function() {
					alert(this.value());
					$('#kkk').dialog('open');
				});
		});
		/** 手工匹配 */
		function manualSel(pid,order){
			$("#paramid1").val(pid);
			$("#paramorder1").val(order);
			$('#kkk').dialog('open');
		}
		function edit(paramid)
		{
			$.ajax({
				   type: "POST",
				   url: "getSysParam.action",
				   data: { "paramid": paramid },
				   success: function(data){
					  alert(data);
					  
					  alert(data.key);
				   }
				 });
			$('#dialog-editSysparam').dialog('open');
		}
		</script>
	</head>
	<body>
		<input type="hidden" id="paramid">
		<input type="hidden" id="dialogtitle">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">系统参数</div></td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;系统参数列表</td>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;系统参数列表</td> 
                									<td class="left_bt2" align="right">
                									<a href="javascript:void(0)" id="searchSysparam">查询</a>&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="javascript:void(0)" id="createSysparam">新建参数</a>&nbsp;&nbsp;&nbsp;&nbsp;
													
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
														<th width="10%" class="left_txt2">序号</th>
														<th width="10%" class="left_txt2">编码</th>
														<th width="20%" class="left_txt2">键</th>
														<th width="10%" class="left_txt2">积分</th>
														
														<th width="10%" class="left_txt2">状态</th>
														<th width="10%" class="left_txt2">描述</th>
														<th width="20%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="sysparam" items="${sysparamList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
														<tr height="10" bgcolor="#f2f2f2" id="${sysparam.paramid}" class="child-of-${sysparam.paramid}">
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0}">
														<tr height="10" id="${sysparam.paramid}" class="child-of-${sysparam.paramid}">
													</c:if>
													<td align="center">${status.count }</td>
													<td align="center">${sysparam.paramcode }</td>
													<td align="center">${sysparam.paramkey }</td>
													<td align="center">${sysparam.paramvalue }</td>
													
													<td align="center">
													<c:if test="${(sysparam.paramstate) ==0 }">
														待审核
													</c:if>
													<c:if test="${(sysparam.paramstate) ==1 }">
														通过
													</c:if>	
													<c:if test="${(sysparam.paramstate) ==2 }">
														不通过
													</c:if>
													</td>
													<td align="center">&nbsp;${sysparam.paramdesc }</td>
													<td align="center">
	<a href="javascript:void(0)" class="class_audit" id="audit" paramid="${sysparam.paramid}">审核</a>&nbsp;&nbsp;&nbsp;&nbsp;													
	<a href="jndelete.action?paramid=<c:url value="${sysparam.paramid}"/>" class="class_del" id="del" name="${sysparam.paramkey}">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="getSysParam.action?paramid=${sysparam.paramid}" name="${sysparam.paramid}">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;
													</td>
														</tr>
													</c:forEach>
													
													<tr><td colspan="6" align="right"></td></tr>
												</tbody>
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
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<div id="dialog-addSysparam" title="创建技能系统参数" style="width:100px;height:150px">
			<form>
			<label>系统编码:</label>
			<input type="text"  	id="paramcodeId" value="JN"	class="text ui-widget-content ui-corner-all" /><br>
			<label>系统参数键:</label>
			<input type="text"   	id="paramkeyId"		class="text ui-widget-content ui-corner-all" /><br>
			<label>技能积分:</label>
			<input type="text"  id="paramvalueId" class="text ui-widget-content ui-corner-all" /><br>
			<label>系统参数描述:</label>
			<input type="text"  	id="paramdescId"	class="text ui-widget-content ui-corner-all" /><br>
			</form>
		</div>
		
		<div id="dialog-editSysparam" title="查询" style="width:100px;height:150px">
			<form name="searchForm" action="showjnsysparam.action" method="post">
			
			<label>系统参数键:</label>
			<input type="text" name="paramkey" value="${paramkey }" class="text ui-widget-content ui-corner-all" /><br>
			<label>系统参数值:</label>
			<input type="text" name="paramvalue" value="${paramvalue }" class="text ui-widget-content ui-corner-all" /><br>
			
			</form>
		</div>
		
		
		
		<div id="dialog-auditSysparam" title="审核">
			
			
		</div>		
		
	</body>
</html>