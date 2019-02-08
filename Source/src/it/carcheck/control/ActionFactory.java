package it.carcheck.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import it.carcheck.control.exception.ActionNotFoundException;
import it.carcheck.control.interfaces.IAction;

public class ActionFactory {
	
	private static void initialize() {
		actions = new HashMap<String, IAction>();
		
		actions.put("POST/login", new WorkshopLoginAction());
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
