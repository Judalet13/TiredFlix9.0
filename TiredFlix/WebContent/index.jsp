<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/addToCart.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CyberFlix</title>
</head>
<body>
	<%@ page import="java.util.List"%>

	<jsp:include page="header.jsp">
		<jsp:param value="Welcome!" name="pageTitle" />
	</jsp:include>
		<jsp:include page="Browse.jsp">
		<jsp:param value="browse" name="browse" />
	</jsp:include>
	<c:forEach items="${films}" var="film">
		<div class="w3-panel w3-margin w3-card-4 w3-white">
			<a href="${detailServlet }?film_id=${film.filmID }"> <img
				src="moviePosters/${film.posterImage }"
				alt="${film.title }"
				style="width: 100%; max-width: 200px; float: left; margin-right: 20px; margin-top: 20px; margin-bottom: 20px;">
			</a> <br>
			<h3>
				<b>${film.title }</b>
			</h3>
			<b>Year: </b>${film.releaseYear } <br> <b>Rating: </b>${film.rating }
			<br> <b>Running Time: </b>${film.length } minutes
			<h6>${film.description }</h6><br><br>
			<button id="film_id" value=${film.filmID } onclick="addToCart(this.value)" class="w3-btn w3-left w3-large w3-round button">Add to Cart</button>
		</div>
	</c:forEach>
</body>
</html>