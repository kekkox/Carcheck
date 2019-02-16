package it.carcheck.control.request;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;

public class WorkshopInsertInspectionAction implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

		WorkshopBean workshop = (WorkshopBean) request.getSession().getAttribute("user");
		response.setHeader(IAction.HEADER_NAME, IAction.FORWARD_RESPONSE);
		request.setAttribute("title", "Inserisci Revisione");
		request.setAttribute("buttontext", "Aggiungi Revisione");
		request.setAttribute("upload", "isempty");
		request.setAttribute("submitVisible", "focus");
		request.setAttribute("buttonSubmitValue", "ADD");
		return "inspectionView";
	}

}
