<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>airAD</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="style/jquery.js"></script>
</head>

<body>
<?php include("common/header.php"); ?>
<div id="main">
<div class="mainCon">
<div class="stepNew">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<col width="33%" /><col width="33%" /><col />
  <tr>
    <td class="now">1. 广告活动信息</td>
    <td>2. 设置广告组投放目标</td>
    <td>3. 设置广告并出价</td>
    </tr>
</table>
</div>
<div class="leftCon">
<h1>广告活动信息</h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <col width="20%" />
        <col width="80%" />
        <tr>
          <th>活动名称</th>
          <td><input class="long" /></td>
        </tr>
        <tr>
          <th>开始时间</th>
          <td><input type="password" class="cal" /></td>
        </tr>
        <tr>
          <th>是否有结束时间</th>
          <td><select name="select2">
            <option selected="selected">否</option>
            <option>是</option>
          </select></td>
        </tr>
        <tr>
          <th>每日预算</th>
          <td><sup>&yen;</sup> <input class="tiny" /> <small> 最低<sup>&yen;</sup>20</small></td>
        </tr>
        <tr>
          <th>活动说明</th>
          <td><textarea rows="6" class="long"></textarea></td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox">
           <a href="owner-ad-add-s2.php" class="btnY fl"><span>提交，进行下一步</span></a><div class="moreBtn"><span class="gray">|</span> <a href="index.php">取消</a></div>
           </div>
          </td>
        </tr>
      </table>
</div>
<div class="rightCon">
<div class="infoCon">
<h2><img src="images/ico_act.gif" alt="活动" width="16" height="16" align="absmiddle" />活动摘要</h2>
<ul>
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