<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<% 
String ctxindex = request.getContextPath();

String username="qk";
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
	<script type="text/javascript" src="<%=ctxindex%>/js/jstree/jquery.jstree.js"></script>
	
	<script src="<%=ctxindex%>/js/jqgrid/js/i18n/grid.locale-en.js" type="text/javascript"></script>
	<script src="<%=ctxindex%>/js/jqgrid/js/jquery.jqGrid.min2.js" type="text/javascript"></script>
	
	<script src="<%=ctxindex%>/js/jquery.blockUI.js" type="text/javascript"></script>
	 
	<link type="text/css" href="<%=ctxindex%>/js/jqueryui/css/redmond2/jquery-ui-1.8.11.custom.css" rel="stylesheet" />

	<script type="text/javascript" src="<%=ctxindex%>/js/jqueryui/js/ui.selectmenu.js"></script> 
	<link type="text/css" href="<%=ctxindex%>/js/jqueryui/css/ui.selectmenu.css" rel="stylesheet" />
	
	<link type="text/css" href="<%=ctxindex%>/js/webcall/css/css.css" rel="stylesheet" />
	
	<link rel="stylesheet" type="text/css" media="screen" href="<%=ctxindex%>/js/jqgrid/css/ui.jqgrid.css" />
	 
	<script type="text/javascript" src="<%=ctxindex%>/js/common.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/json2.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/jquery.form.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/js/jquery-validate/jquery.validate.js" ></script>
	<link rel="stylesheet" type="text/css" href="<%=ctxindex%>/css/ui/ui.css" 		 media="screen"  />
	<link href="css/index/screen.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=ctxindex%>/index.js"></script>
	<!--  <link rel="stylesheet" href="js/michenriksen/stylesheets/reset.css" media="screen">-->
	<!--  <link rel="stylesheet" href="js/michenriksen/stylesheets/demo.css" media="screen">-->
	<link rel="stylesheet" href="js/michenriksen/stylesheets/css3buttons.css" media="screen">
	
	<script type="text/javascript" src="js/fgmenu/fg.menu.js"></script>
    <link type="text/css" href="js/fgmenu/fg.menu.css" media="screen" rel="stylesheet" />
    
    <link type="text/css" href="js/accordion/grey.css" media="screen" rel="stylesheet" />
    <link type="text/css" href="css/im/webim.min.css" media="screen" rel="stylesheet" />
    <script type="text/javascript" src="js/im/webim.js"></script>
    <script type="text/javascript" src="js/im/i18n/webim-zh-CN.js"></script>
    <script type="text/javascript" src="<%=ctxindex%>/calendar.js"></script>
    
    <script type="text/javascript" src="jsp/common/system.js"></script>
    
    
    <!-- 
    <script type="text/javascript" src="https://getfirebug.com/firebug-lite-debug.js"></script>
	 -->
<script type="text/javascript">
/*
 * complex.html
 *
 * This is a demonstration page for the jQuery layout widget
 *
 *	NOTE: For best code readability, view this with a fixed-space font and tabs equal to 4-chars
 */

	var outerLayout, innerLayout;

	/*
	*#######################
	*     ON PAGE LOAD
	*#######################
	*/
	$(document).ready( function() {
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

		// BIND events to hard-coded buttons in the NORTH toolbar
		
		//outerLayout.addToggleBtn( "#tbarToggleNorth", "north" );
		//outerLayout.addOpenBtn( "#tbarOpenSouth", "south" );
		//outerLayout.addCloseBtn( "#tbarCloseSouth", "south" );
		//outerLayout.addPinBtn( "#tbarPinWest", "west" );
		//outerLayout.addPinBtn( "#tbarPinEast", "east" );

		// save selector strings to vars so we don't have to repeat it
		// must prefix paneClass with "body > " to target ONLY the outerLayout panes
		var westSelector = "body > .ui-layout-west"; // outer-west pane
		//var eastSelector = "body > .ui-layout-east"; // outer-east pane

		 // CREATE SPANs for pin-buttons - using a generic class as identifiers
		$("<span></span>").addClass("pin-button").prependTo( westSelector );
		//$("<span></span>").addClass("pin-button").prependTo( eastSelector );
		// BIND events to pin-buttons to make them functional
		outerLayout.addPinBtn( westSelector +" .pin-button", "west");
		//outerLayout.addPinBtn( eastSelector +" .pin-button", "east" );

		 // CREATE SPANs for close-buttons - using unique IDs as identifiers
		$("<span></span>").attr("id", "west-closer" ).prependTo( westSelector );
		//$("<span></span>").attr("id", "east-closer").prependTo( eastSelector );
		// BIND layout events to close-buttons to make them functional
		outerLayout.addCloseBtn("#west-closer", "west");
		//outerLayout.addCloseBtn("#east-closer", "east");



		/* Create the INNER LAYOUT - nested inside the 'center pane' of the outer layout
		 * Inner Layout is create by createInnerLayout() function - on demand
		 *
			innerLayout = $("div.pane-center").layout( layoutSettings_Inner );
		 *
		 */


		// DEMO HELPER: prevent hyperlinks from reloading page when a 'base.href' is set
		$("a").each(function () {
			var path = document.location.href;
			if (path.substr(path.length-1)=="#") path = path.substr(0,path.length-1);
			if (this.href.substr(this.href.length-1) == "#") this.href = path +"#";
		});

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
	}
	,	north: {
			spacing_open:			1			// cosmetic spacing
		,	togglerLength_open:		0			// HIDE the toggler button
		,	togglerLength_closed:	-1			// "100%" OR -1 = full width of pane
		,	resizable: 				false
		,	slidable:				false
		//	override default effect
		,	fxName:					"none"
		,	initClosed:				false
		}
	,	south: {
			maxSize:				200
		,	spacing_closed:			0			// HIDE resizer & toggler when 'closed'
		,	resizable: 				false
		,	slidable:				false		// REFERENCE - cannot slide if spacing_closed = 0
		,	initClosed:				true
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
		,	resizable: 				false
		//	add 'bounce' option to default 'slide' effect
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
		//	override default effect, speed, and settings
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

	//$("#accordion").accordion();
	var accord=$('#accordion').accordion('option', 'animated')
	

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
	
</script>
</head>
<body>

<div class="ui-layout-west">

	
	<div class="header">导航栏</div>

	<div class="content" style="position: relative; height: 174px; visibility: visible;">
			<div id="demo" class="demo" style="height:200px;"></div>
			<script type="text/javascript" class="source below">
$(function () {

	$("#demo").jstree({ 
		"themes" : {
	        "theme" : "classic",
	        "dots" : true,
	        "icons" : true 
    	},
    	"json_data" : {
	        "ajax" : {
	            "url" : "mail?action=jstree"
	       }
    	}
		,
	"plugins" : [ "themes", "json_data" ]
	});	
	
});
</script>
	</div>
</div>


<div class="ui-layout-north">
	<div class="header">云端统一通信平台</div>
	
	
	
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
			<div id="tabs">
				<ul id="ultab">
				</ul>
				<DIV class="ui-layout-content">
				<div id="tabs-1">
				</div>
			</div>
		</div>
	</div>
	<div class="ui-layout-south">详细信息显示内容栏</div>
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
	    //innerLayout.toggle('north')
	    //innerLayout.sizePane('north', 150)
	}
	</script>
</body>
</html>