<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>设置广告并出价</title>
    <%@ include file="/WEB-INF/jspf/js_and_css.jsp"%>
    <link rel="stylesheet" href="/style/jquery.Jcrop.css" type="text/css" />
    <script type="text/javascript" src="/js/jquery.jmpopups-0.5.1.js"></script>
    <script type="text/javascript" src="/js/jquery.Jcrop.js"></script>
  </head>
  <body>
  <%@ include file="/WEB-INF/jspf/header.jsp" %>
  <div id="main">
    <div class="mainCon">
      <%@ include file="/WEB-INF/jspf/errors.jsp" %>
      <!-- 开发嵌入start-->
<div class="stepNew sp3">
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<col width="33%">
		<col width="33%">
		<col>
		<tbody>
		  <tr>
		    <td>1. 广告活动信息</td>
		    <td>2. 设置广告组投放目标</td>
		    <td class="now">3. 设置广告并出价</td>
		  </tr>
		</tbody>
	</table>
</div>
<div class="leftCon">
<h1>设置广告并出价</h1>
<form:form action="/adEdit.do?action=doAdd" method="POST">
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
        <col width="20%">
        <col width="80%">
        <tbody><tr>
          <th>广告类型</th>
          <td><input type="radio" value="" name="1"> 富媒体广告<br>
              <input type="radio" checked="checked" value="" name="1"> 贫媒体广告</td>
        </tr>
        <tr>
          <th>广告名称</th>
          <td><input class="long"></td>
        </tr>
        <tr>
          <th>广告链接</th>
          <td><input class="long"></td>
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
      </tbody>
</table>
<h4>广告 Banner 制作</h4>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
        <col width="20%">
        <col width="80%">
        <tbody><tr>
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
             <div class="ctl">
                <a id="btn01" class="btnS" href="javascript:void(0)"><span>从素材库中选择</span></a>
                <span class="gray">或者</span>
                <a id="btn02" class="btnS" href="javascript:void(0)" onclick="adO.showUpload()"><span>从本地上传</span></a>
                <a id="btn03" class="btnS" href="javascript:void(0)" onclick="adO.crop()"><span>保存图片</span></a>
             </div>
          </td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
            <div id="img01" style="width: 320px; height: 320px; display: block;" class="expl mb collapsed">
              <img height="320" width="320" alt=" " src="/images/test_img01.jpg" id="cropbox"/>
            </div>
            <div>手机 <small>320px*48px</small>
            <div style="width: 320px; height: 48px;" class="expl"></div>
            </div>
            <div>平板 <small>468px*60px</small>
              <div style="width: 468px; height: 60px;" class="expl"></div>
          </div></td>
        </tr>
        <tr>
          <th>Banner文字</th>
          <td><input class="long"></td>
        </tr>
</tbody>
</table>
<h4>
<a class="fr" id="btn04" href="javascript:void(0)">效果预览»</a>
<a id="btn05" class="collapsed fr" href="javascript:void(0)">关闭预览»</a>广告内容制作
</h4>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
    <col width="20%">
    <col width="80%">
    <tbody>
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
	    <td><input class="long"><br>
	      <small>如果您重命名站点名称，在提交广告后，将保存为新 Wap 站点。</small>
	    </td>
    </tr>
	  <tr>
	    <th>图片</th>
	    <td><div class="ctl"><a class="btnS" href="javascript:void(0)"><span>从素材库中选择</span></a><span class="gray">或者</span><a class="btnS" href="javascript:void(0)"><span>从本地上传</span></a></div>
	    <div style="width: 240px; height: 240px;" class="expl"></div>
	    <small>240px*240px</small>
	    </td>
	  </tr>
	  <tr>
	    <th>文字内容</th>
	    <td><textarea class="long" rows="6" name="textarea"></textarea></td>
	  </tr>
	  <tr>
	    <th>电话</th>
	    <td><input class="long"></td>
	  </tr>
	  <tr>
	    <th>传真</th>
	    <td><input class="long"></td>
	  </tr>
	  <tr>
	    <th>QQ</th>
	    <td><input class="long"></td>
	  </tr>
	  <tr>
	    <th>MSN/Gmail</th>
	    <td><input class="long"></td>
	  </tr>
	  <tr>
	    <th>网址</th>
	    <td><input class="long"></td>
	  </tr>
  </tbody>
</table>
<h4>出价</h4>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
   <col width="20%">
   <col width="80%">
   <tbody>
   <tr>
    <th>出价</th>
    <td><sup>¥</sup>
      <input class="tiny"></td>
    </tr>
  </tbody>
</table>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
  <col width="20%">
  <col width="80%">
  <tbody>
    <tr>
    <th>&nbsp;</th>
    <td><div class="btnBox">
       <button type="submit" class="btnBY fl">提交，完成广告制作</button>
      <div class="moreBtn"><span class="gray">|</span> <a href="owner-ad-detail.php">取消</a></div>
      </div>
    </td>
    </tr>
  </tbody>
  </table>
</form:form>  
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
<li><span class="fr">佳能单反相关广告</span>名称</li>
<li><span class="fr">4个操作系统/8个设备</span>平台/设备<br><span></span></li>
<li><span class="fr">10个国家/地区</span>地理位置</li>
<li><span class="fr">联通</span>运营商</li>
</ul>
<h2><img height="16" width="16" align="absmiddle" alt="广告" src="images/ico_single.gif">广告摘要</h2>
<ul>
<li>名称</li>
<li><span class="fr">多媒体</span>类型</li>
<li><span class="fr"><sup>¥</sup>0.03</span>出价</li>
</ul>
</div>
</div>
      <!-- 开发嵌入end-->      
         
    </div>
  </div>
  <%@ include file="/WEB-INF/jspf/footer.jsp" %>
  </body>
</html>
