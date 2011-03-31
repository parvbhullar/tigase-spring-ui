<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ivyinfo.session.bean.SessionUserBean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
String ctxindex = request.getContextPath();
HttpSession getSession = request.getSession();
SessionUserBean sessionUserBean = (SessionUserBean) getSession.getAttribute("sessionUserBean");
String username=sessionUserBean.getUserBean().getName();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title></title>
	<link rel="stylesheet" type="text/css" href="css/index/complex.css" />
	<script type="text/javascript" src="<%=ctxindex%>/js/jqueryui/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/jqueryui/jquery.layout.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/jqueryui/js/jquery-ui-1.8.7.custom.min.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/jqueryui/js/themeswitchertool.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/jqueryui/js/debug.js"></script>
	
	<script src="<%=ctxindex%>/js/jqgrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
	<script src="<%=ctxindex%>/js/jqgrid/js/jquery.jqGrid.min2.js" type="text/javascript"></script>
	
	<script src="<%=ctxindex%>/js/jquery.blockUI.js" type="text/javascript"></script>
	<link type="text/css" href="<%=ctxindex%>/js/jqueryui/css/redmond/jquery-ui-1.8.7.custom.css" rel="stylesheet" />
	
	<script type="text/javascript" src="<%=ctxindex%>/js/jqueryui/js/ui.selectmenu.js"></script> 
	<link type="text/css" href="<%=ctxindex%>/js/jqueryui/css/ui.selectmenu.css" rel="stylesheet" />
	
	<link type="text/css" href="<%=ctxindex%>/js/webcall/css/css.css" rel="stylesheet" />
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=ctxindex%>/js/jqgrid/css/ui.jqgrid.css" />
	 
	<script type="text/javascript" src="<%=ctxindex%>/js/common.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/json2.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/jquery-validate/jquery.validate.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=ctxindex%>/css/index/screen.css"   />
	<link rel="stylesheet" type="text/css" href="<%=ctxindex%>/css/ui/ui.css" 		 media="screen"  />
	<link href="css/index/screen.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/index.js"></script>
	<link rel="stylesheet" href="js/michenriksen/stylesheets/reset.css" media="screen">
	<link rel="stylesheet" href="js/michenriksen/stylesheets/demo.css" media="screen">
	<link rel="stylesheet" href="js/michenriksen/stylesheets/css3buttons.css" media="screen">
	
	<script type="text/javascript" src="js/fgmenu/fg.menu.js"></script>
    <link type="text/css" href="js/fgmenu/fg.menu.css" media="screen" rel="stylesheet" />
    
    <link type="text/css" href="js/accordion/grey.css" media="screen" rel="stylesheet" />
    <link type="text/css" href="css/im/webim.min.css" media="screen" rel="stylesheet" />
    <script type="text/javascript" src="js/im/webim.js"></script>
    <script type="text/javascript" src="js/im/i18n/webim-zh-CN.js"></script>
    <script type="text/javascript" src="<%=ctxindex%>/calendar.js"></script>
    <script type="text/javascript" src="<%=ctxindex%>/calendar.js"></script>
    
<script type="text/javascript">
	var outerLayout, innerLayout,innerWestLayout;
	$(document).ready( function() {
		$( "#accordion" ).hide();
		// create the OUTER LAYOUT
		outerLayout = $("body").layout( layoutSettings_Outer );

		/*******************************
		 ***  CUSTOM LAYOUT BUTTONS  ***
		 *******************************
		 *
		 * Add SPANs to the east/west panes for customer "close" and "pin" buttons
		 *
		 * COULD have hard-coded span, div, button, image, or any element to use as a 'button'...
		 * ... but instead am adding SPANs via script - THEN attaching the layout-events to them
		 *
		 * CSS will size and position the spans, as well as set the background-images
		 */

		var westSelector = "body > .ui-layout-west"; // outer-west pane


		/*
		$("<span></span>").addClass("pin-button").prependTo( westSelector );
		outerLayout.addPinBtn( westSelector +" .pin-button", "west");
		$("<span></span>").attr("id", "west-closer" ).prependTo( westSelector );
		outerLayout.addCloseBtn("#west-closer", "west");
		*/


		// DEMO HELPER: prevent hyperlinks from reloading page when a 'base.href' is set
		$("a").each(function () {
			var path = document.location.href;
			if (path.substr(path.length-1)=="#") path = path.substr(0,path.length-1);
			if (this.href.substr(this.href.length-1) == "#") this.href = path +"#";
		});

		createInnerLayout ();
		createInnerWestLayout ();

	});


	/*
	*#######################
	* INNER LAYOUT SETTINGS
	*#######################
	*
	* These settings are set in 'list format' - no nested data-structures
	* Default settings are specified with just their name, like: fxName:"slide"
	* Pane-specific settings are prefixed with the pane name + 2-underscores: north__fxName:"none"
	*/
	layoutSettings_Inner = {
		applyDefaultStyles:				true // basic styling for testing & demo purposes
	,	minSize:						20 // TESTING ONLY
	,	spacing_closed:					14
	,	north__spacing_closed:			8
	,	south__spacing_closed:			8
	,	north__togglerLength_closed:	-1 // = 100% - so cannot 'slide open'
	,	south__togglerLength_closed:	-1
	,	fxName:							"slide" // do not confuse with "slidable" option!
	,	fxSpeed_open:					1000
	,	fxSpeed_close:					2500
	,	fxSettings_open:				{ easing: "easeInQuint" }
	,	fxSettings_close:				{ easing: "easeOutQuint" }
	,	north__fxName:					"none"
	,	south__fxName:					"drop"
	,	south__fxSpeed_open:			500
	,	south__fxSpeed_close:			1000
	//,	initClosed:						true
	,	center__minWidth:				200
	,	center__minHeight:				200
	,	resizable: 						false
	,	slidable:						true
	};
	
	layoutSettings_West = {
			applyDefaultStyles:				true // basic styling for testing & demo purposes
		,	spacing_closed:					14
		,	north__spacing_closed:			8
		
		,	north__togglerLength_closed:	-1 // = 100% - so cannot 'slide open'
		
		,	fxName:							"slide" // do not confuse with "slidable" option!
		,	fxSpeed_open:					1000
		,	fxSpeed_close:					2500
		,	fxSettings_open:				{ easing: "easeInQuint" }
		,	fxSettings_close:				{ easing: "easeOutQuint" }
		,	north__fxName:					"none"
		
		//,	initClosed:						true
		,	center__minWidth:				200
		,	center__minHeight:				200
		, 	south: {
				slidable:				false
			,	resizable: 				false
			}
		};

	/*
	*#######################
	* OUTER LAYOUT SETTINGS
	*#######################
	*
	* This configuration illustrates how extensively the layout can be customized
	* ALL SETTINGS ARE OPTIONAL - and there are more available than shown below
	*
	* These settings are set in 'sub-key format' - ALL data must be in a nested data-structures
	* All default settings (applied to all panes) go inside the defaults:{} key
	* Pane-specific settings go inside their keys: north:{}, south:{}, center:{}, etc
	*/
	var layoutSettings_Outer = {
		name: "outerLayout" // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
		// options.defaults apply to ALL PANES - but overridden by pane-specific settings
	,	defaults: {
			size:					"auto"
		,	minSize:				50
		,	paneClass:				"pane" 		// default = 'ui-layout-pane'
		,	resizerClass:			"resizer"	// default = 'ui-layout-resizer'
		,	togglerClass:			"toggler"	// default = 'ui-layout-toggler'
		,	buttonClass:			"button"	// default = 'ui-layout-button'
		,	contentSelector:		".content"	// inner div to auto-size so only it scrolls, not the entire pane!
		,	contentIgnoreSelector:	"span"		// 'paneSelector' for content to 'ignore' when measuring room for content
		,	togglerLength_open:		35			// WIDTH of toggler on north/south edges - HEIGHT on east/west edges
		,	togglerLength_closed:	35			// "100%" OR -1 = full height
		,	hideTogglerOnSlide:		true		// hide the toggler when pane is 'slid open'
		,	togglerTip_open:		"Close This Pane"
		,	togglerTip_closed:		"Open This Pane"
		,	resizerTip:				"Resize This Pane"
		//	effect defaults - overridden on some panes
		,	fxName:					"slide"		// none, slide, drop, scale
		,	fxSpeed_open:			750
		,	fxSpeed_close:			1500
		,	fxSettings_open:		{ easing: "easeInQuint" }
		,	fxSettings_close:		{ easing: "easeOutQuint" }
		,	resizable: 				false
		,	slidable:				false
	}
	,	north: {
			spacing_open:			1			// cosmetic spacing
		,	togglerLength_open:		0			// HIDE the toggler button
		,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
		,	resizable: 				false
		,	slidable:				false
		//	override default effect
		,	fxName:					"none"
		}
	,	south: {
			maxSize:				200
		,	spacing_closed:			0			// HIDE resizer & toggler when 'closed'
		,	slidable:				false		// REFERENCE - cannot slide if spacing_closed = 0
		,	initClosed:				true
		,	slidable:				false
		,	resizable: 				false
		//	CALLBACK TESTING...
		,	onhide_start:			function () { return confirm("START South pane hide \n\n onhide_start callback \n\n Allow pane to hide?"); }
		,	onhide_end:				function () { alert("END South pane hide \n\n onhide_end callback"); }
		,	onshow_start:			function () { return confirm("START South pane show \n\n onshow_start callback \n\n Allow pane to show?"); }
		,	onshow_end:				function () { alert("END South pane show \n\n onshow_end callback"); }
		,	onopen_start:			function () { return confirm("START South pane open \n\n onopen_start callback \n\n Allow pane to open?"); }
		,	onopen_end:				function () { alert("END South pane open \n\n onopen_end callback"); }
		,	onclose_start:			function () { return confirm("START South pane close \n\n onclose_start callback \n\n Allow pane to close?"); }
		,	onclose_end:			function () { alert("END South pane close \n\n onclose_end callback"); }
		//,	onresize_start:			function () { return confirm("START South pane resize \n\n onresize_start callback \n\n Allow pane to be resized?)"); }
		,	onresize_end:			function () { alert("END South pane resize \n\n onresize_end callback \n\n NOTE: onresize_start event was skipped."); }
		}
	,	west: {
			size:					250
		,	spacing_closed:			21			// wider space when closed
		,	togglerLength_closed:	21			// make toggler 'square' - 21x21
		,	togglerAlign_closed:	"top"		// align to top of resizer
		,	togglerLength_open:		0			// NONE - using custom togglers INSIDE west-pane
		,	togglerTip_open:		"Close West Pane"
		,	togglerTip_closed:		"Open West Pane"
		,	resizerTip_open:		"Resize West Pane"
		,	slideTrigger_open:		"click" 	// default
		,	initClosed:				false
		,	slidable:				false
		,	resizable: 				false
		,	fxSettings_open:		{ easing: "easeOutBounce" }
		}
	,	east: {
			size:					250
		,	spacing_closed:			21			// wider space when closed
		,	togglerLength_closed:	21			// make toggler 'square' - 21x21
		,	togglerAlign_closed:	"top"		// align to top of resizer
		,	togglerLength_open:		0 			// NONE - using custom togglers INSIDE east-pane
		,	togglerTip_open:		"Close East Pane"
		,	togglerTip_closed:		"Open East Pane"
		,	resizerTip_open:		"Resize East Pane"
		,	slideTrigger_open:		"mouseover"
		,	initClosed:				true
		,	fxName:					"drop"
		,	fxSpeed:				"normal"
		,	fxSettings:				{ easing: "" } // nullify default easing
		}
	,	center: {
			paneSelector:			"#mainContent" 			// sample: use an ID to select pane instead of a class
		,	onresize:				"innerLayout.resizeAll"	// resize INNER LAYOUT when center pane resizes
		,	minWidth:				200
		,	minHeight:				200
		}
	};

	var json0 = {"options":"[{\"mod\":\"sys\",\"modname\":\"系统管理\"}]"}
	var json1 = {"options":"[{\"mod\":\"orgsys\",\"modname\":\"管理平台\"},{\"mod\":\"meeting\",\"modname\":\"会议\"},{\"mod\":\"personal\",\"modname\":\"个人设置\"}]"}
	var json2 = {"options":"[{\"mod\":\"meeting\",\"modname\":\"会议\"},{\"mod\":\"personal\",\"modname\":\"个人设置\"}]"} 
</script>
</head>
<body>

<div class="ui-layout-west">
	<div class="ui-layout-north">
		<div class="content">
			<div id="accordion">
				<h3><a href='#' class="dcjq-parent active">系统管理</a></h3>
				<div>
				<ul>
				<li><a href='#'></a></li></ul>
				</div>
			</div>
		</div>
	</div>//end west ui-layout-north
	<div class="ui-layout-center">
		<div id="content">
		
	</div>
	</div>
	
	</div>


<div class="ui-layout-north" style="background-image:url(images/index/top.gif)">
	<div style="float:right;padding-right:30px;padding-top:10px;"><label id="username"><img src="images/index/group.png" />当前用户：<%=username %></label>&nbsp;|&nbsp;
	<a id="personal" href="javascript:void(false)">个人设置</a>&nbsp;|&nbsp;
	<a id="cancellation" href="login.jsp">注销</a>
	</div>
</div>


<div class="ui-layout-south">
	<div class="header">Outer - South</div>
	<div class="content">
		<p>I only have a resizer/toggler when 'open'</p>
	</div>
</div>


<div id="mainContent">
	<!-- DIVs for the INNER LAYOUT -->

	<div class="ui-layout-center">
		<div class="ui-layout-content" style="padding: 0px;">
			<div id="tabs"></div>
		</div>
	</div>
	
		<div class="ui-layout-south">
			<div id="webim">
			</div>
</div>
	
</div>

<script>
	$(function() {
		var icons = {
				header: "ui-icon-circle-arrow-e",
				headerSelected: "ui-icon-circle-arrow-s"
			};
		$( "#accordion" ).show();
		$( "#accordion" ).accordion();
		$( "#accordion" ).accordion( "option", "autoHeight", true );
		$( "#accordion" ).accordion({ clearStyle: true });
		$( "#accordion" ).accordion( "option", "icons", icons );
		//innerLayout
		innerLayout.sizePane('south', 235);innerLayout.open('south');
		outerLayout.open("west");
		innerWestLayout.sizePane('north', 300);innerWestLayout.open('north');
		//$(".ui-layout-center.ui-layout-pane.ui-layout-pane-center").css("height",200);
		//innerWestLayout.sizePane('center', 130);innerWestLayout.open('center');
	});
	/**
	* createInnerLayout
	*/
	function createInnerLayout () {
	    // innerLayout is INSIDE the center-pane of the outerLayout
	    //debugData( layoutSettings_Inner );
	    innerLayout = $( outerLayout.options.center.paneSelector ).layout( layoutSettings_Inner );
	    // hide 'Create Inner Layout' commands and show the list of testing commands
	    $('#createInner').hide();
	    $('#createInner2').hide();
	    $('#innerCommands').show();
	}
	
	function createInnerWestLayout () {
	    // innerLayout is INSIDE the center-pane of the outerLayout
	    //debugData( layoutSettings_Inner );
	    innerWestLayout = $( outerLayout.options.west.paneSelector ).layout(layoutSettings_West);
	    // hide 'Create Inner Layout' commands and show the list of testing commands
	}
	
	
	</script>
</body>
</html>