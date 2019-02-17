package it.carcheck.control.service;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.model.interfaces.IVehicleInspection;

public class FindVehicleAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		Object user = request.getSession().getAttribute("user");
		String licensePlate = request.getParameter("licensePlate");
		
		if(user == null || !(user instanceof WorkshopBean)) {
			response.getWriter().println(new Gson().toJson(new JsonResponse(JsonResponseStatus.FAILED, "User not logged in")));
			return "find_vehicle_service";
		}
		
		if(licensePlate == null || licensePlate.length() == 0) {
			response.getWriter().println(new Gson().toJson(new JsonResponse(JsonResponseStatus.FAILED, "License plate not specified")));
			return "find_vehicle_service";
		}
		
		WorkshopBean loggedUser = (WorkshopBean) user;
		
		IVehicleInspection manager = VehicleInspectionManager.getInstance();
		Collection<VehicleInspectionBean> vehicles = manager.doRetrieveByLicensePlateLike(loggedUser, licensePlate);
		
		response.getWriter().println(new Gson().toJson(new JsonResponse(JsonResponseStatus.OK,"Success", vehicles)));
		return "find_vehicle_service";
	}
	
}
