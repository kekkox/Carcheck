package it.carcheck.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.carcheck.model.bean.InsuranceBean;
import it.carcheck.model.bean.PeopleBean;
import it.carcheck.model.bean.PossessionFeeBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.interfaces.IVehicle;
import it.carcheck.database.CarcheckDatabase;

public class VehicleManager implements IVehicle {

	public static VehicleManager getInstance() {
		if(instance == null)
			instance = new VehicleManager();
		
		return instance;
	}
	
	private VehicleManager() {
		this.database = CarcheckDatabase.getInstance();
	}

	@Override
	public void doSave(VehicleBean element) throws SQLException {
		try {
			database.update(element);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doDelete(VehicleBean element) throws SQLException {
		try {
			database.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void doInsert(VehicleBean element) throws SQLException {
		try {
			database.create(element);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public ArrayList<VehicleBean> doFind(String query, Object...args) throws SQLException {

		try {
			return database.find(VehicleBean.class, query, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public VehicleBean doRetriveVehicle(String licensePlate)  throws SQLException {
		try {
			return (VehicleBean) database.find(VehicleBean.class, "SELECT * FROM vehicle WHERE licenseplate = ?", licensePlate).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PossessionFeeBean doRetrieveLastPossessionFee(VehicleBean vehicle) throws SQLException {
		try {
			return database.find(PossessionFeeBean.class, "SELECT * FROM possessionfee WHERE vehicle = ? ORDER BY expirationDate ASC LIMIT 1", vehicle.getLicensePlate()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public InsuranceBean doRetrieveLastInsurance(VehicleBean vehicle) throws SQLException {
		try {
			return database.find(InsuranceBean.class, "SELECT * FROM insurance WHERE vehicle = ? ORDER BY expirationDate ASC LIMIT 1", vehicle.getLicensePlate()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Collection<PeopleBean> doRetrieveOwners(VehicleBean vehicle) throws SQLException {
		try {
			return database.find(PeopleBean.class, "SELECT people.* FROM people,owner WHERE owner.vehicle = ? AND people.fiscalCode=owner.people", vehicle.getLicensePlate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public VehicleInspectionBean doRetrieveLastVehicleInspection(VehicleBean vehicle) throws SQLException {
		try {
			return database
					.find(VehicleInspectionBean.class, "SELECT * FROM vehicleInspection WHERE vehicle = ? ORDER BY expirationDate ASC LIMIT 1", vehicle.getLicensePlate()).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Collection<VehicleBean> doRetrieveAll() {
		try {
			return this.doFind("SELECT * FROM vehicle");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private CarcheckDatabase database;
	private static VehicleManager instance;
}
