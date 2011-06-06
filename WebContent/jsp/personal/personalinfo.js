$(document).ready( function() {
		initButton("list");
		initContent("#personalDetail","list");
		initContentFunc("#personalDetail","list");
		//用户初始化
	});	
	
	
	//按钮显示  	分成form和list两种状态
	function initButton(type)
	{
		if(type=="form"){
			$("#personalDetail #edit").hide();
			
			$("#personalDetail #check").show();
			$("#personalDetail #reload").show();	
		}else{
			$("#personalDetail #edit").show();
			
			$("#personalDetail #check").hide();
			$("#personalDetail #reload").hide();
		}
	}

	//初始化内容区域 显示与否 分成form和list两种状态
	function initContent(parentdiv,type){
		if(type=="form"){
			$(parentdiv+" #curdForm").hide();
			$(parentdiv+" #UpdPersonalDiv").show();
		}else{
			$(parentdiv+" #curdForm").show();
			$(parentdiv+" #UpdPersonalDiv").hide();
		}
	}

	//初始化内容区域的数据  分成form和list两种状态
	function initContentFunc(parentdiv,type){
		innerLayout.hide('south');
		if(type=="form"){
			
		}else{
			loadpersonalDetail();
		}
	}
	
	function loadpersonalDetail(){
		$("#dialogViewPersonal").reset;
		$.ajax({
			  url: 'usersystem?action=viewpersonal',
			  type:'post',
			  success: function(data) {
				var obj = eval('(' + data + ')');
				if(obj.sex == '0'){
					vc_sex = "保密";
				}else if(obj.sex == '1'){
					vc_sex = "男";
				}else if(obj.sex == '2'){
					vc_sex = "女";
				}else{
					vc_sex = "保密";
				}
				$("#personalid").val(obj.userid);
				$('#curdForm #logname').text(obj.logname);
				$('#curdForm #nickname').text(obj.nickname);
				$('#curdForm #username').text(obj.name);
				$('#curdForm #userpassword').text(obj.password);
				$('#curdForm #sex').text(vc_sex);
				$('#curdForm #job').text(obj.job);
				$('#curdForm #email').text(obj.email);
				$('#curdForm #officephone').text(obj.officephone);
				$('#curdForm #otherphone').text(obj.otherphone);
				$('#curdForm #mobilephone').text(obj.mobilephone);
				$('#curdForm #faxnumber').text(obj.faxnumber);
				$("#nationality").text(obj.nationality);
				$("#province").text(obj.province);
				$("#city").text(obj.city);
				
				$('#curdForm #detailedaddress').text(obj.detailedaddress);
				$('#curdForm #postcode').text(obj.postcode);
				$('#curdForm #othermessage').text(obj.othermessage);
				$('#curdForm #photoname').text(obj.photoname);
				$('#curdForm #state').text(obj.state);
			  }
			});
	}
	
	//保存
	$("#personalDetail #check").click(function() {
		validate(savePersonalInfo);
	});
	
	//取消
	$("#personalDetail #reload").click(function() {
		clearMessage();
		initButton("list");
		initContent("#personalDetail","list");
		});	
	
	/*编辑用户开始*/
	$("#personalDetail #edit").click(function(){
		$("#UpdPersonal").reset;//清除之前的填写的信息
		var id=$("#personalid").val();
		$.ajax({
			  url: 'usersystem?action=viewuser&id='+id,
			  type:'post',
			  success: function(data) {
				var obj = eval('(' + data + ')');
				$("#personalid").val(obj.userid);
				$('#UpdPersonal input[name$="logname"]').val(obj.logname);
				$('#UpdPersonal input[name$="nickname"]').val(obj.nickname);
				$('#UpdPersonal input[name$="username"]').val(obj.name);
				$('#UpdPersonal input[name$="userpassword"]').val(obj.password);
				$("#sex").attr("value", obj.sex);
				$("#usersort").attr("value", obj.usersort);
				$('#UpdPersonal input[name$="job"]').val(obj.job);
				$("#deptid").attr("value", obj.deptid);
				$('#UpdPersonal input[name$="email"]').val(obj.email);
				$('#UpdPersonal input[name$="officephone"]').val(obj.officephone);
				$('#UpdPersonal input[name$="otherphone"]').val(obj.otherphone);
				$('#UpdPersonal input[name$="mobilephone"]').val(obj.mobilephone);
				$('#UpdPersonal input[name$="faxnumber"]').val(obj.faxnumber);
//				$("#nationality").attr("value", obj.nationality);
//				$("#province").attr("value", obj.province);
//				$("#city").attr("value", obj.city);
				$("#nationality").val(obj.nationality);
				$("#province").val(obj.province);
				$("#city").val(obj.city);
				
				$('#UpdPersonal input[name$="detailedaddress"]').val(obj.detailedaddress);
				$('#UpdPersonal input[name$="postcode"]').val(obj.postcode);
				$('#UpdPersonal input[name$="othermessage"]').val(obj.othermessage);
				$('#UpdPersonal input[name$="photoname"]').val(obj.photoname);
				$("#state").attr("value", obj.state);
				
//				$("#nationality").empty();
//				$("#province").empty();
//				$("#city").empty();
//				chang('nationality',obj.nationality,'province',obj.province,'city',obj.city);
			  }
			});
		/*编辑用户结束*/
		initButton("form");
		initContent("#personalDetail","form");
	});
	
	//验证
	function validate(func){
		$.post("validate?action=validpersonal", $("#UpdPersonal").serialize(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						if(func!=null){
							func();
						}
					}else{
						alert(reason);
					}
				}, 
				"json");
	}
	
	//验证姓名
	$("#UpdPersonal #username").blur(function() {
		validPersonalName();
	});
	
	//验证办公电话
	$("#UpdPersonal #officephone").blur(function() {
		validOfficePhone();
	});
	
	//验证其他电话
	$("#UpdPersonal #otherphone").blur(function() {
		validOtherPhone();
	});
	
	//验证手机
	$("#UpdPersonal #mobilephone").blur(function() {
		validMobilePhone();
	});
	
	//验证传真
	$("#UpdPersonal #faxnumber").blur(function() {
		validfaxnumber();
	});
	
	//验证邮编
	$("#UpdPersonal #postcode").blur(function() {
		validPostcode();
	});
	
	//保存用户个人信息
	function savePersonalInfo(){
		loaDing("保存中");
		$.post("usersystem?action=Updpersonalinfo", $("#UpdPersonal").serialize(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					if(result == "success"){
						$.unblockUI();
						loadpersonalDetail();
						clearMessage();
					}else{
						alert(reason);
					}
				}, 
				"json");
		initButton("list");
		initContent("#personalDetail","list");
	}
	
	//验证用户姓名
	function validPersonalName(){
		$.post("validate?action=validusername&username="+$("#UpdPersonal #username").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#personalnameMessage").html("");
					}else{
						$("#personalnameMessage").html(reason);
					}
				}, 
				"json");
	}
	
	//验证办公电话
	function validOfficePhone(){
		$.post("validate?action=validphone&phone="+$("#UpdPersonal #officephone").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#personalofficephoneMessage").html("");
					}else{
						$("#personalofficephoneMessage").html(reason);
					}
				}, 
				"json");
	}
	
	//验证其他电话
	function validOtherPhone(){
		$.post("validate?action=validphone&phone="+$("#UpdPersonal #otherphone").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#personalotherphoneMessage").html("");
					}else{
						$("#personalotherphoneMessage").html(reason);
					}
				}, 
				"json");
	}
	
	//验证手机
	function validMobilePhone(){
		$.post("validate?action=validmobilephone&phone="+$("#UpdPersonal #mobilephone").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#personalmobilephoneMessage").html("");
					}else{
						$("#personalmobilephoneMessage").html(reason);
					}
				}, 
				"json");
	}
	
	//验证传真
	function validfaxnumber(){
		$.post("validate?action=validfax&fax="+$("#UpdPersonal #faxnumber").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#personalfaxnumberMessage").html("");
					}else{
						$("#personalfaxnumberMessage").html(reason);
					}
				}, 
				"json");
	}
	
	//验证邮编
	function validPostcode(){
		$.post("validate?action=validpostcode&postcode="+$("#UpdPersonal #postcode").val(),
				function (data){
					var obj = eval(data);
					var result = obj.result;
					var reason = obj.reason;
					if(result == "success"){
						$("#personalpostcodeMessage").html("");
					}else{
						$("#personalpostcodeMessage").html(reason);
					}
				}, 
				"json");
	}
	
	//清空提示
	function clearMessage(){
		$("#personalnameMessage").html("");
		$("#personalofficephoneMessage").html("");
		$("#personalotherphoneMessage").html("");
		$("#personalmobilephoneMessage").html("");
		$("#personalfaxnumberMessage").html("");
		$("#personalpostcodeMessage").html("");
	}