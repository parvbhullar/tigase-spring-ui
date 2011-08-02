/**
 * 
 */
$(document).ready( function() {
	$("#demo").jstree({ 
		"themes" : {
	        "theme" : "classic",
	        "dots" : true,
	        "icons" : true 
    	},
    	"json_data" : {
	        "ajax" : {
	            "url" : "menuTree.action"
	       }
    	}
		,
	"plugins" : [ "themes", "json_data" ]
	}).bind("click.jstree", function (event) {
		   var node = $(event.target).closest("li");
		   var data = node.data("jstree");
		   //alert("node.id="+node[0].id+";node="+node[0].getAttribute("alt"));
		   var url="jsp/"+node[0].getAttribute("alt");
		   $.ajax({
				  url: url,
				  success: function(data) {
				    $("#tabs-1").html(data);
				  },
				  complete:function(){
					  
				  }
			});
		   //alert("data="+data);
		   // Do my action
		});
})
