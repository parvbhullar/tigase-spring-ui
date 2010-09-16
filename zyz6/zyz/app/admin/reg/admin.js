$(document).ready(function() {
		DoMenu('ChildMenu1');
		$("input[name='sex'][value="+$("#editsexid").val()+"]").attr("checked",true);  
		$("#credtypeid").val($("#editcredtypeid").val());
		$("#educationid").val($("#editeducationid").val());
		$("#professionid").val($("#editpprofessionid").val());
		var id=$("#editintentionid").val();
		$("#v"+id).attr("checked",true);
		$("#v"+id).click();
		$("#i"+id).attr("checked",true);
		$("#i"+id).click();

		/*社区选择联动框开始*/
		//加载市
		$('#cityid').find('option').remove();
		$.ajax({
		   type: "POST",
		   url: "loadCommunity.action?communityid=320600",
		   data: { "cityid": $(this).val() },
		   success: function(data){
			   $("#cityid").append(data);
		   }
		 });
		//加载区
		$('#cid').find('option').remove();
		$('#cid').append("<option value=''>请选择</option>")
		$.ajax({
			   type: "POST",
			   url: "loadSubCommunity.action?communityid=320600",
			   data: { "cid": $(this).val() },
			   success: function(data){
				   $("#cid").append(data);
				   //设置区的值
				   setTimeout(function() {
						$("#cid").val($("#com-areaid").val());
					}, 1);
				   
				   /*加载街道的值开始*/
				   	$('#streetid').find('option').remove();
					$('#streetid').append("<option value=''>请选择</option>")
					$.ajax({
						   type: "POST",
						   url: "loadSubCommunity.action?communityid="+$("#com-areaid").val(),
						   success: function(data){
							   $("#streetid").append(data);
							   setTimeout(function() {
									$("#streetid").val($("#com-streetid").val());
								}, 1);
							   
						   }
					});
				   /*加载街道的值结束*/
				    /*加载社区的值开始*/
				   	$('#communityidid').find('option').remove();
					$('#communityidid').append("<option value=''>请选择</option>")
					$.ajax({
						   type: "POST",
						   url: "loadSubCommunity.action?communityid="+$("#com-streetid").val(),
						   success: function(data){
							   $("#communityidid").append(data);
							   setTimeout(function() {
								   $("#communityidid").val($("#editcommunityidid").val());
								   /*社区选择方法 设置社区名称开始*/
									$('#communityidid').change(function() {
										$("#communitynameid").val($("#communityidid").find("option:selected").text());
									});
									/*社区选择方法 设置社区名称结束*/
								}, 1);
						   }
						 });
				   /*加载社区的值结束*/
			   }
			 });

		/*区*/
		$('#cid').change(function() {
			$('#streetid').find('option').remove();
			$('#streetid').append("<option value=''>请选择</option>")
			
			$.ajax({
				   type: "POST",
				   url: "loadSubCommunity.action?communityid="+this.value,
				   success: function(data){
					   $("#streetid").append(data);
					   
					   $('#communityidid').find('option').remove();
					   $('#communityidid').append("<option value=''>请选择</option>")
				   }
		});
			/*街道选择方法*/
			$('#streetid').change(function() {
				$('#communityidid').find('option').remove();
				$('#communityidid').append("<option value=''>请选择</option>")
				$.ajax({
					   type: "POST",
					   url: "loadSubCommunity.action?communityid="+this.value,
					   success: function(data){
						   $("#communityidid").append(data);
					   }
					 });
			});
		});

		/**验证*/
		$("#registerForm").validate({
	        rules: {      
					name: "required",
				    email: "email",
				    credcode: {
				        required: true,
				        remote:{
				        	url:"checkCredcode.action",
				        	type: "post",
				        	data: {
				        		"id": function() {
				            		return $("#id").val();
				            	}
				    		}
				        }
				   },
				    dn: {
		        required: true,
		        dn:true,
		        remote:{
		        	url:"checkDn.action",
		        	type: "post",
		        	data: {
		        		"id": function() {
		            		return $("#id").val();
		            	}
		    		}
		        }
		    }
	        },
	        messages:{
	        	dn: {remote:"手机号码不能重复!"},
	        	credcode: {remote:"身份证号码不能重复!"}
	        }
	    });
		
		if($("#regid").val()==1)
		{
			alert("注册成功");
		}
	});

	/*设置服务意向*/
	function setintentionid(intentionid,id)
	{
		if(intentionid==4)
		{
			$("#jntd").show();
			if($("#jnid").val().length!=0)
			{
				var arrjn=$("#jnid").val().split(",");
				for(var i=0;i<arrjn.length;i++)
				{
					$("#jn"+(arrjn[i])).attr("checked",true);
				}
			}
		}
		else
		{
			$("#jntd").hide();
			$('input[name="jn"]').attr("checked",false);
			var arrjn=$("#jnid").val().split(",");
			for(var i=0;i<arrjn.length;i++)
			{
				$("#jn"+(arrjn[i])).attr("checked",false);
			}
			$("#jnid").val("");
		}
		$("#editintentionid").val(intentionid);
		$("#i"+id.substring(1,id.length)).attr("checked",true);
		$("#i"+id.substring(1,id.length)).click();
	}

	function setvolunorgid(volunorgid,orgname)
	{
		$("#volunorgidid").val(volunorgid);
		$("#orgnameid").val(orgname);
	}
	
	/*身份证开始*/
	var powers=new Array("7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2");     
	var parityBit=new Array("1","0","X","9","8","7","6","5","4","3","2");     
	var sex="male";     
	//校验身份证号码的主调用     

	function validId(obj){     
	    var _id=obj.value;     
	    if(_id=="")return;     
	    var _valid=false;     
	    if(_id.length==15){     
	        _valid=validId15(_id);     
	    }else if(_id.length==18){     
	        _valid=validId18(_id);     
	    }     
	    if(!_valid){     
	        alert("身份证号码有误,请检查!");     
	        obj.focus();     
	        return;     
	    }     
	    //设置性别     

	    /*
	    var sexSel=document.getElementById("sex");     
	    var options=sexSel.options;     
	    for(var i=0;i<options.length;i++){     
	        if(options[i].value==sex){     
	            options[i].selected=true;     
	            break;     
	        }     
	    } 
	    */    
	}         
	//校验18位的身份证号码     

	function validId18(_id){     
	    _id=_id+"";     
	    var _num=_id.substr(0,17);     
	    var _parityBit=_id.substr(17);     
	    var _power=0;     
	    for(var i=0;i< 17;i++){     
	        //校验每一位的合法性     

	        if(_num.charAt(i)<'0'||_num.charAt(i)>'9'){     
	            return false;     
	            break;     
	        }else{     
	            //加权     

	            _power+=parseInt(_num.charAt(i))*parseInt(powers[i]);     
	            //设置性别     

	            if(i==16&&parseInt(_num.charAt(i))%2==0){     
	                sex="female";     
	            }else{     
	                sex="male";     
	            }     
	        }     
	    }     
	    //取模     

	    var mod=parseInt(_power)%11;     
	    if(parityBit[mod]==_parityBit){     
	        return true;     
	    }     
	    return false;     
	}     
	//校验15位的身份证号码     

	function validId15(_id){     
	    _id=_id+"";     
	    for(var i=0;i<_id.length;i++){     
	        //校验每一位的合法性     

	        if(_id.charAt(i)<'0'||_id.charAt(i)>'9'){     
	            return false;     
	            break;     
	        }     
	    }     
	    var year=_id.substr(6,2);     
	    var month=_id.substr(8,2);     
	    var day=_id.substr(10,2);     
	    var sexBit=_id.substr(14);     
	    //校验年份位     

	    if(year<'01'||year >'90')return false;     
	    //校验月份     

	    if(month<'01'||month >'12')return false;     
	    //校验日     

	    if(day<'01'||day >'31')return false;     
	    //设置性别     

	    if(sexBit%2==0){     
	        sex="female";     
	    }else{     
	        sex="male";     
	    }     
	    return true;     
	}
	/*身份证结束*/