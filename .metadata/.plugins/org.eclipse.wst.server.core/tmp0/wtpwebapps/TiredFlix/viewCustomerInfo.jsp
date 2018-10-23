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
	<%@ page import="java.util.List"%>
	<%@ page import="java.util.ArrayList"%>
	<%@ page import="java.util.HashMap"%>
	<%@ page import="java.text.SimpleDateFormat" %>
	<jsp:include page="header.jsp">
		<jsp:param value="Rental Records" name="pageTitle" />
	</jsp:include>

	<c:if test="${empty sortedRecords }">
		<div class="w3-panel w3-margin w3-card-4 w3-white"><h3>
			<a href="staffHome.jsp" class="w3-center">
				<b>No Results. Click to Search Again</b>

			</a></h3>
		</div>
	</c:if>	
	<c:forEach items="${sortedRecords.keySet()}" var="customerId">
		<div class="w3-panel w3-margin w3-card-4 w3-white">
		<div class="w3-center">
		<h3 class="shadow"> <b>${sortedRecords.get(customerId).get(0).getCustomer().firstName } ${sortedRecords.get(customerId).get(0).getCustomer().lastName }</b>
		</h3><h4>${sortedRecords.get(customerId).get(0).getCustomer().emailAddress }</h4></div>
	<c:set var="firstOutstanding" value="true"/> 
	<c:set var="firstReturned" value="true"/>
		<div class="w3-panel w3-card-2 w3-padding-24 w3-center"
			style="background-color: #F7D4DC; margin-left: 200px; margin-right: 200px; padding-left: 100px;">	
			
			<div class="w3-left-align">
			<div class="w3-row">
			<div class="w3-third" style="font-size: 20px; padding-left: 40px;"><b>
				Film</b></div>
				<div class="w3-third" style="padding-left: 60px; font-size: 20px"><b>Rental Date</b></div>
				<c:if test="${byCustomer }">
			<div class="w3-third" style="font-size: 20px"><b>Return Date</b></div>
			</c:if>
</div>
	<c:forEach items="${sortedRecords.get(customerId) }" var="record">
	<c:if test="${byCustomer && firstOutstanding && record.getReturnDate() == null }">
	<c:set var="firstOutstanding" value="false"/> 
	<h3 class="shadow"><b>Outstanding</b></h3>
	</c:if>
	<c:if test="${byCustomer && firstReturned && record.getReturnDate() != null }"><br>
	<c:set var="firstReturned" value="false"/>
	<h3 class="shadow"><b>Returned</b></h3>
	</c:if>
		
	<div class="w3-row">
			<div class="film-results w3-third">
				${record.film.title }</div>
				<div class="w3-third" style="padding-left: 60px">${SimpleDateFormat('MMMM d, yyyy').format(record.getRentalDate()) }</div>
			<c:if test="${record.getReturnDate() != null }">
				<div class="w3-third">${SimpleDateFormat('MMMM d, yyyy').format(record.getReturnDate()) }</div>
			</c:if>
</div>
	</c:forEach>	<br>
	</div></div></div>
	</c:forEach>	
	
	
</body>
</html>