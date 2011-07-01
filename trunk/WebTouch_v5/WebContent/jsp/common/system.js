/**
 * 
 */
$(document).ready( function() {
	$.ajax({
		  url: 'jsp/common/common.jsp',
		  success: function(data) {
		    $("#tabs-1").html(data);
		  },
		  complete:function(){
		  }
	});
	
	
})
