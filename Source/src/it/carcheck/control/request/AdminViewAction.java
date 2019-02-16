package it.carcheck.control.request;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionBadFormedException;
import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AdminManager;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.interfaces.IAdmin;

public class AdminViewAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		response.setHeader(IAction.HEADER_NAME, IAction.FORWARD_RESPONSE);
		
		String action = request.getParameter("action");
		
		if(action == null)
			throw new ActionBadFormedException();
		
		int actionCode = Integer.parseInt(action);
		
		switch(actionCode) {
			case 1:
				request.setAttribute("actionCode", actionCode);
				return "admin/adminForm";
			case 2:				
				String email = request.getParameter("email");
				if(email == null)
					throw new ActionBadFormedException();
				
				IAdmin manager = AdminManager.getInstance();
				
				AdminBean adminToEdit;
				try {
					adminToEdit = manager.doRetrieveByEmail(email);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new ActionException();
				}
				
				if(adminToEdit == null)
					throw new ActionBadFormedException();
				
				request.setAttribute("actionCode", actionCode);
				request.setAttribute("name", adminToEdit.getName());
				request.setAttribute("surname", adminToEdit.getSurname());
				request.setAttribute("email", adminToEdit.getEmail());
				request.setAttribute("grade", adminToEdit.getGrade());
				
				return "admin/adminForm";
			default:
				return "";
		}
	}
	
}
