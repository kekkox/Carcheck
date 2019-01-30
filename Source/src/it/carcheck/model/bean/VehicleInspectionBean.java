package it.carcheck.model.bean;

import java.sql.Date;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

/**
 * Represent the VehicleInspection table of database
 */
public class VehicleInspectionBean {
	
	@TableName(name = "vehicleinspection")
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

	@EntityType(type = Type.PrimaryKey, pkType = PKType.Auto_Increment)
    private int id;
    private Date inspectionDate,expirationDate;
    private int km;
    private boolean result;
    private String photo;
    private int workShop;
    private String vehicle;
}

