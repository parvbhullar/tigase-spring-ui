/**
 * 
 */
$(document).ready( function() {		
	      /*添加按钮点击事件开始*/
	      $('#area').click(function() { 
	          $.blockUI({ 
	              theme:     true, 
	              title:    '选择地区', 
	              message:  $("#jobLayerId"),
	              css: { 
	            	  top:  ($(window).height() - 400) /2 + 'px', 
	                  left: ($(window).width() - 400) /2 + 'px'
	              }
	          }); 
	      });
	      
	      $('#cancel').click(function() { 
	            $.unblockUI(); 
	            return false; 
	        });
})