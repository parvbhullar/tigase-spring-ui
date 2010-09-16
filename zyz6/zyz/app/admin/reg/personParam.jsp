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
 font-weight:strong;   
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
	    <form method="POST" name="form" id="form" action="updatePassword.action">
	  	<table width="100%">
	  		<tr>
				<td  height="36">&nbsp;</td>
				<td background="img/trbg.jpg" colspan="2">
					<table width="444" border="0">
					  <tbody><tr>
						<td width="154"> &nbsp; <font size="3"><strong>个人参数</strong></font></td>
					  </tr>
				  </tbody></table>				</td>
			  </tr>
	  		<tr>
				<td  align="center" height="48" colspan="2">您所拥有的时间储蓄是:<strong><font color=red size=5>${postulant.communityid}</font></strong>小时,转换为积分是:<strong><font color=red size=5>${postulant.orgid}</font></strong></td>
			</tr>
	  		<tr>
				<td  align="right" height="48">是否接收短信：</td>
				<td class="font">
				<c:if test="${(postulant.servetimes) ==1 }">
								<input type="radio" value="1" checked="checked" name="servetimes">接收
								<input type="radio" value="0" name="servetimes">不接收
				</c:if>
				<c:if test="${(postulant.servetimes) ==0 }">
								<input type="radio" value="1" name="servetimes">接收
								<input type="radio" value="0" checked="checked"  name="servetimes">不接收
				</c:if>
				
			</td></tr>
			
			<tr>
				<c:if test="${(postulant.isverify) ==1 }">
				<td  align="right" height="48">服&nbsp;务&nbsp;状&nbsp;态：</td>
				<td class="font">
								<input type="radio" value="1" checked="checked" name="isverify">正常服务
								<input type="radio" value="6" name="isverify">暂停服务
				</td>								
				</c:if>
				<c:if test="${(postulant.isverify) ==6 }">
				<td  align="right" height="48">服&nbsp;务&nbsp;状&nbsp;态：</td>
				<td class="font">
								<input type="radio" value="1" name="isverify">正常服务
								<input type="radio" value="6" checked="checked"  name="isverify">暂停服务
				</td>
				</c:if>				
			</tr>
			<tr><td colspan="2" align="center">
			<input type="button" id="btn" value="修改">&nbsp; &nbsp; &nbsp; &nbsp; 
			<input 	type="button" value="返回" onclick="history.go(-1)" ></td>
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
			$.ajax({
				   type: "POST",
				   url: "updatePersonParam.action",
				   data: "servetimes="+$('input:radio[name=servetimes]:checked').val()+"&isverify="+$('input:radio[name=isverify]:checked').val(),
				   success: function(msg){
				   		if(msg==1)
				   		{
					   		alert("修改个人参数成功");
					   	}
				   		else
				   		{
				   			alert("修改个人参数失败");
					   	}
				   		
				   }
				 });
				});
	});

	
</script>
    
</body>
</html>