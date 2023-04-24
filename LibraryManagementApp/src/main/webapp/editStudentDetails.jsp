<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="in.ineuron.dto.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Book Details</title>
<style>
<%@ include file="BookDataAddForm.css" %>
</style>
</head>
<body>
<h1>Update Your Details</h1>
<c:if test="${ename_error_msg ne null }">
	<h1 style="color: red;">${ename_error_msg }</h1>
</c:if>
<c:choose>
	<c:when test="${studentData ne null && !empty studentData }">
<form action="./student/updateDetails" method="post">
		<table class="tab">
		
			<tr>
				<th>Student Id</th>
				<td><input type="text" name="StudentId" id="" value="${studentData.sid  }" readonly="readonly" class="s1"/></td>
			</tr>
			<tr>
				<th>Student Name</th>
				<td><input type="text" name="StudentName" id="" value="${studentData.sname  }" placeholder="${admin.aname  }" class="s1"/></td>
			</tr>
			<tr>
				<th>Mail</th>
				<td><input type="text" name="StudentEmail" id="pass"  value="${studentData.semail }"  readonly="readonly" required class="s1"/></td>
				<td><input type="hidden" name="studentFine" id=""  value="${studentData.fine }" /></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td><select name="stdgender" required>
					<option value="male" <% Student std=(Student)session.getAttribute("studentData"); if(std.getSgender().equals("male")) { %>selected<% } %> >Male</option>
					<option value="female" <%if(std.getSgender().equals("female")) { %>selected<% } %> >Female</option>
						
				</select></td>

			</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="update" class="submitButton"></td>
			</tr>
		</table>
	</form>
</c:when>
<c:when test="${empty admin && admin eq null}">
	<h1 style="color: red">Some Issue has Occurred please try after some time</h1>
</c:when>
</c:choose>
</body>
</html>