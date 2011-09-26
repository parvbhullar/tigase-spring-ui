<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<input type="hidden" class="free wapFree" value="aaa"/>
<div style="display: none;" id="con21">
<h4><span class="fr">
<button id=wapDraftSave type="button" class="collapsed">保存WAP广告</button> <span class="gray collapsed">|</span> <a href="javascript:void(0)" id="exhibitWap" style="display:none"><img src="/images/ico_infoc.gif" alt="展开" width="14" height="14" />展开</a><a href="javascript:void(0)" id="closeWap" ><img src="/images/ico_infoo.gif" alt="收起" width="14" height="14" />收起</a> <span class="gray">|</span> <a id="previewWapShow"
	href="javascript:void(0)">预览</a></span> 广告内容制作</h4>
<%
    //隐藏hidden
%> <form:hidden path="command.wapId" />
<table cellspacing="0" cellpadding="0" border="0" class="tabNF collapsed"
	id="wapTable">
	<col width="20%" />
	<col width="80%" />
	<tr>
		<th><span class="must">*</span>文字内容</th>
		<td><form:textarea path="command.wapContent" cssClass="long"
			rows="6" /></td>
	</tr>
	<tr>
		<th>电话</th>
		<td><form:input path="command.wapTelephone" cssClass="long" maxlength="50"/>
        </td>
	</tr>
	<tr>
		<th>传真</th>
		<td><form:input path="command.wapFax" cssClass="long" maxlength="50"/></td>
	</tr>
	<tr>
		<th>Email</th>
		<td><form:input path="command.wapEmail" cssClass="long" maxlength="100"/>
        <small style="display:block">请输入联系邮箱，xxx@xxx.xxx 格式，不能输入中文。</small>
        </td>
	</tr>
	<tr>
		<th>QQ</th>
		<td><form:input path="command.wapQq" cssClass="long" maxlength="50"/>
        <small style="display:block">请输入QQ号码，只能输入数字。</small>
        </td>
	</tr>
	<tr>
		<th>MSN/Gmail</th>
		<td><form:input path="command.wapMsn" cssClass="long" maxlength="100"/><small style="display:block">请输入 MSN 或 Gmail 地址，xxx@xxx.xxx 格式，不能输入中文。</small></td>
	</tr>
	<tr>
		<th>联系人地址</th>
		<td><form:input path="command.wapAddress" cssClass="long" maxlength="100"/></td>
	</tr>
	<tr>
		<th>网址</th>
		<td><form:input path="command.wapLinkAddres" cssClass="long" maxlength="100"/>
        <small style="display:block">该网址只显示文字，不为超链接，如果您希望能点击跳转到您输入的网站，请选择其他类型的模板。</small>
        </td>
	</tr>
</table>
</div>