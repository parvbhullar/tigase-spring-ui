<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/jstl_contexpath.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../css/yjstyle.css" rel="stylesheet" type="text/css"/>
<title>南通志愿者网站</title>
<script src="jquery-1.4.2.min.js" type="text/javascript" ></script>
<script type="text/javascript" src="../../../javascript/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="../../../javascript/validate/jquery.validate.js"></script>
<script type="text/javascript" src="../../../javascript/validate/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" src="../../../javascript/validate/additional-methods.js"></script>
<script type="text/javascript" src="admin.js"></script>
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
	  
	  <div class="newsbg3" style="overflow:hidden">
	  <form method="POST" name="form" id="registerForm" action="update.action">
	  	<input type="hidden" 		id="editsexid" 									value='${postulant.sex}' />
		<input type="hidden" 		id="editcredtypeid" 							value='${postulant.credtype}' />
		<input type="hidden" 		id="editeducationid" 							value='${postulant.education}' />
		<input type="hidden" 		id="editintentionid" 		name="intention" 	value='${postulant.intention}' />
		
		<input type="hidden" 		id="editpprofessionid" 		 					value='${postulant.profession}' />
		
		<input type="hidden" 		id="editcommunityidid" 			value='${postulant.communityid}' />
		<input type="hidden" 		id="editorgidid" 			value='${postulant.orgid}' />
	  	<input type="hidden" 		id="id" name="id" 					value='${postulant.id}' />
	  	<input type="hidden" 		name="volunorgid" id="volunorgidid" value='' />
	  	<input type="hidden" 		name="orgname" id="orgnameid" value='' />
	  	
	  	<input type="hidden" 		name="communityname" 	id="communitynameid" value="${postulant.communityname}">
    	<input type="hidden" 		name="orgname" 			id="orgnameid" value="${postulant.orgname}" >
    	<input type="hidden" 		name="street" 			id="com-streetid" value="${street}" >
    	<input type="hidden" 		name="area" 			id="com-areaid" value="${area}" >
    	<input type="hidden" 		name="reg" 			    id="regid" value="${reg}" >
    	<input type="hidden" 		 				id="jnid" value="${postulant.jn}" >
	    <table width="768" height="1255" cellspacing="0" cellpadding="0" border="0" class="table_border">
			  <tbody>
			  <tr>
				<td width="3" height="36">&nbsp;</td>
				<td background="img/trbg.jpg" colspan="2">

						<div > &nbsp; <font size="3"><strong>注册信息</strong></font></div>
				</td>
			  </tr>
			  <tr>
				<td height="53">&nbsp;</td>
				<td valign="top" colspan="2"><br>
				   <table width="759" height="84" cellspacing="0" cellpadding="0" border="0">
					  <tbody><tr>
						<td width="81" height="38" align="right">登陆名称：</td>
						<td width="213" class="font"><label>${postulant.loginName }</label> </td>
						<td width="83" align="right">真实姓名：</td>
						<td width="135" class="font"><input type="text" class="text" size="10" name="name" value="${postulant.name}">&nbsp; &nbsp; <font color="#ff0000">*</font></td>
						<td width="85" align="right">性&nbsp; &nbsp; &nbsp; &nbsp; 别：</td>
						<td width="130" class="font"><input type="radio" value="1" name="sex">男&nbsp; &nbsp; <input type="radio" value="2" name="sex">女&nbsp; &nbsp; <font color="#ff0000">*</font></td>
						<td width="10" align="right">&nbsp;</td>
						<td width="81" class="font"></td>
					  </tr>
					   <tr>
						<td align="right">出生日期：</td>
						<td class="font"><input type="text" class="text" size="10" name="birthday1" value="${postulant.birthday}" onFocus="WdatePicker();" readonly="readonly">&nbsp; &nbsp; <font color="#ff0000">*</font></td>
						<td align="right">&nbsp;</td>
						<td class="font">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
					  
				  </tbody></table>				</td>
			  </tr>
			  <tr>
				<td width="3" height="36">&nbsp;</td>
				<td background="img/trbg.jpg" colspan="2">
					<table width="717" border="0">
					  <tbody><tr>
						<td width="154"> &nbsp; <font size="3"><strong>个人基本信息</strong></font></td>
						<td width="615">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </tbody></table>				</td>
			  </tr>
			 <tr>
				<td height="54">&nbsp;</td>
				<td valign="top" colspan="2"><br>
				   <table width="732" cellspacing="0" cellpadding="0" border="0">
					  <tbody><tr>
						<td width="74" height="38" align="right">证件类型：</td>
						<td width="123" class="font"><select name="credtype" id="credtypeid">
							<c:forEach items="${zjlx}" var="current">
	          				<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      		  			  </c:forEach>
						</select>&nbsp; &nbsp; <font color="#ff0000">*</font></td>
						<td width="87" align="right">证件号码：</td>
						<td width="175" class="font"><input type="text" class="text" size="20" name="credcode" id="credcodeid" onblur="validId(this)" maxlength=18 size=18 value=${postulant.credcode}>&nbsp; &nbsp; <font color="#ff0000">*</font></td>
						<td width="89" align="right">联系电话：</td>
						<td width="184" class="font"><input type="text" class="text" size="20" name="phone" value=${postulant.phone}>&nbsp; &nbsp;</td>
					  </tr>
					   <tr>
						<td height="38" align="right">民&nbsp; &nbsp; &nbsp; &nbsp; 族：</td>
						<td class="font"><select name="nation">
							
						  <c:forEach items="${mz}" var="current">
						  <c:if test="${postulant.nation==current.paramValue}">
													          			<option selected="selected" value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
													          		<c:if test="${postulant.nation!=current.paramValue}">
													          			<option value="<c:out value="${current.paramValue}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
													          		</c:if>
      		  				</c:forEach>
						</select>&nbsp; &nbsp; <font color="#ff0000">*</font></td>
						<td align="right">学&nbsp;&nbsp; &nbsp; 历：</td>
						<td class="font"><select name="education" id="educationid">
						  	<c:forEach items="${xl}" var="current">
        						<option value="<c:out value="${current.paramOrder}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      						</c:forEach>
						</select>&nbsp; &nbsp; <font color="#ff0000">*</font></td>
						<td align="right">&nbsp;</td>
						<td class="font">&nbsp;</td>
					  </tr>
				  </tbody></table>				</td>
		  </tr>
		  
			  <tr>
				<td width="3" height="36">&nbsp;</td>
				<td background="img/trbg.jpg" colspan="2">
					<table width="740" border="0">
					  <tbody><tr>
						<td width="154"> &nbsp; <font size="3"><strong>联系方式</strong></font></td>
						<td width="615">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </tbody></table>				</td>
			  </tr>
			 <tr>
				<td height="54">&nbsp;</td>
				<td valign="top" colspan="2"><br>
				   <table width="100%"  cellspacing="0" cellpadding="0" border="0">
					  <tr>
						<td width="87" height="38" align="right">手&nbsp; &nbsp; &nbsp; &nbsp; 机：</td>
						<td width="209" class="font"><input type="text" class="text" size="20" name="dn" value=${postulant.dn}>&nbsp; &nbsp; <font color="#ff0000">*</font></td>
						<td width="87" height="38" align="right">Email：</td>
						<td class="font"><input type="text" class="text" size="20" name="email" value=${postulant.email}></td>
					  </tr>
				  
				  </table>
				  
				  </td>
		  </tr>
			 <tr>
				<td width="3" height="36">&nbsp;</td>
				<td background="img/trbg.jpg" colspan="2">
					<table width="739" border="0">
					  <tbody><tr>
						<td width="154"> &nbsp; <font size="3"><strong>其他信息</strong></font></td>
						<td width="615">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </tbody></table>				</td>
		  </tr>
			 <tr>
				<td height="54">&nbsp;</td>
				<td valign="top" colspan="2"><br>
				   <table width="596" height="84" cellspacing="0" cellpadding="0" border="0">
					  <tbody><tr>
						<td width="80" height="38" align="right">职业分类：</td>
						<td width="409" class="font" align="left"><select name="profession" id="professionid">
						  <c:forEach items="${zy}" var="current">
        						<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
							</c:forEach>
						</select></td>
					  </tr>
					  <tr >
						<td width="80" align="right" height="38">所在地区：</td>
						<td align="left" colspan="6">
						城市<select name="locationcity" id="cityid"><option value="">请选择</option></select>
			          	区<select name="locationcid" id="cid"><option value="">请选择</option></select>
			          	街道<select name="locationcid" id="streetid"><option value="">请选择</option></select>
			          	社区<select name="communityid" id="communityidid"><option value="">请选择</option></select>
			          &nbsp;&nbsp;&nbsp;&nbsp;
			          </td>
					  </tr>
				  </tbody></table>				</td>
		  </tr>
			   <tr>
				<td width="3" height="36">&nbsp;</td>
				<td background="img/trbg.jpg" colspan="2">
					<table width="752" border="0">
					  <tbody><tr>
						<td width="154"> &nbsp; <font size="3"><strong>志愿服务信息</strong></font></td>
						<td width="">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </tbody></table>				</td>
			  </tr>
			  <tr>
				<td height="65">&nbsp;</td>
				<td width="65" align="center"><font color="#ff0000">志愿服务意向：&nbsp; &nbsp; *</font></td>
			    <td width="630" valign="top">
				     <table width="844" border="0">
					  <tr>
				<td width="200">
				<c:forEach items="${zyzntfwyx}" var="current">
	          		<input type="radio" value="<c:out value="${current.paramValue}"/>" name="vol" id="v<c:out value="${current.paramValue}"/>"  onclick="setintentionid(this.value,this.id)"><c:out value="${current.paramKey}"/><br>
				</c:forEach></td>
				
				<td width="200" ><c:forEach items="${zyzntfwyx}" var="current">
	          		<input type="radio" disabled value="<c:out value="${current.paramValue}"/>" id="i<c:out value="${current.paramValue}"/>" name="inte" alt="<c:out value="${current.paramDesc}"/>" onclick=setvolunorgid(this.value,this.alt)><c:out value="${current.paramDesc}"/><br>
				</c:forEach> 
				</td>
				
				
				<td width="200" style="display:none" id="jntd"><c:forEach items="${jn}" var="current">
	          		<input type="checkbox" value="<c:out value="${current.paramId}"/>" id="jn<c:out value='${current.paramId}'/>" name="jn"><c:out value="${current.paramKey}"/><br>
				</c:forEach> </td>
					  </tr>
				  </table>

				</td>
		      </tr>
			  <tr>
				<td height="140">&nbsp;</td>
				<td valign="top" colspan="2">
				   <table width="717" height="84" cellspacing="0" cellpadding="0" border="0">
					  <tbody><tr>
						<td width="119" height="38" align="right">爱好、特长：</td>
						<td width="713" class="font">
						  <textarea class="text2" rows="2" name="hobby">${postulant.hobby}</textarea>
						</td>
					  </tr>
			      </tbody></table>	
				</td>
			  </tr>
			  <tr>
				<td>&nbsp;</td>
				<td align="center" colspan="2">
				<input type="submit" value="确定" src="img/ok.jpg">
			    <input type="button" value="返回" src="img/back.jpg" onclick="history.go(-1)">
				</td>
			  </tr>
			  <tr>
				<td>&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			  </tr>
	    </tbody></table>
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
</body>
</html>