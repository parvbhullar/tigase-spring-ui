<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>airAD － 智慧广告 智慧生活</title>
<%@ include file="/WEB-INF/jsp/page/header_meta.jsp"%>
<style type="text/css">
iframe{height:1100px;width:100%;border:none;}
</style>
</head>
<body style="overflow:auto;">
<%@ include file="/WEB-INF/jsp/page/page_header.jsp"%>
<div id="main" >
<div class="mainCon">
<div class="leftCon">
<div class="con" >
<iframe title="&egrave;?frac34;&aring;&deg;?aring;&sup1;&iquest;&aring;&aring;&ordm;?ccedil;?uml;&ccedil;?sup3;&egrave;&macr;&middot;" frameborder="0" src="http://panyi.wufoo.com/embed/z7x3k7/" onload="SetCwinHeight(this);">
<a href="http://mitian.wufoo.com/forms/m7x3w7/" title="airAD开发者申请">Fill out my form!</a>
</iframe>
</div>
</div>
<div class="rightCon" >
 <div class="con" style="height:880px;">
<h2>相关链接</h2>
<ul>
<li><a href="index.html">首页&raquo;</a></li>
<li><a href="about-us.html">关于我们&raquo;</a></li>
<li><a href="policy.html">隐私协议&raquo;</a></li>
<li><a href="terms.html">服务条款&raquo;</a></li>
<li><a href="join-us.html">加入我们&raquo;</a></li>
<li><a href="help.html">帮助中心&raquo;</a></li>
</ul>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/jsp/page/page_footer.jsp"%>
<script>
function SetCwinHeight(obj)
{
  var cwin=obj;
  if (document.getElementById)
  {
    if (cwin && !window.opera)
    {
      if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight)
        cwin.height = cwin.contentDocument.body.offsetHeight + 20; //FF NS
      else if(cwin.Document && cwin.Document.body.scrollHeight)
        cwin.height = cwin.Document.body.scrollHeight + 10;//IE
    }
    else
    {
        if(cwin.contentWindow.document && cwin.contentWindow.document.body.scrollHeight)
            cwin.height = cwin.contentWindow.document.body.scrollHeight;//Opera
    }
  }
}
</script>
</body>
</html>