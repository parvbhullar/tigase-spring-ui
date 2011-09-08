<%@ page contentType="text/html; charset=UTF-8"%>      
<%@ include file="/WEB-INF/jspf/taglibs.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>airAD － 智慧广告 智慧生活</title>
<link href="style/index.css" rel="stylesheet" type="text/css" />
</head>
  <c:if test="${not empty ispublish}">
       <body>
         <%@ include file="/index.html"%> 
       </body>
  </c:if>
  <c:if test="${empty ispublish}">
   <body style="background:#d9d9d9">
    <div style="background:url(images/intro/intro.jpg) #d9d9d9 no-repeat left top;margin:auto; width:898px;height:603px;position:relative">
    <div style="text-align:center;color:#999;position:absolute;bottom:0;width:898px">
    &copy;2011 米田科技有限公司 版权所有
    </div>
    </div>
     <script type="text/javascript" src="/js/jquery.js"></script>
    <script>
       function isPublish(){
                var url="/sysconfig.do?action=querySysConfigById";
                jQuery.post(url,{r:Math.random()},function(data){
                        if(data=="1"){
                        	 window.clearInterval(timepublish);  
                        	window.location.reload(); //刷新 
                         }
                });   
       }
       var timepublish=window.setInterval(isPublish,5000);
    </script>
    </body>
  </c:if>
</html>
