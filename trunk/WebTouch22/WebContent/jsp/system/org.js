$(document).ready( function() {
		initOrgButton("list");
		initOrgContent("#orgMod","list");
		initOrgContentFunc("#orgMod","list");
		//机构初始化
	});	
	
	
	//按钮显示  	分成form和list两种状态
	function initOrgButton(type)
	{
		if(type=="form"){
			$("#orgMod #plus").hide();
			$("#orgMod #trash").hide();
			$("#orgMod #loop").hide();
			
			$("#orgMod #check").show();
			$("#orgMod #reload").show();	
		}else{
			$("#orgMod #plus").show();
			$("#orgMod #trash").show();
			$("#orgMod #loop").show();
			
			$("#orgMod #check").hide();
			$("#orgMod #reload").hide();
		}
	}

	//初始化内容区域 显示与否 分成form和list两种状态
	function initOrgContent(parentdiv,type){
		if(type=="form"){
			$(parentdiv+" #curdForm").show();
			$(parentdiv+" #UpdOrgDiv").hide();
			$(parentdiv+" #listDiv").hide();			
		}else{
			$(parentdiv+" #curdForm").hide();
			$(parentdiv+" #UpdOrgDiv").hide();
			$(parentdiv+" #listDiv").show();
		}
	}

	//初始化内容区域的数据  分成form和list两种状态
	function initOrgContentFunc(parentdiv,type){
		if(type=="form"){
			
		}else{
			loadOrgList();
			$( "#dialogViewOrg" ).hide();
		}
	}

	function loadOrgList(){	//机构初始化
		var varmaillist=$("#organizationList").jqGrid({ 
			url:'system?action=OrganizationList', 
			datatype: 'json', 
			mtype: 'POST', 
			colNames:['id','名称','公司电话','权限','操作'], 
			colModel :[  
			    {name:'id', 		index:'id', 			hidden:true},
			    {name:'name', 		index:'name', 			width:10,	align:"left"},
			    {name:'officephone',index:'officephone',	width:10,	align:"right",		sortable:false},
			    {name:'pname', 		index:'pname', 			width:10,	align:"left",		sortable:false},
			    {name:'operation',	index:'operation', 		width:10,	align:"center",		sortable:false}
				], 
			pager: "#page",
			rowNum:10,
			autowidth:true,
			height:260,
//			height:320,
//			caption: '机构',
			loadtext: '正在加载...',
			loadui : 'block',
			multiselect : true,
			//rowList:[10], 
			sortname: 'id', 
			recordtext: "记录 {0} - {1} of {2}",//显示记录数的格式
			emptyrecords: "无数据",//空记录时的提示信息
			pgtext : "第 {0}页 总页数 {1}",//页数显示格式
			jsonReader:{
	            repeatitems : false
	    	},
			loadComplete:function(){
				$("#organizationList td").css("border-left","0px");
				$("#organizationList td").css("border-right","0px");
				$("#organizationList .ui-jqgrid-view").css("border","0px");
				$("#organizationList tr").each(function()
				{
							var id=$('td:eq(1)',this).html();
							$('td:eq(2)',this).click(function(){
								viewOrgDetail(id);
								return false;
							});
							$('td:eq(3)',this).click(function(){
								viewOrgDetail(id);
								return false;
							});
							$('td:eq(4)',this).click(function(){
								viewOrgDetail(id);
								return false;
							});
							$('td:eq(5)',this).click(function(){
								$("#settingpermissions").reset;//清除之前的填写的信息
								$('#allpname').tokeninput({
									type :'1',
									url : 'system?action=getorgpurview',//获得数据的Servlet
									initDataFromId	:'token-initdata',
									preAddFromId	:'token-preadd'					
								});
								$( "#settingpermissionsDiv" ).dialog( "open" );
								$.ajax({
									  url: 'system?action=setpermissions&id='+id,
									  type:'post',
									  success: function(data) {
										var obj = eval('(' + data + ')');
										var orgid = obj.orgid;
										var pnumber = obj.pnumber;
										var orgname = obj.orgname;
										var pnames = obj.pnames;
										var allpnumber = obj.allpnumber;
										var allpname = obj.allpname;
										$("#orgid").val(orgid);
										$("#pnumber").val(pnumber);
										$('#settingpermissions td[name$="orgname"]').html(orgname);
										$('#settingpermissions input[name$="allpname"]').val(allpname);
										$('#settingpermissions td[name$="pname"]').html(pnames);
									  }
									});
								return false;
							});
				});
			},
			viewrecords: true 
	 	 });
	}
	
	/*
	 * 设置权限开始
	 */
	var vardialogconfirm=$( "#settingpermissionsDiv" ).dialog({
		resizable: true,
		autoOpen: false,
		height:330,
		width: 600,
		modal: true,
		buttons: {
			"创建": function() {
				var pname = $('#settingpermissions input[name$="tokenName"]').length;
				if(pname == 0){
					if(confirm("权限为空视为删除所有权限，是否删除？\n请选择\"确定\"或者\"取消\"！")){
						$.post("system?action=Addpurview", $("#settingpermissions").serialize(),
								function (data, textStatus){
									if(textStatus == "success"){
										$( "#settingpermissionsDiv" ).dialog( "close" );
										$("#organizationList").trigger("reloadGrid");//刷新表格
									}else{
										blockUI("创建机构权限出错！",1);
									}
								}, 
								"json");
					}
				}else{
					$.post("system?action=Addpurview", $("#settingpermissions").serialize(),
							function (data, textStatus){
								if(textStatus == "success"){
									$( "#settingpermissionsDiv" ).dialog( "close" );
									$("#organizationList").trigger("reloadGrid");//刷新表格
								}else{
									blockUI("创建机构权限出错！",1);
								}
							}, 
							"json");
				}
			},
			"取消": function() {
				$( this ).dialog( "close" );
			}
		}
	});
	/*
	 * 设置权限结束
	 */
	
	// 读取机构详细信息
	function viewOrgDetail(id){
		$("#mainContent .ui-layout-south.ui-layout-pane.ui-layout-pane-south").html($( "#dialogViewOrg" ).html());
		$.ajax({
			  url: 'system?action=view&id='+id,
			  type:'post',
			  success: function(data) {
				var obj = eval('(' + data + ')');
				$('#ViewOrg label[name$="orgname"]').text(obj.name);
				$('#ViewOrg label[name$="email"]').text(obj.email);
				$('#ViewOrg label[name$="officephone"]').text(obj.officephone);
				$('#ViewOrg label[name$="faxnumber"]').text(obj.faxnumber);
				$('#ViewOrg label[name$="nationality"]').text(obj.nationality);
				$('#ViewOrg label[name$="province"]').text(obj.province);
				$('#ViewOrg label[name$="city"]').text(obj.city);
				$('#ViewOrg label[name$="detailedaddress"]').text(obj.detailedaddress);
				$('#ViewOrg label[name$="postcode"]').text(obj.postcode);
				$('#ViewOrg label[name$="remark"]').text(obj.remark);
				$('#ViewOrg label[name$="pname"]').text(obj.pname);
			  }
			});
	}
	
	//取消
	$("#orgMod #reload").click(function() {
		initOrgButton("list");
		initOrgContent("#orgMod","list");
		$('#organizationForm')[0].reset();
		clearMessage();
		innerLayout.show('south');
		$("#mainContent .ui-layout-south.ui-layout-pane.ui-layout-pane-south").html("详细信息显示内容栏");
	});
	
	/*创建机构开始*/
	$("#orgMod #plus").click(function() {
		$('#operation').val("addOrg");
		initOrgButton("form");
		initOrgContent("form");
		$( "#dialogViewOrg" ).hide();
		
		$("#orgMod #listDiv").hide();
		$("#orgMod #UpdOrgDiv").hide();
		$("#orgMod #curdForm").show();
		innerLayout.hide('south');
	});
	/*创建机构结束*/

	/*删除机构开始*/
	$("#orgMod #trash").click(function() {		
			var id = jQuery("#organizationList").jqGrid('getGridParam','selarrrow');//获取选中的所有Id
			if(id==""){
				blockUI("必须选择一条记录进行删除操作！",1);
			}else{
				if(confirm("确定要删除机构吗？")){
					$.ajax({
						url: 'system?action=Delorg&id='+id,
						type:'post',
						success: function(data) {
						$("#organizationList").trigger("reloadGrid");//刷新表格
						}
					});
				}
			}		
		});
	/*删除机构结束*/
	/*刷新机构开始*/
	$("#orgMod #loop").click(function() {
		  $("#organizationList").trigger("reloadGrid");//刷新表格
	});
	/*刷新机构结束*/
	
	//保存
	$("#orgMod #check").click(function(){
		validateOrgCreate(createOrgInfo);
		$("#mainContent .ui-layout-south.ui-layout-pane.ui-layout-pane-south").html("详细信息显示内容栏");
		}
	);
	
	//验证创建机构
	function validateOrgCreate(func){
		$.post("validate?action=validorgcreate", $("#organizationForm").serialize(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						if(func!=null){						
							func();
							$('#organizationForm')[0].reset();
							clearMessage();
						}
					}else{
						alert(reason);
					}
				}, 
				"json");
	}
	
	//创建机构
	function createOrgInfo(){
		$.post("system?action=Addorg", $("#organizationForm").serialize(),
				function (data){
				$("#organizationList").trigger("reloadGrid");//刷新表格
				var obj = eval('(' + data + ')');
				var result = obj.result;
				var reason = obj.reason;
				if(result == "success"){
					
				}else{
					alert("创建机构出错！");
				}
			}, 
			"json");
		initOrgButton("list");
		initOrgContent("#orgMod","list");
		innerLayout.show('south'); 
	}
	
	//验证机构名称
	$("#organizationForm #orgname").blur(function() {
		validNameCreate();
	});
	
	//验证机构登录名
	$("#organizationForm #orglogname").blur(function() {
		validLognameCreate();
	});
	
	//验证密码
	$("#organizationForm #orgpassword").blur(function() {
		validPasswordCreate();
	});
	
	//验证密码确认
	$("#organizationForm #resurePassword").blur(function() {
		validResurePasswordCreate();
	});
	
	//验证Email
	$("#organizationForm #email").blur(function() {
		validEmailCreate();
	});
	
	//验证机构名称
	function validNameCreate(){
		$.post("validate?action=validorgname&orgname="+$("#organizationForm #orgname").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#orgnameMessageCreate").html("");
					}else{
						$("#orgnameMessageCreate").html(reason);
					}
				}, 
				"json");
	}
	

	//验证机构登录名
	function validLognameCreate(){
		$.post("validate?action=validorglogname&orglogname="+$("#organizationForm #orglogname").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#orglognameMessageCreate").html("");
					}else{
						$("#orglognameMessageCreate").html(reason);
					}
					
				}, 
				"json");
	}

	//验证密码
	function validPasswordCreate(){
		$.post("validate?action=validpassword&password="+$("#organizationForm #orgpassword").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#orgpasswordMessageCreate").html("");
					}else{
						$("#orgpasswordMessageCreate").html(reason);
					}
				}, 
				"json");
	}

	//验证确认密码
	function validResurePasswordCreate(){
		$.post("validate?action=validresurepassword&password="+$("#organizationForm #orgpassword").val()+"&resurepassword="+$("#organizationForm #resurePassword").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#orgresurePasswordMessageCreate").html("");
					}else{
						$("#orgresurePasswordMessageCreate").html(reason);
					}
				}, 
				"json");
	}

	//验证Email
	function validEmailCreate(){
		$.post("validate?action=validemail&email="+$("#organizationForm #email").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#orgemailMessageCreate").html("");
					}else{
						$("#orgemailMessageCreate").html(reason);
					}
				}, 
				"json");
	}
	
	//清空提示
	function clearMessage(){
		$("#orgnameMessageCreate").html("");
		$("#orglognameMessageCreate").html("");
		$("#orgpasswordMessageCreate").html("");
		$("#orgresurePasswordMessageCreate").html("");
		$("#orgemailMessageCreate").html("");
	}