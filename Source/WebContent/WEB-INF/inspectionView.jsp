<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="it" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title>Aggiungi Revisione</title>
    <link rel="stylesheet" href="../css/menu.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/addinspection.css">  
        <link rel="stylesheet" href="../css/sidebar.css">  
	<!-- Insert javascript code here -->

    <link href="https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Open+Sans+Condensed:300,700" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <script src="../js/inspectionView.js"> </script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
  </head>

  <body>
    	<jsp:include page = "../WEB-INF/menu.jsp"/>
  
   		 <div class="container">
   		   	   <jsp:include page = "../WEB-INF/sidebar.jsp"/>
   		  <div class="content">
   	
      <div class="signup_container"> 
      	    
        <div class="header">

          <span>${title}</span>
          <div>Alcuni campi inseriti sono errati.Riprovare!</div>
        </div>
        <form action="inspectionOperation" method="POST">
     
        
          <div class = "inputBox">
            <input type="text" name="licensePlate" value ="${inspectionsView.vehicle}" onkeyup="this.setAttribute('value', this.value);"  ${property} 'required'>
            <label ${classname} >Targa veicolo</label>
          </div>
    
          <div class = "inputBox">
            <input type="date" name="inspectionDate" value ="${inspectionsView.inspectionDate}" onkeyup="this.setAttribute('value', this.value);" ${property} required >
            <label class="active">Data revisione</label>
          </div>
    
          <div class = "inputBox">
            <input type="number" name="km" value ="${inspectionsView.km}"onkeyup="this.setAttribute('value', this.value);" ${property} required pattern="\d*">
            <label class="active">Chilometri</label>
          </div>
       
     <div class="row check">
     	<label class="containercheck">Approvata
  				<input type="checkbox"  name="state" ${state}>
  				<span class="checkmark"></span>
				</label>
     </div>
    
     
     <c:if test="${upload eq 'isempty'}">
     <label for="file-upload" class="custom-file-upload">
		<i class="fas fa-cloud-upload-alt"></i> Carica immagine
	</label>
	<input id="file-upload" type="file" name="photofile" accept="image/*" required />
	    </c:if>
	    
	    <c:if test="${upload eq 'full'}">
	  <div class="containerImage">
	    <a id="imagepreview" target="_blank" href="${photo}">
  <img id="vheicleimage" class=img src="${photo}" alt="foto">
<input type="hidden" name="inspectionKey" value="${inspectionKey}">
</a>
    	<label for="file-upload" class="custom-file-upload"">
		<i class="fas fa-cloud-upload-alt"></i> Modifica immagine
	</label>
	<input id="file-upload" type="file" accept="image/*" name="photofile" />
</div>
</c:if> 
	    <c:if test="${upload eq 'fullView'}">
	    <a id="imagepreview" target="_blank" href="${photo}">
  <img id="vheicleimage" class=img src="${photo}" alt="foto">
</a>
</c:if> 
	     <c:if test="${submitVisible eq 'focus'}">
	  <button type="submit" name="button" value= "${buttonSubmitValue}">
        ${buttontext} 
          </button>
            </c:if>
                    </form>
     </div>	
     

      </div>
    </div>
    </div>
  </body>
</html>