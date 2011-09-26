function initPage(){
  addCss('openNotice');
  var remindFlag = $('#remindFlag').val();
  var isActive = remindFlag == '1';
  $('#updateActive').text(isActive?"停止使用":"开启使用");
  if(isActive){
    $('#operate').show();
    $('#btnupdate').show();
    $('#btnsave').hide();
    $('#curStatus').text('启用').attr('class','green');
  }else{
    $('#operate').hide();
    $('#curStatus').text('停用').attr('class','red');
  }
  //
}

function updateActiveFlag(){
  var url= 'accMailNotice.do?action=updateRemindFlag';
  var remind= $('#remindFlag');
  var remindFlag = remind.val();
  var rf = remindFlag=='1'? '0':'1';
  var data= {};
  $.post(url,data,function(result){
    if(result.status == 'succ'){
      remind.val(rf);
      callback();
    }
  },'json');
}

function callback(){
    initPage();
    $('#okTipDiv').show();
    $('#okTip').show().animate({
        opacity: 1.0
    }, 3000).fadeOut("slow", function() {
        $(this).hide();
    });
}

function editRemind(){
  window.location  = 'accMailNotice.do?action=editRemind';
}
$(initPage);
