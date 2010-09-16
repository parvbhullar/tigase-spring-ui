<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>机构管理</title>
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
			.liuyang{padding-left:10px;backgroud:red}
		</style>
		<!-- 树形控件 -->
		<link rel="stylesheet" href="/zyz/javascript/jqueryZtree1.11/style/18x18/zTreeStyle.css" type="text/css">
		<!-- 树形表格 -->
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<!-- 对话框 -->
		<link href="/zyz/javascript/jqueryUI-1.8.2/themes/ui-lightness/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<!-- 树形表格 -->
		<script src="<c:url value="/javascript/jquery.treeTable.js"/>" type="text/javascript"></script>
		<!-- 对话框 -->
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.core.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.widget.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.position.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.dialog.min.js"/>" type="text/javascript"></script>
		<!-- 联动下拉框 -->
		<script src="<c:url value="/javascript/jQuery.FillOptions.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jQuery.CascadingSelect.js"/>" type="text/javascript"></script>
		<!-- 树形控件 -->
		<script src="<c:url value="/javascript/jqueryZtree1.11/jquery-ztree-1.11-min.js"/>" type="text/javascript"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				//树形表格
			    $("#orgtypetabletree").treeTable({
			     // expandable: false
			    });
			    
				//var ff='${treeString}';
				//alert(ff);

				//失效、生效、删除的确认操作
			    $(".online").click(function(){
					return confirm("确认对该组织机构["+this.name+"]进行生效操作?");
				});
				$(".offline").click(function(){
					return confirm("确认对该组织机构["+this.name+"]进行失效操作?");
				});
				$(".delorg").click(function(){
					return confirm("确认删除组织机构[:"+this.name+"]?");
				});
			    
			    var create_orgName = $("#create_orgName"),
			    	edit_orgName = $("#edit_orgName"),
			    	/*
			    	create_orgTypeParentId = $("#create_orgTypeParentId"),
			    	create_orgTypeParentName = $("#create_orgTypeParentName"),
			    	create_orgTypeName = $("#create_orgTypeName"),
			    	edit_orgTypeId = $("#edit_orgTypeId"),
			    	edit_orgTypeName = $("#edit_orgTypeName"),
			        allFields = $([]).add(create_topOrgTypeName).add(create_orgTypeParentId).add(create_orgTypeParentName).add(create_orgTypeName).add(edit_orgTypeId).add(edit_orgTypeName),
					*/
			        allFields = $([]).add(create_orgName).add(edit_orgName),
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

			  	//创建组织机构表单
			    $("#form-createorg").dialog({
					autoOpen: false,
					height: 400,
					width: 350,
					modal: true,
					buttons: {
							'创建': function() {
							var bValid = true;
							allFields.removeClass('ui-state-error');
							bValid = bValid && checkLength(create_orgName,"组织机构名称",1,20);						
							if (bValid) {
								//alert($("#create_regAble").val()+","+$("#create_belongOrgId").val());
								$.post("addorg.action", { parentOrgId: $("#create_parentOrgId").val(),
														orgName:$("#create_orgName").val(),
														orgShotrName:$("#create_orgShotrName").val(),
														orgAddress:$("#create_orgAddress").val(),
														orgTel:$("#create_orgTel").val(),
														orgContactor:$("#create_orgContactor").val(),
														orgZipCode:$("#create_orgZipCode").val(),
														orgTypeId:$("#create_orgTypeId").val(),
														orgSubTypeId:$("#create_orgSubTypeId").val(),
														regAble:$("#create_regAble").val(),
														belongOrgId:$("#create_belongOrgId").val(),
														orgLevel:$("#orgLevel").val()
														},
									function(data){
										if(data=="faile"){
											alert("创建失败!");
										}else{
											//alert(data);
											var tmp=data.split("@");
											//alert(tmp[0]+","+tmp[1]);
											if(tmp[0]=="success"){
												alert("创建成功!");
												alert(tmp[1]);
												location.reload(); 
											}else{
												alert("创建失败!");
											}
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
						$("#create_orgSubTypeId").html("");
						//$("#create_belongOrgName").attr("style","display:''");
						//$("#lable_create_belongOrgName").attr("style","display:''");
						allFields.val('').removeClass('ui-state-error');
					}
				});

				$(".class_addchildorg").click(function(){
					//alert($(this).attr("orgtypeparentid")+","+$(this).attr("orgtypeparentname"));
					$("#create_parentOrgId").val($(this).attr("orgparentid"));
					$("#create_parentOrgName").val($(this).attr("orgparentname"));
					$("#orgLevel").val(($(this).attr("orglevel")));
					cfill();
					$('#form-createorg').dialog('open');
				});

				//修改组织机构表单
			    $("#form-editorg").dialog({
					autoOpen: false,
					height: 400,
					width: 350,
					modal: true,
					buttons: { 
							'修改': function() {
							var bValid = true;
							allFields.removeClass('ui-state-error');
							bValid = bValid && checkLength(edit_orgName,"组织机构名称",1,20);						
							if (bValid) {
								//alert(edit_orgTypeId.val()+","+edit_orgTypeName.val());
								$.post("editorg.action", { orgId: $("#edit_orgId").val(),
														orgName:$("#edit_orgName").val(),
														orgShotrName:$("#edit_orgShotrName").val(),
														orgAddress:$("#edit_orgAddress").val(),
														orgTel:$("#edit_orgTel").val(),
														orgContactor:$("#edit_orgContactor").val(),
														orgZipCode:$("#edit_orgZipCode").val()},
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

				$(".class_editorg").click(function(){
					//alert($(this).attr("orgtypeid"));
					$("#edit_orgId").val($(this).attr("orgid"));
					$("#edit_orgName").val($(this).attr("orgName"));
					$("#edit_orgShotrName").val($(this).attr("orgShotrName"));
					$("#edit_orgAddress").val($(this).attr("orgAddress"));
					$("#edit_orgContactor").val($(this).attr("orgContactor"));
					$("#edit_orgZipCode").val($(this).attr("orgZipCode"));
					$('#form-editorg').dialog('open');
				});

				function cfill(){
					$("#create_orgTypeId").FillOptions("getTopOrgType.action",{datatype:"json",textfield:"orgTypeName",valuefiled:"orgTypeId"});
					$("#create_orgTypeId").AddOption("请选择","-1",true,0);
				}

				/*function efill(){
					$("#edit_orgTypeId").FillOptions("getTopOrgType.action",{datatype:"json",textfield:"orgTypeName",valuefiled:"orgTypeId"});
				}*/

				/*$("#create_orgTypeId").change(function(){
						if(this.value=="100"){
							alert(this.value);
							$("#create_belongOrgName").attr("style","display:none");
							$("#lable_create_belongOrgName").attr("style","display:none");
						}else{
							$("#create_belongOrgName").attr("style","display:''");
							$("#lable_create_belongOrgName").attr("style","display:''");
						}
				});*/
				$("#create_orgTypeId").CascadingSelect(
                    $("#create_orgSubTypeId"),
                    "getSubOrgTypeByOrgTypeId.action",
                    {datatype:"json",textfield:"orgTypeName",valuefiled:"orgTypeId",parameter:"orgTypeId"},
                    function(){
                    	//$("#Select2").AddOption("请选择","-1",true,0);
                        //$("#Select3").html("");
                        //$("#Select3").AddOption("无选项","-1",true,0);
                    }
            	);

				/*$("#edit_orgTypeId").CascadingSelect(
	                $("#edit_orgSubTypeId"),
	                 "getSubOrgTypeByOrgTypeId.action",
	                 {datatype:"json",textfield:"orgTypeName",valuefiled:"orgTypeId",parameter:"orgTypeId"},
	                 function(){}
	            );*/

				//组织机构树形
			    /*$("#orgTree").dialog({
					autoOpen: false,
					height: 400,
					width: 350,
					modal: true,
					buttons: {
						'确定': function() {
			    				var nodes = zTree1.getSelectedNodes();
			    				if(!nodes){
				    				alert("请选择组织!");
			    				}
			    				$("#create_belongOrgId").val(nodes[0].id);
			    				$("#create_belongOrgName").val(nodes[0].name);
			    				//alert(nodes[0]+","+nodes[0].name+","+nodes[0].id);
			    				$(this).dialog('close');
						},
						'退出': function() {
							$(this).dialog('close');
						}
					},
					close: function() {
					}
				});
				
	            function zTreeOnClick(event, treeId, treeNode) {
	            	alert(treeNode.tId + ", " + treeNode.name);
	            }
	            				
	           
         		var zNodes = eval('(' + ff + ')');    
	            var zTree1;
	            
	            var setting = {
	        			showLine: true,
	        			checkable : true,
	        			checkStyle : "radio",
	        			checkRadioType : "all",
	        			checkRadioMaxNum : 1
	        		};
				$("#create_belongOrgName").click(function(){
					zTree1 = $("#tree1").zTree(setting, zNodes);
					$('#orgTree').dialog('open');
				});*/

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
			        		<td height="31"><div class="titlebt">机构管理</div></td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;组织机构列表</td>
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
										                <span class="left_txt2">在这里，您可以创建、修改、删除以及改变组织机构对象</span><br>
										                <span class="left_txt3">注意!</span>
										                <span class="left_txt3">组织机构的删除为物理删除,一旦删除数据将不可恢复.</span>
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
                									<td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;组织机构列表</td> 
                									<td class="left_bt2" align="right">
														<!-- 
														<a href="<c:url value="addtoporgtype.action"/>">创建顶层机构</a>&nbsp;&nbsp;&nbsp;&nbsp;
														<a href="javascript:void(0)" id="createtoporgtype">创建组织机构</a>&nbsp;&nbsp;&nbsp;&nbsp;
														-->
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
														<th width="30%" class="left_txt2">机构名称</th>
														<th width="25%" class="left_txt2">机构简称</th>
														<th width="10%" class="left_txt2">机构状态</th>
														<th width="33%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="org" items="${orgList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
													<c:if test="${org.level >1 }">
														<tr height="10" bgcolor="#f2f2f2" id="${org.orgId}" class="child-of-${org.parentOrgId}">
													</c:if>
													<c:if test="${org.level ==1 }">
														<tr height="10" bgcolor="#f2f2f2" id="${org.orgId}" >
													</c:if>
													</c:if>
													
													<c:if test="${(status.count-1) % 2 !=0}">
													<c:if test="${org.level >1 }">
														<tr height="10" id="${org.orgId}" class="child-of-${org.parentOrgId}">
													</c:if>
													<c:if test="${org.level ==1 }">
														<tr height="10" id="${org.orgId}">
													</c:if>
													</c:if>
														<td align="left" class="liuyang" style="padding-left:40px;">${org.orgName }</td>
														<td align="left">${org.orgShotrName }</td>
														<c:if test="${org.orgState==0}">
														<td align="center">无效</td>
														<td align="center">
															<sec:authorize url="/changeorg.action">
															<a href="<c:url value="${org.orgId}/1/changeorg.action"/>" name="${org.orgName}" class="online">生效</a>&nbsp;&nbsp;&nbsp;&nbsp;
															</sec:authorize>
															<sec:authorize url="/delorg.action">
															<a href="<c:url value="${org.orgId}/delorg.action"/>" name="${org.orgName}" class="delorg">删除组织</a>&nbsp;&nbsp;&nbsp;&nbsp;
															</sec:authorize>
														</td>
														</c:if>
														<c:if test="${org.orgState==1}">
														<td align="center">有效</td>
														<td align="center">
															<!--<sec:authorize access="hasRole('ROLE_ADMIN')">
																7
															</sec:authorize>
															<sec:authorize access="hasRole('ROLE_NORMAL')">
																8
															</sec:authorize>
															<sec:authentication property="principal.username" />
															<sec:authentication property="principal.orgId" />
															<sec:authentication property="principal.currentOrgId" />-->
															<c:if test="${org.level > 1 }">
															<sec:authorize url="/manage/org/changeorg.action">
															<a href="<c:url value="${org.orgId}/0/changeorg.action"/>" name="${org.orgName}" class="offline">无效</a>&nbsp;&nbsp;&nbsp;&nbsp;
															</sec:authorize>
															</c:if>
															<c:if test="${org.level < 5 }">
															<sec:authorize url="/manage/org/addorg.action">
															<a href="javascript:void(0)" class="class_addchildorg"  orgparentid="${org.orgId}" orgparentname="${org.orgName }" orglevel="${org.orgLevel+1} ">增加子组织</a>&nbsp;&nbsp;&nbsp;&nbsp;
															</sec:authorize>
															</c:if>
															<c:if test="${org.level > 1 }">
															<sec:authorize url="/manage/org/delorg.action">
															<a href="<c:url value="${org.orgId}/delorg.action"/>" name="${org.orgName}" class="delorg">删除组织</a>&nbsp;&nbsp;&nbsp;&nbsp;
															</sec:authorize>
															</c:if>
															<sec:authorize url="/manage/org/editorg.action">
															<a href="javascript:void(0)" class="class_editorg" orgid="${org.orgId}" orgName="${org.orgName }" orgShotrName="${org.orgShotrName }" orgAddress="${org.orgAddress }" orgTel="${org.orgTel }" orgContactor="${org.orgContactor }" orgZipCode="${org.orgZipCode }" >修改组织</a>&nbsp;&nbsp;&nbsp;&nbsp;
															</sec:authorize>
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
		
		<div id="form-createorg" title="创建组织机构">
			<p class="validateTips"></p>
			<form>
				<fieldset>
					<input type="hidden" name="parentOrgId" id="create_parentOrgId" value=""/>
					<input type="hidden" name="belongOrgId" id="create_belongOrgId" value="0"/>
					<input type="hidden" name="orgLevel" id=orgLevel value="0"/>
					<label for="name">父组织机构名称:</label>
					<input type="text" name="parentOrgName" id="create_parentOrgName" value="" disabled class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构名称:</label>
					<input type="text" name="orgName" id="create_orgName" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构简称:</label>
					<input type="text" name="orgShotrName" id="create_orgShotrName" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构地址:</label>
					<input type="text" name="orgAddress" id="create_orgAddress" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构电话:</label>
					<input type="text" name="orgTel" id="create_orgTel" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构联系人:</label>
					<input type="text" name="orgContactor" id="create_orgContactor" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">邮政编码:</label>
					<input type="text" name="orgZipCode" id="create_orgZipCode" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构类型:</label>
					<select id="create_orgTypeId" name="orgTypeId">
					</select><br/>
					<label for="name">组织机构子类型:</label>
					<select id="create_orgSubTypeId" name="orgSubTypeId">
					</select><br/>
					<label for="name">是否允许注册:</label>
					<select name="regAble" id="create_regAble">
						<option value="1">允许</option>
						<option value="0">不允许</option>
					</select><br/>
					<!-- 
					<label id="lable_create_belongOrgName" for="name">组织机构归属:</label>
					<input type="text" name="belongOrgName" id="create_belongOrgName" class="text ui-widget-content ui-corner-all" /><br/>
					 -->
				</fieldset>
			</form>
		</div>
		
		<div id="form-editorg" title="修改组织机构">
			<p class="validateTips"></p>
			<form>
				<fieldset>
					<input type="hidden" id="edit_orgId" name="orgId" value=""/>
					<!-- 
					<input type="hidden" name="parentOrgId" id="edit_parentOrgId" value=""/>
					<label for="name">父组织机构名称:</label>
					<input type="text" name="parentOrgName" id="edit_parentOrgName" value="" disabled class="text ui-widget-content ui-corner-all" /><br/> 
					-->
					<label for="name">组织机构名称:</label>
					<input type="text" name="orgName" id="edit_orgName" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构简称:</label>
					<input type="text" name="orgShotrName" id="edit_orgShotrName" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构地址:</label>
					<input type="text" name="orgAddress" id="edit_orgAddress" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构电话:</label>
					<input type="text" name="orgTel" id="edit_orgTel" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构联系人:</label>
					<input type="text" name="orgContactor" id="edit_orgContactor" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">邮政编码:</label>
					<input type="text" name="orgZipCode" id="edit_orgZipCode" class="text ui-widget-content ui-corner-all" />
					<!-- 
					<label for="name">组织机构类型:</label>
					<select id="create_orgTypeId" name="orgTypeId">
					</select><br/>
					<label for="name">组织机构子类型:</label>
					<select id="create_orgSubTypeId" name="orgSubTypeId">
					</select><br/>
					<label for="name">是否允许注册:</label>
					<input type="text" name="regAble" id="edit_regAble" class="text ui-widget-content ui-corner-all" /><br/>
					<label for="name">组织机构归属:</label>
					<input type="text" name="belongOrgId" id="edit_belongOrgId" class="text ui-widget-content ui-corner-all" /><br/>
					-->
				</fieldset>
			</form>
		</div>
		
		<div id="orgTree" title="归属组织列表">
			<ul id="tree1" class="tree" style="width:320px; overflow:auto;"></ul>
		</div>
	</body>
</html>