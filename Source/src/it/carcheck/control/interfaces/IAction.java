package it.carcheck.control.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.carcheck.control.exception.ActionException;

public interface IAction {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException;
}