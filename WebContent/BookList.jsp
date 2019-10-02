<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class = "container">
<div class = "links">
<h1><a href ="list">Book Store</a></h1>
<h2><a href = "new">Add New Book </a></h2>
</div>
<form name ="book_form" method = "post" action="insert">
<caption><h2>New Book Form</h2>
<p><label>Title: </label>
<input type = "text" name="booktitle"/>
</p>
</caption>
</form>
</div>
	<c:forEach items="${book_list}" var="item">
		<p>Book: ${item}</p>
	</c:forEach>
</body>
</html>