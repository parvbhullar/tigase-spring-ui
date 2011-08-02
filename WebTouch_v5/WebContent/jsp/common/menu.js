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
		   console.info("node="+node);
		   // Do my action
		});
})
