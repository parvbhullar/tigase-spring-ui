<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet"
  href="/mice/uploadFile/uploadify.css" />
<title>open flash charts</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type='text/javascript' src='/js/ad/photos.js'></script>
<script type='text/javascript' src='/mice/uploadFile/swfobject.js'></script>
<script type='text/javascript' src='/mice/uploadFile/uploadify.v2.1.4.min.js'></script>
<script type='text/javascript' src='/mice/uploadFile/uploadFile.js'></script>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	
 upLoadFileInit("#backgroundImg", "/fileuploading.do", "*.gif;*.jpg;*.png;", false, 5, '*.gif;*.jpg;*.png;', 300, 6, bgUpload, null, uploadBgImg, null, null,null);
});
function bgUpload(event, data){
    alert("bgUpload");
      $('#backgroundImg').uploadifySettings('scriptData', {
          'action': 'upload',
          'sourceType': '5',
          'imgWidth': '320',
          'imgHeight': '54'
      });
  }

function uploadBgImg(event, queueID, fileObj, response){
    alert('uploadBgImg response  ');
      if (response.substr(0, 1) == "/") {
          response = response.substr(1);
      }
      if (response.substr(0, 1) == "\\") {
          response = response.substr(1);
      }
      $("#bgImgId").val(response);
      $("#bannerDemo").css("background-image", "url('/fileuploading.do?action=loadSessionCropImg&fileName=" + response + "&t=" + Math.random() + "')");
  }

</script>
<input type="file" name="backgroundImg" id="backgroundImg" />
  <div style="width: 320px; height: 54px;" class="expl" id="bannerDemo">
    <div class="upLogo collapsed" id="bannerDemoLogo"></div>
    <div class="upText collapsed" id="bannerDemoText" style="overflow:hidden;height:46px"></div>
    </div>
<div id="my_chart"></div> 
</body>
</html>