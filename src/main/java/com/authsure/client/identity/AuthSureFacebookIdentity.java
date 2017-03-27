package com.authsure.client.identity;

import com.authsure.client.Gender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author Dilip S Sisodia
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthSureFacebookIdentity extends AuthSureIdentity {

	private static final long serialVersionUID = 3578312272602321085L;

	protected String email;
	protected String name;
	protected String givenName;
	protected String familyName;
	protected String locale;
	protected String profileUrl;
	protected String pictureUrl;
	protected Boolean emailVerified;
	protected Gender gender;

	@Override
	public String getPrincipalName() {
		return email;
	}
}