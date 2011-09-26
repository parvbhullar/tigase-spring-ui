<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>

	<table border="0" cellspacing="0" cellpadding="0" class="tabY">
	
	<colgroup>
	<col width="3%"/>
	<col width="22%"/>
	<col width="9%"/>
	<col width="9%"/>
	<col width="9%"/>
	<col width="9%"/>
	<col width="10%"/>
	<col width="8%"/>
	<col width="6%"/>
  </colgroup>
 <tr>
    <th><input type="checkbox" id="checkboxswitch" /></th>
    <th>广告名称</th>
    <%--
    <th><a href="javascript:void(0)">状态<img alt="排序" src="images/ico_sort.gif"></a></th>
    <th><a href="javascript:void(0)">展示次数<img alt="排序" src="images/ico_sort.gif"></a></th>
    <th><a href="javascript:void(0)">点击数<img alt="排序" src="images/ico_sort.gif"></a></th>
    <th><a href="javascript:void(0)">点击率<img alt="排序" src="images/ico_sort.gif"></a></th>
    <th><a href="javascript:void(0)">花费<img alt="排序" src="images/ico_sort.gif"></a></th>
    --%>

    <th style="text-align:right">
		    <c:if test="${p.timeSlotFlag!=1}">
		      <a href="javascript:getDetailChart(1,'desc',1);">展示次数<img alt="排序" src="images/ico_sort.gif"></a>
		    </c:if>
        <c:if test="${p.timeSlotFlag==1}">
	        <c:set var="imgsrc" value="images/ico_sort.gif" ></c:set>
	        <c:if test="${'desc'==p.desc}">
	          <a href="javascript:getDetailChart(1,'asce',1);" id="descId">展示次数<img alt="降序" src="/images/ico_sortza.gif"></a></c:if>
	        <c:if test="${'asce'==p.desc}">
	          <a href="javascript:getDetailChart(1,'desc',1);" id="asceId">展示次数<img alt="升序" src="/images/ico_sortaz.gif"></a>
	        </c:if>
        </c:if>
      </th>
    <th style="text-align:right">
		    <c:if test="${p.timeSlotFlag!=2}">
		      <a href="javascript:getDetailChart(1,'desc',2);">点击数<img alt="排序" src="images/ico_sort.gif"></a>
		    </c:if>
        <c:if test="${p.timeSlotFlag==2}">
	        <c:set var="imgsrc" value="images/ico_sort.gif" ></c:set>
	        <c:if test="${'desc'==p.desc}">
	          <a href="javascript:getDetailChart(1,'asce',2);" id="descId">点击数<img alt="降序" src="/images/ico_sortza.gif"></a></c:if>
	        <c:if test="${'asce'==p.desc}">
	          <a href="javascript:getDetailChart(1,'desc',2);" id="asceId">点击数<img alt="升序" src="/images/ico_sortaz.gif"></a>
	        </c:if>
        </c:if>
      </th>
    <th style="text-align:right">
		    <c:if test="${p.timeSlotFlag!=3}">
		      <a href="javascript:getDetailChart(1,'desc',3);">点击率<img alt="排序" src="images/ico_sort.gif"></a>
		    </c:if>
        <c:if test="${p.timeSlotFlag==3}">
	        <c:set var="imgsrc" value="images/ico_sort.gif" ></c:set>
	        <c:if test="${'desc'==p.desc}">
	          <a href="javascript:getDetailChart(1,'asce',3);" id="descId">点击率<img alt="降序" src="/images/ico_sortza.gif"></a></c:if>
	        <c:if test="${'asce'==p.desc}">
	          <a href="javascript:getDetailChart(1,'desc',3);" id="asceId">点击率<img alt="升序" src="/images/ico_sortaz.gif"></a>
	        </c:if>
        </c:if>
    </th>
    <th style="text-align:right">
		    <c:if test="${p.timeSlotFlag!=4}">
		      <a href="javascript:getDetailChart(1,'desc',4);">花费<img alt="排序" src="images/ico_sort.gif"></a>
		    </c:if>
        <c:if test="${p.timeSlotFlag==4}">
	        <c:set var="imgsrc" value="images/ico_sort.gif" ></c:set>
	        <c:if test="${'desc'==p.desc}">
	          <a href="javascript:getDetailChart(1,'asce',4);" id="descId">花费<img alt="降序" src="/images/ico_sortza.gif"></a></c:if>
	        <c:if test="${'asce'==p.desc}">
	          <a href="javascript:getDetailChart(1,'desc',4);" id="asceId">花费<img alt="升序" src="/images/ico_sortaz.gif"></a>
	        </c:if>
        </c:if>
      </th>
      <th style="text-align:right">
        <c:if test="${p.timeSlotFlag!=5}">
          <a href="javascript:getDetailChart(1,'desc',5);">状态<img alt="排序" src="images/ico_sort.gif"></a>
        </c:if>
        <c:if test="${p.timeSlotFlag==5}">
          <c:set var="imgsrc" value="images/ico_sort.gif" ></c:set>
          <c:if test="${'desc'==p.desc}">
            <a href="javascript:getDetailChart(1,'asce',5);" id="descId">状态<img alt="降序" src="/images/ico_sortza.gif"></a></c:if>
          <c:if test="${'asce'==p.desc}">
            <a href="javascript:getDetailChart(1,'desc',5);" id="asceId">状态<img alt="升序" src="/images/ico_sortaz.gif"></a>
          </c:if>
        </c:if>
      </th>
    <th style="text-align:right">
		    <c:if test="${p.timeSlotFlag!=6}">
		      <a href="javascript:getDetailChart(1,'desc',6);">更新时间<img alt="排序" src="images/ico_sort.gif"></a>
		    </c:if>
        <c:if test="${p.timeSlotFlag==6}">
	        <c:set var="imgsrc" value="images/ico_sort.gif" ></c:set>
	        <c:if test="${'desc'==p.desc}">
	          <a href="javascript:getDetailChart(1,'asce',6);" id="descId">更新时间<img alt="降序" src="/images/ico_sortza.gif"></a></c:if>
	        <c:if test="${'asce'==p.desc}">
	          <a href="javascript:getDetailChart(1,'desc',6);" id="asceId">更新时间<img alt="升序" src="/images/ico_sortaz.gif"></a>
	        </c:if>
        </c:if>
     </th>
     <th>
     </th>


    
<!--    <th><a href="javascript:void(0)">更新时间<img alt="排序" src="images/ico_sortza.gif"></a></th>-->
    </tr>
    <%--
		<tr>

			<th>广告名称</th>
			<th>
			<c:if test="${null==flag}">
          <a href="javascript:adDesc();" id="descId">创建时间<img alt="降序" src="/images/ico_sortza.gif"></a></c:if>
        <c:if test="${null!=flag}">
        <a href="javascript:adAsce();" id="asceId">创建时间<img alt="升序" src="/images/ico_sortaz.gif"></a>
      </c:if></th>
			<th>展示次数</th>
			<th>出价</th>
			<th>点击数</th>
			<th>点击率</th>
			<th colspan="2">成本</th>
<!--			<th>平均每次点击价格</th>-->
			<th>状态</th>
		</tr>
		 --%>
		<c:forEach items="${p.adList}" var="coreAd" varStatus="s">
			<tr onmouseover="showButton('d${s.count}');"
          onmouseout="closeButton('d${s.count}');">
        <td><input type="checkbox"  class="checkboxadid" value="${coreAd.adId}" name="checkbox1"/></td>
    <c:choose>
                 <c:when test="${coreAd.blocking==1}">
        <c:set value="冻结 " var="sta"></c:set>     
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.suspendType==1}">
        <c:set value="停用" var="sta"></c:set>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.flag==0 and coreAd.suspendType==0}">
        <c:set value="草稿" var="sta"></c:set>
               </c:when>
                 <c:when test="${coreAd.blocking==0 and coreAd.flag==1 and coreAd.suspendType==0}">
        <c:set value="待审核" var="sta"></c:set>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.flag==2 and coreAd.suspendType==0}">
        <c:set value="审核通过" var="sta"></c:set>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.flag==3 and coreAd.suspendType==0}">
        <c:set value="审核不通过" var="sta"></c:set>
               </c:when>
  </c:choose>
 
          <c:set value="ico_s_stop.gif" var="imgsrc"></c:set>
        <c:if test="${coreAd.suspendType==0}">
          <c:set value="ico_s_play.gif" var="imgsrc"></c:set>
        </c:if>
        <c:set value="ico_gup.gif" var="imgsrc"></c:set>
        
        <c:if test="${ not coreAd.isTime}">

				<td ><img src="/images/${imgsrc}" alt="广告" title="${sta}" width="16"
					height="16" align="absmiddle" /> 
					<a href="/ad.do?action=detailEdit&adId=${coreAd.adId}&adGroupId=${coreAd.adGroupId}">
					<airad:cutString size="10" value="${coreAd.adName}" mark="..."/></a>
				<span id="d${s.count}"></span>
					<%--
				<a href="ad.do?action=detailEdit&adId=${coreAd.adId }&adGroupId=${coreAd.adGroupId }" class="btnS"><span>编辑</span></a>
				<span class="gray">|</span> 
				<c:if test="${coreAd.suspendType==1}">
        <a href="javascript:sendAd(${coreAd.adId });">运行</a>
        </c:if>
        <c:if test="${coreAd.suspendType==0}">
        <a href="javascript:stopAd(${coreAd.adId })">停止</a>
        </c:if>
				<a href="javascript:deleteAd(${coreAd.adId})">删除</a>
				</div>
				 --%>
				</td>
</c:if>
<c:if test="${ coreAd.isTime}">
<td >
<img src="/images/${imgsrc}" alt="广告" title="${sta}" width="16"
          height="16" align="absmiddle" /> 
          
          <a href="#" onclick="showAdTips()">
          <airad:cutString size="10" value="${coreAd.adName}" mark="..."/> <small>历史数据</small></a>
        <span id="d${s.count}"></span>
        </td>
</c:if>
			 	<!--<td>222 <fmt:formatDate value="${coreAd.addTime}"type="both" pattern="yyyy-MM-dd HH:mm" /></td> -->
				<td class="r"><fmt:formatNumber type="number" value="${coreAd.showNum}"/></td>
				<!-- <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${coreAd.adOffer}" pattern="###,##0.00"/></td> -->
        <td class="r"><fmt:formatNumber type="number" value="${coreAd.clickNum}"/></td>
        <td class="r"><fmt:formatNumber type="number" value="${coreAd.clickRate*100}" pattern="###.##"/>%</td>
        <td class="r"><sup>&yen;</sup><fmt:formatNumber type="number" value="${coreAd.cost}" pattern="###,##0.00"/></td>
<!--        <td><sup>&yen;</sup><fmt:formatNumber type="number" value="${coreAd.avgClick}" pattern="###,#0.00"/></td>-->


        <td class="r">
         <c:choose>
               <c:when test="${coreAd.blocking==1}">
                   <span class="red">冻结  </span>        
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.suspendType==1}">
                  <span class="red">停用</span>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.flag==0 and coreAd.suspendType==0}">
                  <span>草稿</span>
               </c:when>
                 <c:when test="${coreAd.blocking==0 and coreAd.flag==1 and coreAd.suspendType==0}">
                  <span class="orange">待审核</span>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.flag==2 and coreAd.suspendType==0}">
                  <span>审核通过</span>
               </c:when>
               <c:when test="${coreAd.blocking==0 and coreAd.flag==3 and coreAd.suspendType==0}">
                  <span class="red">审核不通过</span>
               </c:when>
        </c:choose>
        </td>
        <td class="r"><fmt:formatDate value="${coreAd.updTime}"type="both" pattern="yyyy-MM-dd" /></td>
        <td ><a href="/report.do?action=showAdReport&adId=a${coreAd.adId }">查看报表</a></td>
        <!--  
				<td><c:choose>
					<c:when test="${coreAd.suspendType==0}">运行</c:when>
					<c:when test="${coreAd.suspendType==1}">暂停</c:when>
					<c:otherwise>   无效状态  </c:otherwise>
				</c:choose></td>
				<td><c:choose>
					<c:when test="${coreAd.flag==0}"> 草稿</c:when>
					<c:when test="${coreAd.flag==1}"> 待审核</c:when>
					<c:when test="${coreAd.flag==2}">审核通过 </c:when>
					<c:when test="${coreAd.flag==3}">审核不通过 </c:when>
					<c:when test="${coreAd.flag==4}"> 发布</c:when>
					<c:otherwise>无效状态  </c:otherwise>
				</c:choose></td>
				-->
			</tr>
		</c:forEach>
		  <c:if test="${empty p.adList}">
        <tr><td colspan="9"><div class="c">暂无数据</div></td></tr>
    </c:if>
	</table>
	<airad:pagination pageSize="${p.pageSize}"
		href="javascript:getDetailChart(PAGENUM,'${p.desc}',${p.timeSlotFlag})"
		totalRecord="${p.totalCount}" currentPage="${p.currentPage}"></airad:pagination>
        <div class="tipBox"></div>

        

<script type="text/javascript" src="/js/ad.js"></script>
<script>

   $(function(){
        $(".checkboxadid").change(function(){
        	  	switchcheckbox();
         });
            //切换全选
        $("#checkboxswitch").change(function() {
        	var isc=($("#checkboxswitch").attr("checked"));
	        	$(".checkboxadid").each(function(i) { 
	        		$("input[name='checkbox1']")[i].checked = isc ;//($("#checkboxswitch").attr("checked")=='checked');
	        	});
        	 switchcheckbox();
        	}); 

        //排序标识索引
        $("#timeSlotFlag").attr("value",'${p.timeSlotFlag}');
        //排序 desc or asce
        $("#desc").attr("value",'${p.desc}');

  });


</script>

