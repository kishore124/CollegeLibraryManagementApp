<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Return Book</title>
<style >
<%@ include file="issueBook_GetStudentDetails.css" %>
<%@ include file="issueBook.css" %>
</style>
</head>
<body>
<h1 style="font-size:50px;">Return Book</h1>

	<c:if test="${student.sid ne null}">
		<h2 style="text-align: center; font-size: 30px">Student Details</h2>
			<table class="bookTable">
				<tr>
					<th>Student Id</th>
					<th>Student Name</th>
					<th>Total Fine</th>
				</tr>
				<tr>
					<td>${student.sid }</td>
					<td>${student.sname }</td>
					<td>${student.fine }</td>
				</tr>
			</table>
	</c:if>
	<c:if test="${student.sid eq null}">
		<div class="form">
			<form action="../issueBook/returnGetStudentDetails" method="post">
				<table>
					<tr>
						<td>Student Id</td>
						<td><input type="text" name="studentId" placeholder="enterStudentId" class="s2" required="required"/></td>
						<td><input type="submit" name="" value="search" class="s1"></td>
					</tr>
				</table>
			</form>
		</div>
			<h1 style="color: red;text-align: center;">Please Enter a valid Student Id Number</h1>
</c:if>

<c:if test="${issuedBooksList ne null && !empty issuedBooksList}">
	<h2 style="text-align: center; font-size: 30px">Issued Book Details</h2>
		<table class="bookTable">
				<tr>
					<th>Student Id</th>
					<th>Book Id</th>
					<th>IssueDate</th>
					<th>ReturnDate</th>
					<th>Status</th>
					<th>action</th>
				</tr>
		<c:forEach var="bookdetails" items="${issuedBooksList}">
			      <tr>
					<td>${bookdetails.stdId }</td>
					<td>${bookdetails.bookId }</td>
					<td>${bookdetails.issueDate }</td>
					<td>${bookdetails.returnDate }</td>
					<td>${bookdetails.status }</td>
					<td><a href="./returnBook?bookId=${bookdetails.bookId }&studentId=${bookdetails.stdId }&issueDate=${bookdetails.issueDate }&returnDate=${bookdetails.returnDate }">
							<input type="button" name="" value="return" class="editButton"></a></td>
				</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${empty issuedBooksList && student.sid ne null}">
<h1 style="color: red;text-align: center;">No Books are issued</h1>
</c:if>	

<c:if test="${errorMessage ne null}">
<h1 style="color: red;text-align: center;">${errorMessage }</h1>
</c:if>	

</body>
</html>