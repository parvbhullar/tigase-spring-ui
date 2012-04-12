/**
 * 首页部分JS
 * 
 * @author XiongChun
 * @since 2010-03-13
 */


Ext.onReady(function() {
	
	var arrData=new Array();
	var options = { 
		    success:    function(data) {
		    	for(var i=0;i<eval("("+data+")").ROOT.length;i++){
		    		var temp=eval("("+data+")").ROOT[i];
		    		$("#portlet1 table tr:eq("+(i+1)+") td:eq(0) div").html(temp.doctitle);
		    		$("#portlet1 table tr:eq("+(i+1)+") td:eq(1) div").html(temp.docauthor);
		    		$("#portlet1 table tr:eq("+(i+1)+") td:eq(2) div").html(temp.updatetime);
		    	}
		    	$("#portlet1").click(function() {
//		    		addTab('k.xzb?reqCode=kInit','文档目录','010401','文档目录','tab_blank.png');
//		    		function addTab(url,name,menuid,pathCh,icon){
		    			var url='k.xzb?reqCode=kInit';
		    			var name='文档目录';
		    			var menuid='010401';
		    			var pathCh='文档目录';
		    			var icon='tab_blank.png';
		    			  var id = "tab_id_" + menuid;
		    			  if(url == '#' || url == ''){
		    			    //Ext.Msg.alert('提示', '此菜单还没有指定请求地址,无法为您打开页面.');
		    			    return;
		    			  }else{
		    			  var index = url.indexOf('.xzb');
		    			  if(index != -1)
		    			    url = url + '&menuid4Log=' + menuid;
		    			  mainTabs=window.parent.parent.parent.Ext.getCmp('mainTabs')
		    			  var n = mainTabs.getComponent(id);
		    			  if (!n) {
		    			     // 如果对centerPanel进行遮罩,则可以出现阴影mainTabs
		    			     //Ext.getCmp('centerPanel').getEl().mask('<span style=font-size:12>正在加载页面,请稍等...</span>', 'x-mask-loading');
		    			     // document.getElementById('endIeStatus').click();//解决Iframe IE加载不完全的问题
		    			     // 兼容IE和FF触发.click()函数
		    			     var endIeStatus = window.parent.parent.parent.document.getElementById("endIeStatus");
		    			     if(document.createEvent){
		    			         var ev = document.createEvent('HTMLEvents');
		    			         ev.initEvent('click', false, true);
		    			         endIeStatus.dispatchEvent(ev);
		    			     }
		    			     else endIeStatus.click();
		    			     n = mainTabs.add({
		    			       id:id,
		    			       title:"<img align='top' class='IEPNG' src='./resource/image/ext/" + icon + "'/>" + name,
		    			       closable:true,
		    			       layout:'fit',
		    			       listeners: {activate: function(){window.parent.parent.parent.Ext.getCmp('centerPanel').setTitle(pathCh)}},
		    			       html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src='+url+'></iframe>'
		    				   //如果功能页面使用中心区域阴影加载模式则使用下面的代码unmaskCenterPanel();页面加载完毕后取消阴影
		    				   //html:'<iframe scrolling="auto" frameborder="0" onload="unmaskCenterPanel()" width="100%" height="100%" src='+url+'></iframe>'
		    			         });
		    			       }
		    			  mainTabs.setActiveTab(n);
		    			 }
				});
		    } 
		}; 
	$('#indexPortalForm').ajaxForm(options);
	$("#indexPortalForm").submit();
	
	SampleGrid = function(limitColumns){
		

	    function italic(value){
	        return '<i>' + value + '</i>';
	    }

	    function change(val){
	        if(val > 0){
	            return '<span style="color:green;">' + val + '</span>';
	        }else if(val < 0){
	            return '<span style="color:red;">' + val + '</span>';
	        }
	        return val;
	    }

	    function pctChange(val){
	        if(val > 0){
	            return '<span style="color:green;">' + val + '%</span>';
	        }else if(val < 0){
	            return '<span style="color:red;">' + val + '%</span>';
	        }
	        return val;
	    }


	    var columns = [
	        {id:'company',header: "标题", width: 160, sortable: true, dataIndex: 'company'},
	        {header: "Price", width: 75, sortable: true, renderer: Ext.util.Format.usMoney, dataIndex: 'price'},
	        {header: "上传人", width: 75, sortable: true, renderer: change, dataIndex: 'change'},
	        {header: "修改时间", width: 75, sortable: true, renderer: pctChange, dataIndex: 'pctChange'},
	        {header: "最后修改时间", width: 85, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastChange'}
	    ];

	    // allow samples to limit columns
	    if(limitColumns){
	        var cs = [];
	        for(var i = 0, len = limitColumns.length; i < len; i++){
	            cs.push(columns[limitColumns[i]]);
	        }
	        columns = cs;
	    }

	    SampleGrid.superclass.constructor.call(this, {
	        store: new Ext.data.Store({
	            reader: new Ext.data.ArrayReader({}, [
	                   {name: 'company'},
	                   {name: 'price', type: 'float'},
	                   {name: 'change', type: 'float'},
	                   {name: 'pctChange', type: 'float'},
	                   {name: 'lastChange', type: 'date', dateFormat: 'n/j h:ia'}
	              ]),
	            data: [
	                ['','','','',''],
	                ['','','','',''],
	                ['','','','',''],
	                ['','','','',''],
	                ['','','','',''],
	                ['','','','',''],
	                ['','','','',''],
	                ['','','','','']
	            ]
	        }),
	        columns: columns,
	        autoExpandColumn: 'company',
	        height:250,
	        width:600
	    });


	}

	Ext.extend(SampleGrid, Ext.grid.GridPanel);

	/*!
	 * Ext JS Library 3.0.0
	 * Copyright(c) 2006-2009 Ext JS, LLC
	 * licensing@extjs.com
	 * http://www.extjs.com/license
	 */
	Ext.onReady(function(){

	    // NOTE: This is an example showing simple state management. During development,
	    // it is generally best to disable state management as dynamically-generated ids
	    // can change across page loads, leading to unpredictable results.  The developer
	    // should ensure that stable state ids are set for stateful components in real apps.
	    Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

	    // create some portlet tools using built in Ext tool ids
	    var tools = [{
	        id:'gear',
	        handler: function(){
	            Ext.Msg.alert('Message', 'The Settings tool was clicked.');
	        }
	    },{
	        id:'close',
	        handler: function(e, target, panel){
	            panel.ownerCt.remove(panel, true);
	        }
	    }];

	    var viewport = new Ext.Viewport({
	        layout:'border',
	        items:[{
	            xtype:'portal',
	            region:'center',
	            margins:'35 5 5 0',
	            items:[{
	                columnWidth:.33,
	                style:'padding:10px 0 10px 10px',
	                items:[{
	                    title: '知识管理',
	                    id: 'portlet1',
	                    layout:'fit',
	                    tools: tools,
	                    items: new SampleGrid([0, 2, 3])
	                },{
	                    title: '工作流',
	                    tools: tools,
	                    html: ''
	                }]
	            },{
	                columnWidth:.33,
	                style:'padding:10px 0 10px 10px',
	                items:[{
	                    title: '统计分析',
	                    tools: tools,
	                    html: ''
	                },{
	                    title: '资产管理',
	                    tools: tools,
	                    html: ''
	                }]
	            },{
	                columnWidth:.33,
	                style:'padding:10px',
	                items:[{
	                    title: '项目管理',
	                    tools: tools,
	                    html: ''
	                },{
	                    title: '会议管理',
	                    tools: tools,
	                    html: ''
	                }]
	            }]
	            
	            /*
	             * Uncomment this block to test handling of the drop event. You could use this
	             * to save portlet position state for example. The event arg e is the custom 
	             * event defined in Ext.ux.Portal.DropZone.
	             */
//	            ,listeners: {
//	                'drop': function(e){
//	                    Ext.Msg.alert('Portlet Dropped', e.panel.title + '<br />Column: ' + 
//	                        e.columnIndex + '<br />Position: ' + e.position);
//	                }
//	            }
	        }]
	    });
	});
});


