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
import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.enums.JsonResponseStatus;

public class CityAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		String provinceCode = request.getParameter("province");
		
		Gson gson = new Gson(); 
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);

		LocationManager locationManager = LocationManager.getInstance();
		PrintWriter writer = response.getWriter();
		
		ArrayList<CityBean> cities = locationManager.getCityManager().getCitiesFromProvinceCode(provinceCode);
		if(cities != null && cities.size() <= 0)
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "no cities")));
		else
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "", cities)));
		
		return "city_service";
	}

}
