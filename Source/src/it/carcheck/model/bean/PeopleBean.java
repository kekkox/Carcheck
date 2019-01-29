package it.carcheck.model.bean;

import java.sql.Date;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

/**
 * Represent the People table of database
 */
public class PeopleBean {
	
	@TableName(name = "people")
    public PeopleBean() {}

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String numberPhone) {
        this.telephoneNumber = numberPhone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String city) {
        this.birthCity = city;
    }
    
    
    @EntityType(type = Type.PrimaryKey, pkType = PKType.None)
    private String fiscalCode;
    private String name;
    private String surname;
    private String telephoneNumber;
    private String birthCity;
    private Date birthDate;
    private String gender;
}
