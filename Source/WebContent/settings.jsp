<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Impostazioni</title>
<link rel="stylesheet" href="css/main.css">

<!--       <link rel="stylesheet" href="css/index.css"> -->
<link rel="stylesheet" href="css/settings.css">
<link rel="stylesheet" href="css/menu.css">
<link rel="stylesheet" href="css/sidebar.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
<script src="js/index.js"></script>
<link
	href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700"
	rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>



<body>
	<jsp:include page="menu.jsp" />
	<div class="container">
		<jsp:include page="sidebar.jsp" />
		<div class="content">
			<h1>Impostazioni generali</h1>
		
			<div class="row">
				<div class="settings_container">
					<div class="row">
						<div>Cambia email</div>
						<div><input type="text" value="carcheck@gmail.com" class="field_element" readonly></div>
					 	<div><button type="button" class="setting_button">Nuova email</button></div>
					</div>
					<div class="row">
						<div>Cambia password</div>
						<div><input type="password" value="password" class="field_element" readonly></div>
				 		<div><button type="button" class="setting_button">Nuova password</button></div>
					</div>
				</div>
			</div>

			
		</div>
	</div>
	

</body>
</html>