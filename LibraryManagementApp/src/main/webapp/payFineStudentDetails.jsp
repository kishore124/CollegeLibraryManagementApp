<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style >
<%@ include file="issueBook_GetStudentDetails.css" %>
<%@ include file="issueBook.css" %>
</style>
</head>
<body>
<h1>Pay Student Fine</h1>
	<c:choose>
		<c:when test="${student.sid ne null}">
		<h1 style="font-size: 35px;">Student Details</h1>
			<table class="bookTable">
				<tr>
					<th>Student Id</th>
					<th>Student Name</th>
					<th>Fine </th>
				</tr>
				<tr>
					<td>${student.sid }</td>
					<td>${student.sname }</td>
					<td>${student.fine }</td>
				</tr>
			</table>
			<br>
			<div class="form">
			<c:if test="${student.fine ne 0 }">
			<form action="../student/toPayFine" method="post">
				<table class="table1">
					<tr>
						<td>Fine Amount</td>
						<td><input type="text" name="fineAmount" placeholder="enterAmount" class="s2" required="required" /></td>
						<td><input type="hidden" name="studentId" value="${student.sid }" required="required" /></td>
						<td><input type="submit" name="" value="pay" class="s1"></td>
					</tr>
				</table>
			</form>
			</c:if>
		</div>
		</c:when>
		<c:otherwise>
		<div class="form">
			<form action="../student/toPayFineSearchStudentById" method="post">
				<table >
					<tr>
						<td>Student Id</td>
						<td><input type="text" name="studentId" placeholder="enterStudnetID" class="s2" required="required" /></td>
						<td><input type="submit" name="" value="search" class="s1"></td>
					</tr>
				</table>
			</form>
		</div>
			<h1 style="color: red;text-align: center;">Please Enter a valid Student Id Number</h1>
		</c:otherwise>
	</c:choose>


</body>
</html>