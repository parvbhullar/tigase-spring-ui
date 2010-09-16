<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>类型管理</title>
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
		
		<script type="text/javascript">
			$(document).ready(function() {
				//树形表格
			    $("#orgtypetabletree").treeTable({
			      expandable: false
			    });

				//失效、生效、删除的确认操作
			    $(".online").click(function(){
					return confirm("确认对该组织类型["+this.name+"]进行生效操作?");
				});
				$(".offline").click(function(){
					return confirm("确认对该组织类型["+this.name+"]进行失效操作?");
				});
				$(".delorgtype").click(function(){
					return confirm("确认删除组织类型[:"+this.name+"]?");
				});
			    
			    var create_topOrgTypeName = $("#create_topOrgTypeName"),
			    	create_orgTypeParentId = $("#create_orgTypeParentId"),
			    	create_orgTypeParentName = $("#create_orgTypeParentName"),
			    	create_orgTypeName = $("#create_orgTypeName"),
			    	edit_orgTypeId = $("#edit_orgTypeId"),
			    	edit_orgTypeName = $("#edit_orgTypeName"),
			        allFields = $([]).add(create_topOrgTypeName).add(create_orgTypeParentId).add(create_orgTypeParentName).add(create_orgTypeName).add(edit_orgTypeId).add(edit_orgTypeName),
			        tips = $(".validateTips");

				function updateTips(t) {
					tips.text(t).addClass('ui-state-highlight');
					setTimeout(function() {tips.removeClass('ui-state-highlight', 1500);}, 500);
				}

				function checkLength(o,n,min,max) {
					
					if ( jQuery.trim(o.val()).length > max || jQuery.trim(o.val()).length < min ) {
						o.addClass('ui-state-error');
						updateTips("\"" + n + "\" 的长度必须在 "+2+" 到 "+20+"之间.");
						return false;
					} else {
						return true;
					}
				}

				function checkRegexp(o,regexp,n) {
					if ( !( regexp.test( o.val() ) ) ) {
						o.addClass('ui-state-error');
						updateTips(n);
						return false;
					} else {
						return true;
					}
				}
				
				//创建顶级组织类型表单
			    $("#form-createtoporgtype").dialog({
					autoOpen: false,
					height: 200,
					width: 350,
					modal: true,
					buttons: {
							'创建': function() {
							var bValid = true;
							allFields.removeClass('ui-state-error');
							bValid = bValid && checkLength(create_topOrgTypeName,"组织类型名称",1,20);						
							if (bValid) {
								//alert($('#create_topOrgTypeName').val());
								$.post("addtoporgtype.action", { orgTypeName: create_topOrgTypeName.val(),orgTypeCategory:$("#create_topOrgTypeCategory").val()},
									function(data){
										if(data=="success"){
											alert("创建成功!");
											location.reload(); 
										}else if(data=="faile"){
											alert("创建失败!");
										}else{
											alert("创建失败!");
										}
									}
								);
								$(this).dialog('close');
							}
						},
						'退出': function() {
							$(this).dialog('close');
						}
					},
					close: function() {
						allFields.val('').removeClass('ui-state-error');
					}
				});
			    $('#createtoporgtype').click(function() {
					$('#form-createtoporgtype').dialog('open');
				});

			  	//创建组织类型表单
			    $("#form-createorgtype").dialog({
					autoOpen: false,
					height: 200,
					width: 350,
					modal: true,
					buttons: {
							'创建': function() {
							var bValid = true;
							allFields.removeClass('ui-state-error');
							bValid = bValid && checkLength(create_orgTypeName,"组织类型名称",1,20);						
							if (bValid) {
								//alert(create_orgTypeName.val()+","+create_orgTypeParentId.val());
								$.post("addorgtype.action", { orgTypeName: create_orgTypeName.val(),orgTypeParentId:create_orgTypeParentId.val(),orgTypeCategory:$("#create_orgTypeCategory").val()},
									function(data){
										if(data=="success"){
											alert("创建成功!");
											location.reload(); 
										}else if(data=="faile"){
											alert("创建失败!");
										}else{
											alert("创建失败!");
										}
									}
								);
								$(this).dialog('close');
							}
						},
						'退出': function() {
							$(this).dialog('close');
						}
					},
					close: function() {
						allFields.val('').removeClass('ui-state-error');
					}
				});

				$(".class_addchildorgtype").click(function(){
					//alert($(this).attr("orgtypeparentid")+","+$(this).attr("orgtypeparentname"));
					$("#create_orgTypeParentId").val($(this).attr("orgtypeparentid"));
					$("#create_orgTypeParentName").val($(this).attr("orgtypeparentname"));
					$("#create_orgTypeCategory").val($(this).attr("orgtypecategory"));
					$('#form-createorgtype').dialog('open');
				});

				//修改组织类型表单
			    $("#form-editorgtype").dialog({
					autoOpen: false,
					height: 200,
					width: 350,
					modal: true,
					buttons: {
							'修改': function() {
							var bValid = true;
							allFields.removeClass('ui-state-error');
							bValid = bValid && checkLength(edit_orgTypeName,"组织类型名称",1,20);						
							if (bValid) {
								//alert(edit_orgTypeId.val()+","+edit_orgTypeName.val());
								$.post("editorgtype.action", { orgTypeId: edit_orgTypeId.val(),orgTypeName:edit_orgTypeName.val()},
									function(data){
										if(data=="success"){
											alert("修改成功!");
											location.reload(); 
										}else if(data=="faile"){
											alert("修改失败!");
										}else{
											alert("修改失败!");
										}
									}
								);
								$(this).dialog('close');
							}
						},
						'退出': function() {
							$(this).dialog('close');
						}
					},
					close: function() {
						allFields.val('').removeClass('ui-state-error');
					}
				});

				$(".class_eidtorgtype").click(function(){
					//alert($(this).attr("orgtypeid"));
					$("#edit_orgTypeId").val($(this).attr("orgtypeid"));
					$('#form-editorgtype').dialog('open');
				});
			});
		</script>
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
			        		<td height="31"><div class="titlebt">类型管理</div></td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;机构类型列表</td>
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
										                <span class="left_txt2">在这里，您可以创建、修改、删除以及改变组织机构类型对象</span><br>
										                <span class="left_txt3">注意!</span>
										                <span class="left_txt3">机构类型的删除为物理删除,一旦删除数据将不可恢复.</span>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;机构类型列表</td> 
                									<td class="left_bt2" align="right">
														<!-- <a href="<c:url value="addtoporgtype.action"/>">创建顶层机构类型</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
														<a href="javascript:void(0)" id="createtoporgtype">创建顶层机构类型</a>&nbsp;&nbsp;&nbsp;&nbsp;
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
														<th width="20%" class="left_txt2">机构类型名称</th>
														<th width="10%" class="left_txt2">机构类型状态</th>
														<th width="70%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="orgType" items="${orgTypeList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
													<c:if test="${orgType.level >1 }">
														<tr height="10" bgcolor="#f2f2f2" id="${orgType.orgTypeId}" class="child-of-${orgType.orgTypeParentId}">
													</c:if>
													<c:if test="${orgType.level ==1 }">
														<tr height="10" bgcolor="#f2f2f2" id="${orgType.orgTypeId}">
													</c:if>
													</c:if>
													
													<c:if test="${(status.count-1) % 2 !=0}">
													<c:if test="${orgType.level >1 }">
														<tr height="10" id="${orgType.orgTypeId}" class="child-of-${orgType.orgTypeParentId}">
													</c:if>
													<c:if test="${orgType.level ==1 }">
														<tr height="10" id="${orgType.orgTypeId}">
													</c:if>
													</c:if>
														<td align="left">${orgType.orgTypeName }</td>
														<c:if test="${orgType.orgTypeState==0}">
														<td align="center">无效</td>
														<td align="center">
															<a href="<c:url value="${orgType.orgTypeId}/1/changeorgtype.action"/>" name="${orgType.orgTypeName}" class="online">生效</a>&nbsp;&nbsp;&nbsp;&nbsp;
															<!-- <a href="<c:url value="${orgType.orgTypeId}/addorgtype.action"/>" class="addshop">增加子机构</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
															<!-- <a href="javascript:void(0)" id="addorgtype"  orgtypeparentid="${orgType.orgTypeId}" orgtypename="${orgType.orgTypeName }">增加子机构类型</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
															<a href="<c:url value="${orgType.orgTypeId}/delorgtype.action"/>" name="${orgType.orgTypeName}" class="delorgtype">删除机构类型</a>&nbsp;&nbsp;&nbsp;&nbsp;
															<!-- <a href="<c:url value="${orgType.orgTypeId}/editorgtype.action"/>">修改机构</a> -->
															<!-- <a href="javascript:void(0)" id="editorgtype" orgtypeid="${orgType.orgTypeId }">修改机构类型</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
														</td>
														</c:if>
														<c:if test="${orgType.orgTypeState==1}">
														<td align="center">有效</td>
														<td align="center">
															<a href="<c:url value="${orgType.orgTypeId}/0/changeorgtype.action"/>" name="${orgType.orgTypeName}" class="offline">无效</a>&nbsp;&nbsp;&nbsp;&nbsp;
															<!-- <a href="<c:url value="${orgType.orgTypeId}/addorgtype.action"/>" class="addshop">增加子机构</a>&nbsp;&nbsp;&nbsp;&nbsp; -->
															<a href="javascript:void(0)" class="class_addchildorgtype" id="addorgtype" orgtypeparentid="${orgType.orgTypeId}" orgtypeparentname="${orgType.orgTypeName }" orgtypecategory="${orgType.category }">增加子机构类型</a>&nbsp;&nbsp;&nbsp;&nbsp;
															<a href="<c:url value="${orgType.orgTypeId}/delorgtype.action"/>" name="${orgType.orgTypeName}" class="delorgtype">删除机构类型</a>&nbsp;&nbsp;&nbsp;&nbsp;
															<!-- <a href="<c:url value="${orgType.orgTypeId}/editorgtype.action"/>">修改机构</a> -->
															<a href="javascript:void(0)" class="class_eidtorgtype" id="eidtorgtype" orgtypeid="${orgType.orgTypeId }">修改机构类型</a>&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														</c:if>
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
		
		<div id="form-createtoporgtype" title="创建顶级组织类型">
			<p class="validateTips"></p>
			<form>
				<fieldset>
					<label for="name">组织类型名称:</label>
					<input type="text" name="create_topOrgTypeName" id="create_topOrgTypeName" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织类型种类:</label>
					<select name="create_topOrgTypeCategory" id="create_topOrgTypeCategory">
						<option value="0">管理</option>
						<option value="1">服务</option>
					</select>
					<!-- <input type="text" name="create_topOrgTypeName" id="create_topOrgTypeName" class="text ui-widget-content ui-corner-all" /> -->
				</fieldset>
			</form>
		</div>
		
		<div id="form-createorgtype" title="创建组织类型">
			<p class="validateTips"></p>
			<form>
				<fieldset>
					<input type="hidden" name="create_orgTypeParentId" id="create_orgTypeParentId" value=""/>
					<input type="hidden" name="create_orgTypeCategory" id="create_orgTypeCategory" value=""/>
					<label for="name">父组织类型名称:</label>
					<input type="text" name="create_orgTypeParentName" id="create_orgTypeParentName" value="" disabled class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织类型名称:</label>
					<input type="text" name="create_orgTypeName" id="create_orgTypeName" class="text ui-widget-content ui-corner-all" />
				</fieldset>
			</form>
		</div>
		
		<div id="form-editorgtype" title="修改组织类型">
			<p class="validateTips"></p>
			<form>
				<fieldset>
					<input type="hidden" id="edit_orgTypeId" name="edit_orgTypeId" value=""/>
					<label for="name">组织类型名称:</label>
					<input type="text" name="edit_orgTypeName" id="edit_orgTypeName" class="text ui-widget-content ui-corner-all" />
				</fieldset>
			</form>
		</div>
	</body>
</html>