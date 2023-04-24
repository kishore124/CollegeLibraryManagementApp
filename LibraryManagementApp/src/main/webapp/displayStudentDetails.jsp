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
	<h1>Student Details</h1>
	<br>
	<c:choose>
		<c:when test="${studentData ne null && !empty studentData}">
			<table class="bookTable">
				<tr>
					<th>Student Id</th>
					<th>Student Name</th>
					<th>Email</th>
					<th>Gender</th>
					<th>Status</th>
					<th>Fine</th>
				</tr>
				<tr>
					<td>${studentData.sid}</td>
					<td>${studentData.sname}</td>
					<td>${studentData.semail}</td>
					<td>${studentData.sgender}</td>
					<td>${studentData.status}</td>
					<td>${studentData.fine}</td>
				</tr>
			</table>

			<br>
		</c:when>
		<c:otherwise>
			<h1 style="text-align: center;color: red">Some issue has occurred</h1>
		</c:otherwise>
	</c:choose>


</body>
</html>