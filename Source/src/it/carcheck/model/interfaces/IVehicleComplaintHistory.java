package it.carcheck.model.interfaces;

import java.util.Collection;

import it.carcheck.model.bean.VehicleComplaintHistoryBean;

public interface IVehicleComplaintHistory extends IDatabaseOperation<VehicleComplaintHistoryBean> {
	public Collection<VehicleComplaintHistoryBean> doGetVehicleComplaintsFromLicensePlate(String licensePlate);
}
