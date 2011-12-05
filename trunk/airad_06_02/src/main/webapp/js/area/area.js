var modules = 	[
				       { include: true, incfile:'jquery.blockUI.js',canloaded:true}
				];

//加载模块
function loadFiile(modules)
{
	var pathtojsfiles = "js/area/"; // need to be ajusted
	for(var i=0;i<modules.length; i++)
    {
		console.info("modules.length="+modules.length+";"+(modules[i].include == true&&modules[i].canloaded));
        if(modules[i].include == true&&modules[i].canloaded) {
        	filename = pathtojsfiles+modules[i].incfile;
       		if(jQuery.browser.safari) {
       			jQuery.ajax({url:filename,dataType:'script', async:false, cache: true});
       		} else {
       			console.info("IncludeJavaScript");
       			IncludeJavaScript(filename);
       			modules[i].canloaded=false;
       		}
        }
    }
}

function IncludeJavaScript(jsFile)
{
        var oHead = document.getElementsByTagName('head')[0];
        var oScript = document.createElement('script');
        oScript.type = 'text/javascript';
        oScript.charset = 'utf-8';
        oScript.src = jsFile;
        oHead.appendChild(oScript);
};

function loadCss(){
    var cssTag = document.getElementById('loadCss');
    var head = document.getElementsByTagName('head').item(0);
    if(cssTag) head.removeChild(cssTag);
    css = document.createElement('link');
//    css.href = "../css/mi_"+file+".css";
    css.href = "js/area/css3buttons/stylesheets/css3buttons.css";
    css.rel = 'stylesheet';
    css.type = 'text/css';
    css.id = 'loadCss';
    head.appendChild(css);
}

$.fn.extend({
    area:function(){
        $(this).click(function(){
    		$("#pslayer").show();
    		$.blockUI({
                message: $('#pslayer'),
                title:"Please",
                css: {
                    top:  (window.screen.availHeight - 400) /2 + 'px',
                    left: (document.documentElement.clientWidth - 400) /2 + 'px',
                    width: '450px'
                }
            });
         });
     }
});


var g_arrChked=[];
var g_arrChkedText=[];
var g_upLevelAreaArr=[];
var g_selected_count=0;

/**
 * 记录已选择地区的总数
 * @return
 */
function selectedCount(b){
	if(b){
		g_selected_count++;
	}else{
		g_selected_count--;
	}
}

/**
 * 删除已选中区域的li标签
 * @return
 */
function removeselectingli(t,proId,level){
	$(t).parent().remove();
	var j=$("#pcbx"+proId).attr("checked",false);
	if(level==1){
		console.info("level="+level);
		$("#pcbx"+proId).parent().parent().removeClass("layicon");
		$("#pcbx"+proId).parent().parent().removeClass("layon");
	}
	console.info("proId="+proId);
}

//获取全部选中的数组
function get_g_arrChked(){
	g_arrChked=[];
	g_arrChkedText=[];
	$("#selecting li").each(function (index){
		var len=$(this).attr("id").length;
		g_arrChked[index]=$(this).attr("id").substring(2,len);
		g_arrChkedText[index]=$(this).find("a").text();
	})
}

function get_g_upLevelAreaArr(t,level){
	if(level==1){
		g_upLevelAreaArr=[];
		g_upLevelAreaArr[0]=$(t).children("a").find("input").val();
	}else{
		g_upLevelAreaArr[1]=$(t).children("a").find("input").val();
	}
}

//checkbox选中事件处理
function changeBgColor(t,level){
	var isChecked=$(t).attr("checked");
	var proId=$(t).val().split("@")[0];
	var text=$(t).val().split("@")[1];
	var new_add=true;
	get_g_arrChked();
	console.info("level="+level+";checked="+$(t).attr("checked")+";isChecked="+isChecked);
	for(var i=0;i<g_arrChked.length;i++){
		if(g_arrChked[i]==proId){
			new_add=false;
		}
	}
	console.info("new_add="+new_add);
	if(new_add){
		addOrRemoveSelecting(t,true,level,proId,text);
	}else{
		if(isChecked!=undefined){
			console.info("变色--取消");
			$(t).parent().parent().removeClass("layon").addClass("nonelay");
		}else{
			console.info("变色--已选取消去色");
			$(t).parent().parent().removeClass("layon").addClass("nonelay");
			if($("#selecting #li"+proId)==null){
				console.info("变色--已选 为空");
				$("#selecting").append("<li id='li"+proId+"'><a href='javascript:void(0);' onclick='javascript:removeselectingli(this,"+proId+","+level+")'>"+text+"</a></li>");
			}
			$("#selecting #li"+proId).remove();
			$("#selecting #"+proId).parent();
		}
		addOrRemoveSelecting(t,false,level,proId,text);
	}
	$("#noSelectedLoc").hide();
	$("#divSelecting").show();
}

//选中或反选
function addOrRemoveSelecting(t,b,level,proId,text){
	selectedCount(b);
	console.info("b="+b);
	console.info("g_selected_count="+g_selected_count);
	if(g_selected_count>6){
		alert("最多只能选择6个地区");
		$(t).attr("checked",false);
		g_selected_count--;
		console.info("g_selected_count="+g_selected_count);
		return false;
	}
	if(b){
		console.info("选中");
		$(t).parent().parent().removeClass("nonelay").addClass("layon");
		var selectingValue=""
		if(level==1){
			checkOrUncheckAllSubArea(true,2,proId);
			selectingValue=text;
			removeSelectedSubArea(text);
		}else{
			if(level==2){
				checkOrUncheckAllSubArea(true,3,proId);
				selectingValue=g_upLevelAreaArr[0].split("@")[1]+"-"+text;
			}else{//区
				selectingValue=g_upLevelAreaArr[0].split("@")[1]+"-"+g_upLevelAreaArr[1].split("@")[1]+"-"+text;
			}
			removeSelectedSubArea(text);
		}
		$("#selecting").append("<li id='li"+proId+"'><a href='javascript:void(0);' onclick='javascript:removeselectingli(this,"+proId+","+level+")'>"+selectingValue+"</a></li>");
	}else{
		console.info("反选");
		$("#selecting #li"+proId).remove();
		if(level==1){
			checkOrUncheckAllSubArea(false,2,proId);
		}else{
			if(level==2){
				checkOrUncheckAllSubArea(false,3,proId);
			}
		}

	}
}

/**
 * 更新上级选中状态
 * @return
 */
function checkOrUncheckUpLevlArea(level){

}

//全选或反选下级区域
function checkOrUncheckAllSubArea(b,level,proId){
	if(b){
		if(2==level){
			$("#subItems li").each(function(){
				if((2==proId)||(25==proId)||(27==proId)||(32==proId)){
					$(this).addClass("layon");
				}else{
					$(this).addClass("layicon");
				}
				$(this).find("input[type=checkbox]").attr("checked",true);
				$(this).find("input[type=checkbox]").prop('disabled', true);
			});
		}else{//level 3
			$("#thirdItems li").each(function(){
				$(this).addClass("layon");
				$(this).find("input[type=checkbox]").attr("checked",true);
				$(this).find("input[type=checkbox]").prop('disabled', true);
			});
		}
	}else{//反选开始
		if(2==level){
			$("#subItems li").each(function(){
				if((2==proId)||(25==proId)||(27==proId)||(32==proId)){
					$(this).removeClass("layon");
				}else{
					$(this).removeClass("layicon");
				}
				$(this).find("input[type=checkbox]").attr("checked",false);
				$(this).find("input[type=checkbox]").prop('disabled', false);
			});
		}else{//level 3
			$("#thirdItems li").each(function(){
				$(this).removeClass("layon");
				$(this).find("input[type=checkbox]").attr("checked",false);
				$(this).find("input[type=checkbox]").prop('disabled', false);
			});
		}
	}
}

//删除已选择的子区域
function removeSelectedSubArea(text){
	get_g_arrChked();
	for(var i=0;i<g_arrChkedText.length;i++){
		var index=g_arrChkedText[i].indexOf(text);
		console.info("index="+index);
		if(index>=0){
			$("#li"+g_arrChked[i]).remove();
		}
	}
	for(var i=0;i<g_arrChkedText.length;i++){
		var index=g_arrChkedText[i].indexOf(text);
		console.info("index="+index);
		if(index>=0){
			g_arrChked.splice(i,1);
			g_arrChkedText.splice(i,1);
		}
	}
}


(function ($, plugin) {
    var data = {}, id = 1, etid = plugin + 'ETID';

    // 延时构造器
    $.fn[plugin] = function (speed, group) {
        id ++;
        group = group || this.data(etid) || id;
        speed = speed || 150;

        // 缓存分组名称到元素
        if (group === id) this.data(etid, group);

        // 暂存官方的hover方法
        this._hover = this.hover;

        // 伪装一个hover函数，并截获两个回调函数交给真正的hover函数处理
        this.hover = function (over, out) {
            over = over || $.noop;
            out = out || $.noop;
            this._hover(function (event) {
                var elem = this;
                clearTimeout(data[group]);
                data[group] = setTimeout(function () {
                    over.call(elem, event);
                }, speed);
            }, function (event) {
                var elem = this;
                clearTimeout(data[group]);
                data[group] = setTimeout(function () {
                    out.call(elem, event);
                }, speed);
            });
            return this;
        };
        return this;
    };

    // 冻结选定元素的延时器
    $.fn[plugin + 'Pause'] = function () {
        clearTimeout(this.data(etid));
        return this;
    };

    // 静态方法
    $[plugin] = {
        // 获取一个唯一分组名称
        get: function () {
            return id ++;
        },
        // 冻结指定分组的延时器
        pause: function (group) {
            clearTimeout(data[group]);
        }
    };

})(jQuery, 'mouseDelay');

$(document).ready(function() {
	loadCss();
	loadFiile(modules);
	$("#areaId").area();
	$("#imgClose").click(function(){
		$.unblockUI({
            onUnblock: function(){$("#pslayer").hide();}
        });
	})

	var group=$.mouseDelay.get();
	var globalStatus=false;
	var top,left;
	var timer;
		$("#allItems li").mouseover(function(){
			var offset=	$(this).offset();
			get_g_upLevelAreaArr(this,1);
			var curLiChecked=$(this).find("input[type=checkbox]").attr("checked");
			$("#subItems").css("top",(offset.top)).css("left",(offset.left+140-20)).css("zIndex",(1099));
			var proId=$(this).children("a").find("input").val().split("@")[0];
			$.ajax({
			  url: 'adGroup.do?action=cityTree&proId='+proId,
			  dataType: 'json',
		      contentType:'application/json;charset=UTF-8',
			  success: function(data) {
				var arrlocal=data;
				get_g_arrChked();
				$("#subItems ol").empty();
				//直辖市区
				if((2==proId)||(25==proId)||(27==proId)||(32==proId)){
					$("#subItems").removeClass("lm");
					arrlocal=data[0].children
				}else{
					$("#subItems").addClass("lm");
				}
				for(var i=0;i<arrlocal.length;i++){
					$("#subItems ol").append("<li ><a href='javascript:void(0);'><input id='pcbx"+arrlocal[i].id+"' type='checkbox' onclick='changeBgColor(this,2)' value='"+arrlocal[i].id+"@"+arrlocal[i].text+"' />"+arrlocal[i].text+"</a></li>");
					if(g_arrChked.length!=0){
						if(curLiChecked){//如果省选中则将 全省所有的市选中
							checkOrUncheckAllSubArea(true,2,proId);
						}else{
							for(var j=0;j<g_arrChked.length;j++){
								if(g_arrChked[j]==(arrlocal[i].id)){
									//如果是选中的则把新加入的(最后一个)input 上色选中
									$("#subItems ol li:last input").parent().css("background","#BACBDD");
									$("#subItems ol li:last input").attr("checked","true");
								}
							}
						}
					}
				}
				if(!((2==proId)||(25==proId)||(27==proId)||(32==proId))){//直辖市外
					$("#subItems li").mouseDelay(false,group).hover(function(e){
						if(!globalStatus){
							var offset2=	$(this).offset();
							get_g_upLevelAreaArr(this,2);
							var curLiChecked=$(this).find("input[type=checkbox]").attr("checked");
							var p2_top=	offset2.top;
							var p2_left=offset2.left+140-20;
							$("#thirdItems").css("top",(p2_top)).css("left",(p2_left)).css("zIndex",1299);
							var proId2=$(this).children("a").find("input").val().split("@")[0];
							$.ajax({
							  url: 'adGroup.do?action=cityTree&proId='+proId2,
							  dataType: 'json',
					          contentType:'application/json;charset=UTF-8',
							  success: function(data) {
								var arrlocal=data;
								get_g_arrChked();
								$("#thirdItems ol").empty();
								for(var i=0;i<arrlocal.length;i++){
									$("#thirdItems ol").append("<li class='nonelay'><a href='javascript:void(0);'><input id='pcbx"+arrlocal[i].id+"' type='checkbox' onclick='changeBgColor(this,3)' value='"+arrlocal[i].id+"@"+arrlocal[i].text+"' />"+arrlocal[i].text+"</a></li>");
									if(g_arrChked.length!=0){
										if(curLiChecked){//如果已选中市则将全部区选中
											checkOrUncheckAllSubArea(true,3,proId2);
										}else{
											for(var j=0;j<g_arrChked.length;j++){
												if(g_arrChked[j]==(arrlocal[i].id)){
													//如果是选中的则把新加入的(最后一个)input选中
													$("#thirdItems ol li:last").removeClass("nonelay").addClass("layon");
													$("#thirdItems ol li:last input").attr("checked","true");
												}
											}
										}
									}
								}
							  }
							});
						      $("#thirdItems").show();
						}
					});
				}
			  }
			});
		      $("#subItems").show();
		      $("#thirdItems").hide();
		});

	$("#sech_layb_id").mouseDelay(false,(group)).hover(function(){
		globalStatus=false;
	},function(){
		globalStatus=true;
		$("#subItems").hide();
		$("#thirdItems").hide();
		globalStatus=false;
	});

	$("#subItems").mouseDelay(false,(group)).hover(function(){
		globalStatus=false;
	},function(){
		globalStatus=true;
		$("#subItems").hide();
		$("#thirdItems").hide();
		globalStatus=false;
	});
	$("#thirdItems").mouseDelay(false,(group)).hover(function(){
		globalStatus=false;
	},function(){
		globalStatus=true;
		$("#subItems").hide();
		$("#thirdItems").hide();
		globalStatus=false;
	});
})