package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.carcheck.database.CarcheckDatabase;
import it.carcheck.model.bean.VehicleComplaintHistoryBean;
import it.carcheck.model.interfaces.IVehicleComplaintHistory;

public class VehicleComplaintHistoryManager implements IVehicleComplaintHistory {

	public static VehicleComplaintHistoryManager getInstance() {
		if(instance == null)
			instance = new VehicleComplaintHistoryManager();
		
		return instance;
	}
	
	private VehicleComplaintHistoryManager() {
		this.database = CarcheckDatabase.getInstance();
	}
	
	@Override
	public void doSave(VehicleComplaintHistoryBean element) throws SQLException {
		try {
			this.database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doDelete(VehicleComplaintHistoryBean element) throws SQLException {
		try {
			this.database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public void doInsert(VehicleComplaintHistoryBean element) throws SQLException {
		try {
			this.database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Override
	public ArrayList<VehicleComplaintHistoryBean> doFind(String query, Object... args) throws SQLException {
		try {
			return this.database.find(VehicleComplaintHistoryBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}



	@Override
	public Collection<VehicleComplaintHistoryBean> doGetVehicleComplaintsFromLicensePlate(String licensePlate) {
		try {
			return this.doFind("SELECT * FROM vehiclecomplaint_history WHERE licensePlate = ?", licensePlate);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private CarcheckDatabase database; 
	private static VehicleComplaintHistoryManager instance;
}
