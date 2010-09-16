<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>结果画面</title> 
    <style>
    	body {font-size: 12px;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;background-color: #EEF2FB;}
		a.MzTreeview /* TreeView 链接的基本样式 */ { cursor: hand; color: #000080; margin-top: 5px; padding: 2 1 0 2; text-decoration: none; }
		.MzTreeview a.select /* TreeView 链接被选中时的样式 */ { color: highlighttext; background-color: highlight; }
		#kkk input {
		vertical-align:middle;
		}
		.MzTreeViewRow {border:none;width:500px;padding:0px;margin:0px;border-collapse:collapse}
		.MzTreeViewCell0 {border-bottom:1px solid #CCCCCC;padding:0px;margin:0px;}
		.MzTreeViewCell1 {border-bottom:1px solid #CCCCCC;border-left:1px solid #CCCCCC;width:200px;padding:0px;margin:0px;}
	</style>  
  </head>
  <body id="main_body">  
   	<c:if test="${result == 1}">
   		<script language="javascript">
   			alert('保存成功!');
   		</script>
   	</c:if>
   	<c:if test="${result == 0}">
   		<script language="javascript">
   			alert('保存失败!');
   		</script>
   	</c:if>
   	<script language="javascript">
   		window.location = "/zyz/manage/active/showactive.action";
   	</script>
   	<br />
   	<br />
   	<br />
   	<br />
   	<br />
   	<br />
   	<br />
   	<br />
  </body>
  </html>
  