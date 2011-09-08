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
  <h1 class="tit">注册新帐号 <small>只需一步就可以成为我们的会员</small></h1>
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
          <th>确认密码</th>
          <td><input class="half" /></td>
        </tr>
        <tr>
          <th>验证码</th>
          <td><table border="0" cellspacing="0" cellpadding="0" style="width:260px" class="tabIn">
            <col />
            <col width="120px" />
            <col width="90px" />
            <tr>
              <td><input type="text" size="4" /></td>
              <td class="c"><img src="images/code.gif" alt=" " /></td>
              <td><a href="javascript:void(0)">看不清，换一张</a></td>
            </tr>
          </table></td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox">
           <a href="suc.php" class="btnY fl"><span>同意以下协议，提交注册信息</span></a>
           </div>
           <div style="padding-left:45px"><a href="javascript:void(0)">点击阅读 airAD 用户协议</a></div>
          </td>
        </tr>
      </table>
</div>
<div class="rightCon">
<div class="infoCon">
<h2 style="margin-bottom:0"><a href="login.php">已经有帐号？登录 &raquo;</a></h2>
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