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
			<div class="container">
  <div class="column span-8 last">
	
          <p>
            <label for="dummy5">Select field</label>
            <select id="dummy5" name="dummy5">
              <option value="1">Ottawa</option>
              <option value="2">Calgary</option>
              <option value="3">Moosejaw</option>
            </select>
          </p>

          <p>
            <label for="dummy6">Text input (title)</label>
            <input type="text" class="title" name="dummy6" id="dummy6" value="Field with class .title">
          </p>

          <p>
            <label for="dummy7">Select field</label>
            <select id="dummy7" name="dummy7">
              <option value="1">Ottawa</option>
              <option value="2">Calgary</option>
              <option value="3">Moosejaw</option>
            </select>
            <label for="dummy8">Another field</label>
            <input type="text" class="text" id="dummy8" name="dummy8" value="Field with class .text">
          </p>
  </div>
</div>
		</div>
	</div>
	
</body>
</html>