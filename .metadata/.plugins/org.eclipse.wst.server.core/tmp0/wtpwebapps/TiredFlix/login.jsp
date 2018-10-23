<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CyberFlix</title>
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param value="Log In" name="pageTitle" />
	</jsp:include>

	<div class="w3-center">
		<h3>
			<b>${message}</b>
		</h3>
	</div>


	<div class="w3-panel w3-margin w3-card-2 w3-white">
		<div class="w3-panel w3-card-2 w3-padding-24 w3-center"
			style="background-color: #F7D4DC; margin-left: 100px; margin-right: 100px;">
			<form class="w3-container forms" action="LoginServlet" method="post">

				<label>Email Address</label> <input
					class="w3-input w3-border w3-round" type="email"
					name="email-address">
				<label>Password</label> <input class="w3-input w3-border w3-round"
					type="password" name="password">
				<br> <input class="w3-btn w3-large w3-round button"
					style="width: 100%;"
					type="submit" value="Submit"> 
				<br> <br> <a href="register.jsp" class="w3-btn w3-large w3-round button"
					style="width: 100%;">
					Register for a New Account</a>

			</form>
		</div>
	</div>
</body>
</html>