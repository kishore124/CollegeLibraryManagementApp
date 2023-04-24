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
		<c:when test="${!empty adminSearch_bookDetails && adminSearch_bookDetails ne null }">
			<table class="bookTable">
				<tr>
					<th>Book Id</th>
					<th>Book Name</th>
					<th>Author</th>
					<th>Status</th>
					<th>Action</th>
				</tr>
				<tr>
					<td>${adminSearch_bookDetails.bookId }</td>
					<td>${adminSearch_bookDetails.bookName }</td>
					<td>${adminSearch_bookDetails.authorName }</td>
					<td>${adminSearch_bookDetails.status }</td>
					<c:if test="${adminSearch_bookDetails.status ne 'lost'}">
							<td><a href="../book/edit/searchBookById?bookId=${adminSearch_bookDetails.bookId }"><input type="button" name="" value="edit"
								class="editButton"></a>
							</td>
					</c:if>
					<c:if test="${adminSearch_bookDetails.status eq 'lost'}">
							<td><h2>Can't edit</h2>
							</td>
					</c:if>
				</tr>
				
			</table>

			<br>
			<br>
		</c:when>
		<c:when test="${adminSearch_bookDetails.status eq null}">
		<div class="form">
				<form action="../book/admin_searchBookById" method="post">
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
			<h1 style="color: red;">Please Enter a valid Book Id Number.</h1>
		</c:when>
		<c:otherwise>
			<h1 style="color: red;">Some Issued has occurred please try
				again.</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>