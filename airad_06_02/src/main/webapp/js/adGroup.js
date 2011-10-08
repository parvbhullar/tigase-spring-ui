/**
 * 发布和暂停
 * @param {Object} str id编号
 * @param {Object} type 类型
 */
/*
function stopandsendAdGroup(adGroupId, type,adCount) {
  if(adCount==0){
    alert("对不起,暂无广告!");
  }else{
     var url = "";
    if (type == "0") {
         if (confirm('您确定要暂停此广告组下所有广告?')) {
           url = "/adGroup.do?action=suspend&adGroupId=" + adGroupId;
            document.sform.action=url;
            document.sform.submit();
         }
    }
    else if (type == "1") {
       if (confirm('您确定要运行此广告组下所有广告?')) {
         url = "/adGroup.do?action=issue&adGroupId=" + adGroupId;
         document.sform.action=url;
         document.sform.submit();
       }
    }
  }
}
*/


/**复制提交*/
function copyAdGroup() {
    var adGroupId = cid;
    var adGroupName = cname;
    var iname = document.getElementById('adGroupName_copy').value;
    if (iname.length > 200) {
        return "广告组名称过长，请修改后再输入";
    }
    if (iname.length < 1) {
        return "广告组名称不能为空";
    }
    document.getElementById('adGroupIdTemp').value = adGroupId;
    document.getElementById('adGroupNameTemp').value = adGroupName;
    document.getElementById('adGroupNameCopyTemp').value = iname;
    document.myfrm.action = "/adGroup.do?action=doCopy";
    document.myfrm.submit();
}

function returnPage() {
    document.myfrm.action = "/adGroup.do?action=selectAllByMemberId";
    document.myfrm.submit();
}

function showAdGroupList(url) {
    document.myfrm.action = url;
    document.myfrm.submit();
}


/**
 * 显示隐藏div
 * @param {Object} names
 */
function divDisplay(names) {
    var obj = document.getElementById(names);
    if (obj.style.display == "none") {
        obj.style.display = "block";
        $("#"+names).parent().parent().addClass("over");
    }
    else {
       $("#"+names).parent().parent().removeClass("over");
        obj.style.display = "none";
    }
}

/**
 * 动态改变边上的值
 */
function valueAlert(obj, names) {
    var text = obj.value;
    if (!text == "") {
        if (text.length > 10) {
            text = text.substr(0, 10) + "...";
        }
    }
    jQuery("#" + names).html(text);

}

/**
 *
 * @param {Object} obj
 * @param {Object} names
 */
function valueAlert1(obj, names) {
    if (obj.checked) {
        jQuery("#" + names).text("全国");
        var adLoclObj = document.getElementsByName("coreAdGroup.adLoclInfo");
        for (var i = 0; i < adLoclObj.length; i++) {
            adLoclObj[i].checked = false;
        }
        document.getElementById("adLoclInfoShowSp").style.display = "none";
        document.getElementById("proId").style.display = "none";
    }
}

/**
 *
 * @param {Object} obj
 */
function showAdLoclInfoSp(obj, flg) {
    var adLoclObj = document.getElementsByName("coreAdGroup.adLoclInfo");
    if (obj.checked) {
        document.getElementById("adLoclInfoShowSp").style.display = "block";
        if (flg) {
            for (var i = 0; i < adLoclObj.length; i++) {
                adLoclObj[i].checked = true;
            }
        }
    }
    var str = "";
    for (var i = 0; i < adLoclObj.length; i++) {
        if (adLoclObj[i].checked) {
            if (str == "") {
                str = adLoclObj[i].title;
            }
            else {
                str = str + "," + adLoclObj[i].title;
            }
        }
    }
    jQuery("#geographyDiv").text(str);
    document.getElementById("proId").style.display = "none";
}

/**
 * 显示隐藏摘要
 */
function showDocket(type) {
    if (type == "1") {
        $("#con01").show();
        $("#btn02").show();
        $("#btn01").hide();
    }
    else {
        $("#con01").hide();
        $("#btn01").show();
        $("#btn02").hide();
    }
}

function showAdGroupTree() {
   var adTagSp=$("#adTagSp").val();
   var strs=adTagSp.split(",");
   $("#tagSpDiv").find("input[type=checkbox]").each(function() {
                    var ckval = $(this).val();
                    if (strs.length > 0) {
                        for (var i = 0; i < strs.length; i++) {
                            if (ckval == strs[i]) {
                                $(this).attr("checked", "true");
                            }
                        }
                    }
                });
   var editFlagCheck=$("#editFlagCheck").val();
   var addFlag=$("#addFlag").val();
   if(addFlag==""&&editFlagCheck==""){
     $("#tagSpDiv").find("input[type=checkbox]").each(function() {
             $(this).attr("checked", "true");
          });
   }
   showTagSp();
}

function registerAdGroupPageSubmti() {
    var ids = "";
    var si = $("#tree>.root>ul>li").each(function() {
        var id1 = $(this).attr('id');
        if (id1) {
            var res = false;
            var si1 = $(this).children("input").each(function() {
                if ($(this).attr("checked")) {
                    if (ids == "") {
                        ids = $(this).val() + ":";
                    }
                    else {
                        ids = ids + ";" + $(this).val() + ":";
                    }
                    res = true;
                }
            });
            if (res) {
                var si11 = $(this).children("ul").children("li").children("input").each(function() {
                    if ($(this).attr("checked")) {
                        ids = ids + $(this).val() + ",";
                    }
                });
                if (ids.substring(ids.length - 1, ids.length) == ",") {
                    ids = ids.substring(0, ids.length - 1);
                }
            }
        }
    });
    var checkType = $(":radio[name=coreAdGroup.adLoclType]:checked");
    if ($(checkType).val() == "2") {
          var checkIds = "";
          $('#proId').children('div').each(function(){
          var province=  $(this).find("input[type=checkbox]:checked").filter(function(index){
                //查找floer 如果div 里有这个样式就说明他是城市级
                if($(this).parent().attr('class').indexOf('folder-close')>-1 || $(this).parent().attr('class').indexOf('folder-open')>-1){
                //查找这个div里面ul html没有值 就说明他没有区，我们就把省市赋值进去
                if($(this).next().next('ul').html()==''){
                  checkIds+=$(this).parent().parent().parent().attr('id')+","+$(this).val()+";";
                }
                }
                if(!$(this).parent('li .doc').size()==0||!$(this).parent('li .doc-last').size()==0){
                  checkIds+=$(this).next("span").attr("name")+";";
                }else{
                  return;
                }
          });//省市 区节点
         });
        $("#exact").val(checkIds);
    }
   document.myfrm.submit();
}

function setAdGroupPageSubmti() {
	var selectVal=$("#selectAdGroup").val();
	if("x"==selectVal)
	{
		alert("请选择广告组")
		return false;
	}

    var ids = "";
    var si = $("#tree>.root>ul>li").each(function() {
        var id1 = $(this).attr('id');
        if (id1) {
            var res = false;
            var si1 = $(this).children("input").each(function() {
                if ($(this).attr("checked")) {
                    if (ids == "") {
                        ids = $(this).val() + ":";
                    }
                    else {
                        ids = ids + ";" + $(this).val() + ":";
                    }
                    res = true;
                }
            });
            if (res) {
                var si11 = $(this).children("ul").children("li").children("input").each(function() {
                    if ($(this).attr("checked")) {
                        ids = ids + $(this).val() + ",";
                    }
                });
                if (ids.substring(ids.length - 1, ids.length) == ",") {
                    ids = ids.substring(0, ids.length - 1);
                }
            }
        }
    });
    var checkType = $(":radio[name=coreAdGroup.adLoclType]:checked");
    if ($(checkType).val() == "2") {
          var checkIds = "";
          $('#proId').children('div').each(function(){
          var province=  $(this).find("input[type=checkbox]:checked").filter(function(index){
                //查找floer 如果div 里有这个样式就说明他是城市级
                if($(this).parent().attr('class').indexOf('folder-close')>-1 || $(this).parent().attr('class').indexOf('folder-open')>-1){
                //查找这个div里面ul html没有值 就说明他没有区，我们就把省市赋值进去
                if($(this).next().next('ul').html()==''){
                  checkIds+=$(this).parent().parent().parent().attr('id')+","+$(this).val()+";";
                }
                }
                if(!$(this).parent('li .doc').size()==0||!$(this).parent('li .doc-last').size()==0){
                  checkIds+=$(this).next("span").attr("name")+";";
                }else{
                  return;
                }
          });//省市 区节点
         });
        $("#exact").val(checkIds);
    }
   document.myfrm.submit();
}

function showDetial() {
    var proId = document.getElementById("proId");
    var checkType = $(":radio[name=coreAdGroup.adLoclType]:checked");
    if ($(checkType).val() == "2") {
        jQuery("#geographyDiv").html("精确到区");
        proId.style.display = "";
        document.getElementById("adLoclInfoShowSp").style.display = "none";
    }
    if ($(checkType).val() == "1") {
        jQuery("#geographyDiv").html("常用地区");
        proId.style.display = "none";
        document.getElementById("adLoclInfoShowSp").style.display = "";
        var localInfo=$("#adLoclInfo").val();
        var arrLocalInfo=localInfo.split(",");
        $("#adLoclInfoShowSp").find("input[type=checkbox]").each(function(index, domEle){
          for ( var i = 0; i < arrLocalInfo.length; i++) {
                    var ckval = $(domEle).val();
                      if (ckval == arrLocalInfo[i]) {
                        $(domEle).attr("checked", "true");
                      }
        }
        });
    }
    if ($(checkType).val() == "0") {
        jQuery("#geographyDiv").html("全国");
         proId.style.display = "none";
        document.getElementById("adLoclInfoShowSp").style.display = "none";
    }
}

/*
 * checkbox状态改变事件
 */
var hasLoad = [];
function loadData(obj,isTrue) {

     var ids = document.getElementById("exact");
     var idReplace = ids.value.replace(new RegExp(";", 'g'), ",");
     idReplace=idReplace.substring(0, idReplace.length - 1);
     var idarray=[];
      if (!isTrue) {
        if (ids.value != "") {
          idarray = idReplace.split(",");
        }
      }
    var id = $(obj).val();
    for(var h=0;h<hasLoad.length;h++){
      if(id==hasLoad[h]){
        return;
      }
    }
    hasLoad.push(id);//只请求一次
    /*$("#d"+id).css("display","block");
     var have=$("#d"+id).children("ul").html();
     if(have!=""){
     return;
     }*/
        var tree = $("#" + id + "").simpleTree({
            url: '/adGroup.do?action=cityTree&proId=' + id,
            dataType: 'json',
            autoclose: true,
            animate: true,
            check: true,
            afterAjax: function() {
              $("#proId").find("input[type=checkbox]").each(function() {
                var ckval = $(this).val();
                if (idarray.length > 0) {
                  for (var i = 0; i < idarray.length; i++) {
                    if (ckval == idarray[i]) {
                      $(this).attr("checked", "checked");
                    }
                  }
                }
              });
              $("#d"+id).show();
            }
        });
        //tree.TREE.isInit(false);

}

/**
 * 层显示与掩藏
 */
function showData(id) {
    $(":input[name=ck]").each(function() {
        if ($(this).val() == id) {
            loadData($(this),true);
        }
    });
   $("#d" + id).toggle(function(){
    var image=$("#image"+id).attr("src");
    if(image=="images/ico_op.gif"){
      $("#image"+id).attr("src","images/ico_cl.gif");
    }else{
      $("#image"+id).attr("src","images/ico_op.gif");
    }
   });
}

function showTagSp() {
   var ids = 0;
   $("#tagSpDiv").find("input[type=checkbox]").each(function() {
            if ($(this).attr("checked")) {
                  ids++;
                }
          });
   jQuery("#tagSpShowDiv").html(ids+"个平台");
}

/**
 * 根据时间段查找收益提交方法
 */
function adGroupTimeSlotSubmit(){
  $("#timeSlotFlag").val("true");
  document.sform.submit();
}

var g_arrChked=[];
var g_arrChkedText=[];
var g_upLevelAreaArr=[];
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
//		console.info("val="+$(t).children("a").find("input").val());
		g_upLevelAreaArr[0]=$(t).children("a").find("input").val();
//		console.info("g_upLevelAreaArr[0]="+g_upLevelAreaArr[0]);
	}else{
//		console.info("val="+$(t).children("a").find("input").val());
		g_upLevelAreaArr[1]=$(t).children("a").find("input").val();
//		console.info("g_upLevelAreaArr[1]="+g_upLevelAreaArr[1]);
	}
//	console.info("g_upLevelAreaArr.length="+g_upLevelAreaArr.length);
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
				$("#selecting").append("<li id='li"+proId+"'><a href='javascript:void(0);' onclick='javascript:$(this).parent().remove()'>"+text+"</a></li>");
			}
			$("#selecting #li"+proId).remove();
			$("#selecting #"+proId).parent();
		}
		addOrRemoveSelecting(t,false,level,proId,text);
	}

	$("#noSelectedLoc").hide();
	$("#divSelecting").show();
}

//添加或删除 选择中
function addOrRemoveSelecting(t,b,level,proId,text){
	if(b){
		console.info("添加");
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
		$("#selecting").append("<li id='li"+proId+"'><a href='javascript:void(0);' onclick='javascript:$(this).parent().remove()'>"+selectingValue+"</a></li>");
	}else{
		console.info("删除");
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
        speed = speed || 250;

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

function hideSubBox(){
	alert(" 隐藏第subBox");
}

$(document).ready(function() {
	$("#areaId").click(function(){
		$("#pslayer").show();
		$.blockUI({
            message: $('#pslayer'),
            title:"Please",
            css: {
                top:  ($(window).height() - 400) /2 + 'px',
                left: ($(window).width() - 400) /2 + 'px',
                width: '450px'
            }
        });
	})

	$("#imgClose").click(function(){
		$.unblockUI({
            onUnblock: function(){$("#pslayer").hide();}
        });
	})

	var group=$.mouseDelay.get();
	var globalStatus=false;
	$("#allItems li").mouseDelay(false,group).hover(function(e){
		//$("#subItems").hide();
		//$("#thirdItems").hide();
		var position = $(this).position();
		get_g_upLevelAreaArr(this,1);
		var curLiChecked=$(this).find("input[type=checkbox]").attr("checked");
		$("#subItems").css("top",(position.top+($(window).height() - 400) /2)).css("left",(130+position.left+($(window).width() - 400) /2)).css("zIndex",1012)
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
				$("#subItems ol").append("<li ><a href='javascript:void(0);'><input type='checkbox' onclick='changeBgColor(this,2)' value='"+arrlocal[i].id+"@"+arrlocal[i].text+"' />"+arrlocal[i].text+"</a></li>");
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
			if(!((2==proId)||(25==proId)||(27==proId)||(32==proId))){
				$("#subItems li").mouseDelay(false,group).hover(function(e){
//					console.info(" #subItems li globalStatus="+globalStatus);
					if(!globalStatus){
						//$("#subItems").hide();
						var position2 = $(this).position();
						get_g_upLevelAreaArr(this,2);
						var curLiChecked=$(this).find("input[type=checkbox]").attr("checked");
						$("#thirdItems").css("top",(190+position2.top+($(window).height() - 400) /2)).css("left",(280+position2.left+($(window).width() - 400) /2)).css("zIndex",1013);
						var proId=$(this).children("a").find("input").val().split("@")[0];
						$.ajax({
						  url: 'adGroup.do?action=cityTree&proId='+proId,
						  dataType: 'json',
				          contentType:'application/json;charset=UTF-8',
						  success: function(data) {
							var arrlocal=data;
							get_g_arrChked();
							$("#thirdItems ol").empty();
							for(var i=0;i<arrlocal.length;i++){
								$("#thirdItems ol").append("<li class='nonelay'><a href='javascript:void(0);'><input type='checkbox' onclick='changeBgColor(this,3)' value='"+arrlocal[i].id+"@"+arrlocal[i].text+"' />"+arrlocal[i].text+"</a></li>");
								if(g_arrChked.length!=0){
									if(curLiChecked){//如果已选中市则将全部区选中
										checkOrUncheckAllSubArea(true,3,proId);
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
				},null);
			}
		  }
		});
	      $("#subItems").show();
	      $("#thirdItems").hide();
	}
	);

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
		console.info("subItems ="+group);
		$("#subItems").hide();
		$("#thirdItems").hide();
		globalStatus=false;
	});
	$("#thirdItems").mouseDelay(false,(group)).hover(function(){
		globalStatus=false;
	},function(){
		globalStatus=true;
		console.info("thirdItems ="+group);
		$("#subItems").hide();
		$("#thirdItems").hide();
		globalStatus=false;
	});




//	$("#allItems li").mouseover(function(e){
//		$("#subItems").hide();
//		$("#thirdItems").hide();
//		var position = $(this).position();
//		get_g_upLevelAreaArr(this,1);
//		var curLiChecked=$(this).find("input[type=checkbox]").attr("checked");
//		$("#subItems").css("top",(position.top+($(window).height() - 400) /2)).css("left",(130+position.left+($(window).width() - 400) /2)).css("zIndex",1012)
//		var proId=$(this).children("a").find("input").val().split("@")[0];
//		$.ajax({
//		  url: 'adGroup.do?action=cityTree&proId='+proId,
//		  dataType: 'json',
//          contentType:'application/json;charset=UTF-8',
//		  success: function(data) {
//			var arrlocal=data;
//			get_g_arrChked();
//			$("#subItems ol").empty();
//			//直辖市区
//			if((2==proId)||(25==proId)||(27==proId)||(32==proId)){
//				$("#subItems").removeClass("lm");
//				arrlocal=data[0].children
//			}else{
//				$("#subItems").addClass("lm");
//			}
//			for(var i=0;i<arrlocal.length;i++){
//				$("#subItems ol").append("<li ><a href='javascript:void(0);'><input type='checkbox' onclick='changeBgColor(this,2)' value='"+arrlocal[i].id+"@"+arrlocal[i].text+"' />"+arrlocal[i].text+"</a></li>");
//				if(g_arrChked.length!=0){
//					if(curLiChecked){//如果省选中则将 全省所有的市选中
//						checkOrUncheckAllSubArea(true,2,proId);
//					}else{
//						for(var j=0;j<g_arrChked.length;j++){
//							if(g_arrChked[j]==(arrlocal[i].id)){
//								//如果是选中的则把新加入的(最后一个)input 上色选中
//								$("#subItems ol li:last input").parent().css("background","#BACBDD");
//								$("#subItems ol li:last input").attr("checked","true");
//
//							}
//						}
//					}
//				}
//			}
//			if(!((2==proId)||(25==proId)||(27==proId)||(32==proId))){
//				$("#subItems li").mouseover(function(e){
//					//$("#subItems").hide();
//					var position2 = $(this).position();
//					get_g_upLevelAreaArr(this,2);
//					var curLiChecked=$(this).find("input[type=checkbox]").attr("checked");
//					$("#thirdItems").css("top",(190+position2.top+($(window).height() - 400) /2)).css("left",(280+position2.left+($(window).width() - 400) /2)).css("zIndex",1013);
//					var proId=$(this).children("a").find("input").val().split("@")[0];
//					$.ajax({
//					  url: 'adGroup.do?action=cityTree&proId='+proId,
//					  dataType: 'json',
//			          contentType:'application/json;charset=UTF-8',
//					  success: function(data) {
//						var arrlocal=data;
//						get_g_arrChked();
//						$("#thirdItems ol").empty();
//						for(var i=0;i<arrlocal.length;i++){
//							$("#thirdItems ol").append("<li class='nonelay'><a href='javascript:void(0);'><input type='checkbox' onclick='changeBgColor(this,3)' value='"+arrlocal[i].id+"@"+arrlocal[i].text+"' />"+arrlocal[i].text+"</a></li>");
//							if(g_arrChked.length!=0){
//								if(curLiChecked){//如果已选中市则将全部区选中
//									checkOrUncheckAllSubArea(true,3,proId);
//								}else{
//									for(var j=0;j<g_arrChked.length;j++){
//										if(g_arrChked[j]==(arrlocal[i].id)){
//											//如果是选中的则把新加入的(最后一个)input选中
//											$("#thirdItems ol li:last").removeClass("nonelay").addClass("layon");
//											$("#thirdItems ol li:last input").attr("checked","true");
//										}
//									}
//								}
//							}
//						}
//					  }
//					});
//				      $("#thirdItems").show();
//				});
//			}
//		  }
//		});
//	      $("#subItems").show();
//	})

//	$("#subItems li").mouseout(function(e){
//		$("#thirdItems").hide();
//	});

	//选中变灰处理开始

	//选中变灰处理结束
})