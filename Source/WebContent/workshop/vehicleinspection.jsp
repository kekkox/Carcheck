<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <link rel="stylesheet" type="text/css" href="../css/datatable.css">
      <link rel="stylesheet" href="../css/menu.css">
         <link rel="stylesheet" href="../css/main.css">
          <link rel="stylesheet" href="../css/sidebar.css">
          <link rel="stylesheet" href="../css/searchbar.css">
              <link rel="stylesheet" href="../css/vehicleinspection.css">
              <link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
	<link rel="stylesheet" href="css/searchbar.css">

	
    <title>Carchek</title>
</head>
<body>
<jsp:include page="../WEB-INF/menu.jsp" />
<div class="container">
<jsp:include page="../WEB-INF/sidebar.jsp" />
<div class="content">
    <div class="tableContainer">
     <jsp:include page="../WEB-INF/searchbar.jsp" />
        <table class="table">
            <tr>
                <th>Targa</th>
                <th>Intestatario</th>
				<th>Telefono</th>
				<th>Data Revisione</th>
				<th>Chilometri</th>
				<th>Data Scadenza</th>
				<th>Esito</th>
                <th class="lastCell">Azioni</th>
            </tr>
            <tr>
                <td class="mainCell">TC215MZ</td>
				<td>Braccobaldo D'aragona</td>
				<td>+390815203651</td>
				<td>10/10/2015</td>
				<td>12.530km</td>
				<td>10/10/2019</td>
				<td><div class='statusCell positive'>Approvata</div></td>
				<td><div class="action"><i class="fas fa-eye" id="eye"></i><i class="fas fa-pencil-alt" id="pencil"></i></div></td>
            </tr>
			<tr>
                <td class="mainCell">MO185LT</td>
				<td>Mario Rossi</td>
				<td>+393257841502</td>
				<td>10/10/2015</td>
				<td>124.530km</td>
				<td>10/10/2019</td>
				<td><div class='statusCell negative'>Respinta</div></td>
				<td><div class="action">
				<a href="https:/dsjdis"><i class="fas fa-eye" id="eye"></i></a>
				<a href="https:/dsjdis"><i class="fas fa-pencil-alt" id="pencil"></i></a>
				</div></td>
            </tr>
        </table>
    </div>
    </div>
   
   <a href="addinspection.jsp?property=add"><Button class="button">	
   	
   	<span class="plustxt">+</span>
   	   <span class="buttontext">Aggiungi Revisione</span></Button></a>
</div>

</body>

</html>