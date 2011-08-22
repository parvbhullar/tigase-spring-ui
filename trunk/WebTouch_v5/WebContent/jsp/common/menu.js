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
		   var url=node[0].getAttribute("alt");
		   $.ajax({
				  url: url,
				  success: function(data) {
					  $("#tabs-1").html("");
					  $("#tabs-1").html(data);
				  }
		   			/*
		   			,
				  complete:function(){
					  
					  var modules = [
					                 { include: true, incfile:'../jsp/common/common.js'}
					             ];
					  jsInclude(modules);
					  
				  }*/
			});
		   //alert("data="+data);
		   // Do my action
		});
})
