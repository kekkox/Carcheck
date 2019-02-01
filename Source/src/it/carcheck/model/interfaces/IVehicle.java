package it.carcheck.model.interfaces;

import java.util.Collection;

import it.carcheck.model.bean.InsuranceBean;
import it.carcheck.model.bean.PossessionFeeBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.PeopleBean;

public interface IVehicle extends IDatabaseOperation<VehicleBean> {
	
	public PossessionFeeBean doRetrieveLastPossessionFee(VehicleBean vehicle);
	public InsuranceBean doRetrieveLastInsurance(VehicleBean vehicle);
	public Collection<PeopleBean> doRetrieveOwners(VehicleBean vehicle);
	public Collection<VehicleInspectionBean> doRetrieveLastVehicleInspection(VehicleBean vehicle);
	
}
