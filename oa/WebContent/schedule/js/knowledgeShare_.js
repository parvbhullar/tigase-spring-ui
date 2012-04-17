//version 3.0

Ext.ux.ItemSelector = Ext.extend(Ext.Panel,  {
	msWidth:200,
	msHeight:600,
	hideNavIcons:false,
	imagePath:null,//图片路径
	iconLeft:"left2.gif",
	iconRight:"right2.gif",
	saveImg:"add.png",
	drawLeftIcon:true,
	drawRightIcon:true,
	saveIcon:true,
	displayField:0,
	valueField:1,
	switchToFrom:false,
	focusClass:undefined,
	readOnly:false,
	toLegend:null,
	fromLegend:null,
	toSortField:null,
	fromSortField:null,
	bodyStyle:null,
	border:false,
	dataUrl:null,//左边树
	hiddenField1:null,
	defaultAutoCreate:{tag: "div"},
	leftTreeRoot:null,//左边树根节点
	leftTreeId:null,//左边树根节点
	saveLimitisUrl:null,
	
    initComponent: function(){
		Ext.ux.ItemSelector.superclass.initComponent.call(this);
		this.addEvents({
			'rowdblclick' : true,
			'change' : true
		});			
	},

    onRender: function(ct, position){
		Ext.ux.ItemSelector.superclass.onRender.call(this, ct, position);
		
		var scheduleId=Ext.getCmp('scheduleId').getValue();//目录id
		var DoclimitisType="";//Ext.getCmp('DirlimitisType').getValue();
		//alert(docId);
		//var depType=Ext.getCmp('depLimitis').getValue();
		//var peopleType=Ext.getCmp('peopleLimitis').getValue();
		//var limitisType=Ext.getCmp('limitisType').getValue();
	
		var treeTextEl={tag: "input", type: "hidden", value: "",id:"treeTextEl", name:this.name};
		var treeIdEl={tag: "input", type: "hidden", value: "",id:"treeIdEl", name:this.name};
		var parentNodeId={tag: "input", type: "hidden", value: "",id:"parentNodeId", name:this.name};
		var treeLeaf={tag: "input", type: "hidden", value: "",id:"treeLeaf", name:this.name};
		
		
		var rightTextEl={tag: "input", type: "hidden", value: "",id:"rightTextEl", name:this.name};
		var rightIdEl={tag: "input", type: "hidden", value: "",id:"rightIdEl", name:this.name};
		var rightParent={tag: "input", type: "hidden", value: "",id:"rightParent", name:this.name};
		
		
		this.textel = Ext.get(document.body).createChild(treeTextEl);
		this.idel = Ext.get(document.body).createChild(treeIdEl);
		this.parentId=Ext.get(document.body).createChild(parentNodeId);
		this.leafel=Ext.get(document.body).createChild(treeLeaf);
		
		this.righttextel = Ext.get(document.body).createChild(rightTextEl);
		this.rightidel = Ext.get(document.body).createChild(rightIdEl);
		this.rightPInput=Ext.get(document.body).createChild(rightParent);
		
	
		
		this.rightRoot=new Ext.tree.AsyncTreeNode({
			expanded : true,
			text:'权限列表'
		});
		
		this.rightTree=new Ext.tree.TreePanel({
			width:200,
			height:500,
			root:this.rightRoot,
			loader : new Ext.tree.TreeLoader({
				dataUrl :'./k.xzb?reqCode=queryLimitis_',
				 baseParams:
				 {
					 docId:scheduleId,
					 moduleType:'0',
					 DoclimitisType:DoclimitisType
					
				}
			}),
			
		});
		
		
		//this.rightTree.setRootNode(this.rightRoot);
		
		this.rightTree.on("click",this.rightTreeClick);
		
		this.rightTree.on('dblclick', this.rightDblClick, this);
		//右边的树
		this.root = new Ext.tree.AsyncTreeNode({
			text :this.leftTreeRoot,
			expanded : true,
			id : this.leftTreeId
		});
		this.treeLoader=new Ext.tree.TreeLoader({
			//baseAttrs : {},
			dataUrl : this.dataUrl
		});
		this.treeLoader.on('beforeload',function(treeLoader,node){
		
		});
		this.dirTree = new Ext.tree.TreePanel({
			loader : this.treeLoader,
			root : this.root,
			title : this.fromLegend, 
			autoScroll : true,
			animate : false,
			useArrows : false,
			width:200,
			height:500,
			border : true,
		
		});
		this.dirTree.on("click",this.treeClick);
		
		this.dirTree.on('dblclick', this.treeDblClick, this);
		
		this.b=new Ext.Button({
			text:'保存',
			iconCls : 'page_addIcon'
		});
		/**
		 * {
						xtype:'button',
						text : '保存',
						iconCls : 'page_addIcon',
						listeners:{
							'click':this.saveRightTree
							
						}
						
			}
		 */
		this.tbar = new Ext.Toolbar({
			items : [this.b
			         
			         ]
		});


		//this.toMultiselect.on('dblclick', this.onRowDblClick, this);
				
		var p = new Ext.Panel({
			//bodyStyle:this.bodyStyle,
			//border:this.border,
			//tbar:[this.tbar],
			layout:"table",
			layoutConfig:{columns:3}
		});
		p.add(this.dirTree);
		var icons = new Ext.Panel({header:false});
		p.add(icons);
		//p.add(this.switchToFrom ? this.fromMultiselect : this.toMultiselect);
		p.add(this.rightTree);
		p.render(this.el);
		icons.el.down('.'+icons.bwrapCls).remove();

		if (this.imagePath!="" && this.imagePath.charAt(this.imagePath.length-1)!="/")
			this.imagePath+="/";
		this.iconLeft = this.imagePath + (this.iconLeft || 'left2.gif');
		this.iconRight = this.imagePath + (this.iconRight || 'right2.gif');
		this.saveImg= this.imagePath + (this.saveImg|| 'add.png');
		var el=icons.getEl();
		if (!this.toSortField) {
		}
		this.addIcon = el.createChild({tag:'img', src:this.switchToFrom?this.iconLeft:this.iconRight, style:{cursor:'pointer', margin:'2px'}});
		el.createChild({tag: 'br'});
		this.removeIcon = el.createChild({tag:'img', src:this.switchToFrom?this.iconRight:this.iconLeft, style:{cursor:'pointer', margin:'2px'}});
		el.createChild({tag: 'br'});
		this.saveIcon=el.createChild({tag:'img', src:this.saveImg, style:{cursor:'pointer', margin:'2px'}});
		el.createChild({tag: 'br'});
		if (!this.toSortField) {
		}
		if (!this.readOnly) {
			this.addIcon.on('click', this.fromTo, this);
			this.removeIcon.on('click', this.toFrom, this);
			this.saveIcon.on('click', this.saveRightTree, this);
		}
		if (!this.drawLeftIcon || this.hideNavIcons) { this.addIcon.dom.style.display='none'; }
		if (!this.drawRightIcon || this.hideNavIcons) { this.removeIcon.dom.style.display='none'; }


		var tb = p.body.first();
		this.el.setWidth(p.body.first().getWidth());
		p.body.removeClass();
		p.doLayout();
	},
	
	initValue:Ext.emptyFn,
	
	
	treeClick:function(node)
	{
			this.treeId = node.attributes.id;
			this.treeText=node.attributes.text;
			this.parentId=node.parentNode.attributes.id;
			this.treeLeaf=node.attributes.leaf;
			document.getElementById("treeTextEl").value=this.treeText;
			document.getElementById("treeIdEl").value=this.treeId;
			document.getElementById("parentNodeId").value=this.parentId;
			document.getElementById("treeLeaf").value=this.treeLeaf;
	},
	
	treeDblClick:function(node)
	{
		var treeId = node.attributes.id;
		var treeText=node.attributes.text;
		var parentId=node.parentNode.attributes.id;
		var treeLeaf=node.attributes.leaf;
		if(treeLeaf==false)
		{
				Ext.MessageBox.alert('提示', '不是叶节点，请重新选择');
				return;
		}
		
		if(treeId=="" && treeText=="")
		{
			Ext.MessageBox.alert('提示', '你还没选择左边的树');
			return;
		}
		
		this.roonodes = this.rightTree.getRootNode().childNodes;
        for(var i=0;i<this.roonodes.length;i++){  //从节点中取出子节点依次遍历
            var rootnode = this.roonodes[i];
            if(rootnode.id==treeId)
            	{
            	Ext.MessageBox.alert('提示', '左边已经存在了');
            		return;
            	}
            //if(rootnode.childNodes.length>0){  //判断子节点下是否存在子节点，个人觉得判断是否leaf不太合理，因为有时候不是leaf的节点也可能没有子节点
                //findchildnode(rootnode);    //如果存在子节点  递归
            //}    
        }
		var node1 = this.dirTree.getNodeById(treeId);
		node1.remove();
		var treeNode=new Ext.tree.TreeNode({
			text:treeText,
			id:treeId,
			parntId:parentId
		});
		this.rightRoot.appendChild(treeNode);
	},
	
	
	
	rightTreeClick:function(node)
	{
		this.rightId = node.attributes.id;
		this.rightText=node.attributes.text;
		this.rightP=node.attributes.parntId;
		document.getElementById("rightTextEl").value=this.rightText;
		document.getElementById("rightIdEl").value=this.rightId;
		document.getElementById("rightParent").value=this.rightP;
		
	},
	
	rightDblClick:function(node)
	{
		var rightId = node.attributes.id;
		var rightText=node.attributes.text;
		var rightP=node.attributes.parntId;
		
		if(rightText=="" && rightId=="")
		{
			Ext.MessageBox.alert('提示', '你还没选择右边的树');
			return;
		}
		var treeNode1=new Ext.tree.TreeNode({
			text:rightText,
			id:rightId,
			icon:'./resource/image/ext/folder.gif'
		});
		var node1 = this.rightTree.getNodeById(rightId);		
		var pNode=this.dirTree.getNodeById(rightP);
		if(!pNode)
			{
				node1.remove();
				return;
			}
		pNode.appendChild(treeNode1);
		node1.remove();
		
		
		
	},
	
	fromTo : function() {
		
		var tText=document.getElementById("treeTextEl").value;
		var tId=document.getElementById("treeIdEl").value;
		var parentId=document.getElementById("parentNodeId").value;
		var tLeaf=document.getElementById("treeLeaf").value;
		if(tLeaf=='false')
			{
			Ext.MessageBox.alert('提示', '不是叶节点，请重新选择');
			return;
			
			}
		if(tText=="" && tId=="")
			{
				Ext.MessageBox.alert('提示', '你还没选择左边的树');
				return;
			}
		
		/**
		 * 判断右面的树是否已经存在了
		 */
		this.roonodes = this.rightTree.getRootNode().childNodes;
        for(var i=0;i<this.roonodes.length;i++){  //从节点中取出子节点依次遍历
            var rootnode = this.roonodes[i];
            if(rootnode.id==tId)
            	{
            	Ext.MessageBox.alert('提示', '左边已经存在了');
            		return;
            	}
            //if(rootnode.childNodes.length>0){  //判断子节点下是否存在子节点，个人觉得判断是否leaf不太合理，因为有时候不是leaf的节点也可能没有子节点
                //findchildnode(rootnode);    //如果存在子节点  递归
            //}    
        }
		
		
		
		var node1 = this.dirTree.getNodeById(tId);
		node1.remove();
		var treeNode=new Ext.tree.TreeNode({
			text:tText,
			id:tId,
			parntId:parentId
		});
		this.rightRoot.appendChild(treeNode);
	
	},
	
	toFrom : function() {
		var rightText=document.getElementById("rightTextEl").value;
		var rightId=document.getElementById("rightIdEl").value;
		var rightP=document.getElementById("rightParent").value;
		if(rightText=="" && rightId=="")
		{
			Ext.MessageBox.alert('提示', '你还没选择右边的树');
			return;
		}
		var treeNode1=new Ext.tree.TreeNode({
			text:rightText,
			id:rightId,
			icon:'./resource/image/ext/folder.gif'
		});
		var node1 = this.rightTree.getNodeById(rightId);		
		var pNode=this.dirTree.getNodeById(rightP);
		if(!pNode)
			{
				node1.remove();
				return;
			}
		pNode.appendChild(treeNode1);
		node1.remove();
		return;
		/**
		this.roonodes = this.rightTree.getRootNode().childNodes;
		alert(this.roonodes);
        for(var i=0;i<this.roonodes.length;i++){  //从节点中取出子节点依次遍历
            var rootnode = this.roonodes[i];
            alert(rootnode.text);
            //if(rootnode.childNodes.length>0){  //判断子节点下是否存在子节点，个人觉得判断是否leaf不太合理，因为有时候不是leaf的节点也可能没有子节点
                //findchildnode(rootnode);    //如果存在子节点  递归
            //}    
        }
        */
		
	},
	
	saveRightTree:function()
	{
		this.roonodes = this.rightTree.getRootNode().childNodes;
		var docId=Ext.getCmp('scheduleId').getValue();//文档id
		var DoclimitisType=Ext.getCmp('limitisType').getValue();
		var fields = '';
		var fieldsName='';
        for(var i=0;i<this.roonodes.length;i++){  //从节点中取出子节点依次遍历
            var rootnode = this.roonodes[i];
            if(fields=='')
            	{
            		fields=this.roonodes[0].id;
            	}else{
            			fields = fields +","+ rootnode.id;
            		}
            if(fieldsName=='')
            	{
            		fieldsName=this.roonodes[0].text;
           
            	}else{
            		
            		fieldsName=fieldsName+","+rootnode.text;
            	}
          
        }
        Ext.Ajax.request({
			url :'./k.xzb?reqCode=saveLimitisDept',
			success : function(response) {
				var resultArray = Ext.util.JSON
						.decode(response.responseText);
				
			
				if(DoclimitisType=='0')
					{
						Ext.getCmp('selectDeps').setValue(fieldsName);
						Ext.getCmp('selectDeptsWindow').close();
					
					}else if(DoclimitisType=='1')
						{
							Ext.getCmp('selectPersons').setValue(fieldsName);
							Ext.getCmp('selectPersonsWindow').close();
						}
				Ext.Msg.alert('提示', resultArray.msg);
			},
			failure : function(response) {
				var resultArray = Ext.util.JSON
						.decode(response.responseText);
				
				Ext.Msg.alert('提示', resultArray.msg);
				
			},
			params : {
				fields :fields,
				docId:docId,
				fieldsName:fieldsName,
				moduleType:'2',
				DoclimitisType:DoclimitisType
			}
		});
        
	}
});

Ext.reg("knowledgeShare_", Ext.ux.ItemSelector);