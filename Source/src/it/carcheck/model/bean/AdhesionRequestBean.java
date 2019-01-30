package it.carcheck.model.bean;

import java.sql.Date;

import it.carcheck.fastcrud.core.TableName;

public class AdhesionRequestBean {


	@TableName(name = "category")
	public AdhesionRequestBean() {
		super();
	}
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
		return workshopCode;
	}
	public void setWorkshopCode(int workshopCode) {
		this.workshopCode = workshopCode;
	}
	public int getRequestStateCode() {
		return requestStateCode;
	}
	public void setRequestStateCode(int requestStateCode) {
		this.requestStateCode = requestStateCode;
	}
	public int getAdminCode() {
		return adminCode;
	}
	public void setAdminCode(int adminCode) {
		this.adminCode = adminCode;
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
	public Date getMeeetingHour() {
		return meeetingHour;
	}
	public void setMeeetingHour(Date meeetingHour) {
		this.meeetingHour = meeetingHour;
	}
	
	
	
	private int id,workshopCode,requestStateCode,adminCode,status;
	private Date meetingDate,meeetingHour;
	
	
	
	
	
}
