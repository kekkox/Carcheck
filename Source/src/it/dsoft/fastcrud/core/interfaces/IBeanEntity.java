package it.dsoft.fastcrud.core.interfaces;

import java.util.Map;

import it.dsoft.fastcrud.utility.Tuple;

/**
 * BeanEntity interface
 * @author Daniele De Falco
 *
 * @param <T> Generic bean class
 */
public interface IBeanEntity<T> {
	public Tuple<String, String> getPrimaryKey();
	public String getTableName();
	
	public Tuple<String, Object> getField(String name);
	public Map<String, Object> getFilteredFields(boolean exclude_autoIncrement);
	public Map<String, Object> getFields();
	
	public boolean setField(String key, Object value);
	public boolean setField(Tuple<String, Object> tuple);
	public void setFields(Map<String, Object> hashmap);
	
	public boolean isAutoIncrement();
	public boolean havePrimaryKey();
	public boolean haveTableName();
	
	public boolean haveModifiedEntityName(String fieldName);
	public String getModifiedEntityName(String fieldName);
	
	public T getEntity();
}
