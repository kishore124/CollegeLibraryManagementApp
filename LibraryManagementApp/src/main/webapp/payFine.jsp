<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style >
<%@ include file="issueBook.css" %>
</style>
<title>Pay Fine</title>
</head>
<body>
	<h1>Pay Student Fine</h1>
	<div class="form">
		<form action="./student/toPayFineSearchStudentById" method="post">
			<table>
				<tr>
					<td>Student Id</td>
					<td><input type="text" name="studentId" placeholder="enterStudentId" class="s2" required="required"/></td>
					<td><input type="submit" name="" value="search" class="s1"></td>
				</tr>

			</table>
		</form>
	</div>
<c:if test="${error ne null }">
	<h1 style="color: red;text-align: center;">${error }</h1>
</c:if>	

</body>
</html>