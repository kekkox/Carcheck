package it.carcheck.control.interfaces;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;

public interface IAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException, IOException;
	
	public static final String HEADER_NAME = "RESPONSE_TYPE";
	public static final String JSON_RESPONSE = "JSON";
	public static final String REDIRECT_RESPONSE = "Redirect";
	public static final String FORWARD_RESPONSE = "Forward";
}