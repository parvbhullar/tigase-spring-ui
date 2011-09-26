<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>应用程序添加</title>
      <style>
    /**此样式可以让表格中有!!!(感叹号)之类的字符时自动换行。**/
    table{table-layout: fixed;}
    /**此样式可以让表格中的一些连续的英文单词自动换行**/
    td{word-break: break-all; word-wrap:break-word;} 
    </style>
  </head>
  <body>
  <%@ include file="/WEB-INF/jspf/header.jsp" %>
  <div id="main">
    <div class="mainCon">
      <%@ include file="/WEB-INF/jspf/errors_detail.jsp" %>
<!-- 程序开始 -->    
<div class="leftCon">
<h1>应用程序新建</h1>
<form:form action="" method="post" commandName="command" name="addApp">
<form:hidden path="appPlatformType"/>
<form:hidden path="appType"/>
<form:hidden path="userSex"/>
<form:hidden path="userAgePag"/>
<form:hidden path="pubOutside"/>
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <col width="30%" />
        <col width="70%" />
        <tr>
          <th><span class="must">*</span>应用名称</th>
          <td><form:input path="app.appName"  cssClass="long" maxlength="50"/></td>
        </tr>
        <tr>
          <th><span class="must">*</span>应用分类</th>
          <td>
            <form:select path="app.appType">
            <c:forEach items="${form.tagTypes}" var="dictionaryTypes">
            <form:option value="${dictionaryTypes.dictKey}" label="${dictionaryTypes.dictVal}"/>
            </c:forEach>
            </form:select>
          </td>
        </tr>
         <tr>
          <th><span class="must">*</span>应用使用平台</th>  
          <td>
            <form:select path="app.appPlatformType">
            <c:forEach items="${form.tagSps}" var="dictionarySps">
            <form:option value="${dictionarySps.dictKey}" label="${dictionarySps.dictVal}"/>
            </c:forEach>
            </form:select>
          </td>
        </tr>
        <tr>
          <th><span class="must"></span>应用程序下载地址</th>
          <td><form:input path="app.downUrl"  cssClass="long" maxlength="100"/></td>
        </tr>
        <tr>
          <th><span class="must">*</span>应用描述</th>
          <td><span style="word-wrap:break-word;word-break:overflow:hidden;"> 
            <form:textarea path="app.appDesc" cssClass="long"/>
            </span>
          </td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
          <div class="btnBox">
             <button type="button" onclick="addAppSub(this);" class="btnBY fl">提交</button>
           <div class="moreBtn"><span class="gray">|</span> <a href="app.do?action=listApp&currentPage=${form.currentPage}">取消</a></div>
           </div>
          </td>
        </tr>
      </table>
 </form:form>
</div>
<div class="rightCon">
<div class="infoCon">
<h2><img src="images/ico_act.gif" alt="活动" width="16" height="16" align="absmiddle" />应用开发者注意事项</h2>
<ol>
<li>请详细、正确的填写应用的相关信息，以便获得更好的广告投放效果。</li>
<li>嵌入SDK中遇到任何问题，您可以联系我们的客服进行反馈。给您带来诸多不便，敬请原谅！</li>
</ol>
</div>
</div>
    </div>
  </div>
  <%@ include file="/WEB-INF/jspf/footer.jsp" %>
    <script>
  $(document).ready(function(){
    addCss("listApp");
    });
  </script>
  </body>
</html>