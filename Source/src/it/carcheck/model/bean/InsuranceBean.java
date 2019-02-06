package it.carcheck.model.bean;

import java.util.Date;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

/**
 * Represent the Insurance table of database
 */
public class InsuranceBean {

	@Table(name = "insurance")
	public InsuranceBean() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public Date getSigningDate() {
		return signingDate;
	}

	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(int insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	@PrimaryKey(option = PrimaryKeyOption.Auto_Increment )
	private int id;
	private String vehicle;
	private Date signingDate;
	private Date expirationDate;
	private int insuranceCompany;
}
