/**
 * 相册事件处理
 *
 */
/**
 *
 * @param {Object} uiId
 */
function initPhotos(uiId) {
    /**
     * begin photos
     */
    $picDemo = $("#" + uiId + "PicTr");
    if ($picDemo.find(("#" + uiId + "PicId")).val() != null) {
        $picDemo.show();
        return;
    }
    //upload
    //展示文件
    $picDemo.find(".uploadPic").attr("id", uiId + "UploadPic");
    //上传图片
    $picDemo.find(".uploadPicImg").attr("id", uiId + "UploadPicImg");
    ;
    //展示图片
    $picDemo.find(".picImg").addClass(uiId + "picImg");
    $picDemo.find(".imgId").addClass(uiId + "imgId");
    $box = $picDemo.find(".norPicBox");
    $box.addClass(uiId + "norPicBox");
    //获取文件路径
    $picDemo.find(".uploadPicPath").attr("id", uiId + "UploadPicPath");
    //获取图片id
    $picDemo.find(".picId").attr("id", uiId + "PicId");
    $picDemo.show();//显示wordTr
    upLoadFileInit("#" + uiId + "UploadPicImg", "/fileuploading.do", "*.jpeg;*.jpg;*.png;", true, 5, '*.jpeg;*.jpg;*.png;', 4, 400*1024,
    function(event, data) {
        $(("#" + uiId + "UploadPicImg")).uploadifySettings('scriptData', {
            'action': 'upload',
            'sourceType': '5',
            'uiId': uiId,
            'imgWidth': '270',
            'imgHeight': '420'
        });
        return false;
    }, null, function(event, queueID, fileObj, response) {
        if (response.substr(0, 1) == "/") {
            response = response.substr(1);
        }
        if (response.substr(0, 1) == "\\") {
            response = response.substr(1);
        }
        var data = response.split(",");
        var uiIdObj = $("#" + data[0] + "PicId");
        if (uiIdObj.val() == "") {
            uiIdObj.val(data[1]);
        }
        else {
            uiIdObj.val(uiIdObj.val() + "," + data[1]);
        }
        $box = $(("." + data[0] + "norPicBox:last")).clone().insertAfter(("." + data[0] + "norPicBox:last"));
        $box.show().find(".imgId").val(data[1]);
        $box.find(".delImg").click(function() {
            $(this).parent().remove();
        });
        
        $(("." + data[0] + "picImg:last")).attr("src", "/fileuploading.do?action=loadSessionCropImg&fileName=" + data[1] + "&t=" + Math.random());
        
    }, function(event, data) {
        var _swfId = "#" + uiId + "UploadPicImgUploader";
        $(_swfId).next('img').remove();
        return false;
        
    }, function(event, ID, fileObj, data) {
        var _swfId = "#" + uiId + "UploadPicImgUploader";
        var _jswf = $(_swfId);
        if (_jswf.next('img').size() == 0) {
            _jswf.after('<img src="/images/ico_loading.gif" />');
        }
        return false;
    },
    function(event,data){
      var currentFileSize=$("."+uiId+"imgId").size();
      var querySize=data.fileCount;
        if((currentFileSize+querySize)>6){
          alert("最多只能上传5张图片");
              $("#" + uiId + "UploadPicImg").uploadifyClearQueue();
               return false;
        }else{
            return true;
        }
    }
    );
    
    //--------end photos
}
