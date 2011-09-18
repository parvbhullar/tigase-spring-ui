<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String ctxindex = request.getContextPath();
HttpSession getSession = request.getSession();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="js/friendchoose/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div id="personalDetail">
<a href="javascript:void(false)" class="button" id="edit"><span class="book icon"></span>修改</a>

<a href="javascript:void(false)" class="button" id="check"><span class="check icon"></span>保存</a>
<a href="javascript:void(false)" class="button" id="reload"><span class="reload icon"></span>取消</a>

<div id="curdForm">
	<form id="dialogViewPersonal" title="查看用户">
		<table id="ViewPersonal">
			<tr>
			<td width="20%" style="border: 0">姓名：</td>
			<td width="30%"><label id="username" ></label></td>
			<td width="20%" style="border: 0">登录名：</td>	
			<td width="30%"><label id="logname" ></label></td>
			</tr>
			<tr>
			<td style="border: 0">昵称：</td>			
			<td><label id="nickname" ></label></td>
			<td style="border: 0">性别：</td>			
			<td><label id="sex"></label></td>
			</tr>
			<tr>
			<td style="border: 0">职务：</td>			
			<td><label id="job"></label></td>
			</tr>
			<tr>
			<td style="border: 0">电子邮箱：</td>			
			<td><label id="email"></label></td>
			<td style="border: 0">详细地址：</td>			
			<td><label id="detailedaddress"></label></td>
			</tr>
			<tr>
			<td style="border: 0">办公电话：</td>			
			<td><label id="officephone"></label></td>
			<td style="border: 0">其他电话：</td>			
			<td><label id="otherphone"></label></td>
			</tr>
			<tr>
			<td style="border: 0">手机号码：</td>			
			<td><label id="mobilephone"></label></td>
			<td style="border: 0">传真号码：</td>			
			<td><label id="faxnumber"></label></td>
			</tr>
			<tr>
			<td style="border: 0">邮编：</td>			
			<td><label id="postcode"></label></td>
			<td style="border: 0">其他信息：</td>			
			<td><label id="othermessage"></label></td>
			</tr>
		</table>
	</form>

</div>

<div id="UpdPersonalDiv">
	<form id="UpdPersonal" name="UpdPersonal" action="" method="post">
		<table id="UpdPersonalView">
			<tr>
			<td width="10%" style="border: 0"><div class="txt">姓名：</div><div class="redstar">*</div></td>
			<td width="25%"><input type="text" class="text" id="username" name="username"></td>
			<td width="15"><div id="personalnameMessage" style="color:red"></div></td>
			<td width="10%" style="border: 0"><div class="txt">昵称：</div></td>
			<td width="25%"><input type="text" class="text" id="nickname" name="nickname"></td>
			<td width="15"></td>
			</tr>
			<tr>			
			<td style="border: 0"><div class="txt">性别：</div></td>			
			<td>
			<select name="sex">
			<option value="0">保密</option>
			<option value="1">男</option>
			<option value="2">女</option>
			</select>
			</td>
			<td></td>
			<td style="border: 0"><div class="txt">职务：</div></td>			
			<td><input type="text" class="text" id="job" name="job"></td>
			<td></td>	
			</tr>
			<tr>
			<td style="border: 0"><div class="txt">详细地址：</div></td>			
			<td><input type="text" class="text" id="detailedaddress" name="detailedaddress"></td>
			<td></td>
			</tr>
			<tr>
			<td style="border: 0"><div class="txt">办公电话：</div></td>			
			<td><input type="text" class="text" id="officephone" name="officephone"></td>
			<td><div id="personalofficephoneMessage" style="color:red"></div></td>
			<td style="border: 0"><div class="txt">其他电话：</div></td>			
			<td><input type="text" class="text" id="otherphone" name="otherphone"></td>
			<td><div id="personalotherphoneMessage" style="color:red"></div></td>
			</tr>
			<tr>
			<td style="border: 0"><div class="txt">手机号码：</div></td>			
			<td><input type="text" class="text" id="mobilephone" name="mobilephone"></td>
			<td><div id="personalmobilephoneMessage" style="color:red"></div></td>
			<td style="border: 0"><div class="txt">传真号码：</div></td>			
			<td><input type="text" class="text" id="faxnumber" name="faxnumber"></td>
			<td><div id="personalfaxnumberMessage" style="color:red"></div></td>
			</tr>
			<tr>
			<td style="border: 0"><div class="txt">邮编：</div></td>			
			<td><input type="text" class="text" id="postcode" name="postcode"></td>
			<td><div id="personalpostcodeMessage" style="color:red"></div></td>
			<td style="border: 0"><div class="txt">其他信息：</div></td>			
			<td><input type="text" class="text" id="othermessage" name="othermessage"></td>
			<td></td>
			</tr>
		</table>
		<input type="hidden" name="personalid" id="personalid">
		<input type="hidden" name="nationality" id="nationality">
		<input type="hidden" name="province" id="province">
		<input type="hidden" name="city" id="city">
	</form>
</div>

</div>
</body>
</html>