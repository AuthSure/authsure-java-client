package com.authsure.client;

import com.authsure.client.http.RestClient;
import com.authsure.client.http.URLConnectionRestClient;

import java.io.Serializable;

/**
 * @author Erik R. Jensen
 */
public class AuthSureClient implements Serializable {

	private static final long serialVersionUID = -8090828763929068073L;

	protected RestClient restClient;

	public AuthSureClient(String url, String username, String password) {
		this.restClient = new URLConnectionRestClient(url, username, password);
	}

	public AuthSureClient(RestClient restClient) {
		this.restClient = restClient;
	}

	public RestClient getRestClient() {
		return restClient;
	}

	public AuthSureLogin validateLoginToken(String loginToken) {
		restClient.get("")
	}

}
