package it.dsoft.fastcrud;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedHashSet;

import it.dsoft.fastcrud.core.BeanEntity;
import it.dsoft.fastcrud.core.BeanEntityFactory;
import it.dsoft.fastcrud.core.BeanManager;
import it.dsoft.fastcrud.core.ConnectionPool;
import it.dsoft.fastcrud.core.interfaces.IBeanEntity;
import it.dsoft.fastcrud.core.interfaces.IBeanEntityFactory;
import it.dsoft.fastcrud.core.interfaces.IConnectionPool;
import it.dsoft.fastcrud.enums.OperationType;
import it.dsoft.fastcrud.enums.StatementType;
import it.dsoft.fastcrud.exceptions.DatabaseUpdateException;
import it.dsoft.fastcrud.interfaces.IDatabase;

/**
 * This class contains a number of methods that perform basic database operations
 * @author Daniele De Falco
 *
 */
public class FastCrud implements IDatabase {

	/**
	 * Base class constructor
	 * @param configuration Database configuration class
	 * @throws ClassNotFoundException
	 */
	public FastCrud(DatabaseConfiguration configuration) throws ClassNotFoundException {
		this.beanEntityFactory = BeanEntityFactory.getInstance();
		
		this.connectionPool = new ConnectionPool(configuration);
		this.statementFactory = new StatementFactory();
		this.queryGenerator = new QueryGenerator();
		this.beanManager = new BeanManager();
	}
	
	/**
	 * This method allows you to insert a generic item into the database
	 */
	@Override
	public <T> int create(T object) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = this.connectionPool.getConnection();
			BeanEntity<T> entity = new BeanEntity<T>(object);
			
			String query = this.queryGenerator.generateQuery(entity, OperationType.Create);
			statement = this.statementFactory.createStatement(StatementType.Create, connection, entity, query);
			
			int returnValue = statement.executeUpdate();
			connection.commit();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			if(resultSet.next())
				returnValue = resultSet.getInt(1);
			
			return returnValue;
		} finally {
			try { if(statement != null) statement.close(); }
			finally { this.connectionPool.releaseConnection(connection); }
		}
	}

	/**
	 * This method allows you to update a generic item into the database
	 */
	@Override
	public <T> void update(T object) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = this.connectionPool.getConnection();
			BeanEntity<T> entity = new BeanEntity<T>(object);
			
			String query = this.queryGenerator.generateQuery(entity, OperationType.Update);
			statement = this.statementFactory.createStatement(StatementType.Update, connection, entity, query);
			
			int resultRow = statement.executeUpdate();
			if(resultRow <= 0)
				throw new DatabaseUpdateException("you cannot update primary key");
			
			connection.commit();
		} finally {
			try { if(statement != null) statement.close(); }
			finally { this.connectionPool.releaseConnection(connection); }
		}
	}
	
	/**
	 * This method allows you to delete a generic item into the database
	 */
	@Override
	public <T> void delete(T object) throws Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = this.connectionPool.getConnection();
			BeanEntity<T> entity = new BeanEntity<T>(object);
			
			String query = this.queryGenerator.generateQuery(entity, OperationType.Delete);
			statement = this.statementFactory.createStatement(StatementType.Delete, connection, entity, query);
			
			statement.executeUpdate();
			connection.commit();
		} finally {
			try { if(statement != null) statement.close(); }
			finally { this.connectionPool.releaseConnection(connection); }
		}
	}

	/**
	 * This method allows you to find a generic item from the database
	 */
	@Override
	public <T> LinkedHashSet<T> read(Class<T> clazz, String query, Object...args) throws Exception {
		BeanEntity<T> entity = (BeanEntity<T>) this.beanEntityFactory.create(clazz);
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = this.connectionPool.getConnection();
			statement = this.statementFactory.createStatement(StatementType.Read, connection, entity, query, args);
			
			ResultSet resultSet = statement.executeQuery();
			connection.commit();
			
			LinkedHashSet<T> queryResult = new LinkedHashSet<>();
			Iterator<IBeanEntity<T>> beanEntitiesIterator = this.beanManager.MakeBeanEntities(resultSet, clazz).iterator();
			
			while(beanEntitiesIterator.hasNext()) {
				IBeanEntity<T> beanEntity = beanEntitiesIterator.next();
				queryResult.add(beanEntity.getEntity());
			}
			
			return queryResult;
		} finally {
			try { if(statement != null) statement.close(); }
			finally { this.connectionPool.releaseConnection(connection); }
		}
	}

	private IBeanEntityFactory beanEntityFactory;
	private StatementFactory statementFactory;
	
	private IConnectionPool connectionPool;
	private QueryGenerator queryGenerator;
	private BeanManager beanManager;
}
