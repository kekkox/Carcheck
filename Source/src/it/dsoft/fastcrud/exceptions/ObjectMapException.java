package it.dsoft.fastcrud.exceptions;

/**
 * This class represents ObjectMap exception
 * @author Daniele De Falco
 *
 */
public class ObjectMapException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Empty constructor
	 */
	public ObjectMapException() {
		super();
	}
	
	/**
	 * Specifies the type of error to display in the exception
	 * @param message Error message
	 */
	public ObjectMapException(String message) {
		super(message);
	}
	
	/**
	 * Specifies the type of error and cause to display in the exception
	 * @param message Error message
	 * @param cause Error cause
	 */
	public ObjectMapException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Specifies the type of cause to display in the exception
	 * @param cause Error cause
	 */
	public ObjectMapException(Throwable cause) {
		super(cause);
	}
}
