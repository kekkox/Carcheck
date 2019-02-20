package it.carcheck.testing;

import java.util.Collection;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class VehicleInspectionManagerTesting extends TestCase {

	public VehicleInspectionManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = VehicleInspectionManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void doRetrieveByLicensePlate() throws Exception {
		Collection<VehicleInspectionBean> beans = manager.doRetrieveByLicensePlate(new WorkshopBean(), "license");
		assertNotNull(beans);
	}
	
	public void doRetrieveByLicensePlateLike() throws Exception {
		Collection<VehicleInspectionBean> beans = manager.doRetrieveByLicensePlateLike(new WorkshopBean(), "license");
		assertNotNull(beans);
	}
	
	public void doRetrieveByKey() throws Exception {
		VehicleInspectionBean bean = manager.doRetrieveByKey(new WorkshopBean(), 1);
		assertNotNull(bean);
	}
	
	public void doRetrieveByWorkshop() throws Exception {
		Collection<VehicleInspectionBean> beans = manager.doRetrieveByWorkshop(new WorkshopBean());
		assertNotNull(beans);
	}
	
	public void doRetrieveExpiringInspection() throws Exception {
		Collection<VehicleInspectionBean> beans = manager.doRetrieveExpiringInspection(new WorkshopBean());
		assertNotNull(beans);
	}
	
	public void doRetriveTotalVehicle() throws Exception {
		Collection<VehicleInspectionBean> beans = manager.doRetriveTotalVehicle(new WorkshopBean());
		assertNotNull(beans);
	}
	
	public void doRetrieveByWorkshopIdAndLicensePlate() throws Exception {
		Collection<VehicleInspectionBean> beans = manager.doRetrieveByWorkshopIdAndLicensePlate(1, "license");
		assertNotNull(beans);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new VehicleInspectionManagerTesting("doRetrieveByLicensePlate"));
		suite.addTest(new VehicleInspectionManagerTesting("doRetrieveByLicensePlateLike"));
		suite.addTest(new VehicleInspectionManagerTesting("doRetrieveByKey"));
		suite.addTest(new VehicleInspectionManagerTesting("doRetrieveByWorkshop"));
		suite.addTest(new VehicleInspectionManagerTesting("doRetrieveExpiringInspection"));
		suite.addTest(new VehicleInspectionManagerTesting("doRetriveTotalVehicle"));
		suite.addTest(new VehicleInspectionManagerTesting("doRetrieveByWorkshopIdAndLicensePlate"));
		
		return suite;
	}
	
	private VehicleInspectionManager manager;
}
