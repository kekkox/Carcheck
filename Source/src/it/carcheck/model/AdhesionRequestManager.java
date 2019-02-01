package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.fastcrud.Configuration;
import it.carcheck.fastcrud.Database;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.interfaces.IAdhesionRequest;

public class AdhesionRequestManager implements IAdhesionRequest {

	public AdhesionRequestManager() {
		this.database = Database.Begin(new Configuration());
	}

	@Override
	public void doSave(AdhesionRequestBean element) throws SQLException {
		database.Update(element);
	}

	@Override
	public void doDelete(AdhesionRequestBean element) throws SQLException {
		database.Delete(element);
	}

	@Override
	public void doInsert(AdhesionRequestBean element) throws SQLException {
		AdminManager adminManager = new AdminManager();
		AdminBean adminBean = adminManager.doFind("SELECT * FROM admin ORDER BY RAND() LIMIT 1").get(0);
		element.setAdminCode(adminBean.getId());
		database.Insert(element);
	}

	@Override
	public ArrayList<AdhesionRequestBean> doFind(String query) throws SQLException {
		return database.Find(new AdhesionRequestBean(), query);
	}

	@Override
	public void doSetState(AdhesionRequestBean adhesionRequest, int status) throws SQLException {
		adhesionRequest.setStatus(status);
	}

	private Database database;
}
