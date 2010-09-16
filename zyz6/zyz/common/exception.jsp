<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>异常</title>
	</head>
	<body>
	<%
	try {
		// The Servlet spec guarantees this attribute will be available
		Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception"); 
		if (exception != null) {
			if (exception instanceof ServletException) {
				// It's a ServletException: we should extract the root cause
				ServletException sex = (ServletException) exception;
				Throwable rootCause = sex.getRootCause();
				if (rootCause == null)
					rootCause = sex;
				out.println("** Root cause is: "+ rootCause.getMessage());
				rootCause.printStackTrace(new java.io.PrintWriter(out)); 
			}else {
				// It's not a ServletException, so we'll just show it
				exception.printStackTrace(new java.io.PrintWriter(out)); 
			}
		} else  {
	    	out.println("No error information available");
		} 
		// Display cookies
		out.println("\nCookies:\n");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
	    	for (int i = 0; i < cookies.length; i++) {
	      		out.println(cookies[i].getName() + "=[" + cookies[i].getValue() + "]");
			}
		}
	} catch (Exception ex) { 
		ex.printStackTrace(new java.io.PrintWriter(out));
	}
	%>
	</body>
</html>