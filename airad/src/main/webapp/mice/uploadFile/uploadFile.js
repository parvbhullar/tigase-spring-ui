//=============================================
//初始化上传控件
//米田科技 2011.3.4
//=============================================
//引入图片上传


/**
 * 上传文件初始化
 * @param {Object} obj 对象
 * @param {Object} url ajax url
 * @param {String} ft 设置格式 *.gif;*.jpg;*.pdf;*.doc;*.docx
 * @param {bool} isMore 是否支持多文件 true支持 false不支持
 * @param {int} fildCount
 * @param {Object} ftErr 设置错误文字如 只支持gif,jpg文件
 * @param {Object} maxNum最大上传数 如1
 * @param {Object} maxSize最大上传大小
 * @param {Object} uploadSelectOnce //选择完文件后触发
 * @param {Object} uploadQueueFull//超过上传最大数量时触发
 * @param {Object} uploadComplete//上传成功时触发
 * @param {Object} uploadAllComplete//所有上传成功时触发
 * @param {Object} onProgress//开始上传时出发
 * @param {Object} onSelectOnce // 文件选择完毕后触发
 */
function upLoadFileInit(obj, url, ft, isMore, fildCount, ftErr, maxNum, maxSize, onSelect, onQueueFull, onComplete, onAllComplete, onProgress, onSelectOnce) {
    if (null==onSelectOnce){
        onSelectOnce=function(){
            return true;
        }
    }
    $(obj).uploadify({
        'uploader': '/mice/uploadFile/uploadify.swf?t=' + (new Date()).getTime(),
        'method': 'GET',
        'script': url,
        'fileDataName': 'fileToUpload',
        'sizeLimit': maxSize, //单个文件大小
        'simUploadLimit': 1,
        'fileDesc': ftErr,
        'fileExt': ft,
        'queueSizeLimit': fildCount,
        'buttonImg': '/images/ico_upload.gif',
        'cancelImg': '/mice/uploadFile/cancel.png',
        'height': 16,
        'auto': true,
        'wmode': 'transparent',
        'multi': isMore,
        'onQueueFull': onQueueFull,
        'onComplete': onComplete,
        'onAllComplete': onAllComplete,
        'onSelectOnce':onSelectOnce,
        'onSelect': onSelect,
        'onProgress': onProgress,
        'onError': function(event, queueId, fileObj, errorObj) {
          if(errorObj.type=='File Size'){
           //alert( "文件"+fileObj.name+"大小超过"+errorObj.info/1024+"KB");
           alert( "文件大小超过"+errorObj.info/1024+"KB");
          }
          $(obj).uploadifyClearQueue();
            return false;
        }
    });
}

