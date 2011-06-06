<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://jakarta.apache.org/taglibs/i18n-1.0"
	prefix="i18n"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<script type="text/javascript" src="js/friendchoose/js/jquery.tokeninput.js"></script>
	<script type="text/javascript" src="jsp/system/org.js"></script>
    <link href="js/friendchoose/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div id="orgMod">

<a href="javascript:void(false)" class="button" id="plus"><span class="plus icon"></span>创建</a>
<!-- <a href="javascript:void(false)" class="button" id="edit"><span class="edit icon"></span>编辑</a> -->
<a href="javascript:void(false)" class="button" id="trash"><span class="trash icon"></span>删除</a>
<a href="javascript:void(false)" class="button" id="loop"><span class="loop icon"></span>刷新</a>

<a href="javascript:void(false)" class="button" id="check"><span class="check icon"></span>保存</a>
<a href="javascript:void(false)" class="button" id="reload"><span class="reload icon"></span>取消</a>
	<div id="listDiv"><table id="organizationList"></table></div>
								<div id="page"></div>
<div id="curdForm">
<form id="organizationForm" name="organizationForm" action="" method="post">
<fieldset>
	<legend>添加机构</legend>
	<table id="addOrgTable">
		<tr>
			<td width="20%" style="border: 0"><div class="txt">名称：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="text" class="text" id="orgname" name="orgname" value=""></td>
			<td width="50%"><div id="orgnameMessageCreate" style="color:red"></div></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">机构登录名：</div><div class="redstar">*</div></td>	
			<td width="30%"><input type="text" class="text" id="orglogname" name="orglogname" value="">
			<div>机构登录名字符只允许输入英文和数字！</div></td>
			<td width="50%"><div id="orglognameMessageCreate" style="color:red"></div></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">密码：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="password" class="text" id="orgpassword" name="orgpassword" value="">
			<div>密码只允许输入英文和数字！</div></td>
			<td width="50%"><div id="orgpasswordMessageCreate" style="color:red"></div></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">密码确认：</div><div class="redstar">*</div></td>	
			<td width="30%"><input type="password" class="text" id="resurePassword" name="resurePassword" value=""></td>
			<td width="50%"><div id="orgresurePasswordMessageCreate" style="color:red"></div></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">邮件：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="text" class="text" id="email" name="email" value=""></td>
			<td width="50%"><div id="orgemailMessageCreate" style="color:red"></div></td>
		</tr>
	</table>
</fieldset>
</form>
</div>

<div id="dialogViewOrg" title="查看机构">
<table id="ViewOrg">
	<tr>
		<td width="10%" style="border: 0">名称：</td>
		<td width="40%"><label name="orgname"></td>
		<td width="10%" style="border: 0">邮件：</td>
		<td width="40%"><label name="email"></td>
	</tr>
	<tr>
		<td width="10%" style="border: 0">公司电话：</td>
		<td width="40%"><label name="officephone"></td>
		<td width="10%" style="border: 0">传真：</td>
		<td width="40%"><label name="faxnumber"></td>
	</tr>
	<tr>
		<td width="10%" style="border: 0">国家：</td>
		<td width="40%"><label name="nationality"></td>
		<td width="10%" style="border: 0">省份：</td>
		<td width="40%"><label name="province"></td>
	</tr>
	<tr>
		<td width="10%" style="border: 0">城市：</td>
		<td width="40%"><label name="city"></td>
		<td width="10%" style="border: 0">详细地址：</td>
		<td width="40%"><label name="detailedaddress"></td>
	</tr>
	<tr>
		<td width="10%" style="border: 0">邮编：</td>
		<td width="40%"><label name="postcode"></td>
		<td width="10%" style="border: 0">备注：</td>
		<td width="40%"><label name="remark"></td>
	</tr>
	<tr>
		<td width="10%" style="border: 0">拥有权限：</td>
		<td width="80%" colspan="3"><label name="pname"></td>
	</tr>
</table>
</div>

<!--设置机构权限时显示权限信息的窗口  -->
<div id="settingpermissionsDiv" title="设置权限">
<form method="post" id="settingpermissions">
<table id="settingpermissionsTable" border="0">
	<tbody>
		<tr>
			<td width="20%">机构名称：</td>
			<td width="80%" name="orgname"></td>
		</tr>
		<tr>
			<td>原机构权限：</td>
			<td name="pname"></td>
		</tr>
		<tr>
			<td style="border: 0">机构权限：</td>
			<td><input id="allpname" name="allpname" class="tokeninput" index="1"/>
			<ul id="token-preadd" style="display: none">
			</ul>

			<ul id="token-initdata" style="display: none">
			</ul>
			</td>
		</tr>
	</tbody>
</table>
<input type="hidden" name="orgid" id="orgid">
<input type="hidden" name="pnumber" id="pnumber">
</form>
</div>
</div>
</body>
</html>