package it.carchek.model.bean;

public class City {
    public City(String istat,String name,short cap,Province province){
    this.istat=istat;
    this.name=name;
    this.cap=cap;
    this.province=province;
    }

    private String istat;

    public String getIstat() {
        return istat;
    }

    public void setIstat(String istat) {
        this.istat = istat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getCap() {
        return cap;
    }

    public void setCap(short cap) {
        this.cap = cap;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    private String name;
    private short cap;
    private Province province;
}
