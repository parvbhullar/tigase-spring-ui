$(document).ready( function() {
	$("#registerOrgBtn").attr("disabled","disabled");
	$("#organizationForm")[0].reset();
	
	//提交
	$("#registerOrgBtn").click(function() {
		validateRegister(register);
	});
	
	$("#agree").click(function(){
		if($("#agree").attr("checked")==true){
			$("#registerOrgBtn").attr("disabled","");
		}else{
			$("#registerOrgBtn").attr("disabled","disabled");
		}
	});

	//验证机构名称
	$("#organizationForm #orgname").blur(function() {
		validName();
	});
	
	//验证机构登录名
	$("#organizationForm #orglogname").blur(function() {
		validLogname();
	});
	
	//验证密码
	$("#organizationForm #orgpassword").blur(function() {
		validPassword();
	});
	
	//验证确认密码
	$("#organizationForm #resurePassword").blur(function() {
		validResurePassword();
	});
	
	//验证Eamil
	$("#organizationForm #email").blur(function() {
		validEmail();
	});
});



//验证
function validateRegister(func){
	$.post("validate?action=validorgcreate", $("#organizationForm").serialize(),
			function (data){
				var obj = eval(data);
				var result = obj.result;
				var reason = obj.reason;
				if(result == "success"){
					if(func!=null){						
						func();
						$('#organizationForm')[0].reset();
					}
				}else{
					alert(reason);
				}
			}, 
			"json");
}

//注册
function register(){
	$.post("RegisterOrganization?action=RegisterOrg", $("#organizationForm").serialize(),
		function (data){
		var obj = eval(data);
		var result = obj.result;
		if(result == "success"){
			alert("注册机构成功！");
			window.location.href="login.jsp";
		}else{
			alert("注册机构出错！");
		}
	}, 
	"json");
}

//验证机构名称
function validName(){
	$.post("validate?action=validorgname&orgname="+$("#orgname").val(),
			function (data){
				var obj = eval(data);
				var result = obj.result;
				var reason = obj.reason;
				if(result == "success"){
					$("#nameMessage").html("");
				}else{
					$("#nameMessage").html(reason);
				}
			}, 
			"json");
}

//验证机构登录名
function validLogname(){
	$.post("validate?action=validorglogname&orglogname="+$("#orglogname").val(),
			function (data){
				var obj = eval(data);
				var result = obj.result;
				var reason = obj.reason;
				if(result == "success"){
					$("#lognameMessage").html("");
				}else{
					$("#lognameMessage").html(reason);
				}
				
			}, 
			"json");
}

//验证密码
function validPassword(){
	$.post("validate?action=validpassword&password="+$("#orgpassword").val(),
			function (data){
				var obj = eval(data);
				var result = obj.result;
				var reason = obj.reason;
				if(result == "success"){
					$("#passwordMessage").html("");
				}else{
					$("#passwordMessage").html(reason);
				}
			}, 
			"json");
}

//验证确认密码
function validResurePassword(){
	$.post("validate?action=validresurepassword&password="+$("#orgpassword").val()+"&resurepassword="+$("#resurePassword").val(),
			function (data){
				var obj = eval(data);
				var result = obj.result;
				var reason = obj.reason;
				if(result == "success"){
					$("#resurePasswordMessage").html("");
				}else{
					$("#resurePasswordMessage").html(reason);
				}
			}, 
			"json");
}

//验证Email
function validEmail(){
	$.post("validate?action=validemail&email="+$("#email").val(),
			function (data){
				var obj = eval(data);
				var result = obj.result;
				var reason = obj.reason;
				if(result == "success"){
					$("#emailMessage").html("");
				}else{
					$("#emailMessage").html(reason);
				}
			}, 
			"json");
}