package it.carcheck.testing;

import java.util.ArrayList;
import java.util.Collection;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.AdminManager;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.enums.Grade;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AdminManagerTesting extends TestCase {

	public AdminManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = AdminManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void doLogin() throws Exception {
		AdminBean bean = manager.doLogin("email", "password");
		assertNotNull(bean);
	}
	
	public void doChangePassword() throws Exception {
		ArrayList<AdminBean> admins = manager.doFind("select * from admin");
		assertNotNull(admins);
		
		AdminBean admin = admins.get(0);
		assertNotNull(admin);
		
		manager.doChangePassword(admin, "newPass");
		
		ArrayList<AdminBean> finalAdmins = manager.doFind("select * from admin where email = ?", admin.getEmail());
		assertNotNull(finalAdmins);
		
		AdminBean finalAdmin = finalAdmins.get(0);
		assertNotSame(admin.getPassword(), finalAdmin.getPassword());
	}
	
	public void doRetrieveByEmail() throws Exception {
		AdminBean admin = manager.doRetrieveByEmail("email");
		assertNotNull(admin);
	}
	
	public void doAddAdmin() throws Exception {
		AdminBean newAdmin = new AdminBean();
		
		ArrayList<AdminBean> admins = manager.doFind("select * from admin where grade = ?", Grade.SUPER_ADMIN);
		AdminBean admin = admins.get(0);
		
		assertNotNull(admin);
		
		manager.doAddAdmin(admin, newAdmin);
		
		ArrayList<AdminBean> finalAdmins = manager.doFind("select * from admin where email = ?", newAdmin.getEmail());
		assertNotNull(finalAdmins);
	}
	
	public void doRemoveAdmin() throws Exception {
		AdminBean newAdmin = new AdminBean();
		
		ArrayList<AdminBean> admins = manager.doFind("select * from admin where grade = ?", Grade.SUPER_ADMIN);
		AdminBean admin = admins.get(0);
		
		assertNotNull(admin);
		
		manager.doRemoveAdmin(admin, newAdmin);
		
		ArrayList<AdminBean> finalAdmins = manager.doFind("select * from admin where email = ?", newAdmin.getEmail());
		assertNull(finalAdmins);
	}
	
	public void doSetAdminPermission() throws Exception {
		ArrayList<AdminBean> admins = manager.doFind("select * from admin");
		AdminBean admin = admins.get(0);
		
		assertNotNull(admin);
		
		manager.doSetAdminPermission(admin, Grade.SUPER_ADMIN);
		
		ArrayList<AdminBean> finalAdmins = manager.doFind("select * from admin where email = ?", admin.getEmail());
		AdminBean finalAdmin = finalAdmins.get(0);
		
		assertEquals(Grade.SUPER_ADMIN, finalAdmin.getGrade());
	}
	
	public void doRetrieveById() throws Exception {
		AdminBean admin = manager.doRetrieveById(1);
		assertNotNull(admin);
	}
	
	public void doRetrieveAll() throws Exception {
		Collection<AdminBean> admins = manager.doRetrieveAll();
		assertNotNull(admins);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new AdminManagerTesting("doLogin"));
		suite.addTest(new AdminManagerTesting("doChangePassword"));
		suite.addTest(new AdminManagerTesting("doRetrieveByEmail"));
		suite.addTest(new AdminManagerTesting("doAddAdmin"));
		suite.addTest(new AdminManagerTesting("doRemoveAdmin"));
		suite.addTest(new AdminManagerTesting("doSetAdminPermission"));
		suite.addTest(new AdminManagerTesting("doRetrieveById"));
		suite.addTest(new AdminManagerTesting("doRetrieveAll"));
		
		return suite;
	}
	
	private AdminManager manager;
}
