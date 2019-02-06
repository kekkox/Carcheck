package it.carcheck.model.bean;

import java.sql.Date;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

/**
 * Represent the VehicleInspection table of database
 */
public class VehicleInspectionBean {
	
	@Table(name = "vehicleinspection")
    public VehicleInspectionBean() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getWorkShop() {
        return workShop;
    }

    public void setWorkShop(int workShop) {
        this.workShop = workShop;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @PrimaryKey(option = PrimaryKeyOption.Auto_Increment )
    private int id;
    private Date inspectionDate,expirationDate;
    private int km;
    private boolean result;
    private String photo;
    private int workShop;
    private String vehicle;
}

