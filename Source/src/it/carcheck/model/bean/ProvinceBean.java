package it.carchek.model.bean;

public class Province {
    public Province(String provinceCode,String name,Region region)
    {
        this.provinceCode=provinceCode;
        this.name=name;
        this.region=region;
    }

    private String provinceCode,name;
    private Region region;
}
