
$(function () {
	$("#dd").jstree({ 
		"json_data" : 
			{"data":"友谊社区","attr" : { "id" : "410682_111"},"state" : "open" }
		,
		"plugins" : [ "themes", "json_data","checkbox" ]		              
	});
});

$(function () {
	//南通市下面直属组织组织
	$("#demo4").jstree({ 
		"json_data" : {
			"ajax" : { "url" : "treedataAllSubOrg.action?orgId=320600" }
		},
		"plugins" : [ "themes", "json_data" ]
	});
	
	$("#demo5").jstree({ 
		"themes" : {  

        "theme" : "default",  

        "dots" : true,  

        "icons" : true 
    	}, 
		"json_data" : {
        "ajax" : {
            "url" : "treedataAllSubOrg.action?orgId=410629"
        }
    },
	"plugins" : [ "themes", "json_data","checkbox" ],
	"callback" : {  
        
        //beforedata默认传id给后台 可以自己根据需要添加参数  
    	// node：节点 tree_obj：tree对象  
        beforedata : function(node,tree_obj){    
    			alert("22");
                return {id : $(node).attr("id") || 0,flat : $(node).attr("flat")};//进行以异步传参     
        },  
        //当点击节点时 如果为叶子节点就执行一些操作  
        onselect : function(){  
        	alert("1");
        	/*
            if($(node).attr("flat")=="gongcheng"){  
                window.open("http://www.baidu.com");  
            } 
            */ 
        }  
    }
	});
});

/*
$(".jstree-leaf a").live("click", function(e) {
    alert(e.target);
 })
 */
 
/*取得单选的叶子节点开始*/
function getNode(strTemp,state)
{
		alert(strTemp);
}

 $("#demo4 .jstree-leaf").live("click", function(e) {
	 		getNode("Y:"+this.id+";"+this.alt,1);
	 })
	 
$("#demo4  .jstree-closed a").live("click", function(e) {
		getNode("N:"+$(this).parents('li:first').attr("id")+";"+$(this).parents('li:first').attr("alt"),0);
		
	 })
	 	 
$("#demo4  .jstree-open a").live("click", function(e) {
		getNode("N:"+$(this).parents('li:first').attr("id")+";"+$(this).parents('li:first').attr("alt"),0);
	 })
/*取得单选的叶子节点结束*/

function getAllCheckedLeaf()	 
 {
	 alert("取所有选中的叶子节点");
	 $('.jstree-leaf').filter('.jstree-checked').each(function(){
		 alert(this.id);
	 })
 }
 
 function getAllCheckedNoLeaf()	 
 {
	 alert("取所有选中的非叶子节点");
	 //在树上分四种情况取出
	 $('.jstree-open').filter('.jstree-checked').each(function(){
		 alert(this.id);
	 })
	 
	 $('.jstree-closed').filter('.jstree-checked').each(function(){
		 alert(this.id);
	 })
	 
	 $('.jstree-undetermined').filter('.jstree-open').each(function(){
		 alert(this.id);
	 })
	 
	 $('.jstree-undetermined').filter('.jstree-closed').each(function(){
		 alert(this.id);
	 })
 }