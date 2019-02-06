package it.dsoft.fastcrud.core;

import it.dsoft.fastcrud.core.interfaces.IBeanEntityFactory;

/**
 * This class can create an BeanEntity
 * @author Daniele De Falco
 *
 */
public class BeanEntityFactory implements IBeanEntityFactory {
	
	/**
	 * Empty constructor
	 */
	private BeanEntityFactory() {}
	
	/**
	 * Return a singleton instance of BeanEntityFactory
	 * @return BeanEntityFactory instance
	 */
	public static BeanEntityFactory getInstance() {
		if(instance == null)
			instance = new BeanEntityFactory();
		
		return instance;
	}
	
	/**
	 * With this method it's possible to create a new generic BeanEntity from his class
	 */
	public <T> BeanEntity<T> create(Class<T> clazz) {
		try {
			return new BeanEntity<T>(clazz.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static BeanEntityFactory instance;
}
