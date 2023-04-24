<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style ><%@ include file="sideMenu.css" %></style>
<style ><%@ include file="adminHome.css" %></style>

<title>Admin Home</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
	%>

	<div class="divTop">
		<h1>INEURON COLLEGE LIBRARY ADMIN PORTAL</h1>
		<h1>USER: &nbsp; ${admin.aname }</h1>
		
	</div>
	<div class="nav">
		<nav class="navbar">
			<div class="content">
				<ul class="ulist">
					<li><a href="./admin/adminBody" target="body">HOME</a></li>
					<li><a href="./books.jsp" target="body">Books</a></li>
					<li><a href="./issueBook.jsp" target="body">ISSUE BOOK</a></li>
					<li><a href="./returnBook.jsp" target="body">RETURN BOOK</a></li>
					<li><a href="./renewalBook.jsp" target="body">RENEWAL BOOK</a></li>
					<li><a href="./payFine.jsp" target="body">PAY FINE</a></li>
					<li><a href="./adminDetails.jsp" target="body">ABOUT</a></li>
					<li><a href="./index.jsp">LOGOUT</a></li>
				</ul>
			</div>
		</nav>
	</div>
	<div class="body">
		<br> <br>
		<iframe src="body.jsp" width="950px" height="700px" name="body" frameborder="0"></iframe>
	</div>

</body>
</html>