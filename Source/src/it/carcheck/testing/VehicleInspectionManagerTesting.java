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
		WorkshopBean workshopBean = new WorkshopBean();
		workshopBean.setId(11);
		
		Collection<VehicleInspectionBean> beans = manager.doRetrieveByLicensePlate(new WorkshopBean(), "NAN70642");
		assertNotNull(beans);
		for(VehicleInspectionBean inspection : beans)
			assertEquals(inspection.getVehicle(), "NAN70642");
	}
	
	public void doRetrieveByLicensePlateLike() throws Exception {
		WorkshopBean workshopBean = new WorkshopBean();
		workshopBean.setId(11);
		
		Collection<VehicleInspectionBean> beans = manager.doRetrieveByLicensePlate(new WorkshopBean(), "NAN");
		assertNotNull(beans);
		for(VehicleInspectionBean inspection : beans)
			assertTrue(inspection.getVehicle().contains("NAN"));
	}
	
	public void doRetrieveByKey() throws Exception {
		WorkshopBean workshopBean = new WorkshopBean();
		workshopBean.setId(11);
		
		VehicleInspectionBean bean = manager.doRetrieveByKey(workshopBean, 1);
		assertNotNull(bean);
		assertEquals(bean.getId(), 1);
	}
	
	public void doRetrieveByWorkshop() throws Exception {
		WorkshopBean workshopBean = new WorkshopBean();
		workshopBean.setId(11);
		
		Collection<VehicleInspectionBean> beans = manager.doRetrieveByWorkshop(workshopBean);
		assertNotNull(beans);
		for(VehicleInspectionBean inspection : beans)
			assertEquals(inspection.getWorkShop(), 11);
	}
	
	public void doRetrieveExpiringInspection() throws Exception {
		WorkshopBean workshopBean = new WorkshopBean();
		workshopBean.setId(11);
		
		Collection<VehicleInspectionBean> beans = manager.doRetrieveExpiringInspection(workshopBean);
		assertNotNull(beans);
	}
	
	public void doRetriveTotalVehicle() throws Exception {
		Collection<VehicleInspectionBean> beans = manager.doRetriveTotalVehicle(new WorkshopBean());
		assertNotNull(beans);
	}
	
	public void doRetrieveByWorkshopIdAndLicensePlate() throws Exception {
		Collection<VehicleInspectionBean> beans = manager.doRetrieveByWorkshopIdAndLicensePlate(11, "NAN70642");
		assertNotNull(beans);
		
		for(VehicleInspectionBean inspection : beans) {
			assertEquals(inspection.getWorkShop(), 11);
			assertEquals(inspection.getVehicle(), "NAN70642");
		}
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
