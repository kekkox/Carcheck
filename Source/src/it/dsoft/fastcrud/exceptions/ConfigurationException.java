package it.dsoft.fastcrud.exceptions;

/**
 * This class represents Configuration exception
 * @author Daniele De Falco
 *
 */
public class ConfigurationException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Empty constructor
	 */
	public ConfigurationException() {
		super();
	}
	
	/**
	 * Specifies the type of error to display in the exception
	 * @param message Error message
	 */
	public ConfigurationException(String message) {
		super(message);
	}
	
	/**
	 * Specifies the type of error and cause to display in the exception
	 * @param message Error message
	 * @param cause Error cause
	 */
	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Specifies the type of cause to display in the exception
	 * @param cause Error cause
	 */
	public ConfigurationException(Throwable cause) {
		super(cause);
	}
}
