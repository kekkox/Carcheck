package it.dsoft.fastcrud.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import it.dsoft.fastcrud.DatabaseConfiguration;
import it.dsoft.fastcrud.core.interfaces.IConnectionPool;
import it.dsoft.fastcrud.exceptions.ConfigurationException;

/**
 * This class manage Database connections
 * @author Daniele De Falco
 *
 */
public class ConnectionPool implements IConnectionPool {

	/**
	 * Class constructor
	 * @param configuration Database configuration class
	 * @throws ClassNotFoundException When mysql class not exist
	 */
	public ConnectionPool(DatabaseConfiguration configuration) throws ClassNotFoundException {
		this.configuration = configuration;
		this.connections = new LinkedList<Connection>();
		
		if(Class.forName("com.mysql.jdbc.Driver") == null)
			throw new ClassNotFoundException();
	}
	
	/**
	 * With this method you can get database connection
	 */
	@Override
	public synchronized Connection getConnection() throws SQLException, ConfigurationException {
		Connection connection = null;
		
		if(!this.connections.isEmpty()) {
			connection = (Connection)this.connections.get(0);
			this.connections.remove(0);
			
			try {
				if(connection.isClosed())
					connection = this.getConnection();
			} catch(SQLException e) {
				connection.close();
				connection = this.getConnection();
			}
		}
		else
			connection = this.createConnection();
		
		return connection;
	}

	/**
	 * With this method you can release an existing connection
	 */
	@Override
	public synchronized void releaseConnection(Connection connection) {
		if(connection != null)
			this.connections.add(connection);
	}
	
	/**
	 * Private method that can create new database connection
	 * @return Database connection
	 * @throws ConfigurationException When configuration file is null
	 * @throws SQLException Database generic error
	 */
	private synchronized Connection createConnection() throws ConfigurationException, SQLException {
		if(this.configuration == null)
			throw new ConfigurationException("configuration null");
		
		String connectionString = "jdbc:mysql://" + this.configuration.getIpAddress() + ":"
				                                  + this.configuration.getPort()      + "/"
				                                  + this.configuration.getDatabase();
		
		Connection connection = null;
		connection = DriverManager.getConnection(connectionString, this.configuration.getUsername(),
				                                                   this.configuration.getPassword());
		connection.setAutoCommit(false);
		return connection;
	}
	
	private DatabaseConfiguration configuration;
	private LinkedList<Connection> connections;
}
