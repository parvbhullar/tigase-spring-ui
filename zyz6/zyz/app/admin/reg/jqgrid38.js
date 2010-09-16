jQuery(document).ready(function(){
    //$('#switcher').themeswitcher();

	$('body').layout({
		resizerClass: 'ui-state-default',
        west__onresize: function (pane, $Pane) {
            jQuery("#west-grid").jqGrid('setGridWidth',$Pane.innerWidth()-2);
		}
	});
	$.jgrid.defaults = $.extend($.jgrid.defaults,{loadui:"enable"});
	
	tableToGrid("#grid", {
        height:300,
        autowidth:true,
        pager: 'pager',
        rowNum: 10,
        viewrecords: true,
        loadui: true,
        rowList: [10,20,50]
    });
	
	var mydata;
	
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
	jQuery("#list47").jqGrid(
  			{ 	
  				url:"http://127.0.0.1:8080/zyz/app/admin/reg/jqgriddata.action", 
  				datatype: "json", 
  				colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'], 
  				colModel:[ 
  				           {name:'id',index:'id', width:55}, 
  				           {name:'invdate',index:'invdate', width:90}, 
  				           {name:'name',index:'name asc, invdate', width:100}, 
  				           {name:'amount',index:'amount', width:80, align:"right"}, 
  				           {name:'tax',index:'tax', width:80, align:"right"}, 
  				           {name:'total',index:'total', width:80,align:"right"}, 
  				           {name:'note',index:'note', width:150, sortable:false} 
  				           ], 
  				           rowNum:10, rowList:[10,20,30], 
  				           sortname: 'id', 
  				           viewrecords: true, 
  				           sortorder: "desc", 
  				           caption:"JSON Example" 
  				/*
  				//data: mydata, 
  				//datatype: "local",
  				url:"http://127.0.0.1:8080/zyz/app/admin/reg/jqgriddata.action",
  				datatype: "json",
  				height: 255, 
  				width: 600, 
  				  colNames:['Inv No','Date', 'Client', 'Amount','Tax','Total','Notes'], 
  				  	colModel:[ {name:'id',index:'id', width:60, sorttype:"int"}, 
  				  	           {name:'invdate',index:'invdate', width:90, sorttype:"date", formatter:"date"}, 
  				  	           {name:'name',index:'name', width:100}, 
  				  	           {name:'amount',index:'amount', width:80, align:"right",sorttype:"float", formatter:"number"}, 
  				  	           {name:'tax',index:'tax', width:80, align:"right",sorttype:"float"}, 
  				  	           {name:'total',index:'total', width:80,align:"right",sorttype:"float"}, 
  				  	           {name:'note',index:'note', width:150, sortable:false} 
  				  	           ], 
			      rowNum:50, 
			      rowTotal: 2000, 
			      rowList : [20,30,50], 
			      loadonce:true, 
			      mtype: "GET", 
			      rownumbers: true, 
			      rownumWidth: 40, 
			      gridview: true, 
			      caption: "Loading data from server at once"
			      */
  	  		});
	
	
	
	var maintab =jQuery('#tabs','#RightPane').tabs({
        add: function(e, ui) {
            // append close thingy
            $(ui.tab).parents('li:first')
                .append('<span class="ui-tabs-close ui-icon ui-icon-close" title="Close Tab"></span>')
                .find('span.ui-tabs-close')
                .click(function() {
                    maintab.tabs('remove', $('li', maintab).index($(this).parents('li:first')[0]));
                });
            // select just added tab
            maintab.tabs('select', '#' + ui.panel.id);
        }
    });
    jQuery("#west-grid").jqGrid({
        url: "jqgriddata.action",
        datatype: "json",
        height: "auto",
        pager: false,
        loadui: "disable",
        colNames: ["id","Items","url"],
        colModel: [
            {name: "id",width:1,hidden:true, key:true},
            {name: "menu", width:150, resizable: false, sortable:false},
            {name: "url",width:1,hidden:true}
        ],
        treeGrid: true,
		caption: "jqGrid Demos",
        ExpandColumn: "menu",
        autowidth: true,
        //width: 180,
        rowNum: 200,
        ExpandColClick: true,
        treeIcons: {leaf:'ui-icon-document-b'},
        onSelectRow: function(rowid) {
            var treedata = $("#west-grid").jqGrid('getRowData',rowid);
            if(treedata.isLeaf=="true") {
                //treedata.url
                var st = "#t"+treedata.id;
				if($(st).html() != null ) {
					maintab.tabs('select',st);
				} else {
					maintab.tabs('add',st, treedata.menu);
					$(st,"#tabs").load(treedata.url);
					/*
					$.ajax({
						url: treedata.url,
						type: "GET",
						dataType: "html",
						complete : function (req, err) {
							$(st,"#tabs").append(req.responseText);
							//
							//try { var pageTracker = _gat._getTracker("UA-5463047-4"); pageTracker._trackPageview(); } catch(err) {};
							//var fs = '<iframe src="adds.html" style="width:336px; height:290px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0"/>\
								//	&nbsp;&nbsp;&nbsp;&nbsp;<iframe src="adds3.html" style="width:336px; height:290px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0"/>\
									//<br/><iframe src="adds2.html" style="width:336px; height:290px;" scrolling="no" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0"/>\
									//&nbsp;&nbsp;&nbsp;&nbsp;<iframe src="adds4.html" style="width:336px; height:290px;"scrolling="no" marginwidth="0" marginheight="0" frameborder="0" vspace="0" hspace="0"/>\
							//';
							//$(st,"#tabs").append(fs);
							
						}
					});
					*/
				}
            }
        }
    });
	
// end splitter

});