package com.authsure.client.exception;

import com.authsure.client.AuthSureError;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds exceptions details for bad/invalid requests.
 *
 * @author Erik R. Jensen
 */
public class AuthSureBadRequestException extends AuthSureException {

  protected List<AuthSureError> errors = new ArrayList<AuthSureError>();

  public AuthSureBadRequestException() {
  }

  public AuthSureBadRequestException(String message) {
    super(message);
  }

  public AuthSureBadRequestException(String message, List<AuthSureError> errors) {
    super(message);
    this.errors = errors;
  }

  public List<AuthSureError> getErrors() {
    return errors;
  }

  public void setErrors(List<AuthSureError> errors) {
    this.errors = errors;
  }

}
