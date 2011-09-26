<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑贫媒体</title>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jsp"%>
<div id="main">
<div class="mainCon"><%@ include file="/WEB-INF/jspf/errors.jsp"%>
<!-- 开发嵌入start-->
<div class="leftCon">
<h4>编辑贫媒体</h4>
<div id="errAdDiv" style="color: red;" ></div>
<form:form name="myfrm" action="ad.do?action=editWapAd" commandName="command"  method="post">
 <table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <col width="20%" /> <col width="80%" />
        <tr>
          <th>广告名称</th>
          <td>
          <form:input path="wap.adName" cssClass="long" />
          <input type="hidden" name="adGroupId" value="${command.wap.adGroupId }" />
           <form:hidden path="wap.adGroupId"/>
           <form:hidden path="wap.wapId"/>
            <form:hidden path="wap.adId"/>
             <form:hidden path="wap.campaignId"/> ${wap.campaignId}
         </td>
        </tr>
 </table>  
 <h4>Wap 制作</h4>
<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
        <col width="20%" />  <col width="80%" />
        
        <tr>
    <th>Wap 标题</th>
    <td> <form:input path="wap.wapTitle" cssClass="long" /></td>
    </tr>
  <tr>
    <th>wap内容</th>
    <td><form:textarea path="wap.wapContent" cssClass="long"/> </td>
  </tr>
  <tr>
    <th>联系电话</th>
    <td><form:input path="wap.telephone" cssClass="long"/> </td>
  </tr>
  <tr>
    <th>点击拨打电话</th>
    <td><form:input path="wap.clickTel" cssClass="long"/> </td>
  </tr>
  <tr>
    <th>选择发送短信</th>
    <td><form:input path="wap.selSendType" cssClass="long"/> </td>
  </tr>
  <tr>
    <th>传真</th>
    <td><form:input path="wap.fax" cssClass="long"/> </td>
  </tr>
  <tr>
    <th>email</th>
    <td><form:input path="wap.email" cssClass="long"/> </td>
  </tr>
    <tr>
    <th>QQ</th>
    <td><form:input path="wap.qq" cssClass="long"/> </td>
  </tr>
  <tr>
    <th>MSN</th>
    <td><form:input path="wap.msn" cssClass="long"/> </td>
  </tr>
  <tr>
    <th>联系地址</th>
    <td><form:input path="wap.contactAddress" cssClass="long"/> </td>
  </tr>
  <tr>
    <th>链接文本</th>
    <td><form:input path="wap.linkText" cssClass="long"/> </td>
  </tr>
  <!-- 
  <tr>
    <th>Url地址</th>
    <td><form:input path="wap." cssClass="long"/> </td>
  </tr>
   <tr>
    <th>  WAP链接文本</th>
    <td><form:input path="wap." cssClass="long"/> </td>
  </tr>
  <tr>
    <th>WAP链接URL</th>
    <td> <form:input path="wap." cssClass="long"/> </td>
  </tr>
 -->
  
</table>
<h4>出价</h4>
<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
        <col width="20%" />  <col width="80%" />
    <tr> 
	    <th>出价</th>
	    <td><sup>&yen;</sup> <input class="tiny" /></td>
    </tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" class="tabNF">
        <col width="20%" />
        <col width="80%" />
  <tr>
    <th>&nbsp;</th>
    <td><div class="btnBox">
    <button type="submit" class="btnBY fl">提交，完成广告编辑</button>
      <div class="moreBtn"><span class="gray">|</span> <input type="button" onclick="javascript:return_adList_page()" value="取消" /></div>
      </div></td> 
    </tr> 
</table>
 
</form:form>
<script type="text/javascript">

  function submitForm(){
	  var name =document.getElementById("wap.adName").value;
	  var title =document.getElementById("wap.wapTitle").value;
	    if(name!=""&&name!=null&&title!=""&&title!=null){
	    	document.getElementById("errAdDiv").innerHTML="";
	    	document.forms[0].submit();
	    }else{
	    	  document.getElementById("errAdDiv").innerHTML="广告名称和Wap 标题不能为空！";
		   }
	  
	 }


</script>
</div>
<div class="rightCon">
<div class="infoCon">
<h2><img src="images/ico_act.gif" alt="广告组" width="16" height="16"
  align="absmiddle" />广告组摘要</h2>
<ul>
  <li><span class="fr"></span>名称:${cag.adGroupName}</li>
</ul>
<h2><img src="images/ico_gup.gif" alt="活动" width="16" height="16"
  align="absmiddle" />广告摘要</h2>
<ul>
  <li>名称: </li>
  <li>Wap 标题: <br /> </li>
  <li>联系地址: </li>
</ul>
</div>
</div>
<!-- 开发嵌入end--></div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jsp"%>
</body>
</html>