package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

public class PermissionType {
	
	@TableName(name = "permissionType")
	public PermissionType(){
	}

	public int getId() {
		return this.Id;
	}
	public void setId(int id) {
		this.Id = id;
	}
	public String getDescription() {
		return this.Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	
	@EntityType(type = Type.PrimaryKey, pkType = PKType.Auto_Increment)
	private int Id;
	private String Description;
}
