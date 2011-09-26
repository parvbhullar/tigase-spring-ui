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


function getDetailChart(pageNum,desc,sortindex) {
  var url = "/ad.do?action=adlistajax";
  
    var currentPage = $("#currentPage").val();
    var adGroupId = $("#adGroupId").val();
    var adName = $("#adName").val();

    var parms = {
        'currentPage': pageNum,
        'adGroupId': adGroupId,
        'adName': adName,
        'desc': desc,
        'timeSlotFlag': sortindex
    };
  
  $.post(url, parms, function(d) {
  
     $("#list").html(d);
  });
};


function showEndTimead(endTime, endHour, endMin) {
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
         $("#adEndHour").show();
        $("#adEndMin").show();
    }
    else {
       /**
        endTimeId.disabled = "true";
        endHour.disabled = "true";
        endMin.disabled = "true";
        */
        $("#showEndTimeId").hide();
        $("#showEndTimeIdPreview").hide();
        $("#adEndHour").hide();
        $("#adEndMin").hide();
    }
    
}

function  isOnlyNumber(v){
  var reg = /^[0-9]{0,}$/;

  return reg.test(v);
}




    function validateBox(){

        if($("input:checkbox[name=checkbox1]").length == 0){
        showTips('这个组还没有广告',true);
       return false ;
      }
      if($("input:checkbox[name=checkbox1]:checked").length == 0){
         showTips('请选择需要操作的广告',true);
         return false;
      }

      return true;
}

   function runCheckedIdS(optype){

    if(!validateBox()){
      return;
     }
     
      var arr=getCheckedIdS();
       
    //   console.log($("input[name='checkbox1']:checked").val());
      // console.log($("input:checkbox[name=checkbox1]:checked'"));
      // console.log($(input[name="checkbox1"]:checked).val());
     //  alert(arr+"--"+optype);
     
     var groupid = $("#selectGroupId").val();


     /*     
            移动此广告
       ad.do?action=adDelive&adIds="+adId;
            删除此广告 
       ad.do?action=deleteAd&adIds="+adId;
           暂停此广告 
       ad.do?action=stopAd&adIds="+adId;
          运行此 
       ad.do?action=issue&adIds="+adId;

     */

     var urlOpe = '';
     var urlOpeNmae = '';
 
     if(optype=="0"){
         urlOpe='/ad.do?action=adDeleve';
         urlOpeNmae = '移动此广告';
     }else if(optype=="1"){
         urlOpe='/ad.do?action=issue';
         urlOpeNmae = '运行此广告';
     }else if(optype=="2"){
         urlOpe='/ad.do?action=stopAd&adIds';
         urlOpeNmae = '暂停此广告 ';
     }else if(optype=="3"){
         urlOpe='/ad.do?action=deleteAd';
         urlOpeNmae = '删除此广告 ';
     }
     
     if (!window.confirm("确定要"+urlOpeNmae+"？")) {
         return ;
     }
     
     $.post(urlOpe, {
       adIds: arr,
       adGroupId: groupid
     }, function(data) {
         if(data.returnCode=="1"){
            showTips('操作成功哟！',false);
             getDetailChart(1,'desc',6);
         }else{
           showTips('操作失败',true);
         }
         
     },'json');
 
      // document.onkeypress=searchAdSubmit;
       //alert($(input[name="checkbox1"]:checked).val());
    //   showTips();
   }
   
   function getCheckedIdS(){
       var arr=new Array();
       $("input:checkbox[name=checkbox1]:checked").each(function() {
            arr.push($(this).val());
       });
       return arr.toString();
   }


   function switchcheckbox(){
       var flag = false;
       
       if($("input:checkbox[name=checkbox1]:checked").length > 0){
         flag = true;
       }
       
        if(flag){
            $(".more").addClass("less");
            $(".more").removeClass("more");
            $("#btns").show();
        } else {
            $(".less").addClass("more");
            $(".less").removeClass("less");
            $("#btns").hide();
        }

        if($("input:checkbox[name=checkbox1]").length ==
          $("input:checkbox[name=checkbox1]:checked").length ){
            document.getElementById("checkboxswitch").checked = true;
         }else{
           document.getElementById("checkboxswitch").checked = false; 
         }
           
   }
   
   function showAdTips(){
     alert('平台广告已改版，旧版广告不可修改，原来投放的广告仍正常运行，若您想修改广告信息，请重新制作广告。');
    }   