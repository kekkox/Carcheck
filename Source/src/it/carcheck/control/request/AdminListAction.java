package it.carcheck.control.request;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AdminManager;
import it.carcheck.model.interfaces.IAdmin;

public class AdminListAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		response.setHeader(IAction.HEADER_NAME, IAction.FORWARD_RESPONSE);
		
		IAdmin manager = AdminManager.getInstance();
		
		request.setAttribute("admins", manager.doRetrieveAll());
		
		return "admin/admins";
	}
	
}
