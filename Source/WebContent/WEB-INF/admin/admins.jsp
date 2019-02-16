<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page  import="it.carcheck.model.bean.enums.Grade"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/css/datatable.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/menu.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/searchbar.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/signup.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/vehicleinspection.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/"crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/searchbar.css">

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
	<script src="${pageContext.servletContext.contextPath}/js/sidebar.js"></script>
	<script src="${pageContext.servletContext.contextPath}/js/admins.js"></script>
	
    <title>Carchek</title>
</head>
<body>
	<jsp:include page="../../WEB-INF/menu.jsp" />
	<div class="container">
		<jsp:include page="../../WEB-INF/sidebarAdmin.jsp" />
		<div class="content">
			<c:if test="${fn:length(admins) - 1 gt 0}">
			    <div class="tableContainer">
			        <table class="table">
			            <tr>
			                <th>Nome</th>
			                <th>Cognome</th>
							<th>Email</th>
							<th>Permessi</th>
			                <th class="lastCell">Azioni</th>
			            </tr>
						<c:forEach items="${admins}" var="item">
							<c:if test="${item.email != sessionScope.user.email}">
								<tr>
					                <td class="mainCell">${item.name}</td>
									<td>${item.surname}</td>
									<td>${item.email}</td>
									<td>
										<c:if test="${item.grade eq Grade.SUPER_ADMIN}">
											<div class='statusCell positive'>Super admin</div>
										</c:if>
										<c:if test="${item.grade eq Grade.DEFAULT_ADMIN}">
											<div class='statusCell neutral'>Admin</div>
										</c:if>
									</td>
									<td>
										<div class="action">
											<a href="${pageContext.servletContext.contextPath}/RequestHandler/adminForm?action=2&email=${item.email}"><i class="fas fa-pencil-alt" id="eye"></i></a>
											<a href="#"><i class="fas fa-times" id="${item.email}" onClick="removeAdmin()"></i></a>
										</div>
									</td>
					            </tr>
				            </c:if>
			            </c:forEach>
			        </table>
			    </div>
		    </c:if>
		    <c:if test="${fn:length(admins) - 1 le 0}">
		    	<h1>Nessun admin da visualizzare</h1>
		    </c:if>
		    <a href="${pageContext.servletContext.contextPath}/RequestHandler/adminForm?action=1">
			   	<Button class="button">	
					<span class="plustxt">+</span><span class="buttontext">Aggiungi Admin</span>
				</Button>
		    </a>
	    </div>
	</div>

</body>

</html>