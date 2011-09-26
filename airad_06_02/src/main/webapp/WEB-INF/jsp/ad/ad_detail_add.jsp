<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/ad/ad_head.jsp"%>
<h1>广告制作</h1>


<form action="/ad.do?action=adSave" method="post" class="ppt.ad_adSave_zh" name="adform" id="adform">
<form:hidden path="command.adId" />
<form:hidden path="command.adGroupId" /> 
<form:hidden path="command.adType"  /> 
<form:hidden path="command.adChildNum"  /> 
<input type="hidden" id="delRichId" name="delRichId"></input> 
<input type="hidden" name="showTypev" id="showTypev" />
<input type="hidden" id="draftStatus"  value="0" />
<input type="hidden" id="submitStatus" value="0" />
<input type="hidden" id="initStatus" />
<input type="hidden" id="showTypeOrignal" value="i" />


<table cellspacing="0" cellpadding="0" border="0" class="tabNF"
  id="adTitleTable">
  <col width="20%" />
  <col width="80%" />
  <tr>
    <th><span class="must">*</span>广告名称</th>
    <td><form:input path="command.adName" cssClass="long"
      maxlength="50" /> 
      <form:hidden path="command.marketType" cssClass="long" maxlength="50" />
      <button id="adDraftSave" type="button" style="display: none">设置广告标题</button>
      <input id="adIsModifed" name="adIsModifed" type="hidden" value="0"/>
      <input id="adBannerContent" type="hidden" />
     
      <small style="display: block">请输入一个有助于您识别该广告的名称。</small>
    </td>
  </tr>
</table>
<!--广告222222222222222222 Banner 制作 -->
<%@ include file="/WEB-INF/jsp/ad/banner_in.jsp" %>

<%--
<h4>

<!-- 背景预览 -->
<div class="fr" >
<a class="collapsed" id="previewNavigation"href="javascript:void(0)">广告背景预览</a>&nbsp;&nbsp;&nbsp;
<a class="collapsed" id="delBackgroundPhoto"href="javascript:void(0)">删除背景图片</a>

</div>
<!--3333333333333333333广告内容制作 - 导航页-->


广告内容制作 - 导航页</h4>

<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
  <col width="20%" />
  <col width="80%" />
  <tr>
    <th>模板选择</th>
    <td>
    <div class="selectPicBox">
    <a id="pic02"
      href="javascript:void(0)"><span class="pBoxCon">单页面 </span><img
      title="单页面价格取决于您选择的广告内容模版价格" alt="单页面" src="images/pic_nav_04.gif" /></a>

    <a id="pic03" href="javascript:void(0)"
      rel="<airad:sysConfig type='NAVIGATION' flag='out' key='1' />"><span
      class="pBoxCon">普通导航
      <c:if test="${!baseRole.ossSales}">
      <span class="sml"><sup>&yen;</sup>
      <airad:sysConfig
      type='NAVIGATION' flag='out' key='1' /></span>
      </c:if>
      </span><img title="普通导航" alt="普通导航"
      src="images/pic_nav_01.gif" /></a> 
      
      <a id="pic04"
      href="javascript:void(0)"
      rel="<airad:sysConfig type='NAVIGATION' flag='out' key='2' />"><span
      class="pBoxCon">特效导航
        <c:if test="${!baseRole.ossSales}">
      <span class="sml"><sup>&yen;</sup><airad:sysConfig
      type='NAVIGATION' flag='out' key='2' /></span>
        </c:if>
      </span><img title="特效导航" alt="特效导航"
      src="images/pic_nav_02.gif" /></a> 
      <a id="pic01"
      href="javascript:void(0)"
      rel="<airad:sysConfig type='WAP' flag='out' key='1' />"><span
      class="pBoxCon">WAP页
        <c:if test="${!baseRole.ossSales}">
      <span class="sml"><sup>&yen;</sup><airad:sysConfig
      type='WAP' flag='out' key='1' /></span>
      </c:if>
      </span><img title="WAP页" alt="WAP页"
      src="images/pic_nav_03.gif" /></a></div>
    </td>
  </tr>
  <tr id="adBackground" style="display: none;">
    <th>上传背景图片</th>
    <td><form:hidden path="command.background" /><input type="file"
      name="navigationBackground" id="navigationBackground" /><small>上传推荐宽高为320px*480px，或于此等比例图片。</small>
    </td>
  </tr>
  <tr style="display: none;" id="con20">
    <th>页面数量</th>
    <td><select name="adChildNum" id="adChildNum">
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
      <option value="6">6</option>
    </select></td>
  </tr>
</table>
 --%>
 
<%--
<!--   wap 内容广告--->
<%@ include file="/WEB-INF/jsp/ad/wap_in.jsp"%>
  --%>
<!-- 非WAP 内容广告  -->
<%@ include file="/WEB-INF/jsp/ad/rich_child_in.jsp"%>
<form:hidden path="command.background" />
<!--444444444444价格 -->
<h4>广告设置</h4>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
  <col width="20%" />
  <col width="80%" />
  <tbody>
  
<!---->
        <tr>
          <th><span class="must">*</span>开始时间</th>
          <td  colspan="2"><form:input path="command.adStartTime" class="cal" readonly="true"/>
          <form:select path="command.adStartHour"  >
            <c:forEach begin="0" end = "23" var="hour">
                          <c:if test="${hour<10}">
                <form:option value="0${hour}" label="0${hour}"/>
              </c:if>
              <c:if test="${hour>9}">
                <form:option value="${hour}" label="${hour}"/>
              </c:if>
            </c:forEach>
            </form:select>
            &nbsp;时
            <form:select path="command.adStartMin" >
            <form:option value="00" label="00"/>
            <form:option value="15" label="15"/>
            <form:option value="30" label="30"/>
            <form:option value="45" label="45"/>
            </form:select>
           &nbsp;分
            ${el:errorTip(command.errors,"adStartTime") }
          </td>
        </tr>
        <tr>
          <th>是否有结束时间</th>
          <td  colspan="2">
           <form:select path="command.timeFlag" onChange="showEndTimead('endTime','endHour','endMin');">
            <form:option value="0" label="否"/>
            <form:option value="1" label="是"/>
           </form:select>
          </td>
        </tr>
        <tr id="showEndTimeId" style="display: none">
        <th><span class="must">*</span>结束时间</th>
        <td colspan="2">
          <form:input 
           path="command.adEndTime" class="cal"  readonly="true"/>
          <form:select path="command.adEndHour" >

            <c:forEach begin="0" end = "23" var="hour">
                            <c:if test="${hour<10}">
                <form:option value="0${hour}" label="0${hour}"/>
              </c:if>
              <c:if test="${hour>9}">
                <form:option value="${hour}" label="${hour}"/>
              </c:if>
            </c:forEach>
            </form:select>
            &nbsp;时
            <form:select path="command.adEndMin"  >
            <form:option value="00" label="00"/>
            <form:option value="15" label="15"/>
            <form:option value="30" label="30"/>
            <form:option value="45" label="45"/>
            </form:select>
           &nbsp;分
            <div id="timeDiv" style="color: red;"></div>
            ${el:errorTip(command.errors,"adEndTime") }
        </td>
        </tr>
<!---->

 

        <tr>
          <th>每日预算</th>
          <td colspan="2"><sup>¥</sup>

          <form:input path="command.adBudgetDay" class="tiny" maxlength="8"/>&nbsp;&nbsp;
          
          <div style="display:block;clear: left;"><small>此项可有效控制每日广告费用。当每日广告花费达到填入金额时，该广告将暂停投放，至次日再继续。但预算额度最低不可低于¥50。不填写时为预算无上限。</small></div>
          </td>
        </tr>
  
        <tr>
          <th>广告总费用</th>
          <td colspan="2"><sup>¥</sup>
          <form:input path="command.adBudgetTotal" class="tiny" maxlength="8" />&nbsp;&nbsp;
          
          <div style="display:block;clear: left;"><small>此项可有效控制本广告的总费用。但预算额度最低不可低于¥50。当广告花费达到填入金额时，该广告将停止投放。不填写时为预算无上限。</small></div>
          </td>
        </tr>
        
  
  <c:choose>
  <c:when test="${baseRole.ossSales}">
    <tr>
     <input type="hidden" id ="isAdOffer" value="1"/>
      <th><span class="must">*</span>每千次展示付费</th>
      <td colspan="2"><sup>&yen;</sup> <input
        class="short" name="adOffer" id="adOffer" type="text"
        value="<fmt:formatNumber value="${command.adOffer }" pattern="##.##" minFractionDigits="2" />" />元</td>
    </tr>
  </c:when>
  <c:otherwise>
    <tr>
    <input type="hidden" id ="isAdOffer" value="0"/>
      <th>点击单价</th>
      <td  colspan="2"><sup>&yen;</sup> <span id="htmlAdFree"><fmt:formatNumber
        value="${command.adOffer }" pattern="##.##元" minFractionDigits="2" /></span><input
        class="short" name="adOffer" id="adOffer" type="hidden"
        value="<fmt:formatNumber value="${command.adOffer }" pattern="##.##" minFractionDigits="2" />" /></td>
    </tr>
  </c:otherwise>
  </c:choose>
  </tbody>
</table>
<table cellspacing="0" cellpadding="0" border="0" class="tabNF">
  <col width="20%" />
  <col width="80%" />
  <tr>
    <th>&nbsp;</th>
    <td>
    <div class="btnBox">
    <button class="btnBY" id="saveForm" style="cursor:pointer">提交，并设置广告组</button>
    <span class="gray">|</span> <a id="saveAd" href="javascript:void(0)">保存为草稿</a>
    <span class="gray">|</span> <a id="adReturn"
      href="ad.do?action=adList&adGroupId=${command.adGroupId}">取消</a>
    </div>
    </td>
  </tr>
</table>
</form>
</div>
</div>

<!-- JS遮罩层 -->
<div id="fullbg">

<iframe width="100%" height="100%" frameborder="0"></iframe>  
</div>
<%
    /**
     *其它的页面 
     *1.右边页面
     */
%>
<%@ include file="/WEB-INF/jsp/ad/ad_right_in.jsp"%>
</div>
<%@ include file="/WEB-INF/jsp/ad/material_in.jsp"%>
<input type="hidden" id="isNeedNotifyWhenChange2Wap" name="isNeedNotifyWhenChange2Wap" value="false"/>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
<%@ include file="/WEB-INF/jsp/ad/ad_footer.jsp"%>


</body>


