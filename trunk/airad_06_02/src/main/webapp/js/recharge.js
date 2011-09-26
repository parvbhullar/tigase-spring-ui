function recharge() {
    var recharge = document.getElementById('coreRechargeHis.money').value;
    var myreg = /^(([1-9]\d*)|0)(\.\d{2})?$/;//数字验证
    if (!myreg.test(recharge)) {
        document.getElementById("recharge").innerHTML = "请填写有效资金转换金额";
    }
    else {
        document.getElementById("recharge").innerHTML = "";
        document.forms[0].submit();
    }

}

function withDrawHis() {
  $("#importXml").attr("href","/withdraw.do?action=exportXML");
  $("#importCsv").attr("href","/withdraw.do?action=exportCSV");
  $("#withDrawHis").addClass("now");
  $("#recHis").removeClass("now");
  $("#withDrawHis").attr("href","#");
}

function recHis() {
  $("#importXml").attr("href","#");
  $("#importCsv").attr("href","#");
  $("#withDrawHis").removeClass("now");
  $("#recHis").addClass("now");
}
/**
 * 排序-降序
 */
function recHisDesc(flag)
{
    var desc=document.getElementById('descId');
    $("#desc").val(flag);
    document.hisName.action = "rechargeHis.do?action=list";
    document.hisName.submit();
   
}
function recHisRecord(str)
{$("#status").val(str);
document.hisName.action = "rechargeHis.do?action=list";
    document.hisName.submit();
}
 /**
 * 排序-升序
 */
function recHisAsce(currentPage,status)
{
    var asce=document.getElementById('asceId');
    asce.href="rechargeHis.do?action=listRechargeHis&asce='asce'&currentPage="+currentPage+"&status="+status;
}
