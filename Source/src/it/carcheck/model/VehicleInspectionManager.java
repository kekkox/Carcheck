package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.carcheck.fastcrud.Database;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.interfaces.IVehicleInspection;

public class VehicleInspectionManager implements IVehicleInspection {

	
	
	public VehicleInspectionManager() {
		this.database = Database.GetInstance();
	}

	@Override
	public Collection<VehicleInspectionBean> doRetrieveByLicensePlate(WorkshopBean workshop, String licensePlate) throws SQLException {
		return doFind("SELECT * FROM vehicleinspection WHERE vehicle = " + licensePlate + " AND workshop = " + workshop.getId());	
	}

	@Override
	public Collection<VehicleInspectionBean> doRetrieveByWorkshop(WorkshopBean workshop) throws SQLException {
		return doFind("SELECT * FROM vehicleinspection WHERE workshop = " + workshop.getId());
	}

	@Override
	public void doSave(VehicleInspectionBean element) throws SQLException {
		database.Update(element);
	}

	@Override
	public void doDelete(VehicleInspectionBean element) throws SQLException {
		database.Delete(element);
		
	}

	@Override
	public void doInsert(VehicleInspectionBean element) throws SQLException {
		database.Insert(element);
		
	}

	@Override
	public ArrayList<VehicleInspectionBean> doFind(String query) throws SQLException {
		return database.Find(new WorkshopBean(), query);
	
	}

	private Database database;
	
}
