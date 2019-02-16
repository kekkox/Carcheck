package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.enums.Grade;
import it.carcheck.model.interfaces.IAdmin;
import it.carcheck.utility.EmailSender;
import it.carcheck.utility.PasswordHasher;

public class AdminManager implements IAdmin{
	
	public static AdminManager getInstance() {
		if(instance == null)
			instance = new AdminManager();
		
		return instance;
	}
	
	private AdminManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public AdminBean doLogin(String email, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		
		AdminBean admin;
		try {
			admin = database.find(AdminBean.class, "SELECT * FROM admin WHERE email = ? AND password = ?", email, cryptedPassword).get(0);
			return admin;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public void doChangePassword(AdminBean user, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		user.setPassword(cryptedPassword);
		
		try {
			database.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public AdminBean doRetrieveByEmail(String email) throws SQLException {
		ArrayList<AdminBean> queryResult = this.doFind("SELECT * FROM admin WHERE email = ?", email);
		
		if(queryResult == null || queryResult.size() <= 0)
			return null;
		else
			return queryResult.get(0);
	}



	@Override
	public void doSave(AdminBean element) throws SQLException {
		try {
			database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void doDelete(AdminBean element) throws SQLException {
		try {
			database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void doInsert(AdminBean element) throws SQLException {
		try {
			database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public ArrayList<AdminBean> doFind(String query, Object...args) throws SQLException {
		try {
			return database.find(AdminBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}



	@Override
	public void doAddAdmin(AdminBean loggedAdmin, AdminBean admin) throws SQLException {
		if(loggedAdmin.getGrade() == Grade.SUPER_ADMIN) {
			String password = UUID.randomUUID().toString();
			password = password.substring(password.lastIndexOf("-") + 1);
			String encryptedPassword = PasswordHasher.Encrypt(password);
			admin.setPassword(encryptedPassword);
			this.doInsert(admin);
			EmailSender.GetInstance().SendEmail("Benvenuto, " + admin.getName(), 
					"Gentile " + admin.getName() + ", sei stato nominato nuovo amministratore di CarCheck.\n Accedi ai servizi con la seguente password:\n\n" + password + "\n\nCordiali saluti, CarCheck.", 
					admin.getEmail());
		}
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
	public AdminBean doRetrieveById(int id) {
		try {
			return this.doFind("SELECT * FROM admin WHERE id = ?", id).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Collection<AdminBean> doRetrieveAll() {
		try {
			return this.doFind("SELECT * FROM admin");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private CarcheckDatabase database;
	private static AdminManager instance;
}
