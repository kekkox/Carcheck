<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page  import="it.carcheck.model.bean.enums.Grade"%>
<!DOCTYPE html>
<html lang="it" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Registrazione officina</title>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/menu.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/dashboard.css">  
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/addinspection.css">  
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/sidebar.css">  
	<!-- Javascript -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="${pageContext.servletContext.contextPath}/js/adminForm.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body>
    	<jsp:include page = "../../WEB-INF/menu.jsp"/>
    	<div class="container">
			<jsp:include page="../../WEB-INF/sidebarAdmin.jsp" />
			<div class = "content">
	      		<div class="signup_container">
	      			
	      			<c:if test="${actionCode eq 1}">
				        <div class="header">
				          <span>Aggiungi admin</span>
				          <div></div>
				        </div>
			
				        <form action="" method="post">
				    	
				    	  <div class = "inputBox" id = "businessName">
						      <input type="text" name="name" value ="" onkeyup="this.setAttribute('value', this.value);" pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" required>
					          <label>Nome admin</label>
				          </div>
				    
				          <div class = "inputBox" id = "owner">
				            <input type="text" name="surname" value ="" onkeyup="this.setAttribute('value', this.value);" pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" required>
				            <label>Cognome admin</label>
				          </div>
				    
				          <div class = "inputBox" id = "email">
				            <input type="email" name="email" value ="" onkeyup="this.setAttribute('value', this.value);" required>
				            <label>Indirizzo Email</label>
				          </div>
				   
				   		  <div class="row check">
     						<label class="containercheck">Super admin
  								<input type="checkbox" name="state"></input>
  								<span class="checkmark"></span>
							</label>
   						  </div>
		   					
				          <button type="submit" name="button" value="AGGIUNGI ADMIN">
				          	AGGIUNGI ADMIN
				          </button>
				        </form>
			        </c:if>
			        
			        <c:if test="${actionCode eq 2}">
				        <div class="header">
				          <span>Modifica admin</span>
				          <div></div>
				        </div>
			
				        <form action="" method="post">
				    	
				    	  <div class = "inputBox" id = "businessName">
						      <input type="text" name="name" value ="${name}" onkeyup="this.setAttribute('value', this.value);" pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" required>
					          <label>Nome admin</label>
				          </div>
				    
				          <div class = "inputBox" id = "owner">
				            <input type="text" name="surname" value ="${surname}" onkeyup="this.setAttribute('value', this.value);" pattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$" required>
				            <label>Cognome admin</label>
				          </div>
				    
				          <div class = "inputBox" id = "email">
				            <input type="email" name="email" value ="${email}" onkeyup="this.setAttribute('value', this.value);" readonly required>
				            <label>Indirizzo Email</label>
				          </div>
				   
				   		  <div class="row check">
     						<label class="containercheck">Super admin
     							<c:if test="${grade eq Grade.DEFAULT_ADMIN}">
  									<input type="checkbox" name="state"></input>
								</c:if>
								<c:if test="${grade eq Grade.SUPER_ADMIN}">
  									<input type="checkbox" name="state" checked></input>
								</c:if>
  								<span class="checkmark"></span>
							</label>
   						  </div>
				   
				          <button type="submit" name="button" value="MODIFICA ADMIN">
				          	MODIFICA ADMIN
				          </button>
				        </form>
			        </c:if>
			        
			        <div class="form_footer">
				        </div>
			        
      			</div>
      	</div>
    </div>
  </body>
</html>