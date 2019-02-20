package it.carcheck.testing;

import java.util.ArrayList;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.ProvinceManager;
import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.RegionBean;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProvinceManagerTesting extends TestCase {

	public ProvinceManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = new ProvinceManager();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void getProvinceByKey() throws Exception {
		ProvinceBean bean = manager.getProvinceByKey("code");
		assertNotNull(bean);
	}
	
	public void getProvinceByName() throws Exception {
		ProvinceBean bean = manager.getProvinceByName("name");
		assertNotNull(bean);
	}
	
	public void getProvinceByCityName() throws Exception {
		ProvinceBean bean = manager.getProvinceByCityName("name");
		assertNotNull(bean);
	}
	
	public void getProvinceByCity() throws Exception {
		ProvinceBean bean = manager.getProvinceByCity(new CityBean());
		assertNotNull(bean);
	}
	
	public void getProvincesFromRegion() throws Exception {
		ArrayList<ProvinceBean> beans = manager.getProvincesFromRegion(new RegionBean());
		assertNotNull(beans);
	}
	
	public void getProvincesByRegionCode() throws Exception {
		ArrayList<ProvinceBean> beans = manager.getProvincesByRegionCode(1);
		assertNotNull(beans);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new ProvinceManagerTesting("getProvinceByKey"));
		suite.addTest(new ProvinceManagerTesting("getProvinceByName"));
		suite.addTest(new ProvinceManagerTesting("getProvinceByCityName"));
		suite.addTest(new ProvinceManagerTesting("getProvinceByCity"));
		suite.addTest(new ProvinceManagerTesting("getProvincesFromRegion"));
		suite.addTest(new ProvinceManagerTesting("getProvincesByRegionCode"));
		
		return suite;
	}
	
	private ProvinceManager manager;
}
