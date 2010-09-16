<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/jstl_contexpath.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../../../javascript/My97DatePicker/WdatePicker.js"></script>
<script src="jquery-1.4.2.min.js" type="text/javascript" >
</script>
<script type="text/javascript" src="../../../javascript/validate/jquery.validate.js"></script>
<script type="text/javascript" src="../../../javascript/validate/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" src="../../../javascript/validate/additional-methods.js"></script>
<script type="text/javascript" src="reg2.js"></script>
<title>南通志愿者网站</title>
</head>
<body background="img/bg.jpg">

<div class="doc">

	<!--头开始-->
	<div class="head">
      <div class="top_bg">
        <div  class="home"> <img  src="img/home.jpg" />&nbsp;&nbsp;<a onclick="this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.ntzyz.org.cn');" href="#">设为首页</a>&nbsp; &nbsp; <img src="img/mail.jpg" />&nbsp;&nbsp;<a href="javascript:window.external.AddFavorite(location.href, document.title);">加入收藏</a> &nbsp; &nbsp; <img src="img/connet.jpg"/>&nbsp;&nbsp;<a href="../../index.jsp">回到首页</a>&nbsp;&nbsp; </div>
        <div class="searchinput"> <span class="keywordsinput">
          <marquee scrollamount="3" direction="right">
            欢迎您来到南通志愿者服务网！
        </marquee>
        </span> </div>
      </div>
	  <div><img src="../img/bannar2.jpg"></div>
  </div>

	<!--头结束-->

    <!--表格开始-->
    <form method="POST" name="form" id="registerForm" action="create.action">
    <input type="hidden" 		name="orgid" id="volunorgidid">
    <input type="hidden" 		name="intention" id="intentionidid">
    <input type="hidden" 		name="communityname" 	id="communitynameid" value="">
    <input type="hidden" 		name="orgname" 			id="orgnameid" value="" >
    
    <input type="hidden" value="${org.orgName}" name="volunorgname">
    <input type="hidden" value="" id="uuid">
	
	
	<div  class="table">
		<div class="tabg">
			<div  class="tatitle">
			<strong> 南通志愿服务者登记表</strong>
		</div>
		</div>
	    <div class="table_wz">
			<table width="999" height="1255" border="0" cellpadding="0" cellspacing="0" class="table_border">
			  <tr>
				<td width="28" height="36">&nbsp;</td>
				<td colspan="2" background="img/trbg.jpg">
					<table width="844" border="0">
					  <tr>
						<td width="102"> &nbsp; <font size="3"><strong>注册信息</strong></font></td>
						<td width="667">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </table>				  </td>
			  </tr>
			  <tr>
				<td height="173">&nbsp;</td>
				<td colspan="2" valign="top"><br />
				   <table width="912" height="84" border="0" cellpadding="0" cellspacing="0">
					  <tr >
						<td width="81" height="38" align="right">登陆名称：</td>
						<td width="213" class="font" ><input name="loginName" id="loginNameid" type="text"  class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td width="83" align="right">真实姓名：</td>
						<td width="135" class="font"><input name="name" type="text" size="10"   class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td width="85" align="right">性&nbsp; &nbsp; &nbsp; &nbsp; 别：</td>
						<td width="130" class="font"><input name="sex" type="radio" value="1" checked/>男&nbsp; &nbsp; <input name="sex" type="radio" value="2" />女&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td width="71" align="right">出生日期：</td>
						<td width="114" class="font"><input name="birthday1" type="text" size="10"   class="text" onFocus="WdatePicker();"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
					  </tr>
					   <tr >
						<td  align="right" height="38">密&nbsp; &nbsp; &nbsp; &nbsp; 码：</td>
						<td class="font"><input name="password" type="password" id="passwordid" class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td align="right">&nbsp;</td>
						<td class="font">&nbsp;</td>
						<td align="right">&nbsp;</td>
						<td class="font">&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						<td  align="right" height="38">确认密码：</td>
						<td class="font"><input name="cfmpwd" type="password"  class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
				  </table>				</td>
			  </tr>
			  <tr>
				<td width="28" height="36">&nbsp;</td>
				<td colspan="2" background="img/trbg.jpg">
					<table width="844" border="0">
					  <tr>
						<td width="154"> &nbsp; <font size="3"><strong>个人基本信息</strong></font></td>
						<td width="615">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </table>				</td>
			  </tr>
			 <tr>
				<td height="140">&nbsp;</td>
				<td colspan="2" valign="top"><br />
				   <table width="912" height="84" border="0" cellpadding="0" cellspacing="0">
					  <tr >
						<td width="87" height="38" align="right">证件类型：</td>
						<td width="209" class="font" ><select name="credtype" id="credtypeid">
						  <c:forEach items="${zjlx}" var="current">
	          				<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      		  			  </c:forEach>
						</select>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td width="80" align="right">证件号码：</td>
						<td width="238" class="font"><input name="credcode" type="text" size="20" id="credcodeid"   onblur="validId(this)" maxlength=18 size=18 class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td width="79" align="right">联系电话：</td>
						<td width="219" class="font"><input name="phone" type="text" size="20"   class="text"/>&nbsp; &nbsp; </td>
					  </tr>
					   <tr >
						<td  align="right" height="38">民&nbsp; &nbsp; &nbsp; &nbsp; 族：</td>
						<td class="font"><select name="nation">
	          				<c:forEach items="${mz}" var="current">
        						<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      		  				</c:forEach>
						</select>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td align="right">学&nbsp; &nbsp; &nbsp; &nbsp; 历：</td>
						<td class="font">
						<select name="education">
			          	<c:forEach items="${xl}" var="current">
        					<option value="<c:out value="${current.paramOrder}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      					</c:forEach>
	          </select>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td align="right">&nbsp;</td>
						<td class="font">&nbsp;</td>
					  </tr>
				  </table>				</td>
			  </tr>
			  <tr>
				<td width="28" height="36">&nbsp;</td>
				<td colspan="2" background="img/trbg.jpg">
					<table width="844" border="0">
					  <tr>
						<td width="154"> &nbsp; <font size="3"><strong>联系方式</strong></font></td>
						<td width="615">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </table>				</td>
			  </tr>
			 <tr>
				<td height="140">&nbsp;</td>
				<td colspan="2" valign="top"><br />
				   <table width="296" height="84" border="0" cellpadding="0" cellspacing="0">
					  <tr >
						<td width="87" height="38" align="right">手&nbsp; &nbsp; &nbsp; &nbsp; 机：</td>
						<td width="209" class="font" ><input name="dn" type="text" size="20"   class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
					  </tr>
					   <tr >
						<td  align="right" height="38">Email：</td>
						<td class="font"><input name="email" type="text" size="20"   class="text"/></td>
					  </tr>
				  </table>				</td>
			  </tr>
			 <tr>
				<td width="28" height="36">&nbsp;</td>
				<td colspan="2" background="img/trbg.jpg">
					<table width="844" border="0">
					  <tr>
						<td width="154"> &nbsp; <font size="3"><strong>其他信息</strong></font></td>
						<td width="615">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </table>				</td>
			  </tr>
			 <tr>
				<td height="140">&nbsp;</td>
				<td colspan="4" valign="top"><br />
				   <table width="796" height="84" border="0" cellpadding="0" cellspacing="0">
					  <tr >
						<td width="87" height="38" align="right">职业分类：</td>
						<td width="209" class="font" ><select name="profession">
	          	<c:forEach items="${zy}" var="current">
        			<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
				</c:forEach>
	         </select></td>
					  </tr>
					   <tr >
						<td  align="right" height="38">所在地区：</td>
						<td align="left" colspan="6">
						城市<select name="locationcity" id="cityid"><option value="">请选择</option></select>
			          	&nbsp;区&nbsp;&nbsp;<select name="locationcid" id="cid" style="width:120px;"><option value="">请选择</option></select>
			          	&nbsp;街道&nbsp;&nbsp;<select name="locationcid" id="streetid" style="width:120px;"><option value="">请选择</option></select>
			          	&nbsp;社区&nbsp;&nbsp;<select name="communityid" id="comid" style="width:150px;"><option value="">请选择</option></select>
&nbsp;&nbsp;&nbsp;&nbsp;			          </td>
					  </tr>
				  </table>				</td>
			  </tr>
			  
			   <tr>
				<td width="28" height="36">&nbsp;</td>
				<td colspan="2" background="img/trbg.jpg">
					<table width="844" border="0">
					  <tr>
						<td width="154"> &nbsp; <font size="3"><strong>志愿服务信息</strong></font></td>
						<td width="615">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </table>				</td>
			  </tr>
			  <tr>
				<td height="165">&nbsp;</td>
				<td width="165" align="center"><font color="#FF0000">志愿服务意向：&nbsp; &nbsp; *</font></td>
			    <td width="804" valign="top">
			    <table width="844" border="0">
					  <tr>
				<td width="200">
				<c:forEach items="${zyzntfwyx}" var="current">
	          		<input type="radio" value="<c:out value="${current.paramValue}"/>" name="vol" id="v<c:out value="${current.paramValue}"/>"  onclick="setintentionid(this.value,this.id)"><c:out value="${current.paramKey}"/><br>
				</c:forEach></td>
				
				<td width="200" ><c:forEach items="${zyzntfwyx}" var="current">
	          		<input type="radio" disabled value="<c:out value="${current.paramValue}"/>" id="i<c:out value="${current.paramValue}"/>" name="inte" alt="<c:out value="${current.paramDesc}"/>" onclick=setvolunorgid(this.value,this.alt)><c:out value="${current.paramDesc}"/><br>
				</c:forEach>				</td>
				<td width="200" style="display:none" id="jntd"><c:forEach items="${jn}" var="current">
	          		<input type="checkbox" value="<c:out value="${current.paramId}"/>" name="jn""><strong><font color=red><c:out value="${current.paramKey}"/></font></strong><br>
				</c:forEach> </td>
					  </tr>
				  </table>				</td>
		      </tr>
			  <tr>
				<td height="140">&nbsp;</td>
				<td colspan="2" valign="top">
				   <table width="832" height="84" border="0" cellpadding="0" cellspacing="0">
					  <tr >
						<td width="119" height="38" align="right">爱好、特长：</td>
						<td width="713" class="font" >
						  <textarea name="hobby" rows="5" class="text2"></textarea>						</td>
					  </tr>
			      </table>				</td>
			  </tr>
			  <tr>
				<td>&nbsp;</td>
				<td colspan="2" align="center">
				<input type="button" value="确定" src="img/ok.jpg" onclick="register()"/>
			    <input type="button" value="返回" src="img/back.jpg" onclick="history.go(-1)">				</td>
			  </tr>
			  <tr>
				<td>&nbsp;</td>
				<td colspan="2">&nbsp;</td>
			  </tr>
		  </table>
		</div>
		
		<div  class="bottom">
			<div  class="line">&nbsp;	</div>
			<div class="logo"  >
			  <img src="img/LOGO.jpg" width="90" height="83" />			</div>
			<div class="bottom_font">

			Copyright ©ntzyz.org.cn All Right Reserved.<br /><br />
			版权所有：南通市精神文明建设指导委员会                 技术支持：中国移动通信集团江苏有限公司</div>
		</div>
    </div>
    </form>
	<!--表格结束-->
    </div>
</body>
</html>