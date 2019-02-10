package it.carcheck.model.interfaces;

import java.util.ArrayList;

import it.carcheck.model.bean.AddressBean;
import it.carcheck.model.bean.CityBean;

public interface IAddress extends IDatabaseOperation<AddressBean>{
	
	public ArrayList<AddressBean> getFullAddressByName(String name, String istat);
	public ArrayList<AddressBean> getFullAddressByName(String name, CityBean city);
	public ArrayList<AddressBean> getAddressByName(String name, String istat);
	public ArrayList<AddressBean> getAddressByName(String name, CityBean city);
	
}
