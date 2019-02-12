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
		
		request.setAttribute("property", "readonly");
		request.setAttribute("title", "Visualizza Revisione");
		request.setAttribute("uploadIsVisible", "focus");
		request.setAttribute("buttontext", "Aggiungi Revisione");
		return null;
	}

}
