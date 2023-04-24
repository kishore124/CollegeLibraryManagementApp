<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style >
<%@ include file="issueBook.css" %>
</style>
<title>IssueBook</title>
</head>
<body>
	<h1>Search Book By Author Name</h1>
	<div class="form">
		<form action="./book/searchBookByAuthorName" method="post">
			<table>
				<tr>
					<td>Book Author</td>
					<td><input type="text" name="authorName" placeholder="enterAuthorName" class="s2" required="required"/></td>
					<td><input type="submit" name="" value="search" class="s1"></td>
				</tr>

			</table>
		</form>
	</div>

</body>
</html>