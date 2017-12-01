package com.authsure.client.exception;

/**
 * Holds exception details when requested resource not found.
 *
 * @author Erik R. Jensen
 */
public class AuthSureNotFoundException extends AuthSureException {

  public AuthSureNotFoundException() {
  }

  public AuthSureNotFoundException(String message) {
    super(message);
  }

  public AuthSureNotFoundException(String message, Throwable t) {
    super(message, t);
  }

  public AuthSureNotFoundException(Throwable t) {
    super(t);
  }

}
