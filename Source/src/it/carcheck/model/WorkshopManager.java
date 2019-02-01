package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.fastcrud.Database;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.RequestStatus;
import it.carcheck.model.interfaces.IWorkshop;
import it.carcheck.utility.PasswordHasher;

public class WorkshopManager implements IWorkshop {

	
	public WorkshopManager() {
		this.database = Database.GetInstance();
	}

	@Override
	public WorkshopBean doLogin(String email, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		WorkshopBean workshop = doFind("SELECT * FROM workshop WHERE email = " + email + " AND password = " + cryptedPassword).get(0);
		return workshop;
	}

	@Override
	public void doChangePassword(WorkshopBean user, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		user.setPassword(cryptedPassword);
		database.Update(user);
		
	}

	@Override
	public void doSave(WorkshopBean element) throws SQLException {
		database.Update(element);		
	}

	@Override
	public void doDelete(WorkshopBean element) throws SQLException {
		database.Delete(element);
		
	}

	@Override
	public void doInsert(WorkshopBean element) throws SQLException {
		database.Insert(element);
	}

	@Override
	public ArrayList<WorkshopBean> doFind(String query) throws SQLException {
		return database.Find(new WorkshopBean(), query);
	}

	@Override
	public void doSignUp(WorkshopBean workshop) throws SQLException {	
		doInsert(workshop);
		doSendAdhesionRequest(workshop);
		
	}

	@Override
	public void doSendAdhesionRequest(WorkshopBean workshop) throws SQLException {
		AdhesionRequestBean adhesionRequestBean = new AdhesionRequestBean();
		adhesionRequestBean.setWorkshopCode(workshop.getId());
		adhesionRequestBean.setStatus(RequestStatus.PROCESSING);
		AdhesionRequestManager adhesionRequestManager = new AdhesionRequestManager();
		adhesionRequestManager.doInsert(adhesionRequestBean);	
		/*
		 * TODO must implement email sender
		 */
	}
	
	@Override
	public void doAddVehicleInspection(WorkshopBean workshop,VehicleInspectionBean vehicleInspection,VehicleBean vehicle) throws SQLException{
		vehicleInspection.setWorkShop(workshop.getId());
		vehicleInspection.setVehicle(vehicle.getLicensePlate());
		VehicleInspectionManager vehicleInspectionManager = new VehicleInspectionManager();
		vehicleInspectionManager.doInsert(vehicleInspection);	
	}


	
	private Database database;
}
