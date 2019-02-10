package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.RegionBean;
import it.carcheck.model.interfaces.ICity;

public class CityManager implements ICity {
	
	public CityManager() {
		this.database = CarcheckDatabase.getInstance();
	}

	@Override
	public void doSave(CityBean element) throws SQLException {
		try {
			this.database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(CityBean element) throws SQLException {
		try {
			this.database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInsert(CityBean element) throws SQLException {
		try {
			this.database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<CityBean> doFind(String query, Object...args) throws SQLException {
		try {
			return this.database.find(CityBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CityBean getCityByName(String name) {
		try {
			return this.database.find(CityBean.class, "SELECT * from city WHERE name = ?", name).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CityBean getCityFromNameAndCap(String name, String cap) {
		try {
			return this.database.find(CityBean.class, "SELECT * from city WHERE name = ? AND cap = ?", name, cap).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<CityBean> getCitiesFromCap(String cap) {
		try {
			return this.database.find(CityBean.class, "SELECT * from city WHERE cap = ?", cap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<CityBean> getCitiesFromProvince(ProvinceBean province) {
		try {
			return this.database.find(CityBean.class, "SELECT * from city WHERE province = ?", province.getProvinceCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<CityBean> getCitiesFromRegion(RegionBean region) {
		ArrayList<CityBean> cities = new ArrayList<>();
		
		try {
			ArrayList<ProvinceBean> provinces = this.database.find(ProvinceBean.class, "SELECT * from province WHERE region = ?", region.getId());
			
			for(int i = 0; i < provinces.size(); i++) {
				ArrayList<CityBean> db_cities = this.database.find(CityBean.class, "SELECT * from city where province = ?", provinces.get(i).getProvinceCode());
				cities.addAll(db_cities);
			}
			
			return cities;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<CityBean> getCitiesFromProvinceCode(String code) {
		try {
			return this.database.find(CityBean.class, "SELECT * from city WHERE province = ?", code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private CarcheckDatabase database;
}
