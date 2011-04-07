<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
	<script type="text/javascript" src="js/friendchoose/js/jquery.tokeninput.js"></script>
	<link href="js/friendchoose/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="meeting">


<a href="javascript:void(0)" class="button" id="plus"><span class="plus icon"></span>创建</a>
<!--  
<a href="javascript:void(false)" class="button" id="trash"><span class="trash icon"></span>删除</a>
-->
<a href="javascript:void(false)" class="button" id="loop"><span class="loop icon"></span>刷新</a>

<a href="javascript:void(false)" class="button" id="check"><span class="check icon"></span>保存</a>
<a href="javascript:void(false)" class="button" id="reload"><span class="reload icon"></span>取消</a>
	<div id="listDiv" style="padding: 10px;">
		<table id="meetingList"></table>
		<div id="page"></div>
	</div>
								
<div id="curdForm">
<form id="meetingForm" name="meetingForm" action="MeetingServlet?action=addFixedMeeting" method="post">
<fieldset class="ui-widget ui-widget-content ui-corner-all">
	<legend>常规选项</legend>
		<table id="addMeetingTable">
		<tr>
			<td width="50%" style="border: 0" colspan="2"><input id="" type="checkbox" name="openType" />不公开</td>
			<td width="50%" style="border: 0" colspan="2"><input id="mustLogin" type="checkbox" name="mustLogin" />参加者必须登录</td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">主题：</div><div class="redstar">*</div></td>
			<td width="80%" colspan="3"><input type="text" class="text" id="subject" name="subject" value=""></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">密码：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="text" class="text" id="passwd" name="passwd" value=""></td>
			<td width="20%" style="border: 0"><div class="txt">密码确认：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="text" class="text" id="resurePasswd" name="resurePasswd" value=""></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">开始时间：</div><div class="redstar">*</div></td>
			<td width="30%"><input id="startTime" class="text" type="text" name="startTime" onClick="WdatePicker({dateFmt:'yyyy-M-d H:mm:ss'})"></td>
			<td width="20%" style="border: 0"><div class="txt">结束时间：</div><div class="redstar">*</div></td>
			<td width="30%"><input id="endTime" class="text" type="text" name="endTime" onClick="WdatePicker({dateFmt:'yyyy-M-d H:mm:ss'})"></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">邀请参与者：</div></td>
			<td width="30%"><input id="tokeninput3" class="tokeninput" index="3"/></td>
		</tr>
		<tr>
			<td width="20%" style="border: 0"><div class="txt">会议模式：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="text" class="text" id="conferencePattern" name="conferencePattern" value=""></td>
			<td width="20%" style="border: 0"><div class="txt">参加人数：</div><div class="redstar">*</div></td>
			<td width="30%"><input type="text" class="text" id="attendeeAmount" name="attendeeAmount" value=""></td>
		</tr>
		<tr>
			<td width="50%" style="border: 0" colspan="2"><input id="openType" type="checkbox" name="openType" />不公开</td>
			<td width="50%" style="border: 0" colspan="2"><input id="mustLogin" type="checkbox" name="mustLogin" />参加者必须登录</td>
		</tr>
		<tr>
			<td style="border: 0" noWrap>定期模式:</td>	
			<td colspan="3">
				<table width="100%">
					<tr><td><input type="radio" name="repeat" value="DAILY" checked>每<input type="text" name="repeatDays" style="width: 60px;"/>天</td>
					</tr>
					<tr><td><input type="radio" name="repeat" value="DAILY2">每个工作日</td></tr>
					<tr><td>
						<input type="radio" name="repeat" value="WEEKLY">每&nbsp;
						<input type="checkbox" name="monday" />周一&nbsp;
						<input type="checkbox" name="tuesday" />周二&nbsp;
						<input type="checkbox" name="wednesday" />周三&nbsp;
						<input type="checkbox" name="thursday" />周四&nbsp;
						<input type="checkbox" name="friday" />周五&nbsp;
						<input type="checkbox" name="saturday" />周六&nbsp;
						<input type="checkbox" name="sunday" />周日</td>
					</tr>
					<tr><td><input type="radio" name="repeat" value="MONTHLY" >每月<input type="text" name="monthDays" style="width: 60px;"/>号</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="20%" style="border: 0">会议描述：</td>
			<td width="80%" style="border: 0" colspan="3"><TEXTAREA name="agenda" style="border: 0px;width:100%;height:100;"></TEXTAREA></td>
		</tr>
	</table>
</fieldset>
</form>
</div>
</div>
</body>
</html>