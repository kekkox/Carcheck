<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.carcheck.model.bean.enums.Grade" %>

<c:set var = "DASHBOARD_URI" value = "dashboard.jsp"/>
<c:set var = "ADHESION_REQUESTS_URI" value = "adhesionRequests.jsp"/>
<c:set var = "SETTINGS_URI" value = "settings.jsp"/>
<c:set var = "ADMINS_URI" value = "admins"/>
<c:set var = "VEHICLES_URI" value = "vehicles"/>
<c:set var = "uri" value="${fn:substringAfter(pageContext.request.requestURI, '/CarCheck/admin/internal/')}"/>

<ul class = "sidebar">
	<!-- Dashboard li -->
	<c:if test="${uri eq DASHBOARD_URI}">
		<li class="selected">
			<a href="/CarCheck/admin/internal/${DASHBOARD_URI}"><i class="fas fa-home"></i><span>Dashboard</span></a>
		</li>
	</c:if>
	<c:if test="${uri ne DASHBOARD_URI}">
		<li>
			<a href="/CarCheck/admin/internal/${DASHBOARD_URI}"><i class="fas fa-home"></i><span>Dashboard</span></a>
		</li>
	</c:if>
	
	<!-- AdhesionRequests li -->
	<c:if test="${uri eq ADHESION_REQUESTS_URI}">
		<li class="selected">
			<a href="/CarCheck/admin/internal/${ADHESION_REQUESTS_URI}"><i class="fas fa-clipboard-list"></i><span>Adesioni</span></a>
		</li>
	</c:if>
	<c:if test="${uri ne ADHESION_REQUESTS_URI}">
		<li>
			<a href="/CarCheck/admin/internal/${ADHESION_REQUESTS_URI}"><i class="fas fa-clipboard-list"></i><span>Adesioni</span></a>
		</li>
	</c:if>
	
	<!-- Vehicles li -->
	<c:if test="${uri eq VEHICLES_URI}">
		<li class="selected">
			<a href="/CarCheck/RequestHandler/${VEHICLES_URI}"><i class="fas fa-car"></i><span>Veicoli</span></a>
		</li>
	</c:if>
	<c:if test="${uri ne VEHICLES_URI}">
		<li>
			<a href="/CarCheck/RequestHandler/${VEHICLES_URI}"><i class="fas fa-car"></i><span>Veicoli</span></a>
		</li>
	</c:if>
	
	<c:if test="${sessionScope.user.grade eq Grade.SUPER_ADMIN}">
		<c:if test="${uri eq ADMINS_URI}">
			<li class="selected">
				<a href="/CarCheck/RequestHandler/${ADMINS_URI}"><i class="fas fa-user"></i><span>Amministratori</span></a>
			</li>
		</c:if>
		<c:if test="${uri ne ADMINS_URI}">
			<li>
				<a href="/CarCheck/RequestHandler/${ADMINS_URI}"><i class="fas fa-user"></i><span>Amministratori</span></a>
			</li>
		</c:if>
	</c:if>
	
	<!-- Settings li -->
	<c:if test="${uri eq SETTINGS_URI}">
		<li class="selected">
			<a href="/CarCheck/admin/internal/${SETTINGS_URI}"><i class="fas fa-cog"></i><span>Impostazioni</span></a>
		</li>
	</c:if>
	<c:if test="${uri ne SETTINGS_URI}">
		<li>
			<a href="/CarCheck/admin/internal/${SETTINGS_URI}"><i class="fas fa-cog"></i><span>Impostazioni</span></a>
		</li>
	</c:if>
	
	<li id="last">
    	<a href="/CarCheck/RequestHandler/logout"><i class="fas fa-sign-out-alt"></i><span>Logout</span></a>
    </li>
</ul>