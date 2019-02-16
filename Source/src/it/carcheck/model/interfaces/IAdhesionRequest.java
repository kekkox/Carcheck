package it.carcheck.model.interfaces;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import it.carcheck.model.bean.AdhesionRequestBean;

public interface IAdhesionRequest extends IDatabaseOperation<AdhesionRequestBean> {
	
	public void doSetState(AdhesionRequestBean adhesionRequest, int status);
	public Collection<AdhesionRequestBean> doRetrieveByAdminId(int id);
	public AdhesionRequestBean doRetrieveByCode(int code);
	public void doApproveRequest(AdhesionRequestBean request);
	public void doRejectRequest(AdhesionRequestBean request, String cause);
	public void doSetRequestAppointment(AdhesionRequestBean request, Date date, Time time);
}
