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
		String operation_str = request.getParameter("operation");
		if(operation_str == null) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "operation type is null")));
			return "admin_service";
		}
		
		Object userSession = request.getSession().getAttribute("user");
		if(userSession instanceof AdminBean) {
			
			AdminManager adminManager = AdminManager.getInstance();
			AdminBean admin = (AdminBean)userSession;
			
			int operation_type = Integer.parseInt(operation_str);
			switch(operation_type) {
			case 1:
				//email - name - surname
				String email = request.getParameter("email");
				String name = request.getParameter("name");
				String surname = request.getParameter("surname");
				
				if(email == null || name == null || surname == null) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "one or more parameters are null")));
					return "admin_service";
				}
				
				AdminBean newAdmin = new AdminBean();
				newAdmin.setEmail(email);
				newAdmin.setName(name);
				newAdmin.setSurname(surname);
				newAdmin.setFirstLogin(true);
				newAdmin.setGrade(Grade.DEFAULT_ADMIN);
				
				try {
					adminManager.doAddAdmin(admin, newAdmin);
				} catch (SQLException e) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to add new admin, exception")));
				}
				break;
			case 2:
				// admin Id
				String admin_id = request.getParameter("id");
				if(admin_id == null) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "admin id is null")));
					return "admin_service";
				}
				
				int adminId = Integer.parseInt(admin_id);
				AdminBean toRemove = adminManager.doRetrieveById(adminId);
				if(toRemove == null) {
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "admin not found into database")));
					return "admin_service";
				}
				
				try {
					adminManager.doRemoveAdmin(admin, toRemove);
					writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
				} catch (SQLException e) {
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
