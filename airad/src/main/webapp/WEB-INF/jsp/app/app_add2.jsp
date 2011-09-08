<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>应用程序信息</title>
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
      <%@ include file="/WEB-INF/jspf/errors.jsp" %>
<!-- 程序开始 -->    

<div class="leftCon">
<div class="okBox">
<div class="ok" id="okTip">
添加成功
</div>
</div>
<h1>应用程序信息</h1>
<form:form action="app.do?action=doAdd" method="post" commandName="command" name="confirmAddApp">
<table border="0" cellspacing="0" cellpadding="0" class="tabNF">
        <col width="30%" />
        <col width="70%" />
        <tr>
          <th>应用名称</th>
          <td>
            <c:out value="${form.app.appName}"/>
          </td>
        </tr>
        <tr>
          <th>应用分类</th>
          <td>
          ${form.appType}          
          </td>
        </tr>
        <tr>
          <th>应用使用平台</th>  
          <td>
            ${form.appPlatformType}   
          </td>
        </tr>
        <tr>
          <th>应用描述</th>
          <td>
           <span style="word-wrap:break-word;word-break:overflow:hidden;"> <c:out value="${form.app.appDesc}"/></span>
          </td>
        </tr>
        <tr>
          <th>应用ID</th>
          <td>
           ${form.app.appCode}  
          </td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td><div class="btnBox">
          <a href="${form.downLoad}" class="btnY fl"><span>点击下载 SDK</span></a> <div class="moreBtn"><span class="gray">|</span> <a href="app.do?action=listApp&currentPage=${form.currentPage}">返回</a></div></div>
          </td>
        </tr>
        <form:hidden path="app.appName"/>
        <form:hidden path="app.appType"/>
        <form:hidden path="app.appPlatformType"/>
        <form:hidden path="app.userSex"/>
        <form:hidden path="app.userAgePag"/>
        <form:hidden path="app.downUrl"/>
        <form:hidden path="app.pubOutside"/>
        <form:hidden path="app.admobCode"/>
        <form:hidden path="app.appDesc"/>
        <form:hidden path="app.appCode"/>
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
  <script type="text/javascript">
  //让层5秒后消失
  $("#okTip").show();
  $("#okTip").animate({
      opacity: 1.0
  }, 5000).fadeOut("slow", function() {
      $(this).hide();
  });
  </script>
  <%@ include file="/WEB-INF/jspf/footer.jsp" %>
  <script>
  $(document).ready(function(){
    addCss("listApp");
    });
  </script>
  </body>
</html>