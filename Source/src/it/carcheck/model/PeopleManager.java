package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.PeopleBean;
import it.carcheck.model.interfaces.IPeople;

public class PeopleManager implements IPeople {

	public static PeopleManager getInstance() {
		if(instance == null)
			instance = new PeopleManager();
		
		return instance;
	}
	
	private PeopleManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public void doSave(PeopleBean element) throws SQLException {
		try {
			this.database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(PeopleBean element) throws SQLException {
		try {
			this.database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInsert(PeopleBean element) throws SQLException {
		try {
			this.database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<PeopleBean> doFind(String query, Object... args) throws SQLException {
		try {
			return this.database.find(PeopleBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public PeopleBean doRetrieveByFiscalCode(String code) {
		try {
			return this.doFind("SELECT * FROM people WHERE fiscalCode = ?", code).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private CarcheckDatabase database;
	private static PeopleManager instance;
}
