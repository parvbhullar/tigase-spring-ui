//创建div*n
map = new Map();
function createChildPage(num) {
    $.getScript("/js/util/markermanager.js");
    oldNum = $(".con22").size();
    if (Number(num) > 1) {
        num = $("#adChildNum").val();
    }
    if (oldNum < Number(num)) {
        for (var i = oldNum; i < num; i++) {
            var objLi = $(".ulFree").find("li:eq(4)");
            if (objLi.next().html() == null) {
                objLi.before("<li class='spanAdRich' value='99'><span class='fr'><sup>&yen;</sup><span class='content singlefree'>0</span></span>广告内容</li>");
            }
            else {
                $(".ulFree").find(".spanAdRich:last").after("<li class='spanAdRich' value='99'><span class='fr'><sup>&yen;</sup><span class='content singlefree'>0</span></span>子页面" + (Number(i) + 1) + "</li>");
            }
            // 复制模版demoCon22
            if (i == 0) {
                $(".demoCon22").clone().insertAfter(".demoCon22");
                $(".demoCon22:last").addClass("con22");
            }
            else {
                $(".demoCon22:first").clone().insertAfter(".con22:last");
                $(".demoCon22:last").addClass("con22");
            }
            //--------end  复制
            //初始化模版
            $con22 = $(".con22:last");
         //   $con22.find(".pnum").html(i + 1);
            $showType = $con22.find(".showType");
            $showType.attr("name", "showType" + (i + 1));
            $showType.attr("id", "showType" + (i + 1));
            $richId = $con22.find(".richId");
            $richId.attr("id", "richId" + (i + 1));
            $title = $con22.find(".richMediaTitle");
            $title.attr("id", "richMediaTitle" + (i + 1));//改变富媒体标题的值
            $title.attr("name", "richMediaTitle" + (i + 1));//改变富媒体标题的值
            $con22.find(".btn11").attr("rel", "showType" + (i + 1));//选择模版时设置第几个按钮响应
            $con22.find(".closeRich").attr("id", "close" + (i + 1));//
            //预览
            $con22.find(".previewRichShow").click(function() {
                var rowNum = $(this).parent().nextAll(".showType").val();
                if (rowNum == "") {
                    alert("请先选择模版");
                    return;
                }
                if (rowNum == 1) {
                    wordNum = ($(this).prevAll("button").find("span").html());
                //    alert($("#previewPage").attr("src"));
                    $("#previewPage").attr("src", "/html/preview/word/word_in.html?var=index:" + wordNum + ";type:1");
                   // showBg('#preview');
                }
                if (rowNum == 2) {
                    picWordNum = ($(this).prevAll("button").find("span").html());
                    picPlace = $("#showType" + (Number(picWordNum) + 1) + "FormatType").val();//图文方式
                    imgId = $(("#" + "showType" + (Number(picWordNum) + 1) + "ImgId")).val();//图文中的图片
                    if (picPlace == 1) {
                        $("#previewPage").attr("src", "/html/preview/word/word_pic_top_in.html?var=index:" + picWordNum + ";type:2");
                    }
                    if (picPlace == 2) {
                        $("#previewPage").attr("src", "/html/preview/word/word_pic_left_in.html?var=index:" + picWordNum + ";type:2");
                    }
                    if (picPlace == 3) {
                        $("#previewPage").attr("src", "/html/preview/word/word_pic_right_in.html?var=index:" + picWordNum + ";type:2");
                    }
                 //   showBg('#preview');
                }
                
                if (rowNum == 3) {
                    picUiNum = $(this).prevAll("button").find("span").html();
                    $("#previewPage").attr("src", "/html/preview/pic/pic_in.html?var=index:" + picUiNum + ";type:3");
                }
                if (rowNum == 4) {
                    mapNum = $(this).prevAll("button").find("span").html();
                    $("#previewPage").attr("src", "/html/preview/map/map_in.html?var=index:" + mapNum + ";type:4");
                }
                if (rowNum == 5) {
                    var num = $(this).prevAll("button").find("span").html();
                    $("#previewPage").attr("src", "/html/preview/taobao/taobao_in.html?var=index:" + num + ";type:5");
                }
                if (rowNum == 6) {
                
                    var taobaoNum = $(this).prevAll("button").find("span").html();
                    $("#previewPage").attr("src", "/html/preview/taobao/taobao_page_in.html?var=index:" + taobaoNum + ";type:6");
                }
                if (rowNum == 7||rowNum == 8) {
                
                    var markerNum = $(this).prevAll("button").find("span").html();
                    $("#previewPage").attr("src", "/html/preview/market/appstore_in.html?var=index:" + markerNum + ";type:"+rowNum);
                }
                if (rowNum == 9) {
                    var markerNum = $(this).prevAll("button").find("span").html();
                    $("#previewPage").attr("src", "/html/preview/market/appstore_in.html?var=index:" + markerNum + ";type:"+rowNum);
                }
                
                showBgNoScroll('#preview');
            });
            
            //富媒体展示收起
            $close = $con22.find(".closeRich");
            $close.click(function() {
              return;
                    var index = $(this).attr("id").substring(5);
                if($("#showType"+index).val()==4){
                    var mapContent = map.get("showType"+index+"Map")
                    var mapValue = mapContent.getCenter().lat()+","+mapContent.getCenter().lng();
                    $("#showType"+index+"MapCenter").val(mapValue);
                }
                $(this).prev().show();//展示显示
                $(this).prev().prev().prev().hide();
                $(this).prev().prev().hide();
                $(this).hide();
                if(isFrist){
                    $(this).parent().parent().next().slideUp();
                    isFrist=false;
                }else{
                    $(this).parent().parent().next().hide();
                }
            })
            $exhidit = $con22.find(".exhibitRich");
            $exhidit.click(function() {
                $(this).next().show();//关闭显示
                $(this).prev().prev().show();
                $(this).prev().show();
                $(this).hide();
                $(this).parent().parent().next().show();
            })
            //选择模版
            $(".btn11").bind("click", function() {
                //  修改使 单页面才显示淘宝页 start ph
                if ($("#pic02").hasClass("now")) {
                    $("#popDiv2").css("width", "575px");
                        $(".myHidDemo").show();
                }
                else {
                    $("#popDiv2").css("width", "490px");
                      setTimeout(function(){//修改 使 IE6兼容隐藏此对象
                        $(".myHidDemo").hide();
                      },0);
                }
                showBg('#popDiv2');
                $("#showTypev").val($(this).attr("rel"));//把当前页面id放进指针中
            });
            var type = $("#showTypev").val();
            //-------结束初始化
            /**
             * begin word
             */
            $con22.find(".trWord").attr("id", "showType" + (i + 1) + "WordTr");//改变tr值
            //-------end word
            /**
             * begin picword
             */
            $con22.find(".trPiceWord").attr("id", "showType" + (i + 1) + "PicWordTr");//改变tr值
            //-------end picword
            
            /**
             * begin photos
             */
            $con22.find(".trPice").attr("id", "showType" + (i + 1) + "PicTr");//改变tr值
            //初始化
            //-------end photos
            /**
             * begin map
             */
            $con22.find(".trMap").attr("id", "showType" + (i + 1) + "MapTr");//改变tr值
            //--------end map
            /**
             * begin taobao
             */
            $con22.find(".trTaobaoDemo").addClass("showType" + (i + 1) + "trTaobaoDemo");
            
            //--------end map
            /**
             * begin taobao2
             */
            $con22.find(".trTaobao2Demo").addClass("showType" + (i + 1) + "Taobao2Tr");
            
            //--------end taobao
            /**
             * begin market
             */
            $con22.find(".trMarket").addClass("showType" + (i + 1) + "MarketTr");
            
            //--------end market
            /**
             * begin callphone
             */
            //修改id
            $con22.find(".trCallPhone").addClass("showType" + (i + 1) + "CallPhone");
            
            //--------end callphone
            /**
             * 保存草稿
             */
            $saveDarft = $con22.find(".pageDraftSave");
            $saveDarft.find("span").html(i);
            //保存草稿
            $saveDarft.bind("click", function() {
                $.ajaxSetup({
                    async: false
                });
                if ($("#adId").val() == "" || $("#adName").val().trim() == "") {
                    $("#adDraftSave").click();
                      if($("#submitStatus").val()=="1"){
                        return false;
                      }
                }
              
                //alert('pagesave before');
                var bs = checkPage4Save(this);
                if (!bs) {
                    return false;
                }
                //修改了广告
                
               // alert("richDraftSave 修改了广告");
                $.post("ad.do?action=richDraftSave", bs, function(data) {
                    if (data.flag == 1) {
                        if (data.showType == '4' && $("#showType" + data.uiId + "MapId").val().length == 0) {
                            $("#showType" + data.uiId + "MapId").val(data.relId);
                        }
                        if ($("#draftStatus").val()!="1"&&$("#submitStatus").val() !="1") {
                            alert("修改成功");
                        }
                    }
                    
                    if (data.richId != null && data.richId != "0") {
                        $(("#richId" + data.uiId)).val(data.richId);
                        if (data.showType == 4) {
                            $("#showType" + data.uiId + "MapId").val(data.relId);
                        }
                        if (data.showType == 6) {
                            $("#showType" + data.uiId + "TaobaoId").val(data.relId);
                        }
                        if (data.showType == 7||data.showType == 8) {
                            $("#showType" + data.uiId + "MarketId").val(data.relId);
                        }
                       
                        if (data.showType == 9 ) {
                          //  alert('hello world');
                            $("#showType" + data.uiId + "CallPhoneId").val(data.relId);
                        }
                        
                        if ($("#draftStatus").val()!="1"&&$("#submitStatus").val() !="1") {
                            alert("保存成功");
                        }
                    }
                     if ($("#draftStatus").val()!="1" && $("#submitStatus").val() !="1") {
                       $(("#close" + data.uiId)).click();
                     }
                }, 'json');
            });
        }
    }
    else {
        var n = Number(num);
        $con22 = $(".con22").eq(n - 1);
        $richli = $(".spanAdRich").eq(n - 1);
        delRichId = "";
        $con22.nextAll(".con22").each(function(i) {
            if (i == 0) {
                delRichId += $(this).find("h4").find(".richId").val();
            }
            else {
                delRichId += ",";
                delRichId += $(this).find("h4").find(".richId").val();
            }
        });
        $("#delRichId").val(delRichId);//删除的richid
        $richli.nextAll(".spanAdRich").remove();//删除摘要
        $con22.nextAll(".con22").each(function() {
            $(this).remove();
            $.post("/ad.do?action=delRich&richId=" + $(this).find(".richId").val());
        })//删除子页面
    }
     
}


try {
 //监听预览
            $("#previewPage").load(function(){
              
                var previewPage=$("#previewPage");
                var sr = previewPage.attr("src");//获取url
                var data = sr.substring(sr.search("=") + 1, sr.length);//获取传递进来的数据
                var  index = data.getAttribute("index");
                var  type = data.getAttribute("type");
                if (type == '1') {
                  
                    if ($("#background").val().trim().length > 0) {
                        previewPage.contents().find("#box").css("background-image", "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + $("#background").val() + "&t=" + Math.random() + "')");
                    }
                    wordPh = "";
                    if ($("#showType" + (Number(index) + 1) + "WordPhone").val().trim().length > 0) {
                        wordPh = "<p />电话:" + $("#showType" + (Number(index) + 1) + "WordPhone").val();
                    }
                    previewPage.contents().find(".con").html(showBoxArray.get("#showType" + (Number(index) + 1) + "Box").get_html() + wordPh);
                }
               else  if (type == '2') {
                    picPlace = $("#showType" + (Number(index) + 1) + "FormatType").val();//图文方式
                    imgId = $(("#" + "showType" + (Number(index) + 1) + "ImgId")).val();//图文中的图片
                    if ($("#background").val().trim().length > 0) {
                        $(this).contents().find(".main").css("background-image", "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + $("#background").val() + "&t=" + Math.random() + "')");
                    }
                    if(imgId!="")
                    {
                      $(this).contents().find("#ioc").hide()
                      $(this).contents().find("img").attr("src", "/fileuploading.do?action=download&fileId=" + imgId + '&t=' + Math.random()).show();
                    }else{
                      $(this).contents().find("#ioc").show()
                      $(this).contents().find("img").hide();
                    }
                    wordPicPh = "";
                    if ($("#showType" + (Number(index) + 1) + "WordPicPhone").val().trim().length > 0) {
                        wordPicPh = "<p />电话:" + $("#showType" + (Number(index) + 1) + "WordPicPhone").val();
                    }
                    $(this).contents().find("#content").html(showBoxArray.get("#showType" + (Number(index) + 1) + "PicBox").get_html() + wordPicPh);
                }
             else if (type == '3') {
                    if ($("#background").val().trim().length > 0) {
                        $(this).contents().find("#box").css("background-image", "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + $("#background").val() + "&t=" + Math.random() + "')");
                    }
                    $myIfram = $(this).contents();
                    html = '';
                    picNum = $(".showType" + (Number($.trim(index)) + 1) + "imgId").size() - 1;
                    var relNum = 1;
                    var flag = 'left';
                    $(".showType" + (Number($.trim(index)) + 1) + "imgId").each(function(){
                        if ($(this).val().trim() != "") {
                            html += '<div class="fl" style="margin-right:50px">';
                            html += '<img class="img"  src="/fileuploading.do?action=download&fileId=' + $(this).val() + '&t=' + Math.random() + '" alt=" " width="268" height="418" /> ';
                            html += '</div>';
                        }
                    });
                    $myIfram.find("#picBox").html(html);
                    var wx = 0;
                    if (picNum > 1) {
                        $myIfram.find("#picBox").everyTime('1.5s', function(i){
                            $(this).animate({
                                left: wx + "px"
                            }, 1000);
                            if (flag == 'right') {
                                relNum--;
                                wx += 320;
                            }
                            if (flag == 'left') {
                                relNum++;
                                wx -= 320;
                            }
                            if (relNum == picNum) {
                                flag = 'right'
                            }
                            if (relNum == 1) {
                                flag = 'left'
                            }
                        });
                    }
                }
              else if (type == '4') {
                    if ($("#background").val().trim().length > 0) {
                        $(this).contents().find("#box").css("background-image", "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + $("#background").val() + "&t=" + Math.random() + "')");
                    }
                    uiMapId = "showType" + (Number($.trim(index)) + 1) + "Map";
                    var $imgId = ".showType" + (Number($.trim(index)) + 1) + "imgId";
                    if (map.get(uiMapId) != null) {
                        //lat = map.get(uiMapId).getCenter().lat();
                        //lng = map.get(uiMapId).getCenter().lng();
                        var mapContent = $("#showType" + (Number($.trim(index)) + 1)+"MapCenter").val();
                        if(mapContent==""){
                            mapContent = map.get(uiMapId).getCenter().lat()+","+map.get(uiMapId).getCenter().lng();
                        }
                        zoom = map.get(uiMapId).getZoom();
                        mak = "";
                        if (markers.get(uiMapId) != null) {
                            for (var i = 0; i < markers.get(uiMapId).length; i++) {
                                if (markers.get(uiMapId)[i] != null) {
                                    mak += "&markers=" + markers.get(uiMapId)[i].getLatLng().lat() + "," + markers.get(uiMapId)[i].getLatLng().lng();
                                }
                            }
                        }
                    }
                    url = "http://maps.google.com/maps/api/staticmap?center=" + mapContent + "&zoom=" + zoom + "&size=260x400" + mak + "&sensor=false"
                    $(this).contents().find("#mapImg").attr("src", url);
                }
            else if (type == '5') {
                    uiTaoId = "showType" + (Number($.trim(index)) + 1) + "TaobaoTr";
                    if ($("#background").val().trim().length > 0) {
                        $(this).contents().find("#pageDiv").css("background-image", "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + $("#background").val() + "&t=" + Math.random() + "')");
                    }
                    html = "";
                    $myIfram = $(this).contents();
                    $(("." + uiTaoId)).find(".numIID").each(function(i){
                        if (i == $(("." + uiTaoId)).find(".numIID").size() - 1) {
                            return;
                        }
                        html += '<div class="single">';
                        html += '<div class="picTBBox fl"><a href="javascript:alert(\'会转入淘宝产品wap页面,以实际为准\')" target="_self"><img src="' + $(this).next().next().val() + '" alt="查看详情" width="100" height="100" /></a></div>';
                        html += '<div class="detail fl">';
                        html += $(this).next().val();
                        html += '<div class="btnYBox">';
                        html += '<a href="javascript:alert(\'在手机上会转入手机淘宝产品页面,以实际为准\')" class="btnY fl"><span>去淘宝查看详细</span></a>';
                        html += '</div>';
                        html += '</div>';
                        html += '</div>';
                    });
                    $myIfram.find("#navigationDiv").html(html);
                }
                else if(type=='6'){
                    var taobaoUrl = $("#showType" + (Number($.trim(index)) + 1) + "TaobaoUrl").val();
                    var $myIfram = $(this).contents();
                    if(taobaoUrl.match(/(^([\w-]+\.(taobao|tmall).com\/)+([\w- ./?%&=]*)?)/)==null){
                            alert("网址只能输入淘宝网址");
                             return false;
                     }
                    $myIfram.find("#box>.main>#taobaoDiv").attr("src","http://"+taobaoUrl);
                }
                 else if(type=='7'||type=='8'){
                    var $myIfram = $(this).contents();
                    if (type=='7') {
                     
                        $myIfram.find("#phoneIco").removeClass("ipBtn");
                        $myIfram.find("#bj").removeClass("conBg");
                        $myIfram.find("#bj").addClass("androidBg");
                        $myIfram.find("#phoneIco").addClass("androidBtn");
                    }else{
                        $myIfram.find("#phoneIco").removeClass("ipBtn");
                        $myIfram.find("#bj").removeClass("ipBg");
                        $myIfram.find("#bj").addClass("conBg");
                        $myIfram.find("#phoneIco").addClass("ipBtn");
                    }
                     $(this).contents().find("#main").css("background-image", "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + $("#background").val() + "&t=" + Math.random() + "')");
                    $myIfram.find("#marketImg").attr("src","/fileuploading.do?action=loadSessionCropImg&fileName=" + $("#showType" + (Number($.trim(index)) + 1)+"MarketImgId").val() + "&t=" + Math.random())
                    $myIfram.find("#btName").html($("#showType" + (Number($.trim(index)) + 1) + "MarketName").val());
                }
            else if (type == 'navigation') {
                    num = $("#adChildNum").val();
                    html = "";
                    for (var i = 0; i < num; i++) {
                        html += '<div class="btn">您的导航</div>';
                    }
                    $("#previewPage").contents().find(".btnBox").html(html);
                    $("#previewPage").contents().find(".main").css("background-image", "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + $("#background").val() + "&t=" + Math.random() + "')");
                }
           if(type != 'navigation'){
                     //showBg('#preview');
           }
         });
         } catch (e) { }
         
$(document).ready(function() {
    //绑定子页面事件 先把原先的页面都删除从新创建页面
    $("#adChildNum").change(function() {
        $pn = $("#adChildNum").val();
        createChildPage(Number($pn));
        $(".con22").show();
    });
    
});
function cleanUrl(oj,head) {
    var url = $(oj);
    url.val(url.val().replace(head, ""));
}
