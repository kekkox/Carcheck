<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Registrazione officina</title>
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/signup.css">  
	<!-- Javascript -->
	<script src="js/signup.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body>
    	<jsp:include page = "/WEB-INF/menu.jsp"/>
  	
    <div class="container">

      <div class="signup_container">
        <div class="header">
          <span>Registrazione officina</span>
          <div>Alcuni campi inseriti sono errati.Riprovare!</div>
        </div>

        <form action="" method="post">
        
          <div class = "inputBox" id = "piva">
            <input type="text" name="p_iva" value ="" onkeyup="this.setAttribute('value', this.value);" pattern ="^[0-9]{11}$" required>
            <label>Partita Iva</label>
          </div>
    
          <div class = "inputBox" id = "owner">
            <input type="text" name="account_holder" value ="" onkeyup="this.setAttribute('value', this.value);" pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" required>
            <label>Intestatario officina</label>
          </div>
    
          <div class = "inputBox" id = "email">
            <input type="email" name="email" value ="" onkeyup="this.setAttribute('value', this.value);" required>
            <label>Indirizzo Email</label>
          </div>
          
          <div class = "grid">
          	<div class = "inputBox">
          		<select id = "region" data-source="RequestHandler/region_service" required disabled>
          			<option disabled selected></option>
				</select>
				<label>Regione</label>
			</div>
			<div class = "inputBox">
				<select id = "province" data-source="RequestHandler/province_service" required disabled>
          			<option disabled selected></option>
				</select>
				<label>Provincia</label>
			</div>
			<div class = "inputBox col-large">
				<select id = "city" data-source="RequestHandler/city_service" required disabled>
          			<option disabled selected></option>
				</select>
				<label>Citt&agrave;</label>
          	</div>
          </div>
    
          <div class = "inputBox" id="address">
            <input type="text" name="address" value ="" onkeyup="this.setAttribute('value', this.value);" required disabled>
            <label>Indirizzo</label>
            <ul id="addressResult"></ul>
          </div>
            
          <div class = "inputBox" id="description">
              <textarea rows="3" name="description" value ="" onkeyup="this.setAttribute('value', this.value);" required></textarea>
            <label>Descrizione servizi offerti</label>
          </div>
   
          <button type="submit" name="button" value="Signup">
          	REGISTRATI
          </button>
        </form>

        <div class="form_footer">
          <div>Hai già un account? <a href="login.jsp">Effettua il login</a></div>
        </div>

      </div>
    </div>
  </body>
</html>