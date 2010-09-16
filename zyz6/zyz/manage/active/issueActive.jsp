<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>发布活动</title>
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/javascript/jquery-1.4.2.min.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/fckeditor/fckeditor.js"></script>
		<script src="<c:url value="/javascript/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
	</head>
	<script type="text/javascript">
		function saveIssueActive(){
			//正整数 正则表达式
			var r = /^[0-9]*[1-9][0-9]*$/;
			// 附件的最大值
			var maxFileSize = 10000000;
	
			// 活动主题
			var acttitle = document.getElementById('acttitle').value.trim();
			// 活动地点
			var actaddr = document.getElementById('actaddr').value.trim();
			// 开始日期
			var startdate = document.getElementById('startdate').value.trim();
			// 结束日期
			var enddate = document.getElementById('enddate').value.trim();
			// 时间储蓄
			var acthours = document.getElementById('acthours').value.trim();
			// 服务意向
			var _intention = document.getElementById('intention').value;
			// 活动人数
			var actnum = document.getElementById('actnum').value.trim();
			// 活动摘要
			var summary = document.getElementById('summary').value;
			// 活动介绍
			var actdesc = FCKeditorAPI.GetInstance("actdesc").GetXHTML(true);
			// 附件
			var attach = document.getElementById('enclosure');
			document.getElementById('acttitle').value = acttitle;
			document.getElementById('actaddr').value = actaddr;			
			// 活动主题不能为空
			if(acttitle == ""){
				alert("活动主题不能为空！");
		   		return false;
			}
			// 活动地点不能为空
			if(actaddr == ""){
				alert("活动地点不能为空！");
		   		return false;
			}
			// 开始日期不能为空
			if(startdate == ""){
				alert("开始日期不能为空！");
		   		return false;
			}
			// 开始日期 >= 系统日期
			var date = new Date();
		   	var y = date.getFullYear();
		   	var m = date.getMonth() + 1;
		   	var d = date.getDate();
		   	var systemDate = y + (m<10?"-0":"-")+ m +(d<10?"-0":"-")+ d;
		   	if(systemDate > startdate){
		   		alert("开始日期必须大于等于系统日期！");
		   		return false;
		   	}
		   	// 结束日期不能为空
			if(enddate == ""){
				alert("结束日期不能为空！");
		   		return false;
			}
			// 结束日期 >= 开始日期
			if(enddate < startdate){
				alert("结束日期必须大于等于开始日期！");
		   		return false;
			}
			// 时间储蓄必须是大于0的整数
			if(!r.test(acthours)){
				alert("时间储蓄必须是大于0的整数！");
		   		return false;
			}else{
				document.getElementById('acthours').value = parseInt(acthours,10);
			}
			// 活动人数必须是大于0的整数
			if(!r.test(actnum)){
				alert("活动人数必须是大于0的整数！");
		   		return false;
			}else{
				document.getElementById('actnum').value = parseInt(actnum,10);
			}
			// 服务意向不能为空
			if(_intention == ""){
				alert("服务意向不能为空！");
		   		return false;
			}
			// 活动摘要不能为空
			if(summary == ""){
				alert("活动摘要不能为空！");
		   		return false;
			}
			// 活动摘要长度检查
			if(getBytesLength(summary) > 1024){
				alert("活动摘要的长度不能超过512个汉字！");
		   		return false;
			}
			// 活动介绍
			if(actdesc == ""){
				alert("活动介绍不能为空！");
		   		return false;
			}
			// 活动介绍长度检查
			if(getBytesLength(actdesc) > 1024){
				alert("活动介绍的长度不能超过512个汉字！");
		   		return false;
			}
			// 附件大小验证
//			var filePath = attach.value;
//			var image=new Image();
//			image.dynsrc=filePath;
//			var file_size = image.fileSize;
//			if(file_size > maxFileSize){
////				alert("上传附件大小不能超过10M");				
//				return false;
//			}			
			document.issueForm.action="../active/saveIssueActive.action";
			document.issueForm.submit();
		}
		//trim 的函数(去除前后空格)
		String.prototype.trim = function(){ 
			return this.replace(/^[\s|\u3000]+|[\s|\u3000]+$/g,"");
		}
		/**  
		* 得到字符串的字符长度（一个汉字占两个字符长）  
		*/  
		function getBytesLength(str) {   
		   // 在GBK编码里，除了ASCII字符，其它都占两个字符宽   
		   return str.replace(/[^\x00-\xff]/g, 'xx').length;   
		}
	</script>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">		
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">发布活动</div></td>
			      		</tr>
    				</table>
    			</td>
    			<td width="16" valign="top" background="../images/mail_rightbg.gif">
    				<img src="../images/nav-right-bg.gif" width="16" height="29" />
    			</td>
  			</tr>
  			<tr>
    			<td height="71" valign="middle" background="../images/mail_leftbg.gif">&nbsp;</td>
   				<td valign="top" bgcolor="#F7F8F9">
   					<table width="100%" height="138" border="0" cellpadding="0" cellspacing="0">
      					<tr>
        					<td valign="top">
        						<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
					          		<tr>
					            		<td height="8">
					            			<table width="100%">
					            				<tr>
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;发布活动</td>
					            					<td align="right" class="left_txt">页面处理时间:<c:out value="${(postTime-preTime)}"/>毫秒&nbsp;&nbsp;</td>
					            				</tr>
					            			</table>
					            		</td>
					          		</tr>
          							<tr>
            							<td height="10">
            								<table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              									<tr><td></td></tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
            							<td>
            								<table width="100%" height="55" border="0" cellpadding="0" cellspacing="0">
              									<tr>
									                <td width="10%" height="55" valign="middle">
									                	<img src="../images/title.gif" width="54" height="55">
									                </td>
									                <td width="90%" valign="top">
                									</td>
              									</tr>
            								</table>
            							</td>
          							</tr>
          							<tr>
          								<td>
          									<form  name="issueForm" method="post" enctype="multipart/form-data">
											<table style="width:100%" cellspacing="0" cellpadding="0">
												<tr>
													<td style="width:15%" style="text-align:right">活动主题：</td>
													<td style="width:35%">
														<input type="text" name="acttitle" id="acttitle" maxlength=64 style="width:40%">
													</td>
													<td style="width:15%" style="text-align:right">活动地点：</td>
													<td style="width:35%">
														<input type="text" name="actaddr" id="actaddr" maxlength=64 style="width:40%">
													</td>
												</tr>
												<tr>
													<td style="width:15%" style="text-align:right">开始日期：</td>
													<td style="width:35%">
														<input type="text" name="startdate" id="startdate" onfocus="WdatePicker();" style="width:40%"/>
													</td>
													<td style="width:15%" style="text-align:right">结束日期：</td>
													<td style="width:35%">
														<input type="text" name="enddate" id="enddate" onfocus="WdatePicker();" style="width:40%"/>
													</td>
												</tr>
												<tr>
													<td style="width:15%" style="text-align:right">时间储蓄：</td>
													<td style="width:35%">
														<input type="text" name="acthours" id="acthours" maxlength=8 style="width:40%">&nbsp;小时
													</td>
													<td style="width:15%" style="text-align:right">活动人数：</td>
													<td style="width:35%">
														<input type="text" name="actnum" id="actnum" maxlength=8 style="width:40%">
													</td>													
												</tr>
												<tr>
													<td style="width:15%" style="text-align:right">活动系数：</td>
													<td style="width:35%">
														<select style="width:42%" name="ratio">
															<option value="10">10</option>
															<option value="20">20</option>
															<option value="30">30</option>
															<option value="40">40</option>
															<option value="50">50</option>
															<option value="60">60</option>
															<option value="70">70</option>
															<option value="80">80</option>
															<option value="90">90</option>
															<option value="100">100</option>
														</select>&nbsp;%
													</td>
													<td style="width:15%" style="text-align:right">服务意向：</td>
													<td style="width:35%">
														<select name="intention" id="intention" style="width:60%">
															<option value="0">--全部--</option>
															<c:forEach items="${fwyxList}" var="p" >
																<option value='<c:out value="${p.paramValue}"/>'>
																<c:out value="${p.paramKey}"/>
																</option>
															</c:forEach>
														</select>
													</td>
												</tr>
												<tr>
													<td style="width:15%" style="text-align:right">活动摘要：</td>
													<td style="width:35%" colspan=3>
														<textarea name="summary" id="summary" rows=3 cols=40></textarea>
													</td>
												</tr>
												<tr>
													<td style="width:15%" style="text-align:right">活动描述：</td>
													<td colspan=3>
														<FCK:editor instanceName="actdesc" toolbarSet="ecommerce">
															<jsp:attribute name="value">
															</jsp:attribute>
															<jsp:body>
																<FCK:config SkinPath="skins/office2003/"/>
															</jsp:body>
														</FCK:editor>
													</td>
												</tr>
												<tr>
													<td style="width:15%" style="text-align:right">附件：</td>
													<td colspan=3><input type="file" name="enclosure" id="enclosure" size="50" />&nbsp;&nbsp;<span style="color:red">(附件大小小于10M)</span></td>
												</tr>
											</table>
											<div align="center">
												<input type="button" name="save" value="保 存" onClick="saveIssueActive()">&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="button" name="save" value="重 置">				
											</div>
											</form>
          								</td>
          							</tr>
          						</table>
          					</td>
          				</tr>
          			</table>
          		</td>
          	</tr>
		</table>		
	</body>
</html>