package it.carcheck.testing;

import java.util.Collection;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.VehicleComplaintHistoryManager;
import it.carcheck.model.bean.VehicleComplaintHistoryBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class VehicleComplaintHistoryManagerTesting extends TestCase {

	public VehicleComplaintHistoryManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = VehicleComplaintHistoryManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void doGetVehicleComplaintsFromLicensePlate() throws Exception {
		Collection<VehicleComplaintHistoryBean> beans = manager.doGetVehicleComplaintsFromLicensePlate("NAN70642");
		assertNull(beans);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new VehicleComplaintHistoryManagerTesting("doGetVehicleComplaintsFromLicensePlate"));
		
		return suite;
	}
	
	private VehicleComplaintHistoryManager manager;
}
