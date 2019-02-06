package it.dsoft.fastcrud;

/**
 * This class represents the database configuration
 * @author Daniele De Falco
 *
 */
public class DatabaseConfiguration {
	
	/**
	 * Class constructor
	 */
	public DatabaseConfiguration() {
		this.ipAddress = "127.0.0.1";
		this.port = 3306;
	}
	
	/**
	 * Class constructor
	 * @param username Mysql username
	 * @param password Mysql password
	 * @param database Mysql database 
	 */
	public DatabaseConfiguration(String username, String password, String database) {
		this();
		
		this.username = username;
		this.password = password;
		this.database = database;
	}
	
	/**
	 * Class constructor
	 * @param ipAddress Mysql address
	 * @param port Mysql port
	 * @param username Mysql username
	 * @param password Mysql password
	 * @param database Mysql database
	 */
	public DatabaseConfiguration(String ipAddress, int port, String username, String password, String database) {
		this.ipAddress = ipAddress;
		this.port = port;
		
		this.username = username;
		this.password = password;
		this.database = database;
	}
	
	
	/**
	 * This method return mysql ipAddress
	 * @return database ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * This method set ipAddress
	 * @param ipAddress Mysql address
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	/**
	 * This method return mysql port
	 * @return mysql port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * This method set mysql port
	 * @param port mysql port
	 */
	public void setPort(int port) {
		this.port = port;
	}


	/**
	 * This method return mysql username
	 * @return mysql username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * This method set mysql username
	 * @param username mysql username
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * This method return mysql password
	 * @return mysql password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * This method set mysql password
	 * @param mysql password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * This method return mysql database
	 * @return mysql database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * This method set mysql database
	 * @param mysql database
	 */
	public void setDatabase(String database) {
		this.database = database;
	}



	private String ipAddress;
	private int port;
	private String username;
	private String password;
	private String database;
}
