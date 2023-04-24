<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="in.ineuron.dto.Admin" %>
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
	<c:when test="${admin ne null && !empty admin }">
<form action="./admin/updateDetails" method="post">
		<table class="tab">
		
			<tr>
				<th>Admin Id</th>
				<td><input type="text" name="adminId" id="" value="${admin.aid  }" readonly="readonly" class="s1"/></td>
			</tr>
			<tr>
				<th>Admin Name</th>
				<td><input type="text" name="adminName" id="" value="${admin.aname  }" placeholder="${admin.aname  }" class="s1"/></td>
			</tr>
			<tr>
				<th>Mail</th>
				<td><input type="text" name="adminEmail" id="pass"  value="${admin.aemail }"  readonly="readonly" required class="s1"/></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td><select name="gender" required>
					<option value="male" <% Admin adminDetails=(Admin)session.getAttribute("admin"); if(adminDetails.getGender().equals("male")) { %>selected<% } %> >Male</option>
					<option value="female" <%if(adminDetails.getGender().equals("female")) { %>selected<% } %> >Female</option>
						
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