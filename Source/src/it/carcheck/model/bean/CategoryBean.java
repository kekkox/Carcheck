package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

/**
 * Represent the Category table of database
 */
public class CategoryBean  {
	
	@TableName(name = "category")
    public CategoryBean() {}

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getInformation() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @EntityType(type = Type.PrimaryKey, pkType = PKType.Auto_Increment)
    private short id;
    private String description;
}
