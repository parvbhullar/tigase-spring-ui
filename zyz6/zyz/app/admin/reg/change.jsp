<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/jstl_contexpath.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/yjstyle.css" rel="stylesheet" type="text/css"/>
<title>南通志愿者网站</title>
<script src="jquery-1.4.2.min.js" type="text/javascript" >
<script type="text/javascript" src="../../../javascript/My97DatePicker/WdatePicker.js"></script>

<script type=text/javascript>  

<!--   
var LastLeftID = "";   
  
function DoMenu(emid){
    var obj = document.getElementById(emid);    
    obj.className = (obj.className.toLowerCase() == "expanded"?"collapsed":"expanded");   
    if((LastLeftID!="")&&(emid!=LastLeftID)){   
        document.getElementById(LastLeftID).className = "collapsed";   
    }   
        LastLeftID = emid;   
}   
-->  

</script> 

<style type="text/css">  
<!--   
ul,li,a{margin:0;padding:0;}      
  
#nav {   
 width:180px; 
 list-style-type: none;   
 text-align:left;   
 margin:10px 0 0 3px;   
}   
  
#nav a {   
 width: 180px;    
 display: block;   
 padding:5px 0 8px 22px;   
}   
  
#nav li {   
 background:url(../img/menu_1.jpg); 
 border-bottom:#ffffff 1px solid;   
 float:left;   
}   
  
#nav li a:hover{   
 
 background:url(../img/menu_2.jpg);   
}   
  
#nav a:link  {   
 color:#666;   
 text-decoration:none;   
}   
 
 
#nav a:visited  {   
 color:#666;   
 text-decoration:none;   
}   
  
#nav a:hover  {   
 color:#000;   
 text-decoration:none;   
 font-weight:bold;   
}   
  
#nav li ul {   
 list-style:none;   
 text-align:left;   
 padding:0;   
}   
  
#nav li ul li{            
background:#f5f4f3;    
 
}                           /*二级菜单底色*/  
     
#nav li ul a{   
 padding-left:22px;   
 width:180px;   
}   
  
#nav li ul a:link  {   
 color:#666;   
 text-decoration:none;   
}   
#nav li ul a:visited  {   
 color:#666;   
 text-decoration:none;   
}   
#nav li ul a:hover {   
 color:#ff0000;   
 text-decoration:none;   
 font-weight:normal;   
 background:#d3d1d1;   
}   
  
#nav li:hover ul {   
 left: auto;   
}   
#nav li.sfhover ul {   
 left: auto;   
}   
#content {   
 clear: left;    
}   
#nav ul.collapsed {   
 display: none;   
}   
-->  
</style>   
</head>
<body background="../img/bg.jpg">
<div class="doc">

	<!--头开始-->
	<div class="head">
	  <div class="top_bg">
				<div  class="home">
					<img  src="img/home.jpg" />&nbsp;&nbsp;<a onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.ntzyz.org.cn');" href="#">设为首页</a>&nbsp; &nbsp;
					<img src="img/mail.jpg" />&nbsp;&nbsp;<a href="javascript:window.external.AddFavorite(location.href, document.title);">加入收藏</a> &nbsp; &nbsp;
					
			        <img src="img/connet.jpg"/>&nbsp;&nbsp;<a href="../../index.jsp">回到首页</a>&nbsp;&nbsp;	
				</div>
				<div class="searchinput">
				  <span class="keywordsinput">
						<marquee scrollamount="3" direction="right">欢迎您来到南通志愿者服务网！</marquee>  </span>		  
				</div>
	  </div>
		 <div><img src="../img/bannar2.jpg"></div>
		 <div style="background: none repeat scroll 0% 0% rgb(255, 0, 0); width: 1003px;">&nbsp;</div>	
    </div>
</div>
	<!--头结束-->

    <!--表格开始-->
	<div style="background: none repeat scroll 0% 0% rgb(255, 255, 255);" class="contect">
	  <div class="menulist" style="float:left">
	     <ul style="padding-top: 20px;" id="nav">  
			<li><a onClick="DoMenu('ChildMenu1')" href="#Menu=ChildMenu1">&nbsp; &nbsp; &nbsp;个人面板</a>  
				 <ul class="collapsed" id="ChildMenu1">  
					 <li><a href="edit.action">&nbsp; &nbsp; &nbsp;个人资料</a></li>  
					 <li><a href="personParam.action">&nbsp; &nbsp; &nbsp;个人参数</a></li>
					 <li><a href="change.action">&nbsp; &nbsp; &nbsp;修改密码</a></li>  
					 <li><a href="showactive.action">&nbsp; &nbsp; &nbsp;参加的活动</a></li>  
					 <li><a href="showrecord.action">&nbsp; &nbsp; &nbsp;工作记录</a></li>  
				 	 <li><a href="logout.action">&nbsp; &nbsp; &nbsp;退出志愿者</a></li> 
				 </ul>  
			</li>  
		</ul>  
	  </div>
	  
	  <div class="newsbg3" >
	  <div style="padding-top:10px;"><img src="img/mmxg.gif" width="642" height="38" /></div>
	    <form method="POST" name="form" id="form" action="updatePassword.action">
	  	<table>
	  		<tr>
			<td  align="right" height="38">原 密 码：</td>
			<td class="font"><input name="oldpassword" type="password" id="oldpasswordid" class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td></tr>
	  		<tr>
				<td  align="right" height="38">密&nbsp; &nbsp; 码：</td>
				<td class="font"><input name="password" type="password" id="passwordid" class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font>
			</td></tr>
			
			<tr>
				<td  align="right" height="38">确认密码：</td>
				<td class="font"><input name="cfmpwd" type="password"  class="text" id="cfmpwdid"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
			</tr>
			<tr><td colspan="2" align="center"><input type="button" id="btn" value="修改">&nbsp; &nbsp; &nbsp; &nbsp; <input type="reset" value="重置" ></td>
				
			</tr>
	  	</table>
	    </form>
	  </div>
		 
		<div style="clear: both; height: 0px; font-size: 0px;"></div>
		
	    <div class="bottom">
			<div class="line">&nbsp;	</div>
			<div class="logo">
			  <img width="90" height="83" src="../img/LOGO.jpg">			</div>
			<div class="bottom_font">

			Copyright &copy;ntzyz.org.cn All Right Reserved.<br><br>
			版权所有：南通市精神文明建设指导委员会                 技术支持：中国移动通信集团江苏有限公司</div>
		</div>
    </div>
	<!--表格结束-->
	<script type="text/javascript">
	$(document).ready(function() {
		DoMenu('ChildMenu1');
		$("#btn").click(function(){
			if($("#oldpasswordid").val().length==0)
			{
				alert("请输入原密码");
				return false;
			}
			if($("#passwordid").val()!=$("#cfmpwdid").val())
			{
				alert("请输入密码一致");
				return false;
			}
			if($("#passwordid").val().length==0)
			{
				alert("请输入新密码");
				return false;
			}
			if($("#cfmpwdid").val().length==0)
			{
				alert("请再次输入新密码");
				return false;
			}
			
			$.ajax({
				   type: "POST",
				   url: "checkPassword.action",
				   data: "oldpassword="+$("#oldpasswordid").val(),
				   success: function(msg){
				   	if(msg=='false')
				   	{
				   		alert( "原密码不对");
				   		return false;
					}
				   	else
				   	{
				   		$("#form").submit();
					}
				   	
				   }
				 });
				});
	});

	function setintentionid(intentionid,id)
	{
		if(intentionid==400000)
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

	function setvolunorgid(volunorgid,orgname)
	{
		$("#volunorgidid").val(volunorgid);
		$("#orgnameid").val(orgname);
	}
</script>
    
</body>
</html>