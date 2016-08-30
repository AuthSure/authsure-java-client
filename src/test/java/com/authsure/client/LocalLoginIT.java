package com.authsure.client;

import com.authsure.client.http.RestClient;
import com.authsure.client.http.URLConnectionRestClient;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author Erik R. Jensen
 */
@Slf4j
public class LocalLoginIT {

	private static AuthSureClient client;
	private static Properties config;

	@BeforeClass
	public static void init() throws IOException {
		client = IntegrationTestHelper.getAdminClient();
		config = IntegrationTestHelper.getConfig();
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class LoginForm {
		private String username;
		private String password;
	}

	@Data
	public static class LoginResponse {
		private String redirect;
	}

	@Test
	public void testLocalLogin() throws IOException {
		try (WebClient webClient = new WebClient()) {
			HtmlPage page = webClient.getPage(config.getProperty("url") + "/console");

			List<?> lst = page.getByXPath("//form[contains(@class, 'as-sign-in-form')]//input[contains(@class, 'as-input-username')]");
			assertThat(lst.size(), equalTo(1));
			HtmlTextInput usernameInput = (HtmlTextInput) lst.get(0);

			lst = page.getByXPath("//form[contains(@class, 'as-sign-in-form')]//input[contains(@class, 'as-input-password')]");
			assertThat(lst.size(), equalTo(1));
			HtmlPasswordInput passwordInput = (HtmlPasswordInput) lst.get(0);

			lst = page.getByXPath("//form[contains(@class, 'as-sign-in-form')]//button[contains(@class, 'as-btn-submit')]");
			assertThat(lst.size(), equalTo(1));
			HtmlButton submit = (HtmlButton) lst.get(0);

			usernameInput.setText(config.getProperty("username"));
			passwordInput.setText(config.getProperty("password"));
			submit.click();
			// TODO Add more to this test and validate the subsequent page
		}
	}

	@Test
	public void testLoginTokenValidation() throws IOException {

		RestClient restClient = new URLConnectionRestClient(config.getProperty("url"));

		// Get login token
		LoginResponse loginResponse = restClient.postForObject(
				"/console/auth",
				new LoginForm(config.getProperty("username"), config.getProperty("password")),
				LoginResponse.class);

		// Validate token
		String redirect = loginResponse.getRedirect();
		log.info("Received redirect: " + redirect);
		assertThat(redirect, not(isEmptyOrNullString()));
		String token = redirect.substring(redirect.indexOf("?t=") + 3, redirect.length());
		assertThat(token, not(isEmptyOrNullString()));
		log.info("Retrieved token: " + token);

		AuthSureLogin login = client.validateLogin(token);
		assertThat("admin", equalTo(login.getName()));

	}

}