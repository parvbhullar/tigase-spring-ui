<%@ page contentType="text/html; charset=UTF-8"%>
<h2> <img src="images/ico_popclose.gif" alt="关闭" id="closePop" class="fr" onclick="$.closePopupLayer('popUploadPage')"/>开发者申请</h2>
<div class="popCon" id="eml">
<div class="info">
上传图片
</div>
<iframe name="uploadIframe" style="display: none" src="about:blank"></iframe>
<form name="uploadForm" action="/adEdit.do?action=doUpload" target="uploadIframe" method="POST" enctype="multipart/form-data">
	<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
       <col width="30%" />
       <col width="70%" />
       <tr>
         <th>选择图片</th>
         <td>
           <input id="file" name="file" type="file" /> 
           <span id="ckeml" class="red"></span>
         </td>
       </tr>
       <tr>
         <th>&nbsp;</th>
         <td>
	         <div class="btnBox">
	            <a href="javascript:void(0)" class="btnY fl" id="btn03" onclick="adO.upload()">
	              <span>提交</span>
	            </a>
	         </div>
         </td>
       </tr>
	</table>
</form>
</div>