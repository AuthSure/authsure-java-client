package com.authsure.client;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
public class AuthSureProviderIdentity implements Serializable {

	private static final long serialVersionUID = 4200450494547083147L;

	private UUID id;
	private UUID identity;
	private UUID provider;
	private AuthSureProviderType providerType;
	private String username;
	private String email;
	private String oid;
	private String name;
	private String givenName;
	private String familyName;
	private String profile;
	private String picture;
	private Boolean emailVerified;
	private Gender gender;
	private String locale;

}
