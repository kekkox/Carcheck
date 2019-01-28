package it.carcheck.fastcrud.core;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.List;

import it.carcheck.fastcrud.Configuration;

public class ConnectionPool {

	private static List<Connection> freeConnections;
	static{
		freeConnections = new LinkedList<Connection>();
		try { Class.forName("com.mysql.jdbc.Driver"); }
		catch(ClassNotFoundException e) { System.out.println("Driver not found: " + e.getMessage()); }
	}
	
	/**
	 * Create new DB connection
	 * @param configuration Connection settings
	 * @return New Connection
	 * @throws SQLException
	 */
	private static synchronized Connection createConnection(Configuration configuration) throws SQLException
	{
		Connection conn = null;
		String ipAddress = configuration.getIp_address();
		String port = String.valueOf(configuration.getPort());
		String database = configuration.getDb_name();
		
		String connectionString = "jdbc:mysql://" + ipAddress + ":" + port + "/" + database;
		conn = DriverManager.getConnection(connectionString, configuration.getUsername(), configuration.getPassword());
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	/**
	 * Get an existing connection or create a new one
	 * @param configuration Configuration settings
	 * @return Connection
	 * @throws SQLException
	 */
	public static synchronized Connection getConnection(Configuration configuration) throws SQLException
	{
		Connection conn;
		if(!freeConnections.isEmpty())
		{
			conn = (Connection)freeConnections.get(0);
			freeConnections.remove(0);
			
			try{
				if(conn.isClosed())
					conn = getConnection(configuration);
			} catch(SQLException e){
				conn.close();
				conn = getConnection(configuration);
			}
		}
		else
			conn = createConnection(configuration);
		
		return conn;
	}
	
	/**
	 * Release an existing connection
	 * @param conn Connection to release
	 */
	public static synchronized void releaseConnection(Connection conn)
	{
		if(conn != null)
			freeConnections.add(conn);
	}
	
}
