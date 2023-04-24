<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Status</title>
</head>
<body bgcolor=" #b3ffe6">
	<br>
	<br>
	<br>
<h1 style="text-align: center;">STATUS</h1>
	<c:if test="${issueBookStatus eq 'success' }">
		<h1 style="color: green; text-align: center;">Issued Book to Student Successful Book Id :: ${bookDetails.bookId }</h1>
	</c:if>
	<c:if test="${issueBookStatus eq 'failure' }">
		<h1 style="color: green; text-align: center;">Issued Book to
			Student UnSuccessful Please Try Again</h1>
	</c:if>
	<c:if test="${bookIssued ne 'success' }">
		<h1 style="color: green; text-align: center;">${bookIssued }</h1>
	</c:if>

	<c:if test="${errorMessage ne null }">
		<h1 style="color: green; text-align: center;">${errorMessage }</h1>
	</c:if>
	
	<c:if test="${booksTableStatus eq 'success' && returnBookstatus eq 'success' && stdStatus eq 'success'}">
		<h1 style="color: green; text-align: center;">Book Returned Successful</h1>
	</c:if>
	
	<c:if test="${booksTableStatus eq 'failure' && stdStatus eq 'failure' && returnBookstatus eq 'failure'}">
		<h1 style="color: red; text-align: center;">Book Returned Successful Please Try Again</h1>
	</c:if>
	
</body>
</html>