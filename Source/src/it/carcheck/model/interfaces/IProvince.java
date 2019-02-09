package it.carcheck.model.interfaces;

import java.util.ArrayList;

import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.RegionBean;

public interface IProvince extends IDatabaseOperation<ProvinceBean> {
	public ProvinceBean getProvinceByCityName(String name);
	public ProvinceBean getProvinceByCity(CityBean city);
	public ArrayList<ProvinceBean> getProvincesFromRegion(RegionBean region);
	
}
