<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style >
<%@ include file="issueBook.css" %>
<%@include file="issueBook_bookDetails.css" %>
</style>
<title>IssueBook</title>
</head>
<body>
	<h1> Delete Book</h1>
	<c:choose>
		<c:when test="${deletebook.status ne null}">
		<h1 style="font-size: 30px">Book Details</h1>
			<table class="bookTable">
				<tr>
					<th>Book Id</th>
					<th>Book Name</th>
					<th>Author</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				<tr>
					<td>${deletebook.bookId }</td>
					<td>${deletebook.bookName }</td>
					<td>${deletebook.authorName }</td>
					<td>${deletebook.status }</td>
					<td><a href="../book/deleteBook?bookId=${deletebook.bookId }&status=${deletebook.status }"><input type="submit" name="" value="delete" class="s1"></a></td>
				</tr>
			</table>
	</c:when>
	<c:when test="${deletebook.status eq null}">
	<c:if test="${deletebook.status ne 'lost'}">
	<div class="form">
		<form action="../book/delete_searchBookById" method="post">
			<table>
				<tr>
					<td>Book Id</td>
					<td><input type="text" name="bookId" placeholder="enterBookId" class="s2" required="required"/></td>
					<td><input type="submit" name="" value="search" class="s1"></td>
				</tr>

			</table>
		</form>
	</div>
	</c:if>
	 	<h1 style="color: red;">Please Enter a valid book id</h1>	
	</c:when>
	</c:choose>
</body>
</html>