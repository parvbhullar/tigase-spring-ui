var showBoxArray = new Map();//所有富文本对象
function changeBox(obj, htmlText){
    var d = showBoxArray.get(obj);
    if (d != null) {
        d.remove();
    }
    $(obj).css("width", 400).css("height", 120)
    var $html = $(obj).htmlbox();
    
    $html.idir("/mice/htmlBox/images/")
    
    $html.button("bold").button("italic").button("underline").button("left").button("center").button("right").button("justify").button("fontsize").button("fontcolor").button("hyperlink").init();
    showBoxArray.put(obj, $html);
    
}

function getBoxData(obj){
    $htmlBox = $(obj);
    vr = $htmlBox.htmlbox().get_html();
    //alert(vr)
}

function removeAllBox(){
    for (var i = 0; i < showBoxArray.arr.length; i++) {
        showBoxArray.arr[i].value.remove();//删除box控件
        showBoxArray = new Map();//初始化
    }
}

/**
 * 初始化boxhtml
 * @param {Object} uiId
 */
function initBox(uiId, htmlText){
    /**
     * begin word
     */
    var $htmlBoxTr = $("#" + uiId + "WordTr");
    if ($htmlBoxTr.find(("#" + uiId + "Box")).val() != null) {
        $htmlBoxTr.show();
        return;
    }
    var $htmlBox = $htmlBoxTr.find(".htmlBox");
    var $phone = $htmlBoxTr.find(".wordPhone");
    $phone.attr("id", uiId + "WordPhone");
    $htmlBox.attr("id", uiId + "Box");//改变box值
    changeBox("#" + uiId + "Box", htmlText)//初始化付文本编辑框
    $htmlBoxTr.show();
    //-------end word
}

/**
 * 初始化picboxhtml
 * @param {Object} uiId
 */
function initPicBox(uiId){

    /**
     * begin word
     */
    var $htmlBoxTr = $("#" + uiId + "PicWordTr");
    var $htmlFormat = $htmlBoxTr.find(".formatType");//初始化类型选择
    if ($htmlBoxTr.find(("#" + uiId + "FormatType")).val() != null) {
        $htmlBoxTr.show();
        return;
    }
    
    $htmlFormat.attr("id", uiId + "FormatType");
    $htmlBoxTr.find(".imgId").attr("id", uiId + "ImgId");
    var $htmlBox = $htmlBoxTr.find(".picHtmlBox");
    $phone = $htmlBoxTr.find(".wordPicPhone");
    $phone.attr("id", uiId + "WordPicPhone");
    $htmlBox.attr("id", uiId + "PicBox");//改变box值
    changeBox("#" + uiId + "PicBox")//初始化付文本编辑框
    upLoadFileInit();//初始化文本框
    //上传图片
    //展示文件
    $htmlBoxTr.find(".uploadWordPic").attr("id", uiId + "UploadWordPic");
    $htmlBoxTr.find(".uploadWordPicImg").attr("id", uiId + "UploadWordPicImg");
    upLoadFileInit("#" + uiId + "UploadWordPicImg", "/fileuploading.do", "*.jpeg;*.jpg;*.png;", false, 1, '*.jpeg;*.jpg;*.png;', 1, 100*1024, function(event, data){
        $(("#" + uiId + "upLoadWordPicControl")).show();
        $(("#" + uiId + "UploadWordPicImg")).uploadifySettings('scriptData', {
            'action': 'upload',
            'sourceType': '5',
            'uiId': uiId,
            'imgWidth': '120',
            'imgHeight': '120'
        });
        return false;
    }, "", function(vent, queueID, fileObj, response){
        if (response.substr(0, 1) == "/") {
            response = response.substr(1);
        }
        if (response.substr(0, 1) == "\\") {
            response = response.substr(1);
        }
        var data = response.split(",");
        $("#" + data[0] + "ImgId").val(data[1]);
         $("#" + data[0] + "ImgId").prev().attr("src", "/fileuploading.do?action=loadSessionCropImg&fileName=" + data[1] + "&t=" + Math.random()).show();
    }, 
    function(event, data) {
        var _swfId = "#" + uiId + "UploadWordPicImgUploader";
        $(_swfId).next('img').remove();
        return false;
        
    }, function(event, ID, fileObj, data) {
         var _swfId = "#" + uiId + "UploadWordPicImgUploader";
        var _jswf = $(_swfId);
        if (_jswf.next('img').size() == 0) {
            _jswf.after('<img src="/images/ico_loading.gif" />');
        }
        return false;
    }, null);
    $htmlBoxTr.show();
    //-------end word
}


