package it.carcheck.model.interfaces;

import java.util.Collection;

import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.WorkshopAdhesionBean;

public interface IWorkshopAdhesion {
	public WorkshopAdhesionBean doRetrieveByWorkshopId(int id);
	public WorkshopAdhesionBean doRetrieveByAdhesionId(int id);
	public Collection<WorkshopAdhesionBean> doRetrieveAll(AdminBean admin);
}
