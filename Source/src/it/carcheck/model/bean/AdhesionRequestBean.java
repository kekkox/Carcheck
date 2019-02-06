package it.carcheck.model.bean;

import java.sql.Date;
import java.sql.Time;

import it.dsoft.fastcrud.core.annotations.*;
import it.dsoft.fastcrud.core.enums.*;

public class AdhesionRequestBean {


	@Table(name = "adhesionrequest")
	public AdhesionRequestBean() {}
	/*
	 * Getters and setters methods
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWorkshopCode() {
		return workshop;
	}
	public void setWorkshopCode(int workshopCode) {
		this.workshop = workshopCode;
	}

	public int getAdminCode() {
		return admin;
	}
	public void setAdminCode(int adminCode) {
		this.admin = adminCode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}
	public Time getMeeetingHour() {
		return meeetingHour;
	}
	public void setMeeetingHour(Time meeetingHour) {
		this.meeetingHour = meeetingHour;
	}
	
	
    @PrimaryKey(option = PrimaryKeyOption.Auto_Increment )
	private int id;
	private int workshop,admin,status;
	private Date meetingDate;
	private Time meeetingHour;	
	
}
