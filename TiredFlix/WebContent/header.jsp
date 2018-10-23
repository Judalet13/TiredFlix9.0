<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/stylesheets.html"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Header</title>
</head>
<body>
	<div class="w3-container w3-card-2" style="background-color: #D61a46">
		<div class="w3-row" id="cart-size">

			<div class="w3-col s3 w3-center">
				<a href="index"> <img src="images/logo2.png" alt="CyberFlix"
					style="width: 100%; max-width: 200px; float: left; margin-right: 10px; margin-top: 10px; margin-bottom: 10px;">
				</a> <br>
			</div>
			<div class="w3-col s9 w3-center">
				<br>
				<h1 class="header">
					<b>${param.pageTitle}</b>
				</h1>
				<div style="display: inline-block; float: right;">
					 					<c:choose>
						<c:when test="${!cart.isEmpty() }"> 
					<div class="shopping-item header_button">
						<a href="ShoppingCart.jsp"> <i
							class="glyphicon glyphicon-shopping-cart"
							style="color: #385900; text-shadow: 2px 2px 2px #152f00"></i> <span 
							class="product-count"><b>${cart.size() }</b></span>
						</a>
					</div>
					 						</c:when>
					</c:choose> 
					<a href="search.jsp"
						class="w3-btn w3-right w3-large w3-round button header_button">Search for a Movie</a>
						
					<a href="index.jsp"
						class="w3-btn w3-right w3-large w3-round button header_button">Browse Movies</a>

					<c:choose>
						<c:when test="${userId == 'guest'}">
							<a href="login.jsp"
								class="w3-btn w3-right w3-large w3-round button header_button">Log In</a>
						</c:when>
						<c:otherwise>
							<form action="LogoutServlet" method="post"
								style="display: inline-block">
								<input type="submit" value="Log Out"
									class="w3-btn w3-left w3-large w3-round button header_button" />
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
