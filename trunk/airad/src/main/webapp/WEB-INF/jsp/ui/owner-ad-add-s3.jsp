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
<div class="stepNew sp3">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<col width="33%" /><col width="33%" /><col />
  <tr>
    <td>1. 广告活动信息</td>
    <td>2. 设置广告组投放目标</td>
    <td class="now">3. 设置广告并出价</td>
    </tr>
</table>
</div>
<div class="leftCon">
<h1>设置广告并出价</h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <col width="20%" />
        <col width="80%" />
        <tr>
          <th>广告类型</th>
          <td><input name="1" type="radio" value="" /> 富媒体广告<br />
              <input name="1" type="radio" value="" checked="checked" /> 贫媒体广告</td>
        </tr>
        <tr>
          <th>广告名称</th>
          <td><input class="long" /></td>
        </tr>
        <tr>
          <th>广告链接</th>
          <td><input class="long" /></td>
        </tr>
        <tr>
          <th>点击 Banner 后</th>
          <td><select name="select3">
            <option selected="selected">请选择...</option>
            <option>展示广告内容页面</option>
            <option>拨打电话</option>
            <option>发送短信</option>
            <option>只展示 Banner 无内容</option>
          </select></td>
        </tr>
      </table>
      <h4>广告 Banner 制作</h4>
<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
        <col width="20%" />
        <col width="80%" />
        <tr>
          <th>Banner 类型</th>
          <td><select name="select2">
              <option>请选择...</option>
              <option>文字</option>
              <option>图片</option>
              <option selected="selected">图片+文字</option>
            </select></td>
        </tr>
        <tr>
          <th>Banner 位置</th>
          <td><select name="select2">
            <option selected="selected">下方</option>
            <option>上方</option>
          </select></td>
        </tr>
        <tr>
          <th>弹出效果</th>
          <td><select name="select">
            <option selected="selected">从下往上</option>
            <option>从上往下</option>
          </select></td>
        </tr>
        <tr>
          <th>Banner 图片设置</th>
          <td>
            <div class="ctl"><a href="javascript:void(0)" class="btnS" id="btn01"><span>从素材库中选择</span></a><span class="gray">或者</span><a href="javascript:void(0)" class="btnS" id="btn02"><span>从本地上传</span></a></div>
          </td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
            <div class="expl mb collapsed" style="width:320px;height:320px" id="img01">
            <img src="images/test_img01.jpg" width="320" height="320" alt=" " /></div>
            <div>手机 <small>320px*48px</small>
            <div class="expl" style="width:320px;height:48px"></div>
            </div>
            <div>平板 <small>468px*60px</small>
              <div class="expl" style="width:468px;height:60px"></div>
          </div></td>
        </tr>
        <tr>
          <th>Banner文字</th>
          <td><input class="long" /></td>
        </tr>
</table>
<h4><a href="javascript:void(0)" id="btn04" class="fr">效果预览&raquo;</a><a href="javascript:void(0)" class="collapsed fr" id="btn05">关闭预览&raquo;</a>广告内容制作</h4>
<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
        <col width="20%" />
        <col width="80%" />
        <tr>
          <th>Wap 站点选择</th>
          <td><div class="showWap">
          <div class="bgWap collapsed">
          </div>
          <select name="select4">
            <option selected="selected">请选择...</option>
            <option>宣传苏宁电器的wap页面</option>
            <option>佳能的wap页面</option>
            <option>创建新站点</option>
          </select></div></td>
        </tr>
        <tr>
    <th>Wap 站点名称</th>
    <td><input class="long" /><br />
<small>如果您重命名站点名称，在提交广告后，将保存为新 Wap 站点。</small>
</td>
    </tr>
  <tr>
    <th>图片</th>
    <td><div class="ctl"><a href="javascript:void(0)" class="btnS"><span>从素材库中选择</span></a><span class="gray">或者</span><a href="javascript:void(0)" class="btnS"><span>从本地上传</span></a></div>
    <div class="expl" style="width:240px;height:240px"></div>
    <small>240px*240px</small>
    </td>
  </tr>
  <tr>
    <th>文字内容</th>
    <td><textarea name="textarea" rows="6" class="long"></textarea></td>
  </tr>
  <tr>
    <th>电话</th>
    <td><input class="long" /></td>
  </tr>
  <tr>
    <th>传真</th>
    <td><input class="long" /></td>
  </tr>
  <tr>
    <th>QQ</th>
    <td><input class="long" /></td>
  </tr>
  <tr>
    <th>MSN/Gmail</th>
    <td><input class="long" /></td>
  </tr>
  <tr>
    <th>网址</th>
    <td><input class="long" /></td>
  </tr>
</table>
<h4>出价</h4>
<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
        <col width="20%" />
        <col width="80%" />
  <tr>
    <th>出价</th>
    <td><sup>&yen;</sup>
      <input class="tiny" /></td>
    </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
        <col width="20%" />
        <col width="80%" />
  <tr>
    <th>&nbsp;</th>
    <td><div class="btnBox"><a href="owner-ad-detail.php" class="btnY fl"><span>提交，完成广告制作</span></a>
      <div class="moreBtn"><span class="gray">|</span> <a href="owner-ad-detail.php">取消</a></div>
      </div></td>
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
<li><span class="fr">佳能单反相关广告</span>名称</li>
<li><span class="fr">4个操作系统/8个设备</span>平台/设备<br /><span></span></li>
<li><span class="fr">10个国家/地区</span>地理位置</li>
<li><span class="fr">联通</span>运营商</li>
</ul>
<h2><img src="images/ico_single.gif" alt="广告" width="16" height="16" align="absmiddle" />广告摘要</h2>
<ul>
<li>名称</li>
<li><span class="fr">多媒体</span>类型</li>
<li><span class="fr"><sup>&yen;</sup>0.03</span>出价</li>
</ul>
</div>
</div>
</div>
</div>
<div class="popDiv collapsed" id="popDiv" style="top:300px;left:150px">
<h2><img src="images/ico_popclose.gif" alt="关闭" id="closePop" class="fr" />Banner 素材库</h2>
<div class="popCon">
<div class="tag"><a href="javascript:void(0)" class="now">默认素材</a><a href="javascript:void(0)">用户上传素材</a></div>
<div class="picBox" style="overflow-y:scroll;height:300px">
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div class="over"><div><a href="javascript:void(0)" id="btn03"><strong>使用</strong></a></div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
<div><img src="images/test_img02.jpg" width="320" height="48" alt=" " /></div>
</div>
</div>
</div>
<div class="popBg collapsed"></div>
<?php include("common/footer.php"); ?>
<script type="text/javascript">
$(document).ready(function(){
  $("#btn02").click(function(){
                  $("#img01").show();
                  })
  $("#btn01").click(function(){
                  $(".popBg").show();
                  $("#popDiv").fadeIn();
                  })
  $("#closePop,#btn03").click(function(){
                  $(".popBg").hide();
                  $("#popDiv").hide();
                  })
  $("#btn04").click(function(){
                  $("#btn05").show();
                  $("#btn04").hide();
                  $(".bgWap").show();
                  })
  $("#btn05").click(function(){
                  $("#btn04").show();
                  $("#btn05").hide();
                  $(".bgWap").hide();
                  })
})
</script>
</body>
</html>