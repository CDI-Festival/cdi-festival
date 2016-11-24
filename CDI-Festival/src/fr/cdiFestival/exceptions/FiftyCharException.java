package fr.cdiFestival.exceptions;

/**
 * This exception can be instantiate when a String is more than fifty characters.
 * 
 * @author Claire
 * @version 20161123
 */
public class FiftyCharException extends Exception {

	private static final long serialVersionUID = 1L;

	public FiftyCharException(String message) {
		super(message);
	}
	
}
