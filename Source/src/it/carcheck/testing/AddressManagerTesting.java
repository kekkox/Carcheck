package it.carcheck.testing;

import java.util.ArrayList;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.AddressManager;
import it.carcheck.model.bean.AddressBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AddressManagerTesting extends TestCase {
	
	public AddressManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = AddressManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void getAddressByKey() throws Exception {
		AddressBean bean = manager.getAddressByKey(14688226);
		assertNotNull(bean);
		assertEquals(bean.getId(), 14688226);
	}
	
	public void getFullAddressByName() throws Exception {
		ArrayList<AddressBean> beans = manager.getFullAddressByName("SS122", "084017");
		assertNotNull(beans);
	}
	
	public void getAddressByName() throws Exception {
		ArrayList<AddressBean> beans = manager.getAddressByName("SS122", "084017");
		assertNotNull(beans);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new AddressManagerTesting("getAddressByKey"));
		suite.addTest(new AddressManagerTesting("getFullAddressByName"));
		suite.addTest(new AddressManagerTesting("getAddressByName"));
		
		return suite;
	}
	
	private AddressManager manager;
}
