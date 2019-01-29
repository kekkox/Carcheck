package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;


/**
 * Represent the City table of database
 */
public class CityBean {
	
	@TableName(name = "city")
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
    
    @EntityType(type = Type.PrimaryKey, pkType = PKType.None)
    private String istat;
    private String name;
    private String cap;
    private String province;
}
