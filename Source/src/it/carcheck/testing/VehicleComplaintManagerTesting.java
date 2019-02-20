package it.carcheck.testing;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.VehicleComplaintManager;
import it.carcheck.model.bean.VehicleComplaintBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class VehicleComplaintManagerTesting extends TestCase {

	public VehicleComplaintManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = VehicleComplaintManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void doGetVehicleComplaintFromLicensePlate() throws Exception {
		VehicleComplaintBean bean = manager.doGetVehicleComplaintFromLicensePlate("license");
		assertNotNull(bean);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new VehicleComplaintManagerTesting("doGetVehicleComplaintFromLicensePlate"));
		
		return suite;
	}
	
	private VehicleComplaintManager manager;
}
