package it.carcheck.model;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.bean.enums.RequestStatus;
import it.carcheck.model.interfaces.IAdhesionRequest;
import it.carcheck.model.interfaces.IWorkshop;
import it.carcheck.utility.EmailSender;
import it.carcheck.utility.PasswordHasher;

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
	public void doSave(AdhesionRequestBean element) {
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
		element.setStatus(RequestStatus.PROCESSING);
		
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
	public void doSetState(AdhesionRequestBean adhesionRequest, int status) {
		adhesionRequest.setStatus(status);
		try {
			this.database.update(adhesionRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Collection<AdhesionRequestBean> doRetrieveByAdminId(int id) {
		try {
			return this.doFind("SELECT * FROM adhesionrequest WHERE admin = ?", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public AdhesionRequestBean doRetrieveByCode(int code) {
		try {
			return this.doFind("SELECT * FROM adhesionrequest WHERE id = ?", code).get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public void doApproveRequest(AdhesionRequestBean request) {
		request.setStatus(RequestStatus.APPROVED);
		
		IWorkshop workshopManager = WorkshopManager.getInstance();
		
		String password = UUID.randomUUID().toString();
		String hashedPassword = PasswordHasher.Encrypt(password);
		
		WorkshopBean workshopBean = workshopManager.doRetrieveWorkshopById(request.getWorkshopCode());
		workshopBean.setPassword(hashedPassword);
		try {
			workshopManager.doSave(workshopBean);
		} catch (SQLException e) {
			return;
		}
		
		this.doSave(request);
		
		EmailSender.GetInstance().SendEmail("Richiesta approvata",
				"Benvenuto in CarChack!\nLa tua richiesta è stata approvata, accedi con la seguente password:\n\n" + password + "\n\nAl primo login ti verrà chiesto di cambiarla", 
				workshopBean.getEmail());
	}



	@Override
	public void doRejectRequest(AdhesionRequestBean request, String cause) {
		request.setStatus(RequestStatus.REFUSED);
		this.doSave(request);
		
		WorkshopBean workshopBean = WorkshopManager.getInstance().doRetrieveWorkshopById(request.getWorkshopCode());
		
		EmailSender.GetInstance().SendEmail("Richiesta respinta",
				"Gentile cliente,\nLa tua richiesta NON è stata approvata. Il motivo del rifiuto è il seguente:\n\n" + cause + "\n\nBuona giornata, il team di CarCheck.", 
				workshopBean.getEmail());
	}



	@Override
	public void doSetRequestAppointment(AdhesionRequestBean request, Date date, Time time) {
		request.setStatus(RequestStatus.APPOINTMENT);
		request.setMeeetingHour(time);
		request.setMeetingDate(date);
		
		WorkshopBean workshopBean = WorkshopManager.getInstance().doRetrieveWorkshopById(request.getWorkshopCode());
		
		EmailSender.GetInstance().SendEmail("Appuntamento",
				"Gentile cliente,\nLa contattiamo per informarle che il " + date.toString() + " alle ore " + time.toString().substring(0, 6) + " un tecnico si recherà nella sua officina per controllare se rispetta i nostri standard.\n\n Cordiali saluti, CarCheck", 
				workshopBean.getEmail());
		
		this.doSave(request);
	}

	private CarcheckDatabase database;
	private static AdhesionRequestManager instance;
}
