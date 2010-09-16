<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>jsTree v.1.0 - json_data documentation</title>
	<script type="text/javascript" src="../../../javascript/jstree/_lib/jquery.js"></script>
	<script type="text/javascript" src="../../../javascript/jstree/_lib/jquery.cookie.js"></script>
	<script type="text/javascript" src="../../../javascript/jstree/_lib/jquery.hotkeys.js"></script>
	<script type="text/javascript" src="../../../javascript/jstree/jquery.jstree.js"></script>
	
	<script type="text/javascript" src="../../../javascript/jquery-easyui-1.2/jquery.easyui.min.js"></script>
	
	<script type="text/javascript" src="../../../javascript/json2.js"></script>
	<script type="text/javascript" src="tree.js"></script>
	
	<link type="text/css" rel="stylesheet" href="../../../javascript/jquery-easyui-1.2/themes/default/easyui.css"/>
	<link type="text/css" rel="stylesheet" href="../../../javascript/jquery-easyui-1.2/themes/dicon.css"/>
	
	<link type="text/css" rel="stylesheet" href="syntax/!style.css"/>
	<link type="text/css" rel="stylesheet" href="!style.css"/>
	<script type="text/javascript" src="syntax/!script.js"></script>
</head>
<body>
<div class="panel">

<script>
		$(function(){
			$('#dd').dialog({
				toolbar:[{
					text:'Add',
					iconCls:'icon-add',
					handler:function(){
						alert('add')
					}
				},'-',{
					text:'Save',
					iconCls:'icon-save',
					handler:function(){
						alert('save')
					}
				}],
				buttons:[{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						alert('ok');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dd').dialog('close');
					}
				}]
			});
		});
		function open1(){
			$('#dd').dialog('open');
		}
		function close1(){
			$('#dd').dialog('close');
		}
	</script>
<script type="text/javascript" class="source">
$(function () {
	
});
</script>

<h1>Dialog</h1>
	<div style="margin-bottom:10px;">
		<a href="#" onclick="open1()">open1</a>
		<a href="#" onclick="close1()">close1</a>
	</div>
	<div id="dd" icon="icon-save" style="padding:5px;width:400px;height:400px;">
		
	</div>

<h3>南通组织</h3>
<div id="demo4" class="demo"></div>

<h3>南通组织CHECKBOX</h3>
<div id="demo5" class="demo"></div>
<input type="button" onclick="getAllCheckedLeaf()" value="取所有选中的叶子节点"/>
<input type="button" onclick="getAllCheckedNoLeaf()" value="非叶子节点"/>
</div>

</div>
</body>
</html>