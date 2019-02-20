package it.carcheck.control.request;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.bean.InsuranceBean;
import it.carcheck.model.bean.PossessionFeeBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.model.interfaces.IVehicle;
import it.carcheck.utility.JsonResponse;

public class VehicleFindAction implements IAction {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		String licenseplate = request.getParameter("licenseplate");
		
		if(licenseplate == null)
			throw new ActionException("Invalid arguments");
		
		if(!isValidLicenseplate(licenseplate))
			throw new ActionException("Invalid licensepalte");
		
		IVehicle vehiclem =VehicleManager.getInstance();
		
		VehicleBean vehicle;
		try {
			
			vehicle = vehiclem.doRetriveVehicle(licenseplate);
		
			if(vehicle == null) {
				response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
				JsonResponse jsonResponse = new JsonResponse(JsonResponseStatus.FAILED, "Non esiste un veicolo con questa targa");
				response.getWriter().println(new Gson().toJson(jsonResponse));
				return "find";
			}
			
			VehicleInspectionBean inspection = vehiclem.doRetrieveLastVehicleInspection(vehicle);
			PossessionFeeBean possessionFee= vehiclem.doRetrieveLastPossessionFee(vehicle);
			InsuranceBean insurance = vehiclem.doRetrieveLastInsurance(vehicle);
			
			boolean inspectionExpired = false;
			boolean possessionFeeExpired = false;
			boolean insuranceExpired = false;
			
			Date now = new Date(System.currentTimeMillis());
			
			if(inspection.getExpirationDate().before(now))
				inspectionExpired = true;
			if(possessionFee.getExpirationDate().before(now))
				possessionFeeExpired = true;
			if(insurance.getExpirationDate().before(now))
				insuranceExpired = true;
			
			request.setAttribute("vehicle", vehicle);
			request.setAttribute("inspectionExpired", inspectionExpired);
			request.setAttribute("inspectionDate", inspection.getExpirationDate());
			request.setAttribute("possessionFeeDate", possessionFee.getExpirationDate());
			request.setAttribute("possessionFeeExpired", possessionFeeExpired);
			request.setAttribute("insuranceDate", insurance.getExpirationDate());
			request.setAttribute("insuranceExpired", insuranceExpired);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "find";
		} catch (IOException e) {
			e.printStackTrace();
			return "find";
		}
		
		response.setHeader(IAction.HEADER_NAME, IAction.FORWARD_RESPONSE);
		return "result";
	
	}
	
	private boolean isValidLicenseplate(String licenseplate) {
		
		if(licenseplate.matches(CAR_VALIDATOR_1948) || 
				licenseplate.matches(CAR_VALIDATOR_1976) || 
				licenseplate.matches(CAR_VALIDATOR_1999) ||
				licenseplate.matches(CAR_VALIDATOR_TODAY))
			return true;
		
		if(licenseplate.matches(BIKE_VALIDATOR_1927) || 
				licenseplate.matches(BIKE_VALIDATOR_1932) ||
				licenseplate.matches(BIKE_VALIDATOR_1994) ||
				licenseplate.matches(BIKE_VALIDATOR_TODAY))
			return true;
		
		if((licenseplate.matches(MOPED_VALIDATOR_2006) ||
				licenseplate.matches(MOPED_VALIDATOR_TODAY)) &&
				!licenseplate.matches(MOPED_DENIED_CHAR))
			return true;
		
		return false;
	}
	
	private static final String CAR_VALIDATOR_1948 = "((?i)([a-z]){2}([0-9]){5})";
    private static final String CAR_VALIDATOR_1976 = "(([0-9]){6}(?i)([a-z]){2})";
    private static final String CAR_VALIDATOR_1999 = "((?i)([a-z]){3}([0-9]){5})";
    private static final String CAR_VALIDATOR_TODAY = "((?i)([a-z]){2}([0-9]){3}([a-z]){2})";
    
    private static final String BIKE_VALIDATOR_1927 = "([0-9]){5}";
    private static final String BIKE_VALIDATOR_1932 = "([0-9]){4}(?i)([a-z]){2}";
    private static final String BIKE_VALIDATOR_1994 = "(?i)([a-z]){2}([0-9]){6}";
    private static final String BIKE_VALIDATOR_TODAY = "((?i)([a-z]){2}([0-9]){5})";
    
    private static final String MOPED_VALIDATOR_2006 = "(?i)([a-z0-9]){5}";
    private static final String MOPED_VALIDATOR_TODAY = "(?i)(X([b-z2-9]){5})";
    private static final String MOPED_DENIED_CHAR = "(?i)[01aeioqu]+";
}

