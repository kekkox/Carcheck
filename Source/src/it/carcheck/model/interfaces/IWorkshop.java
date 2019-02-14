package it.carcheck.model.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;

public interface IWorkshop extends IUser<WorkshopBean>, IDatabaseOperation<WorkshopBean>{
	
	public void doSignUp(WorkshopBean workshop) throws SQLException;
	
	public WorkshopBean doRetrieveByEmail(String email) throws SQLException;

	public void doSendAdhesionRequest(WorkshopBean workshop) throws SQLException;
	
	public void doAddVehicleInspection(WorkshopBean workshop,VehicleInspectionBean vehicleInspection,VehicleBean vehicle) throws SQLException; 

	public Collection<WorkshopBean> doRetrieveAll();
}
