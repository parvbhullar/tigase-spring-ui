  <%@ page contentType="text/html; charset=UTF-8"%>
  <tr>
    <th>会员充值流水号</th>
    <th>充值金额</th>
   
    <c:if test="${''!=form.desc}">
          <th><a href="javascript:recDrawRecordDesc('');"
            id="descId">充值时间<img alt="降序" src="/images/ico_sortza.gif"></img></a>
          </th>
        </c:if>
        <c:if test="${''==form.desc}">
          <th><a href="javascript:recDrawRecordDesc('2');"
            id="asceId">充值时间<img alt="升序" src="/images/ico_sortaz.gif"></img></a>
          </th>
        </c:if>
        <th>备注</th>
   </tr>
    
   <c:if  test="${!empty form.hisList}">
    <c:forEach items="${form.hisList}" var="i">
   <tr>
    <td>${i.serialNum}</td>
    <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${i.money}" pattern="###,##0.00"/></td>
    <td><fmt:formatDate
      value="${i.rechargeTime}" type="both"
      pattern="yyyy-MM-dd HH:mm" /></td>
    <td>
      ${i.remark}
    </td>
    
  </tr>
  </c:forEach>
  </c:if> 
  <c:if  test="${empty form.hisList}">
    <td colspan="4" align="center">暂无数据</td>
  </c:if> 