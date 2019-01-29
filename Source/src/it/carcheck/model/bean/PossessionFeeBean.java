package it.carcheck.model.bean;

import java.sql.Date;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

public class PossessionFeeBean {
	
	@TableName(name = "possessionfee")
    public PossessionFeeBean() {}

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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @EntityType(type = Type.PrimaryKey, pkType = PKType.Auto_Increment)
    private int id;
    private String vehicle;
    private Date expirationDate;
}

