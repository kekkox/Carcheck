package it.carcheck.control.exception;

public class ActionBadFormedException extends ActionException {
	public ActionBadFormedException() {
		super("Bad formed request");
	}
	
	public ActionBadFormedException(String message) {
		super(message);
	}
}
