package it.carcheck.model.bean;

import java.sql.Date;

import it.dsoft.fastcrud.core.annotations.PrimaryKey;
import it.dsoft.fastcrud.core.annotations.Table;
import it.dsoft.fastcrud.core.enums.PrimaryKeyOption;

public class VehicleComplaintHistoryBean {
	
	@Table(name = "vehiclecomplaint_history")
	public VehicleComplaintHistoryBean() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	
	public Date getComplaintDate() {
		return complaintDate;
	}
	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@PrimaryKey(option = PrimaryKeyOption.Auto_Increment)
	private int id;
	private String licensePlate;
	private Date complaintDate;
	private String description;
}
