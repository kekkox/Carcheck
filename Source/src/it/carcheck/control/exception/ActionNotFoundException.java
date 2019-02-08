package it.carcheck.control.exception;

public class ActionNotFoundException extends ActionException {
	public ActionNotFoundException() {
		super("ActionNotFoundException: unable to find the action to execute");
	}
	
	public ActionNotFoundException(String message) {
		super(message);
	}
}
