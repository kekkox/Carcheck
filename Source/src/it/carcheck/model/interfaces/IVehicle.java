package it.carcheck.model.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import it.carcheck.model.bean.InsuranceBean;
import it.carcheck.model.bean.PossessionFeeBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.PeopleBean;

public interface IVehicle extends IDatabaseOperation<VehicleBean> {
	
	public PossessionFeeBean doRetrieveLastPossessionFee(VehicleBean vehicle) throws SQLException;
	public InsuranceBean doRetrieveLastInsurance(VehicleBean vehicle) throws SQLException;
	public Collection<PeopleBean> doRetrieveOwners(VehicleBean vehicle) throws SQLException;
	public VehicleInspectionBean doRetrieveLastVehicleInspection(VehicleBean vehicle) throws SQLException;
	public VehicleBean doRetriveVehicle(String licensePlate)throws SQLException;
	
}
