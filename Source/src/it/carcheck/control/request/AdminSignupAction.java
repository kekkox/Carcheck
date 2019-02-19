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
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.model.interfaces.IAdmin;
import it.carcheck.utility.EmailSender;
import it.carcheck.utility.JsonResponse;;

public class AdminSignupAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("suraname");
		int grade = Integer.parseInt(request.getParameter("grade"));

		AdminBean loggedadmin = (AdminBean) request.getSession().getAttribute("admin");

		AdminBean newAdmin = new AdminBean();
		newAdmin.setEmail(email);
		newAdmin.setPassword(password);
		newAdmin.setName(name);
		newAdmin.setSurname(surname);
		newAdmin.setGrade(grade);

		IAdmin adminManager = AdminManager.getInstance();

		try {
			adminManager.doAddAdmin(loggedadmin, newAdmin);
			String message = "Queste di seguito sono le tue credenziali\n email:" + email + "password:" + password;
			EmailSender credentials = EmailSender.GetInstance();
			credentials.SendEmail("BENEVENUTO NEL TEAM DI CARCHECK", message, email);
			response.setHeader(IAction.HEADER_NAME, IAction.REDIRECT_RESPONSE);
			return "admin/dashboard";
		} catch (SQLException e) {
			response.setHeader(HEADER_NAME, JSON_RESPONSE);
			JsonResponse jsonResponse = new JsonResponse(JsonResponseStatus.FAILED, "Internal server error");
			try {
				response.getWriter().println(new Gson().toJson(jsonResponse));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return "admin/addNewAdmin";
		}

	}
}
