<c:if test="${not empty successinfo}">
    <div class="suc mt">
         <div>
           <h2>提交提示</h2>
            <ul>
              <li style="color: black;"> ${successinfo}</li>
           </ul>
        </div>
    </div>
</c:if>