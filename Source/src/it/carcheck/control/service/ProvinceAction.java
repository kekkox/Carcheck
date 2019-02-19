package it.carcheck.control.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.LocationManager;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.utility.JsonResponse;

public class ProvinceAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		int regionCode = Integer.parseInt(request.getParameter("region"));
		
		Gson gson = new Gson(); 
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		LocationManager locationManager = LocationManager.getInstance();
		PrintWriter writer = response.getWriter();
		
		ArrayList<ProvinceBean> provinces = locationManager.getProvinceManager().getProvincesByRegionCode(regionCode);
		if(provinces != null && provinces.size() <= 0)
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "no provinces")));
		else
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "", provinces)));
		
		return "province_service";
	}
	
}
