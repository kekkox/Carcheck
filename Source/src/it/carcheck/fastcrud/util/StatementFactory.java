package it.carcheck.fastcrud.util;
import java.util.ArrayList;
import java.sql.*;

public class StatementFactory {

	/**
	 * Create a new insert statement
	 * @param conn Connection instance
	 * @param bean User object (BEAN)
	 * @param sql Additional sql string
	 * @return New Prepared Statement
	 * @throws SQLException
	 */
	public PreparedStatement CreateInsertStatement(Connection conn, Object bean, String sql) throws SQLException
	{
		BeanManager manager = new BeanManager(bean);
		PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {manager.GetPrimaryKeyName()});
		
		ArrayList<Tuple<String, String>> table = manager.Map();
		for(int i = 0; i < table.size(); i++)
			preparedStatement.setObject(i + 1, table.get(i).GetValue());
		
		return preparedStatement;
	}
	
	/**
	 * Create new delete statement
	 * @param conn Connection instance
	 * @param bean User object (BEAN)
	 * @param sql Additional sql string
	 * @return New Prepared Statement
	 * @throws SQLException
	 */
	public PreparedStatement CreateDeleteStatement(Connection conn, Object bean, String sql) throws SQLException
	{
		BeanManager manager = new BeanManager(bean);
		PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {manager.GetPrimaryKeyName()});
		
		String keyValue = manager.GetPrimaryKey();
		preparedStatement.setObject(1, keyValue);
		
		return preparedStatement;
	}
	
	/**
	 * Create new update statement
	 * @param conn Connection instance
	 * @param bean User object (DTO)
	 * @param sql Additional sql string
	 * @return New Prepared Statement
	 * @throws SQLException
	 */
	public PreparedStatement CreateUpdateStatement(Connection conn, Object bean, String sql) throws SQLException
	{
		BeanManager manager = new BeanManager(bean);
		PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {manager.GetPrimaryKeyName()});
		
		ArrayList<Tuple<String, String>> table = manager.Map();
		for(int i = 0; i < table.size(); i++)
			preparedStatement.setObject(i + 1, table.get(i).GetValue());
		preparedStatement.setObject(table.size() + 1, manager.GetPrimaryKey());
		
		return preparedStatement;
	}
	
	/**
	 * Create new retrive statement
	 * @param conn Connection instance
	 * @param sql Additional sql string
	 * @return New Prepared Statement
	 * @throws SQLException
	 */
	public PreparedStatement CreateRetriveStatement(Connection conn, String sql) throws SQLException
	{
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		return preparedStatement;
	}
	
}
