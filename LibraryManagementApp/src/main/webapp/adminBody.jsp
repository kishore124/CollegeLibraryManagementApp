<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
<style ><%@ include file="adminBody.css" %></style>

</head>
<body>
	<h1>Admin DashBoard</h1>
	<div class="d">
		<div class="d1"><span>Total Books : <c:if test="${totalBookCount ne null }">${totalBookCount}</c:if> </span></div>
		<div class="d2"><span>Issued Books : <c:if test="${totalIssuedBooks ne null }">${totalIssuedBooks}</c:if> </span></div>
		<div class="d3"><span>Students Registered : <c:if test="${totalRegisteredStudents ne null }">${totalRegisteredStudents}</c:if> </span></div>
		
	</div>
</body>

</html>