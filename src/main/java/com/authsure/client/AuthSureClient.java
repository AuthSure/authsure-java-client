package com.authsure.client;

import com.authsure.client.http.RestClient;
import com.authsure.client.http.URLConnectionRestClient;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;

/**
 * @author Erik R. Jensen
 */
public class AuthSureClient implements Serializable {

	private static final long serialVersionUID = -8090828763929068073L;

	protected String url;
	protected RestClient restClient;

	public AuthSureClient(String url, String username, String password) {
		this.url = url;
		this.restClient = new URLConnectionRestClient(url, username, password);
	}

	public AuthSureClient(RestClient restClient) {
		this.restClient = restClient;
	}

	public RestClient getRestClient() {
		return restClient;
	}

	public String getUrl() {
		return url;
	}

	@Data
	@AllArgsConstructor
	private class TokenValidationRequest {
		private String token;
	}

	public AuthSureLogin validateLogin(String loginToken) throws IOException {
		return restClient.postForObject("/api/tokens", new TokenValidationRequest(loginToken), AuthSureLogin.class);
	}

}
