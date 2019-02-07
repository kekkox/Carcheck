package it.carcheck.model.interfaces;

import java.util.ArrayList;

import it.carcheck.model.bean.CityBean;

public interface ILocation extends IDatabaseOperation<CityBean> {
	public ArrayList<CityBean> doRetrieveByCap(String cap);
	public ArrayList<CityBean> doRetrieveByName(String name);
	public CityBean doRetrieveByName(String name, String cap);
}
