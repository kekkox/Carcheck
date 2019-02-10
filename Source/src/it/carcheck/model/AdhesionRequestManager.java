package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.interfaces.IAdhesionRequest;

public class AdhesionRequestManager implements IAdhesionRequest {

	public static AdhesionRequestManager getInstance() {
		if(instance == null)
			instance = new AdhesionRequestManager();
		
		return instance;
	}
	
	private AdhesionRequestManager() {
		this.database = CarcheckDatabase.getInstance();
	}

	@Override
	public void doSave(AdhesionRequestBean element) throws SQLException {
		try {
			database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(AdhesionRequestBean element) throws SQLException {
		try {
			database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInsert(AdhesionRequestBean element) throws SQLException {
		AdminManager adminManager = AdminManager.getInstance();
		AdminBean adminBean = adminManager.doFind("SELECT * FROM admin ORDER BY RAND() LIMIT 1").get(0);
		
		element.setAdminCode(adminBean.getId());
		
		try {
			database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<AdhesionRequestBean> doFind(String query, Object...args) throws SQLException {
		try {
			return database.find(AdhesionRequestBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void doSetState(AdhesionRequestBean adhesionRequest, int status) throws SQLException {
		adhesionRequest.setStatus(status);
	}

	private CarcheckDatabase database;
	private static AdhesionRequestManager instance;
}
