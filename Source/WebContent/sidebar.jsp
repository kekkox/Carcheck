<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

   <%
   	final String DASHBOARD_URI = "dashboard.jsp";
   	final String VEHICLE_INSPECTION_URI = "vehicleinspection.jsp";
   	final String SETTINGS_URI = "settings.jsp";
   	
   	String uri = request.getRequestURI();
	uri = uri.substring(uri.lastIndexOf('/') + 1);
   %>
   
   <ul class = "sidebar">
   <%
   	if(uri.equals(DASHBOARD_URI)) {
   %>
     <li class="selected">
   <%
   	} else {
   %>
   <li>
   <% }%>
     	<a href=<%=DASHBOARD_URI%>><i class="fas fa-home"></i><span>Dashboard</span></a>
   	</li>
 	<%
   	if(uri.equals(VEHICLE_INSPECTION_URI)) {
   	%>
     <li class="selected">
   <%
   	} else {
   %>
   <li>
   <% }%>
     <a href=<%=VEHICLE_INSPECTION_URI%>><i class="fas fa-wrench"></i><span>Revisioni</span></a>
     </li>
     <%
   	if(uri.equals(SETTINGS_URI)) {
   %>
     <li class="selected">
   <%
   	} else {
   %>
   <li>
   <% }%>
     <a href=<%=SETTINGS_URI%>><i class="fas fa-cog"></i><span>Impostazioni</span></a>
     </li>
   </ul>