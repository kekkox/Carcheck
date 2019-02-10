package it.carcheck.control.request;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.bean.AdminBean;


public class LogoutAction implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException{
		
		if (request.getSession().getAttribute("user") != null) {
			request.getSession().removeAttribute("user");
			
			if((request.getSession().getAttribute("user")) instanceof AdminBean)
				return "admin/login";
			else 
				return "index";
		}
		return "index";
	}
}
