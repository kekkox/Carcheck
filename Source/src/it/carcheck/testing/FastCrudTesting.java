package it.carcheck.testing;

import java.util.LinkedHashSet;

import it.carcheck.model.bean.AdminBean;
import it.dsoft.fastcrud.DatabaseConfiguration;
import it.dsoft.fastcrud.FastCrud;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FastCrudTesting extends TestCase {
	
	public FastCrudTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		DatabaseConfiguration configuration = new DatabaseConfiguration("root", "root", "carcheck");
		this.database = new FastCrud(configuration);
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void testCreate() throws Exception {
		AdminBean adminBean = new AdminBean();
		adminBean.setEmail("test@gmail.com");
		adminBean.setName("Testing");
		adminBean.setSurname("Testing");
		adminBean.setPassword("XXXXXX");
		adminBean.setFirstLogin(true);
		adminBean.setGrade(0);
		
		int result = this.database.create(adminBean);
		assertNotSame(0, result);
	}
	
	public void testRead() throws Exception {
		LinkedHashSet<AdminBean> result = this.database.read(AdminBean.class, "select * from admin");
		assertNotNull(result);
	}
	
	public void testUpdate() throws Exception {
		LinkedHashSet<AdminBean> result = this.database.read(AdminBean.class, "select * from admin");
		if(result.size() > 0) {
			AdminBean bean = result.iterator().next();
			bean.setEmail("test_admin");
			
			int res = this.database.update(bean);
			assertNotSame(0, res);
		}
	}
	
	public void testDelete() throws Exception {
		LinkedHashSet<AdminBean> result = this.database.read(AdminBean.class, "select * from admin where email = 'test@gmail.com'");
		if(result.size() > 0) {
			AdminBean bean = result.iterator().next();
			int res = this.database.delete(bean);
			
			assertNotSame(0, res);
		}
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new FastCrudTesting("testCreate"));
		suite.addTest(new FastCrudTesting("testRead"));
		suite.addTest(new FastCrudTesting("testUpdate"));
		suite.addTest(new FastCrudTesting("testDelete"));
		
		return suite;
	}
	
	private FastCrud database;
}
