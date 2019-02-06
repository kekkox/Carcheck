package it.dsoft.fastcrud.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import it.dsoft.fastcrud.core.annotations.Entity;
import it.dsoft.fastcrud.core.annotations.PrimaryKey;
import it.dsoft.fastcrud.core.annotations.Table;
import it.dsoft.fastcrud.core.enums.PrimaryKeyOption;
import it.dsoft.fastcrud.core.interfaces.IBeanEntity;
import it.dsoft.fastcrud.utility.Tuple;

/**
 * Class that deals with the management of beans
 * @author Daniele De Falco
 *
 * @param <T> Bean type
 */
public class BeanEntity<T> implements IBeanEntity<T> {
	public BeanEntity(T entity) {
		this.entity = entity;
	}
	
	/**
	 * Get a tuple that contains a primary key name and value
	 */
	public Tuple<String, String> getPrimaryKey() {
		try{
			Field[] fields = this.getBaseFields();
			for(Field field : fields) {
				if(field.getAnnotation(PrimaryKey.class) != null) {
					field.setAccessible(true);
					return new Tuple<String, String>(field.getName(), field.get(entity).toString());
				}
			}
		}catch(Exception e) {}
		return null;
	}
	
	/**
	 * Get entity table name
	 */
	public String getTableName() {
		try{
			@SuppressWarnings("rawtypes")
			Constructor[] constructors = this.getBaseConstructors();
			for(Constructor<?> constructor : constructors) {
				if(constructor.getAnnotation(Table.class) != null)
					return constructor.getAnnotation(Table.class).name();
			}	
		}catch(Exception e) {}
		return null;
	}
	
	/**
	 * Get a tuple that contains entity field name and value from field name
	 */
	public Tuple<String, Object> getField(String name) {
		return new Tuple<String, Object>(name, this.getFields().get(name));
	}
	
	/**
	 * Get a LinkedHashMap that contains all entity filtered fields
	 */
	public Map<String, Object> getFilteredFields(boolean exclude_autoIncrement) {
		try{
			LinkedHashMap<String, Object> hashmap = new LinkedHashMap<>();
			
			Field[] fields = this.getBaseFields();
			for(Field field : fields) {
				if(!exclude_autoIncrement && this.containAutoIncrement(field))
					continue;
				
				String fieldName = field.getName();
				if(this.containModifiedName(field))
					fieldName = this.getModifiedName(field);
				
				field.setAccessible(true);
				hashmap.put(fieldName, field.get(entity));
			}
			
			return hashmap;
		}catch(Exception e) {}
		return null;
	}
	
	/**
	 * Get a LinkedHashMap that contains all entity fields
	 */
	public Map<String, Object> getFields() {
		try{
			LinkedHashMap<String, Object> hashmap = new LinkedHashMap<>();
			
			Field[] fields = this.getBaseFields();
			for(Field field : fields) {
				
				field.setAccessible(true);
				hashmap.put(field.getName(), field.get(entity));
			}
			
			return hashmap;
		}catch(Exception e) {}
		return null;
	}
	
	/**
	 * Set an entity field by key
	 */
	public boolean setField(String key, Object value) {
		return this.setField(new Tuple<String, Object>(key, value));
	}
	
	/**
	 * Set entity field by tuple
	 */
	public boolean setField(Tuple<String, Object> tuple) {
		try{
			Field[] fields = this.getBaseFields();
			for(Field field : fields) {
				if(field.getName() == tuple.GetKey()) {
					field.setAccessible(true);
					field.set(this.entity, tuple.GetValue());
					
					return true;
				}
			}
		}catch(Exception e) {}
		return false;
	}
	
	/**
	 * Set entity fields with an hashmap of elements
	 */
	public void setFields(Map<String, Object> hashmap) {
		Iterator<String> keys = hashmap.keySet().iterator();
		
		while(keys.hasNext()) {
			String key = keys.next();
			Object value = hashmap.get(key);
			
			this.setField(new Tuple<String, Object>(key, value));
		}
	}
	
	/**
	 * Get if primary key is auto increment
	 */
	public boolean isAutoIncrement() {
		try{
			Field[] fields = this.getBaseFields();
			for(Field field : fields) {
				if(field.getAnnotation(PrimaryKey.class) != null &&
				   field.getAnnotation(PrimaryKey.class).option() == PrimaryKeyOption.Auto_Increment)
					return true;
			}
		}catch(Exception e) {}
		return false;
	}
	
	/**
	 * Get if a particular field have modified name
	 */
	public boolean haveModifiedEntityName(String fieldName) {
		Field[] fields = this.getBaseFields();
		for(Field field : fields) {
			if(field.getName().equals(fieldName) && containModifiedName(field))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Get modified field name
	 */
	public String getModifiedEntityName(String fieldName) {
		Field[] fields = this.getBaseFields();
		for(Field field : fields) {
			if(field.getName().equals(fieldName) && containModifiedName(field))
				return getModifiedName(field);
		}
		
		return null;
	}
	
	/**
	 * Check if entity have a primary key
	 */
	public boolean havePrimaryKey() {
		return this.getPrimaryKey() != null ? true : false;
	}
	
	/**
	 * Check if entity have a table name
	 */
	public boolean haveTableName() {
		return this.getTableName() != null ? true : false;
	}
	
	/**
	 * Get generic entity
	 */
	public T getEntity() {
		return this.entity;
	}
	
	/**
	 * Return entity constructors
	 * @return Constructor array
	 */
	@SuppressWarnings("rawtypes")
	private Constructor[] getBaseConstructors() {
		try{
			Class<? extends Object> entityClass = this.entity.getClass();
			return entityClass.getConstructors();
		}catch(Exception e) {}
		return null;
	}
	
	/**
	 * Get entity fields
	 * @return Field array
	 */
	private Field[] getBaseFields() {
		try{
			Class<? extends Object> entityClass = this.entity.getClass();
			return entityClass.getDeclaredFields();
		}catch(Exception e) {}
		return null;
	}
	
	/**
	 * Helper method that return if entity contain an auto increment primary key
	 * @param field Entity field
	 * @return true if contain auto increment primary key, false else
	 */
	private boolean containAutoIncrement(Field field) {
		if(field.getAnnotation(PrimaryKey.class) != null &&
		   field.getAnnotation(PrimaryKey.class).option() == PrimaryKeyOption.Auto_Increment)
			return true;
		
		return false;
	}
	
	/**
	 * Helper method that return if entity contain modified name
	 * @param field Entity field
	 * @return true if contain modified name, false else
	 */
	private boolean containModifiedName(Field field) {
		return field.getAnnotation(Entity.class) != null;
	}
	
	/**
	 * Helper method that return modified entity name
	 * @param field Entity field
	 * @return Entity modified name
	 */
	private String getModifiedName(Field field) {
		return field.getAnnotation(Entity.class) != null ? field.getAnnotation(Entity.class).name()
				                                         : field.getName();
	}
	
	private T entity;
}
