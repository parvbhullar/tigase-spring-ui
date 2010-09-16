<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>发布帮扶</title>
		<link href="../images/skin.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		</style>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/javascript/jquery-1.4.2.min.js"></script>

		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<link href="/zyz/javascript/jqueryUI-1.8.2/themes/ui-lightness/jquery-ui-1.8.2.custom.css" rel="stylesheet" type="text/css" />
		<link href="/zyz/javascript/jquery.treeTable.css" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/javascript/jquery-1.4.2.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jquery.treeTable.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.core.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.widget.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.position.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/javascript/jqueryUI-1.8.2/jquery.ui.dialog.min.js"/>" type="text/javascript"></script>

		
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/fckeditor/fckeditor.js"></script>
		<script src="<c:url value="/javascript/My97DatePicker/WdatePicker.js"/>" type="text/javascript"></script>
		<script src="showactive.js" type="text/javascript"></script>
	
<script language="javascript"> 
function isIE(){ //ie? 
   if (window.navigator.userAgent.toLowerCase().indexOf("msie")>=1) 
    return true; 
   else 
    return false; 
} 

if(!isIE()){ //firefox innerText define 
   HTMLElement.prototype.__defineGetter__(     "innerText", 
    function(){ 
     var anyString = ""; 
     var childS = this.childNodes; 
     for(var i=0; i<childS.length; i++) { 
      if(childS[i].nodeType==1) 
       anyString += childS[i].tagName=="BR" ? '\n' : childS[i].textContent; 
      else if(childS[i].nodeType==3) 
       anyString += childS[i].nodeValue; 
     } 
     return anyString; 
    } 
   ); 
   HTMLElement.prototype.__defineSetter__(     "innerText", 
    function(sText){ 
     this.textContent=sText; 
    } 
   ); 
} 
</script> 	
	
	
	
	</head>
	
	
		<script type="text/javascript">
		
$(document).ready(function() {
	// 查询
	$("#search-postulant").dialog( {
		autoOpen : false,
		height : 450,
		width : 550,
		modal : true,
		buttons : {
			'确认' : function() {
				 postdata(); 
			},
			'退出' : function() {
				$(this).dialog('close');
			}
		}
	});

	function postdata(){       
                     //提交数据函数       
	   $.ajax({                                                //调用jquery的ajax方法       
	   	  type: "POST",                                     //设置ajax方法提交数据的形式       
	      url: "showassist.action",                                      //把数据提交到ok.php       
		  data: "typeId="+$("#typeId").val(),           
	      success: function(msg){                 //       
	      $("#zyz").html(msg);
	      //alert(msg);                     //如果有必要，可以把msg变量的值显示到某个DIV元素中       
    	 }
	   });   
	 }      



		$("#searchp").click(function() {
			$('#search-postulant').dialog('open');
		});
		
		
	$("#search-helping").dialog( {
		autoOpen : false,
		height : 450,
		width : 550,
		modal : true,
		buttons : {
			'确认' : function() {
				if($("#aname")[0].value =="")
				{
					alert("姓名不能为空");
				}
				else{postdata2();} 
			},
			'退出' : function() {
				$(this).dialog('close');
			}
		}
	});		
		
		$("#searczp").click(function() {
			$('#search-helping').dialog('open');
		});		
		
		
	function postdata2(){       
                     //提交数据函数       
	   $.ajax({                                                //调用jquery的ajax方法       
	   	  type: "POST",                                     //设置ajax方法提交数据的形式       
	      url: "assist.action",                                      //把数据提交到ok.php       
		  data: "aname="+$("#aname").val(),       
	      success: function(msg){                 //       
	      $("#zhz").html(msg);
	      //alert(msg);                     //如果有必要，可以把msg变量的值显示到某个DIV元素中       
    	 }
	   });   
	 } 		
		
		
		


			// 发送短消息
			$("#sendMessage").dialog({
					autoOpen : false,
					height : 250,
					width : 350,
					modal : true,
					buttons : {
						'退 出' : function() {
							$(this).dialog('close');
						},
						'确 认' : function() {
								$.ajax({
									type : "POST",
									url : "sendMessage.action",
									data : {
										worksheetid : $("#worksheetid").val(),
										message : $("#messageId").val(),
										dn : $("#dn").val()          
									},
									success : function() {
										alert("短信已发送");
									}
								});
								$(this).dialog('close');
						}
					}
				});


	
	
	
			
	
	
	
	
	
	
	
		
		
	});
	
	
function sendMessage(o) {
	document.getElementById("dn").value = o
	//alert(document.getElementById("dn").value);
	$('#sendMessage').dialog('open');
}
		</script>	
	
	
	
	
	
	
	<script>
		function saveIssueActive(){
			//正整数 正则表达式
			var r = /^[0-9]*[1-9][0-9]*$/
	
			// 被帮扶者姓名
			var HName = document.getElementById('HName').value.trim();
			// 帮扶地点
			var HAddress = document.getElementById('HAddress').value.trim();
			
			// 志愿者姓名
			var VName = document.getElementById('VName').value.trim();
			// 志愿者联系电话
			var VTel = document.getElementById('VTel').value.trim();		
			// 帮扶主题
			var HTitle = document.getElementById('HTitle').value.trim();
			
			
			
			// 开始日期
			var HStartdate = document.getElementById('HStartdate').value.trim();
			// 结束日期
			var HOverdate = document.getElementById('HOverdate').value.trim();
			
			// 简介
			var HNote = FCKeditorAPI.GetInstance("HNote").GetXHTML(true);
			
			document.getElementById('worksheetno').value = datetime();
		//	document.getElementById('actaddr').value = actaddr;			
			
			if(HName == ""){
				alert("帮扶者姓名不能为空！");
		   		return false;
			}						

			if(HAddress == ""){
				alert("帮扶者地址不能为空！");
		   		return false;
			}
			
			if(VName == ""){
				alert("志愿者姓名不能为空！");
		   		return false;
			}			
			if(VTel == ""){
				alert("志愿者电话不能为空！");
		   		return false;
			}			
			if(HTitle == ""){
				alert("主题不能为空！");
		   		return false;
			}				
			
			// 开始日期不能为空
			if(HStartdate == ""){
				alert("开始日期不能为空！");
		   		return false;
			}
			// 开始日期 >= 系统日期
			var date = new Date();
		   	var y = date.getFullYear();
		   	var m = date.getMonth() + 1;
		   	var d = date.getDate();
		   	var systemDate = y + (m<10?"-0":"-")+ m +(d<10?"-0":"-")+ d;
		   	if(systemDate > HStartdate){
		   		alert("开始日期必须大于等于系统日期！");
		   		return false;
		   	}
		   	// 结束日期不能为空
			if(HOverdate == ""){
				alert("结束日期不能为空！");
		   		return false;
			}
			// 结束日期 >= 开始日期
			if(HOverdate < HStartdate){
				alert("结束日期必须大于等于开始日期！");
		   		return false;
			}


			if(HNote == ""){
				alert("简介不能为空！");
		   		return false;
			}
			// 简介长度检查
			if(getBytesLength(HNote) > 1024){
				alert("活动介绍的长度不能超过512个汉字！");
		   		return false;
			}
			document.issueForm.action="../helping/saveWorksheet.action";
			document.issueForm.submit();
		}
		//trim 的函数(去除前后空格)
		String.prototype.trim = function(){ 
			return this.replace(/^[\s|\u3000]+|[\s|\u3000]+$/g,"")
		}
		/**  
		* 得到字符串的字符长度（一个汉字占两个字符长）  
		*/  
		function getBytesLength(str) {   
		   // 在GBK编码里，除了ASCII字符，其它都占两个字符宽   
		   return str.replace(/[^\x00-\xff]/g, 'xx').length;   
		}
		
		function datetime()
		{
			var myDate = new Date();
			dd = myDate.getYear();       //获取当前年份(2位)
			dd = dd + myDate.getFullYear();   //获取完整的年份(4位,1970-????)
			dd = dd + myDate.getMonth();      //获取当前月份(0-11,0代表1月)
			dd = dd + myDate.getDate();       //获取当前日(1-31)
			dd = dd + myDate.getDay();        //获取当前星期X(0-6,0代表星期天)
			dd = dd + myDate.getTime();       //获取当前时间(从1970.1.1开始的毫秒数)
			dd = dd + myDate.getHours();      //获取当前小时数(0-23)
			dd = dd + myDate.getMinutes();    //获取当前分钟数(0-59)
			dd = dd + myDate.getSeconds();    //获取当前秒数(0-59)
			dd = dd + myDate.getMilliseconds();   //获取当前毫秒数(0-999)
			return dd;
		}
		
	  	function goPage(page){
	   $.ajax({                                                //调用jquery的ajax方法       
	   	  type: "POST",                                     //设置ajax方法提交数据的形式       
	      url: "showassist.action",                                      //把数据提交到ok.php       
		  data: "typeId="+$("#typeId").val() + "&page="+page,          
	      success: function(msg){                 //       
	      $("#zyz").html(msg);
	      //alert(msg);                     //如果有必要，可以把msg变量的值显示到某个DIV元素中       
    	 }
	   });   	  	
	  	
	  	  //document.location='/zyz/manage/helping/showWorksheet.action?page='+page;
	  	}		
		
	</script>
	<body onload="datetime()">
	
		<table width="100%" border="0" cellpadding="0" cellspacing="0">		
  			<tr>
    			<td width="17" valign="top" background="../images/mail_leftbg.gif">
    				<img src="../images/left-top-right.gif" width="17" height="29" />
    			</td>
    			<td valign="top" background="../images/content-bg.gif">
    				<table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
			      		<tr>
			        		<td height="31"><div class="titlebt">发布帮扶</div></td>
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
					            					<td class="left_txt">当前位置：&nbsp;>>&nbsp;发布帮扶</td>
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
		<table style="width:100%">
		
			<tr>
				<td style="width:15%" style="text-align:right">被帮扶者姓名：</td>
				<td style="width:35%"><input type="text" name="HName" id="HName" maxlength=64 readonly>
				<a href="javascript:void(0)" id="searczp">查找被帮扶对象</a>
				</td>
				<td style="width:15%" style="text-align:right">帮扶地点：</td>
				<td style="width:35%"><input type="text" name="HAddress" id="HAddress" maxlength=64 readonly></td>
			</tr>


			<tr>
				<td style="width:15%" style="text-align:right">志愿者姓名：</td>
				<td style="width:35%"><input type="text" name="VName" id="VName" maxlength=64 readonly>
				<a href="javascript:void(0)" id="searchp">匹配志愿者</a>
				</td>
				<td style="width:15%" style="text-align:right">志愿者联系电话：</td>
				<td style="width:35%"><input type="text" name="VTel" id="VTel" maxlength=64 readonly></td>
			</tr>

			
			<tr>
				<td style="width:15%" style="text-align:right">帮扶主题：</td>
				<td colspan=3><input type="text" name="HTitle" id="HTitle" maxlength=200 size=100></td>
			</tr>
			
			<tr>
				<td style="width:15%" style="text-align:right">开始日期：</td>
				<td style="width:35%"><input type="text" name="HStartdate" id="HStartdate" onfocus="WdatePicker();"/></td>
				<td style="width:15%" style="text-align:right">结束日期：</td>
				<td style="width:35%"><input type="text" name="HOverdate" id="HOverdate" onfocus="WdatePicker();"/></td>
			</tr>

			<tr>
				<td style="width:15%" style="text-align:right">描述：</td>
				<td  colspan=3>
					<FCK:editor instanceName="HNote" toolbarSet="ecommerce">
						<jsp:attribute name="value">
						</jsp:attribute>
						<jsp:body>
							<FCK:config SkinPath="skins/office2003/"/>
						</jsp:body>
					</FCK:editor>
				</td>
			</tr>
			
			<input type="hidden" name="worksheetid" id="worksheetid" value=${worksheetid} />
			<input type="hidden" name="worksheetno" id="worksheetno" value="" />
			<input type="hidden" name="HAreaid"  value=${currentOrgId} />
			<input type="hidden" name="VAreaid" id="VAreaid" />
			<input type="hidden" name="HTypeid" id="HTypeid" value="" />
			<input type="hidden" name="state" value="0" />		
			<input type="hidden" name="adminid" value=${username} />
			<input type="hidden" name="isdel" value="1" />
			<input type="hidden" name="sysValue" id="sysValue" value="0" />	
			<input type="hidden" name="id"  id="v_id" value="122" />							
						

		</table>
		<div align="center">
			<input type="button" name="save" value="保 存" onClick="saveIssueActive()">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="reset" name="reset" value="重 置">&nbsp;&nbsp;&nbsp;&nbsp;
							
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








		
		
		<div id="search-postulant" title="查询" style="width:100px;height:150px"> 
			选择志愿者帮扶类型：<select name="typeId" id="typeId">
				<c:forEach var="typeList" items="${typeList}" varStatus="status">
					<option value="${typeList.paramid}">${typeList.paramkey}</option>
				
				</c:forEach>
				</select>
				
				
				<div id="zyz"></div>
				
		</div>		
		
		
		<div id="search-helping" title="查询帮扶对象" style="width:100px;height:150px"> 
			帮扶对象姓名：	<input type="text" name="aname" id="aname" />

				
				<div id="zhz"></div>
				
		</div>		
		
		



		<div id="sendMessage" title="发送短信" style="display:none">
			<div id="inputNumDiv"></div>
			<input type="hidden" id="dn" name="dn" />
			<textarea name="message" id="messageId" cols="50" rows="5" onKeyUp="javasrript:getInputNum()"> </textarea>					
		</div>
		
				
	</body>
</html>