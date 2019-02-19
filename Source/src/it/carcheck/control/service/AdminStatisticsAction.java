package it.carcheck.control.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AdhesionRequestManager;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.WorkshopManager;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.utility.JsonResponse;

public class AdminStatisticsAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		Gson gson = new Gson(); 
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		PrintWriter writer = response.getWriter();
		
		AdminBean admin = (AdminBean)request.getSession().getAttribute("user");
		if(admin == null)
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "incorrect or null admin")));
		
		AdhesionRequestManager adhesionManager = AdhesionRequestManager.getInstance();
		VehicleManager vehicleManager = VehicleManager.getInstance();
		WorkshopManager workshopManager = WorkshopManager.getInstance();
		
		Collection<AdhesionRequestBean> requests = adhesionManager.doRetrieveByAdminId(admin.getId());
		Collection<VehicleBean> vehicles = vehicleManager.doRetrieveAll();
		Collection<WorkshopBean> workshops = workshopManager.doRetrieveAll();
		
		if(vehicles == null || requests == null || workshops == null)
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "one or more results are null")));
		
		Map<String, Integer> result = new HashMap<String, Integer>();
		result.put("requests", requests.size());
		result.put("vehicles", vehicles.size());
		result.put("workshops", workshops.size());
		
		writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "", result)));
		return "admin_statistics_service";
	}

}
