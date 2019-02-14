package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.RequestStatus;
import it.carcheck.model.interfaces.IWorkshop;
import it.carcheck.utility.EmailSender;
import it.carcheck.utility.PasswordHasher;

public class WorkshopManager implements IWorkshop {

	public static WorkshopManager getInstance() {
		if(instance == null)
			instance = new WorkshopManager();
		
		return instance;
	}
	
	private WorkshopManager() {
		this.database = CarcheckDatabase.getInstance();
	}

	@Override
	public WorkshopBean doLogin(String email, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		ArrayList<WorkshopBean> workshops = doFind("SELECT * FROM workshop WHERE email = ? AND password = ?", email, cryptedPassword);
		if(workshops != null && workshops.size() > 0)
			return workshops.get(0);
		
		return null;
	}

	@Override
	public void doChangePassword(WorkshopBean user, String password) throws SQLException {
		String cryptedPassword = PasswordHasher.Encrypt(password);
		user.setPassword(cryptedPassword);
		
		try {
			database.update(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public WorkshopBean doRetrieveByEmail(String email) throws SQLException {
		ArrayList<WorkshopBean> workshops = this.doFind("SELECT * FROM workshop WHERE email = ?", email);
		
		if(workshops == null || workshops.size() <= 0)
			return null;
		else
			return workshops.get(0);
		
	}

	@Override
	public void doSave(WorkshopBean element) throws SQLException {
		try {
			database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void doDelete(WorkshopBean element) throws SQLException {
		try {
			database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doInsert(WorkshopBean element) throws SQLException {
		try {
			int id = database.create(element);
			element.setId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<WorkshopBean> doFind(String query, Object...args) throws SQLException {
		try {
			return database.find(WorkshopBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
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
		
		AdhesionRequestManager adhesionRequestManager = AdhesionRequestManager.getInstance();
		adhesionRequestManager.doInsert(adhesionRequestBean);	
		
		EmailSender sender = EmailSender.GetInstance();
		sender.SendEmail("Carcheck - Richiesta adesione", String.format(MAIL_BODY, workshop.getOwner(), workshop.getBusinessName()), workshop.getEmail());
	}
	
	@Override
	public void doAddVehicleInspection(WorkshopBean workshop,VehicleInspectionBean vehicleInspection,VehicleBean vehicle) throws SQLException{
		vehicleInspection.setWorkShop(workshop.getId());
		vehicleInspection.setVehicle(vehicle.getLicensePlate());
		
		VehicleInspectionManager vehicleInspectionManager = VehicleInspectionManager.getInstance();
		vehicleInspectionManager.doInsert(vehicleInspection);	
	}
	
	@Override
	public Collection<WorkshopBean> doRetrieveAll() {
		try {
			return this.doFind("SELECT * FROM workshop");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private CarcheckDatabase database;
	private static WorkshopManager instance;
	private static final String MAIL_BODY = "Grazie %s per la richiesta di adesione ai servizi di Carcheck con la sua officina %s .\nLa sua richiesta verra' esaminatada un'operatore e controllata nel piu' breve tempo possibile";
}
