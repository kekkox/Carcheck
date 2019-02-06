package it.dsoft.fastcrud.core.interfaces;

/**
 * BeanEntityFactory interface
 * @author Daniele De Falco
 *
 */
public interface IBeanEntityFactory {
	public <T> IBeanEntity<T> create(Class<T> clazz);
}
