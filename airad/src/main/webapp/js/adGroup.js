/**
 * 发布和暂停
 * @param {Object} str id编号
 * @param {Object} type 类型
 */
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
    document.myfrm.action = "/adGroup.do?action=selectAll";
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
//enter事件
function adGroupStatistic(e)
 {
  var currKey=0,e=e||event;
  if(e.keyCode==13)
  document.sform.submit();
 }
