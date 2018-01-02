package com.authsure.client.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * AuthSure API Identity.
 *
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthSureSimpleApiIdentity extends AuthSureIdentity {

  private static final long serialVersionUID = 7954723395253074296L;

  protected String username;
  protected String name;
  protected String givenName;
  protected String familyName;
  protected String email;

  @Override
  public String getPrincipalName() {
    return username;
  }
}
