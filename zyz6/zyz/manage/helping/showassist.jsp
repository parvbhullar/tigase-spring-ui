<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.linkage.app.gqt.backstage.helping.controller.HelpingController;"%>
<%@ include file="/common/jstl_contexpath.jsp"%>


<script type="text/javascript">
	function select(i)
	{
		document.getElementById("VName").value = document.getElementById("name"+i).innerText;
		document.getElementById("VTel").value = document.getElementById("tel"+i).innerText;
		document.getElementById("VAreaid").value = document.getElementById("nid"+i).value;	
		document.getElementById("v_id").value = document.getElementById("vid"+i).value;
		
		//alert(document.getElementById("v_id").value);
		document.getElementById("HTypeid").value = ${id};
		document.getElementById("sysValue").value = ${sysValue};
		$('#search-postulant').dialog('close');			
	}

</script>


<table width="90%" border="1" cellspacing="0" cellpadding="0" id="orgtypetabletree">
		<tr>
			<th width="15%" class="left_txt2">志愿者姓名</th>
			<th width="25%" class="left_txt2">志愿者电话</th>
			<th width="30%" class="left_txt2">志愿者所属区域</th>
			<th width="30%" class="left_txt2">操作</th>
		</tr>
		<c:forEach var="postulant" items="${postulant}" varStatus="st">
		
		<tr height="10" bgcolor="#f2f2f2" >
														
		<td align="center" id="name${st.index}">${postulant.name }</td>
		<td align="center" id="tel${st.index}">${postulant.dn }</td>
		<td align="center">${postulant.communityname }
			<input type="hidden" value="${postulant.communityid}" id="nid${st.index}"  />
			<input type="hidden" value="${postulant.id}" id="vid${st.index}"  />
		</td>
		<td align="center">
			<button onclick="select(${st.index})">选择</button>&nbsp;
			<button onclick="sendMessage(${postulant.dn })">发送短信</button>											
		</td>
		</tr>
		</c:forEach>
		
		<tr><td colspan=4 align="right">${toolNav}</td></tr>
		</table>