function sub() {
  alert("4444");
    $("#command").attr("action", "withdraw.do?action=doCreateWithdraw").submit();
}
/**
 * 排序-降序
 */
function reqTimeDesc(currentPage)
{
    var desc=document.getElementById('descId');
    desc.href="withdraw.do?action=listOrderByTime&desc='desc'&currentPage="+currentPage;
}

 /**
 * 排序-升序
 */
function reqTimeAsce(currentPage)
{
    var asce=document.getElementById('asceId');
    asce.href="withdraw.do?action=listOrderByTime&asce='asce'&currentPage="+currentPage;
}


