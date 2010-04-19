package com.dhev.exception;

public class DhevClassCastException extends ClassCastException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2397574779432806691L;

	public DhevClassCastException(String message, ClassCastException cause) {
		super(message);
		this.initCause(cause);
	}

}
