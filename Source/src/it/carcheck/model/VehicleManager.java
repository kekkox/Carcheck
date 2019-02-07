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

	public VehicleManager() {
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
	public ArrayList<VehicleBean> doFind(String query) throws SQLException {

		try {
			return database.find(query, VehicleBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public PossessionFeeBean doRetrieveLastPossessionFee(VehicleBean vehicle) throws SQLException {
		try {
			return database.find("SELECT * FROM possessionfee WHERE vehicle="
					+ vehicle.getLicensePlate() + " ORDER BY expirationDate ASC LIMIT 1", PossessionFeeBean.class).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public InsuranceBean doRetrieveLastInsurance(VehicleBean vehicle) throws SQLException {
		try {
			return database.find("SELECT * FROM insurance WHERE vehicle="
					+ vehicle.getLicensePlate() + " ORDER BY expirationDate ASC LIMIT 1", InsuranceBean.class).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Collection<PeopleBean> doRetrieveOwners(VehicleBean vehicle) throws SQLException {
		try {
			return database.find("SELECT People.* FROM people,owner WHERE Owner.vehicle= "
					+ vehicle.getLicensePlate() + "and people.fiscalCode=owner.people", PeopleBean.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public VehicleInspectionBean doRetrieveLastVehicleInspection(VehicleBean vehicle) throws SQLException {
		try {
			return database
					.find("SELECT * FROM vehicleInspection WHERE vehicle=" + vehicle.getLicensePlate()
							+ " ORDER BY expirationDate ASC LIMIT 1", VehicleInspectionBean.class).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private CarcheckDatabase database;

}
