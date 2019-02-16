package it.carcheck.model.interfaces;

import it.carcheck.model.bean.AdminBean;

import java.sql.SQLException;

public interface IAdmin extends IUser<AdminBean>, IDatabaseOperation<AdminBean> {

	public void doAddAdmin(AdminBean loggedAdmin, AdminBean admin) throws SQLException;

	public void doRemoveAdmin(AdminBean loggedAdmin, AdminBean admin) throws SQLException;
	
	public AdminBean doRetrieveByEmail(String email) throws SQLException;

	public void doSetAdminPermission(AdminBean admin, int grade) throws SQLException;

	public AdminBean doRetrieveById(int id);
}
