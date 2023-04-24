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
	  <c:when test="${adminDataUpdateStatus eq 'success' }">
	  	<h1 style="color: green; text-align: center;"> Admin Data Updation Successful</h1>
	  </c:when>
  
	  <c:when test="${adminDataUpdateStatus eq 'failure' }">
	 	 <h1 style="color: green; text-align: center;"> Admin Data Updation UnSuccessful</h1>
	  </c:when>
	  <c:when test="${adminDataUpdateStatus eq null && ! empty adminDataUpdateStatus}">
	  	<h1 style="color: green; text-align: center;">Some Issue has occurred please try after some time</h1>
	  </c:when>
  </c:choose>
  
   <c:choose>
	  <c:when test="${adminPasswordUpdateStatus eq 'success' }">
	  	<h1 style="color: green; text-align: center;"> Password Updation Successful</h1>
	  </c:when>
  
	  <c:when test="${adminPasswordUpdateStatus eq 'failure' }">
	 	 <h1 style="color: green; text-align: center;">Password Updation UnSuccessful</h1>
	  </c:when>
	  <c:when test="${adminPasswordUpdateStatus eq null && ! empty adminPasswordUpdateStatus}">
	  	<h1 style="color: green; text-align: center;">Some Issue has occurred please try after some time</h1>
	  </c:when>
  </c:choose>
  
  <c:choose>
	  <c:when test="${studentPasswordUpdateStatus eq 'success' }">
	  	<h1 style="color: green; text-align: center;"> Password Updation Successful</h1>
	  </c:when>
  
	  <c:when test="${studentPasswordUpdateStatus eq 'failure' }">
	 	 <h1 style="color: green; text-align: center;">Password Updation UnSuccessful</h1>
	  </c:when>
	  <c:when test="${studentPasswordUpdateStatus eq null && ! empty studentPasswordUpdateStatus }">
	  	<h1 style="color: green; text-align: center;">Some Issue has occurred please try after some time</h1>
	  </c:when>
  </c:choose>
	
	
	<c:if test="${errorMessage ne null}">
				<h1 style="color: red;text-align: center;">${errorMessage }</h1>
	</c:if>
	
</body>
</html>