<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style>
<%@ include file="booksList.css" %>
</style>
<title>Books List</title>
</head>
<body>
	<h1 style="font-size: 55px">Books Details</h1>
	<br>
	<c:choose>
		<c:when test="${booksList ne null && ! empty booksList}">
	
			<table class="bookTable">
				<tr>
					<th>Book Id</th>
					<th>Book Name</th>
					<th>Author</th>
					<th>Publisher</th>
					<th>Price</th>
					<th>Category</th>
					<th>Status</th>
				</tr>
				<c:forEach var="bookdetails" items="${booksList}">
					<tr>
						<td>${bookdetails.bookId }</td>
						<td>${bookdetails.bookName }</td>
						<td>${bookdetails.authorName }</td>
						<td>${bookdetails.publisherName }</td>
						<td>${bookdetails.price }</td>
						<td>${bookdetails.categoryName }</td>
						<td>${bookdetails.status }</td>
					</tr>

				</c:forEach>
			</table>
		</c:when>
		<c:when test="${empty booksList }">
			<h1 style="color: red;">No Books to display</h1>
		</c:when>
	</c:choose>
	<c:if test="${booksList eq null }">
		<h1 style="text-align: center; color: red;">Some Issue Has
			Occurred Please Try After Some Time</h1>
	</c:if>
</body>
</html>