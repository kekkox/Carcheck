package it.carcheck.control.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AdminManager;
import it.carcheck.model.WorkshopManager;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.utility.JsonResponse;

public class EmailCheckerAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		String emailToCheck = request.getParameter("email");
		
		Gson gson = new Gson(); 
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);

		AdminManager adminManager = AdminManager.getInstance();
		WorkshopManager workshopManager = WorkshopManager.getInstance();
		PrintWriter writer = response.getWriter();
		
		try {
			if(adminManager.doRetrieveByEmail(emailToCheck) != null ||
			   workshopManager.doRetrieveByEmail(emailToCheck) != null)
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "", true)));
			else
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "", false)));
		} catch (SQLException e) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, e.getMessage())));
			e.printStackTrace();
		}
		
		return "mail_check_service";
	}

}
