<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>通知设置</title>
</head>
<body>
<div id="okTipDiv" class="okBox"><div class="ok" style="display:none" id="okTip">修改成功</div></div>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
	<div id="main">
		<div class="mainCon">
		<h1 class="tit">通知设置</h1>
		<div class="leftCon">
			<form:form id="updateRemind"  name="updateRemind" action="accMailNotice.do?action=updateRemind" commandName="command" method="post" >
			<table class="tabNF wa" >
				<col width="50%" /><col width="5%" /><col />
				<tr>
				<th><b>您的数据邮件提醒功能目前状态为</b></th>
				<td >
	          <span class="" id="curStatus"></span>
	      </td>
				<td>
					    <a href="javascript:updateActiveFlag();" class="btnS fl"><span id="updateActive"></span></a>
			  </td>
				</tr>
			</table>
			<table class="tabX" cellpadding="0" cellspacing="0" border="0">
				<col width="30%" /><col width="70%" />
				<tr>
				<th>当账户余额小于此金额时通知</th>
				<td> 
				  <span id="remindAmount">${command.accountBlance}</span>
				  <airad:sysConfigForMailNoticeTag type="MAIL_NOTICE_TYPE_VALUE" valDefault="${command.accountBlance}" id="accountBlance" name="accountBlance" style="display:none"/>
				  <div id="noticeErrDiv" style="color: red;"></div>
				</td>
				</tr>
				<tr>
				<th>您的邮箱地址</th>
				<td>
				   <label id="lbMailAddr" >${command.mailAddr }</label>
				</td>
				</tr>
			</table>
			<div class="btnBox" style="padding-top:0px;" id="operate">
			<div id="btnupdate">
			<input class="btnBY" style="margin-left:205px;" type="button" value="修改" onclick="editRemind();" id="editNotice" />
			</div>
			</div>
			<form:hidden path="remindFlag"/>
			</form:form>
		</div>
		<div class="rightCon">
		<div class="infoCon">
		<h2>提示</h2>
		<ul>
		<li>您可以点击修改，设置邮件通知最低额度。在邮件提醒功能打开的情况下，如果余额低于您设置的值，您将会收到邮件通知。</li>
		</ul>
		</div>
		</div>
		  </div>
	</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script type="text/javascript" src="/js/notice.js"></script>
</body>
</html>
