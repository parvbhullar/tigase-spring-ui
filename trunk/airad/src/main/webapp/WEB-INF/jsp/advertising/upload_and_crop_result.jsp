<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>upload result</title>
    <script type="text/javascript">
    window.onload=function(){
    	 //错误处理
    	 //调用父页面进行相关的处理
    	 if(typeof parent.adO != 'undefined'){
    		  parent.adO.uploadCallback(${result});
    	 }
    }
    </script>
  </head>
  <body>
  </body>
</html>
