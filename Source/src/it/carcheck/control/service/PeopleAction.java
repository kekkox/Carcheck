package it.carcheck.control.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.PeopleManager;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.PeopleBean;
import it.carcheck.model.bean.enums.JsonResponseStatus;

public class PeopleAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		Gson gson = new Gson(); 
		PrintWriter writer = response.getWriter();
		
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		//1 -> add new paople
		//2 -> delete people
		//3 -> modify existing people
		String operation_str = request.getParameter("operation");
		
		if(operation_str == null) {
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "operation type is null")));
			return "people_service";
		}
		
		String peopleFiscalCode = request.getParameter("fiscalCode");
		
		String name 			= request.getParameter("name");
		String surname 			= request.getParameter("surname");
		String birthDate 		= request.getParameter("birthDate");
		String telephoneNumber 	= request.getParameter("telephoneNumber");
		String gender 			= request.getParameter("gender");
		String birthCity 		= request.getParameter("birthCity");
		
		int operation_type = Integer.parseInt(operation_str);
		PeopleManager peopleManager = PeopleManager.getInstance();
		
		switch(operation_type) {
		case 1:
			if(peopleFiscalCode == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "people fiscal code is null")));
				return "people_service";
			}
			
			if(name == null || surname == null || birthDate == null || telephoneNumber == null || gender == null || birthCity == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "one or more parameters not found")));
				return "people_service";
			}
			
			PeopleBean peopleToAdd = new PeopleBean();
			peopleToAdd.setFiscalCode(peopleFiscalCode);
			peopleToAdd.setName(name);
			peopleToAdd.setSurname(surname);
			peopleToAdd.setBirthDate(Date.valueOf(birthDate));
			peopleToAdd.setTelephoneNumber(telephoneNumber);
			peopleToAdd.setGender(gender);
			peopleToAdd.setBirthCity(birthCity);
			
			try {
				peopleManager.doInsert(peopleToAdd);
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
			} catch (SQLException e1) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to add people from database")));
			}
			break;
		case 2:
			if(peopleFiscalCode == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "people fiscal code is null")));
				return "people_service";
			}

			PeopleBean people = peopleManager.doRetrieveByFiscalCode(peopleFiscalCode);
			if(people == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to find people")));
				return "people_service";
			}
			
			try {
				peopleManager.doDelete(people);
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
			} catch (SQLException e) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to delete people from database")));
			}
			
			break;
		case 3:
			if(peopleFiscalCode == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "people fiscal code is null")));
				return "people_service";
			}
			
			if(name == null || surname == null || birthDate == null || telephoneNumber == null || gender == null || birthCity == null) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "one or more parameters not found")));
				return "people_service";
			}
			
			PeopleBean peopleToModify = peopleManager.doRetrieveByFiscalCode(peopleFiscalCode);
			peopleToModify.setName(name);
			peopleToModify.setSurname(surname);
			peopleToModify.setBirthDate(Date.valueOf(birthDate));
			peopleToModify.setTelephoneNumber(telephoneNumber);
			peopleToModify.setGender(gender);
			peopleToModify.setBirthCity(birthCity);
			
			try {
				peopleManager.doSave(peopleToModify);
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "")));
			} catch (SQLException e) {
				writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "unable to modify people from database")));
			}
			
			break;
		default:
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "invalid operation type")));
			break;
		}
		
		return "people_service";
	}
}
