package it.carcheck.control.exception;

public class ActionException extends Exception {
	public ActionException() {
		super("ActionException generated");
	}
	
	public ActionException(String message) {
		super(message);
	}
}
