package com.currencycloud.coolpay.exception;

/**
 * This class implements the exception for requests submitted to the server
 * which returns any other which are not previously implemented, as:
 * - 400
 * - 401
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * @see Exception
 */
public class GenericException extends Exception {

	/**
	 * Serial version UID of the class
	 */
	private static final long serialVersionUID = 1531320576635020108L;

	public GenericException() {
		super();
	}

	public GenericException(String message) {
		super(message);
	}

	public GenericException(Throwable cause) {
		super(cause);
	}

	public GenericException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}