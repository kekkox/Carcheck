<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	final String HOMEPAGE_URI = "index.jsp";
	String uri = request.getRequestURI();
	uri = uri.substring(uri.lastIndexOf('/') + 1);
	
	if(uri.equals(HOMEPAGE_URI)) {
%>    
	<div class="menu">
	  <a class="logo" href="index.jsp">CarCheck</a>
	  <a class="login" href="login.jsp">Sei un officina? Accedi qui</a>
	</div>
<%	}
	
	else { %>
		<div class="menu fill">
			<div class = "hamburger">
				<div class="bar"></div>
			</div>
		  	<a class="logo" href="../index.jsp">CarCheck</a>
		</div>
<% } %>