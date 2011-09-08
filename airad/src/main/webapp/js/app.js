function showButton(tt) {
    var daps = document.getElementById(tt);
    $("#"+tt).parent().parent().addClass("over");
    daps.style.display = "block";
}

function closeButton(obj) {
    var daps = document.getElementById(obj);
    $("#"+obj).parent().parent().removeClass("over");
    daps.style.display = "none";
}

/**
 * 排序-降序
 */
function appDesc() {
      $("#asce").val("");
    document.myform.submit(); 
}

/**
 * 排序-升序
 */
function appAsce() {
   $("#asce").val("true");
   document.myform.submit(); 
}

/**
 * 判断是否需要在admob上发布
 */
function checkScope(obj) {
    var pubOutSide = document.getElementById('app.pubOutside');
    var admobCode = document.getElementById('app.admobCode');
    if (obj.value == 1) {
        admobCode.disabled = "";
    }
    else {
        admobCode.value = "";
        admobCode.disabled = "true";
    }
    
}

/**
 * 提交
 */
function addAppSub(obj) {
    var appPlatformType = document.getElementById("app.appPlatformType");
    document.getElementById("appPlatformType").value = appPlatformType.options[appPlatformType.selectedIndex].text;
    
    var appType = document.getElementById("app.appType");
    document.getElementById("appType").value = appType.options[appType.selectedIndex].text;
    //暂时去除年龄段、性别选项
    /**
     var userSex=document.getElementById("app.userSex");
     document.getElementById("userSex").value = userSex.options[userSex.selectedIndex].text;
     var userAgePag=document.getElementById("app.userAgePag");
     document.getElementById("userAgePag").value = userAgePag.options[userAgePag.selectedIndex].text;
     */
    /**
     //发布范围
     var pubOutside = document.getElementById("app.pubOutside");
     document.getElementById("pubOutside").value = pubOutside.options[pubOutside.selectedIndex].text;
     **/
    if (clckimg(obj)) {
        document.addApp.action = "app.do?action=doAdd";
        document.addApp.submit();
    }
}

function defScope() {
    var pubOutSide = document.getElementById('app.pubOutside');
    var admobCode = document.getElementById("app.admobCode");
    if (pubOutSide.value == 0) {
        admobCode.value = "";
        admobCode.disabled = "true";
    }
    else {
        admobCode.disabled = "";
    }
}

/**
 * 发布和暂停
 * @param {Object} str id编号
 * @param {Object} type 类型
 */
function stopandsendApp(str, type) {
    var appid = str;
    var url = "";
    if (type == "0") {
        if (confirm('您确定要停用此应用?')) {
          url = "/app.do?action=suspend";
          $.post(url, {
            appId: appid,
            r: Math.random()
            }, function(data) {
                document.myform.submit();
            });
        }
    }
    else if (type == "1") {
        url = "/app.do?action=issue";
        if (confirm('您确定要运行此应用?')) {
          $.post(url, {
            appId: appid,
            r: Math.random()
            }, function(data) {
              document.myform.submit();
            });
        }
    }
    else {
        alert("error");
        return;
    }
}

function cancle() {
    document.confirmAddApp.action = "app.do?action=add";
    document.confirmAddApp.submit();
}

function subApp(obj) {
    if (clckimg(obj)) {
        document.confirmAddApp.submit();
    }
}

/**
 * 根据时间段查找收益提交方法
 */
function timeSlotSubmit() {
  $("#timeSlotFlag").val("true");
  document.myform.submit();
}
//enter事件
function key(e)
 {
  var currKey=0,e=e||event;
  if(e.keyCode==13)document.myform.submit();
 }
