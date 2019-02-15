package it.carcheck.control.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;

public class VehicleAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		Gson gson = new Gson(); 
		PrintWriter writer = response.getWriter();
		
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		//Operation type
		// 1 -> Add vehicle
		// 2 -> Remove vehicle
		String operation_str = request.getParameter("operation");
		if(operation_str == null) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "operation type is null")));
			return "vehicle_service";
		}
		
		Object userSession = request.getSession().getAttribute("user");
		if(userSession == null) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "user not in session (logged in?)")));
			return "vehicle_service";
		}
		
		if((userSession instanceof AdminBean) || (userSession instanceof WorkshopBean)) {
			VehicleManager vehicleManager = VehicleManager.getInstance();
			int operation_type = Integer.parseInt(operation_str);
			
			String licensePlate = request.getParameter("license");
			
			 switch(operation_type) {
			 case 1:
				 if(licensePlate == null) {
					 writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "license plate is null")));
					 return "vehicle_service";
				 }
				 
				 String description 	= request.getParameter("description");
				 String year 			= request.getParameter("year");
				 String displacement 	= request.getParameter("displacement");
				 String kw 				= request.getParameter("kw");
				 String fuel 			= request.getParameter("fuel");
				 String category 		= request.getParameter("category");
				 String euroClass 		= request.getParameter("euroClass");
				 String scrapped 		= request.getParameter("scrapped");
				 
				 if(description == null || year == null || displacement == null ||
				    kw == null || fuel == null || category == null || euroClass == null || scrapped == null) {
					 
					 writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "one or more parameters are null")));
					 return "vehicle_service";
				 }
				 
				 VehicleBean vehicle = new VehicleBean();
				 vehicle.setLicensePlate(licensePlate);
				 vehicle.setDescription(description);
				 vehicle.setYearOfRegistration(Integer.parseInt(year));
				 vehicle.setDisplacement(Integer.parseInt(displacement));
				 vehicle.setKw(Integer.parseInt(kw));
				 vehicle.setFuel(fuel);
				 vehicle.setCategory(category);
				 vehicle.setEuroClass(euroClass);
				 vehicle.setScrapped(Boolean.parseBoolean(scrapped));
				 
				 try {
					vehicleManager.doInsert(vehicle);
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
				} catch (SQLException e1) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to add vehicle to database")));
				}
				 
				 break;
			 case 2:
				 if(licensePlate == null) {
					 writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "license plate is null")));
					 return "vehicle_service";
				 }
				 
				 try {
					VehicleBean toRemove = vehicleManager.doRetriveVehicle(licensePlate);
					vehicleManager.doDelete(toRemove);
					
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
				} catch (SQLException e) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to find or delete vehicle (DB Error)")));
				}
				 break;
			 default:
				 writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "invalid operation type")));
				 break;
			 }
			
		} else {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "operation type is null")));
		}
		
		return "vehicle_service";
	}

}
