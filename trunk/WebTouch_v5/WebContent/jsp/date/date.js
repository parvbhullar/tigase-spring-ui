/**
 * 
 */
$(document).ready( function() {		
	      /*添加按钮点击事件开始*/
	      $('#add').click(function() { 
	          $.blockUI({ 
	              theme:     true, 
	              title:    '添加用户', 
	              message:  $("#addForm"),
	              css: { 
	            	  top:  ($(window).height() - 400) /2 + 'px', 
	                  left: ($(window).width() - 400) /2 + 'px' 
	              }
	          }); 
	      });
	      
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
	      
	      $('#cancel').click(function() { 
	            $.unblockUI(); 
	            return false; 
	        });
	      /*添加按钮点击事件结束*/
	      
	      $('#delete').click(function() { 
	    	  var selarrrow=$("#listTable").getGridParam('selarrrow');
	    	  $.ajax({
	    		   type: "POST",
	    		   url: "user/deleteUserItems.action",
	    		   data: "selarrrow="+selarrrow,
	    		   success: function(msg){
	    			   $("#listTable").trigger("reloadGrid");
	   	            	return false;
	    		   }
	    		 }); 
	            return false;
	        });
})
