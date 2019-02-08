package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.CityBean;
import it.carcheck.model.interfaces.ILocation;

public class LocationManager implements ILocation {

	public static LocationManager getInstance() {
		if(instance == null)
			instance = new LocationManager();
		
		return instance;
	}
	
	private LocationManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public void doSave(CityBean element) throws SQLException {
		try {
			database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(CityBean element) throws SQLException {
		try {
			database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInsert(CityBean element) throws SQLException {
		try {
			database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<CityBean> doFind(String query) throws SQLException {
		try {
			return database.find(query, CityBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<CityBean> doRetrieveByCap(String cap) {
		try {
			return database.find("SELECT * FROM city WHERE cap = " + cap, CityBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ArrayList<CityBean> doRetrieveByName(String name) {
		try {
			return database.find("SELECT * FROM city WHERE name = " + name, CityBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public CityBean doRetrieveByName(String name, String cap) {
		try {
			return database.find("SELECT * FROM city WHERE name = " + name + " AND cap = " + cap, CityBean.class).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private CarcheckDatabase database;
	private static LocationManager instance;
}
