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

public class RegionManagerTesting extends TestCase {

	public RegionManagerTesting(String name) {
		super(name);
	}
	
	@Override
	protected void setUp() throws Exception {
		CarcheckDatabase.begin(new CarcheckConfiguration());
		this.regionManager = new RegionManager();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void getRegionByKey() throws Exception {
		RegionBean bean = this.regionManager.getRegionByKey(1);
		assertNotNull(bean);
		assertEquals(bean.getId(), 1);
	}
	
	public void getRegionFromCityName() throws Exception {
		RegionBean bean = this.regionManager.getRegionFromCityName("Napoli");
		assertNotNull(bean);
		assertEquals(bean.getName(), "Campania");
	}
	
	public void getRegionFromCity() throws Exception {
		CityBean city = new CityManager().getCityByName("Napoli");
		RegionBean bean = this.regionManager.getRegionFromCity(city);
		assertNotNull(bean);
		assertEquals(bean.getName(), "Campania");
	}
	
	public void getRegionFromProvince() throws Exception {
		ProvinceBean provinceBean = new ProvinceManager().getProvinceByKey("NA");
		RegionBean bean = this.regionManager.getRegionFromProvince(provinceBean);
		assertNotNull(bean);
	}
	
	public void getAllRegions() throws Exception {
		ArrayList<RegionBean> beans = this.regionManager.getAllRegions();
		assertNotNull(beans);
		assertEquals(beans.size(), 20);
	}
	
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(new RegionManagerTesting("getRegionByKey"));
		suite.addTest(new RegionManagerTesting("getRegionFromCityName"));
		suite.addTest(new RegionManagerTesting("getRegionFromCity"));
		suite.addTest(new RegionManagerTesting("getRegionFromProvince"));
		suite.addTest(new RegionManagerTesting("getAllRegions"));
		
		return suite;
	}
	
	private RegionManager regionManager;
}
