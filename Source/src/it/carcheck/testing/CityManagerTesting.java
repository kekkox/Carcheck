package it.carcheck.testing;

import java.util.ArrayList;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.CityManager;
import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.RegionBean;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CityManagerTesting extends TestCase {
	
	public CityManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.manager = new CityManager();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void getCityByKey() throws Exception {
		CityBean city = manager.getCityByKey("0000");
		assertNotNull(city);
	}
	
	public void getCityByName() throws Exception {
		CityBean city = manager.getCityByName("name");
		assertNotNull(city);
	}
	
	public void getCityFromNameAndCap() throws Exception {
		CityBean city = manager.getCityFromNameAndCap("name", "00000");
		assertNotNull(city);
	}
	
	public void getCitiesFromCap() throws Exception {
		ArrayList<CityBean> cities = manager.getCitiesFromCap("00000");
		assertNotNull(cities);
	}
	
	public void getCitiesFromProvince() throws Exception {
		ArrayList<CityBean> cities = manager.getCitiesFromProvince(new ProvinceBean());
		assertNotNull(cities);
	}
	
	public void getCitiesFromRegion() throws Exception {
		ArrayList<CityBean> cities = manager.getCitiesFromRegion(new RegionBean());
		assertNotNull(cities);
	}
	
	public void getCitiesFromProvinceCode() throws Exception {
		ArrayList<CityBean> cities = manager.getCitiesFromProvinceCode("00000");
		assertNotNull(cities);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new CityManagerTesting("getCityByKey"));
		suite.addTest(new CityManagerTesting("getCityByName"));
		suite.addTest(new CityManagerTesting("getCityFromNameAndCap"));
		suite.addTest(new CityManagerTesting("getCitiesFromCap"));
		suite.addTest(new CityManagerTesting("getCitiesFromProvince"));
		suite.addTest(new CityManagerTesting("getCitiesFromRegion"));
		suite.addTest(new CityManagerTesting("getCitiesFromProvinceCode"));
		
		return suite;
	}
	
	private CityManager manager;
}
