<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/common/jstl_contexpath.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


<a href="add.action">新增</a>


<table width="100%" border="1" cellspacing="0" cellpadding="0" id="orgtypetabletree">
           										<thead>
													<tr>
														<th width="20%" class="left_txt2">日志标题</th>
														<th width="10%" class="left_txt2">日志内容</th>
														<th width="10%" class="left_txt2">结束日期</th>
														<th width="10%" class="left_txt2">活动状态</th>
														<th width="50%" class="left_txt2">操作</th>
													</tr>
												</thead>	
												<tbody>
													<c:forEach var="diary" items="${diaryList}" varStatus="status">
													<c:if test="${(status.count-1) % 2 ==0 }">
													</c:if>
													<c:if test="${(status.count-1) % 2 !=0}">
													</c:if>
													<td align="center"><a href="view.action?recid=${diary.recid}">${diary.title }</a></td>
													<td align="center">${diary.content }</td>
													<td align="center">${diary.content }</td>
													<td align="center">${diary.content }</td>
													<td align="center">
													</td>
													<td align="center">
													</td>
														</tr>
													</c:forEach>
												</tbody>
           									</table>
</body>
</html>