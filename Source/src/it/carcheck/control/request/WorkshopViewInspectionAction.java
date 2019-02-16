package it.carcheck.control.request;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;;

public class WorkshopViewInspectionAction implements IAction {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		int inspectionKey = Integer.parseInt(request.getParameter("inspectionKey"));
		WorkshopBean workshop = (WorkshopBean) request.getSession().getAttribute("user");
		response.setHeader(IAction.HEADER_NAME, IAction.FORWARD_RESPONSE);
		VehicleInspectionManager inspection = VehicleInspectionManager.getInstance();
		try{
		VehicleInspectionBean inspectionBean = inspection.doRetrieveByKey(workshop, inspectionKey);
		request.setAttribute("property", "readonly");
		request.setAttribute("classname", "class=active");
		request.setAttribute("title", "Visualizza Revisione");
		request.setAttribute("upload", "fullView");
		
		request.setAttribute("inspectionsView", inspectionBean);
		/*
		request.setAttribute("licenseplate", inspectionBean.getVehicle());
		request.setAttribute("inspectionDate", inspectionBean.getInspectionDate());
		request.setAttribute("km", inspectionBean.getKm());
		request.setAttribute("approved", inspectionBean.isResult());
		*/
		}

		catch (SQLException e) {
			throw new ActionException();
		}
		return "inspectionView";

	}
}
