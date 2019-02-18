package it.carcheck.control.interfaces;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;

public interface IAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException, ServletException, ParseException;
	
	public static final String HEADER_NAME = "RESPONSE_TYPE";
	public static final String JSON_RESPONSE = "JSON";
	public static final String REDIRECT_RESPONSE = "Redirect";
	public static final String FORWARD_RESPONSE = "Forward";
}