<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<%@ taglib uri="/WEB-INF/tags/fmt.tld" prefix="fmt" %>
<%@page import="com.mitian.airad.model.StatAd"%><table cellspacing="0" cellpadding="0" border="0" class="tabY">
  <col width="20%"/>
  <col width="15%"/>
  <col width="15%"/>
  <col width="15%"/>
  <!-- <col width="15%"/> -->
  <!--   <col width="12%"/> -->
  <c:if test="${form.report.reportStatus=='1'}">
  <col width="12%"/>
  </c:if>
  <tbody>
    <tr>
      <c:if test="${form.report.reportStatus=='1'}">
         <th>名称</th>
      </c:if>
      <th>
      <c:if test="${empty adlist}">
          <a href="javascript:void(0);" id="descId">日期<img alt="降序" src="/images/ico_sortza.gif"></a>
      </c:if>
      <c:if test="${not empty adlist}">
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
      <!-- <th>广告展现形式</th> -->
      <th>总展示次数</th>
      <th>总点击数</th>
<!--       <th>展示成本</th> -->
       <th width="20%">点击率</th> 
      <th  width="20%">点击成本</th>
    </tr>
    <c:forEach var="r" items="${adlist}">
    <tr>
      <c:if test="${form.report.reportStatus=='1'}">
	      <c:choose>
	        <c:when test="${form.report.reportType=='0'}">
	        <td><airad:cutString size="20" value="${r.campaignName }" mark="..."/>&nbsp;</td>
	        </c:when>
	        <c:when test="${form.report.reportType=='1'}">
	        <td><airad:cutString size="20" value="${r.adGroupName }" mark="..."/>&nbsp;</td>
	        </c:when>
	        <c:when test="${form.report.reportType=='2'}">
	        <td><airad:cutString size="20" value="${r.adName }" mark="..."/>&nbsp;</td>
	        </c:when>
	      </c:choose>
      </c:if>
        <td><fmt:formatDate value="${r.addTime }" timeStyle="yyyy-MM-dd"/>&nbsp;</td>
        <!--  
        <td>
					<c:choose>
					  <c:when test="${r.showType=='1'}">
					               文字
					  </c:when>
					  <c:when test="${r.showType=='2'}">
					               图文
					  </c:when>
					  <c:when test="${r.showType=='3'}">
					               图片
					  </c:when>
					  <c:when test="${r.showType=='4'}">
					               地图
					  </c:when>
					  <c:when test="${r.showType=='5'}">
					               淘宝
					  </c:when>
					  <c:otherwise>
					             其他
					  </c:otherwise>
					</c:choose>&nbsp;</td>-->
        <td>${r.showNum }&nbsp;</td>
        <td>${r.clickNum }&nbsp;</td>
        <td>
        
        <fmt:formatNumber value="${r.clickRate*100}" pattern="###.##" var="num" />${num}%
     &nbsp;</td>
        <!-- 
        <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${r.showMoney}" pattern="###,#0.00"/>&nbsp;</td> -->
        <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${r.clickMoney}" pattern="###,#0.00"/>&nbsp;</td>
     </tr> 
    </c:forEach>
    <c:if test="${empty adlist}">
      <tr><td colspan="8"><div class="c">暂无数据</div></td></tr>
    </c:if>
  </tbody>
</table>
<airad:pagination pageSize="${form.pageSize}" href="javascript:fillPage(PAGENUM,'${desc}${asce}');" totalRecord="${form.totalCount}" currentPage="${form.currentPage}"></airad:pagination>
