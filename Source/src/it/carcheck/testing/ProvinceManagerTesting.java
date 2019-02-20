package it.carcheck.testing;

import java.util.ArrayList;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.CityManager;
import it.carcheck.model.ProvinceManager;
import it.carcheck.model.RegionManager;
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
		ProvinceBean bean = manager.getProvinceByKey("MI");
		assertNotNull(bean);
		assertEquals(bean.getProvinceCode(), "MI");
	}
	
	public void getProvinceByName() throws Exception {
		ProvinceBean bean = manager.getProvinceByName("Napoli");
		assertNotNull(bean);
		assertEquals(bean.getName(), "Napoli");
	}
	
	public void getProvinceByCityName() throws Exception {
		ProvinceBean bean = manager.getProvinceByCityName("Pompei");
		assertNotNull(bean);
	}
	
	public void getProvinceByCity() throws Exception {
		CityBean cityBean = new CityManager().getCityByKey("063065");
		ProvinceBean bean = manager.getProvinceByCity(cityBean);
		assertNotNull(bean);
		assertEquals(cityBean.getProvince(), bean.getProvinceCode());
	}
	
	public void getProvincesFromRegion() throws Exception {
		RegionBean regionBean = new RegionManager().getRegionFromCityName("Napoli");
		ArrayList<ProvinceBean> beans = manager.getProvincesFromRegion(regionBean);
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
