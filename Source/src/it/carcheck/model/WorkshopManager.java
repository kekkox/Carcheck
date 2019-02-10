package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

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
			database.create(element);
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

	private CarcheckDatabase database;
	private static WorkshopManager instance;
	private static final String MAIL_BODY = "Grazie %s per la richiesta di adesione ai servizi di Carcheck da parte dell'officina %s\n La sua richiesta verrà esaminatada un'operatore e controllata nel più breve tempo possibile";
}
