package it.carcheck.model.interfaces;

import java.sql.SQLException;

import it.carcheck.model.bean.WorkshopBean;

public interface IWorkshop extends IUser<WorkshopBean>, IDatabaseOperation<WorkshopBean>{
	
	public void doSignUp(WorkshopBean workshop) throws SQLException;

	public void doSendAdhesionRequest(WorkshopBean workshop) throws SQLException;

}
