<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="it.carcheck.model.bean.*"%>
<html>
    <head>
        <title>${vehicle.licensePlate} - Informazioni</title>
        <link rel="stylesheet" href="../css/result.css">
        <link rel="stylesheet" href="../css/main.css">
        <link rel="stylesheet" href="../css/menu.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans:300,400,700,800|Open+Sans+Condensed:300,700" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
    
    	<jsp:include page = "menu.jsp"/>
        
        <div class = "container">
        	<div class = "row">
	        	<div class = "license_palette">
	    			<b>Targa del veicolo:</b> ${vehicle.licensePlate}
	    		</div>
	        	
	        	<div class = "left">
	        		<div class="vehicle_card">
			            <h1>
			                <div>${vehicle.yearOfRegistration}</div>
			                ${vehicle.description}
			            </h1>
			            <img src="<c:url value="../img/vehiclephoto/${vehicle.photo}"/>" alt="Immagine auto">
			            <div class="card_footer">
			                <div class="footer_element">
			                    <span class = "value">${vehicle.euroClass}</span>
			                    <span class = "label">EURO</span>
			                </div>
			                <div class="footer_element">
			                    <span class = "value">${vehicle.kw}</span>
			                    <span class = "label">kW</span>
			                </div>
			                <div class="footer_element">
			                    <span class = "value">${vehicle.displacement}</span>
			                    <span class = "label">Cilindrata</span>
			                </div>
		            	</div>
	        		</div>
        		</div>
        		
        		<div class = "right">
	        		<div class = "general_info">
		        		<div class = "info_element">
		        			<c:if test="${vehicle.kw gt 55}">
			        			<i class="fas fa-user-times negative"></i>
			        			Il veicolo non può essere guidato da neopatentati.
		        			</c:if>
		        			<c:if test="${vehicle.kw le 55}">
			        			<i class="fas fa-user positive"></i>
			        			Il veicolo può essere guidato da neopatentati.
		        			</c:if>
		        		</div>
		        		<div class = "info_element">
		        			<i class="fas fa-file positive"></i>
		        			Non sono presenti denunce sul veicolo
		        		</div>
		        		<div class = "info_element">
		        			<c:if test = "${!possessionFeeExpired}">
		        				<i class="fas fa-check-circle positive"></i>
		        				Bollo in regola - <b>Scadenza:</b> ${possessionFeeDate}
	        				</c:if>
	        				<c:if test = "${possessionFeeExpired}">
	        					<i class="fas fa-exclamation-circle negative"></i>
		        				Bollo scaduto - <b>Scaduto il:</b> ${possessionFeeDate}
	        				</c:if>
		        		</div>
		        		<div class = "info_element">
		        			<c:if test = "${!inspectionExpired}">
		        				<i class="fas fa-check-circle positive"></i>
		        				Revisione in regola - <b>Scadenza:</b> ${inspectionDate}
	        				</c:if>
	        				<c:if test = "${inspectionExpired}">
	        					<i class="fas fa-exclamation-circle negative"></i>
		        				Revisione scaduta - <b>Scaduta il:</b> ${inspectionDate}
	        				</c:if>
		        		</div>
		        		<div class = "info_element">
		        		<c:if test = "${!insuranceExpired}">
		        				<i class="fas fa-check-circle positive"></i>
		        				Assicurazione in regola - <b>Scadenza:</b> ${insuranceDate}
	        				</c:if>
	        				<c:if test = "${insuranceExpired}">
	        					<i class="fas fa-exclamation-circle negative"></i>
		        				Assicurazione scaduta - <b>Scaduta il:</b> ${insuranceDate}
	        				</c:if>
		        		</div>
        			</div>
        		</div>
        		
        	</div>
        	
        </div>
    </body>
</html>
