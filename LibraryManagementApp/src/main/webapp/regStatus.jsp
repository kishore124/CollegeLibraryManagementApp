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


<c:choose>
	<c:when test="${Adminstatus eq 'success'}">
		<h1 style="color: green; text-align: center;">Registration Successful</h1>
		<h1 style="color: #4B0082; text-align: center;"><a href="./adminLogin.jsp">|Login Page|</a></h1>
	</c:when>
	<c:when test="${Adminstatus eq 'failure'}">
		<h1 style="color: green; text-align: center;">Registration UnSuccessful</h1>
		<h1 style="color: #FF4500; text-align: center;"><a href="./adminRegister.jsp">|Register Page|</a> </h1>
	</c:when>
	
	<c:when test="${ !empty Adminstatus && Adminstatus eq null }">
		<h1 style="color: green; text-align: center;">Some Issue has occurred please try after some time</h1>
		<h1 style="color: #FF4500; text-align: center;"><a href="./adminRegister.jsp">|Register Page|</a> </h1>
	</c:when>
</c:choose>

<c:choose>
<c:when test="${studentStatus eq 'success'}">
		<h1 style="color: green; text-align: center;">Registration Successful</h1>
		<h1 style="color: #4B0082; text-align: center;"><a href="./studentLogin.jsp">|Login Page|</a> </h1>
	</c:when>
	<c:when test="${studentStatus eq 'failure'}">
		<h1 style="color: green; text-align: center;">Registration UnSuccessful</h1>
		<h1 style="color: #FF4500; text-align: center;"><a href="./StudentRegister.jsp">|Register Page|</a> </h1>
	</c:when>
	<c:when test="${ !empty studentStatus && studentStatus eq null }">
		<h1 style="color: green; text-align: center;">Some Issue has occurred please try after some time</h1>
		<h1 style="color: #FF4500; text-align: center;"><a href="./adminRegister.jsp">|Register Page|</a> </h1>
	</c:when>
</c:choose>

<c:choose>
    <c:when test="${bookStatus eq 'success' }">
		<h1 style="color: green; text-align: center;">Book Details Entered Successful</h1>
	</c:when>
	
	<c:when test="${bookStatus eq 'failure' }">
		<h1 style="color: green; text-align: center;">Book Details Insertion  UnSuccessful</h1>
	</c:when>
	
	<c:when test="${ !empty bookStatus && bookStatus eq null }">
		<h1 style="color: green; text-align: center;">Some Issue has occurred please try after some time</h1>
		<h1 style="color: #FF4500; text-align: center;"><a href="./adminRegister.jsp">|Register Page|</a> </h1>
	</c:when>
</c:choose>


<c:choose>
	<c:when test="${bookUpdatestatus eq 'success' }">
		<h1 style="color: green; text-align: center;">Book Details Updated Successful</h1>
	</c:when>
	<c:when test="${bookUpdatestatus eq 'failure' }">
		<h1 style="color: green; text-align: center;">Book Details updation UnSuccessful</h1>
	</c:when>
	<c:when test="${!empty bookUpdatestatus && bookUpdatestatus eq null}">
		<h1 style="color: green; text-align: center;">Some Issue has occurred please try after some time</h1>
		<h1 style="color: #FF4500; text-align: center;"><a href="./adminRegister.jsp">|Register Page|</a> </h1>
	</c:when>
</c:choose>
	
<c:choose>
	<c:when test="${deletedBookstatus eq 'success' }">
		<h1 style="color: green; text-align: center;">Book Details Deleted Successful</h1>
	</c:when>
	<c:when test="${deletedBookstatus eq 'failure' }">
		<h1 style="color: green; text-align: center;">Book Details Deletion UnSuccessful</h1>
	</c:when>
	<c:when test="${!empty deletedBookstatus && deletedBookstatus eq null}">
		<h1 style="color: green; text-align: center;">Some Issue has occurred please try after some time</h1>
	</c:when>
</c:choose>
	
	
	
</body>
</html>