package com.authsure.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthSureProviderType implements Serializable {

	private static final long serialVersionUID = 8713619483780516203L;

	private UUID id;
	private String name;
}
