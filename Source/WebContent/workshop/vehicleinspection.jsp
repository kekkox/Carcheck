<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<c:forEach items="${totalInspections}" var="item">
			<tr>
                <td class="mainCell">${item.vehicle}</td>
				<td>$item.licenseplate</td>
				<td>$item.licenseplate</td>
				<td>$item.licenseplate</td>
				<td>$item.licenseplate</td>
				<td>$item.licenseplate</td>
				<td><div class='statusCell negative'>Respinta</div></td>
				<td><div class="action">
				<a href="../RequestHandler/inspectionview?inspectionKey=${item.id}"><i class="fas fa-eye" id="eye"></i></a>
				<a href="../RequestHandler/inspectionedit?inspectionKey=${item.id}"><i class="fas fa-pencil-alt" id="pencil"></i></a>
				</div></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    </div>
   
   <a href="../RequestHandler/inspectioninsert"><Button class="button">	
   	
   	<span class="plustxt">+</span>
   	   <span class="buttontext">Aggiungi Revisione</span></Button></a>
</div>

</body>

</html>