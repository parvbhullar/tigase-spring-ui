function showEndTime(endTime, endHour, endMin) {
    /**
     var endTimeId=document.getElementById(endTime);
     var endHour=document.getElementById(endHour);
     var endMin=document.getElementById(endMin);
     */
    var selval = document.getElementById("timeFlag");
    if (selval.value == "1") {
       /**
        endTimeId.style.background = "";
        endTimeId.disabled = "";
        endHour.disabled = "";
        endMin.disabled = "";
        */
        $("#showEndTimeId").show();
        $("#showEndTimeIdPreview").show();
         $("#endHour").show();
        $("#endMin").show();
    }
    else {
       /**
        endTimeId.disabled = "true";
        endHour.disabled = "true";
        endMin.disabled = "true";
        */
        $("#showEndTimeId").hide();
        $("#showEndTimeIdPreview").hide();
        $("#endHour").hide();
        $("#endMin").hide();
    }
    
}


/**
 * ajax加载树
 */
/**
 var simpleTreeCollection;
 $(document).ready(function() {
 simpleTreeCollection = $('.simpleTree').simpleTree({
 url: '/adGroup.do?action=selectAreaTree&root=source',
 dataType: 'json',
 autoclose: true,
 animate: true,
 check: true,
 });
 });
 */
function addSelect(s1, s2, endTime, endHour, endMin) {
    var endTimeId = document.getElementById(endTime);
    if (endTimeId.value != "") {
        var s1 = document.getElementById(s1);
        s1.selected = "true";
        endTimeId.style.background = "";
        var endHour = document.getElementById(endHour);
        var endMin = document.getElementById(endMin);
        endTimeId.disabled = "";
        endHour.disabled = "";
        endMin.disabled = "";
    }
    else {
        var s2 = document.getElementById(s2);
        if(!!s2){
        s2.selected = "true";
        }
    }
}


function validateTimeValue() {

    var endTimeId = document.getElementById('endTime').value.split('-');
    var endHour = document.getElementById('endHour').value;
    var endMin = document.getElementById('endMin').value;
    var endTime = new Date(endTimeId[0], endTimeId[1], endTimeId[2], endHour, endMin);
    
    var startTimeId = document.getElementById('startTime').value.split('-');
    var startHour = document.getElementById('startHour').value;
    var startMin = document.getElementById('startMin').value;
    var startTime = new Date(startTimeId[0], startTimeId[1], startTimeId[2], startHour, startMin);
    
    if (startTime.getTime() >= endTime.getTime()) {
        document.getElementById("timeDiv").innerHTML = "开始时间大于结束时间！"
    }
    else {
        document.getElementById("timeDiv").innerHTML = "";
    }
    
}

function copy(campaignName, campaignId) {
    document.getElementById("copyId").innerHTML = "<div id='copyId' style='color: green'>活动名称：" + campaignName + "</div>";
    document.getElementById("cover").style.display = "block";
}

function hidden() {
    document.getElementById("cover").style.display = "none";
}

/**
 * 排序-降序
 */
function campaignDesc() {
   $("#asce").val("");
   document.statisticform.submit(); 
}

/**
 * 排序-升序
 */
function campaignAsce() {
   $("#asce").val("true");
   document.statisticform.submit(); 
}

function onPreview(obj, id) {
    var text =obj.value;
    jQuery("#" + id).html(text);
    var h = jQuery("#startHour").val();
    var s = jQuery("#startMin").val();
    jQuery("#campaign_hs").html(" " + h + ":" + s);
}

function onPreview() {
    var campaignName = document.getElementById("campaign.campaignName").value;
    var startTime = document.getElementById("startTime").value;
    var startHour = document.getElementById("startHour").value;
    var startMin = document.getElementById("startMin").value;
    var buggetDay = document.getElementById("buggetDay").value;
    if (!campaignName == "") {
        if (campaignName.length > 10) {
            campaignName = campaignName.substr(0, 10) + "...";
        }
    }
    
    jQuery("#campaign_name").html(campaignName);
    var h = jQuery("#startHour").val();
    var s = jQuery("#startMin").val();
    jQuery("#campaign_hs").html(startTime + " " + h + ":" + s);
    jQuery("#campaign_day").html("<sup>&yen;</sup>"+buggetDay);
}

/**
 * 验证是否为数字并且大于20
 * @param {Object} obj
 */
function checkNum(event) {
    var e = window.event || event;
    var target = e.srcElement || e.target;
    var k = e.keyCode;
    if (isFunKey(k)) {
        return true;
    }
    var c = getChar(k);
    if (target.value.length == '' && (c == '-' || c == '+')) {
        return true;
    }
    if (isNaN(target.value + getChar(k))) {
        return false;
    }
    return true;
}

function isFunKey(code) {
    //  8 --> Backspace   
    // 35 --> End   
    // 36 --> Home   
    // 37 --> Left Arrow   
    // 39 --> Right Arrow   
    // 46 --> Delete   
    // 112~123 --> F1~F12   
    var funKeys = [8, 35, 36, 37, 39, 46];
    for (var i = 112; i <= 123; i++) {
        funKeys.push(i);
    }
    for (var i = 0; i < funKeys.length; i++) {
        if (funKeys[i] == code) {
            return true;
        }
    }
    return false;
}

function getChar(k) {
    if (k >= 48 && k <= 57) {
        return String.fromCharCode(k);
    }
    if (k >= 96 && k <= 105) {
        return String.fromCharCode(k - 48);
    }
    if (k == 110 || k == 190) {
        return ".";
    }
    if (k == 109 || k == 189) {
        return "-";
    }
    if (k == 107 || k == 187) {
        return "+";
    }
    return "#";
}

/**提交*/
function copyNames() {
    var campaignId = cid;
    var campaignName = cname;
    var iname = document.getElementById('campaignName_copy').value;
    if (iname.length > 200) {
        return "活动名称过长，请修改后再输入";
    }
    if (iname.length < 1) {
        return "活动名称不能为空";
    }
    var a = document.getElementById('campaignIdTemp').value;
    document.getElementById('campaignIdTemp').value = campaignId;
    document.getElementById('campaignNameTemp').value = campaignName;
    document.getElementById('campaignNameCopyTemp').value = iname;
    closepop();
    document.statisticform.action = "/campaign.do?action=doCopy";
    document.statisticform.submit();
}

/**删除Wendong.Gao*/
function doDel(campaignId) {
    if (confirm('您确定要删除此活动?')) {
        document.getElementById('campaignIdTemp').value = campaignId;
        document.statisticform.action = "/campaign.do?action=doDel";
        document.statisticform.submit();
    }
    
}

/**暂停Wendong.Gao*/
function doSuspend(campaignId, suspendType,adCount) {
  if(adCount==0){
    alert("对不起,暂无广告!");
  }else{
    if(suspendType==0){
    if (confirm('您确定要暂停此活动下所有广告?')) {
    document.getElementById('campaignIdTemp').value = campaignId;
    document.getElementById('suspendTypeTemp').value = suspendType;
    document.statisticform.action = "/campaign.do?action=doSuspend";
    document.statisticform.submit();
   }
  }else{
    if (confirm('您确定要运行此活动下所有广告?')) {
    document.getElementById('campaignIdTemp').value = campaignId;
    document.getElementById('suspendTypeTemp').value = suspendType;
    document.statisticform.action = "/campaign.do?action=doSuspend";
    document.statisticform.submit();
  }
  }
  }
}

function showDigest() {
    var campaignName = document.getElementById("campaign.campaignName").value;
    if (!campaignName == "") {
        if (campaignName.length > 10) {
            campaignName = campaignName.substr(0, 10) + "...";
        }
    }
    var startTime = document.getElementById("startTime").value;
    var startHour = document.getElementById("startHour").value;
    var startMin = document.getElementById("startMin").value;
    var buggetDay = document.getElementById("buggetDay").value;
    jQuery("#campaign_name").html(campaignName);
    var h = jQuery("#startHour").val();
    var s = jQuery("#startMin").val();
    jQuery("#campaign_hs").html(startTime + " " + h + ":" + s);
    jQuery("#campaign_day").html("<sup>&yen;</sup>"+buggetDay);
    var endTime = document.getElementById("endTime").value;
    var endHour = document.getElementById("endHour").value;
    var endMin = document.getElementById("endMin").value;
    jQuery("#campaign_endTime").html(endTime + " " + endHour + ":" + endMin);
}

/**
 * 根据时间段查找收益提交方法
 */
function campaignTimeSlotSubmit() {
    $("#timeSlotFlag").val("true");
    document.statisticform.submit();
}

//enter事件
function searchSubmit(e) {
    var currKey = 0, e = e || event;
    if (e.keyCode == 13) 
        document.statisticform.submit();
}

/**
 * 
 * @param {Object} campaignId
 * @param {Object} type 0：查询活动下广告的具体信息,1:查询广告组下广告的具体信息
 */
function adDetial(id,type,imageId) {
    var url ="/campaign.do?action=adDetial";
    if (type == "0") {
        //活动下广告
        url = url+"&type=0";
    }
    else if (type == "1") {
       //广告组下广告
        url = url+"&type=1";
    }
    else {
        alert("error");
        return;
    }
    $.post(url, {
        id: id,
        r: Math.random()
      },function(date){
        $("#"+imageId).attr("title",date);
      }
    );
}
