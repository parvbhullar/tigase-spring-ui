/**
 * 
 */
$(document).ready( function() {		
	      $('#save').click(function() { 
	    	  var validator=$('#userForm').validate({
	    	    	rules: { 
	    	    		logname: "required", 
	    	    		password: "required"
	    	        }, 
	    	        messages: { 
	    	        	logname: "登陆名必填", 
	    	        	password: "密码必填"
	    	        },
	    	        success: function(label) { 
	    	            label.html(" ").addClass("checked"); 
	    	        }
	    	    }).form();
	    	  if(validator){
	    		  $.ajax({
		    		   type: "POST",
		    		   url: "user/saveUserItem.action",
		    		   data: $('#userForm').formSerialize(),
		    		   success: function(msg){
		    			   $.unblockUI();
		    			   $("#listTable").trigger("reloadGrid");
		   	            	return false;
		    		   }
		    		 }); 
	    	  }
	            return false; 
	        });
})
