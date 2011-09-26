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
<div class="stepNew sp2">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<col width="33%" /><col width="33%" /><col />
  <tr>
    <td>1. 广告活动信息</td>
    <td class="now">2. 设置广告组投放目标</td>
    <td>3. 设置广告并出价</td>
    </tr>
</table>
</div>
<div class="leftCon">
<h1>设置广告组投放目标</h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <col width="20%" />
        <col width="80%" />
        <tr>
          <th>广告组名称</th>
          <td><input class="long" /></td>
        </tr>
        <tr>
          <th>平台/设备</th>
          <td>
          <div class="selectBox">
<table border="0" cellspacing="0" cellpadding="0" class="tabN">
        <col width="60%" />
        <col width="40%" />
  <tr>
    <td><img src="images/list.gif" alt="list" width="235" height="364" /></td>
    <td>
    <strong>iphone 操作系统</strong>
    <div class="mb">最低<select name="select2">
            <option selected="selected">2.0</option>
            <option>3.0</option>
          </select><br />
          最高<select name="select2">
            <option selected="selected">无上限</option>
          </select>
</div>
    <strong>Android 操作系统</strong>
    <div>最低<select name="select2">
            <option selected="selected">2.0</option>
            <option>3.0</option>
          </select><br />
          最高<select name="select2">
            <option selected="selected">无上限</option>
          </select></div>
    </td>
  </tr>
</table>

          </div>
          </td>
        </tr>
        <tr>
          <th>地理位置</th>
          <td><input name="1" type="radio" value="" checked="checked" /> 面向所有地理位置<br />
          <input name="1" type="radio" value="" /> 面向特定地理位置
          </td>
        </tr>
        <tr>
          <th>运营商</th>
          <td>
          <input name="2" type="radio" value="" /> 针对所有流量，包括 Wi-Fi 流量<br />
          <input name="2" type="radio" value="" /> 仅针对 Wi-Fi 流量<br />
          <input name="2" type="radio" value="" checked="checked" /> 面向手机运营商流量
          <div class="selectBox">
          <input name="3" type="radio" value="" checked="checked" /> 联通<br />
          <input name="3" type="radio" value="" /> 移动
          </div>
          </td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox">
           <a href="owner-ad-add-s3.php" class="btnY fl"><span>提交，进行下一步</span></a><div class="moreBtn"><span class="gray">|</span> <a href="owner-ad-gup.php">取消</a></div>
           </div>
            </td>
        </tr>
      </table>
</div>
<div class="rightCon">
<div class="infoCon">
<h2><img src="images/ico_act.gif" alt="活动" width="16" height="16" align="absmiddle" />活动摘要</h2>
<ul>
<li><span class="fr">宣传米田科技有限公司的...</span>名称</li>
<li><span class="fr sml">2010-12-24 00:00</span>开始时间</li>
<li><span class="fr sml"><sup>&yen;</sup>50</span>每日预算</li>
</ul>
<h2><img src="images/ico_gup.gif" alt="活动" width="16" height="16" align="absmiddle" />广告组摘要</h2>
<ul>
<li>名称</li>
<li><span class="fr">4个操作系统/8个设备</span>平台/设备<br /><span></span></li>
<li><span class="fr">10个国家/地区</span>地理位置</li>
<li><span class="fr">联通</span>运营商</li>
</ul>
</div>
</div>
</div>
</div>
<?php include("common/footer.php"); ?>
</body>
</html>