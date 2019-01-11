<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Login officina</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/menu.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body>
  	<jsp:include page = "menu.jsp"/>
    <div class="container">

      <div class="login_container">

        <span>Login officina</span>

        <form action="" method="post">
          <input type="email" name="email" placeholder="Indirizzo email"><br>
          <input type="password" name="password" placeholder="Password"><br>
          <button type="button" name="button">LOGIN</button>
        </form>

        <div class="form_footer">
          <div>Non hai un account? <a href="#">Aderisci a Carcheck</a></div>
        </div>

      </div>
    </div>
  </body>
</html>
    