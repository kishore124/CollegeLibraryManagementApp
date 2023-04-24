<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
<%@ include file="formRegister.css" %>
</style>
<title>Student Register Form</title>
</head>
<body bgcolor="#c2d6d6">
	<h1>Welcome to INeuron College Library</h1>
	<h1>Student Registration</h1>


	<form action="./studentRegister" method="post">
		<table class="table">
			<tr>
				<th>FullName</th>
				<td><input type="text" name="studentName" id=""
					required="required" class="input"/></td>
				<td><font color='red'> <c:if
							test="${sname_error_msg ne null }">${sname_error_msg}</c:if></font></td>
			</tr>
			<tr>
				<th>Email</th>
				<td><input type="email" name="studentEmail" id=""
					required="required" class="input"/></td>
				<td><font color='red'> <c:if
							test="${semail_error_msg ne null }">${semail_error_msg}</c:if></font></td>
			</tr>
			<tr>
				<th>Gender</th>
				<td>
				<input type="radio" name="stdGender" value="male" id="male"> <label for="male">Male</label>
				<input type="radio" name="stdGender" value="female" id="female"> <label for="female">Female</label><br>
			</tr>
			<tr>
				<th>Password</th>
				<td><input type="password" name="studentPassword" id="pass"
					required="required" class="input"/></td>
				<td><font color='red'> <c:if
							test="${pss_error_msg ne null }">${pss_error_msg}</c:if></font></td>
			</tr>
			<tr>
				<th>Confirm password</th>
				<td><input type="text" name="stdConfirmPassword" id=""
					required="required" class="input"/></td>
			</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="register" class="submitButton"></td>
			</tr>
		</table>
	</form>
	<c:if test="${mailStatus ne null }">
		<h1 style="color: red">"${mailStatus}"</h1>
	</c:if>
	<c:if test="${erroMessage ne null }">
		<h1 style="color: red">"${erroMessage}"</h1>
	</c:if>

	<p>
		<b> 
			Minimum character length is '4' for Name<br> The Email id should
			end with "@ineuron.ai" ,Ex:-******@ineuron.ai"<br>
			Minimum password length should be Greater then '5'
		</b>
	</p>

</body>
</html>