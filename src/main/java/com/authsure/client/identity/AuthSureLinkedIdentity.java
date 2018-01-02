package com.authsure.client.identity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

/**
 * AuthSure Linked Identities.
 *
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthSureLinkedIdentity {

  private UUID id;
  private String provider;

}
