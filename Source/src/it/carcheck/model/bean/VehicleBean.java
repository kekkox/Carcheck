package it.carcheck.model.bean;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

/**
 * Represent the Vehicle table of database
 */
public class VehicleBean {

	@Table(name = "vehicle")
	public VehicleBean() {
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public short getYearOfRegistration() {
		return yearOfRegistration;
	}

	public void setYearOfRegistration(short yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	public int getDisplacement() {
		return displacement;
	}

	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}

	public int getKw() {
		return kw;
	}

	public void setKw(int kw) {
		this.kw = kw;
	}

	public boolean isScrapped() {
		return scrapped;
	}

	public void setScrapped(boolean scrapped) {
		this.scrapped = scrapped;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getEuroClass() {
		return euroClass;
	}

	public void setEuroClass(String euroClass) {
		this.euroClass = euroClass;
	}
	
	@PrimaryKey(option = PrimaryKeyOption.None )
	private String licensePlate;
	private String description;
	private short yearOfRegistration;
	private int displacement, kw;
	private boolean scrapped;
	private String category;
	private String fuel;
	private String euroClass;
}
