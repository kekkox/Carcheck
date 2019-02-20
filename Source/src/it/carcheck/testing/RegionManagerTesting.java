package it.carcheck.testing;

import java.util.ArrayList;

import it.carcheck.database.CarcheckConfiguration;
import it.carcheck.database.CarcheckDatabase;
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
	}
	
	public void getRegionFromCityName() throws Exception {
		RegionBean bean = this.regionManager.getRegionFromCityName("name");
		assertNotNull(bean);
	}
	
	public void getRegionFromCity() throws Exception {
		RegionBean bean = this.regionManager.getRegionFromCity(new CityBean());
		assertNotNull(bean);
	}
	
	public void getRegionFromProvince() throws Exception {
		RegionBean bean = this.regionManager.getRegionFromProvince(new ProvinceBean());
		assertNotNull(bean);
	}
	
	public void getAllRegions() throws Exception {
		ArrayList<RegionBean> beans = this.regionManager.getAllRegions();
		assertNotNull(beans);
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
