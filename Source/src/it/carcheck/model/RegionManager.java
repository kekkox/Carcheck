package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.RegionBean;
import it.carcheck.model.interfaces.IRegion;

public class RegionManager implements IRegion {

	public RegionManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public void doSave(RegionBean element) throws SQLException {
		try {
			this.database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(RegionBean element) throws SQLException {
		try {
			this.database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInsert(RegionBean element) throws SQLException {
		try {
			this.database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<RegionBean> doFind(String query, Object...args) throws SQLException {
		try {
			return this.database.find(RegionBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RegionBean getRegionFromCityName(String name) {
		try {
			CityBean city = this.database.find(CityBean.class, "SELECT * from city WHERE name = ?", name).get(0);
			ProvinceBean province = this.database.find(ProvinceBean.class, "SELECT * from province WHERE provinceCode = ?", city.getProvince()).get(0);
			
			return this.database.find(RegionBean.class, "SELECT * from region WHERE id = ?", province.getRegion()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RegionBean getRegionFromCity(CityBean city) {
		return getRegionFromCityName(city.getName());
	}

	@Override
	public RegionBean getRegionFromProvince(ProvinceBean province) {
		try {
			return this.database.find(RegionBean.class, "SELECT * from region WHERE id = ?", province.getRegion()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<RegionBean> getAllRegions() {
		try {
			return this.doFind("SELECT * from region ORDER BY name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private CarcheckDatabase database;
}
