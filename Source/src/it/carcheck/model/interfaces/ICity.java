package it.carcheck.model.interfaces;

import java.util.ArrayList;

import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.RegionBean;

public interface ICity extends IDatabaseOperation<CityBean> {
	public CityBean getCityByName(String name);
	public CityBean getCityFromNameAndCap(String name, String cap);
	public ArrayList<CityBean> getCitiesFromCap(String cap);
	public ArrayList<CityBean> getCitiesFromProvince(ProvinceBean province);
	public ArrayList<CityBean> getCitiesFromRegion(RegionBean region);
	public ArrayList<CityBean> getCitiesFromProvinceCode(String code);
}
