package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

/**
 * Represent the Region table of database
 */
public class RegionBean {
    
	@TableName(name = "region")
	public RegionBean() {}

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @EntityType(type = Type.PrimaryKey, pkType = PKType.Auto_Increment)
    private short id;
    private String name;
}
