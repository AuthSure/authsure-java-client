package com.authsure.client;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
public class AuthSureIdentity implements Serializable {

	private static final long serialVersionUID = -6270223877043430412L;

	private UUID id;
	private AuthSureIdentityType type;
	private List<AuthSureProviderIdentity> providerIdentities;
	private List<String> groups;
	private List<String> roles;
	private List<String> permissions;
	private List<String> effectivePermissions;

}
