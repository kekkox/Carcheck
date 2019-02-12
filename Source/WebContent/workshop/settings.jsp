<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Impostazioni</title>
<link rel="stylesheet" href="../css/main.css">
<link rel="stylesheet" href="../css/settings.css">
<link rel="stylesheet" href="../css/menu.css">
<link rel="stylesheet" href="../css/sidebar.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<script src="../js/sidebar.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700"
	rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>



<body>
	<jsp:include page="../WEB-INF/menu.jsp" />
	<div class="container">
		<jsp:include page="../WEB-INF/sidebar.jsp" />
		<div class="content">
			<h1>Impostazioni account</h1>
			<div class="row">
				<div class="settings_container">
					<h1>Cambio Password</h1>
					<div class="inputBox">
						<input type="password" name="password" value="" required>
						<label>Vecchia password</label>
					</div>
					<div class="inputBox">
						<input type="password" name="password" value="" required>
						<label>Nuova password</label>
					</div>
					<div class="inputBox">
						<input type="password" name="password" value="" required>
						<label>Ripeti password</label>
					</div>
					<button type="submit" name="button" value="Login">
					CAMBIA PASSWORD
					</button>
				</div>
			</div>
		</div>


	</div>
	</div>


</body>
</html>