package it.dsoft.fastcrud.core;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import it.dsoft.fastcrud.core.interfaces.IBeanEntity;
import it.dsoft.fastcrud.exceptions.ObjectMapException;
import it.dsoft.fastcrud.exceptions.TableNameException;

/**
 * This class can be manage an BeanEntity
 * @author Daniele De Falco
 *
 */
public class BeanManager {
	/**
	 * Empty BeanManager constructor
	 */
	public BeanManager() {}
	
	/**
	 * This method can create a linkedHashSet of BeanEntity from database resultSet
	 * @param result Database resultSet
	 * @param objectClass BeanEntity class
	 * @return LinkedHashSet of BeanEntity
	 */
	public <T> LinkedHashSet<IBeanEntity<T>> MakeBeanEntities(ResultSet result, Class<T> objectClass) {
		if(result == null)
			throw new NullPointerException("ResultSet is null");
		
		LinkedHashSet<IBeanEntity<T>> entities = new LinkedHashSet<>();
		
		try {
			while(result.next()) {
				IBeanEntity<T> entity = BeanEntityFactory.getInstance().create(objectClass);
				LinkedHashMap<String, Object> fields = (LinkedHashMap<String, Object>) entity.getFields();
				
				Iterator<String> keys = fields.keySet().iterator();
				while(keys.hasNext()) {
					String key = keys.next();
					entity.setField(key, result.getObject(entity.haveModifiedEntityName(key)
							                             ? entity.getModifiedEntityName(key)
					                                     : key));
				}
				
				entities.add(entity);
			}
			return entities;
		} catch(Exception ex) {}
		return null;
	}
	
	/**
	 * This method can create a linkedHashMap of BeanEntity fields name and his value
	 * @param entity BeanEntity object
	 * @return LinkedHashMap of BeanEntity fields
	 * @throws ObjectMapException Exception generated when an object can not be mapped
	 * @throws TableNameException Exception generated when the name of a table is not found
	 */
	public <T> LinkedHashMap<String, String> MapObject(IBeanEntity<T> entity) throws ObjectMapException, TableNameException {
		if(entity == null)
			throw new ObjectMapException("BeanEntity is null");
		
		if(!entity.haveTableName())
			throw new TableNameException("BeanEntity does not have a defined table name");
		
		LinkedHashMap<String, String> hashmap = new LinkedHashMap<>();
		
		try {
			LinkedHashMap<String, Object> fields = (LinkedHashMap<String, Object>) entity.getFilteredFields(false);
			Iterator<String> keys = fields.keySet().iterator();
			
			while(keys.hasNext()) {
				String key = keys.next();
				String value = fields.get(key).toString();
				
				hashmap.put(key, value);
			}
			return hashmap;
		} catch(Exception ex) {}
		return null;
	}
}
