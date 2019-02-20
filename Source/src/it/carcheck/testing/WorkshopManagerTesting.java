package it.carcheck.testing;

import java.util.ArrayList;
import java.util.Collection;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.AdhesionRequestManager;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.WorkshopManager;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class WorkshopManagerTesting extends TestCase {

	public WorkshopManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = WorkshopManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void doLogin() throws Exception {
		WorkshopBean bean = this.manager.doLogin("mail", "pass");
		assertNotNull(bean);
	}
	
	public void doChangePassword() throws Exception {
		ArrayList<WorkshopBean> workshops = manager.doFind("select * from workshop");
		assertNotNull(workshops);
		
		WorkshopBean workshop = workshops.get(0);
		assertNotNull(workshop);
		
		manager.doChangePassword(workshop, "newPass");
		
		ArrayList<WorkshopBean> finalWorkshops = manager.doFind("select * from workshop where email = ?", workshop.getEmail());
		assertNotNull(finalWorkshops);
		
		WorkshopBean finalWorkshop = finalWorkshops.get(0);
		assertNotSame(workshop.getPassword(), finalWorkshop.getPassword());
	}
	
	public void doRetrieveByEmail() throws Exception {
		WorkshopBean bean = this.manager.doRetrieveByEmail("mail");
		assertNotNull(bean);
	}
	
	public void doSignUp() throws Exception {
		WorkshopBean ws = new WorkshopBean();
		this.manager.doSignUp(ws);
		
		WorkshopBean workshop = this.manager.doFind("select * from workshop where email = ?", ws.getEmail()).get(0);
		assertNotNull(workshop);
	}
	
	public void doSendAdhesionRequest() throws Exception {
		WorkshopBean ws = new WorkshopBean();
		this.manager.doSendAdhesionRequest(ws);
		
		AdhesionRequestManager adhesionManager = AdhesionRequestManager.getInstance();
		AdhesionRequestBean req = adhesionManager.doFind("select * from adhesionrequest where workshop = ?", ws.getId()).get(0);
		
		assertNotNull(req);
	}
	
	public void doAddVehicleInspection() throws Exception {
		WorkshopBean ws = new WorkshopBean();
		this.manager.doAddVehicleInspection(ws, new VehicleInspectionBean(), new VehicleBean());
		
		VehicleInspectionManager vehicleInspection = VehicleInspectionManager.getInstance();
		VehicleInspectionBean inspection = vehicleInspection.doFind("select * from vehicleinspection where workshop = ?", ws.getId()).get(0);
		
		assertNotNull(inspection);
	}
	
	public void doRetrieveAll() throws Exception {
		Collection<WorkshopBean> workshops = this.manager.doRetrieveAll();
		assertNotNull(workshops);
	}
	
	public void doRetrieveWorkshopById() throws Exception {
		WorkshopBean workshop = this.manager.doRetrieveWorkshopById(1);
		assertNotNull(workshop);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new WorkshopManagerTesting("doLogin"));
		suite.addTest(new WorkshopManagerTesting("doChangePassword"));
		suite.addTest(new WorkshopManagerTesting("doRetrieveByEmail"));
		suite.addTest(new WorkshopManagerTesting("doSignUp"));
		suite.addTest(new WorkshopManagerTesting("doSendAdhesionRequest"));
		suite.addTest(new WorkshopManagerTesting("doAddVehicleInspection"));
		suite.addTest(new WorkshopManagerTesting("doRetrieveAll"));
		suite.addTest(new WorkshopManagerTesting("doRetrieveWorkshopById"));
		
		return suite;
	}
	
	WorkshopManager manager;
}
