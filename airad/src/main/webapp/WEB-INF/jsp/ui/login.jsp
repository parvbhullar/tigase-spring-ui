<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>airAD</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="style/jquery.js"></script>
<style type="text/css">
<!--
.memInfo{display:none}
-->
</style>
</head>

<body>
<?php include("common/header-basic.php"); ?>
<div id="main">
<div class="mainCon">
  <div class="leftCon">
  <h1 class="tit">会员登录</h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF reg">
        <col width="30%" />
        <col width="70%" />
        <tr>
          <th>电子邮箱</th>
          <td><input class="half" /></td>
        </tr>
        <tr>
          <th>密码</th>
          <td><input class="half" /></td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td><input type="checkbox" name="checkbox" id="checkbox" /> <small>一周内免登录</small></td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox">
           <a href="index.php" class="btnY fl"><span>登录</span></a><div class="moreBtn"><span class="gray">|</span> <a href="javascript:void(0)">找回密码</a></div>
           </div></td>
        </tr>
      </table>
</div>
<div class="rightCon">
<div class="infoCon">
<h2 style="margin-bottom:0"><a href="reg.php">没有帐号？一步免费注册 &raquo;</a></h2>
<ul style="display:none">
<li>名称</li>
<li><span class="fr sml">2010-12-24 00:00</span>开始时间</li>
<li><span class="fr sml"><sup>&yen;</sup>50</span>每日预算</li>
</ul>
</div>
</div>
</div>
</div>
<?php include("common/footer.php"); ?>
</body>
</html>