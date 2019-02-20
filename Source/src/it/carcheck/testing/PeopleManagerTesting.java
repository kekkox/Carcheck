package it.carcheck.testing;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.PeopleManager;
import it.carcheck.model.bean.PeopleBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PeopleManagerTesting extends TestCase {

	public PeopleManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = PeopleManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void doRetrieveByFiscalCode() throws Exception {
		PeopleBean bean = manager.doRetrieveByFiscalCode("CPRFNC96M14C129F");
		assertNotNull(bean);
		assertEquals(bean.getFiscalCode(), "CPRFNC96M14C129F");
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new PeopleManagerTesting("doRetrieveByFiscalCode"));
		
		return suite;
	}
	
	private PeopleManager manager;
}
