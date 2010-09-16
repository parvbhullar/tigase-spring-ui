<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authentication property="principal" var="authentication"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><%//= title %> - 管理页面</title>
		<base target="main">
		<link href="../images/skin.css" rel="stylesheet" type="text/css">
		<script language=JavaScript>
			function logout(){
				if (confirm("您确定要退出控制面板吗？"))
				top.location = "out.asp";
				return false;
			}
		</script>
		<script language=JavaScript1.2>
			function showsubmenu(sid) {
				var whichEl = eval("submenu" + sid);
				var menuTitle = eval("menuTitle" + sid);
				if (whichEl.style.display == "none"){
					eval("submenu" + sid + ".style.display=\"\";");
				}else{
					eval("submenu" + sid + ".style.display=\"none\";");
				}
			}
		</script>
		<style>
		body {font-size:12px;}
		</style>
		
<script language="javascript" type="text/javascript" src="../images/jquery-1.4.2.min.js"></script>		

		
<script>

function myajax(){
$.ajax({
	   	type: "POST",                                     //设置ajax方法提交数据的形式       
	    url: "/zyz/manage/helping/showCount.action",                                      //把数据提交到ok.php                
	    success: function(msg){                 //       
	    $("#zyz").html(msg);
 }
});
}

setInterval("myajax();", 60000);

</script>		
		
		
	</head>
	<body leftmargin="0" topmargin="0" >
		<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  			<tr>
    			<td width="61%" height="64"><img src="../images/logo.gif" width="262" height="64"></td>
    			<td width="39%" valign="top">
    				<table width="100%" border="0" cellspacing="0" cellpadding="0">
      					<tr>
        					<td width="40%" height="38" alert="left" style="color:white"><b>${authentication.nickName}</b> 您好！</td>
							<td width="34%" alert="right" style="color: white" id="zyz">
								
							</td>
							<td width="22%"><a href="../j_spring_security_logout" target="_top"><img src="../images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
        					<td width="4%">&nbsp;</td>
      					</tr>
      					<tr>
        					<td height="19" colspan="4">&nbsp;</td>
        				</tr>
    				</table>
    			</td>
  			</tr>
		</table>
	</body>
</html>