package it.carcheck.testing;

import java.util.Collection;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.bean.InsuranceBean;
import it.carcheck.model.bean.PeopleBean;
import it.carcheck.model.bean.PossessionFeeBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class VehicleManagerTesting extends TestCase {

	public VehicleManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = VehicleManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void doRetriveVehicle() throws Exception {
		VehicleBean bean = this.manager.doRetriveVehicle("NAN70642");
		assertNotNull(bean);
		assertEquals(bean.getLicensePlate(), "NAN70642");
	}
	
	public void doRetrieveLastPossessionFee() throws Exception {
		VehicleBean vehicleBean = this.manager.doRetriveVehicle("NAN70642");
		PossessionFeeBean bean = this.manager.doRetrieveLastPossessionFee(vehicleBean);
		assertNotNull(bean);
	}
	
	public void doRetrieveLastInsurance() throws Exception {
		VehicleBean vehicleBean = this.manager.doRetriveVehicle("NAN70642");
		InsuranceBean bean = this.manager.doRetrieveLastInsurance(vehicleBean);
		assertNotNull(bean);
	}
	
	public void doRetrieveOwners() throws Exception {
		VehicleBean vehicleBean = this.manager.doRetriveVehicle("NAN70642");
		Collection<PeopleBean> beans = this.manager.doRetrieveOwners(vehicleBean);
		assertNotNull(beans);
	}
	
	public void doRetrieveLastVehicleInspection() throws Exception {
		VehicleBean vehicleBean = this.manager.doRetriveVehicle("NAN70642");
		VehicleInspectionBean bean = this.manager.doRetrieveLastVehicleInspection(vehicleBean);
		assertNotNull(bean);
	}
	
	public void doRetrieveAll() throws Exception {
		Collection<VehicleBean> beans = this.manager.doRetrieveAll();
		assertNotNull(beans);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new VehicleManagerTesting("doRetriveVehicle"));
		suite.addTest(new VehicleManagerTesting("doRetrieveLastPossessionFee"));
		suite.addTest(new VehicleManagerTesting("doRetrieveLastInsurance"));
		suite.addTest(new VehicleManagerTesting("doRetrieveOwners"));
		suite.addTest(new VehicleManagerTesting("doRetrieveLastVehicleInspection"));
		suite.addTest(new VehicleManagerTesting("doRetrieveAll"));
		
		return suite;
	}
	
	private VehicleManager manager;
}
