function initPage(){
  addCss('openNotice');
  var remindFlag = $('#remindFlag').val();
  var isActive = remindFlag == '1';
  if(isActive){
    $('#curStatus').text('启用').attr('class','green');
  }else{
    $('#curStatus').text('停用').attr('class','red');
  }
  //
  var aBlance=$('#accountBlance').val();
}


$(initPage);
