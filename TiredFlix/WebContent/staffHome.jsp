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
		<jsp:param value="Search Rental Records" name="pageTitle" />
	</jsp:include>
	
	<div class="w3-panel w3-margin w3-card-4 w3-white">	
		<div class="w3-panel w3-card-2 w3-padding-24 w3-center"
			style="background-color: #F7D4DC; margin-left: 100px; margin-right: 100px;"><br>
			<form class="w3-container forms"
				action="CustomerRentalsServlet" method="get">
				<label>Search By Customer Email Address</label><input
					class="w3-input w3-border w3-round" type="text"
					name="email-address">
				<br> <input class="w3-btn w3-large button w3-round"
					style="width: 100%;"
					type="submit" value="View Customer Record">				
	</form><br></div>
		<div class="w3-panel w3-card-2 w3-padding-24 w3-center"
			style="background-color: #F7D4DC; margin-left: 100px; margin-right: 100px;">
			<br>
			<form class="w3-container forms"
				action="OutstandingRentalsServlet" method="get"><input class="w3-btn w3-large button w3-round"
					style="width: 100%;"
					type="submit" value="View All Outstanding Rentals">				
	</form><br>
	</div>
	</div>
</body>
</html>