package it.carcheck.control.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AddressManager;
import it.carcheck.model.bean.AddressBean;
import it.carcheck.model.bean.JsonResponse;
import it.carcheck.model.bean.enums.JsonResponseStatus;

public class AddressAction implements IAction {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		String istat = request.getParameter("istat");
		String addressName = request.getParameter("address");
		
		Gson gson = new Gson(); 
		PrintWriter writer = response.getWriter();
		
		response.setHeader(IAction.HEADER_NAME, IAction.JSON_RESPONSE);
		
		ArrayList<AddressBean> addresses = AddressManager.getInstance().getAddressByName(addressName, istat);
		if(addresses != null && addresses.size() <= 0)
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.FAILED, "no address")));
		else
			writer.println(gson.toJson(new JsonResponse(JsonResponseStatus.OK, "", addresses)));
		
		return "address_service";
	}

}
