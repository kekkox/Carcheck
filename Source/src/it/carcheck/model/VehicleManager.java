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
import it.carcheck.fastcrud.Database;
import it.carcheck.fastcrud.Configuration;

public class VehicleManager implements IVehicle {

	public VehicleManager() {
		this.database = Database.Begin(new Configuration());
	}

	@Override
	public void doSave(VehicleBean element) throws SQLException {
		database.Update(element);

	}

	@Override
	public void doDelete(VehicleBean element) throws SQLException {
		database.Delete(element);

	}

	@Override
	public void doInsert(VehicleBean element) throws SQLException {
		database.Insert(element);

	}

	@Override
	public ArrayList<VehicleBean> doFind(String query) throws SQLException {

		return database.Find(new VehicleBean(), query);
	}

	@Override
	public PossessionFeeBean doRetrieveLastPossessionFee(VehicleBean vehicle) throws SQLException {
		// TODO Auto-generated method stub
		return (PossessionFeeBean) database.Find(vehicle, "SELECT * FROM possessionfee WHERE vehicle="
				+ vehicle.getLicensePlate() + " ORDER BY expirationDate ASC LIMIT 1").get(0);

	}

	@Override
	public InsuranceBean doRetrieveLastInsurance(VehicleBean vehicle) throws SQLException {
		// TODO Auto-generated method stub
		return (InsuranceBean) database.Find(vehicle, "SELECT * FROM insurance WHERE vehicle="
				+ vehicle.getLicensePlate() + " ORDER BY expirationDate ASC LIMIT 1").get(0);
	}

	@Override
	public Collection<PeopleBean> doRetrieveOwners(VehicleBean vehicle) throws SQLException {

		return database.Find(vehicle, "SELECT People.* FROM people,owner WHERE Owner.vehicle= "
				+ vehicle.getLicensePlate() + "and people.fiscalCode=owner.people");
	}

	@Override
	public VehicleInspectionBean doRetrieveLastVehicleInspection(VehicleBean vehicle) throws SQLException {
		return (VehicleInspectionBean) database
				.Find(vehicle, "SELECT * FROM vehicleInspection WHERE vehicle=" + vehicle.getLicensePlate()
						+ " ORDER BY expirationDate ASC LIMIT 1")
				.get(0);
	}

	private Database database;

}
