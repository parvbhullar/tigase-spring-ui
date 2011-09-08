<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<table cellspacing="0" cellpadding="0" border="0" class="tabY">
  <col width="20%"/>
  <col width="15%"/>
  <col width="10%"/>
  <col width="10%"/>
  <col width="10%"/>
  <col width="10%"/>
  <col width="10%"/>
  <col width="15%"/>
  <tbody>
  <tr>
    <th>应用程序名称</th>
    <th>  
        <c:choose>
          <c:when test="${empty sortFlag}">
             <a href="javascript:fillPage('${p.currentPage}','desc');" id="descId">日期<img alt="降序" src="/images/ico_sortza.gif"></a>
          </c:when>
          <c:otherwise>
             <a href="javascript:fillPage('${p.currentPage}','asce');" id="asceId">日期<img alt="升序" src="/images/ico_sortaz.gif"></a>
          </c:otherwise>
        </c:choose>
    </th>
    <th>请求数</th>  
    <th>展示数</th>
    <th>点击数</th>    
   <!--  <th>点击收入</th>--> 
    <th>总收入</th>
    <th>投放率</th>
    <th>点击率（CTR）</th>
    </tr>
    <c:forEach items="${p.statAppView}" var="appview">
      <tr>
        <td>${appview.appName }&nbsp;</td>
        <td>${appview.reqtime }&nbsp;</td>
        <td>${appview.maxNum }&nbsp;</td>
        <td>${appview.showNum }&nbsp;</td>
        <td>${appview.clickNum }&nbsp;</td>
        <td><fmt:formatNumber value="${appview.offer }"  maxFractionDigits="2"/>&nbsp;</td>
        <td><fmt:formatNumber value="${appview.putRate }" type="percent" maxFractionDigits="2"/>&nbsp;</td>
        <td><fmt:formatNumber value="${appview.clickRate }" type="percent" maxFractionDigits="2"/> &nbsp;</td>
      </tr>
    </c:forEach>
    <c:if test="${empty p.statAppView}">
      <tr><td colspan="8">暂无请求统计数据！</td></tr>
    </c:if>

</tbody></table>
<airad:pagination pageSize="${p.pageSize}" href="javascript:fillPage(PAGENUM);" totalRecord="${p.totalCount}" currentPage="${p.currentPage}"></airad:pagination>