package it.carcheck.control.request;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;

@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class WorkshopInspectionOperation extends HttpServlet implements IAction{
	
	public String execute(HttpServletRequest request, HttpServletResponse response)  throws ActionException, ServletException, IOException, ParseException{
		
		String operation = request.getParameter("button");
		String licenseplate = request.getParameter("licensePlate");
		
		response.setHeader(IAction.HEADER_NAME, IAction.REDIRECT_RESPONSE);
		WorkshopBean workshop = (WorkshopBean) request.getSession().getAttribute("user");
			VehicleInspectionManager inspectionman = (VehicleInspectionManager) VehicleInspectionManager.getInstance();
		 

		
		try {
			
	
			VehicleBean vehicle = VehicleManager.getInstance().doRetriveVehicle(licenseplate);
			if (vehicle == null) {
				return "workshop/vehicleinspection";
			}
			
			String inspectionDate = request.getParameter("inspectionDate");
			int km = Integer.parseInt(request.getParameter("km"));
			String state = request.getParameter("state");
			String photo = request.getParameter("photo");
			if (operation.equals("ADD")) 
			{
				//PrintWriter writer = response.getWriter();
	
				VehicleInspectionBean inspection = new VehicleInspectionBean();
				inspection.setVehicle(vehicle.getLicensePlate());
				inspection.setInspectionDate(Date.valueOf((inspectionDate)));
				inspection.setKm(km);
				int year = Integer.parseInt(inspectionDate.substring(0,4));
				year=year+2;
				String expirationdate = inspectionDate.substring(4);
				expirationdate=year+expirationdate;
				
					inspection.setExpirationDate(Date.valueOf(expirationdate));
				inspection.setPhoto("photo");
				if(state=="1")
				inspection.setResult(true);
				else 
					inspection.setResult(false);
				inspection.setWorkShop(workshop.getId());
			inspectionman.doInsert(inspection);
			return "workshop/vehicleinspection";
				}
				
			else if(operation.equals("EDIT"))
			{
				int inspectionKey = Integer.parseInt(request.getParameter("inspectionKey"));
				VehicleInspectionBean inspection = inspectionman.doRetrieveByKey(workshop, inspectionKey);
				inspection.setVehicle(vehicle.getLicensePlate());
				inspection.setInspectionDate(Date.valueOf((inspectionDate)));
				inspection.setKm(km);
				int year = Integer.parseInt(inspectionDate.substring(0,4));
				year=year+2;
				String expirationdate = inspectionDate.substring(4);
				expirationdate=year+expirationdate;
				inspection.setExpirationDate(Date.valueOf(expirationdate));
				inspection.setPhoto(photo);
				if(state=="1")
				inspection.setResult(true);
				else 
				inspection.setResult(false);
				inspection.setWorkShop(workshop.getId());
				inspectionman.doSave(inspection);
				return "workshop/vehicleinspection";

			}
		} 
		
		catch (SQLException e) {
			throw new ActionException();
		}
		return null;

	}

}
