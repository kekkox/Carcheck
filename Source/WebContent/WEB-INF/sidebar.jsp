<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<c:if test="${fn:contains(fn:substringAfter(pageContext.request.requestURI, '/CarCheck/'), 'workshop/') }">

	<c:set var = "DASHBOARD_URI" value = "workshop/dashboard.jsp"/>
	<c:set var = "VEHICLE_INSPECTION_URI" value = "workshop/vehicleinspection.jsp"/>
	<c:set var = "SETTINGS_URI" value = "workshop/settings.jsp"/>
	<c:set var = "uri" value="${fn:substringAfter(pageContext.request.requestURI, '/CarCheck/')}"/>
	
	<ul class = "sidebar">
		<!-- Dashboard li -->
		<c:if test="${uri eq DASHBOARD_URI}">
			<li class="selected">
				<a href="../<c:out value = "${DASHBOARD_URI}"/>"><i class="fas fa-home"></i><span>Dashboard</span></a>
			</li>
		</c:if>
		<c:if test="${uri ne DASHBOARD_URI}">
			<li>
				<a href="../<c:out value = "${DASHBOARD_URI}"/>"><i class="fas fa-home"></i><span>Dashboard</span></a>
			</li>
		</c:if>
		
		<!-- VehicleInspection li -->
		<c:if test="${uri eq VEHICLE_INSPECTION_URI}">
			<li class="selected">
				<a href="../<c:out value = "${VEHICLE_INSPECTION_URI}"/>"><i class="fas fa-wrench"></i><span>Revisioni</span></a>
			</li>
		</c:if>
		<c:if test="${uri ne VEHICLE_INSPECTION_URI}">
			<li>
				<a href="../<c:out value = "${VEHICLE_INSPECTION_URI}"/>"><i class="fas fa-wrench"></i><span>Revisioni</span></a>
			</li>
		</c:if>
		
		<!-- Settings li -->
		<c:if test="${uri eq SETTINGS_URI}">
			<li class="selected">
				<a href="../<c:out value = "${SETTINGS_URI}"/>"><i class="fas fa-cog"></i><span>Impostazioni</span></a>
			</li>
		</c:if>
		<c:if test="${uri ne SETTINGS_URI}">
			<li>
				<a href="../<c:out value = "${SETTINGS_URI}"/>"><i class="fas fa-cog"></i><span>Impostazioni</span></a>
			</li>
		</c:if>
	</ul>
</c:if>

<c:if test="${fn:contains(fn:substringAfter(pageContext.request.requestURI, '/CarCheck/'), 'admin/internal/') }">

	<c:set var = "DASHBOARD_URI" value = "dashboard.jsp"/>
	<c:set var = "ADHESION_REQUESTS_URI" value = "adhesionRequests.jsp"/>
	<c:set var = "SETTINGS_URI" value = "settings.jsp"/>
	<c:set var = "uri" value="${fn:substringAfter(pageContext.request.requestURI, '/CarCheck/admin/internal/')}"/>
	
	<ul class = "sidebar">
		<!-- Dashboard li -->
		<c:if test="${uri eq DASHBOARD_URI}">
			<li class="selected">
				<a href="<c:out value = "${DASHBOARD_URI}"/>"><i class="fas fa-home"></i><span>Dashboard</span></a>
			</li>
		</c:if>
		<c:if test="${uri ne DASHBOARD_URI}">
			<li>
				<a href="<c:out value = "${DASHBOARD_URI}"/>"><i class="fas fa-home"></i><span>Dashboard</span></a>
			</li>
		</c:if>
		
		<!-- AdhesionRequests li -->
		<c:if test="${uri eq ADHESION_REQUESTS_URI}">
			<li class="selected">
				<a href="<c:out value = "${ADHESION_REQUESTS_URI}"/>"><i class="fas fa-clipboard-list"></i><span>Adesioni</span></a>
			</li>
		</c:if>
		<c:if test="${uri ne VEHICLE_INSPECTION_URI}">
			<li>
				<a href="<c:out value = "${ADHESION_REQUESTS_URI}"/>"><i class="fas fa-clipboard-list"></i><span>Adesioni</span></a>
			</li>
		</c:if>
		
		<!-- Settings li -->
		<c:if test="${uri eq SETTINGS_URI}">
			<li class="selected">
				<a href="../<c:out value = "${SETTINGS_URI}"/>"><i class="fas fa-cog"></i><span>Impostazioni</span></a>
			</li>
		</c:if>
		<c:if test="${uri ne SETTINGS_URI}">
			<li>
				<a href="../<c:out value = "${SETTINGS_URI}"/>"><i class="fas fa-cog"></i><span>Impostazioni</span></a>
			</li>
		</c:if>
	</ul>
</c:if>