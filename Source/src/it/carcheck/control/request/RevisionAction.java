package it.carcheck.control.request;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleInspectionManager;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.WorkshopBean;

public class RevisionAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		Object user = request.getSession().getAttribute("user");
		response.setHeader(IAction.HEADER_NAME, IAction.REDIRECT_RESPONSE);
		
		if(user != null && (user instanceof WorkshopBean)) {
			WorkshopBean workshop = (WorkshopBean)user;
			VehicleInspectionManager inspectionManager = VehicleInspectionManager.getInstance();

			try {
				Collection<VehicleInspectionBean> inspections = inspectionManager.doRetrieveByWorkshop(workshop);
				if(inspections != null && !inspections.isEmpty())
					request.setAttribute("inspections", inspections);
				
				return "workshop/vehicleinspection";
				
			} catch (SQLException e) {
				throw new ActionException(e.getMessage());
			}
		}
		else
			throw new ActionException("unauthorized user");
	}

}
