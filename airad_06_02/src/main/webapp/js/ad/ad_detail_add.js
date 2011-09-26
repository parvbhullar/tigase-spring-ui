//引入自定义jquery
document.write("<script type='text/javascript' src='/js/ad/ad.jquery.js'></script>");
//引入定时器
document.write("<script type='text/javascript' src='/js/util/jquery.timers-1.2.js'></script>");
//富文本
document.write("<script type='text/javascript' src='/mice/htmlBox/htmlbox.min.js'></script>");
//引入string扩展
document.write("<script type='text/javascript' src='/js/util/utilString.js'></script>");
document.write("<script type='text/javascript' src='/js/ad/banner.js'></script>");
//引入map
document.write("<script type='text/javascript' src='/js/ad/map.js'></script>");
//引入taobao
document.write("<script type='text/javascript' src='/js/ad/taobao.js'></script>");
//引入photos
document.write("<script type='text/javascript' src='/js/ad/photos.js'></script>");
//引入market
document.write("<script type='text/javascript' src='/js/ad/market.js'></script>");

//rich.js
document.write("<script type='text/javascript' src='/js/ad/rich.js'></script>");
//遮罩
document.write("<script type='text/javascript' src='/js/ad/thickbox.js'></script>");
document.write("<script type='text/javascript' src='/js/ad/htmlbox.js'></script>");
document.write("<script type='text/javascript' src='/mice/uploadFile/swfobject.js'></script>");
document.write("<script type='text/javascript' src='/mice/uploadFile/uploadify.v2.1.4.min.js'></script>");
document.write("<script type='text/javascript' src='/mice/uploadFile/uploadFile.js'></script>");
//body
document.write("<script type='text/javascript' src='/js/body.js'></script>");
//校验
document.write("<script type='text/javascript' src='/js/validator.js'></script>");
//对错标记
var isFrist = true;

$(document).ready(function() {
    $.ajaxSetup({
        async: false
    });
    var bgImgId=$.trim($("#background").val());
    if(bgImgId.length>0&&!isNaN(bgImgId)){
     $("#navigationPreviewPage>div").css({
                "background-image": "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + bgImgId + "&t=" + Math.random() + "')",
                width: 320,
                height: 480
            });
            $("#previewNavigation").show();
            $("#delBackgroundPhoto").show();
    }
    //保存广告信息
    $("#adDraftSave").click(function() {
 
 
        if($("#adName").val().trim()==""){
           alert("请填写广告名称")
           $("#submitStatus").val(0);
           $("#draftStatus").val(0);
           return false;
        }

        if(isNaN($("#adOffer").val())){
           alert("请填写正确广告价格");
            $("#submitStatus").val(0);
           return false;
        }
        
 
        
        $.post("ad.do?action=adDraftSave", {
            adId: $("#adId").val(),//广告id
            adGroupId: $("#adGroupId").val(),//广告组id
            adType: $("#adType").val(),//广告类型
            adChildNum: $("#adChildNum").val(),//子页面数量
            adName: $("#adName").val(),//广告名称
            adGroupId: $("#adGroupId").val(),//广告名称
            campaignId: $("#campaignId").val(),//广告名称
            background: $("#background").val(),//保存广告页背景图片
            adOffer:$("#adOffer").val(),
            
            adStartTime:$("#adStartTime").val(),
            adStartHour:$("#adStartHour").val(),
            adStartMin:$("#adStartMin").val(),
            timeFlag:$("#timeFlag").val(),
            adEndTime:$("#adEndTime").val(),
            adEndHour:$("#adEndHour").val(),
            adEndMin:$("#adEndMin").val(),
            adBudgetDay:$("#adBudgetDay").val(),
            adBudgetTotal:$("#adBudgetTotal").val(),
            draftStatus:$("#submitStatus").val()
        }, function(data) {
          //设置是否修改了广告名字
          
        //  alert(data.adIsModifed+" 修改了 状态");
          //  if ($("#adIsModifed").val() == "1") {
          //      $("#adIsModifed").val(data.adIsModifed);
          //  }
            
            if ($("#adId").val() == "") {
                $("#adId").val(data.adid);
            }
 
        },'json');
    });
     //保存banner草稿
    $("#bannerDraftSave").click(function() {
      
        var bannerDesign=checkBanner4Save();
        if (!bannerDesign) {
            if ($("#draftStatus").val()=="1") {
              $("#submitStatus").val(0);
            }
            return false;
        }
        var bannerContent = "";
        if ($("#adId").val() == ""||$("#adName").val().trim()=="") {
          //如果广告id等于空字符就保存
          var isSub=0;
          if ($("#submitStatus").val() == 0) {
            $("#submitStatus").val(1)
            isSub=1;
          }
          $("#adDraftSave").click()
          if($("#submitStatus").val()==0){
            return false;
          }
          if (isSub == 1) {
            $("#submitStatus").val(0)
            isSub=0
          }
        }
        var bannerImgId = $("#bannerImgId").val();//banner图片id
        
        //修改了广告
       // alert('bannerDraftSave 修改了广告');
       // $("#adIsModifed").val("1");
        $.post("ad.do?action=bannerDraftSave", {
            adId: $("#adId").val(),//广告id
            bannerId: $("#bannerId").val(),//BannerId
            bannerColor: $("#bannerColor").val(),//banner类型
            bannerType: $("#bannerType").val(),
            bannerModelName: $("#bannerModelName").val(),//banner模版名称
            bannerHtml: bannerDesign,//banner内容
            bannerColor:bannerImgId,
            bannerBgCon:bannerImgId,
            bannerGrain: $("#bannerGrain").val(),
            bannerIconCon:$("#bannerIconCon").val()
        }, function(data) {
            if (data.bannerId != null) {
                $("#bannerId").val(data.bannerId)
            }
            if ($("#draftStatus").val()!="1"&&$("#submitStatus").val()!="1") {
                alert("Banner保存成功");
            }
            if ($("#draftStatus").val()!="1"&& $("#submitStatus").val()!="1") {
               $("#closeBanner").click();
            }
        }, 'json');
    });

    //保存草稿
    $("#saveAd").click(function(e) {
       $("#draftStatus").val(1);
         if ($("#adBudgetTotal").val().trim() != "" &
            !isOnlyNumber1($("#adBudgetTotal").val())) {
                  alert("总预算必须是数字");
              return ;
          }
          
          if ($("#adBudgetDay").val().trim() != "" &
           !isOnlyNumber1($("#adBudgetDay").val())) {
                  alert("每日预算必须是数字");
              return ;
          }
          
          if ($("#adBudgetDay").val().trim() != "" ) {
             if ($("#adBudgetDay").val() < 50) {
               alert("每日预算不小于50");
              return ;
             }
          }
          
          if ($("#adBudgetTotal").val().trim() != "" ) {
             if ($("#adBudgetTotal").val() < 50) {
               alert("总预算不小于50");
              return ;
             }
          }

        if($("#submitStatus").val()!="1"){
        //  $("#draftStatus").val(1);
        }
        $.ajaxSetup({
            async: false
        });
        //保存广告
        if ($("#adId").val() == "" || $("#adName").val().trim() == "") {
            $("#adDraftSave").click();
            
            if ($("#draftStatus").val()!="1") {
                $("#draftStatus").val(1);
                return false;
            }
        }else if($("#adType").val()!=""){
           $("#adDraftSave").click();
           if($("#draftStatus").val()!="1"){
             if ($("#submitStatus").val() != "1") {
               return false;
             }
           }
        }
        
        //保存banner
        $("#bannerDraftSave").click();
        if ($("#draftStatus").val() != "1") {
          if ($("#submitStatus").val()!="1") {
            return false;
          }
        }
        //保存广告页
        var $adType = $("#adType").val().trim();
        
        //wap 页面
        if ($adType == 1) {
          $("#wapDraftSave").click()
            if ($("#draftStatus").val()!="1") {
              if ($("#submitStatus").val() == "0") {
                return false;
              }
            }
        }
        else if ($adType == 2) {
            $(".pageDraftSave").each(function(i) {
                if (i > 0) {
                    $(this).click();
                    if ($("#draftStatus").val()!="1") {
                       if ($("#submitStatus").val()!="1") {
                          return false;
                       }
                    }
                }
            });
        }
        else if ($adType == 3 || $adType == 4) {
            var isError=false;
            var pageId = "";
            $(".richId").each(function(i) {
                if (i > 0) {
                    if ($(this).val().trim() == "") {
                        var pg = i + " ";
                        pageId += pg;
                    }
                }
            });

            var c = $(".pageDraftSave").each(function(i) {
                if (i > 0) {
                  //console.log(i)
                    $(this).click();
                    if ($("#draftStatus").val()!="1") {
                      if ($("#submitStatus").val() !="1") {
                          return false;
                       }
                    }

                }
            });
        }
        if ($("#draftStatus").val()=="1") {
       //   
          var url='/adGroup.do?action=adGroupSet&adId='+$("#adId").val()+'&flag=0&submitStatus=0&showAuth=no&adGroupId='+$("#adGroupId").val();
          
          var url1= "ad.do?action=adList&adGroupId=" + $("#adGroupId").val() + "&campaignId=" + $("#campaignId").val();

          if($("#adGroupId").val()==null || $("#adGroupId").val()=="0" || $("#adGroupId").val()==""){
            window.location.href =url;
          }else{
             window.location.href =url1;
          }
           
         
            return false;
        }
    });
    //提交广告
    $("#saveForm").click(function() {
 

        $("#submitStatus").val("1");
        if ($("#adName").val().trim()=="") {
             alert("请填写广告名称")
            $("#submitStatus").val("0");
            return ;
        }
        
      
        var $adType = $("#adType").val().trim();
        if (!checkBanner4Save()) {
            $("#submitStatus").val("0");
            return false;
        }
        
        if($("#adStartTime").val()!='' && $("#timeFlag").val()=='1'){
            var startTime = $("#adStartTime").val() +$("#adStartHour").val()+$("#adStartMin").val();
            var endTime = $("#adEndTime").val()+$("#adEndHour").val()+$("#adEndMin").val();
            if(endTime < startTime){
             alert("结束时间必须大于开始时间");
              return false;
            }
        }

      //current html
      var current_html= $.trim($(".bannerOut").text());
  //     current_html= checkBanner4Save();
      
      //original html
      var original_html=$("#adBannerContent").val();
     
      
      
      if(original_html!=current_html){
         $("#adIsModifed").val("1");
        // alert('url unmatched');
       //  alert(original_html);
        // alert(current_html);

      }
      
      if($("#adIsModifed").val()=="1"){
      //   alert('修改了banner');
      }else{
      //   alert('没有修改banner');
      }
      
      if($("#showType1").val()!=$("#showTypeOrignal").val()){
          $("#adIsModifed").val("1");
      //  alert('改变了模板');
      }
      
      

        var checkPageResult=true;
        $(".con22").find(".pageDraftSave").each(function(){
              if(!checkPage4Save($(this))){
                checkPageResult=false;
                return false;
              };
        });
        if(!checkPageResult){
          $("#submitStatus").val("0");
          return false;
        }
        $("#saveAd").click();
         if($("#submitStatus").val() !="1"){
            return false;
         }
         
          

 
      
        document.getElementById('adform').submit();
        return false;
    }); 
   
    //单页面
    $("#pic02").click(function() {
      $('#isNeedNotifyWhenChange2Wap').val("true");
   //     $(".spanAdNavigation").parent().parent().hide();
  //      $(".spanAdWap").parent().parent().hide();
      //  $(".spanAdRich").show();
       // $("#adType").val(2);//广告类型2单页面
       // $("#adBackground").show()
        //removeAllBox();

        $("#pic02").addClass("now");
    //    $("#pic01").removeClass("now");
     //   $("#pic03").removeClass("now");
     //   $("#pic04").removeClass("now");
      //  $("#con21").hide();
        var con20=$("#con20");
     //   con20.find("select").attr("style","display:none");
    //    con20.hide();
        createChildPage(1);//按钮个数
        $(".con22").show();
      //  $("#adBackground").show()
        getFree();
    });

    
    //点击子页面达到的效果
    $(".demoType").click(function() {
        $(".demoType").removeClass("now");
        $(this).addClass("now");
        $showType = $(this).attr("rev");//获取当前子页面的类型
        $("#btn12").attr("rev", $showType);
    });
    //子页面双击事件
    $(".demoType").dblclick(function() {
        $("#btn12").click();
    });
    
    //选择模版
    $("#btn10,#btn11").click(function() {
        $(".popBg").show();
        $("#popDiv2").fadeIn();
    });
    

    //关闭模版
    $("#closePop2,#btn12").click(function() {
        $("#fullbg").css("display", "none");
        $("#popDiv2").hide();
    });
    
    $("#btn12").click(function() {
        $showType = $(this).attr("rev");//获取当前子页面的类型
        changeChildPage($showType, $("#showTypev").val());
    });

    $('.popBg,#popDiv2').bind('mousewheel', function(event, delta) {
        if (delta == 0) {
            center(".popBg")
            center("#popDiv2")
        }
    });

    function backbroundUpload() {
        $('#navigationBackground').uploadifySettings('scriptData', {
            'action': 'upload',
            'sourceType': '5',
            'imgWidth': '320',
            'imgHeight': '480'
        });
    }
    function getBackgroundData(event, queueID, fileObj, response) {
        if (response.substr(0, 1) == "/") {
            response = response.substr(1);
        }
        if (response.substr(0, 1) == "\\") {
            response = response.substr(1);
        }
        if (!isNaN(response)) {
            $("#background").val(response);
            $("#navigationPreviewPage>div").css({
                "background-image": "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + response + "&t=" + Math.random() + "')",
                width: 320,
                height: 480
            });
            var adType = $("#adType").val();
            if (adType == 2) {
                $("#backGroupContent").css({
                    "background-image": "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + $("#background").val() + "&t=" + Math.random() + "')",
                    width: 320,
                    height: 480
                });
            }
            if (adType == 3) {
                $("#previewPage").attr("src", "/html/preview/navigation/commonly.html?var=index:navigation;type:navigation");
            }
            if (adType == 4) {
                $("#previewPage").attr("src", "/html/preview/navigation/advanced.html?var=index:navigation;type:navigation");
            }
            $("#previewNavigation").show();
            $("#delBackgroundPhoto").show();
        }
    }
    function onNavComplete(event, data) {
        var _swfId = "#navigationBackgroundUploader";
        $(_swfId).next('img').remove();
        return false;
    }

     function onNavProgress(event, ID, fileObj, data) {
         var _swfId = "#navigationBackgroundUploader";
        var _jswf = $(_swfId);
        if (_jswf.next('img').size() == 0) {
            _jswf.after('<img src="/images/ico_loading.gif" />');
        }
        return false;
    }

    if($("#navigationBackgroundUploader").size()==0){
    upLoadFileInit("#navigationBackground", "/fileuploading.do", "*.jpeg;*.jpg;*.png;", false, 5, '*.jpeg;*.jpg;*.png;', 1, 400 * 1024, backbroundUpload, "", getBackgroundData, null, null,null);
    }
    //广告背景预览
    $("#previewNavigation").click(function() {
        showBg('#navigationPreviewPage');
    });
    $("#delBackgroundPhoto").click(function() {
        if (!confirm("您确定要删除吗？"))
            return;
        $("#background").val("");
        $("#previewNavigation").hide();
        $("#delBackgroundPhoto").hide();
    });
    $(".navigationPreviewColse").click(function() {
        $("#fullbg").hide();
        $('#navigationPreviewPage').fadeOut();
    });

});
/**
 * 通过模版查
 * @param {Object} $showType
 */
function changeChildPage($showType, val) {
    $(".btn11").each(function(i) {
        if ($(this).attr("rel") == val) {
   
            if ($showType == 1) {
                $("." + val + "RichLabel").remove();
                var richLabel = $(".demoType").eq(0).clone().prependTo($(this).next());

                $(".spanAdRich").eq(Number(val.substring(val.length - 1, val.length)) - 1).find(".content").html(richLabel.attr("rel"));
                richLabel.removeClass("demoType").addClass(val + "RichLabel");
                if (richLabel.parent().find(".nor").html() == null) {
                    richLabel.parent().append('<div class="nor"><span class="modeTitle">纯文字模板</span><br><a href="javascript:void(0)">重新选择模板&raquo;</a></div>');
                }
                else {
                    richLabel.parent().find(".nor").find(".modeTitle").html("纯文字模板");
                }
            }
            if ($showType == 2) {
                $("." + val + "RichLabel").remove();
                var richLabel = $(".demoType").eq(1).clone().prependTo($(this).next());
                $(".spanAdRich").eq(Number(val.substring(val.length - 1, val.length)) - 1).find(".content").html(richLabel.attr("rel"));
                richLabel.removeClass("demoType").addClass(val + "RichLabel");
                if (richLabel.parent().find(".nor").html() == null) {
                    richLabel.parent().append('<div class="nor"><span class="modeTitle">图文模板</span><br><a href="javascript:void(0)">重新选择模板&raquo;</a></div>');
                }
                else {
                    richLabel.parent().find(".nor").find(".modeTitle").html("图文模板");
                }
            }
            if ($showType == 3) {
                $("." + val + "RichLabel").remove();
                var richLabel = $(".demoType").eq(2).clone().prependTo($(this).next());
                $(".spanAdRich").eq(Number(val.substring(val.length - 1, val.length)) - 1).find(".content").html(richLabel.attr("rel"));
                richLabel.removeClass("demoType").addClass(val + "RichLabel");
                if (richLabel.parent().find(".nor").html() == null) {
                    richLabel.parent().append('<div class="nor"><span class="modeTitle">相册模板</span><br><a href="javascript:void(0)">重新选择模板&raquo;</a></div>');
                }
                else {
                    richLabel.parent().find(".nor").find(".modeTitle").html("相册模板");
                }
            }
            if ($showType == 4) {
                $("." + val + "RichLabel").remove();
                var richLabel = $(".demoType").eq(3).clone().prependTo($(this).next());
                $(".spanAdRich").eq(Number(val.substring(val.length - 1, val.length)) - 1).find(".content").html(richLabel.attr("rel"));
                richLabel.removeClass("demoType").addClass(val + "RichLabel");
                if (richLabel.parent().find(".nor").html() == null) {
                    richLabel.parent().append('<div class="nor"><span class="modeTitle">地图模板</span><br><a href="javascript:void(0)">重新选择模板&raquo;</a></div>');
                }
                else {
                    richLabel.parent().find(".nor").find(".modeTitle").html("地图模板");
                }
            }
            if ($showType == 5) {
                $("." + val + "RichLabel").remove();
                var richLabel = $(".demoType").eq(4).clone().prependTo($(this).next());
                $(".spanAdRich").eq(Number(val.substring(val.length - 1, val.length)) - 1).find(".content").html(richLabel.attr("rel"));
                richLabel.removeClass("demoType").addClass(val + "RichLabel");
                if (richLabel.parent().find(".nor").html() == null) {
                    richLabel.parent().append('<div class="nor"><span class="modeTitle">淘宝商品模板</span><br><a href="javascript:void(0)">重新选择模板&raquo;</a></div>');
                }
                else {
                    richLabel.parent().find(".nor").find(".modeTitle").html("淘宝商品模板");
                }
            }
            if ($showType == 6) {
              
              
                $("." + val + "RichLabel").remove();
         
                var richLabel = $(".demoType").eq(5).clone().prependTo($(this).next());
 
                $(".spanAdRich").eq(Number(val.substring(val.length - 1, val.length)) - 1).find(".content").html(richLabel.attr("rel"));
         
                richLabel.removeClass("demoType").addClass(val + "RichLabel");
           
                if (richLabel.parent().find(".nor").html() == null) {
                    richLabel.parent().append('<div class="nor"><span class="modeTitle">淘宝直链模板</span><br><a href="javascript:void(0)">重新选择模板&raquo;</a></div>');
                } else {
                    richLabel.parent().find(".nor").find(".modeTitle").html("淘宝直链模板");
                }
         
                
            }
            if ($showType == 7||$showType == 8) {
                $("." + val + "RichLabel").remove();
               var richLabel;
                var modName;
                var modDec;
                if ($showType == 8) {
                    richLabel = $(".demoType").eq(7).clone().prependTo($(this).next());

                     $(".spanAdRich").eq(Number(val.substring(val.length - 1, val.length)) - 1).find(".content").html(richLabel.attr("rel"));
                     richLabel.removeClass("demoType").addClass(val + "RichLabel");
                     modName = "iPhone应用";
                     modDec = "该模版只支持iPhone手机下载";
                     $(".trMarketIphone").show();
                     $(".trMarketAndroid").hide();
                }else{
                     richLabel = $(".demoType").eq(6).clone().prependTo($(this).next());
                     modName="安卓应用";
                     modDec="该模版只支持安卓手机下载";
                     $(".spanAdRich").eq(Number(val.substring(val.length - 1, val.length)) - 1).find(".content").html(richLabel.attr("rel"));
                     richLabel.removeClass("demoType").addClass(val + "RichLabel");
                     $(".trMarketIphone").hide();
                     $(".trMarketAndroid").show();
                }
                if (richLabel.parent().find(".nor").html() == null) {
                    richLabel.parent().append('<div class="nor"><span class="modeTitle">'+modName+'</span><small>'+modDec+'</small><br><a href="javascript:void(0)">重新选择模板&raquo;</a></div>');
                }
                else {
                    richLabel.parent().find(".nor").find(".modeTitle").html(modName).next().html("<br>"+modDec);
                }
                
             } else if ($showType == 9) {
             //    alert('电话直拨');
             //电话直拨页面展示
             
                $("." + val + "RichLabel").remove();
             
                var richLabel = $(".demoType").eq(8).clone().prependTo($(this).next());
             
                $(".spanAdRich").eq(Number(val.substring(val.length - 1, val.length)) - 1).find(".content").html(richLabel.attr("rel"));
              
                richLabel.removeClass("demoType").addClass(val + "RichLabel");
                if (richLabel.parent().find(".nor").html() == null) {
                    richLabel.parent().append('<div class="nor"><span class="modeTitle">电话直拨模板</span><br><a href="javascript:void(0)">重新选择模板&raquo;</a></div>');
                } else {
       
                    richLabel.parent().find(".nor").find(".modeTitle").html("电话直拨模板");
                }
                
                //$(".trMarketIphone1").show();
             }

            mo = richLabel.parent();

            richLabel.parent().prev().hide();
            
            mo.find(".nor").find("a").click(function() {
                richLabel.parent().prev().click();
            })

            getFree();
            return false;
        }
    });
    
    uiId = val;
    $("#" + uiId).val($showType);//传入子页面当中（子页面类型）
    
    $(("#" + uiId + "WordTr")).css("display", "none");
    $(("#" + uiId + "PicWordTr")).css("display", "none");
    $(("#" + uiId + "PicTr")).css("display", "none");
    $(("#" + uiId + "MapTr")).css("display", "none");
    $(("#" + uiId + "Map")).css("display", "none");
    $(("." + uiId + "TaobaoTr")).css("display", "none");
    $(("." + uiId + "Taobao2Tr")).css("display", "none");
    $(("." + uiId + "MarketTr")).css("display", "none");
    //绑定元素名称 ,用作显示
    $(("." + uiId + "CallPhone")).css("display", "none");
    
    if ($showType == "1") {
        initBox(uiId, val);
    }
    if ($showType == "2") {
        initPicBox(uiId);
    }
    if ($showType == "3") {
        initPhotos(uiId);
    }
    if ($showType == "4") {
        initMap(uiId);
    }
    if ($showType == "5") {
        initTaobao(uiId);//初始化淘宝
    }
    if ($showType == "6") {
        initTaobao2(uiId);//初始化淘宝
    }
    if ($showType == "7"||$showType == "8") {
        initMarket(uiId);//初始化应用下载
    }
    if ($showType == "9") {
        initCallPhone(uiId);//初始化应用下载
    }
}

function isAdNameError() {
   $("#submitStatus").val("1");
         if ($("#adId").val() == "" || $("#adName").val().trim() == "") {
             $("#adDraftSave").click();
            if ($("#submitStatus").val() == 0) {
              return true;
            }
   }
}
function wapPageHasError(){
  if (isAdNameError())
  {
      return true;
  }
  if ($("#wapContent").val().trim() == "") {
      if ($("#draftStatus").val()!="1") {
          $("#submitStatus").val(0)
          alert("请填写wap文字内容");
          return true
      }
  }
}

function saveWapPage(succCallback){
  $.post("ad.do?action=wapDraftSave", {
      adId: $("#adId").val(),//广告id
      wapId: $("#wapId").val(),//wapid
      wapContent: $("#wapContent").val(),//广告内容
      wapFax: $("#wapFax").val(),//传真
      wapTelephone: $("#wapTelephone").val(),//电话
      wapEmail: $("#wapEmail").val(),//email
      wapQq: $("#wapQq").val(),//qq
      wapMsn: $("#wapMsn").val(),//msn
      wapAddress: $("#wapAddress").val(),//地址
      wapLinkText: $("#wapLinkText").val(),//连接文本
      wapLinkAddres: $("#wapLinkAddres").val()//链接地址
  },
  function(data) {
    succCallback(data);
  }, 'json');
}

//
function center(obj) {
    var windowWidth = document.documentElement.clientWidth;
    var windowHeight = document.documentElement.clientHeight;
    var popupHeight = $(obj).height();
    var popupWidth = $(obj).width();
    $(obj).css({
        "position": "absolute",
        "top": (windowHeight - popupHeight) / 2 + $(document).scrollTop(),
        "left": (windowWidth - popupWidth) / 2
    });

    //预览
    $("#previewWapShow").click(function() {

        TB_show("#previewWap");
    });
}

//wap页面事件
$("#previewWapShow").click(function() {
    $("#previewPage").attr("src", "/html/preview/wap/wap_in.html");

    $("#previewWapClose").show();

});

try {
$("#previewPage").load(function() {
  
  /*
    var wapPage = $(this).contents();
    $("#wapContent").val().trim() == "" ? "" : wapPage.find("#wapContent").html($("#wapContent").val() + "<p />");
    $("#wapTelephone").val().trim() == "" ? "" : wapPage.find("#phone").html("电话：" + $("#wapTelephone").val() + "<br />");
    $("#wapFax").val().trim() == "" ? "" : wapPage.find("#fax").html("传真：" + $("#wapFax").val() + "<br />");
    $("#wapEmail").val().trim() == "" ? "" : wapPage.find("#email").html("Email：" + $("#wapEmail").val() + "<br />");
    $("#wapQq").val().trim() == "" ? "" : wapPage.find("#qq").html("QQ：" + $("#wapQq").val() + "<br />");
    $("#wapMsn").val().trim() == "" ? "" : wapPage.find("#msn").html("MSN/Gmail：" + $("#wapMsn").val() + "<br />");
    $("#wapAddress").val().trim() == "" ? "" : wapPage.find("#address").html("联系人地址：" + $("#wapAddress").val() + "<br />");
    $("#wapLinkAddres").val().trim() == "" ? "" : wapPage.find("#url").html("网址：" + $("#wapLinkAddres").val());
    */
});
} catch (e) {
  
}

$("#exhibitWap").click(function() {
    $(this).hide();
    $("#closeWap").show();
    $("#wapTable").show();
    $("#wapDraftSave").show();
    $(this).prev().show();
    $("#pic01").click();
})
$("#closeWap").click(function() {
    $(this).hide();
    $("#exhibitWap").show();
    $("#wapTable").hide();
    $("#wapDraftSave").hide();
    $("#exhibitWap").prev().hide();
    $("#pic01").click();
})
if ($("#wapId").val() != "") {
    $("#closeWap").click();
}
else {
    $("#exhibitWap").click();
}

/**
 * 设置当前页面价格
 */
function getFree() {
 // alert('getFree');
    spanAdFree = 0;
    $(".singlefree").each(function() {
        var target = $(this);
        if (target.parent().parent().css("display") != 'none') {
            var htmlText = target.html().trim();
            if (htmlText != "") {
                spanAdFree += Number(htmlText);
            }
        }
    });
    var spanAdFreeFloat = spanAdFree.toFixed(2);
    $(".spanAdFree").html(spanAdFreeFloat);
    $("#htmlAdFree").html(spanAdFreeFloat);
}

//广告名称抬起事件
$("#adName").keyup(function() {
    if ($(this).val().length > 10) {
        $(".spanAdName").html($(this).val().substring(0, 10) + "...")
    }
    else {
        $(".spanAdName").html($(this).val());
    }
});
$("#uploadImgBannerButton").click(function() {
    var uiid = $("#uploadBannerImgDiv").data("uiid");
    $("#" + uiid).html("");
    var dem = $("#bannerImageData");
    var imgWidth = dem.width();
    var imgHeight = dem.height();
    var imgSrc = dem.attr("src");
    $("#" + uiid).html('<img style=" width: ' + imgWidth + 'px; height: ' + imgHeight + 'px;"  src="' + imgSrc + '"> ');
    $("#closeBannerImage").click();
});
$("#closeBannerImage").click(function() {
    $("#fullbg").hide();
    closeBg('#uploadBannerImgDiv');
});

function checkPage4Save(target) {

          if ($("#adName").val().trim() == "") {
                  alert("请先填写广告名称");
              return false;
          }
          var uiId = Number($(target).find("span").html().trim()) + 1;

          var currentModel = $("#showType" + uiId).val();
          if ($("#draftStatus").val()!="1") {
             if (currentModel == "") {
                 alert("请选择模板");
                 return false;
             }
          }
                    
          if ($("#draftStatus").val() != "1") {
            if ($("#adStartTime").val().trim() == "") {
              alert("请选择广告开始时间");
              return false;
            }
          }
          
          if ($("#isAdOffer")!=null && $("#isAdOffer").val().trim() == "1") {
          var cpm=$("#adOffer").val().trim();
            if (cpm=="" ) {
              alert("请输入每千次展示付费价格");
              return false;
            }
            
            if (!isOnlyNumber1(cpm)) {
              alert("每千次展示付费价格必须是数字");
              return false;
            }else if(cpm<=0){
                alert("每千次展示付费价格必须大于0");
                return false;
            }
          }
          
          
        
        
        
          if ($("#adBudgetTotal").val().trim() != "" &
            !isOnlyNumber1($("#adBudgetTotal").val())) {
                  alert("总预算必须是数字");
              return false;
          }
          
          if ($("#adBudgetDay").val().trim() != "" &
           !isOnlyNumber1($("#adBudgetDay").val())) {
                  alert("每日预算必须是数字");
              return false;
          }
          
          if ($("#adBudgetDay").val().trim() != "" ) {
             if ($("#adBudgetDay").val() < 50) {
               alert("每日预算需不小于50");
              return ;
             }
          }
          
          if ($("#adBudgetTotal").val().trim() != "" ) {
             if ($("#adBudgetTotal").val() < 50) {
               alert("总预算不小于50");
              return ;
             }
          }

          var $val = $(target).find("span").html();
          var i = Number($val);//获取标签数
          var bs;//mgr
          var $showType = "showType" + (i + 1);
          if ($("#" + $showType).val() == "1") {
               //保存纯文本
               var phone = $("#" + $showType + "WordPhone").val();
               if (phone.trim().length > 0 && !phone.isTel()) {
                        alert("电话号码错误，请重新填写");
                        return false;
               }
               var checkVr = showBoxArray.get("#" + $showType + "Box").get_text();//获取没有样式的VR内容
               checkVr = checkVr.toString().trim();
               //判断富文本的长度,如果查过2000个字符,就不允许输入了
               var len=0;
               len = checkVr.length;
               len = checkVr.length;
               if ($("#draftStatus").val() != "1") {
                    if (len == 0) {
                      alert("请填写广告内容")
                      return false;
                    }
               }
               if(len >2000){//内容过长,影响AJAX 提交信息,所以简略的做一个判断
                   alert("您输入的文本内容太长了,请重新输入");
                   return false;
               }
               var vr = showBoxArray.get("#" + $showType+ "Box").get_html();//获取复文本的内容
               bs = {
                          adId: $("#adId").val(),//广告id
                          richId: $(("#" + "richId" + (i + 1))).val(),//富媒体id
                          showType: $(("#" + $showType)).val(),
                          sort: i + 1,
                          richMediaTitle: $(("#" + "richMediaTitle" + (i + 1))).val(),//广告标题
                          delRichId: $("#delRichId").val(),
                          phone: $("#"+$showType + "WordPhone").val(),
                          richMediaContent: vr
                 };
            }
            else if ($("#" + $showType).val() == "2") {
                 //保存纯文本
                 var phone = $("#" + $showType + "WordPicPhone").val();
                 if (phone.trim().length > 0 && !phone.isTel()) {
                       alert("电话号码错误，请重新填写");
                       return false;
                 }
                 var imgId = $(("#" + $showType + "ImgId")).val();
                 if ($("#draftStatus").val()!="1") {
                    if (imgId.trim() == "") {
                       alert("请上传图片");
                       return false;
                    }
                 }
                 //保存图文
                 var checkVr = showBoxArray.get("#" + $showType + "PicBox").get_text();//获取没有样式的VR内容
                 checkVr = checkVr.toString().trim();
                 //判断富文本的长度,如果查过200个字符,就不允许输入了
                 var len=0;
                 len = checkVr.length;
                  if ($("#draftStatus").val() != "1") {
                    if (len == 0) {
                      alert("请填写广告内容")
                      return false;
                    }
                  }
                 if(len >2000){
                      alert("您输入的文本内容太长了,请重新输入");
                      return false;
                 }
                 var vr = showBoxArray.get("#" + $showType + "PicBox").get_html();//获取复文本的内容
                 bs = {
                            adId: $("#adId").val(),//广告id
                            richId: $(("#" + "richId" + (i + 1))).val(),//富媒体id
                            showType: $(("#" + $showType)).val(),//类型地图 淘宝等
                            formatType: $(("#" + $showType + "FormatType")).val(),
                            sort: i + 1,//排序
                            relId: $(("#" + $showType + "ImgId")).val(),
                            richMediaTitle: $(("#" + "richMediaTitle" + (i + 1))).val(),//广告标题
                            delRichId: $("#delRichId").val(),
                            phone: $("#"+$showType + "WordPicPhone").val(),
                            richMediaContent: vr
                 };
             }
             else if ($("#" + $showType).val() == "3") {
                 //保存保存相册
                 var rel = "";
                 var num = $(("." + $showType+ "imgId")).each(function(){
                                if ($(this).val().trim() != "") {
                                    rel += $(this).val();
                                    rel += ",";
                                }
                           })
                 if (rel != "") {
                     rel = rel.substring(0, rel.length - 1);
                 }
                 if (num.size() > 6) {
                     alert("最多只能显示5个图片,请删除多余图片");
                     return false;
                 }
                 //console.log(num.size())
                 if (num.size() < 2) {
                     if ($("#draftStatus").val()!="1") {
                        alert("请上传至少一张图片");
                        return false;
                     }
                 }
                 bs = {
                         adId: $("#adId").val(),//广告id
                         richId: $(("#" + "richId" + (i + 1))).val(),//富媒体id
                         showType: $(("#" + $showType)).val(),
                         sort: i + 1,
                         relId: rel,
                         delRichId: $("#delRichId").val(),
                         richMediaTitle: $(("#" + "richMediaTitle" + (i + 1))).val()//广告标题
                 };
             }
             else if ($("#" + $showType).val() == "4") {
                 //保存地图
                 bs = saveMap($showType, i);
             }
             else if ($("#" + $showType).val() == "5") {
                 //保存淘宝
                 var taobaoIId = "";
                 $(("." + $showType+ "TaobaoTr")).find(".numIID").each(function(l){
                     if (l < $(("." + $showType + "TaobaoTr")).find(".numIID").size() - 1) {
                          if ($(this).val() != "") {
                               taobaoIId += $(this).val();
                               taobaoIId += ",";
                           }
                      }
                  });
                  if (taobaoIId != "") {
                      taobaoIId = taobaoIId.substring(0, taobaoIId.length - 1);
                  }
                  else
                  {
                      if ($("#draftStatus").val()!="1") {
                         alert("请正确填写淘宝id");
                         return false;
                      }
                   }
                   bs = {
                      adId: $("#adId").val(),//广告id
                      richId: $(("#" + "richId" + (i + 1))).val(),//富媒体id
                      showType: $(("#" + $showType)).val(),
                      sort: i + 1,
                      relId: taobaoIId,
                      delRichId: $("#delRichId").val(),
                      richMediaTitle: $(("#" + "richMediaTitle" + (i + 1))).val()//广告标题
                   };
            }
            else if ($("#" + $showType).val() == "6") {
                  var taobaoUrl = $("#"+$showType+"TaobaoUrl").val()
                  if(taobaoUrl.trim()==""){
                       alert("网址不能为空");
                       return false;
                  }
                  if(taobaoUrl.match(/(^([\w-]+\.(taobao|tmall).com\/)+([\w- ./?%&=]*)?)/)==null){
                       alert("网址只能输入淘宝网址");
                       return false;
                  }
                  bs = {
                       adId: $("#adId").val(),//广告id
                       richId: $(("#" + "richId" + (i + 1))).val(),//富媒体id
                       showType: $(("#" + $showType)).val(),
                       sort: i + 1,
                       taobaoId: $("#"+$showType+"TaobaoId").val(),
                       taobaoUrl: $("#"+$showType+"TaobaoUrl").val(),
                       richMediaTitle: $(("#" + "richMediaTitle" + (i + 1))).val()//广告标题
                      };
             }
             else if ($("#" + $showType).val() == "7"||$("#" + $showType).val() == "8") {
                  //var taobaoUrl = $("#" + $showType+"TaobaoUrl").val()
                  var marketName = $("#" + $showType+"MarketName").val()
                  var marketAndroidUrl = $("#" + $showType+"MarketAndroidUrl")
                  var marketIphoneUrl = $("#" + $showType+"MarketIphoneUrl")
                  var showType=$("#" + $showType).val();
                  if(marketName.trim()==""){
                      alert("应用名称不能为空");
                      return false;
                  }
                  else{
                      if (marketName.maxLengthEn(14)) {
                           alert("应用名称不能大于7个中文字");
                           return false;
                      }
                  }
                  if($("#showType" + (i + 1)+"MarketImgId").val().trim()==""){
                      alert("请上传功能图片");
                      return false;
                  }
                  var $marketType = $("#marketType").val();
                  if(showType==7){
                      $marketType=1;
                      if(marketAndroidUrl.val().trim().length==0){
                          alert("请正确输入Android的应用地址");
                          return false;
                      }
                      else if(marketAndroidUrl.val().trim().length>0){
                          if(marketAndroidUrl.val().match(/(^((details\?id\=))+([\w-\.]*)?)/)==null){
                              alert("请正确输入Android的应用地址");
                              return false;
                          }
                      }
                      marketIphoneUrl.val("");
                   }else{
                          $marketType=2;
                          if(marketIphoneUrl.val().trim().length==0){
                             alert("请正确输入Iphone的应用地址");
                             return false;
                          }else if(marketIphoneUrl.val().trim().length>0){
                             if(marketIphoneUrl.val().match(/(^(itunes.apple.com)+([\w- ./?%&=]*)?)/)==null){
                               alert("请正确输入Iphone的应用地址");
                                   return false;
                              }
                          }
                          marketAndroidUrl.val("");
                   }
                   bs = {
                          adId: $("#adId").val(),//广告id
                          richId: $(("#" + "richId" + (i + 1))).val(),//富媒体id
                          showType: $(("#" + "showType" + (i + 1))).val(),
                          sort: i + 1,
                          richMediaTitle: $(("#" + "richMediaTitle" + (i + 1))).val(),//广告标题
                          marketId: $("#"+$showType+"MarketId").val(),
                          marketName: $("#"+$showType+"MarketName").val(),
                          marketImgId: $("#"+$showType+"MarketImgId").val(),
                          marketType: $marketType,
                          marketAndroidId: $("#"+$showType+"MarketAndroidId").val(),
                          marketIphoneId: $("#"+$showType+"MarketIponeId").val(),
                          marketAndroidUrl: $("#"+$showType+"MarketAndroidUrl").val(),
                          marketIphoneUrl: $("#"+$showType+"MarketIphoneUrl").val()
                         };
             }else if ($("#" + $showType).val() == "9") {
               //保存电话直拨
 
               var phone = $("#" + $showType + "CallPhoneNumber").val();

               if (!phone.isTel()) {
                        alert("电话号码错误，请重新填写");
                        return false;
               }
                bs = {
                          adId: $("#adId").val(),//广告id
                          richId: $(("#" + "richId" + (i + 1))).val(),//富媒体id
                          showType: $(("#" + $showType)).val(),
                          sort: i + 1,
                          callPhoneId: $("#"+$showType+"CallPhoneId").val(),
                          callPhoneNumber: $("#"+$showType+"CallPhoneNumber").val()
                 };
             }
             else {
                    bs = {
                          adId: $("#adId").val(),//广告id
                          richId: $(("#" + "richId" + (i + 1))).val(),//富媒体id
                          sort: i + 1,
                          delRichId: $("#delRichId").val(),
                          richMediaTitle: $(("#" + "richMediaTitle" + (i + 1))).val()//广告标题
                         };
            }
          //   alert('richId:'+bs.richId);
            return bs;
}

function isOnlyNumber1(str){
 // alert('validate');
  //var reg=/^(1(\.0+)?|0+(\.[0-9]+)?)$/;
  var reg=/^[0-9]+(\.[0-9]{1,2})?$/;
  
  return reg.test(str);
}

  function checkBanner4Save() {
          $(".bannerOut:first").attr("style","");
          $(".bannerOut").eq(1).css("display","none");
        var bannerDesign = $("#bannerDesign").html();
        var errMagess=""
            var bannerText=$.trim($(".bannerOut").text());
            var bannerTextTrim=bannerText.replaceAll("<br>","").replaceAll("<br/>","").replaceAll("Top","v");
            var bannerModelName=$("#bannerModelName").val();
            var bannerType=$("#bannerType").val();
            var bannerImgFalg = false;
            $(".bannerImage").each(function() {
                if ($(this).find("img").size() == 0) {
                    bannerImgFalg = true;
                }
            });
            if(bannerModelName.trim()==""){
                errMagess ="选择banner模版";
            }else if (bannerType!='9'&&(bannerText== "请输入文字"||bannerTextTrim.length==0)) {
                 errMagess ="请输入Banner文字";
            }
            else if (bannerImgFalg) {
                 errMagess ="请上传Banner图片";
            }
            else if ($("#bannerType").val().trim() == "") {
                 errMagess ="请先选Banner模版";
            }
            if(errMagess!=""){
                var tempFlag =true;
                if ($("#draftStatus").val()!="1") {
                    alert(errMagess)
                }
                if(errMagess=='请输入Banner文字'){
                   if ($("#draftStatus").val() == "1") {
                     tempFlag = false;
                   }
                }
                if (tempFlag) {
                  return false;
                }
                  
            }
        var bannerOutVal = false;
        $(".bannerOut").each(function(i) {
            var trimVal=$.trim($(this).text());
            $(this).text(trimVal);
            var textValue = $(this).text();
                   var len=0;
                    for(var myindex=0;myindex<textValue.length;myindex++){
                      var intCode=textValue.charCodeAt(myindex);
                      if (intCode>=0&&intCode<=128){
                        len=len+1;
                      }else{
                        len=len+2;
                      }
                    }
            if ($("#bannerType").val() == 5) {
                if (len > 80) {
                    alert("Banner字数在40个汉字(80个字符)以内");
                    bannerOutVal = true;
                }
            }
            if ($("#bannerType").val() == 6) {
                if (len > 64) {
                    alert("Banner字数在32个汉字(64个字符)以内");
                    bannerOutVal = true;
                }
            }
            if ($("#bannerType").val() == 7||$("#bannerType").val() == 8) {
                if (len > 36) {
                    alert("Banner字数在18个汉字(36个字符)以内");
                    bannerOutVal = true;
                }
            }
            if ($("#bannerType").val() == 8) {
                if (len > 64) {
                    alert("Banner字数在32个汉字(64个字符)以内");
                    bannerOutVal = true;
                }
            }
            var px = $(this).attr("style");
            bannerDesign = bannerDesign.replace(px, "");
            bannerDesign = bannerDesign.replace('style=""', "");
            bannerDesign = bannerDesign.replace('contenteditable="true"', "");
        });
        if (bannerOutVal) {
            if ($("#draftStatus").val()!="1") {
                return false;
            }
        }
        return bannerDesign;
    }
    
 
   
$(document).ready(function(){ 
        $("#adType").val(2);
         createChildPage(1);//按钮个数
        $(".con22").show();
        $("#adChildNum").val('1');
        
      $("#adStartTime").datepick();
      $("#adEndTime").datepick();
      showEndTimead();
      
      
         //编辑事件
   
   //barner修改
   /*
    $("#bannerDesign").bind('click',function() {
       $("#adIsModifed").val("1");
    });
    */
   
    /*
    //广告名称修改
    $("#adName").click(function() {
       $("#adIsModifed").val("1");
    });
    */
   
    //输入框修改
    $("#adName,.wordPhone,.wordPicPhone,.taobaoUrl,.marketName,.marketAndroidUrl,.marketIphoneUrl,.CallPhoneId").bind('input',function() {
       $("#adIsModifed").val("1");
    });
    

  //$("#adBackground").show();
  //salert(22);/
  //$("#pic02").click();
})
