package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

/**
 * Represent the InsuranceCompany table of database
 */
public class InsuranceCompanyBean {
	
	@TableName(name = "insurancecompany")
    public InsuranceCompanyBean() {}


    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @EntityType(type = Type.PrimaryKey, pkType = PKType.Auto_Increment)
    private short id;
    private String companyName;
}
