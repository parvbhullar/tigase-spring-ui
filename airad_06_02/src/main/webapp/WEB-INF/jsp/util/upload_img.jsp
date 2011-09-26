<%@ page
	language="java"
	contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
	<style type="text/css"> 
        .jcrop-holder { 
            text-align: left; 
        } 
        .jcrop-vline, .jcrop-hline{ 
            font-size: 0; 
            position: absolute; 
            background: white url('http://img.jb51.net/jslib/images/Jcrop.gif') top left repeat; 
        } 
        .jcrop-vline { 
            height: 100%; 
            width: 1px !important; 
        } 
        .jcrop-hline { 
            width: 100%; 
            height: 1px !important; 
        } 
        .jcrop-handle { 
            font-size: 1px; 
            width: 7px !important; 
            height: 7px !important; 
            border: 1px #eee solid; 
            background-color: #333; 
            *width: 9px; 
            *height: 9px; 
        } 
         
        .jcrop-tracker { 
            width: 100%; 
            height: 100%; 
        } 
         
        .custom .jcrop-vline,.custom .jcrop-hline{ 
            background: yellow; 
        } 
        .custom .jcrop-handle{ 
            border-color: black; 
            background-color: #C7BB00; 
            -moz-border-radius: 3px; 
            -webkit-border-radius: 3px; 
        } 
        </style> 
        
        
        
        <div style="top: 300px; left: 150px;" id="popDiv" class="popDiv collapsed">
<h2><img class="fr" id="closePop" alt="关闭" src="images/ico_popclose.gif">请上传您的图片</h2>
<div class="popCon">
<div class="tag"><a class="now" href="javascript:void(0)">默认素材</a><a href="javascript:void(0)">用户上传素材</a></div>
<div style="overflow-y: scroll; height: 300px;" class="picBox">
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div class="over"><div><a id="btn03" href="javascript:void(0)"><strong>使用</strong></a></div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
<div><img width="320" height="48" alt=" " src="images/test_img02.jpg"></div>
</div>
</div>
</div>


<div
	class="popDiv impUp">
	<h2><img class="fr" id="closePop" alt="关闭" src="images/ico_popclose.gif">本地上传</h2>
<div class="popCon">
<div
	class="tab">
	<div id="groupLogo"></div>
			<input type="file" name="groupLogoUpload"
				id="groupLogoUpload" /> <input id="groupLogoPath" type="hidden"
				name="companyDetailDto.groupLogoPath" />
			<div id="uploadGroupLogoControl" style="display: none"><a
				href="javascript:$('#groupLogoUpload').uploadifyUpload()">上传</a>| <a
				href="javascript:$('#groupLogoUpload').uploadifyClearQueue()">取消上传</a>
			</div>
	
	裁剪宽度：<select id="sizeType"><option value="1">300px</opend><option value="2">216px</opend><option value="3">168px</opend><option value="4">120px</opend></select><button id="butUoload">确认</button>
<div class="info"><img src="/utilAjax.do?action=upLoadImg&type=1" id="cropbox"/></div>

<div class="info"><img src="/images/test_img02.jpg" id="retuImg"/></div>

</div>
</div>
</div>
<script type="text/javascript" src="/js/util/upload_img.js"></script>




