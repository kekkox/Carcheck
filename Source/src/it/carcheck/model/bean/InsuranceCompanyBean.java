package it.carcheck.model.bean;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

/**
 * Represent the InsuranceCompany table of database
 */
public class InsuranceCompanyBean {
	
	@Table(name = "insurancecompany")
    public InsuranceCompanyBean() {}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @PrimaryKey(option = PrimaryKeyOption.Auto_Increment )
    private int id;
    private String companyName;
}
