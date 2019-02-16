package it.carcheck.model;

import java.util.Collection;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.WorkshopAdhesionBean;
import it.carcheck.model.interfaces.IWorkshopAdhesion;

public class WorkshopAdhesionManager implements IWorkshopAdhesion{

	public static WorkshopAdhesionManager getInstance() {
		if(instance == null)
			instance = new WorkshopAdhesionManager();
		
		return instance;
	}
	
	private WorkshopAdhesionManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public WorkshopAdhesionBean doRetrieveByWorkshopId(int id, AdminBean admin) {
		final String query = "SELECT workshop.id AS workshopId, adhesionrequest.id AS adhesionRequestId, businessName, pIva, owner, description, telephone, email, status, address "
				+ "FROM workshop, adhesionrequest "
				+ "WHERE workshop.id = ? AND admin = ? AND workshop.id = adhesionrequest.workshop";
		try {
			return database.find(WorkshopAdhesionBean.class, query , id, admin.getId()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public WorkshopAdhesionBean doRetrieveByAdhesionId(int id, AdminBean admin) {
		final String query = "SELECT workshop.id AS workshopId, adhesionrequest.id AS adhesionRequestId, businessName, pIva, owner, description, telephone, email, status, address "
				+ "FROM workshop, adhesionrequest "
				+ "WHERE adhesionrequest.id = ? AND admin = ? AND workshop.id = adhesionrequest.workshop";
		try {
			return database.find(WorkshopAdhesionBean.class, query , id, admin.getId()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Collection<WorkshopAdhesionBean> doRetrieveAll(AdminBean admin) {
		final String query = "SELECT workshop.id AS workshopId, adhesionrequest.id AS adhesionRequestId, businessName, pIva, owner, description, telephone, email, status, address "
				+ "FROM workshop, adhesionrequest "
				+ "WHERE admin = ? AND workshop.id = adhesionrequest.workshop";
		try {
			return database.find(WorkshopAdhesionBean.class, query , admin.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private CarcheckDatabase database;
	private static WorkshopAdhesionManager instance;
}
