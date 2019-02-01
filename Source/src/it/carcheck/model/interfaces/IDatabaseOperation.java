package it.carcheck.model.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IDatabaseOperation<T> {

	public void doSave(T element) throws SQLException;

	public void doDelete(T element) throws SQLException;

	public void doInsert(T element) throws SQLException;
	
	public ArrayList<T> doFind(String query) throws SQLException;
}
