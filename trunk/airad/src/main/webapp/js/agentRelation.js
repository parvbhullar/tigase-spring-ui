 function deleteAgentRelation(id) {
    if (window.confirm("确定要解除与广告商的关系吗？")) {
      setFormAction('agentRelation.do?action=deleteAgentRelation&relationId=' + id);
    }
  }
  function sureInvitationCode() {
        if (window.confirm("确定要生成邀请吗？")) {
            setFormAction('agentRelation.do?action=invitationCode');
        }
  }
  function setFormAction(prm) {
      document.myfrm.action = prm;
      document.myfrm.submit();
  } 
/**
 * 排序-降序
 */
function agentDesc() {
  $("#asce").val("");
    document.agentSortform.submit(); 
}

/**
 * 排序-升序
 */
function agentAsce() {
   $("#asce").val("true");
   document.agentSortform.submit(); 
}

/**
 * 根据时间段查找收益提交方法
 */
function agentTimeSlotSubmit() {
    $("#timeSlotFlag").val("true");
    document.agentStatisticform.submit();
}

//enter事件
function agentSearchSubmit(e) {
    var currKey = 0, e = e || event;
    if (e.keyCode == 13) 
        document.agentSearchform.submit();
}
//enter事件
function agentRechargeHistorySearchSubmit(e) {
    var currKey = 0, e = e || event;
    if (e.keyCode == 13) 
        document.myform.submit();
}
