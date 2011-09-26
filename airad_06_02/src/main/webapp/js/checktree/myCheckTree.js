var tree;

/**
 * Yahoo Ajax Get Method
 * @param divName 树生成位置指定div
 * @param sUrl 访问路径
 * @param treeType 树类型（default:普通树,check:带checkBox
 * @param addLabelEvent 是否
 * @param addCheckBoxEvent 树生成位置指定div
 * @param domains 已经被选择的值
 **/
function getTreeByAjax(divName,sUrl,treeType,addLabelEvent,addCheckBoxEvent,domains){

    var call =
        {
        //正常返回处理函数
        success: buildSuccess,
         //出错返回处理函数 
        failure: handleFailure,
        //可以在success函数和failure函数中访问的变量
        argument: {treeDiv: divName,
                    url: sUrl,
                    treeName: treeType,
                    isLabelEvent: addLabelEvent,
                    isCheckEvent: addCheckBoxEvent,
                    checkResult:domains}
        }

    //AJAX GET请求
    var transaction = YAHOO.util.Connect.asyncRequest('GET', sUrl, call);
    return tree;
}

/**
 * 将结果集进行处理，把数组转换成以';'分割的字串返回
 * @param tree 树对象
 * @return str 结果字串
 **/
function getMultipleResult(tree){
    var checkResult = getCheckResult(tree);
	
    return getStrFromArr(checkResult);
}

/**
 * 将结果集进行处理，只允许一个元素的数组结果，否则报错
 * @param tree 树对象
 * @return str 结果字串
 **/
function getSingleCheckResult(tree){
	var checkResult = getCheckResult(tree);
	
	if(checkResult.length > 1){
		alert("归属区域只能是一个");
		return null;
	} else {
	   return checkResult[0];
	}
}

/**
 * 将结果集进行处理，并得到所有选择结果的叶子节点
 * 把数组转换成以';'分割的字串返回
 * @param tree 树对象
 * @return str 结果字串
 **/
function getMultipleLeafResult(tree){
    var checkResult = new Array(0);
    var topNodes = tree.getRoot().children;
    getLeafResult(topNodes, checkResult);
	
    return getStrFromArr(checkResult);
}

/**
 * 将一个节点的所有子节点ID返回，包括自身
 * @param tree 树对象
 * @param domainId 节点值
 * @return domains 结果值 以";"分割
 **/
function getDomains(tree,domainId){
    var topNodes = tree.getNodeByIndex(domainId);	
    var domains = new Array();
    domains.push(domainId);
    
    var childrenNodes = topNodes.children;
    if(childrenNodes){
        getSelectDomains(childrenNodes, domains);
    }
    
    return getStrFromArr(domains);
}

/**
 * 将一个节点的所有父节点ID返回，包括自身
 * 只返回单个节点的父节点，点击多个节点报错 返回null
 * @param tree 树对象
 * @param domainId 节点值
 * @return domains 结果值 以";"分割
 **/
function getParentDomains(tree){
    var checkResult = getCheckResult(tree);
    var domains = new Array();
    var root = tree.getRoot();

    if(checkResult.length == 0){
        return "";
    } else if (checkResult.length > 1){
        alert("只能选择一个区域");
        return null;
    } else {
        var node = tree.getNodeByIndex(checkResult[0]);
        var parentNode = node.parent;
        while(parentNode != root){
            domains.push(tree.getNodeByIndex(parentNode.index).index);
            parentNode = parentNode.parent;
            }
            domains.push(node.index);
    }

    return getStrFromArr(domains)
}

/**
 * 将数组转化为以“;”分割的字符串
 */
function getStrFromArr(result){
	var str = "";
	if(result.length>0){
		for(var i=0,len=result.length;i<len;i++){
			str=str + result[i];
			if(i!=len-1){
				str = str + ";";
			}
		}
	}
	
	return str;
}

/**
 * 将一个节点的所有子节点ID返回，包括自身
 * @param tree 树对象
 * @param domainId 节点值
 * @return domains 结果值 以";"分割
 **/
function getSelectDomains(nodes,domains){
    for(var i=0;i<nodes.length;i++){
        var node = nodes[i];
        domains.push(node.index);
        var childrenNodes = node.children;
        if(childrenNodes){
            getSelectDomains(childrenNodes, domains);
        }
    }
}

/**
 * 取得checktree选择结果，将结果保存在一个数组中返回
 * @param tree 树对象
 * @return checkResult[] 结果集
 **/
function getCheckResult(tree){
	var checkResult = new Array(0);
	var topNodes = tree.getRoot().children;
	getResult(topNodes, checkResult);
	
	return checkResult;
}

/**
 * 将resultList的值转化为checkTree
 * @param resultList 域列表
 * @param treeDiv 显示树的div区域
 * @param addCheckBoxEvent 增加checkbox点击事件
 * @param addLabelEvent 增加label点击事件
  **/
function buildCheckTree(treeDiv, domainList, addCheckBoxEvent, addLabelEvent){
		var aNode;
		tree = new YAHOO.widget.TreeView(treeDiv);
			for(j=0;j<domainList.length;j++){
				var domain = domainList[j];
				if(j==0){
					eval("var node" + domain.domainId + "_" + domain.levelId + "= new YAHOO.widget.TaskNode(domain.name,tree.getRoot(), false, false, domain.domainId);");
				}else{
					var levelId = parseInt(domain.levelId) - 1;
					try{
					eval("var node" + domain.domainId + "_" + domain.levelId + "= new YAHOO.widget.TaskNode(domain.name,eval(node" + domain.domDomainId  + "_" + levelId + "), false, false, domain.domainId);");
					}catch(e){
					eval("var node" + domain.domainId + "_" + domain.levelId + "= new YAHOO.widget.TaskNode(domain.name,tree.getRoot(), false, false, domain.domainId);");
					}
				}
			}
		
		if(addCheckBoxEvent){
		  tree.subscribe("checkClick", onCheckClick);
		}
		if(addLabelEvent){
		  tree.subscribe("labelClick", onLabelClick);
		}
		tree.draw();
}

/**
 * 将resultList的值转化为Tree
 * @param treeDiv 显示树的div区域
 * @param resultList 域列表
 * @param addLabelEvent 增加label点击事件
  **/
function buildTree(treeDiv, domainList, addLabelEvent){
		var aNode;
		tree = new YAHOO.widget.TreeView(treeDiv);
		//for(i=0;i<resultList.length;i++){
			//var domainList = resultList[i];
			for(j=0;j<domainList.length;j++){
				var domain = domainList[j];
				if(j==0){
					eval("var node" + domain.domainId + "_" + domain.levelId + "= new YAHOO.widget.TextNode(domain.name,tree.getRoot(), false, domain.domainId);");
				}else{
					var levelId = parseInt(domain.levelId) - 1;
					try{
					eval("var node" + domain.domainId + "_" + domain.levelId + "= new YAHOO.widget.TextNode(domain.name,eval(node" + domain.domDomainId  + "_" + levelId + "), false, domain.domainId);");
					}catch(e){
					eval("var node" + domain.domainId + "_" + domain.levelId + "= new YAHOO.widget.TextNode(domain.name,tree.getRoot(), false, domain.domainId);");
					}
				}
			}
		//}
		if(addLabelEvent){
		  tree.subscribe("labelClick", onLabelClick);
		}
		
		tree.draw();
}

/**
 * 域的树生成
 **/
var buildSuccess = function(o){
	var result = o.responseText;
	if(result != undefined){
		var resultList = eval(result);
		if(o.argument.treeName == "default"){
		  buildTree(o.argument.treeDiv, resultList, o.argument.isLabelEvent);
		}else{
		  buildCheckTree(o.argument.treeDiv, resultList, o.argument.isCheckEvent, o.argument.isLabelEvent);
		}
		
		var checkResult = o.argument.checkResult;
		if(checkResult != null && checkResult != ""){
		  checkByDomains(checkResult);
		}
	}
}

/**
 * 将已经选入的值钩选
 **/
function checkByDomains(checkResult){
    var domains = new Array();
    if(checkResult.indexOf(";") > 0){
        domains = checkResult.split(";");
    } else {
        domains.push(checkResult);
    }
    for(var i = 0; i < domains.length; i++){
        var domainId = domains[i];
        tree.getNodeByIndex(domainId).check();
    }
}

/**
 * 默认的错误返回处理
 **/
var handleFailure = function(o){
	alert("访问发生错误");
}

/**
 * 默认的复选框选中动作
 **/
function onCheckClick(node) {
	alert(node);
}

/**
 * 递归取得checktree选择结果
 * @nodes 需要遍历的根节点
 * @checkResult 结果集
 **/
function getResult(nodes,checkResult){
  for(var i=0; i<nodes.length; ++i) {
		var node = nodes[i];
		var checkState = node.checkState;
		if(checkState == 0){  //结果为零表示没有被选，直接跳出遍历下一节点
			continue;
		}	else if (checkState == 2){   //表示以下所有节点都被选中
			checkResult.push(node.index);
		}	else if (checkState == 1){   //表示以下节点部分选中
			var childNodes = node.children;
			if(childNodes && childNodes.length>0){
			 getResult(childNodes, checkResult);
			}
		}
	}
}

/**
 * 递归取得checktree选择结果
 * 返回所有结果的叶子节点集合
 * @nodes 需要遍历的根节点
 * @checkResult 结果集
 **/
function getLeafResult(nodes,checkResult){
  for(var i=0; i<nodes.length; ++i) {
		var node = nodes[i];
		var checkState = node.checkState;
		if(checkState == 0){  //结果为零表示没有被选，直接跳出遍历下一节点
			continue;
		}	else if (checkState == 2){   //表示以下所有节点都被选中
			var childNodes = node.children;
			if(childNodes!=null && childNodes.length>0){ //仍有子节点就继续遍历
			 getLeafResult(childNodes, checkResult);
			} else { //将叶子节点值插入结果集
			 checkResult.push(node.index);
			}
		}	else if (checkState == 1){   //表示以下节点部分选中
			var childNodes = node.children;
			if(childNodes && childNodes.length>0){
			 getLeafResult(childNodes, checkResult);
			}
		}
	}
}

/**
 * 默认的Label点击动作
 **/
function onCheckClick(node) {
	alert(node);
}

/**
 * checkTree的全选方法
 **/
function checkAll(tree) {
  var topNodes = tree.getRoot().children;
  for(var i=0; i<topNodes.length; ++i) {
    topNodes[i].check();
     }
}

/**
 * checkTree的取消全选方法
 **/
function uncheckAll() {
  var topNodes = tree.getRoot().children;
  for(var i=0; i<topNodes.length; ++i) {
    topNodes[i].uncheck();
  }
}
