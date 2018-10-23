<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CyberFlix</title>
</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param value="Detailed Search" name="pageTitle" />
	</jsp:include>
	<div class="w3-panel w3-margin w3-card-2 w3-white">
		<div class="w3-panel w3-card-2 w3-padding-24 w3-center"
			style="background-color: #F7D4DC; margin-left: 100px; margin-right: 100px;">
			<form class="w3-container forms"
				action="CyberFlixServlet"
				method="get">
				<label>Film Title</label> <input class="w3-input w3-border w3-round"
					type="text" name="film_title">
				<label>Film Description</label> <input
					class="w3-input w3-border w3-round" type="text"
					name="film_description">
				<div class="w3-row-padding w3-margin">
					<div class="w3-half">
						<div class="w3-half w3-right"
							style="width: 100%; max-width: 300px; min-width: 75px">
							<select class="w3-select" name="film_rating">
								<option value="" selected>Film Rating</option>
								<option value="G">G</option>
								<option value="PG">PG</option>
								<option value="PG-13">PG-13</option>
								<option value="R">R</option>
								<option value="NC-17">NC-17</option>
							</select>
						</div>
					</div>
					<div class="w3-half">
						<div class="w3-half w3-left"
							style="width: 100%; max-width: 300px; min-width: 75px">
							<select class="w3-select" name="film_length">
								<option value="0" selected>Film Length</option>
								<option value="60">60 minutes or less</option>
								<option value="90">60-90 minutes</option>
								<option value="120">90-120 minutes</option>
								<option value="150">120-150 minutes</option>
								<option value="151">Longer than 150 minutes</option>
							</select>
						</div>
					</div>
				</div>
				<br> <input class="w3-btn w3-large button w3-round"
					style="width: 100%;"
					type="submit" value="Submit">
			</form>
		</div>
	</div>
</body>
</html>