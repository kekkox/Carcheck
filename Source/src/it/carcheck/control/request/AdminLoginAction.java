package it.carcheck.control.request;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AdminManager;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.enums.JsonResponseStatus;

public class AdminLoginAction implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		try {
			AdminBean user = AdminManager.getInstance().doLogin(email, password);
			JsonResponse jsonResponse = new JsonResponse();
			if(user == null) {
				jsonResponse.setJsonResponseStatus(JsonResponseStatus.FAILED);
				jsonResponse.setJsonResponseMessage("Username o password errati");
				response.getWriter().println(new Gson().toJson(jsonResponse));
				return "admin/login";
			}
			else {
				request.getSession().setAttribute("user", user);
				jsonResponse.setJsonResponseStatus(JsonResponseStatus.OK);
				jsonResponse.setJsonResponseMessage("");
				jsonResponse.setJsonResponseContent(request.getContextPath() + "/admin/internal/dashboard.jsp");
				response.getWriter().println(new Gson().toJson(jsonResponse));
				return "admin/dashboard";
			}
		} 
		catch (SQLException | IOException e) {
			throw new ActionException();
		}
	}
}
