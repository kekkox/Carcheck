package it.carcheck.testing;

import java.util.Collection;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.WorkshopAdhesionManager;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.WorkshopAdhesionBean;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class WorkshopAdhesionManagerTesting extends TestCase {

	public WorkshopAdhesionManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = WorkshopAdhesionManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void doRetrieveByWorkshopId() throws Exception {
		WorkshopAdhesionBean bean = manager.doRetrieveByWorkshopId(1, new AdminBean());
		assertNotNull(bean);
	}
	
	public void doRetrieveByAdhesionId() throws Exception {
		WorkshopAdhesionBean bean = manager.doRetrieveByAdhesionId(1, new AdminBean());
		assertNotNull(bean);
	}
	
	public void doRetrieveAll() throws Exception {
		Collection<WorkshopAdhesionBean> beans = manager.doRetrieveAll(new AdminBean());
		assertNotNull(beans);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new WorkshopAdhesionManagerTesting("doRetrieveByWorkshopId"));
		suite.addTest(new WorkshopAdhesionManagerTesting("doRetrieveByAdhesionId"));
		suite.addTest(new WorkshopAdhesionManagerTesting("doRetrieveAll"));
		
		return suite;
	}
	
	private WorkshopAdhesionManager manager;
}
