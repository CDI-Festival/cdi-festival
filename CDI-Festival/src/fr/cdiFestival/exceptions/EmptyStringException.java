package fr.cdiFestival.exceptions;

/**
 * This exception can be instantiate when a value shouldn't be null or empty.
 * 
 * @author Claire
 * @version 20161123
 */
public class EmptyStringException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmptyStringException(String message) {
		super(message);
	}
	
}
