package com.authsure.client;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Erik R. Jensen
 */
@Data
@Accessors(chain = true)
public class AuthSureError implements Serializable {

	private static final long serialVersionUID = -5986079040025190316L;

	private String message;
	private String field;
}
