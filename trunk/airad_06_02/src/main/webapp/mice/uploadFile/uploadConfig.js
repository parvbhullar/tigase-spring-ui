
//=============================================
//文件上传控件配置
//xingshikang
//=============================================
//初始化
$(document).ready(function() {
	$('#fileToUpload').uploadify({
	'uploader': '/mice/uploadFile/uploadify.swf',
	'folder':'E:/',
	'script': 'fileuploading.do?xcase=upload',
	'fileDataName':'fileToUpload',
	'simUploadLimit':10,
	'fileDesc':'只支持gif jpg pdf doc docx文件',
	'fileExt':'*.gif;*.jpg;*.pdf;*.doc;*.docx',
	'queueSizeLimit':10,
	'buttonText':'选择文件',
	'cancelImg': '/mice/uploadFile/cancel.png',
	'wmode': 'transparent',
	'multi':true,
	'onSelectOnce':uploadSelectOnce,
	'onQueueFull':uploadQueueFull,
	'onComplete':uploadComplete,
	'onAllComplete':uploadAllComplete
	});
});
//上传成功后
function uploadComplete(event,queueID,fileObj,response){
	if(response.substr(0,1)=="/"){
		response=response.substr(1);
	}
	$("input[name=uploadedFileName]").get(0).value+=response+"*";
//	$('#fileToUpload').uploadifySettings(
//			'scriptData',
//			{'uploadedFileName':$('input[name=uploadedFileName]').val()}
//	);
}
//选择完文件后
function uploadSelectOnce(event,data){
	$("#uploadControl").show();
}
//超过最大限定数量时
function uploadQueueFull(event,queueSizeLimit){
	alert("超过了单次文件最大上传数量！");
	return false;
}
//所有文件上传成功后
function uploadAllComplete(event,data){
	$("#uploadControl").hide();
	//alert("所有文件上传成功");
}