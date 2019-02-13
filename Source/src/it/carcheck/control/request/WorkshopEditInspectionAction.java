package it.carcheck.control.request;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;

public class WorkshopEditInspectionAction implements IAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		int inspectionKey = Integer.parseInt(request.getParameter("inspectionKey"));

		VehicleInspectionManager inspection = VehicleInspectionManager.getInstance();

		try{
			WorkshopBean workshop = (WorkshopBean) request.getSession().getAttribute("user");
			VehicleInspectionBean inspectionBean = (VehicleInspectionBean)inspection.doRetrieveByKey(workshop, inspectionKey);
		request.setAttribute("property", "readonly");
		request.setAttribute("class", "classname=active");
		request.setAttribute("title", "Visualizza Revisione");
		request.setAttribute("uploadIsVisible", "");
		request.setAttribute("buttontext", "Modifica Revisione");
		request.setAttribute("licenseplate", inspectionBean.getVehicle());
		request.setAttribute("inspectionDate", inspectionBean.getInspectionDate());
		request.setAttribute("km", inspectionBean.getKm());
		request.setAttribute("approved", inspectionBean.isResult());
		}
		 catch (SQLException e) {
			throw new ActionException();
		}
		return null;
	}
}

