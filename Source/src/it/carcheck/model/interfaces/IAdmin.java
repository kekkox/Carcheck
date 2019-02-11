package it.carcheck.model.interfaces;

import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.AdminBean;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public interface IAdmin extends IUser<AdminBean>, IDatabaseOperation<AdminBean> {

	public void doAddAdmin(AdminBean loggedAdmin, AdminBean admin) throws SQLException;

	public void doRemoveAdmin(AdminBean loggedAdmin, AdminBean admin) throws SQLException;
	
	public AdminBean doRetrieveByEmail(String email) throws SQLException;

	public void doSetAdminPermission(AdminBean admin, int grade) throws SQLException;

	public void doApproveRequest(AdhesionRequestBean request) throws SQLException;

	public void doRejectRequest(AdhesionRequestBean request) throws SQLException;

	public void doSetRequestAppointment(AdhesionRequestBean request, Date date, Time time) throws SQLException;

}
