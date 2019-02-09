<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Login officina</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="./js/login.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body>
  	<jsp:include page = "menu.jsp"/>
    <div class="container">

      <div class="login_container">
        <div class="header">
          <span>Login officina</span>
          <div></div>
        </div>

        <form action="" method="post">
          <div class = "inputBox">
            <input type="email" name="email" value ="" onkeyup="this.setAttribute('value', this.value);" required>
            <label>Indirizzo Email</label>
          </div>
          <div class = "inputBox">
            <input type="password" name="password" value ="" required>
            <label>Password</label>
          </div>
          <button type="submit" name="button" value="Login">
          	LOGIN
          </button>
        </form>

        <div class="form_footer">
          <div>Non hai un account? <a href="./signup.jsp">Aderisci a Carcheck</a></div>
        </div>

      </div>
    </div>
  </body>
</html>
    