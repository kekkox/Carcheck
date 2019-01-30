package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.TableName;

public class OwnerBean {

	@TableName(name = "category")
	public OwnerBean() {
		super();
	}

	/*
	 * Getters and setters methods
	 */
	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	
	
	private String personCode,vehicleCode;
	
}
