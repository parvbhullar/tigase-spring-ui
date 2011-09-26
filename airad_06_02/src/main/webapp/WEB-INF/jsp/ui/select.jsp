<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>airAD</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="style/jquery.js"></script>
</head>

<body>
<div class="okBox">
<div class="ok">邮箱验证成功</div>
</div>
<?php include("common/header-basic.php"); ?>
<div id="main">
<div class="mainCon">
  <h1 class="tit">选择您的角色</h1>
<div class="info">请选择您的角色，完善会员信息</div>
<div class="halfCon" style="margin-right:27px" id="con01">
<h1 class="orange">广告主</h1>
<ul>
<li>广告主可着干那，干着干那。以干着干那。以干着干那，干着干那。</li>
<li>广告主可以干着干可以干着干那，干着干那。</li>
<li>广告主可以干着干那，干着干那。</li>
<li>广可以干着干那，干着干那。以干那。以干着干那，干着干那。</li>
</ul>
<div class="c"><img src="images/test_intro01.jpg" alt="我是广告主" width="220" height="220" /></div>
<div class="btnBox">
<a href="member-edit.php" class="btnY half c" style="margin:auto"><span>我是广告主</span></a>
</div>
</div>
<div class="halfCon" id="con02">
<h1 class="orange">开发者</h1>
<ul>
<li>开发者可以干那，干着干那。以干着干那。以干着干那，干着干那。</li>
<li>开发者可以干着干可以干着干那，干着干那。</li>
<li>开发者可以干着干那，干着干那。</li>
<li>开干着干那，干着干那。以干着干那。以干着干那，干着干那。</li>
</ul>
<div class="c"><img src="images/test_intro02.jpg" alt="我是开发者" width="220" height="220" /></div>
<div class="btnBox">
<a href="member-edit.php" class="btnY half c" style="margin:auto"><span>我是开发者</span></a>
</div>
</div>
</div>
</div>
<?php include("common/footer.php"); ?>
<script type="text/javascript">
$(document).ready(function(){
  $("#con01").mouseover(function(){
                  $("#con01").addClass("now");
                  })
  $("#con01").mouseout(function(){
                  $("#con01").removeClass("now");
                  })
  $("#con02").mouseover(function(){
                  $("#con02").addClass("now");
                  })
  $("#con02").mouseout(function(){
                  $("#con02").removeClass("now");
                  })
})
</script>
</body>
</html>