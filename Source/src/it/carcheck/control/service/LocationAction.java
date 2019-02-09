package it.carcheck.control.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.LocationManager;
import it.carcheck.model.bean.JsonResponse;

import com.google.gson.*;

public class LocationAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		
		Gson gson = new Gson(); 
		JsonResponse resonse = new JsonResponse();
		
		LocationManager locationManager = LocationManager.getInstance();
		
		return null;
	}

}
