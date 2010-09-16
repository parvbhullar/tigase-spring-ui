<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jstl_contexpath.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>jqGrid3.8</title>
	<script type="text/javascript" src="../../../javascript/jstree/_lib/jquery.js"></script>
	
	<link rel="stylesheet" type="text/css" media="screen" href="../../../javascript/jqGrid38/themes/redmond/jquery-ui-1.8.2.custom.css" />
	<link rel="stylesheet" type="text/css" media="screen" href="../../../javascript/jqGrid38/themes/ui.jqgrid.css" />
	<style>
html, body {
	margin: 0;			/* Remove body margin/padding */
	padding: 0;
	overflow: hidden;	/* Remove scroll bars on browser window */	
    font-size: 75%;
}
/*Splitter style */


#LeftPane {
	/* optional, initial splitbar position */
	overflow: auto;
}
/*
 * Right-side element of the splitter.
*/

#RightPane {
	padding: 2px;
	overflow: auto;
}
.ui-tabs-nav li {position: relative;}
.ui-tabs-selected a span {padding-right: 10px;}
.ui-tabs-close {display: none;position: absolute;top: 3px;right: 0px;z-index: 800;width: 16px;height: 14px;font-size: 10px; font-style: normal;cursor: pointer;}
.ui-tabs-selected .ui-tabs-close {display: block;}
.ui-layout-west .ui-jqgrid tr.jqgrow td { border-bottom: 0px none;}
.ui-datepicker {z-index:1200;}
.rotate
    {
        /* for Safari */
        -webkit-transform: rotate(-90deg);

        /* for Firefox */
        -moz-transform: rotate(-90deg);

        /* for Internet Explorer */
        filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=3);
    }

</style> 
	
	<!-- jqgrid3.8 开始 -->
	<script src="../../../javascript/jqGrid38/js/jquery-ui-1.8.2.custom.min.js" type="text/javascript"></script>
	<script src="../../../javascript/jqGrid38/js/jquery.layout.js" type="text/javascript"></script>
	<script src="../../../javascript/jqGrid38/js/i18n/grid.locale-en.js" type="text/javascript"></script>
	<script type="text/javascript">
		$.jgrid.no_legacy_api = true;
		$.jgrid.useJSON = true;
	</script>
	<script src="../../../javascript/jqGrid38/js/ui.multiselect.js" type="text/javascript"></script>
	<script src="../../../javascript/jqGrid38/js/jquery.jqGrid.js" type="text/javascript"></script>
	<!-- jqgrid3.8 结束 -->
	<script type="text/javascript" src="jqgrid38.js"></script>
	
</head>
<body>
<div id="LeftPane" class="ui-layout-west ui-widget ui-widget-content">
		<table id="west-grid"></table>
	</div> <!-- #LeftPane -->
	<div id="RightPane" class="ui-layout-center ui-helper-reset ui-widget-content" ><!-- Tabs pane -->
    <div id="switcher"></div>
		<div id="tabs" class="jqgtabs">
			<ul>
				<li><a href="#tabs-1">jqGrid 3.8</a></li>
			</ul>
			<div id="tabs-1" style="font-size:12px;"> 3.8 Adds grouping<br/>
			<table id="list47"></table> <div id="plist47"></div> 
			<br/>
			<script type="text/javascript">
			var mydata = [ 
			  			{id:"1",invdate:"2010-05-24",name:"test",note:"note",tax:"10.00",total:"2111.00"} , 
			  			{id:"2",invdate:"2010-05-25",name:"test2",note:"note2",tax:"20.00",total:"320.00"}, 
			  			{id:"3",invdate:"2007-09-01",name:"test3",note:"note3",tax:"30.00",total:"430.00"}, 
			  			{id:"4",invdate:"2007-10-04",name:"test",note:"note",tax:"10.00",total:"210.00"}, 
			  			{id:"5",invdate:"2007-10-05",name:"test2",note:"note2",tax:"20.00",total:"320.00"}, 
			  			{id:"6",invdate:"2007-09-06",name:"test3",note:"note3",tax:"30.00",total:"430.00"}, 
			  			{id:"7",invdate:"2007-10-04",name:"test",note:"note",tax:"10.00",total:"210.00"}, 
			  			{id:"8",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"21.00",total:"320.00"}, 
			  			{id:"9",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}, 
			  			{id:"11",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"}, 
			  			{id:"12",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"}, 
			  			{id:"13",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}, 
			  			{id:"14",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"}, 
			  			{id:"15",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"}, 
			  			{id:"16",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}, 
			  			{id:"17",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"}, 
			  			{id:"18",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"}, 
			  			{id:"19",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}, 
			  			{id:"21",invdate:"2007-10-01",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"}, 
			  			{id:"22",invdate:"2007-10-02",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"}, 
			  			{id:"23",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}, 
			  			{id:"24",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"}, 
			  			{id:"25",invdate:"2007-10-05",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"}, 
			  			{id:"26",invdate:"2007-09-06",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"}, 
			  			{id:"27",invdate:"2007-10-04",name:"test",note:"note",amount:"200.00",tax:"10.00",total:"210.00"}, 
			  			{id:"28",invdate:"2007-10-03",name:"test2",note:"note2",amount:"300.00",tax:"20.00",total:"320.00"}, 
			  			{id:"29",invdate:"2007-09-01",name:"test3",note:"note3",amount:"400.00",tax:"30.00",total:"430.00"} 
			  			]; 
  			 
			</script>
			</div>
			<table width="100%" border="1" cellspacing="0" cellpadding="0" id="grid">
           										<thead>
													<tr>
														<th width="18%" class="left_txt2">活动主题</th>
														<th width="8%" class="left_txt2">开始日期</th>
														<th width="8%" class="left_txt2">结束日期</th>
														<th width="12%" class="left_txt2">组织名称</th>
														<th width="7%" class="left_txt2">活动状态</th>
														<th width="40%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="active" items="${orgList}" varStatus="status">
													<tr height="10" bgcolor="#f2f2f2" id="${active.orgId}">
													<td align="center">${active.orgName }</td>
													</tr>
													</c:forEach>
													
												</tbody>
           									</table>
		</div>
	</div>
</body>
</html>