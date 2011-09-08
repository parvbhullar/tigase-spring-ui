<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>airAD</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="style/jquery.js"></script>
</head>

<body>
<?php include("common/header-member.php"); ?>
<div id="main">
<div class="mainCon">
<div class="wa">只有在您提供会员信息并通过审核之后，您的广告才可以被发布。</div>
<h1 class="tit">会员信息 <span class="orange">[未通过审核]</span></h1>
<div class="leftCon">
<h1>个人信息</h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
<col width="30%" /><col width="70%" />
  <tr>
    <th><span class="must">*</span>姓名</th>
    <td><input type="text" name="input5" id="input5" class="half" /></td>
    </tr>
  <tr>
    <th><span class="must">*</span>性别</th>
    <td><select name="select3">
      <option>请选择&hellip;</option>
      <option>男</option>
      <option>女</option>
    </select></td>
    </tr>
  <tr>
    <th><span class="must">*</span>出生年月</th>
    <td><span style="border:0">
      <input type="text" name="input9" id="input9" class="cal" />
    </span></td>
    </tr>
  <tr>
    <th>所在城市</th>
    <td><select name="select6">
      <option>江南京南京南</option>
      </select>
      <select name="select7">
        <option>南京南京南京</option>
      </select></td>
    </tr>
  <tr>
    <th><span class="must">*</span>证件号码</th>
    <td>
      <select name="select4">
        <option selected="selected">请选择…</option>
        <option>身份证</option>
      </select>
      <input type="text" name="input" id="input" class="short" /></td>
    </tr>
  <tr>
    <th><span class="must">*</span>手机</th>
    <td><input type="text" name="input7" id="input7" class="half" /></td>
  </tr>
  <tr>
    <th>固定电话</th>
    <td><input type="text" name="input2" id="input2" class="half" /></td>
    </tr>
  <tr>
    <th><span class="must">*</span>通讯地址</th>
    <td><input type="text" name="input10" id="input10" class="half" /></td>
    </tr>
  <tr>
    <th>邮编</th>
    <td><input type="text" name="input16" id="input16" class="half" /></td>
    </tr>
  <tr>
    <th>职业</th>
    <td><input type="text" name="input4" id="input4" class="half" /></td>
    </tr>
</table>
<h1>公司信息</h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
<col width="30%" /><col width="70%" />
  <tr>
    <th><span class="must">*</span>公司名称</th>
    <td><input type="text" name="input17" id="input17" class="half" /></td>
    </tr>
  <tr>
    <th><span class="must">*</span>公司行业</th>
    <td><select name="select8" id="select2">
      <option>互联网</option>
    </select></td>
    </tr>
  <tr>
    <th><span class="must">*</span>公司性质</th>
    <td><select name="select9" id="select3">
      <option>民营</option>
    </select></td>
    </tr>
  <tr>
    <th><span class="must">*</span>公司人数</th>
    <td><select name="select10" id="select4">
      <option>1000人以上</option>
    </select></td>
    </tr>
  <tr>
    <th><span class="must">*</span>所在地</th>
    <td><select name="select">
      <option>江南京南京南</option>
    </select>
      <select name="select">
        <option>南京南京南京</option>
      </select></td>
    </tr>
  <tr>
    <th><span class="must">*</span>公司网址</th>
    <td><input type="text" name="input3" id="input3" class="half" /></td>
    </tr>
  <tr>
    <th><span class="must">*</span>详细地址</th>
    <td><input type="text" name="input18" id="input18" class="half" /></td>
    </tr>
  <tr>
    <th>邮编</th>
    <td><input type="text" name="input11" id="input11" class="half" /></td>
    </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
<col width="30%" /><col width="70%" />
  <tr>
    <th>&nbsp;</th>
    <td><div class="btnBox"><a href="javascript:void(0)" class="btnY fl"><span>提交</span></a> </div></td>
  </tr>
</table>
</div>
<div class="rightCon">
<div class="infoCon">
<h2>提示</h2>
<ul>
<li>广告主需要提供营业执照号码来验证账户的真实性。</li>
<li>请您提供真实有效的信息，这样更有助于广告的发布和推广。</li>
<li>如果您需要修改登录邮箱，从这里进入 <a href="javascript:void(0)">修改登录邮箱 &raquo;</a></li>
</ul>
</div>
</div>
</div>
</div>
<?php include("common/footer.php"); ?>
</body>
</html>