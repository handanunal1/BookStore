<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		java.util.Date today = new java.util.Date();
		String message = "Today's date is " + today.toString();
	%>

	<%=message%>

</body>
</html>