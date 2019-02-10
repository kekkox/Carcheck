package it.carcheck.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import it.carcheck.control.exception.ActionNotFoundException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.control.request.WorkshopLoginAction;
import it.carcheck.control.service.AddressAction;
import it.carcheck.control.service.CityAction;
import it.carcheck.control.service.ProvinceAction;
import it.carcheck.control.service.RegionAction;

public class ActionFactory {
	
	private static void initialize() {
		actions = new HashMap<String, IAction>();
		
		// Requests
		actions.put("POST/login", new WorkshopLoginAction());
		
		// Services
		actions.put("POST/region_service", new RegionAction());
		actions.put("POST/province_service", new ProvinceAction());
		actions.put("POST/city_service", new CityAction());
		actions.put("POST/address_service", new AddressAction());
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
