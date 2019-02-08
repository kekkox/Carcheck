package it.carcheck.control.request;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.WorkshopManager;
import it.carcheck.model.bean.WorkshopBean;

public class WorkshopLoginAction implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		try {
			WorkshopBean user = WorkshopManager.getInstance().doLogin(email, password);
			
			if(user != null) {
				request.getSession().setAttribute("user", user);
				return "workshop/dashboard";
			}
			else {
				request.setAttribute("error", ERROR_MESSAGE);
				return "login";
			}
				
			
		} catch (SQLException e) {
			return "login";
		}
	}

	
	private static final String ERROR_MESSAGE = "Username o password errati";
}
