package it.carcheck.testing;

import java.util.ArrayList;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.CityManager;
import it.carcheck.model.RegionManager;
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
		CityBean city = manager.getCityByKey("063065");
		assertNotNull(city);
		assertEquals(city.getIstat(), "063065");
	}
	
	public void getCityByName() throws Exception {
		CityBean city = manager.getCityByName("Roccarainola");
		assertNotNull(city);
		assertEquals(city.getName(), "Roccarainola");
	}
	
	public void getCityFromNameAndCap() throws Exception {
		CityBean city = manager.getCityFromNameAndCap("Pompei", "80045");
		assertNotNull(city);
		assertEquals(city.getName(),"Pompei");
		assertEquals(city.getCap(), "80045");
	}
	
	public void getCitiesFromCap() throws Exception {
		ArrayList<CityBean> cities = manager.getCitiesFromCap("00100");
		assertNotNull(cities);
		for(CityBean city : cities)
			assertEquals(city.getCap(), "00100");
	}
	
	public void getCitiesFromProvince() throws Exception {
		ProvinceBean bean = new ProvinceBean();
		bean.setProvinceCode("NA");
		
		ArrayList<CityBean> cities = manager.getCitiesFromProvince(bean);
		assertNotNull(cities);
		
		for(CityBean city: cities)
			assertEquals(city.getProvince(), "NA");
	}
	
	public void getCitiesFromRegion() throws Exception {
		RegionBean region = new RegionManager().getRegionByKey(1);
		ArrayList<CityBean> cities = manager.getCitiesFromRegion(region);
		assertNotNull(cities);
	}
	
	public void getCitiesFromProvinceCode() throws Exception {
		ArrayList<CityBean> cities = manager.getCitiesFromProvinceCode("MI");
		assertNotNull(cities);
		
		for(CityBean city: cities)
			assertEquals(city.getProvince(), "MI");
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
