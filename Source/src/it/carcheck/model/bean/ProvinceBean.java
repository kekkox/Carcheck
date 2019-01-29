package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

/**
 * Represent the Province table of database
 */
public class ProvinceBean {
	
	@TableName(name = "province")
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

	@EntityType(type = Type.PrimaryKey, pkType = PKType.None)
	private String provinceCode;
	private String name;
    private int region;
}
