package it.carcheck.model.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;

public interface IVehicleInspection extends IDatabaseOperation<VehicleInspectionBean> {
	
	public Collection<VehicleInspectionBean> doRetrieveByLicensePlate(WorkshopBean workshop, String licensePlate) throws SQLException;
	
	public Collection<VehicleInspectionBean> doRetrieveByWorkshop(WorkshopBean workshop) throws SQLException;
	
	public VehicleInspectionBean doRetrieveByKey(WorkshopBean workshop, int inspectionCode) throws SQLException;
	
	public Collection<VehicleInspectionBean> doRetrieveExpiringInspection(WorkshopBean workshop) throws SQLException;
	
	public Collection<VehicleInspectionBean>  doRetriveTotalVehicle(WorkshopBean workshop) throws SQLException;
	
	public Collection<VehicleInspectionBean> doRetrieveByWorkshopIdAndLicensePlate(int id, String license);
}
