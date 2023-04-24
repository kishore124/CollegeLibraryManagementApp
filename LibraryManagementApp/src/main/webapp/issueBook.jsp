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
	<h1>Issue Book</h1>
	<div class="form">
		<form action="./book/issue_searchBookById" method="post">
			<table>
				<tr>
					<td>Book Id</td>
					<td><input type="text" name="bookId" placeholder="enterBookId" class="s2" required="required"/></td>
					<td><input type="submit" name="" value="search" class="s1"></td>
				</tr>

			</table>
		</form>
	</div>

</body>
</html>