<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Renewal Book Status</title>
</head>
<body bgcolor=" #b3ffe6">
	<br>
	<br>
	<br>
<h1 style="text-align: center;">STATUS</h1>
	<c:if test="${bookstatus eq 'success' && stdStatus eq 'success'}">
		<h1 style="color: green; text-align: center;">Book Renewal Successful</h1>
	</c:if>
	
	<c:if test="${bookstatus eq 'failure' && stdStatus eq 'failure'}">
		<h1 style="color: green; text-align: center;">Book Renewal Successful Please Try Again</h1>
	</c:if>
	
	<c:if test="${fineAmountStatus eq 'success' }">
		<h1 style="color: green; text-align: center;">Fine Paid Successful</h1>
	</c:if>
	
	<c:if test="${fineAmountStatus eq 'failure' }">
		<h1 style="color: green; text-align: center;"> UnSuccessful Please try after some time</h1>
	</c:if>
	
	
	<c:if test="${message ne null}">
		<h1 style="color: red; text-align: center;">${message }</h1>
	</c:if>
	
	<c:if test="${errorMessage ne null}">
		<h1 style="color: red; text-align: center;">${errorMessage }</h1>
	</c:if>
	
	
	
</body>
</html>