<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style >
<%@ include file="issueBook.css" %>
<%@ include file="BookDataAddForm.css" %>
</style>
<title>IssueBook</title>
</head>
<body>
	<h1>Search Book By Category</h1>
	<div class="form">
		<form action="./book/searchBookByCategory" method="post">
			<table>
				<tr>
					<td><th>Category</th>
				 		<td><select name="category" required>
						<option value="History" > History</option>
						<option value="Comedy" >Comedy</option>
						<option value="Friction" >Friction Stories</option>
						<option value="Technology" >Technology</option>
						<option value="Maths">Maths</option>
						<option value="Science">Science</option>
						<option value="Management">Management</option>
				</select>
				</td>
					<td><input type="submit" name="" value="search" class="s1"></td>
				</tr>

			</table>
		</form>
	</div>

</body>
</html>