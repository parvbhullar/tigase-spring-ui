<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.linkage.app.gqt.backstage.helping.controller.HelpingController;"%>
<%@ include file="/common/jstl_contexpath.jsp"%>


<script type="text/javascript">
	function select2(i)
	{
		document.getElementById("HName").value = document.getElementById("aname"+i).innerText;
		document.getElementById("HAddress").value = document.getElementById("address"+i).innerText;		
		$('#search-helping').dialog('close');					
	}

</script>


<table width="90%" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<th width="15%" class="left_txt2">帮扶者姓名</th>
			<th width="30%" class="left_txt2">帮扶者身份证号码</th>
			<th width="35%" class="left_txt2">帮扶者地址</th>
			<th width="15%" class="left_txt2">操作</th>
		</tr>
		<c:forEach var="assist" items="${assist}" varStatus="st">
		
		<tr height="10" bgcolor="#f2f2f2" >
														
		<td align="center" id="aname${st.index}">${assist.name }</td>
		<td align="center" >${assist.credcode }</td>
		<td align="center" id="address${st.index}">${assist.address }</td>
		<td align="center">
			<button onclick="select2(${st.index})">选择</button>											
		</td>
		</tr>
		</c:forEach>
		
		</table>