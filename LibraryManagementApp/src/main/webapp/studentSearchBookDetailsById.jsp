<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Issue book</title>
<style>
<%@include file="issueBook_bookDetails.css" %>
<%@ include file="issueBook.css" %>
</style>
</head>
<body>
	<h1> Book Details</h1>

	<c:choose>
		<c:when test="${!empty std_BookDetails && std_BookDetails ne null }">
			<table class="bookTable">
				<tr>
					<th>Book Id</th>
					<th>Book Name</th>
					<th>Author</th>
					<th>Category</th>
					<th>Status</th>
				</tr>
				<tr>
					<td>${std_BookDetails.bookId }</td>
					<td>${std_BookDetails.bookName }</td>
					<td>${std_BookDetails.authorName }</td>
					<td>${std_BookDetails.categoryName }</td>
					<td>${std_BookDetails.status }</td>
				</tr>
				
			</table>

			<br>
			<br>
		</c:when>
		<c:when test="${std_BookDetails.status eq null}">
		<div class="form">
				<form action="../book/studentsearchBookById" method="post">
					<table>
						<tr>
							<td>Book Id</td>
							<td><input type="text" name="bookId"
								placeholder="enterBookId" class="s2" required="required" /></td>
							<td><input type="submit" name="" value="search" class="s1"></td>
						</tr>

					</table>
				</form>
			</div>
			<h1 style="color: red;">Please Enter a valid Book Id Number</h1>
		</c:when>
		<c:otherwise>
			<h1 style="color: red;">Some Issued has occurred please try
				again.</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>