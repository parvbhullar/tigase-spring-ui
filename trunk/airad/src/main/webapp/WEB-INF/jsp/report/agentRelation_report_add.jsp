<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>广告商报表添加</title>
    <link href="/js/tree/tree.css"  type="text/css" rel="stylesheet"/>
  </head>
  <body>
  <%@ include file="/WEB-INF/jspf/header.jsp" %>
  <%@ include file="/WEB-INF/jspf/calendar.jsp"%>
  <div id="main">
    <div class="mainCon">
      <%@ include file="/WEB-INF/jspf/errors.jsp" %>
<!-- 程序开始 -->    
<div class="leftCon">
<h1>广告商报表</h1>
<form:form action="report.do?action=doReport" method="post" commandName="command">
<form:hidden path="report.reportCon"/>
<form:hidden path="currentPage"/>
<form:hidden path="dateFlag"/>
<form:hidden path="flag"/>
     <%@ include file="/WEB-INF/jsp/report/comm_report.jsp" %>
 </form:form>
</div>

</div>
         
    </div>
  <%@ include file="/WEB-INF/jspf/footer.jsp" %>
  <script type="text/javascript" src="/js/tree/js/jquery.simple.tree.self.js"></script>
  <script type="text/javascript" src="/js/report.js"></script>
  <script type="text/javascript">
  $(document).ready(function() {
	    addCss("listReport");
      var obj = $(":radio:checked");
      loadData(obj);
      checkDateType();
  });
  </script>
  </body>
</html>