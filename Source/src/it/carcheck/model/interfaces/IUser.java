package it.carcheck.model.interfaces;

import java.sql.SQLException;

public interface IUser<T> {
	
	public T doLogin(String email, String password) throws SQLException;
	
	public void doChangePassword(T user, String password) throws SQLException;
}
