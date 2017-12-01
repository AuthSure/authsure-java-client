package com.authsure.client;

import com.authsure.client.http.RestClient;
import com.authsure.client.http.URLConnectionRestClient;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

/**
 * Tests for LocalLogin.
 *
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

  @Test
  public void testLocalLogin() throws IOException {
    try (WebClient webClient = new WebClient()) {
      HtmlPage page = webClient.getPage(config.getProperty("url") + "/console");

      List<?> lst = page.getByXPath("//form[contains(@class, 'as-sign-in-form')]//"
          + "input[contains(@class, 'as-input-username')]");
      assertThat(lst.size(), equalTo(1));
      HtmlTextInput usernameInput = (HtmlTextInput) lst.get(0);
      usernameInput.setText(config.getProperty("username"));

      lst = page.getByXPath("//form[contains(@class, 'as-sign-in-form')]//"
          + "input[contains(@class, 'as-input-password')]");
      assertThat(lst.size(), equalTo(1));
      HtmlPasswordInput passwordInput = (HtmlPasswordInput) lst.get(0);
      passwordInput.setText(config.getProperty("password"));

      lst = page.getByXPath("//form[contains(@class, 'as-sign-in-form')]//"
          + "button[contains(@class, 'as-btn-submit')]");
      assertThat(lst.size(), equalTo(1));
      HtmlButton submit = (HtmlButton) lst.get(0);

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
    assertThat(login.getName(), equalTo("admin"));
    assertThat(login.getIdentity().getRoles().size(), equalTo(1));
    assertThat(login.getIdentity().getRoles().iterator().next(), equalTo("AS_ADMIN"));

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
}