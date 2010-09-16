<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.linkage.app.gqt.purview.entity.*" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/common/jstl_contexpath.jsp"%>
<sec:authentication property="principal.menus" var="menus"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>管理页面</title>
		<script src="../javascript/prototype.lite.js" type="text/javascript"></script>
		<script src="../javascript/moo.fx.js" type="text/javascript"></script>
		<script src="../javascript/moo.fx.pack.js" type="text/javascript"></script>
		<style type="text/css">
			body {font:12px Arial, Helvetica, sans-serif;color: #000;background-color: #EEF2FB;margin: 0px;}
			#container {width: 182px;}
			H1 {font-size: 12px;margin: 0px;width: 182px;cursor: pointer;height: 30px;line-height: 20px;}
			H1 a {display: block;width: 182px;color: #000;height: 30px;text-decoration: none;moz-outline-style: none;background-image: url(../images/menu_bgs.gif);background-repeat: no-repeat;line-height: 30px;text-align: center;margin: 0px;padding: 0px;}
			.content{width: 182px;height: 26px;}
			.MM ul {list-style-type: none;margin: 0px;padding: 0px;display: block;}
			.MM li {font-family: Arial, Helvetica, sans-serif;font-size: 12px;line-height: 26px;color: #333333;list-style-type: none;display: block;text-decoration: none;height: 26px;width: 182px;padding-left: 0px;}
			.MM {width: 182px;margin: 0px;padding: 0px;left: 0px;top: 0px;right: 0px;bottom: 0px;clip: rect(0px,0px,0px,0px);}
			.MM a:link {font-family: Arial, Helvetica, sans-serif;font-size: 12px;line-height: 26px;color: #333333;background-image: url(../images/menu_bg1.gif);background-repeat: no-repeat;height: 26px;width: 182px;display: block;text-align: center;margin: 0px;padding: 0px;overflow: hidden;text-decoration: none;}
			.MM a:visited {font-family: Arial, Helvetica, sans-serif;font-size: 12px;line-height: 26px;color: #333333;background-image: url(../images/menu_bg1.gif);background-repeat: no-repeat;display: block;text-align: center;margin: 0px;padding: 0px;height: 26px;width: 182px;text-decoration: none;}
			.MM a:active {font-family: Arial, Helvetica, sans-serif;font-size: 12px;line-height: 26px;color: #333333;background-image: url(../images/menu_bg1.gif);background-repeat: no-repeat;height: 26px;width: 182px;display: block;text-align: center;margin: 0px;padding: 0px;overflow: hidden;text-decoration: none;}
			.MM a:hover {font-family: Arial, Helvetica, sans-serif;font-size: 12px;line-height: 26px;font-weight: bold;color: #006600;background-image: url(../images/menu_bg2.gif);background-repeat: no-repeat;text-align: center;display: block;margin: 0px;padding: 0px;height: 26px;width: 182px;text-decoration: none;}
		</style>
	</head>
	<body>
		<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
  			<tr>
    			<td width="182" valign="top">
    				<div id="container">
    					<!--
    					<c:forEach var="menu" items="${menus}" varStatus="status">
    						
    					</c:forEach>
      					-->
      					<%
      					List<Menu> menuList=(List<Menu>)pageContext.getAttribute("menus");
      					int q_level=0;//前一个层级
      					int c_level=0;//当前层级
      					for(Menu menu:menuList){
      						System.out.println(menu.getMenuName());
      						q_level=c_level;
      						c_level=menu.getLevel();
      						if(c_level==1&&q_level==0){
      							out.println("<h1 class=\"type\"><a href=\"javascript:void(0)\">"+menu.getMenuName()+"</a></h1>");
      						}else if(c_level==1&&q_level>c_level){
      							out.println("</ul>");
      							out.println("</div>");
      							out.println("<h1 class=\"type\"><a href=\"javascript:void(0)\">"+menu.getMenuName()+"</a></h1>");
      						}else if(c_level>1&&q_level==c_level){
      							out.println("<li><a href=\""+menu.getUrl()+"\" target=\"main\">"+menu.getMenuName()+"</a></li>");
      						}else if(c_level>1&&q_level<c_level){
      							out.println("<div class=\"content\">");
      							out.println("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td><img src=\"../images/menu_topline.gif\" width=\"182\" height=\"5\" /></td></tr></table>");
      							out.println("<ul class=\"MM\">");
      							out.println("<li><a href=\""+menu.getUrl()+"\" target=\"main\">"+menu.getMenuName()+"</a></li>");
      						}
      					}
      					
      					if(c_level>1){
      						out.println("</ul>");
  							out.println("</div>");
      					}
      					%>
      					
						<!-- 
      					<h1 class="type"><a href="javascript:void(0)">统计管理</a></h1>
      					<div class="content">
        					<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="../images/menu_topline.gif" width="182" height="5" /></td></tr></table>
        					<ul class="MM">
				          		<li><a href="/zyz/manage/active/initActiveCount.action" target="main">活动统计</a></li>
				          		<li><a href="/zyz/manage/helping/helpingCount.action" target="main">帮扶统计</a></li>				          		
        					</ul>
      					</div> 

      					<h1 class="type"><a href="javascript:void(0)">组织机构管理</a></h1>
      					<div class="content">
        					<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td><img src="../images/menu_topline.gif" width="182" height="5" /></td></tr></table>
        					<ul class="MM">
				          		<li><a href="/zyz/manage/org/orgtype.action" target="main">类型管理</a></li>
				          		<li><a href="/zyz/manage/org/orgarea.action" target="main">区域管理</a></li>
				          		<li><a href="/zyz/manage/org/org.action" target="main">机构管理</a></li>
        					</ul>
      					</div>
      					<h1 class="type"><a href="javascript:void(0)">用户管理</a></h1>
      					<div class="content">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          	<tr>
					           		<td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
					          	</tr>
					        </table>
					        <ul class="MM">
					          	<li><a href="../reguser/show.action" target="main">用户管理</a></li>
					        </ul>
      					</div>
      					<h1 class="type"><a href="javascript:void(0)">活动管理</a></h1>
      					<div class="content">
					        <table width="100%" border="0" cellspacing="0" cellpadding="0">
					          	<tr>
					           		<td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
					          	</tr>
					        </table>
					        <ul class="MM">
					          	<li><a href="../active/showactive.action" target="main">活动管理</a></li>
					          	<li><a href="../active/activeconfirm.action" target="main">活动确认</a></li>
					        </ul>
      					</div>
        				<h1 class="type"><a href="javascript:void(0)">系统参数管理</a></h1>
      					<div class="content">
				          	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				            	<tr>
				              		<td><img src="../images/menu_topline.gif" width="182" height="5" /></td>
				            	</tr>
				          	</table>
					        <ul class="MM">
					            <li><a href="javascript:void(0)" target="main">上传管理</a></li>
					          	<li><a href="javascript:void(0)" target="main">参数管理</a></li>
					        </ul>
      					</div> -->
      				</div>
        			<script type="text/javascript">
						var contents = document.getElementsByClassName('content');
						var toggles = document.getElementsByClassName('type');
						var myAccordion = new fx.Accordion(
							toggles, contents, {opacity: true, duration: 400}
						);
						myAccordion.showThisHideOpen(contents[0]);
					</script>
        		</td>
  			</tr>
		</table>
	</body>
</html>