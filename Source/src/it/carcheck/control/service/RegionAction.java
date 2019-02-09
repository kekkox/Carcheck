package it.carcheck.control.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.LocationManager;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.RegionBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;

import com.google.gson.*;

public class RegionAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		Gson gson = new Gson(); 

		LocationManager locationManager = LocationManager.getInstance();
		JsonResponse jsonResponse = new JsonResponse();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
		
		String requestJson = reader.readLine();
		jsonResponse = gson.fromJson(requestJson, JsonResponse.class);
		
		if(jsonResponse == null)
			return gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "json response is null"));
		
		ArrayList<RegionBean> regions = locationManager.getRegionManager().getAllRegions();
		if(regions != null && regions.size() <= 0)
			return gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "no regions"));
		
		return gson.toJson(new JsonResponse(JsonResponseStatus.OK, "", regions));
	}

}
