$(document).ready( function() {
		initUserButton("list");
		initUserContent("#userMod","list");
		initUserContentFunc("#userMod","list");
		//用户初始化
	});	
	
	
	//按钮显示  	分成form和list两种状态
	function initUserButton(type)
	{
		if(type=="form"){
			$("#userMod #plus").hide();
			$("#userMod #edit").hide();
			$("#userMod #trash").hide();
			$("#userMod #loop").hide();
			
			$("#userMod #check").show();
			$("#userMod #reload").show();	
		}else if(type=="edit"){
			$("#userMod #plus").hide();
			$("#userMod #edit").hide();
			$("#userMod #trash").hide();
			$("#userMod #loop").hide();
			
			$("#userMod #check").show();
			$("#userMod #reload").show();	
		}else{
			$("#userMod #plus").show();
			$("#userMod #edit").show();
			$("#userMod #trash").show();
			$("#userMod #loop").show();
			
			$("#userMod #check").hide();
			$("#userMod #reload").hide();
		}
	}

	//初始化内容区域 显示与否 分成form和list两种状态
	function initUserContent(parentdiv,type){
		if(type=="form"){
			$(parentdiv+" #curdForm").show();
			$(parentdiv+" #UpdUserDiv").hide();
			$(parentdiv+" #listDiv").hide();
		}else if(type=="edit"){
			$(parentdiv+" #curdForm").hide();
			$(parentdiv+" #UpdUserDiv").show();
			$(parentdiv+" #listDiv").hide();
		}else{
			$(parentdiv+" #curdForm").hide();
			$(parentdiv+" #UpdUserDiv").hide();
			$(parentdiv+" #listDiv").show();
			countHeight("list");
		}
	}

	//初始化内容区域的数据  分成form和list两种状态
	function initUserContentFunc(parentdiv,type){
		if(type=="form"){
			
		}else if(type=="edit"){
			
		}else{
			loadUserList();
			$( "#dialogViewUser" ).hide();
		}
	}
	
	//用户初始化
	function loadUserList(){	
		var varmaillist=$("#userList").jqGrid({ 
			url:'usersystem?action=UserList', 
			datatype: 'json', 
			mtype: 'POST', 
			colNames:['id','登录名','姓名','邮箱','办公电话','手机号码','状态'], 
			colModel :[
			    {name:'id', 			index:'id', 			hidden:true},
			    {name:'a.logname', 		index:'a.logname', 		width:50,align:"left"},
			    {name:'b.name', 		index:'b.name', 		width:50,align:"left"},
			    {name:'c.email', 		index:'c.email', 		width:50,align:"left"},
			    {name:'c.officephone', 	index:'c.officephone', 	width:50,align:"right"},
			    {name:'c.mobilephone', 	index:'c.mobilephone', 	width:50,align:"right"},
			    {name:'a.state', 		index:'a.state', 		width:50,align:"center"},
				], 
			pager: "#page",
			rowNum:10,
			autowidth:true,
			height:260,
//			height:320,
//			caption: '用户',
			loadtext: '正在加载...',
			loadui : 'block',
			multiselect : true,
			//rowList:[10], 
			sortname: 'a.id', 
			recordtext: "记录 {0} - {1} of {2}",//显示记录数的格式
			emptyrecords: "无数据",//空记录时的提示信息
			pgtext : "第 {0}页 总页数 {1}",//页数显示格式
			jsonReader:{
	            repeatitems : false
	    	},
			loadComplete:function(){
				$("#userList td").css("border-left","0px");
				$("#userList td").css("border-right","0px");
				$("#userList .ui-jqgrid-view").css("border","0px");
				$("#userList tr").each(function()
				{
					var id=$('td:eq(1)',this).html();
					$('td:eq(2)',this).click(function(){
						viewUserDetail(id);						
						return false;
					});
					$('td:eq(3)',this).click(function(){
						viewUserDetail(id);
						return false;
					});
					$('td:eq(4)',this).click(function(){
						viewUserDetail(id);
						return false;
					});
					$('td:eq(5)',this).click(function(){
						viewUserDetail(id);
						return false;
					});
					$('td:eq(6)',this).click(function(){
						viewUserDetail(id);
						return false;
					});
					$('td:eq(7)',this).click(function(){
						viewUserDetail(id);
						return false;
					});
					$('td:eq(8)',this).click(function(){
						$( "#settingcardDiv" ).dialog( "open" );
						$.ajax({
							  url: 'usersystem?action=viewusercard&id='+id,
							  type:'post',
							  success: function(data) {
								var obj = eval('(' + data + ')');
								$("#cardid").val(obj.cardid);
								$("#carduserid").val(obj.userid);
								$('#settingcardTable input[name$="cardnumber"]').val(obj.cardnumber);
								$('#settingcardTable input[name$="cardpassword"]').val(obj.cardpassword);
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
	 * webcall冲值开始
	 */
	var vardialogconfirm=$( "#settingcardDiv" ).dialog({
		resizable: true,
		autoOpen: false,
		height:255,
		width:470,
		modal: true,
		buttons: {
			"提交": function() {
				var cardid = $("#cardid").val();
				if(cardid == ""){
					$.post("usersystem?action=Addcard", $("#settingcard").serialize(),
							function (data, textStatus){
								if(textStatus == "success"){
									$( "#settingcardDiv" ).dialog( "close" );
									$("#userList").trigger("reloadGrid");//刷新表格
								}else{
									alert("创建用户卡号出错！");
								}
							}, 
							"json");
				}else{
					$.post("usersystem?action=Updcard", $("#settingcard").serialize(),
							function (data, textStatus){
								if(textStatus == "success"){
									$( "#settingcardDiv" ).dialog( "close" );
									$("#userList").trigger("reloadGrid");//刷新表格
								}else{
									alert("修改用户卡号出错！");
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
	 * webcall冲值结束
	 */	
	
	function viewUserDetail(id){
		//innerLayout.
		$.ajax({
			  url: 'usersystem?action=viewuser&id='+id,
			  type:'post',
			  success: function(data) {
				var obj = eval('(' + data + ')');
				var vc_sex;
				if(obj.sex == '0'){
					vc_sex = "保密";
				}else if(obj.sex == '1'){
					vc_sex = "男";
				}else if(obj.sex == '2'){
					vc_sex = "女";
				}else{
					vc_sex = "保密";
				}
				var vc_usersort;
				if(obj.usersort == '1'){
					vc_usersort = "企业内部用户";
				}else if(obj.usersort == '2'){
					vc_usersort = "代理商用户";
				}else if(obj.usersort == '3'){
					vc_usersort = "供应商用户";
				}else if(obj.usersort == '4'){
					vc_usersort = "客户用户";
				}
				var vc_state;
				if(obj.state == '2'){
					vc_state = "在职";
				}else if(obj.state == '3'){
					vc_state = "离职";
				}
				$('#viewuser #logname').text(obj.logname);
				$('#viewuser #nickname').text(obj.nickname);
				$('#viewuser #username').text(obj.name);
				$('#viewuser #sex').text(vc_sex);
				$('#viewuser #usersort').text(vc_usersort);
				$('#viewuser #job').text(obj.job);
				$('#viewuser #email').text(obj.email);
				$('#viewuser #officephone').text(obj.officephone);
				$('#viewuser #otherphone').text(obj.otherphone);
				$('#viewuser #mobilephone').text(obj.mobilephone);
				$('#viewuser #faxnumber').text(obj.faxnumber);
				$('#viewuser #detailedaddress').text(obj.detailedaddress);
				$('#viewuser #postcode').text(obj.postcode);
				$('#viewuser #othermessage').text(obj.othermessage);
				$('#viewuser #photoname').text(obj.photoname);
				$('#viewuser #state').text(vc_state);
			  },
			  complete:function(){
				  $("#mainContent .ui-layout-south").html($( "#dialogViewUser" ).html());
			  }
			});
	}
	
	//取消
	$("#userMod #reload").click(function() {
		  initUserButton("list");
		  initUserContent("#userMod","list");
		  $('#userForm')[0].reset();
		  clearMessage();
		  countHeight("form");
		  $("#mainContent .ui-layout-south.ui-layout-pane.ui-layout-pane-south").html("详细信息显示内容栏");
		});
	
	/*创建用户开始*/
	$("#userMod #plus").click(function() {
		  $('#operation').val("addUser");
		  initUserButton("form");
		  initUserContent("form");
		  
		  
		  $("#userMod #listDiv").hide();  
		  $("#userMod #UpdUserDiv").hide();  
		  $("#userMod #curdForm").show();
		  //innerLayout.hide('south');
		});
	/*创建用户结束*/
	
	/*编辑用户开始*/
	$("#userMod #edit").click(function() {
		var id = jQuery("#userList").jqGrid('getGridParam','selarrrow');//获取选中的所有Id
		if(id==""){
			alert("必须选择一条记录进行编辑操作！");
		}else if(id.toString().indexOf(",")>-1){
			alert("只能选择一条记录进行编辑操作！");
		}else{
			$('#operation').val("editUser");
			initUserButton("edit");
			initUserContent("edit");
			  
			
			$("#userMod #listDiv").hide();  
			$("#userMod #UpdUserDiv").show();  
			$("#userMod #curdForm").hide();
			innerLayout.hide('south');
			$.ajax({
				  url: 'usersystem?action=viewuser&id='+id,
				  type:'post',
				  success: function(data) {
					var obj = eval('(' + data + ')');
					$("#userid").val(obj.userid);
					$('#UpdUser input[name$="logname"]').val(obj.logname);
					$('#UpdUser input[name$="nickname"]').val(obj.nickname);
					$('#UpdUser input[name$="username"]').val(obj.name);
					$('#UpdUser input[name$="uuserpassword"]').val(obj.password);
					$("#state").attr("state", obj.state);
					
					$("#sex").attr("value", obj.sex);
					$("#usersort").attr("value", obj.usersort);
					$('#UpdUser input[name$="job"]').val(obj.job);
					$("#deptid").attr("value", obj.deptid);
					$('#UpdUser input[name$="email"]').val(obj.email);
//					$('#UpdUser input[name$="otheremail"]').val(obj.otheremail);
					$('#UpdUser input[name$="officephone"]').val(obj.officephone);
					$('#UpdUser input[name$="otherphone"]').val(obj.otherphone);
					$('#UpdUser input[name$="mobilephone"]').val(obj.mobilephone);
					$('#UpdUser input[name$="faxnumber"]').val(obj.faxnumber);
//					$("#nationality").attr("value", obj.nationality);
//					$("#province").attr("value", obj.province);
//					$("#city").attr("value", obj.city);
					$("#nationality").val(obj.nationality);
					$("#province").val(obj.province);
					$("#city").val(obj.city);
					
					$('#UpdUser input[name$="detailedaddress"]').val(obj.detailedaddress);
					$('#UpdUser input[name$="postcode"]').val(obj.postcode);
					$('#UpdUser input[name$="othermessage"]').val(obj.othermessage);
					$('#UpdUser input[name$="photoname"]').val(obj.photoname);
					$("#state").attr("value", obj.state);
					
//					$("#nationality").empty();
//					$("#province").empty();
//					$("#city").empty();
//					chang('nationality',obj.nationality,'province',obj.province,'city',obj.city);
				  }
				});
		}
		});
	/*编辑用户开始*/
	
	/*删除用户开始*/
	$("#userMod #trash").click(function() {
			var id = jQuery("#userList").jqGrid('getGridParam','selarrrow');//获取选中的所有Id					
			if(id==""){
				alert("必须选择一条记录进行删除操作！");
			}else{
				if(confirm("确定要删除用户吗？")){
					$.ajax({
						url: 'usersystem?action=Deluser&id='+id,
						type:'post',
						success: function(data) {
							$("#userList").trigger("reloadGrid");//刷新表格
							var obj = eval('(' + data + ')');
							var result = obj.result;
							var reason = obj.reason;
							if(result == "success"){
								
							}else{
								alert(reason);
							}
						}
					});				
				}
			}
		});
	/*删除用户结束*/
	
	/*刷新用户开始*/
	$("#userMod #loop").click(function() {
		  $("#userList").trigger("reloadGrid");//刷新表格
	});
	/*刷新用户结束*/
	
	//保存
	$("#userMod #check").click(function(){
			var operation=$('#operation').val();
			if(operation=="addUser"){
				validateUserCreate(createUserInfo);
			}else{
				validateUserUpdate(updateUserInfo);
			}
			$("#mainContent .ui-layout-south.ui-layout-pane.ui-layout-pane-south").html("详细信息显示内容栏");
		}
	);
	
	//验证创建用户
	function validateUserCreate(func){
		$.post("validate?action=validusercreate", $("#userForm").serialize(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						if(func!=null){						
							func();
							$('#userForm')[0].reset();
							clearMessage();
						}
					}else{
						alert(reason);
					}
				}, 
				"json");
	}
	
	//验证修改用户
	function validateUserUpdate(func){
		$.post("validate?action=validuserupdate", $("#UpdUser").serialize(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						if(func!=null){						
							func();
							clearMessage();
						}
					}else{
						alert(reason);
					}
				}, 
				"json");
	}
	
	//创建用户
	function createUserInfo(){
		$.post("usersystem?action=Adduser", $("#userForm").serialize(),
				function (data){
					$("#userList").trigger("reloadGrid");//刷新表格
					var obj = eval('(' + data + ')');
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						
					}else{
						alert(reason);
					}
				}, 
				"json");
		initUserButton("list");
		initUserContent("#userMod","list");
		//innerLayout.show('south');
	}
	
	//修改用户
	function updateUserInfo(){
		$.post("usersystem?action=Upduser", $("#UpdUser").serialize(),
				function (data){
					$("#userList").trigger("reloadGrid");//刷新表格
					var obj = eval('(' + data + ')');
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						
					}else{
						alert(reason);
					}
				}, 
				"json");
		initUserButton("list");
		initUserContent("#userMod","list");
		//innerLayout.show('south');
	}
	
	//验证用户登录名
	$("#userForm #logname").blur(function() {
		validLognameCreate();
	});
	
	//验证用户姓名
	$("#userForm #username").blur(function() {
		validNameCreate();
	});
	
	//验证密码
	$("#userForm #userpassword").blur(function() {
		validPasswordCreate();
	});
	
	//验证密码确认
	$("#userForm #resurePassword").blur(function() {
		validResurePasswordCreate();
	});
	
	//验证用户姓名
	$("#UpdUser #username").blur(function() {
		validNameUpdate();
	});
	
	//验证密码
	$("#UpdUser #uuserpassword").blur(function() {
		validPasswordUpdate();
	});
	
	//验证用户姓名
	function validNameCreate(){
		$.post("validate?action=validusername&username="+$("#userForm #username").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#usernameMessageCreate").html("");
					}else{
						$("#usernameMessageCreate").html(reason);
					}
				}, 
				"json");
	}
	
	//验证用户姓名
	function validNameUpdate(){
		$.post("validate?action=validusername&username="+$("#UpdUser #username").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#usernameMessageUpdate").html("");
					}else{
						$("#usernameMessageUpdate").html(reason);
					}
				}, 
				"json");
	}

	//验证用户登录名
	function validLognameCreate(){
		$.post("validate?action=validuserlogname&logname="+$("#userForm #logname").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#userlognameMessageCreate").html("");
					}else{
						$("#userlognameMessageCreate").html(reason);
					}
					
				}, 
				"json");
	}

	//验证密码
	function validPasswordCreate(){
		$.post("validate?action=validpassword&password="+$("#userForm #userpassword").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#userpasswordMessageCreate").html("");
					}else{
						$("#userpasswordMessageCreate").html(reason);
					}
				}, 
				"json");
	}
	
	//验证密码
	function validPasswordUpdate(){
		$.post("validate?action=validpassword&password="+$("#UpdUser #uuserpassword").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#passwordMessageUpdate").html("");
					}else{
						$("#passwordMessageUpdate").html(reason);
					}
				}, 
				"json");
	}

	//验证确认密码
	function validResurePasswordCreate(){
		$.post("validate?action=validresurepassword&password="+$("#userForm #userpassword").val()+"&resurepassword="+$("#userForm #resurePassword").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#resurePasswordMessageCreate").html("");
					}else{
						$("#resurePasswordMessageCreate").html(reason);
					}
				}, 
				"json");
	}

	
	//清空提示
	function clearMessage(){
		$("#userlognameMessageCreate").html("");
		$("#usernameMessageCreate").html("");
		$("#userpasswordMessageCreate").html("");
		$("#resurePasswordMessageCreate").html("");
		$("#usernameMessageUpdate").html("");
		$("#passwordMessageUpdate").html("");
	}
	
	
	