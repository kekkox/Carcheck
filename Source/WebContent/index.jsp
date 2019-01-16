<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
 <html lang="it" dir="ltr">
   <head>
     <meta charset="utf-8">
     <title>Carcheck</title>
     <link rel="stylesheet" href="css/main.css">
     <link rel="stylesheet" href="css/index.css">
     <link rel="stylesheet" href="css/menu.css">
     <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
     <script src="js/index.js"></script>
     <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
   <body>
     <div class="container">

	<jsp:include page="menu.jsp" />

       <div class="overlay"></div>

       <div class="search_bar">
       <div id="error_message">BELLO</div>
         <select name="category">
           <option value="-1" disabled selected>Categoria veicolo</option>
           <option value="1">Autoveicolo</option>
           <option value="2">Motoveicolo</option>
           <option value="3">Ciclomotore</option>
         </select>
         <input type="text" name="license_plate" placeholder="Targa del veicolo">
         <button type="submit" name="search"><i class="fas fa-search"></i></button>
       </div>
     </div>
   </body>
 </html>