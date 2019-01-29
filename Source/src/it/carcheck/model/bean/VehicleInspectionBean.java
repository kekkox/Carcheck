package it.carchek.model.bean;

import java.util.Calendar;

public class VehicleInspection {
    public VehicleInspection(Calendar inspectionDate, Calendar expirationDate,short km,boolean result,String photo,Vehicle vehicle,WorkShop workShop)
    {
        this.inspectionDate=inspectionDate;
        this.expirationDate=expirationDate;
        this.km=km;
        this.result=result;
        this.photo=photo;
        this.vehicle=vehicle;
        this.workShop=workShop;
    }

    public Calendar getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(Calendar inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public short getKm() {
        return km;
    }

    public void setKm(short km) {
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

    public WorkShop getWorkShop() {
        return workShop;
    }

    public void setWorkShop(WorkShop workShop) {
        this.workShop = workShop;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    private Calendar inspectionDate,expirationDate;
    private short km;
    private boolean result;
    private String photo;
    private WorkShop workShop;
    private Vehicle vehicle;
}

