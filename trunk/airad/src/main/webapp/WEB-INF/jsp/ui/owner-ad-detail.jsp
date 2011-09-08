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
<a href="owner-ad-add-s3.php" class="btnY fr"><span>新建广告</span></a>
<h1><span>广告管理 &raquo; <a href="index.php">活动管理</a> &raquo; <a href="owner-ad-gup.php"><img src="images/ico_act.gif" alt="活动" width="16" height="16" align="absmiddle" />宣传米田科技有限公司...</a> &raquo;</span> <img src="images/ico_gup.gif" alt="广告组" width="16" height="16" align="absmiddle" />佳能单反相关广告 <span><a href="javascript:void(0)" id="btn01">显示摘要</a><a href="javascript:void(0)" id="btn02" style="display:none">隐藏摘要</a> | <a href="owner-ad-gup-edit.php"><img src="images/ico_edit.gif" alt="编辑" width="16" height="16" align="absmiddle" />编辑</a></span></h1>
<div class="info" id="con01" style="display:none">
<table border="0" cellspacing="0" cellpadding="0" class="tabN">
<col width="12%"><col width="38%"><col width="12%"><col width="38%">
  <tr>
    <th>名称：</th>
    <td>佳能单反相关广告</td>
    <th>类型：</th>
    <td>手机网络</td>
  </tr>
  <tr>
    <th>平台/设备：</th>
    <td>所有手机</td>
    <th>地理位置/运营商</th>
    <td>所有地理位置</td>
  </tr>
</table>
</div>
<table border="0" cellspacing="0" cellpadding="0" class="tabYH">
<col /><col width="12%" /><col width="12%" /><col width="12%" /><col width="12%" /><col width="15%" />
  <tr>
    <th>广告名称</th>
    <th>展示次数</th>
    <th>出价</th>
    <th><a href="javascript:void(0)">创建时间<img src="images/ico_sortza.gif" alt="排序" /></a></th>
    <th><a href="javascript:void(0)">生效时间<img src="images/ico_sort.gif" alt="排序" /></a></th>
    <th>状态</th>
    </tr>
  <tr>
    <td><img src="images/ico_single.gif" alt="广告" width="16" height="16" align="absmiddle" />宣传米田科技有限公司的广告 <a href="javascript:void(0)"><img src="images/ico_view.gif" width="16" height="16" alt="预览" align="absmiddle" /></a></td>
    <td>5,234</td>
    <td><sup>&yen;</sup>0.50</td>
    <td>2010-12-23<br />
      20:50</td>
    <td>2010-12-25<br />
      0:00</td>
    <td>发布中</td>
  </tr>
  <tr class="over">
    <td><img src="images/ico_single.gif" alt="广告" width="16" height="16" align="absmiddle" />在应用中植入airAD关于苏宁电器的节日宣传 <a href="javascript:void(0)"><img src="images/ico_view.gif" width="16" height="16" alt="预览" align="absmiddle" /></a>
    <div class="ctl"><a href="javascript:void(0)" class="btnS"><span>修改出价</span></a><a href="owner-ad-add-s3.php" class="btnS"><span>编辑</span></a><a href="owner-ad-add-s3.php" class="btnS"><span>复制</span></a><span class="gray">|</span><a href="javascript:void(0)">停用</a><a href="javascript:void(0)">删除</a></div></td>
    <td>3</td>
    <td><sup>&yen;</sup>0.20</td>
    <td>2010-12-23<br />
      20:50</td>
    <td>2010-12-25<br />
      0:00</td>
    <td>发布中</td>
  </tr>
  <tr>
    <td><img src="images/ico_single.gif" alt="广告" width="16" height="16" align="absmiddle" />苏宁地产2011年投放广告 <a href="javascript:void(0)"><img src="images/ico_view.gif" width="16" height="16" alt="预览" align="absmiddle" /></a></td>
    <td>0</td>
    <td><sup>&yen;</sup>0.50</td>
    <td>2010-12-23<br />
      20:50</td>
    <td>2010-12-25<br />
      0:00</td>
    <td><span class="orange">提醒</span><br />
      <small>正在审核</small></td>
  </tr>
  <tr>
    <td><img src="images/ico_single.gif" alt="广告" width="16" height="16" align="absmiddle" />在应用中植入airAD关于苏宁电器的节日宣传 <a href="javascript:void(0)"><img src="images/ico_view.gif" width="16" height="16" alt="预览" align="absmiddle" /></a></td>
    <td>6,345,354</td>
    <td><sup>&yen;</sup>0.12</td>
    <td>2010-12-23<br />
      20:50</td>
    <td>2010-12-25<br />
      0:00</td>
    <td>发布中</td>
  </tr>
  </table>
<div class="pager">共 <strong>4</strong> 条记录</div>
</div>
</div>
<?php include("common/footer.php"); ?>
<script type="text/javascript">
$(document).ready(function(){
  $("#btn01").click(function(){
                  $("#con01").show();
                  $("#btn02").show();
                  $("#btn01").hide();
                  })
  $("#btn02").click(function(){
                  $("#con01").hide();
                  $("#btn01").show();
                  $("#btn02").hide();
                  })
})
</script>
</body>
</html>