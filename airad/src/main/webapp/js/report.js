function deleteReport() {


    if ($(":checkbox:checked").size() == 0) {
        alert("请选择需要删除的报告！");
        return;
    }
    else {
        if (window.confirm("是否删除？")) {
            $("#command").attr("action", "report.do?action=doDelete").submit();
        }
    }
}

function editReport(obj, reportId) {
    $(obj).attr("href", "/report.do?action=edit&reportId=" + reportId);
}

function savOrUpd(obj) {
    if (clckimg(obj)) {
        var reportName = $("#reportName").val();
        reportName = jQuery.trim(reportName);
        reportName = reportName.replace(/^\s+|\s+$/g, "");
        if (reportName == "") {
            $("#ts").show();
            $("#tscnt").html("请输入报表名称。");
            obj.ctimes = "0";
            return;
        }
        
        var reportId = document.getElementById("report.reportId").value;
        if (reportId == "") {
            closepop();//关闭层
            $("#rname").val(reportName);
            $("#command").attr("action", "report.do?action=doAdd").submit();
        }
        else {
            var errorInfo = checkAll(obj);
            if (errorInfo > 0) {
                return;
            }
            $("#command").attr("action", "report.do?action=doEdit").submit();
        }
    }
    
}


/* 
 * radio状态改变事件
 */
function loadData(obj) {
    //先清空提示
    $("#tstime").hide();
    $("#ts").hide();
    var id = $(obj).val();
    // var showdel = document.getElementById("report.reportFlag1");
    if (id == "0") {
        $("#tree0").css("display", "block");
        $("#tree1").css("display", "none");
        $("#tree2").css("display", "none");
        $("#chooseType").html("选择活动");
    }
    else if (id == "1") {
        $("#tree1").css("display", "block");
        $("#tree0").css("display", "none");
        $("#tree2").css("display", "none");
        $("#chooseType").html("选择广告组");
    }
    else if (id == "3") {
        $("#tree3").css("display", "block");
    }
    else if (id == "4") {
        $("#tree3").css("display", "block");
    }
    else {
        $("#tree2").css("display", "block");
        $("#tree0").css("display", "none");
        $("#tree1").css("display", "none");
        $("#chooseType").html("选择广告");
    }
    filterDel();
}

/*
 * 是否显示已删除
 */
function filterDel(obj) {
    var cked = "false";
    if (obj) {
        cked = $(obj).attr("checked");
    }
    else {
        $(":checkbox[name=report.reportFlag]").each(function() {
            if ($(this).attr("checked")) {
                cked = "true";
            }
        });
    }
    
    var rds = document.getElementsByName("report.reportType");
    var rd = "";
    for (var i = 0; i < rds.length; i++) {
        if (rds[i].checked) {
            rd = rds[i].value;
        }
    }
    $("#tree" + rd).html("");
    var simpleTreeCollection = $("#tree" + rd).simpleTree({
        url: '/report.do?action=doAdTree&type=' + rd + "&status=" + cked,
        dataType: 'json',
        autoclose: true,
        animate: true,
        check: true,
        afterAjax: function() {
            var ids = document.getElementById("report.reportCon");
            if (ids && ids.value != "") {
                //var idarray = ids.value.split(";");
                var idReplace = ids.value.replace(new RegExp(";", 'g'), ",");
                var idarray = idReplace.split(",");
                $("#tree" + rd).find("input[type=checkbox]").each(function() {
                    var ckval = $(this).val();
                    for (var i = 0; i < idarray.length; i++) {
                        if (ckval == idarray[i]) {
                            $(this).attr("checked", "true");
                        }
                    }
                    //选中全部
                    if (ckval == 0) {
                        $(this).attr("checked", "true");
                    }
                    
                });
            }
        }
    });
}

//查找SWF控件 
function findSWF(movieName) {
    if (navigator.appName.indexOf("Microsoft") != -1) {
        return window[movieName];
    }
    else {
        return document[movieName];
    }
}

function reportShow(obj) {
    var title = "";
    if (obj) {
        $(".tag a").each(function() {
            $(this).removeClass("now");
        });
        $(obj).addClass("now");
        title = $(obj).html();
    }
    var type = $("#type").val();
    var ids = $("#ids").val();
    var dateType = $("#dateType").val();
    var to = $(obj).attr("tabindex");
    var presetValue = $("#presetValue").val();
    var start = $("#reportStartTime").val();
    var end = $("#reportEndTime").val();
    var status = $("#status").val();
    reloadShow(title, type, ids, to, dateType, presetValue, start, end, status);
}

function exports(str) {
    var url = "/report.do?action=docsvReport";
    if (str == "xml") {
        url = "/report.do?action=doxmlReport";
    }
    $("#e_export").attr("action", url);
    document.e_export.submit();
}

function chooseDate(obj) {
    //先清空提示
    $("#tstime").hide();
    $("#ts").hide();
    var dateType = $(obj).val();
    var reportStartTime = $("#reportStartTime");
    var reportEndTime = $("#reportEndTime");
    var presetValue = $("#presetValue");
    if (dateType == 1) {
        //$(presetValue).attr("disabled", "");
        $(presetValue).removeAttr("disabled");
        $(reportStartTime).attr("disabled", "disabled");
        $(reportEndTime).attr("disabled", "disabled");
    }
    else {
        $(presetValue).val("3");
        $(presetValue).attr("disabled", "disabled");
        $(reportStartTime).removeAttr("disabled");
        $(reportEndTime).removeAttr("disabled");
    }
    //时间控件
    $("#reportStartTime").datepick();
    $("#reportEndTime").datepick();
}

function createReportNext() {
    var errorInfo = checkAll();
    if (errorInfo > 0) {
        return;
    }
    $("#command").attr("action", "report.do?action=doReport");
    $("#command").submit();
}

function checkAll(obj) {
    //先清空提示
    $("#tstime").hide();
    $("#ts").hide();
    //时间范围
    var dtype = document.getElementsByName("report.dateType"); //是否选中日期
    var ischecked = false;
    for (var a = 0; a < dtype.length; a++) {
        if (dtype[a].checked && dtype[a].value == "0") {
            ischecked = true;
        }
    }
    if (ischecked) {
        var start = $("#reportStartTime").val();
        var end = $("#reportEndTime").val();
        if (start == "" || end == "") {
            $("#tstime").show();
            $("#tscnttime").html("请选择日期范围。");
            if (obj) {
                obj.ctimes = "0";
            }
            return 1;
        }
        //开始时间不能大于结束时间
        if (start > end) {
            $("#tstime").show();
            $("#tscnttime").html("开始时间不能大于结束时间。");
            if (obj) {
                obj.ctimes = "0";
            }
            return 1;
        }
    }
    
    var ids = getIds();
    var tshtml = "";
    var typeval = $(":radio[name=report.reportType]:checked").val();
    //当前只有一个节点并且是全部的时候。
    if (ids == "" || ids == "0") {
        $("#ts").show();
        //$("#tscnt").html("请选择需要统计的名称。");
        
        
        if (typeval == "0") {
            tshtml = "请选择该报表的活动名称";
        }
        else if (typeval == "1") {
            tshtml = "请选择该报表的广告组名称";
        }
        else if (typeval == "3") {
            tshtml = "请选择该报表的应用名称";
        }
        else {
            tshtml = "请选择该报表的展示元素";
        }
        $("#tscnt").html(tshtml);
        if (obj) {
            obj.ctimes = "0";
        }
        return 1;
    }
    
     //start   展示广告报表必须选中 至少一个广告       彭瀚  2011 5 9
     if(typeval=='2' && ids.indexOf('a')<0){//如果是广告报表,且没有选中的广告
     $("#ts").show();
     tshtml = "请选择该报表的广告元素";
     $("#tscnt").html(tshtml);
     if (obj) {
     obj.ctimes = "0";
     }
     return 1;
     }
    //end
    document.getElementById("report.reportCon").value = ids;
    return 0;
}

function getIds() {
    var ids = "";
    var id = $(":radio:checked").val();
    
    /*
     * 如果只存最底层的id，那么考虑只选择子节点时父节点也需选中。？？？？？
     */
    /*$("#tree" + id).find("input[type=checkbox]:checked").each(function() {
     var val = $(this).val();
     ids += val + ",";
     });
     
     if (ids.length > 1) {
     ids = ids.substring(0, ids.length - 1);
     }
     */
    if (id == "0") {//活动
        $("#tree" + id).find("input[type=checkbox]:checked").each(function() {
            var val = $(this).val();
            ids += val + ",";
        });
    }
    else if (id == "1") {//广告组
        var cid = "";
        $("#tree" + id).find("input[value^='c']:checked").each(function() {
            var val = $(this).val();
            cid += val + ",";
        });
        if (cid.length > 1) {
            cid = cid.substring(0, cid.length - 1);
        }
        var gid = "";
        $("#tree" + id).find("input[value^='g']:checked").each(function() {
            var val = $(this).val();
            gid += val + ",";
        });
        if (cid != "" && gid != "") {
            ids = cid + ";" + gid;
        }
    }
    else if (id == "3") {//应用程序
        $("#tree" + id).find("input[type=checkbox]:checked").each(function() {
            var val = $(this).val();
            ids += val + ",";
        });
    }
    else if (id == "4") {//代理商
    }
    else {//广告
        var cid = "";
        $("#tree" + id).find("input[value^='c']:checked").each(function() {
            var val = $(this).val();
            cid += val + ",";
        });
        if (cid.length > 1) {
            cid = cid.substring(0, cid.length - 1);
        }
        var gid = "";
        $("#tree" + id).find("input[value^='g']:checked").each(function() {
            var val = $(this).val();
            gid += val + ",";
        });
        if (gid.length > 1) {
            gid = gid.substring(0, gid.length - 1);
        }
        var cgid = "";
        if (cid != "" && gid != "") {
            cgid = cid + ";" + gid + ";";
        }
        var aid = "";
        $("#tree" + id).find("input[value^='a']:checked").each(function() {
            var val = $(this).val();
            aid += val + ",";
        });
        ids = cgid + aid;
    }
    if (ids.length > 1) {
        ids = ids.substring(0, ids.length - 1);
    }
    return ids;
}

/**
 * 排序-降序
 */
function reportDesc(currentPage, reportName) {
    var desc = document.getElementById('descId');
    desc.href = "report.do?action=listReport&desc='desc'&currentPage=" + currentPage + "&report.reportName=" + reportName;
}

/**
 * 排序-升序
 */
function reportAsce(currentPage, reportName) {
    var asce = document.getElementById('asceId');
    asce.href = "report.do?action=listReport&asce='asce'&currentPage=" + currentPage + "&report.reportName=" + reportName;
}

function checkDateType() {
    var reportStartTime = document.getElementById('reportStartTime');
    var reportEndTime = document.getElementById('reportEndTime');
    var presetValue = document.getElementById('presetValue');
    var dateType = $(":radio[name=report.dateType]:checked");
    if ($(dateType).val() == "1") {
        $(presetValue).removeAttr("disabled");
        $(reportStartTime).attr("disabled", "");
        $(reportEndTime).attr("disabled", "");
    }
    else {
        $(presetValue).val("3");
        $(presetValue).attr("disabled", "");
        $(reportStartTime).removeAttr("disabled");
        $(reportEndTime).removeAttr("disabled");
        //时间控件
        $("#reportStartTime").datepick();
        $("#reportEndTime").datepick();
    }
    if (dateType.val() == "0") {
        $(presetValue).attr("disabled", "true");
    }
}

function cancle() {
    var reportId = document.getElementById("report.reportId");
    if (reportId.value == null || reportId.value == "") {
        document.reportForm.action = "report.do?action=add";
    }
    else {
        document.reportForm.action = "report.do?action=edit&cancleFlag=" + 1;
    }
    document.reportForm.submit();
    
}

//分页
function fillPage(PAGENUM, sort) {
    var s = "";
    if ("desc" == sort) {
        s = "desc=" + sort;
    }
    else if ("asce" == sort) {
        s = "asce=" + sort;
    }
    else {
        s = "desc=desc";
    }
    var url = "/report.do?action=fillPage&currentPage=" + PAGENUM + "&" + s;
    var ids = $("#ids").val();
    var type = $("#type").val();
    var reportStartTime = $("#reportStartTime").val();
    var reportEndTime = $("#reportEndTime").val();
    var presetValue = $("#presetValue").val();
    var status = $("#status").val();
    var dateType = $("#dateType").val();
    
    $.post(url, {
        "report.dateType": dateType,
        "reportStartTime": reportStartTime,
        "reportEndTime": reportEndTime,
        "presetValue": presetValue,
        "report.reportType": type,
        "report.reportCon": ids,
        "report.reportStatus": status
    }, function(data) {
        $("#list").html(data);
    });
}

function showPopDev() {
    $.openPopupLayer({
        name: 'popDiv',
        url: 'add_report_pop.jsp',
        cache: false,
        width: '500',
        success: function() {
        
        }
    });
}

function closepop() {
    $.closePopupLayer('popDiv');
}

/**
 * 报表
 * @param {Object} parms
 */
var chart = "";
var chartjson = "";
function getChar(obj) {
    var type = $("#type").val();
    var ids = $("#ids").val();
    var presetValue = $("#presetValue").val();
    var start = $("#reportStartTime").val();
    var end = $("#reportEndTime").val();
    var datetype = $("#dateType").val();
    var status = $("#status").val();
    var to = "1";
    var txt = "";
    if (obj) {
        to = $(obj).attr("tabindex");
        $(".tag a").each(function() {
            $(this).removeClass("now");
        });
        $(obj).addClass("now");
        txt = $(obj).html();
    }
    
    var parms = {
        'title': txt,
        'type': type,
        'ids': ids,
        'to': to,
        'presetValue': presetValue,
        'start': start,
        'end': end,
        //日期类型
        'dateType': datetype,
        //表表形式 汇总 、详情
        'status': status
    };
    
    if ("0" == status) {//汇总
        getTotalChart(parms);
    }
    else {//详细
        getDetailChart(parms);
    }
}

function getDetailChart(parms) {
    var url = "/report.do?action=dochartShow";
    jQuery.post(url, parms, function(d) {
        var data = d;
        var xname = data.columnkeys;
        var divide = data.divide;
        var ydata = data.rowkeys;
        var title = data.title;
        var today = "";
        var showTip = data.rowkeys.length < 15;
        
        if (showTip) {
            chartjson = {
                chart: {
                    //将报表对象渲染到层上  
                    renderTo: 'show',
                    defaultSeriesType: 'line', ///  图表类别，可取值有：line、spline、area、areaspline、bar、column等等
                    spacingRight: 30
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: title
                },
                xAxis: {
                    categories: xname,
                    labels: {
                        step: divide
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    }
                },
                legend: {
                    enabled: data.rowkeys.length < 15,
                    itemWidth: 200
                },
                
                plotOptions: {
                    line: {
                    
                        lineWidth: 1
                    }
                },
                tooltip: {
                    formatter: function() {
                        var s = '' + this.x + '';
                        
                        $.each(this.points, function(i, point) {
                            s += '<br/><span style=\"color:' + point.series.color + '\">' + point.series.name + ':</span> ' +
                            getShowNumStr(point.y) +
                            '';
                        });
                        return s;
                    },
                    shared: true
                },
                //设定报表对象的初始数据  
                series: ydata
            };
        }
        else {
    
            chartjson = {
                chart: {
                    //将报表对象渲染到层上  
                    renderTo: 'show',
                    defaultSeriesType: 'line', ///  图表类别，可取值有：line、spline、area、areaspline、bar、column等等
                    spacingRight: 30
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: title
                },
                xAxis: {
                    categories: xname,
                    labels: {
                        step: divide
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: ''
                    }
                },
                tooltip: {
                  formatter: function() {
                    return '' +this.x  + '<br/><b>' +
                     this.series.name +
                    '</b> : ' +
                    getShowNumStr(this.y) +
                    '';
                  }
                },
                legend: {
                    enabled: data.rowkeys.length < 15,
                    itemWidth: 200
                
                },
                //设定报表对象的初始数据  
                series: ydata
            };
        }
        chart = new Highcharts.Chart(chartjson);
    }, 'json');
}

function getShowNumStr(num1){
 // alert(num1);
  if(num1=='0'){
    return "0";
  }else{
    return '<b>'+num1+'</b>';
  }
}

function getTotalChart(parms) {
    var url = "/report.do?action=dochartShow";
    jQuery.post(url, parms, function(d) {
        var data = d;
        var xname = data.columnkeys;
        var ydata = data.rowkeys;
        var title = data.title;
        var sname = data.seriesName;
        var divide = data.divide;
        chartjson = {
            chart: {
                renderTo: 'show',
                defaultSeriesType: 'line',
                spacingRight: 30
            },
            credits: {
                enabled: false
            },
            title: {
                text: title
            },
            xAxis: {
                categories: xname,
                labels: {
                    step: divide
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: ''
                }
            },
            tooltip: {
                formatter: function() {
                  var s = '' + this.x  +'';
            
                  $.each(this.points, function(i, point) {
                      s += '<br/><span style=\"color:'+point.series.color+'\">'+ point.series.name +':</span>'+
                          getShowNumStr(point.y) +'';
                  });
                  
                  return s;
                },
                shared: true
            },
            legend: {
                enabled: false
            },
            series: [{
                name: sname,
                data: ydata
            }]
        };
        chart = new Highcharts.Chart(chartjson);
    }, 'json');
}

