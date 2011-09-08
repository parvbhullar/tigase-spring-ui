<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="com.chinapay.util.*"%>
<%@ page import="com.chinapay.util.ChinapayNotify"%>
<%@ page import="com.chinapay.config.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付成功客户端返回</title>
<style type="text/css">
.font_content {
	font-family: "宋体";
	font-size: 14px;
	color: #FF6600;
}

.font_title {
	font-family: "宋体";
	font-size: 16px;
	color: #FF0000;
	font-weight: bold;
}

table {
	border: 1px solid #CCCCCC;
}
</style>

</head>
<%
	String TransDate = null;
	String MerId = null;
	String OrdId = null;
	String TransType = null;
	String TransAmt = null;
	String CuryId = null;
	String ChkValue = null;
	String OrderStatus = null;
	String GateId = null;
	try {
		TransDate = request.getParameter("transdate");
		MerId = request.getParameter("merid");
		OrdId = request.getParameter("orderno");
		TransType = request.getParameter("transtype");
		TransAmt = request.getParameter("amount");
		CuryId = request.getParameter("currencycode");
		OrderStatus = request.getParameter("status");
		ChkValue = request.getParameter("checkvalue");
		ChinapayNotify ch=new ChinapayNotify();
        chinapay.SecureLink t= ch.repsKey(MerId);
        boolean flag1;
	    String msg = "";
        flag1 = t.verifyTransResponse(MerId, OrdId, TransAmt, CuryId,
				TransDate, TransType, OrderStatus, ChkValue);
		if (flag1 == false) {
			System.out.println("交易验证失败!");
			msg = "交易验证失败";
		} else {
			/* …...
			 数据库更新等相关处理过程
			 */
		}
	} catch (Exception e) {
		out.println(e.getMessage());
		return;
	}
%>

















</html>