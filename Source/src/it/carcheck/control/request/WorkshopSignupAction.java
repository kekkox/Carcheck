package it.carcheck.control.request;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.WorkshopManager;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.interfaces.IWorkshop;

public class WorkshopSignupAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		//Initializing parameters
		String piva = request.getParameter("iva");
		String owner = request.getParameter("owner");
		String email = request.getParameter("email");
		int city = Integer.parseInt(request.getParameter("city"));
		int address = Integer.parseInt(request.getParameter("address"));
		String description = request.getParameter("description");
		
		WorkshopBean workshopBean = new WorkshopBean();
		workshopBean.setpIva(piva);
		workshopBean.setOwner(owner);
		workshopBean.setEmail(email);
		workshopBean.setCity(city);
		workshopBean.setAddress(""+address); //TODO replace with int after database edit
		workshopBean.setDescription(description);
		
		IWorkshop workshopManager = WorkshopManager.getInstance();
		
		try {
			workshopManager.doSignUp(workshopBean);
			return "signup";
		} catch(SQLException e) {
			request.setAttribute("error", ERROR_MESSAGE);
			return "signup";
		}
	}

	private static final String ERROR_MESSAGE = "Impossibile inviare la richiesta di adesione";
}
