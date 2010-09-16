<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/jstl_contexpath.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../../../javascript/My97DatePicker/WdatePicker.js"></script>
<script src="jquery-min.js" type="text/javascript" >
</script>
<script type="text/javascript" src="../../../javascript/validate/jquery.validate.js"></script>
<script type="text/javascript" src="../../../javascript/validate/messages_cn.js" type="text/javascript"></script>
<script type="text/javascript" src="../../../javascript/validate/additional-methods.js"></script>
<script type="text/javascript">
function changeCity(areaid)
{
	alert(areaid)
	$.ajax({
		   type: "POST",
		   url: "load.action",
		   data: { "areaid": areaid },
		   success: function(msg){
		     alert( "Data Saved: " + msg );
		   }
		 });
}
var arrayAreaCity = new Array();
<c:forEach items="${orgarea}" var="current">
	<c:if test="${current.areaParentId==1}">
		arrayAreaCity[arrayAreaCity.length]='<option value='+<c:out value="${current.areaId}"/>+' label=<c:out value="${current.areaName}"/>><c:out value="${current.areaName}"/></option>';
	</c:if>
</c:forEach>
</script>
<script type="text/javascript" src="reg2.js"></script>
<title>南通志愿者网站</title>
</head>
<body background="img/bg.jpg">

<div class="doc">

	<!--头开始-->
	<div class="head">
		 <div class="top_bg">
				<div  class="home">
					<img src="img/home.jpg" />&nbsp;&nbsp;设为首页&nbsp; &nbsp;
					<img src="img/mail.jpg" />&nbsp;&nbsp;加入收藏 &nbsp; &nbsp;
			        <img src="img/connet.jpg"/>&nbsp;&nbsp;<a href="../../index.jsp">回到首页</a>&nbsp;&nbsp;
				</div>
				<div class="searchinput">
				  <span class="keywordsinput">
						用户名:&nbsp; &nbsp;<input type="text" name="keywords"   size="10" class="text" />&nbsp; &nbsp;
						密码:&nbsp; &nbsp;<input type="text" name="keywords"  class="text" size="10"/>
				  </span>
				  <a href="#" ><img src="img/login.jpg" border="0" /></a>&nbsp; &nbsp;<a href="#"><img src="img/rel.jpg" border="0" /></a>&nbsp; &nbsp;<img src="img/why.jpg" />忘记密码		        </div>
				<div class="apply">
				     <a target="_blank" href="/zyz/app/admin/reg/register2.action"><strong>申请注册志愿者</strong></a>
	       </div>
		</div>
		
		<div class="bannar"><img src="img/bannar.jpg" /></div>
		
		<div class="menu_bg">
            <div class="menu">
			<div class="list">  
			  <a  href="#"><strong>首&nbsp; &nbsp; 页</strong></a>
			  <a  href="#"><strong>关于我们</strong></a>
			  <a  href="#"><strong>志愿服务</strong></a>
			  <a  href="#"><strong>资讯动态</strong></a>
			  <a  href="#"><strong>文件资料</strong></a>
			  <a  href="#"><strong>志愿之星</strong></a>
			  <a  href="#"><strong>基层风采</strong></a>
			  <a  href="#"><strong>下载专区</strong></a>
			  <a  href="#"><strong>各地团讯</strong></a>
			  <a  href="#"><strong>志愿者日志</strong></a>
			  <a  href="#"><strong>页志愿者专享区</strong></a>  </div>
            </div>
		</div>
		
		<div class="bg2">
		        <div  class="home">
					欢迎您来到 南通志愿者服务网！&nbsp; &nbsp;
				</div>
				<div class="weather">
					<img src="img/weather_cloudy.png" />&nbsp;2010年8月5日  星期四  天气：晴转多云   气温：35℃~38℃
		        </div>
				<div  class="search">
				      <span >
						<strong>全文检索:</strong>&nbsp; &nbsp;
						<input type="text" name="keywords"   size="20" class="text"  /> 
			      </span>
          </div>
			   <div  class="searchpic">
				      <a href="#"><img src="img/search.jpg"  border="0"/ > </a>
	           </div>
		</div>
  </div>
</div>
	<!--头结束-->

    <!--表格开始-->
    <form method="POST" name="form" id="registerForm" action="create.action">
    <input type="hidden" value="${org.orgId}" name="volunorgid">
    <input type="hidden" value="${org.orgName}" name="volunorgname">
    <input type="hidden" value="" id="uuid">
	<div  class="table">
		<div class="tabg">
			<div  class="tatitle">
			<strong> 南通志愿服务者登记表</strong>		</div>
		</div>
	    <div class="table_wz">
			<table width="999" height="1255" border="0" cellpadding="0" cellspacing="0" class="table_border">
			  <tr>
				<td width="28" height="38">&nbsp;</td>
				<td colspan="2" background="img/trbg.jpg">
					<table width="844" border="0">
					  <tr>
						<td width="102"> &nbsp; <font size="3"><strong>注册机构</strong></font></td>
						<td width="667"><font  color="#FF0000">（ 请尽量完整填写以下信息以便您完成注册志愿者的申请， *  为必填项目 ）</font></td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </table>				</td>
			  </tr>
			  <tr>
				<td height="157">&nbsp;</td>
				<td colspan="2" valign="top"><br />
				  <table width="811" height="84" border="0" cellpadding="0" cellspacing="0">
					  <tr >
						<td width="81" height="38" align="right">机构名称：</td>
						<td width="213" class="font" >${org.orgName}</td>
						<td  align="right" height="38">联&nbsp; 系&nbsp;  人：</td>
						<td class="font">${org.orgContactor}</td>
						<td align="right">联系电话：</td>
						<td class="font">${org.orgTel}</td>
					  </tr>
					   <tr >
						<td  align="right" height="38">联系地址：</td>
						<td class="font">${org.orgAddress}</td>
						<td align="right">邮政编码：</td>
						<td class="font">${org.orgZipCode}</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
					  <tr>
						
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					  </tr>
				  </table>				 </td>
			  </tr>
			  <tr>
				<td width="28" height="36">&nbsp;</td>
				<td colspan="2" background="img/trbg.jpg">
					<table width="844" border="0">
					  <tr>
						<td width="102"> &nbsp; <font size="3"><strong>注册信息</strong></font></td>
						<td width="667">&nbsp; </td>
						<td width="61">&nbsp;</td>
					  </tr>
				  </table>				
				  </td>
			  </tr>
			  <tr>
				<td height="173">&nbsp;</td>
				<td colspan="2" valign="top"><br />
				   <table width="912" height="84" border="0" cellpadding="0" cellspacing="0">
					  <tr >
						<td width="81" height="38" align="right">登陆名称：</td>
						<td width="213" class="font" ><input name="userName" id="usernameid" type="text"  class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td width="83" align="right">真实姓名：</td>
						<td width="135" class="font"><input name="name" type="text" size="10"   class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td width="85" align="right">性&nbsp; &nbsp; &nbsp; &nbsp; 别：</td>
						<td width="130" class="font"><input name="sex" type="radio" value="1" checked/>男&nbsp; &nbsp; <input name="" type="radio" value="2" />女&nbsp; &nbsp; <font color="#FF0000">*</font></td>
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
						<td width="209" class="font" ><select name="credtype">
						  <c:forEach items="${zjlx}" var="current">
	          				<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      		  			  </c:forEach>
						</select>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td width="80" align="right">证件号码：</td>
						<td width="238" class="font"><input name="credcode" type="text" size="20"   class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td width="79" align="right">联系电话：</td>
						<td width="219" class="font"><input name="cnttel" type="text" size="20"   class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
					  </tr>
					   <tr >
						<td  align="right" height="38">民&nbsp; &nbsp; &nbsp; &nbsp; 族：</td>
						<td class="font"><select name="posnai">
	          				<c:forEach items="${mz}" var="current">
        						<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      		  				</c:forEach>
						</select>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
						<td align="right">学&nbsp; &nbsp; &nbsp; &nbsp; 历：</td>
						<td class="font">
						<select name="eduLevel">
	          	<option value="" label=""></option>
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
						<td width="209" class="font" ><input name="cellPhone" type="text" size="20"   class="text"/>&nbsp; &nbsp; <font color="#FF0000">*</font></td>
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
				<td colspan="2" valign="top"><br />
				   <table width="296" height="84" border="0" cellpadding="0" cellspacing="0">
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
						<td width="20%" align="left"><select  name="loccit" id="loccitid">
			          <option value="">请选择</option>
			          </select>
			          <select name="locationcity" id="cityid"><option value="">请选择</option></select>
			          &nbsp;&nbsp;&nbsp;&nbsp;
			          <select name="locationcountry" id="countryid"><option value="">请选择</option></select>
			          &nbsp;&nbsp;&nbsp;&nbsp;
			          <select name="locationstreet" id="streetid"><option value="">请选择</option></select>
			          &nbsp;&nbsp;&nbsp;&nbsp;
			          <select name="locationcommunity" id="communityid"><option value="">请选择</option></select>
			          &nbsp;&nbsp;&nbsp;&nbsp;
			          </td>
						
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
				     <c:forEach items="${fwyx}" var="current">
	          		<input type="checkbox" value="<c:out value="${current.paramOrder}"/>" name="intention"><c:out value="${current.paramKey}"/><br>
				</c:forEach>
				</td>
		      </tr>
			  <tr>
				<td height="140">&nbsp;</td>
				<td colspan="2" valign="top">
				   <table width="832" height="84" border="0" cellpadding="0" cellspacing="0">
					  <tr >
						<td width="119" height="38" align="right">爱好、特长：</td>
						<td width="713" class="font" >
						  <textarea name="hobby" rows="5" class="text2"></textarea>
						</td>
					  </tr>
			      </table>	
				</td>
			  </tr>
			  <tr>
				<td>&nbsp;</td>
				<td colspan="2" align="center">
				<input type="submit" value="确定" src="img/ok.jpg">
			    <input type="button" value="返回" src="img/back.jpg">
				</td>
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
			  <img src="img/LOGO.jpg" width="90" height="83" />
			</div>
			<div class="bottom_font">
			
			Copyright ©ntzyz.org.cn All Right Reserved.<br /><br />
			版权所有：南通市精神文明建设指导委员会                 技术支持：中国移动通信集团江苏有限公司</div>
			
		</div>
    </div>
    </form>
	<!--表格结束-->
    
</body>
</html>