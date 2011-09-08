<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<table cellspacing="0" cellpadding="0" border="0" class="tabY">
<c:if test="${form.report.reportStatus=='1'}">
 <col width="20%"/>
</c:if>
  <col width="15%"/>
  <col width="15%"/>
  <col width="15%"/>
  <col width="12%"/>
  <tbody><tr>
  <c:if test="${form.report.reportStatus=='1'}">
    <th>广告商名称</th>
   </c:if>
    <th>
       <c:if test="${empty advlist}">
          <a href="javascript:void(0);" id="descId">广告商创建日期<img alt="降序" src="/images/ico_sortza.gif"></a>
      </c:if>
      <c:if test="${not empty advlist}">
     <c:choose>
          <c:when test="${empty sortFlag}">
             <a href="javascript:fillPage('${form.currentPage}','desc');" id="descId">广告商创建日期<img alt="降序" src="/images/ico_sortza.gif"></a>
          </c:when>
          <c:otherwise>
             <a href="javascript:fillPage('${form.currentPage}','asce');" id="asceId">广告商创建日期<img alt="升序" src="/images/ico_sortaz.gif"></a>
          </c:otherwise>
        </c:choose>
        </c:if>
    </th>
    <th>广告商广告展示次数</th>
    <th>广告商广告点击次数</th>
    <th>分成收入</th>  
    </tr>
  <tr>
  <c:forEach items="${advlist}" var="adv">
    <c:if test="${form.report.reportStatus=='1'}">
       <td><airad:cutString size="20" value="${adv.advertiserName }" mark="..."/>&nbsp;</td>
     </c:if>
    <td><fmt:formatDate value="${adv.createTime }" timeStyle="yyyy-MM-dd"/>&nbsp;</td>
    <td>${adv.showNum }&nbsp;</td>
    <td>${adv.clickNum }&nbsp;</td>
    <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${adv.offer}" pattern="###,#0.00"/>&nbsp;</td>
  </c:forEach>
  <c:if test="${empty advlist}">
      <tr><td colspan="6"><div class="c">暂无数据</div></td></tr>
    </c:if>
  </tr>
</tbody></table>
<airad:pagination pageSize="${form.pageSize}" href="javascript:fillPage(PAGENUM,'${desc}${asce}');" totalRecord="${form.totalCount}" currentPage="${form.currentPage}"></airad:pagination>