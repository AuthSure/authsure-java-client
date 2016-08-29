package com.authsure.client.exception;

import java.io.IOException;

/**
 * @author Erik R. Jensen
 */
public class AuthSureException extends IOException {

	public AuthSureException() {}

	public AuthSureException(String message) {
		super(message);
	}

	public AuthSureException(String message, Throwable t) {
		super(message, t);
	}

	public AuthSureException(Throwable t) {
		super(t);
	}

}
