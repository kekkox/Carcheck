package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.VehicleComplaintBean;
import it.carcheck.model.interfaces.IVehicleComplaint;

public class VehicleComplaintManager implements IVehicleComplaint {

	public static VehicleComplaintManager getInstance() {
		if(instance == null)
			instance = new VehicleComplaintManager();
		
		return instance;
	}
	
	private VehicleComplaintManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public void doSave(VehicleComplaintBean element) throws SQLException {
		try {
			this.database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(VehicleComplaintBean element) throws SQLException {
		try {
			this.database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void doInsert(VehicleComplaintBean element) throws SQLException {
		try {
			this.database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public ArrayList<VehicleComplaintBean> doFind(String query, Object... args) throws SQLException {
		try {
			return this.database.find(VehicleComplaintBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}



	@Override
	public VehicleComplaintBean doGetVehicleComplaintFromLicensePlate(String licensePlate) {
		try {
			ArrayList<VehicleComplaintBean> vehicles = this.doFind("SELECT * FROM vehiclecomplaint WHERE licensePlate = ?", licensePlate);
			if(vehicles != null && vehicles.size() > 0)
				return vehicles.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private CarcheckDatabase database; 
	private static VehicleComplaintManager instance;
}
