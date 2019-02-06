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
    <jsp:include page="../menu.jsp" />
     
     <div class="container">

        <jsp:include page="../sidebar.jsp" />
      
        <div class="content">
        
            <div class="row">
                <div class="card">
                    <div class="roundBackground">
                        <i class="fas fa-car"></i>
                    </div>
                    <div class="info">
                        <p>Veicoli revisionati</p>
                        <h2>35</h2>
                    </div>
                </div>
                <div class="card">
                    <div class="roundBackground">
                        <i class="fas fa-wrench"></i>
                    </div>
                    <div class="info">
                        <p>Revisioni</p>
                        <h2>70</h2>
                    </div>
                </div>
            </div>
            
            <h1>Revisioni in scadenza</h1>
            <div class="row">
            		<div class="tableContainer">
						<table class="table">
							<tr>
								<th>Targa</th>
								<th>Descrizione veicolo</th>
								<th>Chilometri</th>
								<th class="lastCell">Data scadenza</th>
							</tr>
							<tr>
								<td>NAN70642</td>
								<td>Fiat Uno</td>
								<td>100000</td>
								<td class="mainCell">10/02/2018</td>
							</tr>
						</table>
					</div> 
            </div>
            
        </div>

     </div>
   </body>
 </html>