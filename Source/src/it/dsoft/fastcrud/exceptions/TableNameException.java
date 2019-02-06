package it.dsoft.fastcrud.exceptions;

/**
 * This class represents TableName exception
 * @author Daniele De Falco
 *
 */
public class TableNameException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Empty constructor
	 */
	public TableNameException() {
		super();
	}
	
	/**
	 * Specifies the type of error to display in the exception
	 * @param message Error message
	 */
	public TableNameException(String message) {
		super(message);
	}
	
	/**
	 * Specifies the type of error and cause to display in the exception
	 * @param message Error message
	 * @param cause Error cause
	 */
	public TableNameException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * Specifies the type of cause to display in the exception
	 * @param cause Error cause
	 */
	public TableNameException(Throwable cause) {
		super(cause);
	}
}
