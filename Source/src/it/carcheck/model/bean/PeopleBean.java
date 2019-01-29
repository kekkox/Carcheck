package it.carchek.model.bean;

import java.util.Calendar;

public class People {
    public People(String fiscalCode, String name, String surname, String numberPhone, Calendar birthDate, Gender gender, City city)
    {
        this.fiscalCode=fiscalCode;
        this.name=name;
        this.surname=surname;
        this.numberPhone=numberPhone;
        this.birthDate=birthDate;
        this.gender=gender;
        this.city=city;
    }

    private String fiscalCode,name,surname,numberPhone;

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

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    private Calendar birthDate;
    private Gender gender;
    private City city;
}
