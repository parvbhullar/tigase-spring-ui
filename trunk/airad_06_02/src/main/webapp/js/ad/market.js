/**
 * @author Administrator
 */
/**
 * 初始化initMarket
 * @param {Object} uiId
 */
function initMarket(uiId){
    /**
     * begin word
     */
     var $marKetTr = $("." + uiId + "MarketTr");
     $marKetTr.find(".marketName").attr("id",uiId +"MarketName");
     $marKetTr.find(".marketAndroidUrl").attr("id",uiId +"MarketAndroidUrl");
     $marKetTr.find(".marketIphoneUrl").attr("id",uiId +"MarketIphoneUrl");
     $marKetTr.find(".uploadMarketImg").attr("id",uiId +"UploadMarketImg");
     $marKetTr.find(".marketImgId").attr("id",uiId +"MarketImgId");
     $marKetTr.find(".MarketImg").attr("id",uiId +"MarketImg");
     $marKetTr.find(".marketId").attr("id",uiId +"MarketId");
     $marKetTr.find(".marketIponeId").attr("id",uiId +"MarketIponeId");
     $marKetTr.find(".marketAndroidId").attr("id",uiId +"MarketAndroidId"); 
     if($('#showType1UploadMarketImgUploader').size()==0){
         upLoadFileInit("#" + uiId + "UploadMarketImg", "/fileuploading.do", "*.jpeg;*.jpg;*.png;", false, 1, '*.jpeg;*.jpg;*.png;', 1, 100*1024, function(event, data){
            $(("#" + uiId + "uploadMarketImgControl")).show();
            $(("#" + uiId + "UploadMarketImg")).uploadifySettings('scriptData', {
                'action': 'upload',
                'sourceType': '5',
                'uiId': uiId,
                'imgWidth': '150',
                'imgHeight': '150'
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
            $("#" + data[0] + "MarketImgId").val(data[1]);
             $("#" + data[0] + "MarketImgId").prev().attr("src", "/fileuploading.do?action=loadSessionCropImg&fileName=" + data[1] + "&t=" + Math.random()).show();
             $("#" + data[0] + "MarketImg").show();
           //  alert('上传成功2');
             $("#adIsModifed").val("1");
        }, 
        function(event, data) {
            var _swfId = "#" + uiId + "UploadMarketImgUploader";
            $(_swfId).next('img').remove();
            return false;
            
        }, function(event, ID, fileObj, data) {
             var _swfId = "#" + uiId + "UploadMarketImgUploader";
            var _jswf = $(_swfId);
            if (_jswf.next('img').size() == 0) {
                _jswf.after('<img src="/images/ico_loading.gif" />');
            }
            return false;
        }, null);
     }
     $marKetTr.show();
}