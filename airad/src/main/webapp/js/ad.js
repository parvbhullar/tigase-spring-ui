var adO=new function(){
  this.showUpload=function(){
    $.openPopupLayer({
       name:'popUploadPage',
       url:'/adEdit.do?action=uploadPage',
       cache:false,
       width:'500',
       success:function(){
       }
     }); 
  }  
  this.upload=function(){
    document.forms['uploadForm'].submit();
  };
  var jcrop_api;
  //剪切保存的参数
  var cropParam={x:'',y:'',w:'',h:'',fileId:''};
  this.uploadCallback=function(data){
      if(data['status']=='fail'){
        
      }else if(data['status']=='succ'){
        //当前上传的图片id
        var fileId=data['fileId'];
        //当前上传的图片url
        var imageUrl='/adEdit.do?action=lookImage&fileId='+fileId;
        cropParam['fileId']=fileId;
        $('#cropbox').attr('src',imageUrl);
        //设置剪切操作
        setTimeout(function(){
          jcrop_api=$('#cropbox').Jcrop({
            aspectRatio: 1,
            //allowResize:false,
            //minSelect:      [ 200, 100 ],
            //maxSize:      [ 100, 100 ],
            //minSize:      [ 200, 200 ],
            setSelect:   [ 100, 100, 200, 150 ],
            onSelect: function(c){
                cropParam['x']=c.x;
                cropParam['y']=c.y;
                cropParam['w']=c.w;
                cropParam['h']=c.h;
            }
          });          
        },500);
        //关闭页面
        $.closePopupLayer('popUploadPage');        
      }else{
        alert('上传错误处理');
      }
  };
  this.crop=function(){
    $.getJSON('/adEdit.do?action=doCrop',cropParam,function(data){
        var fileId=data['fileId'];
        var imageUrl='/adEdit.do?action=lookImage&fileId='+fileId;
        //清除之前的剪切
        jcrop_api.destroy();
        $('#cropbox').attr('src',imageUrl).show();
        //设置剪切操作
        setTimeout(function(){
          jcrop_api=$('#cropbox').Jcrop({
            aspectRatio: 1,
            onSelect: function(c){
                cropParam['x']=c.x;
                cropParam['y']=c.y;
                cropParam['w']=c.w;
                cropParam['h']=c.h;
            }
          });          
        },500);        
    })
  }
}()


function return_adList_page(){
  document.myfrm.action="/ad.do?action=adList";
  document.myfrm.submit(); 
}
/**
 * 根据时间段查找收益提交方法
 */
function adTimeSlotSubmit() {
    $("#timeSlotFlag").val("true");
    document.statisticform.submit();
}
/**
 * 查询广告
 */
function searchAdSubmit(e) {
  //enter
   var currKey = 0, e = e || event;
    if (e.keyCode == 13) 
  document.statisticform.submit(); 
}


/**
 * 排序-降序
 */
function adDesc() {
  $("#asce").val("");
    document.statisticform.submit(); 
}

/**
 * 排序-升序
 */
function adAsce() {
   $("#asce").val("true");
   document.statisticform.submit(); 
}
