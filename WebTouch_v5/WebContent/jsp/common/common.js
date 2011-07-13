/**
 * 
 */
$(document).ready( function() {		      
	      $("#listTable").jqGrid({ 
	  		url:'mail/mailReceive.action', 
	  		datatype: 'json', 
	  		mtype: 'post', 
	  		colNames:['id','材料费','包仓费','标签费', '仓储费','打托费','加固费'], 
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
})
