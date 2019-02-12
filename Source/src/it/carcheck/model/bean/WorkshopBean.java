package it.carcheck.model.bean;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

/**
 * Represent the Workshop table of database
 */
public class WorkshopBean {

	@Table(name = "workshop")
	public WorkshopBean() {}

	/*
	 * Getters and setters methods
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}


	/*
	 * Instance Variables
	 */
	@PrimaryKey(option = PrimaryKeyOption.Auto_Increment )
	private int id;
	private int address;	
	private String businessName, pIva, owner, description, telephone;
	private String email, password;
	private boolean isFirstLogin;
}
