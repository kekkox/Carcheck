package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

/**
 * Represent the Insurance table of database
 */
public class InsuranceBean {
	
	@TableName(name = "insurance")
    public InsuranceBean() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public int getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(int insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @EntityType(type = Type.PrimaryKey, pkType = PKType.Auto_Increment)
    private int id;
    private String vehicle;
    private int insuranceCompany;
}

