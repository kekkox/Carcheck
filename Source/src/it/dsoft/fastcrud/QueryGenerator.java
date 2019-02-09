package it.dsoft.fastcrud;

import java.util.Iterator;
import java.util.LinkedHashMap;

import it.dsoft.fastcrud.core.BeanManager;
import it.dsoft.fastcrud.core.interfaces.IBeanEntity;
import it.dsoft.fastcrud.enums.OperationType;
import it.dsoft.fastcrud.exceptions.ObjectMapException;
import it.dsoft.fastcrud.exceptions.TableNameException;
import it.dsoft.fastcrud.utility.CharacterChecking;

/**
 * This class is responsible for generating queries used by FastCrud
 * @author Daniele De Falco
 *
 */
class QueryGenerator {
	
	/**
	 * 
	 */
	public QueryGenerator() {
		this.beanManager = new BeanManager();
	}
	
	/**
	 * With this method it is possible to automatically generate a query starting from a beanEntity
	 * @param entity Object that represents an entity in the database
	 * @param perationType Enumerator that represents the operation on which to create the query
	 * @return Generic type
	 * @throws ObjectMapException Exception generated when an object can not be mapped
	 * @throws TableNameException Exception generated when the name of a table is not found
	 */
	public <T> String generateQuery(IBeanEntity<T> entity, OperationType operationType) throws ObjectMapException, TableNameException {
		
		if(operationType == OperationType.Create)
			return createOperation(entity);
		else if(operationType == OperationType.Update)
			return updateOperation(entity);
		else if(operationType == OperationType.Delete)
			return deleteOperation(entity);
		
		return null;
	}
	
	/**
	 * With this method it is possible to automatically generate a query starting from a beanEntity
	 * @param entity Object that represents an entity in the database
	 * @return Generic type
	 * @throws ObjectMapException Exception generated when an object can not be mapped
	 * @throws TableNameException Exception generated when the name of a table is not found
	 */
	private <T> String createOperation(IBeanEntity<T> entity) throws ObjectMapException, TableNameException {
		LinkedHashMap<String, String> map = this.beanManager.MapObject(entity);
		
		if(map == null)
			throw new ObjectMapException();
		
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ").append(entity.getTableName()).append("(");
		
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = CharacterChecking.replaceInvalidCharacters(iterator.next()); //Workaround
			builder.append("'").append(key).append("'").append(",");
		}
		builder.deleteCharAt(builder.length() - 1).append(")").append("VALUES(");
		
		for(int i = 0; i < map.size(); i++)
			builder.append("?,");
		
		builder.deleteCharAt(builder.length() - 1).append(");");
		return builder.toString();
	}
	
	/**
	 * With this method it is possible to automatically generate a query starting from a beanEntity
	 * @param entity Object that represents an entity in the database
	 * @return Generic type
	 * @throws ObjectMapException Exception generated when an object can not be mapped
	 * @throws TableNameException Exception generated when the name of a table is not found
	 */
	private <T> String updateOperation(IBeanEntity<T> entity) throws ObjectMapException, TableNameException {
		LinkedHashMap<String, String> map = this.beanManager.MapObject(entity);
		
		if(map == null)
			throw new ObjectMapException();
		
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ").append(entity.getTableName()).append(" SET ");
		
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()) {
			String key = CharacterChecking.replaceInvalidCharacters(iterator.next()); //Workaround
			builder.append("'").append(key).append("'").append("=?,");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(" WHERE ").append("'").append(entity.getPrimaryKey().GetKey()).append("'").append("=?;");
		
		return builder.toString();
	}
	
	/**
	 * With this method it is possible to automatically generate a query starting from a beanEntity
	 * @param entity Object that represents an entity in the database
	 * @return Generic type
	 */
	private <T> String deleteOperation(IBeanEntity<T> entity) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ").append(entity.getTableName());
		builder.append(" WHERE ").append("'").append(entity.getPrimaryKey().GetKey()).append("'").append("=?;");
		
		return builder.toString();
	}
	
	private BeanManager beanManager;
}
