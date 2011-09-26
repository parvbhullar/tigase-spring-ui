<%@ page contentType="text/html; charset=UTF-8"%>
<%-- background --%>
<%@ include file="old_banner_background.jsp" %>
<%-- background with words --%>
<c:if test="${command.bannerType == 2}">
	<div
	  style="position: absolute; top: 5px; left: 5px; height: 44px; width: 265px;">${command.bannerText}</div>
</c:if>
<%-- background with double words --%>
<c:if test="${command.bannerType == 3}">
  <script type="text/javascript">
  setInterval("clock()",3000);
  var i=0;
  function clock(){
	 var text1='${command.bannerText}';
	 var text2='${command.bannerText2}';
	 var str = text1;
	 if(i%2==0){
		  str = text1;
	 }else{
		  str = text2;
	 }
	 document.getElementById('banner_txt_id').innerHTML= str;
	 i++;
  }
  </script>
  <div id="banner_txt_id"
    style="position: absolute; top: 5px; left: 5px; height: 44px; width: 265px;">${command.bannerText}</div>
</c:if>
<c:if test="${command.bannerType == 4}">
  <div style="position:absolute;top:3px;left:5px;height:44px;width:44px;border:1px solid #27201d; vertical-align:middle"><img src="/fileuploading.do?action=download&fileId=${command.bannerIconCon}" width="44" height="44" /></div>
	<div
	  style="position: absolute; top: 5px; right:5px; height: 44px; width: 255px;">${command.bannerText}</div>
</c:if>