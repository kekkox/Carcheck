<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <%
  
  String property=request.getParameter("property");
  
String title=request.getParameter("title");
String classname=request.getParameter("classname");
String uploadIsVisible=request.getParameter("uploadIsVisible");
String buttontext=request.getParameter("buttontext");
  
%>
  <body>
    	<jsp:include page = "../WEB-INF/menu.jsp"/>
  
   		 <div class="container">
   		   	   	<jsp:include page = "../WEB-INF/sidebar.jsp"/>
   		  <div class="content">
      <div class="signup_container"> 
        <div class="header">
          <span><%= title %></span>
          <div>Alcuni campi inseriti sono errati.Riprovare!</div>
        </div>

        <form action="" method="post">
        
          <div class = "inputBox">
            <input type="text" name="licensePlace" value ="" onkeyup="this.setAttribute('value', this.value);" required <%=property %> >
            <label <%= classname %> >Targa veicolo</label>
          </div>
    
          <div class = "inputBox">
            <input type="date" name="inspectionDate" value ="" onkeyup="this.setAttribute('value', this.value);" required <%=property %> >
            <label class="active">Data revisione</label>
          </div>
    
          <div class = "inputBox">
            <input type="number" name="km" value ="" onkeyup="this.setAttribute('value', this.value);" required pattern="\d*" <%=property %>>
            <label class="active">Chilometri</label>
          </div>
       
     <div class="row check">
     	<label class="containercheck">Approvata
  				<input type="checkbox" checked="checked" name="approved">
  				<span class="checkmark"></span>
				</label>
     </div>
     <%
     if(uploadIsVisible=="focus")
     {
     %>
	<label for="file-upload" class="custom-file-upload">
		<i class="fas fa-cloud-upload-alt"></i> Carica immagine
	</label>

	
	<input id="file-upload" type="file" accept="image/*" />
	
	  <button type="submit" name="button" value="Signup">
          	<%= buttontext %>
          </button>
<%
     }
	else {  %>
  <a target="_blank" href="http://www.autousata.us/wp-content/uploads/2015/01/ACQUISTO-AUTO-RITIRO-VALUTAZIONE-INCIDENTATE-PER-ESPORTAZIONE-1080x675.jpg">
  <img src="http://www.autousata.us/wp-content/uploads/2015/01/ACQUISTO-AUTO-RITIRO-VALUTAZIONE-INCIDENTATE-PER-ESPORTAZIONE-1080x675.jpg" alt="foto">
</a>
<%
  }
%>
          
     </div>
   			
		  
      
        </form>


      </div>
    </div>
    </div>
  </body>
</html>