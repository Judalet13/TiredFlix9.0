<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	int total = 0;
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart Page</title>
<style>
body {
	background-color: #C8e887;
}

h1 {
	color: white;
	text-align: center;
}

h3 {
	text-align: center;
}
</style>
</head>
<body>
	<%@ page import="java.util.List"%>
	<%
		total = total + 1;
	%>
	<jsp:include page="header.jsp">
		<jsp:param value="Shopping Cart" name="pageTitle" />
	</jsp:include>

	<h1>Your Current Order</h1>

	<div class="w3-panel w3-margin w3-card-4 w3-orange w3-round">
		<c:forEach items="${cart}" var="film">
			<br>
			<h3>
				<b>${film.title } </b> $1
			</h3>
		</c:forEach>
		<h3>
			Your Total Is: <b>$ ${cart.size() } </b>
		</h3>
	</div>
	<c:choose>
		<c:when test="${userId == 'guest'}">
			<a href="login.jsp"
				class="w3-btn w3-large w3-margin button w3-round"
				style="width: 100%;">Log In to Checkout</a>
		</c:when>
		<c:otherwise>
			<form action="CheckoutServlet" method="post">
				<input type="submit" value="Checkout"
				class="w3-btn w3-large w3-margin button w3-round"
				style="width: 100%;"/>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>
