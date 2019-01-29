package it.carcheck.model.bean;

import it.carcheck.fastcrud.core.EntityType;
import it.carcheck.fastcrud.core.TableName;
import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

/**
 * Represent the PermissionType table of database
 */
public class PermissionTypeBean {
	
	@TableName(name = "permissionType")
	public PermissionTypeBean() {}

	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@EntityType(type = Type.PrimaryKey, pkType = PKType.Auto_Increment)
	private int id;
	private String description;
}
