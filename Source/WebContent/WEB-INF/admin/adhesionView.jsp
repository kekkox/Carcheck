<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page  import="it.carcheck.model.bean.enums.RequestStatus"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/menu.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/dashboard.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/signup.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/adhesionView.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/searchbar.css">

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@8"></script>
	<script src="/CarCheck/js/adhesionView.js"></script>

	<title>Carchek</title>
</head>
<body>
	<jsp:include page="../../WEB-INF/menu.jsp" />
		<div class="container">
		<jsp:include page="../../WEB-INF/sidebarAdmin.jsp" />
		<div class="content">
			<div class="signup_container">
		        <div class="header">
		          <span>Richiesta #${showedAdhesion.adhesionRequestId}</span>
		        </div>
		
		        <form>
		        
		          <div class = "inputBox" id = "piva">
		            <input type="text" name="iva" value ="${showedAdhesion.pIva}" readonly>
		            <label>Partita Iva</label>
		          </div>
		    	
		    	  <div class = "inputBox" id = "businessName">
				      <input type="text" name="businessName" value ="${showedAdhesion.businessName}" readonly>
			          	<label>Ragione sociale</label>
		          </div>
		    
		          <div class = "inputBox" id = "owner">
		            <input type="text" name="account_holder" value ="${showedAdhesion.owner}" readonly>
		            <label>Intestatario officina</label>
		          </div>
		    
		    	  <div class = "inputBox" id = "telephone">
		            <input type="tel" name="telephone" value ="${showedAdhesion.telephone}" readonly>
		            <label>Telefono</label>
		          </div>
		    
		          <div class = "inputBox" id = "email">
		            <input type="email" name="email" value ="${showedAdhesion.email}" readonly>
		            <label>Indirizzo Email</label>
		          </div>
		          
		          <div class = "grid">
		          	<div class = "inputBox">
		          		<input value="${region}" readonly>
						<label>Regione</label>
					</div>
					<div class = "inputBox">
						<input value="${province}" readonly>
		            	<label>Provincia</label>
					</div>
					<div class = "inputBox col-large">
						<input value="${city}" readonly>
						<label>Citt&agrave;</label>
		          	</div>
		          </div>
		    
		          <div class = "inputBox" id="address">
		            <input type="text" name="address" value ="${address}" readonly>
		            <label>Indirizzo</label>
		            <ul id="addressResult"></ul>
		          </div>
		            
		          <div class = "inputBox" id="description">
		              <textarea rows="3" name="description" readonly>${showedAdhesion.description}</textarea>
		            <label>Descrizione servizi offerti</label>
		          </div>
		   
		   		  <c:if test="${showedAdhesion.status eq RequestStatus.PROCESSING}">
		   		  	<div class = "grid">
		   		  		<button type="button" onClick = "rejectRequest();" class="secondary" name="reject">
		          			RESPINGI
		          		</button>
						<button type="button" onClick = "setAppointment();" name="appointment">
		          			APPROVA
		          		</button>
					</div>
		   		  </c:if>
		   		  
		   		  <c:if test="${showedAdhesion.status eq RequestStatus.APPOINTMENT}">
		   		  	<div class = "grid">
		   		  		<button type="button" onClick = "rejectRequest();" class="secondary" name="reject">
		          			RESPINGI
		          		</button>
						<button type="button" onClick = "approveRequest();" name="appointment">
		          			CONFERMA ADESIONE
		          		</button>
					</div>
		   		  </c:if>
		   		  
		   		  <c:if test="${showedAdhesion.status eq RequestStatus.REFUSED}">
		   		  	<button type="button" class="secondary" name="reject">
		          			RICHIESTA RESPINTA
	          		</button>
		   		  </c:if>
		   		  
		   		  <c:if test="${showedAdhesion.status eq RequestStatus.APPROVED}">
		   		  	<button type="button" class="secondary" name="reject">
		          			RICHIESTA APPROVATA
	          		</button>
		   		  </c:if>
		   		  
		        </form>
		        <div class="form_footer">
		          <div></div>
		        </div>
	      </div>
      </div>
</div>

</body>

</html>