package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.TableName;

public class OwnerBean {

	@TableName(name = "owner")
	public OwnerBean() {
	}

	/*
	 * Getters and setters methods
	 */
	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	private String people, vehicle;
}
