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
<%@ include file="/WEB-INF/jspf/errors.jsp"%>
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
</style>
</head>
<body>
<div id="main">
<div class="mainCon">
<div class="stepNew sp3">
<table width="100%" cellspacing="0" cellpadding="0" border="0"><col width="33%" /><col width="33%" /><col /><tr><td>1. 广告活动信息</td><td>2. 设置广告组投放目标</td><td class="now">3. 设置广告并出价</td></tr></table></div>
<div class="leftCon "><div class="loading">加载中...</div><div id="fish" class="collapsed"><div class="info collapsed" >审核通过的广告修改保存后，将变为<strong>待审核</strong>状态；修改提交后将<strong>重新审核</strong>。</div>