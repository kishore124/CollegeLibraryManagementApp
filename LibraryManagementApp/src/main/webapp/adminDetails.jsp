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
	<c:if test="${admin ne null }">
		<h1>Admin Details</h1>
			<table class="bookTable">
				<tr>
					<th>Admin Id</th>
					<th>Admin Name</th>
					<th>Gender</th>
					<th>Mail Id</th>
					<th>Status</th>
				</tr>
				<tr>
					<td>${admin.aid }</td>
					<td>${admin.aname }</td>
					<td>${admin.gender }</td>
					<td>${admin.aemail }</td>
					<td>${admin.status }</td>
				</tr>
			</table>
</c:if>
<br>
<div class="d1">
		<a href="editAdminData.jsp"><input type="button" value="updateDetails" class="button" /></a>
	</div>
	<br />
	<div class="d2">
		<a href="changeAdminPassword.jsp"><input type="button" value="ChangePassword" class="searchButton" /></a>
	</div>

</body>
</html>