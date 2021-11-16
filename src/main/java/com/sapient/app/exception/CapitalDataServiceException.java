package com.sapient.app.exception;

public class CapitalDataServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4159051658128968901L;

	public CapitalDataServiceException(String message) {
		super(message);
	}

	public CapitalDataServiceException(String message, Throwable ex) {
		super(message, ex);
	}

}
