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

<fieldset class="ui-widget ui-widget-content ui-corner-all">
		<p>
			<label for="cname">Name (required, at least 2 characters)</label>
			<input minlength="2" class="required ui-widget-content" name="name" id="cname">
		</p><p>
			<label for="cemail">E-Mail (required)</label>
			<input class="required email ui-widget-content" name="email" id="cemail">
		</p>
		<p>
			<label for="curl">URL (optional)</label>
			<input value="" class="url ui-widget-content" name="url" id="curl">
		</p>
		<p>
			<label for="ccomment">Your comment (required)</label>
			<textarea class="required ui-widget-content" name="comment" id="ccomment"></textarea>
		</p>
		<p>
			<button type="submit" class="submit ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Submit</span></button>
		</p>
	</fieldset>

<div id="name01">
<div class="img"><img src="images/renwu.png" /></div>
<div class="mz"><label id="username"></label></div>
</div>

<div class="name">
<div class="txt">登录名：</div>
<span class="nr"><label id="logname" ></label></span>
</div>

<div class="name">
<div class="txt">昵称：</div>
<span class="nr"><label id="nickname"></label></span>
</div>

<div class="name">
<div class="txt">性别：</div>
<span class="nr"><label id="sex"></label></span>
</div>

<div class="name">
<div class="txt">职务：</div>
<span class="nr"><label id="job"></label></span>

<div class="name">
<div class="txt">电子邮箱：</div>
<span class="nr"><label id="email"></label></span>
</div>

<div class="name">
<div class="txt">详细地址：</div>
<span class="nr"><label id="detailedaddress"></label></span>
</div>

<div class="name">
<div class="txt">办公电话：</div>
<span class="nr"><label id="officephone"></label></span>
</div>
</div>

<div class="name">
<div class="txt">其他电话：</div>
<span class="nr"><label id="otherphone"></label></span>
</div>

<div class="name">
<div class="txt">手机号码：</div>
<span class="nr"><label id="mobilephone"></label></span>
</div>

<div class="name">
<div class="txt">传真号码：</div>
<span class="nr"><label id="faxnumber"></label></span>
</div>

<div class="name">
<div class="txt">邮编：</div>
<span class="nr"><label id="postcode"></label></span>
</div>

<div class="name">
<div class="txt">其他信息：</div>
<span class="nr"><label id="othermessage"></label></span>
</div>

<div class="name">
<div class="txt">状态：</div>
<span class="nr"><label id="state"></label></span>
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