<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page import="in.ineuron.dto.Book"  %>  
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
<h1>Edit the Book Details</h1>
<c:if test="${editBook ne null && !empty editBook }">
<form action="../../book/updateBookDetails" method="post">
		<table class="tab">
			<tr>
				<th>Book ID</th>
				<td><input type="text" name="bookId" id="" value="${editBook.bookId }" readonly="readonly" class="s1"/></td>
			</tr>
			<tr>
				<th>Book Name</th>
				<td><input type="text" name="Book_name" id="" value="${editBook.bookName }" placeholder="${editBook.bookName } "class="s1"/></td>
			</tr>
			<tr>
				<th>Author Name</th>
				<td><input type="text" name="author_name" id="" value="${editBook.authorName }" placeholder="${editBook.bookName }" class="s1" required/></td>
			</tr>
			<tr>
				<th>Publisher</th>
				<td><input type="text" name="pulisher" id="pass"  value="${editBook.publisherName }" placeholder="${editBook.bookName }" required class="s1"/></td>
			</tr>
			<tr>
				<th>ISB Number</th>
				<td><input type="number" name="book_ISBN" id="" value="${editBook.isbNumber }" placeholder="${editBook.bookName }" class="s1" required/></td>
			</tr>

			<tr>
				<th>Price</th>
				<td><input type="number" name="price" id="" required min="1.00" max="100000.90" step="any" value="${editBook.price }" placeholder="${editBook.price }" class="s1" /></td>
			</tr>
			<tr>
				<th>Category</th>
				<td><select name="category" required>
				
					  <option value="History" <% Book bookDetails=(Book)request.getAttribute("editBook"); if(bookDetails.getCategoryName().equals("History")) { %>selected<% } %> >History</option>
					  <option value="Comedy" <% if(bookDetails.getCategoryName().equals("Comedy")) { %>selected<% } %> >Comedy</option>
					  <option value="Friction" <% if(bookDetails.getCategoryName().equals("Friction")) { %>selected<% } %> >Friction</option>
					  <option value="Technology" <% if(bookDetails.getCategoryName().equals("Technology")) { %>selected<% } %> >Technology</option>
					  <option value="Maths" <% if(bookDetails.getCategoryName().equals("Maths")) { %>selected<% } %> >Maths</option>
					  <option value="Science" <% if(bookDetails.getCategoryName().equals("Science")) { %>selected<% } %> >Science</option>
					  <option value="Management" <% if(bookDetails.getCategoryName().equals("Management")) { %>selected<% } %> >Management</option>
				</select></td>

			</tr>
			<tr>
				<th></th>
				<td><input type="submit" value="edit" class="submitButton"></td>
			</tr>
		</table>
	</form>
</c:if>
<c:if test="${editBook eq null }">
	<h1 style="color: red;text-align: center;">Some Issue Has Occured Please try after some time</h1>
</c:if>
</body>
</html>