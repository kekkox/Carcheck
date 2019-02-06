package it.carcheck.model.bean;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;


/**
 * Represent the City table of database
 */
public class CityBean {
	
	@Table(name = "city")
    public CityBean() {}

    public String getIstat() {
        return istat;
    }

    public void setIstat(String istat) {
        this.istat = istat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    
    @PrimaryKey(option = PrimaryKeyOption.None )
    private String istat;
    private String name;
    private String cap;
    private String province;
}
