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
		<a href="searchBookById.jsp"><input type="button" value="searchBookById" class="button" /></a>
	</div>
	<br />
	<div class="d2">
		<a href="searchBookByTitle.jsp"><input type="button" value="searchBookByTitle" class="searchButton" /></a>
	</div>
	<br>
	<div class="d2">
		<a href="searchBookByCategory.jsp"><input type="button" value="searchBookByCategory" class="deleteButton" /></a>
	</div>
   <br>
	<div class="d2">
		<a href="searchBookByAuthorName.jsp"><input type="button" value="searchBookByAuthorName" class="button" /></a>
	</div>
</body>
</html>