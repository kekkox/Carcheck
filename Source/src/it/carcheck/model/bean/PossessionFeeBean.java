package it.carcheck.model.bean;

import java.sql.Date;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

/**
 * Represent the PossessionFee table of database
 */
public class PossessionFeeBean {
	
	@Table(name = "possessionfee")
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

    @PrimaryKey(option = PrimaryKeyOption.Auto_Increment )
    private int id;
    private String vehicle;
    private Date expirationDate;
}

