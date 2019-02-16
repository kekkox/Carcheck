package it.carcheck.control.request;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionBadFormedException;
import it.carcheck.control.exception.ActionException;
import it.carcheck.control.interfaces.IAction;
import it.carcheck.model.AddressManager;
import it.carcheck.model.LocationManager;
import it.carcheck.model.WorkshopAdhesionManager;
import it.carcheck.model.bean.AddressBean;
import it.carcheck.model.bean.AdminBean;
import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.RegionBean;
import it.carcheck.model.bean.WorkshopAdhesionBean;
import it.carcheck.model.interfaces.IAddress;
import it.carcheck.model.interfaces.ICity;
import it.carcheck.model.interfaces.IProvince;
import it.carcheck.model.interfaces.IRegion;
import it.carcheck.model.interfaces.IWorkshopAdhesion;

public class ViewAdhesionAction implements IAction {
	public ViewAdhesionAction() {}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException {
		
		response.setHeader(IAction.HEADER_NAME, IAction.FORWARD_RESPONSE);
		
		AdminBean loggedAdmin = (AdminBean) request.getSession().getAttribute("user");
		String adhesionParameter = request.getParameter("adhesion");
		if(adhesionParameter == null)
			throw new ActionBadFormedException();
		int adhesionId = Integer.parseInt(adhesionParameter);
		
		
		IWorkshopAdhesion manager = WorkshopAdhesionManager.getInstance();
		IAddress addressManager = AddressManager.getInstance();
		ICity cityManager = LocationManager.getInstance().getCityManager();
		IProvince provinceManager = LocationManager.getInstance().getProvinceManager();
		IRegion regionManager = LocationManager.getInstance().getRegionManager();
		
		WorkshopAdhesionBean adhesion = manager.doRetrieveByAdhesionId(adhesionId, loggedAdmin);
		
		if(adhesion == null)
			throw new ActionBadFormedException();
		
		AddressBean addressBean = addressManager.getAddressByKey(adhesion.getAddress());
		CityBean cityBean = cityManager.getCityByKey(addressBean.getCity());
		ProvinceBean provinceBean = provinceManager.getProvinceByKey(cityBean.getProvince());
		RegionBean regionBean = regionManager.getRegionByKey(provinceBean.getRegion());
		
		request.setAttribute("showedAdhesion", adhesion);
		request.setAttribute("region", regionBean.getName());
		request.setAttribute("province", provinceBean.getName());
		request.setAttribute("city", cityBean.getName());
		request.setAttribute("address", addressBean.getName());
		
		return "admin/adhesionView";
	}
	
	
}
