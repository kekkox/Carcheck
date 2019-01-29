package it.carcheck.model.bean;

public class WorkShopBean {

	/*
	 * Basic constructor
	 */
	public WorkShopBean(String businessName, String pIva, String address, String owner, String description,
			String telephone, String email, String password, boolean isFirstLogin) {
		super();
		this.businessName = businessName;
		this.pIva = pIva;
		this.address = address;
		this.owner = owner;
		this.description = description;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
		this.isFirstLogin = isFirstLogin;
	}

	/*
	 * Getters and setters methods
	 */

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
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
	private String businessName, pIva, address, owner, description, telephone;
	private String email, password;
	private boolean isFirstLogin;
}
