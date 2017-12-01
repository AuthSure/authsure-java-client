package com.authsure.client.exception;

/**
 * Holds exception details when requested action is not allowed.
 *
 * @author Erik R. Jensen
 */
public class AuthSureForbiddenException extends AuthSureException {

  public AuthSureForbiddenException() {
  }

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
