package it.carcheck.model.interfaces;

import java.sql.SQLException;

import it.carcheck.model.bean.AdhesionRequestBean;

public interface IAdhesionRequest extends IDatabaseOperation<AdhesionRequestBean> {
	
	public void doSetState(AdhesionRequestBean adhesionRequest, int status) throws SQLException;
}
