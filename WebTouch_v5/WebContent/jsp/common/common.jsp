<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  
<script type="text/javascript" src="jsp/common/common.js"></script>

</head>
<body>
<a href="#" class="button">查询</a>
<a href="#" class="button" id="add">新增</a>
<a href="#" class="button">删除</a>

	<table id="listTable"></table>
	<div id="page"></div>
	<div style="visibility:hidden;">
		<div id="addForm" >
			
      <div class="span-10">
        <form id="dummy" action="" method="post">
            <p>
              <label for="dummy0">用户名</label><br>
              <input type="text" class="text" name="dummy0" id="dummy0" value=>
            </p>

            <p>
              <label for="dummy1">密     码</label><br>
              <input type="text" class="text" id="dummy1" name="dummy1" value="">
            </p>

            <p>
              	<a href="#" class="button" id="save">保存</a>
				<a href="#" class="button" id="cancel">关闭</a>
            </p>
        </form>
      </div>
		</div>
	</div>
	
</body>
</html>