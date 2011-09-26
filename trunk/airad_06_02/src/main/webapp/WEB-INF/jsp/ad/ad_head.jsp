<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告管理</title>
<link type="text/css" rel="stylesheet" href="/mice/uploadFile/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/style/ad/ad_detail_add.css" />
<link type="text/css" rel="stylesheet" href="/js/validator.css" />
<link type="text/css" rel="stylesheet" href="/style/banner.css" />
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>
<%@ include file="/WEB-INF/jspf/errors.jsp"%>

<script type="text/javascript">
<!--
$(function(){
	
	addCssByURL("adGroup.do?action=list");
	 <c:if test="${empty command.adId}">
	 addCssByURL("action=detailEdit");
	 </c:if>
  });
//-->
</script>
<style type="text/css">
#fullbg {
	background-color: Gray;
	display: none;
	z-index: 3;
	position: absolute;
	left: 0px;
	top: 0px;
	filter: Alpha(Opacity = 30);
	/* IE */
	-moz-opacity: 0.4;
	/* Moz + FF */
	opacity: 0.4;
}

#dialog {
	position: absolute;
	width: 200px;
	height: 200px;
	background: #F00;
	display: none;
	z-index: 5;
}

.aboxinitcss{  
    position:absolute;  
    margin-top:0px;  
    margin-left:0px;  
    display:none;  
    background:#FFF;  
    border:solid #CCC 1px;  
    padding:30px;  
    z-index:9999;  
    width:620px;overflow:hidden;  
}  
.ifhideselect{  
z-index:-1;  
width:720px;  
height:325px;  
position:absolute;  
border:0;  
left:0px;  
top:0px;
}  
.ifhideselect1{  
z-index:-1;  
width:490px;  
height:430px;  
position:absolute;  
border:0;  
left:0px;  
top:0px;
}  
</style>
</head>
<body>
<div id="main">
<div class="mainCon">

<div class="stepNew">
<table width="100%" cellspacing="0" cellpadding="0" border="0">
<colgroup><col width="50%"><col>
  </colgroup><tbody><tr>
    <td class="now">1. 广告制作</td>
    <td>2. 广告组设置</td>
    </tr>
</tbody></table>
</div>
<div class="leftCon "><div class="loading">加载中...</div><div id="fish" class="collapsed">

<div class="info collapsed" >审核通过的广告修改保存后，将变为<strong>待审核</strong>状态；修改提交后将<strong>重新审核</strong>。</div>