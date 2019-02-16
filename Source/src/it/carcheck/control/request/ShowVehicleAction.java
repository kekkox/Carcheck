package it.carcheck.control.request;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.interfaces.IVehicle;

public class ShowVehicleAction implements IAction{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		response.setHeader(IAction.HEADER_NAME, FORWARD_RESPONSE);
		
		IVehicle manager = VehicleManager.getInstance();
		request.setAttribute("vehicles", manager.doRetrieveAll());
		
		return "admin/vehicles";
	}

	
}
