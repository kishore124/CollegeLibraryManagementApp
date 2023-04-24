<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
<%@ include file="BookDataAddForm.css" %>
</style>
</head>
<body>
	<h1>Add Book Details</h1>
	<form action="./book/addBookDetails" method="post">
		<table class="tab">
			<tr>
				<th>Book Name</th>
				<td><input type="text" name="Book_name" id="" required  class="s1"/></td>
				<td><font color='red'> <c:if
							test="${bookName_error_msg ne null }">${bookName_error_msg}</c:if></font></td>
			</tr>
			<tr>
				<th>Author Name</th>
				<td><input type="text" name="author_name" id="" required class="s1"/></td>
				<td><font color='red'> <c:if
							test="${author_error_msg ne null }">${author_error_msg}</c:if></font></td>
			</tr>
			<tr>
				<th>Publisher</th>
				<td><input type="text" name="pulisher" id="pass" required class="s1"/></td>
				<td><font color='red'> <c:if
							test="${publisher_error_msg ne null }">${publisher_error_msg}</c:if></font></td>
			</tr>
			<tr>
				<th>ISB Number</th>
				<td><input type="number" name="book_ISBN" id="" required class="s1"/></td>
				<td><font color='red'> <c:if
							test="${isbNumber_error_msg ne null }">${isbNumber_error_msg}</c:if></font></td>
			</tr>

			<tr>
				<th>Price</th>
				<td><input type="number" name="price" id="" required min="1.00" max="100000.90" step="any" class="s1"/></td>
				<td><font color='red'> <c:if
							test="${price_error_msg ne null }">${price_error_msg}</c:if></font></td>
			</tr>
			<tr>
				<th>Category</th>
				<td><select name="category" required>
						<option value="History" > History</option>
						<option value="Comedy" >Comedy</option>
						<option value="Friction" >Friction Stories</option>
						<option value="Technology" >Technology</option>
						<option value="Maths">Maths</option>
						<option value="Science">Science</option>
						<option value="Management">Management</option>
				</select></td>

			</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="add" class="submitButton"></td>
			</tr>
		</table>
	</form>
	<c:choose>
		<c:when test="${bookStatus ne null}" >
	 		<h1 style="color: red,aqua;text-align: center;">${bookStatus }</h1>
		</c:when>
	</c:choose>
</body>
</html>