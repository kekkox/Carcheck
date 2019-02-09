package it.carcheck.model.bean;

import it.dsoft.fastcrud.core.annotations.Entity;
import it.dsoft.fastcrud.core.annotations.PrimaryKey;
import it.dsoft.fastcrud.core.annotations.Table;
import it.dsoft.fastcrud.core.enums.PrimaryKeyOption;

/**
 * Represent the Address table of database
 */
public class AddressBean {

	@Table(name = "address")
	public AddressBean() {}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCivicAddress() {
		return civicAddress;
	}
	
	public void setCivicAddress(String civicAddress) {
		this.civicAddress = civicAddress;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}


	@PrimaryKey(option = PrimaryKeyOption.Auto_Increment )
	private int id;
	private String name;
	@Entity(name = "street_number")
	private String civicAddress;
	@Entity(name = "istat")
	private String city;
}
