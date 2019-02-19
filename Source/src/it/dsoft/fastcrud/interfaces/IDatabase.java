package it.dsoft.fastcrud.interfaces;

import java.sql.SQLException;
import java.util.LinkedHashSet;

import it.dsoft.fastcrud.exceptions.ConfigurationException;
import it.dsoft.fastcrud.exceptions.ObjectMapException;
import it.dsoft.fastcrud.exceptions.TableNameException;

/**
 * Database interface
 * @author Daniele De Falco
 *
 */
public interface IDatabase {
	public <T> int create(T object) throws SQLException, ConfigurationException, ObjectMapException, TableNameException, Exception;
	public <T> int update(T object) throws SQLException, ConfigurationException, ObjectMapException, TableNameException, Exception;
	public <T> int delete(T object) throws SQLException, ConfigurationException, ObjectMapException, TableNameException, Exception;
	public <T> LinkedHashSet<T> read(Class<T> clazz, String query, Object...args) throws SQLException, ConfigurationException, Exception;
}
