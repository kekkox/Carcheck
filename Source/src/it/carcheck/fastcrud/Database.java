package it.carcheck.fastcrud;
import java.util.ArrayList;

import it.carcheck.fastcrud.core.ConnectionPool;
import it.carcheck.fastcrud.util.BeanManager;
import it.carcheck.fastcrud.util.StatementFactory;
import it.carcheck.fastcrud.util.Tuple;

import java.sql.*;

public class Database {
	
	private Configuration configuration;
	
	private StatementFactory factory;
	private static Database instance;
	
	/**
	 * Database CRUD constructor
	 */
	private Database() { factory = new StatementFactory(); }
	/**
	 * Get database instance (Singleton)
	 * @return Database instance
	 */
	public static Database GetInstance()
	{
		if(instance == null)
			instance = new Database();
		
		return instance;
	}
	
	/**
	 * Get database instance (Singleton)
	 * @param configuration Set your mysql database settings
	 * @return Database instance
	 */
	public static Database Begin(Configuration configuration)
	{
		Database db = Database.GetInstance();
		db.configuration = configuration;
		
		return db;
	}
	
	/**
	 * Set database configuration
	 * @param configuration Settings to use
	 */
	public void SetConfiguration(Configuration configuration)
	{
		this.configuration = configuration;
	}
	
	/**
	 * Get your database settings
	 * @return Database settings
	 */
	public Configuration GetConfiguration()
	{
		return this.configuration;	
	}
	
	/**
	 * Insert an element into database
	 * @param object Object to insert into database (DTO -- Data Transfer Object)
	 * @return Current index into database
	 * @throws SQLException
	 */
	public <T> int Insert(T object) throws SQLException
	{
		Connection conn = null;
		PreparedStatement statement = null;
		BeanManager manager = new BeanManager(object);
		
		try{
			conn = ConnectionPool.getConnection(configuration);
			String tableName = manager.GetTableName();
			
			ArrayList<Tuple<String, String>> mapping = manager.Map();
			if(tableName == null)
				throw new IllegalArgumentException("Insert failed, table name is null");
			
			if(mapping == null)
				throw new IllegalArgumentException("Insert failed, mapping is null");
			
			String sql = "INSERT INTO " + tableName + " (";
			for(int i = 0; i < mapping.size() - 1; i++)
				sql += mapping.get(i).GetKey() + ",";
			sql+= mapping.get(mapping.size() - 1).GetKey() + ") VALUES(";
			
			for(int i = 0; i < mapping.size() - 1; i++)
				sql += "?,";
			sql += ("?);");
			
			int value = 0;
			statement = factory.CreateInsertStatement(conn, object, sql);
			value = statement.executeUpdate();
			conn.commit();
			
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next())
				value = rs.getInt(1);
			
			return value;
		}
		finally{
			try { if(statement != null) statement.close(); }
			finally { ConnectionPool.releaseConnection(conn); }
		}
	}
	
	/**
	 * Update an existing object into database
	 * @param object Object to modify
	 * @throws SQLException
	 */
	public <T> void Update(T object) throws SQLException
	{
		Connection conn = null;
		PreparedStatement statement = null;
		BeanManager manager = new BeanManager(object);
		
		try{
			conn = ConnectionPool.getConnection(configuration);
			String tableName = manager.GetTableName();
			
			ArrayList<Tuple<String, String>> mapping = manager.Map();
			if(mapping == null)
				throw new IllegalArgumentException("Update failed, mapping is null");
			
			if(tableName  == null)
				throw new IllegalArgumentException("Update failed, table name is null");
			
			String sql = "UPDATE " + tableName + " SET ";
			for(int i = 0; i < mapping.size() - 1; i++)
				sql += mapping.get(i).GetKey() + "=?, ";
			sql += mapping.get(mapping.size() - 1).GetKey() + "=?";
			sql += " WHERE " + manager.GetPrimaryKeyName() + "=?;";
			
			statement = factory.CreateUpdateStatement(conn, object, sql);
			statement.executeUpdate();
			conn.commit();
		}
		finally{
			try { if(statement != null) statement.close(); }
			finally { ConnectionPool.releaseConnection(conn); }
		}
	}
	
	/**
	 * Delete an existing object into Database
	 * @param object Object to remove
	 * @throws SQLException
	 */
	public <T> void Delete(T object) throws SQLException
	{
		Connection conn = null;
		PreparedStatement statement = null;
		BeanManager manager = new BeanManager(object);
		
		try{
			conn = ConnectionPool.getConnection(configuration);
			String tableName = manager.GetTableName();
			
			ArrayList<Tuple<String, String>> mapping = manager.Map();
			if(mapping == null)
				throw new IllegalArgumentException("Delete failed, mapping is null");
			
			if(tableName == null)
				throw new IllegalArgumentException("Delete failed, table name is null");
			
			String primaryKeyName = manager.GetPrimaryKeyName();
			if(primaryKeyName == null)
				throw new IllegalArgumentException("Delete failed, primary key name is null");
			
			String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyName + "=?;";
			
			statement = factory.CreateDeleteStatement(conn, object, sql);
			statement.executeUpdate();
			conn.commit();
		}
		finally{
			try { if(statement != null) statement.close(); }
			finally { ConnectionPool.releaseConnection(conn); }
		}
	}
	
	/**
	 * Find all elements into database (with a query) and return new peopled element
	 * @param type Base type to return after query execution
	 * @param query User query
	 * @return Dto object representing the user's table
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public <T> ArrayList<T> Find(Object type, String query) throws SQLException
	{
		Connection conn = null;
		PreparedStatement statement = null;
		BeanManager manager = new BeanManager(type);
		
		try{
			conn = ConnectionPool.getConnection(configuration);
			String tableName = manager.GetTableName();
			if(tableName == null)
				throw new IllegalArgumentException("Error, table name is null");
			
			statement = factory.CreateRetriveStatement(conn, query);
			ResultSet result = statement.executeQuery();
			conn.commit();
			
			return (ArrayList<T>)BeanManager.MakeBeanObjectArray(result, type);
		}
		finally {
			try { if(statement != null) statement.close(); }
			finally { ConnectionPool.releaseConnection(conn); }
		}
	}
}
