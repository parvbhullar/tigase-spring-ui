function register()
{
	if($("#comid").val().length==0)
	{
		alert("请选择社区");
		return false;
	}
	if($("#volunorgidid").val().length==0)
	{
		alert("服务意向");
		return false;
	}
	$("#registerForm").submit();
}

function setvolunorgid(volunorgid,orgname)
{
	$("#volunorgidid").val(volunorgid);
	$("#orgnameid").val(orgname);
}

function setintentionid(intentionid,id)
{
	//如果是民政局管的"社区志愿服务" 加技能
	if(intentionid==4)
	{
		$("#jntd").show();
	}
	else
	{
		$("#jntd").hide();
		$('input[name="jn"]').attr("checked",false);
	}
	
	$("#intentionidid").val(intentionid);
	$("#i"+id.substring(1,id.length)).attr("checked",true);
	$("#i"+id.substring(1,id.length)).click();
}


$(document).ready(function() {
	
	$('#cityid').find('option').remove();
	$.ajax({
		   type: "POST",
		   url: "loadCommunity.action?communityid=320600",
		   data: { "cityid": $(this).val() },
		   success: function(data){
			   $("#cityid").append(data);
		   }
		 });
	
	$('#cid').find('option').remove();
	$('#cid').append("<option value=''>请选择</option>")
	$.ajax({
		   type: "POST",
		   url: "loadSubCommunity.action?communityid=320600",
		   data: { "cid": $(this).val() },
		   success: function(data){
			   $("#cid").append(data);
		   }
		 });

	$('#cid').change(function() {
		$('#streetid').find('option').remove();
		$('#comid').find('option').remove();		//删除社区的下拉框
		$('#streetid').append("<option value=''>请选择</option>")
		$('#comid').append("<option value=''>请选择</option>")
		
		$.ajax({
			   type: "POST",
			   url: "loadSubCommunity.action?communityid="+this.value,
			   success: function(data){
				   $("#streetid").append(data);
			   }
			 });
	});
	
	/*街道选择方法*/
	$('#streetid').change(function() {
		$('#comid').find('option').remove();
		$('#comid').append("<option value=''>请选择</option>")
		
		$.ajax({
			   type: "POST",
			   url: "loadSubCommunity.action?communityid="+this.value,
			   success: function(data){
					//alert(data)
				   $("#comid").append(data);
			   }
			 });
	});
	
	/*社区选择方法*/
	$('#comid').change(function() {
		//alert($("#comid").find("option:selected").text());   
		//$("#communitynameid").val($("#comid option[selected]").text());
		$("#communitynameid").val($("#comid").find("option:selected").text());
	});
	
	$("#registerForm").validate({
        rules: {      
			loginName: {
	        required: true,
	        remote:{
	        	url:"checkLoginname.action",
	        	type: "post",
	        	data: {
	        		"loginName": function() {
	            		return $("#loginNameid").val();
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
			        		"uuid": function() {
			            		return $("#uuid").val();
			            	}
			    		}
			        }
			   },
			   credcode: {
			        required: true,
			        remote:{
			        	url:"checkCredcode.action",
			        	type: "post",
			        	data: {
			        		"id": function() {
			            		return $("#uuid").val();
			            	}
			    		}
			        }
			   },
			    email: "email",
			    password: "required",
				cfmpwd: {
				      equalTo: "#passwordid"
				    }
        },
        messages:{
        		loginName: {remote:"登录名不能重复!"},
	        	dn: {remote:"手机号码不能重复!"},
	        	credcode: {remote:"身份证号码不能重复!"}
	    }
    });
	
	/*
	$("#registerForm").validate({
        rules: {       
		loginName: {
	        required: true,
	        remote:{
	        	url:"checkloginName.action",
	        	type: "post",
	        	data: {
	        		"loginName": function() {
	            		return $("#loginNameid").val();
	            	}
	          	}                          
	        }    
	    	},
			name: "required",
			password: "required",
			cfmpwd: {
			      equalTo: "#passwordid"
			    },
			dn: {
			        required: true,
			        dn:true,
			        remote:{
			        	url:"checkDn.action",
			        	type: "post",
			        	data: {
			        		"uuid": function() {
			            		return $("#uuid").val();
			            	}
			    		}
			        }
			    },
			    email: "email"
        },
        messages:{
	        	loginName: {remote:"登录名不能重复!"},
	        	dn: {remote:"手机号码不能重复!"}
	    }
    });
    */
});

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