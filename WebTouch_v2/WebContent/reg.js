$(document).ready(function() {
	var validator = $("#signupform").validate({
		rules: {
			username: {
				required: true,
				minlength: 3
			},
			orgpassword: {
				required: true,
				minlength: 5
			},
			resurePassword: {
				required: true,
				minlength: 5,
				equalTo: "#orgpassword"
			},
			orglogname: "required",
			email	: "required email"
		},
		messages: {
			username: {
				required: "请填写用户名",
				minlength: "用户名不能小于3位"
			},
			orgpassword: {
				required: "请填写密码",
				minlength: "密码不能小于5位"
			},
			resurePassword: {
				required: "请填写密码",
				minlength: "密码不能小于5位",
				equalTo: "请输入同样的密码"
			},
			orglogname: "请填写组织名称",
			email	: "邮箱地址不对"
		},
		// the errorPlacement has to take the table layout into account
		errorPlacement: function(error, element) {
			if ( element.is(":radio") )
				error.appendTo( element.parent().next().next() );
			else if ( element.is(":checkbox") )
				error.appendTo ( element.next() );
			else
			{
				//$"<span class='form-tip tip-error'>"+error.label+"</br></span>".appendTo( element.next() );
				
				//element.next("").append("<span class='form-tip tip-error'><br></span>")
				error.appendTo( element.next() );
			}
		},
		// specifying a submitHandler prevents the default submit, good for the demo
		submitHandler: function() {

			$.post("RegisterOrganization?action=RegisterOrg", $("#signupform").serialize(),
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

		},
		// set this class to error-labels to indicate valid fields
		success: function(label) {
			// set &nbsp; as text for IE
			label.html("&nbsp;").addClass("checked");
		}
	});
})