/**
 * 
 */
$(document).ready( function() {		
	/*表格初始化开始*/
			
	      $("#listTable").jqGrid({ 
	  		url:'mail/mailReceive.action', 
	  		datatype: 'json', 
	  		mtype: 'post', 
	  		colNames:['id','姓名','包仓费','标签费', '仓储费','打托费','加固费'], 
	  		colModel :[  
	  		    {name:'id', 		index:'id', 			hidden:true},
	  		    {name:'state', 		index:'state' 			},
	  			{name:'sendname', 	index:'sendname' 		},  
	  			{name:'subject', 	index:'subject' 		},  
	  			{name:'datetime', 	index:'datetime' 		}, 
	  			{name:'filename',	index:'filename' 		},
	  			{name:'name',		index:'name', 			hidden:true}
	  			], 
	  		pager: '#page', 
	  		rowNum:10,
	  		autowidth:true,
	  		height:320,
	  		loadtext: '正在加载...',
	  		loadui : 'block',
	  		multiselect : true,
	  		rowList:[10], 
	  		sortname: 'id', 
	  		sortorder: "desc",
	  		recordtext: "显示记录从{0}到{1},总数 {2} 条",		//显示记录数的格式
	  		emptyrecords: "无数据",							//空记录时的提示信息
	  		pgtext : "第 {0}页 总页数 {1}",					//页数显示格式
	  		jsonReader:{
	              repeatitems : false
	      	},
	  		viewrecords: true 
	   	 });
	      /*表格初始化结束*/
	      
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
	      
	      var singleCheckNode;
	      
	      $('#dept').click(function() { 
	    	  $( "#depttree" ).dialog( "open" );
	    	  $("#depttree").show();
	    	  //console.info("open");
	    	  $("#depttree")
	    		.jstree({ 
	    			"plugins" : [ 
	    				"themes","json_data","checkbox","ui"
	    			],
	    			"json_data" : { 
	    				"ajax" : {
	    					"url" : "menuTreeTest.action",
	    					"data" : function (n) { 
	    						return { 
	    							"operation" : "get_children", 
	    							"id" : n.attr ? n.attr("id") : "0102" 
	    						}; 
	    					}
	    				}
	    			},
	    		"themes" : {
	    	        "theme" : "classic",
	    	        "dots" : true,
	    	        "icons" : true 
	    		},
	    		checkbox : { "two_state" : true }
	    		})
	    		.bind("change_state.jstree", function(e, data){
	    			if(data.inst.get_checked().length==1){ 
	    				singleCheckNode=data.rslt[0]; 
	            }
	    	        if(data.inst.get_checked().length>1){ 
	                    data.inst.uncheck_node(singleCheckNode);
	                    singleCheckNode=data.rslt[0];
	            }})
	            .bind("loaded.jstree", function(event, data) {
	            	$('.jstree-last').find('ins.jstree-checkbox').hide();
	            	$('.jstree-closed').find('ins.jstree-checkbox').hide();
				})
	            ;
	        });
	      $( "#depttree" ).dialog({autoOpen: false,modal: true});
})