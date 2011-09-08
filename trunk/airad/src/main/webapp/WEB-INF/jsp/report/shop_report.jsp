<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>报表查看</title>
<link href="style/main.css" rel="stylesheet" type="text/css" />
<link href="style/pop.css" rel="stylesheet" type="text/css" />
</head>

<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon">
     <h1 class="tit">报表查看</h1>
<form:form action="" method="post" commandName="command" name="reportForm">
<form:hidden path="report.reportType" id="type"/>
<form:hidden path="report.reportStatus" id="status"/>
<form:hidden path="presetValue"/>
<form:hidden path="reportStartTime"/>
<form:hidden path="reportEndTime"/>
<form:hidden path="report.reportFlag"/>
<form:hidden path="checkList"/>
<form:hidden path="report.reportId"/>
<form:hidden path="report.reportCon" id="ids"/>
<form:hidden path="report.dateType" id="dateType"/>
<form:hidden path="currentPage"/>
<form:hidden path="report.reportName" id="rname"/>
</form:form>
<div class="tag">
<!-- <a class="now" href="javascript:void(0);" onclick="reportShow(this)" id="adcount" title="0">时间</a>  -->
<a class="now" href="javascript:void(0);" onclick="getChar(this)" id="click" tabindex="1">总展示次数</a>
<a href="javascript:void(0);" onclick="getChar(this)" id="clickRate" tabindex="2">总点击数</a>
<a href="javascript:void(0);" onclick="getChar(this)" id="cost" tabindex="3">点击率</a>  
</div>
<div class="borderBox">
<div id="show" style="height:400px;width:918px;">
<!-- <img height="277" width="918" src="images/test_report.gif"> --> <!-- 报表显示 -->
</div>
</div>
<div class="fr">
<a href="javascript:void(0);" onclick="exports('csv');" id="export_csv_link">导出 CSV</a>  <span class="gray">|</span> 
<a href="javascript:void(0);" onclick="exports('xml');" id="export_xml_link">导出 XML</a></div>
<h2>活动详细信息
<small><fmt:formatDate value="${report.reportStartTime}" timeStyle="yyyy-MM-dd"/> -
  <fmt:formatDate value="${report.reportEndTime}"  timeStyle="yyyy-MM-dd"/></small></h2>
<div id="list"></div>
<div class="r"><a href="/personal.do?action=index">返回&raquo;</a></div>
</div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<script language="javascript" type="text/javascript" src="js/jquery.jmpopups-0.5.1.js"></script>
<script type='text/javascript' src='js/highchart/excanvas.compiled.js'></script><!-- IE需要的js -->
<script type='text/javascript' src='js/highchart/highcharts.src.js'></script>
<script type="text/javascript" src="/js/report.js"></script>

<iframe id="_iframe" name="_iframe" style="display:none"></iframe>
<form action="" id="e_export" name="e_export" method="post" target="_iframe">
  <input type="hidden" id="e_ids" name="e_ids" value="${form.report.reportCon }"></input>
  <input type="hidden" id="e_type" name="e_type" value="${form.report.reportType }"></input>
  <input type="hidden" id="e_dateType" name="e_dateType" value="${form.report.dateType}"></input>
  <input type="hidden" id="e_presetValue" name="e_presetValue" value="${form.presetValue}"></input>
  <input type="hidden" id="e_reportStartTime" name="e_reportStartTime" value="${form.reportStartTime}"></input>
  <input type="hidden" id="e_reportEndTime" name="e_reportEndTime" value="${form.reportEndTime}"></input>
  <input type="hidden" id="e_reportStatus" name="e_reportStatus" value="${form.report.reportStatus}"></input>
</form>
<script type="text/javascript">
  $(document).ready(function() {
    addCss("listReport");
    var PAGENUM=1;
    //asce
   fillPage(PAGENUM,'desc');
    getChar();
    });
//enter事件
  function keypress(e)
   {
    var currKey=0,e=e||event;
    if(e&&e.keyCode==13){
    	savOrUpd($("#btn03"));
    }
   }
  document.onkeyup=keypress;
</script>
</body>
</html>