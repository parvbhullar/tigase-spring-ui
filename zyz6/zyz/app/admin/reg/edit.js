$(document).ready(function() {
	for(var i=0;i<arrayAreaCity.length;i++)
	{
		$("#loccitid").append(arrayAreaCity[i]);
	}

	$('#loccitid').change(function() {
		$('#locareid').find('option').remove();
		$.ajax({
			   type: "POST",
			   url: "load.action",
			   data: { "areaid": $(this).val() },
			   success: function(data){
				   $("#locareid").append(data);
			   }
			 });
		//changeCity($(this).val());
	  //alert();
	});

	$("#registerForm").validate({
        rules: {       
			name: "required",
			password: "required",
			cfmpwd: {
			      equalTo: "#passwordid"
			    },
			    email: "email",
			    cellPhone: {
			        required: true,
			        dn:true,
			        remote:{
			        	url:"checkDn.action",
			        	type: "post",
			        	data: {
			        		"uuid": function() {
			            		return $("#uuidid").val();
			            	}
			    		}
			        }
			    }
        },
        messages:{
	        	userName: {remote:"登录名不能重复!"}
	    }
    });
});