<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<style ><%@ include file="booksList.css" %></style>
<title>Books List</title>
</head>
<body>
	<h1 style="font-size: 55px">Books Details</h1>
	<div>
		<div class="left-div">
			<form action="../book/admin_searchBookById" method="post">
				<input type="text" value="" placeholder="search book by id" name="bookId" class="searchBox">
				<input type="submit" value="search" name="" class="searchButton">
			</form>
		</div >
		<div class="right-div">
			<a href="../BookDataAddForm.jsp"><input type="button" value="addNew"
			class="addButton"></a>
		</div>
	</div>
		
	<br/>
	<br/>
	<br>
	<table class="bookTable">
		<tr>
			<th>Book Id</th>
			<th>Book Name</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Price</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<c:if test="${booksList ne null}">
			<c:forEach var="bookdetails" items="${booksList}">
				<tr>
					<td>${bookdetails.bookId }</td>
					<td>${bookdetails.bookName }</td>
					<td>${bookdetails.authorName }</td>
					<td>${bookdetails.publisherName }</td>
					<td>${bookdetails.price }</td>
					<td>${bookdetails.status }</td>
					<td><a href="../book/edit/searchBookById?bookId=${bookdetails.bookId }"><input type="button" name="" value="edit"
							class="editButton"></a></td>
				</tr>

			</c:forEach>
		</c:if>
		<c:if test="${booksList eq null}">
		  <h1 style="text-align: center;color: red;">Some Issue Has Occurred Please Try After Some Time </h1>
		</c:if>
	</table>
</body>
</html>