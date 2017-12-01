package com.authsure.client;

import com.authsure.client.identity.AuthSureIdentity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;

/**
 * Hold user account login information.
 *
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
public class AuthSureLogin implements Principal, Serializable {

  private static final long serialVersionUID = -8818959207764452260L;

  private AuthSureIdentity identity;
  private Date sessionExpiration;

  @Override
  public String getName() {
    return identity.getPrincipalName();
  }

  public boolean isExpired() {
    return sessionExpiration != null && sessionExpiration.before(new Date());
  }
}
