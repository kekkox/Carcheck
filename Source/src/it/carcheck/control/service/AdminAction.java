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
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.enums.Grade;
import it.carcheck.model.bean.enums.JsonResponseStatus;

public class AdminAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		Gson gson = new Gson(); 
		PrintWriter writer = response.getWriter();
		
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		//operation_type
		// 1 -> insert admin
		// 2 -> remove admin
		// 3 -> edit admin
		String operation_str = request.getParameter("operation");
		if(operation_str == null) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "operation type is null")));
			return "admin_service";
		}
		
		Object userSession = request.getSession().getAttribute("user");
		if(userSession == null) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "user not in session (logged in?)")));
			return "admin_service";
		}
		
		if(userSession instanceof AdminBean) {
			
			AdminManager adminManager = AdminManager.getInstance();
			AdminBean admin = (AdminBean)userSession;
			
			int operation_type = Integer.parseInt(operation_str);
			switch(operation_type) {
			case 1:
				//email - name - surname - grade
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String surname = request.getParameter("surname");
				int grade = Integer.parseInt(request.getParameter("grade"));
				
				if(email == null || name == null || surname == null) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "one or more parameters are null")));
					return "admin_service";
				}
				
				AdminBean newAdmin = new AdminBean();
				newAdmin.setEmail(email);
				newAdmin.setName(name);
				newAdmin.setSurname(surname);
				newAdmin.setFirstLogin(true);
				newAdmin.setGrade(grade);
				
				try {
					adminManager.doAddAdmin(admin, newAdmin);
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "Amministratore aggiunto con successo")));
				} catch (SQLException e) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to add new admin, exception")));
				}
				break;
			case 2:
				String adminEmail = request.getParameter("email");
				if(adminEmail == null) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "admin id is null")));
					return "admin_service";
				}
				try {
					AdminBean toRemove = adminManager.doRetrieveByEmail(adminEmail);
					if(toRemove == null) {
						writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "admin not found into database")));
						return "admin_service";
					}
				
					adminManager.doRemoveAdmin(admin, toRemove);
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "Admin rimosso con successo")));
				} catch (SQLException e) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to remove admin (SQL Ex)")));
				}
				break;
			case 3:
				String emailAdmin = request.getParameter("email");
				String adminName = request.getParameter("name");
				String adminSurname = request.getParameter("surname");
				int adminGrade = Integer.parseInt(request.getParameter("grade"));
				
				if(emailAdmin == null) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "admin id is null")));
					return "admin_service";
				}
				
				try {
					AdminBean bean = adminManager.doRetrieveByEmail(emailAdmin);
					bean.setGrade(adminGrade);
					bean.setName(adminName);
					bean.setSurname(adminSurname);
					
					adminManager.doSave(bean);
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "Aggiornamento effettuato con successo")));
				} catch(SQLException e) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to remove admin (SQL Ex)")));
				}
				
				break;
					
			default:
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "invalid operation type")));
				break;
			}
		}
		else {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "invalid user, Warning")));
		}
		
		return "admin_service";
	}

}
