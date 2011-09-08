<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.mitian.airad.web.auth.roles.*"%>
<%@page import="com.mitian.airad.*"%>
<%@page import="com.mitian.airad.common.auth.*"%>
<%@page import="com.mitian.airad.common.auth.context.SecurityContext"%>
<%@page import="com.mitian.airad.common.auth.context.SecurityContextHolder"%>
<%@page import="com.mitian.airad.web.form.*"%>
<%@page import="com.mitian.airad.model.SysConfig"%>
<%@page import="com.mitian.airad.model.CoreMemberInfo"%>
<c:set value="${cookie.ct.value eq '2'}" var="isCurrentDevRole" />
<%
SecurityContext context=SecurityContextHolder.getContext();
BaseRole baseRole=null;
String loginEmail=null;
CoreMemberInfo memberInfo=null;
AbstractForm abstractForm=null;
if(context!=null){
memberInfo=context.getMemberInfo();
    loginEmail=memberInfo.getEmail();
  baseRole=memberInfo.getRole();
  abstractForm=(AbstractForm)request.getAttribute(Constants.DEFAULT_COMMAND);
  Boolean isMemberPage=abstractForm instanceof MemberForm;
  String navClass="";
  String navAccountClass="";
  if(isMemberPage){
      navAccountClass="now";
  }else{
      navClass="now";
  }
  request.setAttribute("loginEmail",loginEmail);
  request.setAttribute("isMemberPage",isMemberPage);
  request.setAttribute("navClass",navClass);
  request.setAttribute("navAccountClass",navAccountClass);
  request.setAttribute("baseRole",baseRole);
}
%>
<link type="text/css" href="/style/main.css" rel="stylesheet" />
<script type="text/javascript" src="/js/jquery.js"></script>
<div id="header">
  <div class="headCon">
      <input type="hidden" value="${command.action }" name="actionType" />
<c:choose>
<c:when test="${baseRole.dev}">
<%@ include file="/WEB-INF/jspf/role_dev_header.jspf"%>
</c:when>
<c:when test="${baseRole.advAndDev}">
<%@ include file="/WEB-INF/jspf/role_dev_and_adv_header.jspf"%>
</c:when>
<c:when test="${baseRole.advertisers}">
<%@ include file="/WEB-INF/jspf/role_adv_header.jspf"%>
</c:when>
<c:when test="${baseRole.agents}">
<%@ include file="/WEB-INF/jspf/role_agent_header.jspf"%>
</c:when>
<c:when test="${baseRole.shopper}">
<%@ include file="/WEB-INF/jspf/role_shopper_header.jspf"%>
</c:when>
<c:when test="${baseRole.ossSales}">
<%@ include file="/WEB-INF/jspf/role_oss_sales_header.jspf"%>
</c:when>
<c:when test="${baseRole.general}">
<%@ include file="/WEB-INF/jspf/role_general_header.jspf"%>
</c:when>
<c:otherwise>
<%@ include file="/WEB-INF/jspf/role_not_logon.jspf"%>
</c:otherwise>
</c:choose>
</div>
</div>
<script>
function exchange(){
    $("#imenu").toggle();             
}
function addTitleCss(){
  var d=document.getElementById("logonShowDiv");
  if(d==null||d=='undefine'){
    $("#header").css("height","35px");//添加样式
  }
}
addTitleCss();
</script>