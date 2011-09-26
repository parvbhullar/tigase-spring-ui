$.initBannerModel = function(uiId){
    $.ajaxSetup({
        cache: false
    });
    //点击“请选择banner模板链接”，将不同的模板按照图片id加载出来
    $("#bannerSelect").click(function(){
        //初始化模版
        $.getJSON("/html/model/banner.json", function(data){
            var html = [];
            $("#bannerConetnt").data("json", data);
            showBg('#bannerModel');
            $("#bannerModel").show();
        });
        //加入事件（选择模版类型）
        $(".selectModelByType").click(function(){
            var _targetModelType=$(this);
            var pointerModelType = _targetModelType.text().trim();
            var bannerPrice = $("#bannerPriceSelect");
            bannerPrice.find("option").each(function(){
                if ($(this).text().trim() == pointerModelType) {
                    var priceVal=$(this).val();
                    $("#selectedBannerPrice").html(priceVal);
                    return;
                }
            });
            var pointerBannerColor = $(".selectModelByColor:.now").attr("color")
            _targetModelType.attr("color", pointerBannerColor);
            var bannerData = $("#bannerConetnt").data("json");
            
            $(".selectModelByType").removeClass("now");
            _targetModelType.addClass("now");
            var color = _targetModelType.attr("color");
            var tag = _targetModelType.attr("tag");
            $("#bannerType").val(tag);
            $("#bannerConetnt").html("");
            for (var index in bannerData) {
                var siginBanner = bannerData[index];
                var siginImg = siginBanner.imgUrl;
                var siginId = siginBanner.id;
                if (siginBanner.tag == tag && siginBanner.color == color) {
                    $("#bannerConetnt").append("<div id='" + siginId + "' class='bannerImgModel'><img src='" + siginImg + "' /></div>");
                    $("#" + siginId).data("banner", siginBanner);
                    $(".colorBox").show();
                    $("#chooseColorDiv").show();
                }
                else 
                    if (tag == "9" && siginBanner.tag == tag) {
                        $("#bannerConetnt").append("<div id='" + siginId + "' class='bannerImgModel'><img src='" + siginImg + "' /></div><div style='border:0;background:none'>您可以上传个性图片作为您的 Banner；<br />图片大小为320*54。</div>");
                        $("#" + siginId).data("banner", siginBanner);
                        //$(".colorBox").hide();
                        $("#chooseColorDiv").hide();
                    }
               
            }
			 getFree();
            $(".bannerImgModel").hover(function(){
                var _targetImgModel=$(this);
                _targetImgModel.addClass("over");
                _targetImgModel.prepend("<div class='myDiv' name='" + _targetImgModel.attr('id') + "'><a href='javascript:void(0)'>使用</a></div>");
                $(".myDiv").click(function(){
                   $(".spanAdBanner").html( $("#selectedBannerPrice").html());
                    $('#bannerDraftSave').show();
                    var bannerData = $("#" + $(this).attr('name')).data("banner");
                    var pageDemoUrl = bannerData.url;
                    var uis = bannerData.ui;
                    var bannerModelName = bannerData.id;
                    var color = bannerData.color;
                    var bannerType = bannerData.tag;
                    $("#bannerColor").val(color);
                    $("#bannerModelName").val(bannerModelName);
                    $("#bannerGrain").val(bannerData.grain);
                    $("#bannerType").val(bannerType);
                    $.get(pageDemoUrl, function(data){
                        $("#bannerDemo").html(data);
                    })
                    var outKey = "";
                    var outNum = 0;
                    for (var i in uis) {
                        var ui = uis[i];
                        if (ui.uiTag == "out") {
                            $("#" + ui.uiId).attr("contentEditable", true);
                            $("#" + ui.uiId).addClass("bannerOut");
                            outNum++;
                            outKey += "<a href='javascript:void(0)' class='outKey' tag='" + ui.uiId + "'>第" + outNum + "帧文字</a>";
                            onpaste = "sayHi()"
                        }
                        if (ui.uiTag == "img") {
                            $("#" + ui.uiId).html('点击上传图片');
                            $("#" + ui.uiId).addClass("bannerImage");
                        }
                        if (ui.uiTag == "imgBg") {
                            $("#" + ui.uiId).append('<div class="c" style="padding-top:16px">点击上传图片</div>');
                            $("#" + ui.uiId).addClass("bannerImage");
                        }
                        $("#imgSizeSpan").html(ui.size);
                    }
                    
                    if (outNum > 1) {
                        $("#outKey").html(outKey);
                    }
                    else {
                        $("#outKey").html("");
                    }
                    var $outKey = $(".outKey");
                    var currKey = $outKey.first().addClass("now");
                    var currIndex = 0;
                    $(".bannerImage").click(function(){
                        $("#uploadImgDiv").hide();
                        $("#bannerImageData").attr("src","");
                        $("#bannerImgId").val("");
                        $("#uploadImgBannerButtonDiv").hide();
                       showBg('#uploadBannerImgDiv');
                       if($('#upLoadBannerImageUploader').size()==0){
                                initBannerFileUpload();
                       }
                        $("#uploadBannerImgDiv").data("uiid", $(this).attr("id"));
                    });
                    $outKey.click(function(){
                        var margin_left = $(".bannerOut:first").css("margin-left");
                        var imgWidth = $(".bannerOut:first").prev().css("width");
                        imgWidth = imgWidth.substring(0, imgWidth.length - 2);
                        margin_left = margin_left.substring(0, margin_left.length - 2);
                        if (!$(this).is(".now")) {
                            var isIE6=$.browser.msie && ($.browser.version == "6.0") && !$.support.style;
                            if (currIndex == 0) {
                                var left = Number(margin_left * 3) + Number(imgWidth);
                                if(!isIE6){
                                $(".bannerOut:eq(1)").css("margin-left", left);
                                }
                            }
                            var outUiId = $(this).attr("tag");
                            if (currIndex == 2) {
                                if (isIE6) {
                                    setTimeout(function(){
                                     $(".bannerOut:eq(1)").hide();
                                     if($(".bannerOut:eq(1)").text().length<10){
                                       $(".bannerOut:eq(1)").text($(".bannerOut:eq(1)").text()+"          ");
                                     }
                                     if($(".bannerOut:eq(0)").text().length<10){
                                       $(".bannerOut:eq(0)").text($(".bannerOut:eq(0)").text()+"          ");
                                     }
                                     $(".bannerOut:first").show();
                                     },0);
                                     $(".bannerOut:eq(0)").height();
                                     $(".bannerOut:eq(1)").height();
                                }
                                else {
                                    $(".bannerOut:eq(1)").animate({
                                        top: 0
                                    }, 1000);
                                    $("#" + outUiId).animate({
                                        top: 0
                                    }, 1000);
                                }
                                    currIndex = 1;
                            }
                            else {
                                if (isIE6) {
                                     setTimeout(function(){
                                     $(".bannerOut:first").hide();
                                     if($(".bannerOut:eq(0)").text().length<10){
                                       $(".bannerOut:eq(0)").text($(".bannerOut:eq(0)").text()+"          ");
                                     }
                                     if($(".bannerOut:eq(1)").text().length<10){
                                       $(".bannerOut:eq(1)").text($(".bannerOut:eq(1)").text()+"          ");
                                     }
                                    $(".bannerOut:eq(1)").show();
                                    },0);
                                    $(".bannerOut:eq(0)").height();
                                     $(".bannerOut:eq(1)").height();
                                }
                                else {
                                    $(".bannerOut:first").animate({
                                        top: -54
                                    }, 1000);
                                    $("#" + outUiId).animate({
                                        top: -54
                                    }, 1000);
                                }
                                    currIndex = 2;
                            }
                            $outKey.removeClass("now");
                            $(this).addClass("now");
                        }
                    });
                    if (bannerType == 5) {
                        $("#bannerDesc").show();
                        $("#bannerDesc").html("您可以输入40个中文字。");
                        $("#imgPatternSpan").html("jpeg,jpg,png。");
                        $("#outKey").hide();
                    }
                    if (bannerType == 6) {
                        $("#bannerDesc").show();
                        $("#bannerDesc").html("您可以输入32个中文字。");
                        $("#imgPatternSpan").html("jpeg,jpg,png。");
                        $("#outKey").hide();
                    }
                    if (bannerType == 7) {
                        $("#bannerDesc").show();
                        $("#bannerDesc").html("您可以输入18个中文字。");
                        $("#imgPatternSpan").html("jpeg,jpg,png。");
                        $("#outKey").hide();
                    }
                    if (bannerType == 8) {
                        $("#bannerDesc").show();
                        $("#bannerDesc").html("您每帧可以输入32个中文字。");
                        $("#imgPatternSpan").html("jpeg,jpg,png。");
                        $("#outKey").show();
                    }
                    if (bannerType == 9) {
                      $("#bannerDesc").hide();
                        $("#imgPatternSpan").html("jpeg,jpg,png,gif。");
                        $("#outKey").hide();
                    }
                     $("#fullbg").hide();
       				 $('#bannerModel').fadeOut();
                    $(".trBannerDemo").show();
                    
                    //解决IE6 下 无airad图标样式的问题
                     if($.browser.msie&&($.browser.version == "6.0")&&!$.support.style){
                         setTimeout(function(){
                             $(".bannerLogo").removeClass().addClass("bannerLogo");
                         },0);
                    }
                     getFree();
                });
            }, function(){
                $(".bannerImgModel").removeClass("over");
                $(".myDiv").remove();
            });
        })
        var currentType = $("#bannerType").val();
        var isBannerTypeClick = false;
        $(".selectModelByType").each(function(){
            if ($(this).attr("tag") == currentType) {
                $(this).click();
                isBannerTypeClick = true;
            }
        })
        if (!isBannerTypeClick) {
            $(".selectModelByType:first").click();
        }
    });
}
$(".selectModelByColor").click(function(){
    $(".selectModelByColor").removeClass("now");
    var target=$(this);
    target.addClass("now");
    $("#bannerColor").val(target.attr("color"));
    $(".selectModelByType:.now").click();
})
$.initAdModelPage = function(uiId){
    $.getJSON("/html/model/ad.json", function(ads){
        var domSeg = [];
        for (var i in ads) {
            var ad = ads[i];
            var $uiId = ad.id; //获取banner模板的id
            var $imgUri = ad.imgUrl;
            var $imgName = ad.name;
            //$.get($(this).find("imgUrl").text(),function(source){
            //	$("#bannerConetnt").append("<img src='"+$imguri+"' />");
            // })
            var adSeg = '<a class="demoType" href="javascript:void(0)" rev="1" id="' + $uiId + '">' +
            '<span class="pBoxCon">' +
            $imgName +
            '<span class="sml"><sup>&yen;</sup></span></span>' +
            '<img alt="图片加载失败" src="' +
            $imgUri +
            '">' +
            '</a>';
            $(uiId).append(adSeg);
            $("#" + $uiId).data("ad", ads[i]);
            
        }
    });
}
$.loadAdModelPage = function(uiId){
    var jsonData = $(uiId).data("ad");
}



