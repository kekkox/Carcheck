<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="it" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Aggiungi Revisione</title>
    <link rel="stylesheet" href="../css/menu.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/addinspection.css">  
        <link rel="stylesheet" href="../css/sidebar.css">  
	<!-- Insert javascript code here -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body>
    	<jsp:include page = "../WEB-INF/menu.jsp"/>
  
   		 <div class="container">
   		   	   	<jsp:include page = "../WEB-INF/sidebar.jsp"/>
   		  <div class="content">
      <div class="signup_container"> 
        <div class="header">
          <span>Aggiungi Revisione</span>
          <div>Alcuni campi inseriti sono errati.Riprovare!</div>
        </div>

        <form action="" method="post">
        
          <div class = "inputBox">
            <input type="text" name="p_iva" value ="" onkeyup="this.setAttribute('value', this.value);" required>
            <label>Targa veicolo</label>
          </div>
    
          <div class = "inputBox">
            <input type="text" name="account_holder" value ="" onkeyup="this.setAttribute('value', this.value);" required>
            <label>Data revisione</label>
          </div>
    
          <div class = "inputBox">
            <input type="text" name="email" value ="" onkeyup="this.setAttribute('value', this.value);" required>
            <label>Chilometri</label>
          </div>
       
     <div class="row check">
     	<label class="containercheck">Approvata
  				<input type="checkbox" checked="checked">
  				<span class="checkmark"></span>
				</label>
     </div>
     
	<label for="file-upload" class="custom-file-upload">
		<i class="fas fa-cloud-upload-alt"></i> Carica immagine
	</label>
	
	<input id="file-upload" type="file"/>
     
    <button type="submit" name="button" value="Signup">
          	REGISTRA REVISIONE
          </button>
      
     </div>
   			
		  
     
      
        </form>


      </div>
    </div>
    </div>
  </body>
</html>