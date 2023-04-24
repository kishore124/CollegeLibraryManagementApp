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
	<h1>Issue Book</h1>

	<c:choose>
		<c:when test="${bookDetails.status eq 'available'}">
		<h2 style="text-align: center;font-size: 30px;">Book Details</h2>
			<table class="bookTable">
				<tr>
					<th>Book Id</th>
					<th>Book Name</th>
					<th>Author</th>
					<th>Status</th>
				</tr>
				<tr>
					<td>${bookDetails.bookId }</td>
					<td>${bookDetails.bookName }</td>
					<td>${bookDetails.authorName }</td>
					<td>${bookDetails.status }</td>
				</tr>
			</table>

			<br>
			<br>

			<div class="form">
				<form action="../student/issue_searchStudentById" method="post">
					<table>
						<tr>
							<td>Student Id</td>
							<td><input type="text" name="studentId"
								placeholder="enterStudnetID" class="s2" required="required" /></td>
							<td><input type="hidden" name="bookDetails"
								value="${bookDetails }" /></td>
							<td><input type="submit" name="" value="search" class="s1"></td>
						</tr>
					</table>
				</form>
			</div>
		</c:when>
		<c:when test="${bookDetails.status eq 'issued'}">
			<h1 style="color: red;">Book Has Already Being Issued.</h1>
		</c:when>
		<c:when test="${bookDetails.status eq null}">
		<div class="form">
				<form action="../book/issue_searchBookById" method="post">
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
		<c:when test="${bookDetails.status eq 'lost'}">
		<h1 style="color: red;">Book is Deleted Cannot Issue</h1>
		</c:when>
		<c:otherwise>
			<h1 style="color: red;">Some Issued has occurred please try
				again.</h1>
		</c:otherwise>
	</c:choose>
</body>
</html>