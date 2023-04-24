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
</style>
</head>
<body>
<h1>Issue Book</h1>
	<c:choose>
		<c:when test="${bookDetails ne null }">
		<h2>Book Details</h2>
			<table class="bookTable">
				<tr>
					<th>Book Id</th>
					<th>Book Name</th>
					<th>Author</th>
					<th>Status</th>
				</tr>
				<tr>
					<td>${bookDetails.bookId }</td>
					<td>${bookDetails.bookName }</td>
					<td>${bookDetails.authorName }</td>
					<td>${bookDetails.status }</td>
				</tr>
			</table>
		</c:when>
	</c:choose>
	
	<br>
	<c:choose>
		<c:when test="${student ne null }">
		<h2>Student Details</h2>
			<table class="bookTable">
				<tr>
					<th>Student Id</th>
					<th>Student Name</th>
					<th>No. of Books Taken</th>
				</tr>
				<tr>
					<td>${student.sid }</td>
					<td>${student.sname }</td>
					<td>${noOfBooksTaken}</td>
				</tr>
			</table>

			<br>
			<br>
			<c:if test="${noOfBooksTaken ne 3}">
				<div class="div-center">
					<a href="../issueBook/newBookToStudent?&studentId=${student.sid }"><input type="submit" name="" value="issueBook" class="s1"></a>
			    </div>
			</c:if>
				<c:if test="${noOfBooksTaken eq 3}">
					<h1 style="color: red;text-align: center;">Maximum books to be issued is 3 per student</h1>
			</c:if>
		</c:when>
		<c:otherwise>
		<div class="form">
			<form action="../student/issue_searchStudentById" method="post">
				<table>
					<tr>
						<td>Student Id</td>
						<td><input type="text" name="studentId" placeholder="enterStudnetID" class="s2" required="required" /></td>
						<td><input type="hidden" name="bookDetails" value="${bookDetails }"  /></td>
						<td><input type="submit" name="" value="search" class="s1"></td>
					</tr>
				</table>
			</form>
		</div>
			<h1 style="color: red;text-align: center;">Please Enter a valid Student Id Number</h1>
		</c:otherwise>
	</c:choose>


</body>
</html>