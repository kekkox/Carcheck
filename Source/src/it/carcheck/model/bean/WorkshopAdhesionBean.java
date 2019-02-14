package it.carcheck.model.bean;

/**
 * Represent the workshop table joined with the adhesion request table of database
 */
public class WorkshopAdhesionBean {

	public WorkshopAdhesionBean() {}
	
	public int getWorkshopId() {
		return workshopId;
	}
	
	public int getAddress() {
		return address;
	}
	
	public String getBusinessName() {
		return businessName;
	}
	
	public String getpIva() {
		return pIva;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public String getEmail() {
		return email;
	}

	public int getAdhesionRequestId() {
		return adhesionRequestId;
	}
	
	public int getStatus() {
		return status;
	}


	private int adhesionRequestId;
	private int status;
	private int workshopId;
	private int address;
	private String businessName, pIva, owner, description, telephone;
	private String email;
}
