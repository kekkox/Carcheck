package it.carcheck.testing;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.AdhesionRequestManager;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.enums.RequestStatus;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AdhesionRequestManagerTesting extends TestCase {

	public AdhesionRequestManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = AdhesionRequestManager.getInstance();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void doSetState() throws Exception {
		ArrayList<AdhesionRequestBean> beans = manager.doFind("select * from adhesionrequest");
		assertNotNull(beans);
		
		AdhesionRequestBean bean = beans.get(0);
		manager.doSetState(bean, 1);
		
		ArrayList<AdhesionRequestBean> resultBeans = manager.doFind("select * from adhesionrequest");
		assertNotNull(beans);
		
		AdhesionRequestBean resultBean = resultBeans.get(0);
		assertEquals(1, resultBean.getStatus());
	}
	
	public void doRetrieveByAdminId() throws Exception {
		Collection<AdhesionRequestBean> beans = manager.doRetrieveByAdminId(1);
		assertNotNull(beans);
	}
	
	public void doRetrieveByCode() throws Exception {
		AdhesionRequestBean bean = manager.doRetrieveByCode(13);
		assertNotNull(bean);
	}
	
	public void doApproveRequest() throws Exception {
		ArrayList<AdhesionRequestBean> beans = manager.doFind("select * from adhesionrequest");
		
		AdhesionRequestBean bean = beans.get(0);
		manager.doApproveRequest(bean);
		
		ArrayList<AdhesionRequestBean> finalBeans = manager.doFind("select * from adhesionrequest");
		AdhesionRequestBean finalBean = finalBeans.get(0);
		
		assertEquals(RequestStatus.APPROVED, finalBean.getStatus());
	}
	
	public void doRejectRequest() throws Exception {
		ArrayList<AdhesionRequestBean> beans = manager.doFind("select * from adhesionrequest");
		
		AdhesionRequestBean bean = beans.get(0);
		manager.doRejectRequest(bean, "Test");
		
		ArrayList<AdhesionRequestBean> finalBeans = manager.doFind("select * from adhesionrequest");
		AdhesionRequestBean finalBean = finalBeans.get(0);
		
		assertEquals(RequestStatus.REFUSED, finalBean.getStatus());
	}
	
	public void doSetRequestAppointment() throws Exception {
		ArrayList<AdhesionRequestBean> beans = manager.doFind("select * from adhesionrequest");
		
		AdhesionRequestBean bean = beans.get(0);
		manager.doSetRequestAppointment(bean, Date.valueOf("2000-01-01"), Time.valueOf("01:01:00"));
		
		ArrayList<AdhesionRequestBean> finalBeans = manager.doFind("select * from adhesionrequest");
		AdhesionRequestBean finalBean = finalBeans.get(0);
		
		assertEquals(RequestStatus.APPOINTMENT, finalBean.getStatus());
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new AdhesionRequestManagerTesting("doSetState"));
		suite.addTest(new AdhesionRequestManagerTesting("doRetrieveByAdminId"));
		suite.addTest(new AdhesionRequestManagerTesting("doRetrieveByCode"));
		suite.addTest(new AdhesionRequestManagerTesting("doApproveRequest"));
		suite.addTest(new AdhesionRequestManagerTesting("doRejectRequest"));
		suite.addTest(new AdhesionRequestManagerTesting("doSetRequestAppointment"));
		
		return suite;
	}
	
	private AdhesionRequestManager manager;
}
