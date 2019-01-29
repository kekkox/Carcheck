package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

/**
 * Represent the Vehicle table of database
 */
public class VehicleBean {

	@TableName(name = "vehicle")
    public VehicleBean() {}


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getYearOfRegistration() {
        return yearOfRegistration;
    }

    public void setYearOfRegistration(short yearOfRegistration) {
        this.yearOfRegistration = yearOfRegistration;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int getKw() {
        return kw;
    }

    public void setKw(int kw) {
        this.kw = kw;
    }

    public boolean isScrapped() {
        return scrapped;
    }

    public void setScrapped(boolean scrapped) {
        this.scrapped = scrapped;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getEuroClass() {
        return euroClass;
    }

    public void setEuroClass(int euroClass) {
        this.euroClass = euroClass;
    }

    @EntityType(type = Type.PrimaryKey, pkType = PKType.None)
    private String licensePlate;
    private String description;
    private short yearOfRegistration;
    private int displacement,kw;
    private boolean scrapped;
    private int category;
    private int fuel;
    private int euroClass;
}
