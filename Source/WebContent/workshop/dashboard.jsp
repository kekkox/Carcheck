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
     <link rel="stylesheet" href="../css/main.css">
     <link rel="stylesheet" href="../css/menu.css">
     <link rel="stylesheet" href="../css/dashboard.css">
     <link rel="stylesheet" href="../css/sidebar.css">
     <link rel="stylesheet" href="../css/datatable.css">
     <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
     <!-- Script -->
     <script src="../js/sidebar.js"></script>
     <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
   <body>
    <jsp:include page="../WEB-INF/menu.jsp" />
     
     <div class="container">

        <jsp:include page="../WEB-INF/sidebar.jsp" />
      
        <div class="content">
        
            <div class="row">
                <div class="card">
                    <div class="roundBackground">
                        <i class="fas fa-car"></i>
                    </div>
                    <div class="info">
                        <p>Veicoli revisionati</p>
                        <h2>${ fn:length(totalInspectionsVehicle)}</h2>
                    </div>
                </div>
                <div class="card">
                    <div class="roundBackground">
                        <i class="fas fa-wrench"></i>
                    </div>
                    <div class="info">
                        <p>Revisioni</p>
                        <h2>${ fn:length(totalInspections)}</h2>
                    </div>
                </div>
            </div>
            
            <h1>Revisioni in scadenza</h1>
            <c:if test="${fn:length(expiringInspections) le 0}">
            	<div class="row">
   					<h4>Nessuna revisione in scadenza</h4>
   				</div>
			</c:if>
			<c:if test="${fn:length(expiringInspections) gt 0}">
   				<div class="row">
            		<div class="tableContainer">
						<table class="table">
							<tr>
								<th>Targa</th>
								<th>Chilometri</th>
								<th class="lastCell">Data scadenza</th>
							</tr>
							<c:forEach items="${expiringInspections}" var="item">
								<tr>
									<td class="mainCell">${item.vehicle}</td>
									<td>${item.km}</td>
											<td>${item.expirationDate}</td>
								</tr>
							</c:forEach>
						</table>
					</div> 
            	</div>
			</c:if>
            
        </div>

     </div>
   </body>
 </html>