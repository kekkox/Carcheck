package it.carcheck.model.bean;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

/**
 * Represent the Province table of database
 */
public class ProvinceBean {
	
	@Table(name = "province")
    public ProvinceBean() {}

	
    public String getProvinceCode() {
		return provinceCode;
	}
    
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getRegion() {
		return region;
	}
	
	public void setRegion(int region) {
		this.region = region;
	}

	@PrimaryKey(option = PrimaryKeyOption.None )
	private String provinceCode;
	private String name;
    private int region;
}
