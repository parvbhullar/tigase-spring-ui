<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>广告管理</title>
<link type="text/css" rel="stylesheet" href="/mice/uploadFile/uploadify.css" />
<link type="text/css" rel="stylesheet" href="/style/ad/ad_detail_add.css" />
<link type="text/css" rel="stylesheet" href="/js/validator.css" />
<link type="text/css" rel="stylesheet" href="/style/banner.css" />
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<%@ include file="/WEB-INF/jspf/calendar.jsp"%>
<%@ include file="/WEB-INF/jspf/errors.jsp"%>
<style type="text/css">
#fullbg {
	background-color: Gray;
	display: none;
	z-index: 3;
	position: absolute;
	left: 0px;
	top: 0px;
	filter: Alpha(Opacity = 30);
	/* IE */
	-moz-opacity: 0.4;
	/* Moz + FF */
	opacity: 0.4;
}

#dialog {
	position: absolute;
	width: 200px;
	height: 200px;
	background: #F00;
	display: none;
	z-index: 5;
}
</style>
</head>
<body>

<div class="stepNew">
<table width="100%" cellspacing="0" cellpadding="0" border="0">
<colgroup>
  </colgroup><tbody><tr>
    <td class="now">1. 广告制作</td>
    <td>2. 广告组设置</td>
    </tr>
</tbody></table>
</div>
<div class="leftCon ">
<h1>广告组设置</h1>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
  <colgroup><col width="20%">
  <col width="80%">
  </colgroup><tbody><tr>
    <th>选择广告组</th>
    <td><select name="adGroupSelect" id="adGroupSelect">
      <option selected="selected" value="pleaseChoose">请选择...</option>
      <c:forEach items="${adGroupList}" var="adGroup">
        <option value="${adGroup.adGroupId }">${adGroup.adGroupName }</option>
      </c:forEach>
      <option value="select_creatNewGroup">新建广告组</option>
    </select></td>
  </tr>
</tbody></table>

<!-- 遍历所有广告组,将每个广告组信息存在DIV里面,广告组的ID 为  div_groupIdX   X 为 广告组ID -->
<c:forEach items="${adGroupList}" var="adGroup">
  <div style="display: none" id="div_groupId${adGroup.adGroupId }"  class="groupIds">
<table>
    <tr>
    <th>名称：</th>
    <td>${adGroup.adGroupName }</td>
    <th>所属行业：</th>
    <td>
      <c:forEach items="${industryInvolvedList}" var="dictionaryTypes">
            <c:if test="${cag.adTagSoftType==dictionaryTypes.dictKey}">
            ${dictionaryTypes.dictVal}
            </c:if>
            </c:forEach>
</td>
  </tr>
  <tr>
    <th>平台：</th>
    <td><c:forEach items="${adGroup.platforms}" var="coreAd" varStatus="sta">
          <airad:dictString valueKey="${coreAd}" typeKey="TAG_SP"/>
          <c:if test="${not sta.last}">,</c:if>
        </c:forEach></td>
    <th>地理位置：</th>
    <td>
<c:choose>
          <c:when test="${adGroup.adLoclType==0}">全国</c:when>
          <c:when test="${adGroup.adLoclType=='2'}">精确到区</c:when>
          <c:otherwise>常用地区</c:otherwise> </c:choose>
</td>
  </tr>
</table>
  </div>
</c:forEach>
<div style="display: none" id ="div_creatNewGroup" class="info">
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <colgroup><col width="20%">
        <col width="80%">
        </colgroup><tbody><tr>
          <th>广告组名称</th>
          <td><input class="long"></td>
        </tr>
        <tr>
          <th>地理位置</th>
          <td><div class="selectBox">
          全国</div></td>
        </tr>
        <tr>
          <th>所属行业</th>
          <td><select name="select" id="select">
            <option selected="selected">影音娱乐</option>
            <option>啥啥啥</option>
          </select></td>
        </tr>
        <tr>
          <th>平台</th>
          <td><input type="checkbox" name="checkbox2" id="checkbox2">Android
          <input type="checkbox" name="checkbox" id="checkbox">iPhone</td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox">
           <a href="list.php" class="btnY fl"><span>提交</span></a><div class="moreBtn"><span class="gray">|</span> <a href="javascript:history.go(-1)">取消</a></div>
           </div>
            </td>
        </tr>
      </tbody></table>
</div>


<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
  <colgroup><col width="20%">
  <col width="80%"> </colgroup><tbody><tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox">
           <a href="list.php" class="btnY fl"><span>提交，完成广告制作</span></a><div class="moreBtn"><span class="gray">|</span> <a href="owner-ad-gup.php">取消，返回上一步</a></div>
           </div>
          </td>
        </tr>
</tbody>
</table>
</div>
</body>
<script type="text/javascript">
$(function(){
	  $("#adGroupSelect").change(function(){
		  var value = $(this).val();
		  if(value == "select_creatNewGroup"){ //新建广告组
			  $(".groupIds").hide();
			  $("#div_creatNewGroup").show();
			}else if(value == "pleaseChoose"){
				$(".groupIds").hide();
		        $("#div_creatNewGroup").hide();
				}else{
					  $(".groupIds").hide();
		            $("#div_creatNewGroup").hide();
					  var needShowGroupDivStr ="div_groupId"+ value;
					  $("#"+needShowGroupDivStr).show();
			 }
		});
})
</script>