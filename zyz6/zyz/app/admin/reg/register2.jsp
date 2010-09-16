<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<title>注册第二步</title>
</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0" border="0" align="center">
  <tbody>
  	<tr>
    <td><table width="100%" cellspacing="0" cellpadding="0" border="0">
      <tbody><tr align="center">
        <td valign="top" bgcolor="F9F9F9">
          <br>
		  <form method="POST" name="form" id="registerForm" action="create.action">
		  <input type="hidden" value="${org.orgId}" name="regorg">
	      <table width="77%" cellspacing="1" cellpadding="2" border="0" bgcolor="#ffffff" class="style2">
	        <tbody><tr bgcolor="#ffb077">
	          <td class="style2" colspan="6"><strong>注册志愿者信息（请尽量完整填写以下信息以便您完成注册志愿者的申请， ＊为必填项目）</strong></td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e" rowspan="5">注册机构</td>
	          <td bgcolor="#fed89e">机构名称</td>
	          <td align="left" colspan="3">${org.orgName}</td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e">联&nbsp;系&nbsp;人</td>
	          <td align="left" colspan="3">${org.orgContactor}</td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e">联系电话</td>
	          <td align="left" colspan="3">${org.orgZipCode}</td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e">联系地址</td>
	          <td align="left" colspan="3">${org.orgAddress}</td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e">邮政编码</td>
	          <td align="left" colspan="3">210007</td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e" rowspan="3">注册信息</td>
	          <td bgcolor="#fed89e" class="required">登录名称*</td>
	          <td align="left"><input type="text" maxlength="16" value="" id="usernameid" name="username"><div id="shwlgn"></div></td>
	          <td bgcolor="#fed89e" class="required">真实姓名*</td>
	          <td align="left"><input type="text" maxlength="32" value="" name="name"></td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e" class="required">密&nbsp;&nbsp;&nbsp;&nbsp;码*</td>
	          <td align="left"><input type="password" maxlength="16" value="" name="password" id="passwordid"></td>
	          <td bgcolor="#fed89e" class="required">性&nbsp;&nbsp;&nbsp;&nbsp;别*</td>
	          <td align="left"><input type="radio" value="1" name="sex" checked>男<input type="radio" value="2" name="sex">女</td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e" class="required">确认密码*</td>
	          <td align="left"><input type="password" maxlength="16" value="" name="cfmpwd" id="cfmpwdid"></td>
	          <td bgcolor="#fed89e" class="required">出生日期*</td>
	          <td align="left"><input type="text" onFocus="WdatePicker();" value="" size="10" id="birthday"  name="birthday" readyonly="true"></td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td width="12%" bgcolor="#fed89e" rowspan="4">个人基本信息</td>
	          <td width="12%" bgcolor="#fed89e" class="required">证&nbsp;&nbsp;&nbsp;&nbsp;件*</td>
	          <td width="32%" align="left">
	          <select name="credtype">
	          <option value="" label="请选择">请选择</option>
	          <c:forEach items="${zjlx}" var="current">
	          		<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      		  </c:forEach>
	          </select>
	          <input type="text" size="18" maxlength="32" value="" name="credcode"></td>
	          <td width="12%" bgcolor="#fed89e" class="required">联系电话*</td>
	          <td width="32%" align="left"><input type="text" size="16" maxlength="64" value="" name="cnttel">(例：025-12345678)</td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          
	          <td bgcolor="#fed89e" class="required">民&nbsp;&nbsp;&nbsp;&nbsp;族*</td>
	          <td align="left"><select name="posnai">
	          <c:forEach items="${mz}" var="current">
        			<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      		  </c:forEach>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e" class="required">学&nbsp;&nbsp;&nbsp;&nbsp;历*</td>
	          <td align="left">
	          <select name="edulevel">
	          	<option value="" label=""></option>
	          	<c:forEach items="${xl}" var="current">
        			<option value="<c:out value="${current.paramOrder}" />" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
      			</c:forEach>
	          </select></td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e" rowspan="4">联系方式 </td>
	          <td bgcolor="#fed89e">手&nbsp;&nbsp;&nbsp;&nbsp;机*</td>
	          <td align="left"><input type="text" size="31" maxlength="64" value="" name="cellphone" id="dnid"></td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e">E-MAIL</td>
	          <td align="left"><input type="text" size="31" maxlength="64" value="" name="email"></td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e" rowspan="4">其它信息</td>
	          <td bgcolor="#fed89e">职业分类</td>
	          <td align="left"><select name="profession">
	          	<c:forEach items="${zy}" var="current">
        			<option value="<c:out value="${current.paramOrder}"/>" label="<c:out value="${current.paramKey}" />"><c:out value="${current.paramKey}" /></option>
				</c:forEach>
	         </select></td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	        </tr>
	        <tr bgcolor="#fffaf4">
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e">所在地区</td>
	          <td align="left" colspan="3">
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
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e" rowspan="4">志愿服务信息</td>
	          <td bgcolor="#fed89e" class="required">志愿服务意向*</td>
	          <td valign="top" align="left" >
	          	<c:forEach items="${fwyx}" var="current">
	          		<input type="checkbox" value="<c:out value="${current.paramOrder}"/>" name="sevint"><c:out value="${current.paramKey}"/><br>
				</c:forEach>
	          </td>
	          <td ></td>
	          <td valign="top" align="left">
	            

				
	          </td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e" class="required">爱好、特长*</td>
	          <td align="left" colspan="3"><textarea cols="77" name="hobby"></textarea></td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e">主要志愿服务<br>经历</td>
	          <td align="left" colspan="3"><textarea cols="77" name="sevexp"></textarea></td>
	        </tr>
	        <tr bgcolor="#fffaf4">
	          <td bgcolor="#fed89e">受表彰情况</td>
	          <td align="left" colspan="3"><textarea cols="77" name="honinf"></textarea></td>
	        </tr>
	      </tbody></table>
          <br>
	      <table width="98%" cellspacing="0" cellpadding="5" border="0">
	        <tbody><tr>
	          <td align="center">
			    <input type="submit" value="确定">
			    <input type="button" value="返回">
			  </td>
	        </tr>
	      </tbody></table>
		  </form>
          <br></td>
        </tr>
    </tbody></table>
    </td>
  </tr>
</tbody></table>
</body>
</html>