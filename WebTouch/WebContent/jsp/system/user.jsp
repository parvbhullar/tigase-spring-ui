<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0"
	prefix="i18n"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<link href="js/friendchoose/css/style.css" rel="stylesheet" type="text/css" />
	<link href="css/index/userdetailinfo.css" rel="stylesheet" type="text/css" />
</head>
<body>

<input type="hidden" name="operation" id="operation">
<div id="userMod">
<a href="javascript:void(false)" class="button" id="plus"><span class="plus icon"></span>创建</a>
<a href="javascript:void(false)" class="button" id="edit"><span class="book icon"></span>编辑</a>
<a href="javascript:void(false)" class="button" id="trash"><span class="trash icon"></span>删除</a>
<a href="javascript:void(false)" class="button" id="loop"><span class="loop icon"></span>刷新</a>

<a href="javascript:void(false)" class="button" id="check"><span class="check icon"></span>保存</a>
<a href="javascript:void(false)" class="button" id="reload"><span class="reload icon"></span>取消</a>
	<div id="listDiv"><table id="userList"></table></div>
		<div id="page"></div>
<div id="curdForm">
<form id="userForm" name="userForm" action="" method="post">
<fieldset>
	<legend>添加用户</legend>
	<table id="addUserTable">
		<tr>
			<td width="20%" style="border: 0"><div class="txt">登录名：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="text" class="text" id="logname" name="logname" value="">
			<div>用户登录名字符只允许输入英文和数字！</div></td>
			<td width="50%"><div id="userlognameMessageCreate" style="color:red"></div></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">姓名：</div><div class="redstar">*</div></td>	
			<td width="30%"><input type="text" class="text" id="username" name="username" value=""></td>
			<td width="50%"><div id="usernameMessageCreate" style="color:red"></div></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">密码：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="password" class="text" id="userpassword" name="userpassword" value="">
			<div>密码只允许输入英文和数字！</div></td>
			<td width="50%"><div id="userpasswordMessageCreate" style="color:red"></div></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">密码确认：</div><div class="redstar">*</div></td>	
			<td width="30%"><input type="password" class="text" id="resurePassword" name="resurePassword" value=""></td>
			<td width="50%"><div id="resurePasswordMessageCreate" style="color:red"></div></td>
		</tr>
	</table>
</fieldset>
</form>
</div>

<div id="UpdUserDiv">
<form id="UpdUser" name="UpdUser" action="" method="post">
<fieldset>
	<legend>编辑用户</legend>
	<table id="updUserTable">
		<tr>
			<td width="20%" style="border: 0"><div class="txt">姓名：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="text" class="text" id="username" name="username" value=""></td>
			<td width="50%"><div id="usernameMessageUpdate" style="color:red"></div></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">密码：</div><div class="redstar">*</div></td>	
			<td width="30%"><input type="password" class="text" id="uuserpassword" name="uuserpassword" value="">
			<div>密码只允许输入英文和数字！</div></td>
			<td width="50%"><div id="passwordMessageUpdate" style="color:red"></div></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">状态：</div><div class="redstar">*</div></td>
			<td width="30%">
			<select name="ustate">
			<option value="2">在职</option>
			<option value="3">离职</option>
			</select>
			</td>
			<td width="50%"></td>
		</tr>
	</table>
<input type="hidden" name="userid" id="userid">
<input type="hidden" name="nationality" id="nationality">
<input type="hidden" name="province" id="province">
<input type="hidden" name="city" id="city">
</fieldset>
</form>
</div>

<div id="dialogViewUser" title="查看用户">
<div id="viewuser">
	
</div>

</div>
</div>

<!--设置用户webcall冲值信息的窗口  -->
<div id="settingcardDiv" title="webcall冲值卡" >
<form method="post" id="settingcard">
<p><label for="dummy1">webcall卡号：</label>
	<input type="text" class="text" id="cardnumber" name="cardnumber" />
</p>
<p><label for="dummy1">webcall密码：</label>
	<input type="text" class="text" id="cardnumber" name="cardnumber" />
</p>
<input type="hidden" name="cardid" id="cardid">
<input type="hidden" name="carduserid" id="carduserid">
</form>
</div>

</div>
</body>
</html>