<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css"><%@ include file="books.css" %></style>

<title>Books</title>
</head>
<body>
	<h1>Books Related Operations</h1>
	<div class="d1">
		<a href="BookDataAddForm.jsp"><input type="button" value="AddNewBook" class="button" /></a>
	</div>
	<br />
	<div class="d2">
		<a href="book/getBooksList"><input type="button" value="GetBooksList" class="searchButton" /></a>
	</div>
	<br>
	<div class="d2">
		<a href="deleteBook.jsp"><input type="button" value="DeleteBook" class="deleteButton" /></a>
	</div>

</body>
</html>