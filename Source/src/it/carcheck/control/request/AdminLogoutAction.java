package it.carcheck.control.request;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;


public class AdminLogoutAction implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException{
			if(request.getSession().getAttribute("user") != null) {
			request.getSession().removeAttribute("user");
			return "login";
			}
			return null;
			}	
}
