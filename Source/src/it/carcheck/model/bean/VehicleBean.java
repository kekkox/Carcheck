package it.carchek.model.bean;

public class Vehicle {

    public Vehicle(String licensePlate,String description,short yearOfRegistration,int displacement,int kw,boolean scrapped,Category category,Fuel fuel, EuroClass euroClass)
    {
       this.licensePlate=licensePlate;
       this.description=description;
       this.yearOfRegistration=yearOfRegistration;
       this.displacement=displacement;
       this.kw=kw;
       this.scrapped=scrapped;
       this.category=category;
       this.fuel=fuel;
       this.euroClass=euroClass;
    }


    private String licensePlate,description;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public EuroClass getEuroClass() {
        return euroClass;
    }

    public void setEuroClass(EuroClass euroClass) {
        this.euroClass = euroClass;
    }

    public Insurance isInsured()
    {
     return null;
    }
    public boolean isForYoundDriver()
    {
        return false;
    }
    private short yearOfRegistration;
    private int displacement,kw;
    private boolean scrapped;
    private Category category;
    private Fuel fuel;
    private EuroClass euroClass;
}
