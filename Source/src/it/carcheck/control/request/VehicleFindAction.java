package it.carcheck.control.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.VehicleManager;
import it.carcheck.model.WorkshopManager;
import it.carcheck.model.bean.InsuranceBean;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.PossessionFeeBean;
import it.carcheck.model.bean.VehicleBean;
import it.carcheck.model.bean.VehicleInspectionBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.model.interfaces.IVehicle;

	
	public class VehicleFindAction implements IAction {
		
		public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException{
			String licenseplate = request.getParameter("licenseplate");
			Gson gson = new Gson();
			if(licenseplate.length()==7)
			{
				
				
				
			}
			
			try {
			  IVehicle vehiclem =VehicleManager.getInstance();
			  
			  VehicleBean vehicle =vehiclem.doRetriveVehicle(licenseplate);
			  VehicleInspectionBean inspection = vehiclem.doRetrieveLastVehicleInspection(vehicle);
			  PossessionFeeBean possessionfee= vehiclem.doRetrieveLastPossessionFee(vehicle);
			  InsuranceBean insurance = vehiclem.doRetrieveLastInsurance(vehicle);
			  
			  ArrayList<Object> inforesponse = new ArrayList();
			  inforesponse.add(vehicle);
			  inforesponse.add(inspection);
			  inforesponse.add(possessionfee);
			  inforesponse.add(insurance);
					try {
						PrintWriter writer = response.getWriter();
						writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK,"",vehicle)));
					} catch (IOException e){
						System.out.println("Eccezione IO");
						e.printStackTrace();
						throw new ActionException();
					}			
			} catch (SQLException e) {
				System.out.println("L'eccezioneeeee");
				e.printStackTrace();
				return "login";
			}
			return licenseplate;
		}

	}

