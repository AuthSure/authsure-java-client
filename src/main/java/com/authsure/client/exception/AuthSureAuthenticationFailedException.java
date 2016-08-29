package com.authsure.client.exception;

/**
 * @author Erik R. Jensen
 */
public class AuthSureAuthenticationFailedException extends AuthSureException {

	public AuthSureAuthenticationFailedException() {}

	public AuthSureAuthenticationFailedException(String message) {
		super(message);
	}

	public AuthSureAuthenticationFailedException(String message, Throwable t) {
		super(message, t);
	}

	public AuthSureAuthenticationFailedException(Throwable t) {
		super(t);
	}
}
