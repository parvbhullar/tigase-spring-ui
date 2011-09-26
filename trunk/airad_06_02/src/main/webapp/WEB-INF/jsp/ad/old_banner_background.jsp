<%@ page contentType="text/html; charset=UTF-8"%>
<c:if test="${command.bannerBgType == 0 }">
  <div
    style="width: 320px; height: 54px; position: relative; border: 1px solid #27201d; background: ${command.bannerBgCon}  repeat-x top left">
  </div>
</c:if>
<c:if test="${command.bannerBgType == 1 }">
  <div
    style="width: 320px; height: 54px; position: relative; border: 1px solid #27201d; background: #403531 url(/fileuploading.do?action=download&fileId=${command.bannerBgCon})  repeat-x top left">
  </div>
</c:if>