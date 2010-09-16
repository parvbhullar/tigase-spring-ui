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
					<img  src="img/home.jpg" />&nbsp;&nbsp;<a onClick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.ntzyz.org.cn');" href="#">设为首页</a>&nbsp; &nbsp;
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
	  
	  <div class="newsbg3" style="overflow:hidden">
	     <table width="100%"  cellspacing="0" border="0" class="table2_border">
			  <tbody><tr>
						<td height="56" background="img/bg3.jpg" align="center" colspan="5"><font color="#ff3333" size="3"><strong>工作记录</strong></font></td>
			  </tr>
			  <tr align="center">
				<td height="26" width="53" bgcolor="#fbe8cf">序号</td>
				<td width="154" bgcolor="#fbe8cf">名称</td>
				<td width="157" bgcolor="#fbe8cf">开始时间</td>
				<td width="129" bgcolor="#fbe8cf">结束时间</td>
				<td width="129" bgcolor="#fbe8cf">状态</td>
			  </tr>
			  <c:forEach var="active" items="${activeList}" varStatus="status">
			  <tr align="center">
			  	<td height="26" align="center">${status.count }</td>
				<td align="center">${active.acttitle }</td>
				<td align="center">${active.startdate }</td>
				<td align="center">${active.enddate }</td>
				<td>
					&nbsp;
					<c:if test="${active.actstatus==0}">
						进行中
					</c:if>
					<c:if test="${active.actstatus==1}">
						结束
					</c:if>
				</td>

			  </tr>
			  </c:forEach>
			  <tr>
				<td colspan=5 align="right">${toolNav}</td>
			</tr>
	    </tbody></table>
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
		$("input[name='sex'][value="+$("#editsexid").val()+"]").attr("checked",true);  
		$("#credtypeid").val($("#editcredtypeid").val());
		$("#educationid").val($("#editeducationid").val());
		$("#professionid").val($("#editpprofessionid").val());
		var id=$("#editintentionid").val();
		$("#v"+id).attr("checked",true);
		$("#v"+id).click();
		$("#i"+id).attr("checked",true);
		$("#i"+id).click();
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