<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Return Book</title>
<style >
<%@ include file="issueBook.css" %>
</style>
</head>
<body>
<h1>Return Book</h1>
	<div class="form">
		<form action="./issueBook/returnGetStudentDetails" method="post">
			<table>
				<tr>
					<td>Student Id</td>
					<td><input type="text" name="studentId" placeholder="enterStudentId" class="s2" required="required"/></td>
					<td><a href="./issueBook/getStudentDetails"><input type="submit" name="" value="search" class="s1"></a></td>
				</tr>

			</table>
		</form>
	</div>
</body>
</html>