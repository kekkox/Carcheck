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
		response.setHeader(IAction.HEADER_NAME, IAction.FORWARD_RESPONSE);
		try{
			WorkshopBean workshop = (WorkshopBean) request.getSession().getAttribute("user");
			VehicleInspectionBean inspectionBean = (VehicleInspectionBean)inspection.doRetrieveByKey(workshop, inspectionKey);
		request.setAttribute("classname", "class=active");
		request.setAttribute("title", "Modifica Revisione");
		request.setAttribute("upload", "full");
		request.setAttribute("buttontext", "Salva Modifica");
		request.setAttribute("inspectionsView", inspectionBean);
		request.setAttribute("submitVisible", "focus");
		request.setAttribute("buttonSubmitValue", "EDIT");
		
		if(inspectionBean.isResult())
		request.setAttribute("state", "checked");
		
		}
		 catch (SQLException e) {
			throw new ActionException();
		}
		return "inspectionView";
	}
}

