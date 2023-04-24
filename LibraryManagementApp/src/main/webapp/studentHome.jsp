<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style ><%@ include file="sideMenu.css" %></style>
<style ><%@ include file="adminHome.css" %></style>

<title>Student Home</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	%>

	<div class="divTop">
		<h1>INEURON COLLEGE LIBRARY STUDENT PORTAL</h1>
		<h1>USER: &nbsp; ${studentData.sname }</h1>
	</div>
	<div class="nav">
		<nav class="navbar">
			<div class="content">
				<ul class="ulist">
					<li><a href="./displayStudentDetails.jsp" target="body">HOME</a></li>
					<li><a href="./searchBooks.jsp" target="body">SEARCH BOOKS</a></li>
					<li><a href="./student/stdPortal_getstudentDetials?studentId=${studentData.sid }" target="body">ISSUED BOOK</a></li>
					<li><a href="./editStudentDetails.jsp" target="body">UPDATE DETAILS</a></li>
					<li><a href="./changeStudentPassword.jsp" target="body">CHANGE PASSWORD</a></li>
					<li><a href="./index.jsp">LOGOUT</a></li>
				</ul>
			</div>
		</nav>
	</div>
	<div class="body">
		<br> <br>
		<iframe src="studentBody.jsp" width="950px" height="600px" name="body" frameborder="0"></iframe>
	</div>

</body>
</html>