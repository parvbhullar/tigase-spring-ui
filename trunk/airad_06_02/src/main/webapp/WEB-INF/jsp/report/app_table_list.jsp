<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<table cellspacing="0" cellpadding="0" border="0" class="tabY">
  <col />
  <col width="15%"/>
  <col width="8%"/>
  <col width="8%"/>
  <col width="8%"/>
  <col width="10%"/>
  <c:if test="${form.report.reportStatus=='1'}">
  <col width="8%"/>
  </c:if>
  <col width="15%"/>
  <tbody>
  <tr>
  <c:if test="${form.report.reportStatus=='1'}">
    <th>应用程序名称</th>
    </c:if> 
    <th>  
       <c:if test="${empty applist}">
          <a href="javascript:void(0);" id="descId">日期<img alt="降序" src="/images/ico_sortza.gif"></a>
      </c:if>
      <c:if test="${not empty applist}">
       <c:choose>
          <c:when test="${empty sortFlag}">   
             <a href="javascript:fillPage('${form.currentPage}','desc');" id="descId">日期<img alt="降序" src="/images/ico_sortza.gif"></a>
          </c:when>
          <c:otherwise>
             <a href="javascript:fillPage('${form.currentPage}','asce');" id="asceId">日期<img alt="升序" src="/images/ico_sortaz.gif"></a>
          </c:otherwise>
        </c:choose>
        </c:if>
    </th>
    <th>请求数</th> 
    <th>展示数</th>
    <th>点击数</th>    
    <th>收入</th>
    <th>投放率</th>
    <th>点击率（CTR）</th>
    </tr>
    <c:forEach items="${applist}" var="p">
      <tr>
      <c:if test="${form.report.reportStatus=='1'}">
         <td>
         <airad:cutString size="20" value="${p.appName }" mark="..."/>&nbsp;</td>
      </c:if>
        <td><fmt:formatDate value="${p.addTime }" timeStyle="yyyy-MM-dd"/>&nbsp;</td>
        <td>${p.maxNum }&nbsp;</td>
        <td>${p.showNum }&nbsp;</td>
        <td>${p.clickNum }&nbsp;</td>
        <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${p.offer}" pattern="###,#0.00"/>&nbsp;</td>
        <td><fmt:formatNumber value="${p.putRate }" type="percent" maxFractionDigits="2"/>&nbsp;</td>
        <td><fmt:formatNumber value="${p.clickRate }" type="percent" maxFractionDigits="2"/> &nbsp;</td>
      </tr>
    </c:forEach>
     <c:if test="${empty applist}">
      <tr><td colspan="9"><div class="c">暂无数据</div></td></tr>
    </c:if>

</tbody></table>
<airad:pagination pageSize="${form.pageSize}" href="javascript:fillPage(PAGENUM,'${desc}${asce}');" totalRecord="${form.totalCount}" currentPage="${form.currentPage}"></airad:pagination>