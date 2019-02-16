package it.carcheck.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import it.carcheck.control.exception.ActionNotFoundException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.control.request.*;
import it.carcheck.control.service.*;


public class ActionFactory {
	
	private static void initialize() {
		actions = new HashMap<String, IAction>();
		
		// Requests
		actions.put("POST/signup", new WorkshopSignupAction());
		actions.put("POST/login", new WorkshopLoginAction());
		actions.put("POST/find", new VehicleFindAction());
		actions.put("POST/loginAdmin", new AdminLoginAction());
		actions.put("POST/signupAdmin", new AdminSignupAction());
		actions.put("GET/logout", new LogoutAction());
		actions.put("POST/workshopRevision", new RevisionAction());
		actions.put("GET/inspectioninsert", new WorkshopInsertInspectionAction());
		actions.put("GET/inspectionview", new WorkshopViewInspectionAction());
		actions.put("GET/inspectionedit", new WorkshopEditInspectionAction());
		actions.put("GET/adhesionView", new ViewAdhesionAction());
		actions.put("POST/workshopRevision", new RevisionAction());
		actions.put("GET/vehicles", new ShowVehicleAction());
		actions.put("GET/admins", new AdminListAction());
		actions.put("GET/adminForm", new AdminViewAction());
		actions.put("POST/InpectionOperation", new WorkshopInspectionOperation());
		// Services
		actions.put("POST/region_service", new RegionAction());
		actions.put("POST/province_service", new ProvinceAction());
		actions.put("POST/city_service", new CityAction());
		actions.put("POST/address_service", new AddressAction());
		actions.put("POST/mail_check_service", new EmailCheckerAction());
		actions.put("POST/admin_statistics_service", new AdminStatisticsAction());
		actions.put("GET/requests_service", new RequestsAction());
		actions.put("POST/admin_service", new AdminAction());
		actions.put("POST/vehicle_service", new VehicleAction());
		actions.put("POST/people_service", new PeopleAction());
		
	
	}
	
	public static IAction getAction(HttpServletRequest request) throws ActionNotFoundException{
		if(actions == null) {
			initialize();
		}
		
		
		IAction result = actions.get(request.getMethod() + request.getPathInfo());
		if(result == null)
			throw new ActionNotFoundException();
		else
			return result;
	}
	
	private static Map<String, IAction> actions;
}
