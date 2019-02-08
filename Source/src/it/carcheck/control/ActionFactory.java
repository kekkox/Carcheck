package it.carcheck.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import it.carcheck.control.interfaces.IAction;

public class ActionFactory {
	
	private static void initialize() {
		actions = new HashMap<String, IAction>();
		
		actions.put("POST/login", new WorkshopLoginAction());
	}
	
	public static IAction getAction(HttpServletRequest request) {
		if(actions == null) {
			initialize();
		}
		
		System.out.println(request.getMethod() + request.getPathInfo() + "\n");
		return actions.get(request.getMethod() + request.getPathInfo());
	}
	
	private static Map<String, IAction> actions;
}
