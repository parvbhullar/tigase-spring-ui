	//设置发送短信的人员ID
	function showsel(){
		var out="";
		// 选中的末端叶子节点
		$('#manualmatch .jstree-leaf').filter('#manualmatch .jstree-checked').each(function(){
		 	if("" != out){
		 		out = out + "," + this.alt
		 	}else{
		 		out = this.alt
		 	}
	 	})
	 	// 选中的非末端叶子节点
	 	$('#manualmatch .jstree-open').filter('#manualmatch .jstree-checked').each(function(){
		 	if("" != out){
		 		out = out + "," + this.alt
		 	}else{
		 		out = this.alt
		 	}
	 	})
	 
	 	$('#manualmatch .jstree-closed').filter('#manualmatch .jstree-checked').each(function(){
		 	if("" != out){
		 		out = out + "," + this.alt
		 	}else{
		 		out = this.alt
		 	}
	 	})
	 
	 	$('#manualmatch .jstree-undetermined').filter('#manualmatch .jstree-open').each(function(){
		 	if("" != out){
		 		out = out + "," + this.alt
		 	}else{
		 		out = this.alt
		 	}
	 	})
	 
	 	$('#manualmatch .jstree-undetermined').filter('#manualmatch .jstree-closed').each(function(){
		 	if("" != out){
		 		out = out + "," + this.alt
		 	}else{
		 		out = this.alt
		 	}
	 	})
		
		if(out != ""){
		 	$("#intention").val(out);
		 	return true;
		}else{
			alert("请选择组织！");
			return false;
		}		
	}

	function getInputNum() {
		var inputLen = $("#messageId").text().length;
		if (inputLen <= 140) {
			var contentStr = "您还可以输入 <span>" + (140-inputLen) +"</span> 字 &nbsp; &nbsp; &nbsp;";
			$("#inputNumDiv").html(contentStr);
		} else {
			var contentStr = "已超出 <span>" + (inputLen - 140) +"</span> 字 &nbsp; &nbsp; &nbsp;";
			$("#inputNumDiv").html(contentStr);
		}
	}

	/** 手工匹配 */
	function manualSel(actId, intention){
		$("#actIdId").val(actId);
		$("#intention").val(intention);
		$('#manualmatch').dialog('open');
	}
	
	/** 选择组织 按钮*/ 
	function selectOrg(actId){
		$('#currentTree').dialog('open');
	}
	
	function autoSel(actId){
		$('#sendMessage').dialog('open');
	}

	$(document).ready(function() {
			$(".class_del").click(function() {
				return confirm("确认对该活动[" + this.name + "]进行删除操作?");
			});
			// 手工匹配 组织树
			$("#manualmatch").dialog({
				autoOpen : false,
				height : 450,
				width : 250,
				modal : true,
				open:  function(){loadChkTree()},
				buttons : {
					'退 出' : function() {
						$(this).dialog('close');
					},
					'确 认' : function() {
						if(showsel()){							
							$(this).dialog('close');
							$('#sendMessage').dialog('open');
						}
					}
				}
			});				
			// 发送短消息
			$("#sendMessage").dialog({
					autoOpen : false,
					height : 250,
					width : 350,					
					modal : true,
					buttons : {
						'退 出' : function() {
							$(this).dialog('close');
						},
						'确 认' : function() {
								$.ajax({
									type : "POST",
									url : "/zyz/manage/active/handMatch.action",
									data : {
										intention :  $("#intention").val(),
										message : $("#messageId").val(),
										actId : $("#actIdId").val()
									},
									success : function() {
										window.location = "/zyz/manage/active/showactive.action?intention=" + document.getElementById('intention').value + "&selectOrgId=" + document.getElementById('selectOrgId').value
									}
								});
								$(this).dialog('close');
						}
					}
				});
		// 选择组织 匹配树
		$("#currentTree").dialog({
			autoOpen : false,
			height : 450,
			width : 250,
			open:  function(){loadTree()},
			modal : true,
			buttons : {
				'关 闭' : function() {
					$(this).dialog('close');
				}
			}
		});
		});
	/********************************构造 组织树 start***********************************/
	// 手工匹配 组织树
	function loadChkTree(){
		$("#manualmatch").jstree({ 
			"themes" : {
        	"theme" : "default",
        	"dots" : true,
        	"icons" : true 
    	}, 
		"json_data" : {
	        "ajax" : {
	            "url" : "loadChkTree.action?intention=" + document.getElementById('intention').value 
	        }
    	},
		"plugins" : [ "themes", "json_data","checkbox" ],
		"callback" : {       
        	//beforedata默认传id给后台 可以自己根据需要添加参数  
    		// node：节点 tree_obj：tree对象  
       	 	beforedata : function(node,tree_obj){    
                return {id : $(node).attr("id") || 0,flat : $(node).attr("flat")};//进行以异步传参     
        	},  
        	//当点击节点时 如果为叶子节点就执行一些操作  
        	onselect : function(){  
        		/*
            	if($(node).attr("flat")=="gongcheng"){  
                	window.open("http://www.baidu.com");  
            	} 
            	*/ 
        	}  
    	}
		});
	}	
		
	// 选择组织 组织树
	function loadTree(){
		$("#currentTree").jstree({ 
			"themes" : {
		        "theme" : "default",
		        "dots" : true,
		        "icons" : true 
	    	}, 
			"json_data" : {
	        "ajax" : {
	            "url" : "/zyz/manage/active/loadTree.action"
	        }
	    },
		"plugins" : [ "themes", "json_data" ]
		});	
	}
	$("#currentTree .jstree-leaf").live("click", function(e) {
	 	$("#selectedOrgId").val(this.id);
	 	window.location = "/zyz/manage/active/showactive.action?selectOrgId=" + this.id;
		$("#currentTree").dialog('close');
	})
	 
	$("#currentTree  .jstree-closed a").live("click", function(e) {
		$("#selectedOrgId").val($(this).parents('li:first').attr("id"));
		window.location = "/zyz/manage/active/showactive.action?selectOrgId=" + $(this).parents('li:first').attr("id");
		$("#currentTree").dialog('close');
	})
	 	 
	$("#currentTree  .jstree-open a").live("click", function(e) {
		$("#selectedOrgId").val($(this).parents('li:first').attr("id"));
		window.location = "/zyz/manage/active/showactive.action?selectOrgId=" + $(this).parents('li:first').attr("id");
		$("#currentTree").dialog('close');
	})
	/********************************构造 组织树 end***********************************/