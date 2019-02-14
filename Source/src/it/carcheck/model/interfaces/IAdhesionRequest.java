package it.carcheck.model.interfaces;

import java.util.Collection;

import it.carcheck.model.bean.AdhesionRequestBean;

public interface IAdhesionRequest extends IDatabaseOperation<AdhesionRequestBean> {
	
	public void doSetState(AdhesionRequestBean adhesionRequest, int status);
	public Collection<AdhesionRequestBean> doRetrieveByAdminId(int id);
	public AdhesionRequestBean doRetrieveByCode(int code);
}
