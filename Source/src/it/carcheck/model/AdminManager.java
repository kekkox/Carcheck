package it.carcheck.model;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import it.carcheck.fastcrud.Configuration;
import it.carcheck.fastcrud.Database;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.enums.Grade;
import it.carcheck.model.bean.enums.RequestStatus;
import it.carcheck.model.interfaces.IAdmin;

public class AdminManager implements IAdmin{
	
	public AdminManager() {
		this.database = Database.Begin(new Configuration());
	}
	
	
	@Override
	public AdminBean doLogin(String email, String password) throws SQLException {
		AdminBean admin = (AdminBean) database.Find(new AdminBean(), "SELECT * FROM admin WHERE email = " + email + " AND password = " + password).get(0);
		return admin;
	}



	@Override
	public void doChangePassword(AdminBean user, String password) throws SQLException {
		user.setPassword(password);
		database.Update(user);
	}



	@Override
	public void doSave(AdminBean element) throws SQLException {
		database.Update(element);
	}



	@Override
	public void doDelete(AdminBean element) throws SQLException {
		database.Delete(element);
	}



	@Override
	public void doInsert(AdminBean element) throws SQLException {
		database.Insert(element);
	}



	@Override
	public ArrayList<AdminBean> doFind(String query) throws SQLException {
		return database.Find(new AdminBean(), query);
	}



	@Override
	public void doAddAdmin(AdminBean loggedAdmin, AdminBean admin) throws SQLException {
		if(loggedAdmin.getGrade() == Grade.SUPER_ADMIN)
			this.doInsert(admin);
		//TODO Catch exception if admin doesn't have permission
	}



	@Override
	public void doRemoveAdmin(AdminBean loggedAdmin, AdminBean admin) throws SQLException {
		if(loggedAdmin.getGrade() == Grade.SUPER_ADMIN)
			this.doDelete(admin);
		//TODO Catch exception if admin doesn't have permission
	}



	@Override
	public void doSetAdminPermission(AdminBean admin, int grade) throws SQLException {
		admin.setGrade(grade);
		this.doSave(admin);
	}



	@Override
	public void doApproveRequest(AdhesionRequestBean request) throws SQLException {
		request.setStatus(RequestStatus.APPROVED);
		AdhesionRequestManager requestManager = new AdhesionRequestManager();
		requestManager.doSave(request);
	}



	@Override
	public void doRejectRequest(AdhesionRequestBean request) throws SQLException {
		request.setStatus(RequestStatus.REFUSED);
		AdhesionRequestManager requestManager = new AdhesionRequestManager();
		requestManager.doSave(request);
	}



	@Override
	public void doSetRequestAppointment(AdhesionRequestBean request, Date date, Time time) throws SQLException {
		request.setStatus(RequestStatus.APPOINTMENT);
		request.setMeeetingHour(time);
		request.setMeetingDate(date);
		AdhesionRequestManager requestManager = new AdhesionRequestManager();
		requestManager.doSave(request);
	}
	
	private Database database;
}
