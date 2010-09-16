$(document)
		.ready(function() {
			// 添加系统参数
				$("#dialog-addSysparam").dialog( {
					autoOpen : false,
					height : 550,
					width : 550,
					modal : true,
					buttons : {
						'确认' : function() {
							$.ajax( {
								type : "POST",
								url : "create.action",
								data : {
								paramcode 	: 	$("#paramcodeId").val(),
								paramkey 	: 	$("#paramkeyId").val(),
								paramvalue 	:	$("#paramvalueId").val(),
								paramdesc 	: 	$("#paramdescId").val()
								},
								success : function(data) {
									if (data == 1)
										location.reload();
									else
										alert("更新失败!");
								}
							});
							$(this).dialog('close');
						},
						'退出' : function() {
							$(this).dialog('close');
						}
					}
				});
				
				// 编辑系统参数
				$("#dialog-editSysparam").dialog( {
					autoOpen : false,
					height : 550,
					width : 550,
					modal : true,
					buttons : {
						'确认' : function() {
							$.ajax( {
								type : "POST",
								url : "create.action",
								data : {
								paramcode 	: 	$("#editparamcodeId").val(),
								paramkey 	: 	$("#editparamkeyId").val(),
								paramvalue 	:	$("#editparamvalueId").val(),
								paramdesc 	: 	$("#editparamdescId").val()
								},
								success : function(data) {
									if (data == 1)
										location.reload();
									else
										alert("更新失败!");
								}
							});
							$(this).dialog('close');
						},
						'退出' : function() {
							$(this).dialog('close');
						}
					}
				});
		
				//更改状态
			    $("#dialog-auditSysparam").dialog({
					autoOpen: false,
					height: 200,
					width: 350,
					modal: true,
					buttons: {
							'有效': function() {
			    	$.ajax({
						   type: "POST",
						   url: "audit.action",
						   data: { paramid:$("#paramid").val(),paramstate: 1 },
						   success: function(data){
							   if(data==1)
								   location.reload();
							   else
								   alert("更新失败!");
						   }
						 	});
								$(this).dialog('close');
						},
						'无效': function() {
							$.ajax({
								   type: "POST",
								   url: "audit.action",
								   data: { paramid:$("#paramid").val(),paramstate: 0 },
								   success: function(data){
									   if(data==1)
										   location.reload();
									   else
										   alert("更新失败!");
								   }
								 });
							$(this).dialog('close');
					},
						'退出': function() {
							$(this).dialog('close');
						}
					}
				});

				$("#createSysparam").click(function() {
					$('#dialog-addSysparam').dialog('open');
				});
				
				$(".class_audit").click(function(){
					$("#paramid").val($(this).attr("paramid"));
					$('#dialog-auditSysparam').dialog('open');
				});
				
				$(".class_del").click(function(){
					return confirm("确认对该系统参数["+this.name+"]进行删除操作?");
				});
				
				$(".class_edit").click(function(){
					$('#dialog-editSysparam').dialog('open');
				});
		});