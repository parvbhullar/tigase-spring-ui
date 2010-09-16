$(document).ready(function() {
	$("#updateForm").validate({
        rules: {      
			oldpassword: {
	        required: true,
	        remote:{
	        	url:"checkPassword.action",
	        	type: "post",
	        	data: {
	        		"oldpassword": function() {
	            		return $("#oldpasswordId").val();
	            	}
	          	}                          
	        }    
	    	},
			    password: "required",
				cfmpwd: {
				      equalTo: "#passwordid"
				    }
        },
        messages:{
        		oldpassword: {remote:"原密码不对!"}	        
	    }
    });	
	
	/*
	$("#dialog-message").dialog({
		modal: true,
		buttons: {
			确定: function() {
				$(this).dialog('close');
			}
		}
	});
	*/
	
	$("#submitid").click(function(){
		$.ajax({
			   type: "POST",
			   url: "updatePassword.action",
			   data: "password="+$("#passwordid").val(),
			   success: function(msg){
			   		alert(msg);
			   		history.go(-1);
			   }
			 });
		});
});



