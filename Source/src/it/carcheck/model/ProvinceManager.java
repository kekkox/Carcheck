package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.RegionBean;
import it.carcheck.model.interfaces.IProvince;

public class ProvinceManager implements IProvince {

	public ProvinceManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public void doSave(ProvinceBean element) throws SQLException {
		try {
			this.database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(ProvinceBean element) throws SQLException {
		try {
			this.database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInsert(ProvinceBean element) throws SQLException {
		try {
			this.database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ProvinceBean> doFind(String query, Object...args) throws SQLException {
		try {
			return this.database.find(ProvinceBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProvinceBean getProvinceByName(String name) {
		try {
			return this.database.find(ProvinceBean.class, "SELECT * FROM province WHERE name = ?", name).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public ProvinceBean getProvinceByCityName(String name) {
		try {
			CityBean city = this.database.find(CityBean.class, "SELECT * FROM city WHERE name = ?", name).get(0);
			return this.database.find(ProvinceBean.class, "SELECT * FROM province WHERE provinceCode = ?", city.getProvince()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProvinceBean getProvinceByCity(CityBean city) {
		return getProvinceByCityName(city.getName());
	}

	@Override
	public ArrayList<ProvinceBean> getProvincesFromRegion(RegionBean region) {
		try {
			return this.database.find(ProvinceBean.class, "SELECT * from province WHERE region = ?", region.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ArrayList<ProvinceBean> getProvincesByRegionCode(int regionCode) {
		try {
			return this.database.find(ProvinceBean.class, "SELECT * from province WHERE region = ?", regionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private CarcheckDatabase database;
}
