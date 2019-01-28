package it.carcheck.fastcrud;

public class Configuration {
	private String ip_address 	= "127.0.0.1";
	private String port 		= "3306";
	private String username 	= "root";
	private String password 	= "";
	private String db_name 		= "";
	
	/**
	 * Empty configuration constructor
	 */
	public Configuration() {}
	
	/**
	 * Configuration constructor
	 * 
	 * @param address Database address (127.0.0.1)
	 * @param port Database port (3306)
	 * @param username Database username
	 * @param password Database password
	 * @param db_name Database name (name of your database)
	 */
	public Configuration(String address, int port, String username, String password, String db_name)
	{
		this.ip_address = address;
		this.port 		= String.valueOf(port);
		this.username 	= username;
		this.password 	= password;
		this.db_name 	= db_name;
	}
	
	/**
	 * Get your database ip address
	 * @return Database ip address
	 */
	public String getIp_address	(					) {return this.ip_address;			}
	/**
	 * Set your database ip address
	 * @param ip_address Ip address to set
	 */
	public void setIp_address	(String ip_address	) {this.ip_address = ip_address;	}
	
	/**
	 * Set your database port
	 * @return Database port
	 */
	public int getPort			(					) {return Integer.parseInt(this.port);	}
	/**
	 * Get your database port
	 * @param port Port to set
	 */
	public void setPort			(int port			) {this.port = String.valueOf(port);	}
	
	/**
	 * Get your database username
	 * @return Database username
	 */
	public String getUsername	(					) {return this.username;		}
	/**
	 * Set your database username
	 * @param username Username to set
	 */
	public void setUsername		(String username	) {this.username = username;	}
	
	/**
	 * Get your database password
	 * @return Database password
	 */
	public String getPassword	(					) {return this.password;		}
	/**
	 * Set your database password
	 * @param password Password to set
	 */
	public void setPassword		(String password	) {this.password = password;	}
	
	/**
	 * Get your database name
	 * @return Database name
	 */
	public String getDb_name	(					) {return this.db_name;		}
	/**
	 * Set your database name
	 * @param db_name Database name to set
	 */
	public void setDb_name		(String db_name		) {this.db_name = db_name;	}
}
