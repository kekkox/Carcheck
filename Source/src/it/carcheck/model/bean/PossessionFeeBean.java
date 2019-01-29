package it.carchek.model.bean;

import java.util.Calendar;

public class PossessionFee {
    public PossessionFee(int id, Vehicle vehicle,Calendar expirationDate)
    {
        this.id=id;
        this.vehicle=vehicle;
        this.expirationDate=expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    private int id;
    private Vehicle vehicle;
    private Calendar expirationDate;
}

