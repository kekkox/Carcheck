package it.carcheck.model.interfaces;

import java.util.ArrayList;

import it.carcheck.model.bean.CityBean;
import it.carcheck.model.bean.ProvinceBean;
import it.carcheck.model.bean.RegionBean;

public interface IRegion extends IDatabaseOperation<RegionBean> {
	public RegionBean getRegionByKey(int id);
	public RegionBean getRegionFromCityName(String name);
	public RegionBean getRegionFromCity(CityBean city);
	public RegionBean getRegionFromProvince(ProvinceBean province);
	public ArrayList<RegionBean> getAllRegions();
}
