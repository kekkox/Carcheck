package it.carcheck.model.bean;

public class AdminBean {

	/*
	 * Basic constructor
	 */
	public AdminBean(String name, String surname, String email, String password, boolean isFirstLogin) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.isFirstLogin = isFirstLogin;
	}

	/*
	 * Getters and setters methods
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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
	private String name, surname;
	private String email, password;
	private boolean isFirstLogin;

}
