package com.currencycloud.coolpay.exception;

/**
 * This class implements the exception for bad requests submitted to the server.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see Exception
 */
public class BadRequestException extends Exception {

	/**
	 * Serial version UID of the class
	 */
	private static final long serialVersionUID = 5940376832889762830L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}