package it.carcheck.control.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AdhesionRequestManager;
import it.carcheck.model.bean.AdhesionRequestBean;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.enums.JsonResponseStatus;

public class RequestsAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		//1 -> change request state
		//2 -> change date/hour
		int request_id = Integer.parseInt(request.getParameter("id"));
		int operation_type = Integer.parseInt(request.getParameter("operation"));
		
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		Gson gson = new Gson(); 
		PrintWriter writer = response.getWriter();
		
		if(request_id == 0 && operation_type == 0) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "invalid request type or operation type")));
			return "requests_service";
		}
		
		AdhesionRequestManager adhesionManager = AdhesionRequestManager.getInstance();
		AdhesionRequestBean adhesion = adhesionManager.doRetrieveByCode(request_id);
		
		if(adhesion == null) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "no adhesion found")));
			return "requests_service";
		}
			
		switch(operation_type) {
		case 1:
			int newState = Integer.parseInt(request.getParameter("state"));
			if(newState == 0) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "invalid state id")));
				return "requests_service";
			}
			
			adhesionManager.doSetState(adhesion, newState);
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
			break;
		case 2:
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			
			if(date == null || time == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "date or time null")));
				return "requests_service";
			}
			
			adhesion.setMeetingDate(Date.valueOf(date));
			adhesion.setMeeetingHour(Time.valueOf(time));
			
			adhesionManager.doSave(adhesion);
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
			break;
		default:
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "invalid operation type")));
			return "requests_service";
		}
		
		return "requests_service";
	}

}
