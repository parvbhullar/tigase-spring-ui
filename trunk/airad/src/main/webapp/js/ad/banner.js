function initBannerFileUpload(pattern) {
    if (!pattern) {
        pattern = "*.jpeg;*.jpg;*.png;*.gif;";
    }
    upLoadFileInit("#upLoadBannerImage", "/fileuploading.do", pattern, false, 1, pattern, 1, 1024 * 100, function(event, data) {
        var a = $("#imgSizeSpan").html();
        var i = a.indexOf("*");
        var width = a.substring(0, i);
        var height = a.substring(i + 1);
        $('#upLoadBannerImage').uploadifySettings('scriptData', {
            'action': 'upload',
            'sourceType': '5',
            'imgWidth': width,
            'imgHeight': height
        });
        return false;
    }, null, function(event, queueID, fileObj, response) {
        if (response.substr(0, 1) == "/") {
            response = response.substr(1);
        }
        if (response.substr(0, 1) == "\\") {
            response = response.substr(1);
        }
        
        $("#bannerImgId").val(response);
        var $bannerImageData = $("#bannerImageData");
        $("#bannerIconCon").val(response)
        $bannerImageData.attr("src", "/fileuploading.do?action=loadSessionCropImg&fileName=" + response + "&t=" + Math.random());
        var sizeSpan = $("#imgSizeSpan").html();
        var index = sizeSpan.indexOf("*");
        var widthNum = sizeSpan.substring(0, index);
        var heightNum = sizeSpan.substring(index + 1);
        $bannerImageData.width(Number(widthNum));
        $bannerImageData.height(Number(heightNum));
        $("#uploadImgBannerButtonDiv").show();
        $("#uploadImgDiv").show();
    }, function(event, data) {
        var _swfId = "#upLoadBannerImageUploader";
        $(_swfId).next('img').remove();
        return false;
        
    }, function(event, ID, fileObj, data) {
        var _swfId = "#upLoadBannerImageUploader";
        var _jswf = $(_swfId);
        if (_jswf.next('img').size() == 0) {
            _jswf.after('<img src="/images/ico_loading.gif" />');
        }
        return false;
    }, null);
}


$(document).ready(function() {
    /**
     * 根据banner类型改变模版
     *
     */
    $.initBannerModel("#bannerConetnt");
    $("#bannerClose").click(function() {
        $("#fullbg").hide();
        $('#bannerModel').fadeOut();
    });
    $("#bennerAffirm").click(function() {
        $("#fullbg").hide();
        $('#bannerModel').fadeOut();
        $.designBanner("#bannerDesign");
    });
    //展开/收起banner
    $("#exhibitBanner").click(function() {
        //$("#bannerTable").show();
        
        $("#bannerDraftSave").show()
        $("#bannerDraftSave").next().show()
        $("#exhibitBanner").hide();
        $("#closeBanner").show();
        $("#bannerTable").show();
    });
    $("#closeBanner").click(function() {
        // $("#bannerTable").slideUp();
        $("#bannerDraftSave").next().hide()
        $("#bannerDraftSave").hide()
        $("#closeBanner").hide();
        $("#exhibitBanner").show();
        $("#bannerTable").hide();
    });
});
