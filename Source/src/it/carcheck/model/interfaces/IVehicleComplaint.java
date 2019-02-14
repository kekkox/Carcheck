package it.carcheck.model.interfaces;

import it.carcheck.model.bean.VehicleComplaintBean;

public interface IVehicleComplaint extends IDatabaseOperation<VehicleComplaintBean> {
	public VehicleComplaintBean getVehicleComplaintFromLicensePlate(String licensePlate);
}
