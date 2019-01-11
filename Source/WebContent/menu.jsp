<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	final String LOGIN_PAGE = "login.jsp"; 
	String uri = request.getRequestURI();
	uri = uri.substring(uri.lastIndexOf('/') + 1);
	
	if(uri.equals(LOGIN_PAGE)) {
%>    
	<div class="menu fill">
	  <a class="logo" href="index.jsp">CarCheck</a>
	</div>

<%	}
	
	else { %>

	<div class="menu">
	  <a class="logo" href="index.jsp">CarCheck</a>
	  <a class="login" href="login.jsp">Sei un officina? Accedi qui</a>
	</div>
	
<% } %>