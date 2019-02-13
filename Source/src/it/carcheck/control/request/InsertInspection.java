package it.carcheck.control.request;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;

public class InsertInspection implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

		String licenseplate = request.getParameter("licensePlace");
		int  km =Integer.parseInt(request.getParameter("km"));
		String file = request.getParameter("file");
		Date inspectionDate=Date.valueOf(request.getParameter("inspectionDate"));
		boolean state;
		if(request.getParameter("approved")=="true")
			state=true;
		else
			state=false;
		response.setHeader(IAction.HEADER_NAME,IAction.REDIRECT_RESPONSE);
		
		VehicleInspectionManager inspection = VehicleInspectionManager.getInstance();
		VehicleManager vehicle = VehicleManager.getInstance();
		VehicleInspectionBean vehicleinspection = new VehicleInspectionBean();
		WorkshopBean workshop = (WorkshopBean) request.getSession().getAttribute("user");
		
		vehicleinspection.setVehicle(licenseplate);
		vehicleinspection.setInspectionDate(inspectionDate);
		vehicleinspection.setKm(km);
		vehicleinspection.setWorkShop(workshop.getId());
		vehicleinspection.setResult(state);
		vehicleinspection.setPhoto(file);
		
		try{
			inspection.doInsert(vehicleinspection);
			return "home";
			
		}
		catch(SQLException e)
		{
			throw new  ActionException();
		}
		
	}

}