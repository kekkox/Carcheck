package it.carchek.model.bean;

public class Insurance {
    public Insurance(int id, Vehicle vehicle,InsuranceCompany insuranceCompany)
    {
        this.id=id;
        this.vehicle=vehicle;
        this.insuranceCompany=insuranceCompany;
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

    public InsuranceCompany getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(InsuranceCompany insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    private int id;
    private Vehicle vehicle;
    private InsuranceCompany insuranceCompany;
}

