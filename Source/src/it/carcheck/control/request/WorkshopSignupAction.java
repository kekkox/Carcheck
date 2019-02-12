package it.carcheck.control.request;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.WorkshopManager;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.model.interfaces.IWorkshop;

public class WorkshopSignupAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		//Initializing parameters
		String piva = request.getParameter("iva");
		String owner = request.getParameter("owner");
		String email = request.getParameter("email");
		String businessName = request.getParameter("businessName");
		String telephone = request.getParameter("telephone");
		int address = Integer.parseInt(request.getParameter("address"));
		String description = request.getParameter("description");
		
		WorkshopBean workshopBean = new WorkshopBean();
		workshopBean.setpIva(piva);
		workshopBean.setOwner(owner);
		workshopBean.setBusinessName(businessName);
		workshopBean.setTelephone(telephone);
		workshopBean.setEmail(email);
		workshopBean.setAddress(address);
		workshopBean.setFirstLogin(true);
		workshopBean.setDescription(description);
		
		IWorkshop workshopManager = WorkshopManager.getInstance();
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		try {
			workshopManager.doSignUp(workshopBean);
			JsonResponse jsonResponse = new JsonResponse(JsonResponseStatus.OK, SUCCESS_MESSAGE);
			response.getWriter().println(new Gson().toJson(jsonResponse));
			return "signup";
		} catch(SQLException | IOException e) {
			throw new ActionException();
		}
	}

	private static final String SUCCESS_MESSAGE = "Richiesta inviata con successo. Controlla la tua casella di posta";
}
