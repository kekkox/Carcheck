package it.carcheck.control.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionBadFormedException;
import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AdminManager;
import it.carcheck.model.WorkshopManager;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.model.interfaces.IAdmin;
import it.carcheck.model.interfaces.IWorkshop;
import it.carcheck.utility.JsonResponse;

public class ChangePasswordAction implements IAction{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		Object user = request.getSession().getAttribute("user");
		if(user == null)
			throw new ActionBadFormedException();
		
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("password");
		String repeatedPassword = request.getParameter("repeatedPassword");
		
		PrintWriter writer = response.getWriter();
		
		if(oldPassword == null || newPassword == null || repeatedPassword == null) {
			writer.println(new Gson().toJson(new JsonResponse(JsonResponseStatus.FAILED, "Tutti i campi sono obbligatori")));
			return "change_password_service";
		}
		
		if(!newPassword.equals(repeatedPassword)) {
			writer.println(new Gson().toJson(new JsonResponse(JsonResponseStatus.FAILED, "Le due nuove password non coincidono")));
			return "change_password_service";
		}
		
		try {
			if(user instanceof AdminBean) {
				AdminBean bean = (AdminBean) user;
				IAdmin manager = AdminManager.getInstance();
				
				if(manager.doLogin(bean.getEmail(), oldPassword) == null) {
					writer.println(new Gson().toJson(new JsonResponse(JsonResponseStatus.FAILED, "Password errata")));
					return "change_password_service";
				}
				
				manager.doChangePassword(bean, newPassword);
				writer.println(new Gson().toJson(new JsonResponse(JsonResponseStatus.OK, "Password cambiata con successo", "/CarCheck/admin/internal/dashboard.jsp")));
			}
			else if(user instanceof WorkshopBean) {
				WorkshopBean bean = (WorkshopBean) user;
				IWorkshop manager = WorkshopManager.getInstance();

				if(manager.doLogin(bean.getEmail(), oldPassword) == null) {
					writer.println(new Gson().toJson(new JsonResponse(JsonResponseStatus.FAILED, "Password errata")));
					return "change_password_service";
				}
				
				manager.doChangePassword(bean, newPassword);
				writer.println(new Gson().toJson(new JsonResponse(JsonResponseStatus.OK, "Password cambiata con successo", "/CarCheck/workshop/dashboard.jsp")));
			}
		} catch (SQLException e) {
			throw new ActionException();
		}
		
		return "change_password_service";
	}

}
