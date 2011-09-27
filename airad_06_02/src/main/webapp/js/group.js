/**
 * 排序-降序
 */
function showNumDesc(currentPage, sortCol) {
    var desc = document.getElementById('descId');
    $("#sortCol").val(sortCol);
    $("#desc").val("1");
    $("#asce").val("0");
    document.sform.action = "adGroup.do?action=list&currentPage=" + currentPage;
    document.sform.submit();
}

function showNumAsce(currentPage, sortCol) {
    $("#sortCol").val(sortCol);
    $("#desc").val("0");
    $("#asce").val("1");
    var asce = document.getElementById('asceId');
    document.sform.action = "adGroup.do?action=list&currentPage=" + currentPage;
    document.sform.submit();
}

$(document).ready(function() {

    $(".checkboxadid").click(function() {
        switchcheckbox();
    });

    //全选
    $("#checkboxswitch").change(function() {
        var isc = $(this).attr('checked');
        var b = false;
        if (isc)
            b = true;
        $(".checkboxadid").each(function() {
            //	$(this).click();
            $(this).attr("checked", b);
        });
        switchcheckbox();
    });


    //修改投放地区
    $("#btnModLoclType").click(function() {
        if ($("tbody").find("td input[type=checkbox]:checked").length == 0) {
            alert("对不起,请先选择广告!");
        }
        else {
            $(".popBg").show();
            $("#popLoclTypeDiv").fadeIn();
        }
    })

    //修改所属地区提交
    $("#btnModLoclTypeSubmit").click(function() {
        if (confirm('您确定要修改所属地区?')) {
            var adLoclType = $('input:radio[@name="coreAdGroup.adLoclType"]:checked').val();
            if (adLoclType == 2) {
                var len = $("#proId").find("input[type=checkbox]:checked").length;
                if (len == 0) {
                    alert("请选择精确到的区");
                    return false;
                }
                var checkIds = "";
                $('#proId').children('div').each(function() {
                    var province = $(this).find("input[type=checkbox]:checked").filter(function(index) {
                        //查找floer 如果div 里有这个样式就说明他是城市级
                        if ($(this).parent().attr('class').indexOf('folder-close') > -1 || $(this).parent().attr('class').indexOf('folder-open') > -1) {
                            //查找这个div里面ul html没有值 就说明他没有区，我们就把省市赋值进去
                            if ($(this).next().next('ul').html() == '') {
                                checkIds += $(this).parent().parent().parent().attr('id') + "," + $(this).val() + ";";
                            }
                        }
                        if (!$(this).parent('li .doc').size() == 0 || !$(this).parent('li .doc-last').size() == 0) {
                            checkIds += $(this).next("span").attr("name") + ";";
                        }
                        else {
                            return;
                        }
                    });//省市 区节点
                });
                $("#exact").val(checkIds);
            }
            else {
                if (adLoclType == 1) {
                    var len = $("#adLoclInfoShowSp").find("input[type=checkbox]:checked").length;
                    if (len == 0) {
                        alert("请选择常用地区");
                        return false;
                    }
                }
            }
            var url = "";
            var arrAdGroupId = "";
            $("#list tbody").find("input[type=checkbox]:checked").each(function() {
                if ($(this).attr("alt") != "全选")
                    arrAdGroupId = arrAdGroupId + $(this).attr("alt") + ",";
            })
            arrAdGroupId = arrAdGroupId.substring(0, (arrAdGroupId.length - 1))
            url = "/adGroup.do?action=batchModLoclType&adGroupId=" + arrAdGroupId +
            "&sortCol=" +
            $("#sortCol").val() +
            "&desc=" +
            $("#desc").val() +
            "&asce=" +
            $("#asce").val() +
            "&adGroupName=" +
            $("#adGroupName").val();
            document.modLoclTypeForm.action = url;
           document.modLoclTypeForm.submit();
        }
        else {
            return false;
        }
    })

    //修改所属行业
    $("#btnModIndustry").click(function() {
        if ($("tbody").find("td input[type=checkbox]:checked").length == 0) {
            alert("对不起,请先选择广告!");
        }
        else {
            $(".popBg").show();
            $("#popIndustryDiv").fadeIn();
        }
    })

    //修改所属行业提交
    $("#btnModIndustrySubmit").click(function() {
        var boxArray = $('input:radio[@name="coreAdGroup.adTagSoftType"]:checked').val();
        if (boxArray == 0) {
            alert("您必须选择一个所属行业！");
            return false;
        }
        if (confirm('您确定要修改所属行业?')) {
            var url = "";
            var arrAdGroupId = "";
            $("#list tbody").find("input[type=checkbox]:checked").each(function() {
                if ($(this).attr("alt") != "全选")
                    arrAdGroupId = arrAdGroupId + $(this).attr("alt") + ",";
            })
            arrAdGroupId = arrAdGroupId.substring(0, (arrAdGroupId.length - 1))
            url = "/adGroup.do?action=batchModIndustry&adGroupId=" + arrAdGroupId + "&adTagSoftType=" + $("input[name='coreAdGroup.adTagSoftType']:checked").val() +
            "&sortCol=" +
            $("#sortCol").val() +
            "&desc=" +
            $("#desc").val() +
            "&asce=" +
            $("#asce").val() +
            "&adGroupName=" +
            $("#adGroupName").val();
            document.modIndustryForm.action = url;
            document.modIndustryForm.submit();
        }
        else {
            return false;
        }
    })

    //所属行业关闭事件
    $("#closeIndustryPop").click(function() {
        $(".popBg").hide();
        $("#popIndustryDiv").hide();
    })

    $("#btnModOs").click(function() {
        if ($("tbody").find("td input[type=checkbox]:checked").length == 0) {
            alert("对不起,请先选择广告!");
        }
        else {
            $(".popBg").show();
            $("#popDiv").fadeIn();
        }
    })

    //修改平台提交
    $("#btnModOsSubmit").click(function() {
        var boxArray = document.myfrm.CheckboxGroup1_1;
        var flag = false;
        for (var i = 0; i < boxArray.length; i++) {
            if (boxArray[i].checked) {
                flag = true;
                break;
            }
        }
        if (flag == false) {
            alert("您必须选择一个！");
            return false;
        }
        if (confirm('您确定要修改投放平台?') && flag == true) {
            var url = "";
            var arrAdGroupId = "";
            $("#list tbody").find("input[type=checkbox]:checked").each(function() {
                if ($(this).attr("alt") != "全选")
                    arrAdGroupId = arrAdGroupId + $(this).attr("alt") + ",";
            })
            arrAdGroupId = arrAdGroupId.substring(0, (arrAdGroupId.length - 1))
            url = "/adGroup.do?action=batchEdit&adGroupId=" + arrAdGroupId +
            "&sortCol=" +
            $("#sortCol").val() +
            "&desc=" +
            $("#desc").val() +
            "&asce=" +
            $("#asce").val() +
            "&adGroupName=" +
            $("#adGroupName").val();
            document.myfrm.action = url;
            document.myfrm.submit();
        }
        else {
            return false;

        }
    })

    $("#btnRun").click(function() {
        batchStopandsendAdGroup(1);
    })
    $("#btnStop").click(function() {
        batchStopandsendAdGroup(0);
    })

    //批量删除广告组
    $("#btnDel").click(function() {
        batchDeleteAdGroup(1);
    })

});

//批量停用或者启动广告组函数定义
function batchStopandsendAdGroup(type) {
    if ($("tbody").find("td input[type=checkbox]:checked").length == 0) {
        alert("对不起,请先选择广告!");
    }
    else {
        var url = "";
        var arrAdGroupId = "";
        $("#list tbody").find("td input[type=checkbox]:checked").each(function() {
            if ($(this).attr("alt") != "全选")
                arrAdGroupId = arrAdGroupId + $(this).attr("alt") + ",";
        })
        arrAdGroupId = arrAdGroupId.substring(0, (arrAdGroupId.length - 1))
        if (type == "0") {
            if (confirm('您确定要暂停此广告组下所有广告?')) {
                url = "/adGroup.do?action=batchSuspend&adGroupId=" + arrAdGroupId;
                document.sform.action = url;
                document.sform.submit();
            }
        }
        else if (type == "1") {
            if (confirm('您确定要运行此广告组下所有广告?')) {
                url = "/adGroup.do?action=batchIssue&adGroupId=" + arrAdGroupId;
                document.sform.action = url;
                document.sform.submit();
            }
        }
    }
}

//批量删除广告组函数定义
function batchDeleteAdGroup(type) {
    if ($("tbody").find("td input[type=checkbox]:checked").length == 0) {
        alert("对不起,请先选择广告!");
    }
    else {
        var url = "";
        var arrAdGroupId = "";
        $("#list tbody").find("td input[type=checkbox]:checked").each(function() {
            if ($(this).attr("alt") != "全选")
                arrAdGroupId = arrAdGroupId + $(this).attr("alt") + ",";
        })
        arrAdGroupId = arrAdGroupId.substring(0, (arrAdGroupId.length - 1))
        if (confirm('若删除广告组，则该广告组下所有广告均被删除，您确定要删除该广告组?')) {
            url = "/adGroup.do?action=batchDelAdGroup&adGroupId=" + arrAdGroupId;
            document.modIndustryForm.action = url;
            document.modIndustryForm.submit();
        }
    }
}

function switchcheckbox() {
    var flag = false;

    if ($("input:checkbox[name=checkbox1]:checked").length > 0) {
        flag = true;
    }

    if (flag) {
        $(".more").addClass("less");
        $(".more").removeClass("more");
        $("#btns").show();
    }
    else {
        $(".less").addClass("more");
        $(".less").removeClass("less");
        $("#btns").hide();
    }

    if ($("input:checkbox[name=checkbox1]").length ==
    $("input:checkbox[name=checkbox1]:checked").length) {
        document.getElementById("checkboxswitch").checked = true;
    }
    else {
        document.getElementById("checkboxswitch").checked = false;
    }

}

