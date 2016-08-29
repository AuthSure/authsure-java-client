package com.authsure.client.exception;

/**
 * @author Erik R. Jensen
 */
public class AuthSureForbiddenException extends AuthSureException {

	public AuthSureForbiddenException() {}

	public AuthSureForbiddenException(String message) {
		super(message);
	}

	public AuthSureForbiddenException(String message, Throwable t) {
		super(message, t);
	}

	public AuthSureForbiddenException(Throwable t) {
		super(t);
	}

}
