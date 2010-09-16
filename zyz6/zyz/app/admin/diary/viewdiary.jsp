<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/jstl_contexpath.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<form name="createForm" action="reply.action" method="post">
<input type="hidden" name="srcrecid" value="${diary.recid}"/>
${diary.title}<br>
${diary.content}<br>
<textarea name="content" id="content" cols="50" rows="5" > </textarea>
<table>
<c:forEach var="diaryReply" items="${diaryReplyList}" varStatus="status">
<tr><td>${ diaryReply.content}</td></tr>
</c:forEach>
</table>
<input type="submit" value="回复">
</form>
</body>
</html>