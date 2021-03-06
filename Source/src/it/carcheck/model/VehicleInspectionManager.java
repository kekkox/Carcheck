package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;
import it.carcheck.model.interfaces.IVehicleInspection;

public class VehicleInspectionManager implements IVehicleInspection {

	public static VehicleInspectionManager getInstance() {
		if(instance == null)
			instance = new VehicleInspectionManager();
		
		return instance;
	}
	
	private VehicleInspectionManager() {
		this.database = CarcheckDatabase.getInstance();
	}

	@Override
	public Collection<VehicleInspectionBean> doRetrieveByLicensePlate(WorkshopBean workshop, String licensePlate) throws SQLException {
		return doFind("SELECT * FROM vehicleinspection WHERE vehicle = ? AND workshop = ?", licensePlate, workshop.getId());	
	}
	
	@Override
	public Collection<VehicleInspectionBean> doRetrieveByLicensePlateLike(WorkshopBean workshop, String licensePlate) {
		try {
			return doFind("SELECT * FROM vehicleinspection WHERE vehicle LIKE '" + licensePlate + "%' AND workshop = ?", workshop.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public VehicleInspectionBean doRetrieveByKey(WorkshopBean workshop, int inspectionCode) throws SQLException {
		return doFind("SELECT * FROM vehicleinspection WHERE id = ? AND workshop = ?", inspectionCode, workshop.getId()).get(0);	
	}

	@Override
	public Collection<VehicleInspectionBean> doRetrieveByWorkshop(WorkshopBean workshop) throws SQLException {
		return doFind("SELECT * FROM vehicleinspection WHERE workshop = ?", workshop.getId());
	}
	
	@Override
	public Collection<VehicleInspectionBean> doRetrieveExpiringInspection(WorkshopBean workshop) throws SQLException {
		return doFind("SELECT * FROM vehicleinspection WHERE workshop = ? AND (expirationDate BETWEEN NOW() AND DATE_ADD(NOW(), INTERVAL 30 DAY))", workshop.getId());
	}
	@Override
	public  Collection<VehicleInspectionBean> doRetriveTotalVehicle(WorkshopBean workshop) throws SQLException {
		return doFind("SELECT DISTINCT * FROM vehicleinspection WHERE workshop = ? GROUP BY vehicle", workshop.getId());
	}
	
	@Override
	public Collection<VehicleInspectionBean> doRetrieveByWorkshopIdAndLicensePlate(int id, String license) {
		try {
			return doFind("SELECT * FROM vehicleinspection WHERE workshop = ? AND vehicle LIKE '" + license + "%'", id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void doSave(VehicleInspectionBean element) throws SQLException {
		try {
			database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(VehicleInspectionBean element) throws SQLException {
		try {
			database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void doInsert(VehicleInspectionBean element) throws SQLException {
		try {
			database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<VehicleInspectionBean> doFind(String query, Object...args) throws SQLException {
		try {
			return database.find(VehicleInspectionBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private CarcheckDatabase database;
	private static VehicleInspectionManager instance;
}
