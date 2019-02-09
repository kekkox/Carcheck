package it.carcheck.control.request;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AdminManager;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.interfaces.IAdmin;
import it.carcheck.utility.EmailSender;;

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
			return "admin/dashboard";
		} catch (SQLException e) {
			request.setAttribute("error", ERROR_MESSAGE);
			return "admin/dashboard";
		}

	}

	private static final String ERROR_MESSAGE = "Impossibile aggiungere un nuovo admin";
}
