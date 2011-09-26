<%@ page contentType="text/html; charset=UTF-8"%>
<c:choose>
  <c:when test="${not empty command.errors }">
    <div id="errorsDiv" class="stop">
         <div>
           <h2 id="errorTitle">出错</h2>
            <ul>
              <c:forEach items="${command.errors}" var="errorinfo">
              <li> ${errorinfo.value}</li>
              </c:forEach>
           </ul>
        </div>
    </div>
  </c:when>
</c:choose>