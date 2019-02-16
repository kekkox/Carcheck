<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page  import="it.carcheck.model.bean.enums.RequestStatus"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <link rel="stylesheet" type="text/css" href="../../css/datatable.css">
      <link rel="stylesheet" href="../../css/menu.css">
         <link rel="stylesheet" href="../../css/main.css">
          <link rel="stylesheet" href="../../css/sidebar.css">
          <link rel="stylesheet" href="../../css/searchbar.css">
              <link rel="stylesheet" href="../../css/vehicleinspection.css">
              <link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css"
	integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"
	crossorigin="anonymous">
	<link rel="stylesheet" href="css/searchbar.css">

	
    <title>Carchek</title>
</head>
<body>
<jsp:include page="../../WEB-INF/menu.jsp" />
<div class="container">
<jsp:include page="../../WEB-INF/sidebarAdmin.jsp" />
<div class="content">
    <div class="tableContainer">
        <table class="table">
            <tr>
                <th>Officina</th>
                <th>Intestatario</th>
				<th>Email</th>
				<th>Partita IVA</th>
				<th>Stato</th>
                <th class="lastCell">Azioni</th>
            </tr>
		<c:forEach items="${adhesions}" var="item">
			<tr>
                <td class="mainCell">${item.businessName}</td>
				<td>${item.owner}</td>
				<td>${item.email}</td>
				<td>${item.pIva}</td>
				<td>
					<c:if test="${item.status eq RequestStatus.PROCESSING}">
						<div class='statusCell neutral'>In attesa</div>
					</c:if>
					<c:if test="${item.status eq RequestStatus.APPOINTMENT}">
						<div class='statusCell neutralHigh'>Appuntamento</div>
					</c:if>
					<c:if test="${item.status eq RequestStatus.APPROVED}">
						<div class='statusCell positive'>Approvato</div>
					</c:if>
					<c:if test="${item.status eq RequestStatus.REFUSED}">
						<div class='statusCell negative'>Respinta</div>
					</c:if>
				</td>
				<td><div class="action">
				<a href="${pageContext.servletContext.contextPath}/RequestHandler/adhesionView?adhesion=${item.adhesionRequestId}"><i class="fas fa-eye" id="eye"></i></a>
				</div></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    </div>
</div>

</body>

</html>