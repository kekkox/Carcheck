<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <title>NAN70642 - Informazioni</title>
        <link rel="stylesheet" href="css/result.css">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/menu.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans:300,400,700,800|Open+Sans+Condensed:300,700" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    
    	<jsp:include page = "menu.jsp"/>
        
        <div class = "container">
        	<div class = "row">
	        	<div class = "license_palette">
	    			<b>Targa del veicolo:</b> AB123CD
	    		</div>
	        	
	        	<div class = "left">
	        		<div class="vehicle_card">
			            <h1>
			                <div>2015</div>
			                Alfa Romeo Giulietta
			                <small>5 Posti</small>
			            </h1>
			            <img src="https://www.stavauto.it/wp-content/uploads/sites/21/2017/12/PNGPIX-COM-Alfa-Romeo-Giulietta-Car-PNG-image-1024x564.png" alt="Immagine auto">
			            <div class="card_footer">
			                <div class="footer_element">
			                    <span class = "value">6</span>
			                    <span class = "label">EURO</span>
			                </div>
			                <div class="footer_element">
			                    <span class = "value">120</span>
			                    <span class = "label">Cavalli</span>
			                </div>
			                <div class="footer_element">
			                    <span class = "value">88</span>
			                    <span class = "label">kW</span>
			                </div>
			                <div class="footer_element">
			                    <span class = "value">1598</span>
			                    <span class = "label">Cilindrata</span>
			                </div>
		            	</div>
	        		</div>
        		</div>
        		
        		<div class = "right">
	        		<div class = "general_info">
		        		<div class = "info_element">
		        			<i class="fas fa-user-times negative"></i>
		        			Il veicolo non può essere guidato da neopatentati.
		        		</div>
		        		<div class = "info_element">
		        			<i class="fas fa-file positive"></i>
		        			Non sono presenti denunce sul veicolo
		        		</div>
		        		<div class = "info_element">
		        			<i class="fas fa-check-circle positive"></i>
		        			Bollo in regola - <b>Scadenza:</b> 12/12/19
		        		</div>
		        		<div class = "info_element">
		        			<i class="fas fa-check-circle positive"></i>
		        			Revisione in regola - <b>Scadenza:</b> 25/12/19
		        		</div>
		        		<div class = "info_element">
		        			<i class="fas fa-exclamation-circle negative"></i>
		        			Assicurazione scaduta - <b>Scadenza:</b> 01/01/19
		        		</div>
        			</div>
        		</div>
        		
        	</div>
        	
        </div>
    </body>
</html>
