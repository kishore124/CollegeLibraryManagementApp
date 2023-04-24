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
<%@ include file="adminDetails.css" %>
</style>
</head>
<body>
	<c:if test="${studentData ne null }">
		<h1>Student Details</h1>
			<table class="bookTable">
				<tr>
					<th>Student Id</th>
					<th>Student Name</th>
					<th>Gender</th>
					<th>Mail</th>
				</tr>
				<tr>
					<td>${studentData.sid }</td>
					<td>${studentData.sname }</td>
					<td>${studentData.sgender }</td>
					<td>${studentData.semail }</td>
				</tr>
			</table>
</c:if>
<br>

<c:if test="${pss_error_msg ne null }">
  <h3 style="color: red;text-align: center;">${pss_error_msg }</h3>
</c:if>
<div class="form">
			<form action="./studentPasswordChange" method="post">
				<table>
					<tr>
				<th>Password</th>
				<td><input type="password" name="studentPassword" id="pass"
					required="required" class="input"/></td>
			</tr>
			<tr>
				<th>Confirm password</th>
				<td><input type="text" name="studentConfirmPassword" id=""
					required="required" class="input"/></td>
			</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="changePassword" class="submitButton"></td>
			</tr>
				</table>
			</form>
		</div>
</body>
</html>