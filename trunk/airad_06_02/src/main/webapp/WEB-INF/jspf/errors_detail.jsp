<% //提交之后显示错误信息，跟随每个input %>
<c:choose>
  <c:when test="${not empty command.errors }">
              <c:forEach items="${command.errors}" var="errorinfo">
              <div class="wrBox"  name="_errorinfo"   id="_error${errorinfo.key}" >
              <div  class="wr" >${errorinfo.value}</div>
              </div>
              </c:forEach>
  </c:when>
</c:choose>
<script>
 $(document).ready(function(){
	  $("div[name='_errorinfo']").each(function(){
		  var tid=$(this).attr("id").replace("_error","#").replace(/\./g,"\\.");
		  $(tid).after($(this));
		  });

	 });
</script>