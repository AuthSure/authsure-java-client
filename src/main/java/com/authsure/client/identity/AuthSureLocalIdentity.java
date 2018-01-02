package com.authsure.client.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * AuthSure Local Identity.
 *
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthSureLocalIdentity extends AuthSureIdentity {

  private static final long serialVersionUID = -7313954580160373015L;

  protected String username;
  protected String email;
  protected String name;
  protected String givenName;
  protected String familyName;
  protected String locale;

  @Override
  public String getPrincipalName() {
    return username;
  }
}
