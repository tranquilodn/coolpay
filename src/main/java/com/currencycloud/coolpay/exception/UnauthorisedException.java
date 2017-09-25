package com.currencycloud.coolpay.exception;

/**
 * This class implements the exception for unauthorised requests submitted to the server.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see Exception
 */
public class UnauthorisedException extends Exception {

	/**
	 * Serial version UID of the class
	 */
	private static final long serialVersionUID = -5898059571128596407L;

	public UnauthorisedException() {
		super();
	}

	public UnauthorisedException(String message) {
		super(message);
	}

	public UnauthorisedException(Throwable cause) {
		super(cause);
	}

	public UnauthorisedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnauthorisedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}