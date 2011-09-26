<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
<link href="tree.css"  type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.simple.tree.self.js"></script>
<script type="text/javascript">
var simpleTreeCollection;
$(document).ready(function(){
  simpleTreeCollection = $('.simpleTree').simpleTree({
    url:'iphone.json',
    dataType:'json',
    check:true,
    cascadeType:false,//默认是true 即节点被选中时 其祖先节点和后代节点都被选中
    animate:true,
    afterClick:function(node){
      alert(node.id+"---"+node.text);
      if(node.attributes){
        alert(node.attributes.p1+"--"+node.attributes.p2);
      }
    },
    afterDblClick:function(node){
      //alert("text-"+$('span:first',node).text());
    },
    afterMove:function(destination, source, pos){
      //alert("destination-"+destination.attr('id')+" source-"+source.attr('id')+" pos-"+pos);
    },
    afterAjax:function()
    {
      //alert('Loaded');
    }
  });

  $("#selectCheckId").click(function(){
    
    if($("input:checked",".simpleTree").length == 0) {
      alert("没有选中节点");
    }else{
      var nodeid = [];
      $("input:checked",".simpleTree").each(function(){
        nodeid.push($(this).val());
      });
      alert(nodeid.join(","));
    }
  });
});
</script>
  </head>
  <body>
<div class="mainCon">
<div class="stepNew sp2">
<table width="100%" cellspacing="0" cellpadding="0" border="0">
<col width="33%"><col width="33%"><col>
  <tbody><tr>
    <td>1. 广告活动信息</td>
    <td class="now">2. 设置广告组投放目标</td>
    <td>3. 设置广告并出价</td>
    </tr>
</tbody></table>
</div>
<div class="leftCon">
<h1>设置广告组投放目标</h1>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
        <col width="20%">
        <col width="80%">
        <tbody><tr>
          <th>广告组名称</th>
          <td><input class="long"></td>
        </tr>
        <tr>
          <th>平台/设备</th>
          <td>
          <div class="selectBox">
<table cellspacing="0" cellpadding="0" border="0" class="tabN">
        <col width="60%">
        <col width="40%">
  <tbody><tr>
    <td><!-- <img height="364" width="235" alt="list" src="images/list.gif"> -->
    <!-- tree -->
    <div >
		  <ul id="tree" class="simpleTree">
		  </ul>
		</div>
    </td>
    <td>
    <strong>iphone 操作系统</strong>
    <div class="mb">最低<select name="select2">
            <option selected="selected">2.0</option>
            <option>3.0</option>
          </select><br>
          最高<select name="select2">
            <option selected="selected">无上限</option>
          </select>
</div>
    <strong>Android 操作系统</strong>
    <div>最低<select name="select2">
            <option selected="selected">2.0</option>
            <option>3.0</option>
          </select><br>
          最高<select name="select2">
            <option selected="selected">无上限</option>
          </select></div>
    </td>
  </tr>
</tbody></table>

          </div>
          </td>
        </tr>
        <tr>
          <th>地理位置</th>
          <td><input type="radio" checked="checked" value="" name="1"> 面向所有地理位置<br>
          <input type="radio" value="" name="1"> 面向特定地理位置
          </td>
        </tr>
        <tr>
          <th>运营商</th>
          <td>
          <input type="radio" value="" name="2"> 针对所有流量，包括 Wi-Fi 流量<br>
          <input type="radio" value="" name="2"> 仅针对 Wi-Fi 流量<br>
          <input type="radio" checked="checked" value="" name="2"> 面向手机运营商流量
          <div class="selectBox">
          <input type="radio" checked="checked" value="" name="3"> 联通<br>
          <input type="radio" value="" name="3"> 移动
          </div>
          </td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox">
           <a class="btnY fl" href="owner-ad-add-s3.php"><span>提交，进行下一步</span></a><div class="moreBtn"><span class="gray">|</span> <a href="owner-ad-gup.php">取消</a></div>
           </div>
            </td>
        </tr>
      </tbody></table>
</div>
<div class="rightCon">
<div class="infoCon">
<h2><img height="16" width="16" align="absmiddle" alt="活动" src="images/ico_act.gif">活动摘要</h2>
<ul>
<li><span class="fr">宣传米田科技有限公司的...</span>名称</li>
<li><span class="fr sml">2010-12-24 00:00</span>开始时间</li>
<li><span class="fr sml"><sup>¥</sup>50</span>每日预算</li>
</ul>
<h2><img height="16" width="16" align="absmiddle" alt="活动" src="images/ico_gup.gif">广告组摘要</h2>
<ul>
<li>名称</li>
<li><span class="fr">4个操作系统/8个设备</span>平台/设备<br><span></span></li>
<li><span class="fr">10个国家/地区</span>地理位置</li>
<li><span class="fr">联通</span>运营商</li>
</ul>
</div>
</div>
</div>
  </body>
</html>