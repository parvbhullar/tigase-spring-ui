	//设置发送短信的人员ID
	function showsel(){
		var es=document.getElementsByName("sel");
		var out="";
		for(var i=0;i<es.length;i++){
			 if(es[i].checked){
		 		out = out + es[i].value + ","
			 }			 
		}
		if(out != ""){
		 	out = out.substring(0, out.length-1)
		 	return true;
		}else{
			alert("请选择组织！");
			return false;
		}
		$("#sendUuidId").val(out);
	}

	

	/** 手工匹配 */
	function manualSel(pid,order){
		$("#paramid1").val(pid);
		$("#paramorder1").val(order);
		$('#kkk').dialog('open');
	}
	
	$(document).ready(function() {
			// 自动匹配树
			$("#kkk").dialog({
				autoOpen : false,
				height : 450,
				width : 250,
				modal : true,
				buttons : {
					'退 出' : function() {
						$(this).dialog('close');
					},
					'确 认' : function() {
						var es=document.getElementsByName("radio");
						
						var out="";
						var oid;
						for(var i=0;i<es.length;i++)
						{
							
							if(es[i].checked)
							{
								oid=es[i].value;
							}
							//if (es[i].checked&&('u'==es[i].value.substring(es[i].value.length-1))) out+=es[i].value.substring(0,(es[i].value.length-1))+",";
						}
						$.ajax({
							   type: "POST",
							   url: "match.action",
							   data: { "paramid": $("#paramid1").val(),"paramorder":$("#paramorder1").val(),"oid":oid },
							   success: function(data){
								  if(data=='1'){
									  alert('保存组织成功');
								  }
								  
							   }
							 });
						$(this).dialog('close');
					}
				}
			});				

			$(".class_tree").click(function() {
				alert(this.value());
				$('#kkk').dialog('open');
			});
	});