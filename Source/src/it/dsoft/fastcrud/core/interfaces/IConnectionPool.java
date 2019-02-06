package it.dsoft.fastcrud.core.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

import it.dsoft.fastcrud.exceptions.ConfigurationException;

/**
 * ConnectionPool interface
 * @author Daniele De Falco
 *
 */
public interface IConnectionPool {
	public Connection getConnection() throws SQLException, ConfigurationException;
	public void releaseConnection(Connection connection);
}
