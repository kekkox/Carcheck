<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
 <html lang="it" dir="ltr">
   <head>
     <meta charset="utf-8">
     <title>Carcheck</title>
     <!-- Style -->
     <link rel="stylesheet" href="../../css/main.css">
     <link rel="stylesheet" href="../../css/menu.css">
     <link rel="stylesheet" href="../../css/dashboard.css">
     <link rel="stylesheet" href="../../css/sidebar.css">
     <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
     <!-- Script -->
     <script src="../../js/sidebar.js"></script>
     <script src="../../js/dashboardAdmin.js"></script>
     <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
   <body>
    <jsp:include page="../../WEB-INF/menu.jsp" />
     
     <div class="container">

        <jsp:include page="../../WEB-INF/sidebarAdmin.jsp" />
      
        <div class="content">
        
            <div class="row">
                <div class="card">
                    <div class="roundBackground">
                        <i class="fas fa-car"></i>
                    </div>
                    <div class="info">
                        <p>Veicoli registrati</p>
                        <h2><i class="fa fa-spinner fa-spin"></i></h2>
                    </div>
                </div>
                <div class="card">
                    <div class="roundBackground">
                        <i class="fas fa-clipboard-list"></i>
                    </div>
                    <div class="info">
                        <p>Richieste in sospeso</p>
                        <h2><i class="fa fa-spinner fa-spin"></i></h2>
                    </div>
                </div>
                <div class="card">
                    <div class="roundBackground">
                        <i class="fas fa-wrench"></i>
                    </div>
                    <div class="info">
                        <p>Officine registrate</p>
                        <h2><i class="fa fa-spinner fa-spin"></i></h2>
                    </div>
                </div>
            </div>
           
        </div>

     </div>
   </body>
 </html>