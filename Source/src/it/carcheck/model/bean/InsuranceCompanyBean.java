package it.carchek.model.bean;

public class InsuranceCompany {
    public InsuranceCompany(short id,String companyName)
    {
        this.id=id;
        this.companyName=companyName;
    }


    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private short id;
    private String companyName;
}
