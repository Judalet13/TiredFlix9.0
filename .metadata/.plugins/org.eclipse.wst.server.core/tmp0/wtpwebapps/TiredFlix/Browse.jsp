<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Browse by Category</title>
</head>
<body>
	<%-- 	<jsp:include page="header.jsp">
		<jsp:param value="Browse" name="pageTitle" />
	</jsp:include> --%>

	<div class="w3-panel w3-margin w3-card-2 w3-white">
		<div class="w3-panel w3-card-2 w3-padding-24 w3-center"
			style="background-color: #F7D4DC; margin-left: 100px; margin-right: 100px;">

			<div class="w3-row-padding">

				<div class="w3-half w3-margin-bottom">
					<form class="w3-container" action="BrowseByCategoryServlet"
						method="get">
						<ul class="w3-ul w3-center">
							<li><select class="w3-select" name="Category">
									<option value="" selected>By Category</option>
									<option value="Action">ACTION</option>
									<option value="Animation">ANIMATION</option>
									<option value="Children">CHILDREN</option>
									<option value="Classics">CLASSICS</option>
									<option value="Comedy">COMEDY</option>
									<option value="Documentart">DOCUMENTARY</option>
									<option value="Drama">DRAMA</option>
									<option value="Family">FAMILY</option>
									<option value="Foreign">FOREIGN</option>
									<option value="Games">GAMES</option>
									<option value="Horror">HORROR</option>
									<option value="Music">MUSIC</option>
									<option value="New">NEW</option>
									<option value="Sci_fi">SCI_FI</option>
									<option value="Sports">SPORTS</option>
									<option value="Travel">TRAVEL</option>
							</select></li>
							<li class="w3-padding-16"><input
								class="w3-btn w3-large button w3-round" style="width: 100%;"
								type="submit" value="Browse By Category"></li>

						</ul>
					</form>
				</div>

				<div class="w3-half w3-margin-bottom">

					<form class="w3-container" action="BrowseAlphabeticallyServlet"
						method="get">
						<ul class="w3-ul w3-center">
							<li><select class="w3-select" name="firstCharacter">
									<option value="" selected>Alphabetically</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
									<option value="D">D</option>
									<option value="E">E</option>
									<option value="F">F</option>
									<option value="G">G</option>
									<option value="H">H</option>
									<option value="I">I</option>
									<option value="J">J</option>
									<option value="K">K</option>
									<option value="L">L</option>
									<option value="M">M</option>
									<option value="N">N</option>
									<option value="O">O</option>
									<option value="P">P</option>
									<option value="Q">Q</option>
									<option value="R">R</option>
									<option value="S">S</option>
									<option value="T">T</option>
									<option value="U">U</option>
									<option value="V">V</option>
									<option value="W">W</option>
									<option value="Y">Y</option>
									<option value="Z">Z</option>
							</select></li>
							<li class="w3-padding-16"><input
								class="w3-btn w3-large button w3-round" style="width: 100%;"
								type="submit" value="Browse Alphabetically"></li>

						</ul>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>