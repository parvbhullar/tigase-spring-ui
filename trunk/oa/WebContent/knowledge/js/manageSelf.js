
Ext.namespace("mySelf");

myPanel=Ext.extend(Ext.Panel,{
	
	id:'',
	title:'权限分配',
    treeText:null,
    treeId:null,
	allowBlank:false,
	blankText:'this isrequired',
	dirTree:null,
	textfeild:null,
	formP:null,
	p:null,
	constructor:function(config){
		this.formP=new Ext.form.FormPanel({
			id : 'modifyMenuFormPanel1',
			name : 'modifyMenuFormPanel1',
			
			items : [{
						fieldLabel : '',
						name : 'treeId1',
						id : 'treeId1',
						hidden:true
					},{
						id : 'treeText1',
						name : 'treeText1',
						hidden : true
					}]
		});
	
		var treeText1=Ext.id('treeText1');
		this.root = new Ext.tree.AsyncTreeNode({
			text :'根目录',
			expanded : true,
			id : '001003001'
		});
		
this.dirTree = new Ext.tree.TreePanel({
			loader : new Ext.tree.TreeLoader({
						baseAttrs : {},
						dataUrl : './k.xzb?reqCode=dirTreeInit'
					}),
			root : this.root,
			title : '', 
			applyTo : 'deptTreeDiv',
			autoScroll : false,
			animate : false,
			useArrows : false,
			border : false,
			listeners:{
				click:{
					fn:function(node){
						this.treeId = node.attributes.id;
						this.treeText=node.attributes.text;
						alert(this.treeText);
						this.formP.getItemId();
					}
				}
				
			}
		});
//this.dirTree.on('click', this.clickTree);
this.searchBtn2 = new Ext.Button({  
    text : '查询2'  
});  
this.tbar = new Ext.Toolbar({
	items : [ {
				xtype:'button',
				text : '新增',
				iconCls : 'page_addIcon',
				
	}, '-', {
		xtype:'button',
		text : '删除',
		iconCls : 'deleteIcon',
	}, '->',{
		xtype:'button',
		text : '刷新',
		iconCls : 'page_refreshIcon',
		handler : function() {
			store.reload();
		}
	} ]
});

this.formP=new Ext.form.FormPanel({
	id : 'modifyMenuFormPanel',
	name : 'modifyMenuFormPanel',
	
	items : [{
				fieldLabel : '',
				name : 'treeId',
				id : 'treeId',
				hidden:true
			},{
				id : 'treeText',
				name : 'treeText',
				hidden : true
			}]
});
this.p = new Ext.Panel({
	width:100,
	height:500,
	layout: {
        type:'vbox',
        padding:'5',
        pack:'start',
        align:'center'
    },
    defaults:{margins:'0 0 5 0'},
    items:[{
        xtype:'button',
        text: '增加',
        handler : this.addTree
    },{
        xtype:'button',
        text: '取消'
    },{
        xtype:'button',
        text: '全部取消'
    },{
    	xtype:'textfield',
    	hidden:true
    	
    	
    	
    }]

    
});


this.textfeild=new Ext.form.TextArea({
    width:150,
    //border:false,
    height:500,
   // bodyStyle:"padding:5px 5px 0",
    items:[
       {
         xtype:"textfield",
         id:'user',
         style:'border:0px;'
        	 
       },
       {
         xtype:"textfield",
         style:'border:0px;'
       }, {
           xtype:"textfield",
           style:'border:0px;'
         }
       , {
           xtype:"textfield",
           style:'border:0px;'
         }
       , {
           xtype:"textfield",
           style:'border:0px;'
         }
    ]
 });

myPanel.superclass.constructor.call(this, Ext.apply(config || {}, {
	layout : 'border',
	width : 500,
	height : 500,
	split : true,
	items : [{
		region:'west',
		width:200,
		height:500,
		items : [this.dirTree]
	},{
		region:'center',
		width:100,
		height:500,
		items : [this.p]
	},{
		region:'east',
		width:200,
		height:500,
		items : [this.textfeild]
	}]
}));
		
		
		
	},
addTree:function(){
	//var t=this.dirTree.getSelectionModel().getSelectedNode();
	Ext.getCmp('treeText').getValue();
	if(this.treeText=='')
		{
			alert("qing xuanz ");
		}else{
			alert(this.treeText);
		}
	
	
},
clickTree:function(node) {
	this.treeId = node.attributes.id;
	this.treeText=node.attributes.text;
	alert(this.treeText);
	Ext.getCmp("treeText").setValue(this.treeText);
	
	if(this.treeText=='根目录')
		{
		
			return;
		}
}
		
});
Ext.reg('myPanel', myPanel);


 
