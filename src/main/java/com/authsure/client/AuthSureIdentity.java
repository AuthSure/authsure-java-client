package com.authsure.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSubTypes({
		@JsonSubTypes.Type(value = AuthSureLocalIdentity.class, name = "Local"),
		@JsonSubTypes.Type(value = AuthSureGoogleIdentity.class, name = "Google"),
		@JsonSubTypes.Type(value = AuthSureFacebookIdentity.class, name = "Facebook"),
		@JsonSubTypes.Type(value = AuthSureSalesforceIdentity.class, name = "Salesforce"),
		@JsonSubTypes.Type(value = AuthSureGitHubIdentity.class, name = "GitHub"),
		@JsonSubTypes.Type(value = AuthSureYahooIdentity.class, name = "Yahoo"),
		@JsonSubTypes.Type(value = AuthSureTwitterIdentity.class, name = "Twitter")
})
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "provider",
		visible = true)
public abstract class AuthSureIdentity implements Serializable {

	private static final long serialVersionUID = 4200450494547083147L;

	protected UUID id;
	protected UUID identity;
	protected String provider;
	protected AuthSureProviderType providerType;
	protected List<AuthSureLinkedIdentity> linkedIdentities;
	private Set<String> groups;
	private Set<String> roles;
	private Set<String> permissions;
	private Set<String> effectivePermissions;

	public abstract String getPrincipalName();

}
