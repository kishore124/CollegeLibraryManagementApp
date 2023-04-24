<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
<%@ include file="issueBook_GetStudentDetails.css" %>

<%@ include file="issueBook.css" %>
</style>
</head>
<body>
	<br>
	<c:choose>
		<c:when test="${stdbooksList ne null && !empty stdbooksList}">

			<h1>Issued Book Details</h1>
			<table class="bookTable">
				<tr>
					<th>Student Id</th>
					<th>Book Id</th>
					<th>IssueDate</th>
					<th>ReturnDate</th>
				</tr>
				<c:forEach var="bookdetails" items="${stdbooksList}">
					<tr>
						<td>${bookdetails.stdId }</td>
						<td>${bookdetails.bookId }</td>
						<td>${bookdetails.issueDate }</td>
						<td>${bookdetails.returnDate }</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:when test="${empty stdbooksList}">
			<h1 style="text-align: center; color: red">No Books are issued</h1>
		</c:when>

		<c:otherwise>
			<h1 style="text-align: center; color: red">Some issue has
				occurred</h1>
		</c:otherwise>
	</c:choose>


</body>
</html>