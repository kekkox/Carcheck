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
import it.carcheck.model.bean.enums.JsonResponseStatus;
import it.carcheck.model.interfaces.IAdhesionRequest;
import it.carcheck.utility.JsonResponse;

public class RequestsAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		int request_id = Integer.parseInt(request.getParameter("id"));
		int operation_type = Integer.parseInt(request.getParameter("operation"));
		
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		Gson gson = new Gson(); 
		PrintWriter writer = response.getWriter();
		
		if(request_id == 0 && operation_type == 0) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "invalid request type or operation type")));
			return "requests_service";
		}
		
		IAdhesionRequest adhesionManager = AdhesionRequestManager.getInstance();
		AdhesionRequestBean adhesion = adhesionManager.doRetrieveByCode(request_id);
		
		if(adhesion == null) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "no adhesion found")));
			return "requests_service";
		}
		
		//1 -> refuse request
		//2 -> set appointment
		//3 -> approve request
		switch(operation_type) {
		case 1:
			String cause = request.getParameter("cause");
			
			if(cause == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "Null cause")));
				return "requests_service";
			}
			
			adhesionManager.doRejectRequest(adhesion, cause);
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
			break;
		case 2:
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			
			if(date == null || time == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "date or time null")));
				return "requests_service";
			}
			
			adhesionManager.doSetRequestAppointment(adhesion, Date.valueOf(date), Time.valueOf(time + ":00"));
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
			break;
		case 3:
			adhesionManager.doApproveRequest(adhesion);
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
			break;
		default:
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "invalid operation type")));
			return "requests_service";
		}
		
		return "requests_service";
	}

}
