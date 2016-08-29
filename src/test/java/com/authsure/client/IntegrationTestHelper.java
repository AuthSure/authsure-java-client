package com.authsure.client;

import com.netradius.commons.lang.ValidationHelper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Erik R. Jensen
 */
@Slf4j
public class IntegrationTestHelper {

	private static Properties config;
	private static AuthSureClient adminClient;

	private static Properties read(final File file) {
		log.info("Loading integration test settings from " + file.getAbsolutePath());
		try (FileInputStream in = new FileInputStream(file)) {
			Properties properties = new Properties();
			properties.load(in);
			return properties;
		} catch (IOException x) {
			log.error("Error reading " + file.getAbsolutePath() + ": " + x.getMessage(), x);
		}
		return null;
	}

	private static boolean loadConfig() {
		// Try and read from default location first
		File file = new File("integration.properties");
		if (file.exists()) {
			config = read(file);
		}

		// Try alternate location
		if (config == null) {

			// Check for system property
			String loc = System.getProperty("config");
			if (loc == null) {
				loc = System.getProperty("CONFIG");
			}

			// Check environment variable
			if (loc == null) {
				loc = System.getenv("CONFIG");
			}
			if (loc == null) {
				loc = System.getenv("config");
			}

			if (loc != null) {
				file = new File(loc);
				if (file.exists()) {
					config = read(file);
				}
			}
		}

		return config != null;
	}

	private static void init() throws IOException {
		if (config == null) {

			log.info("Initializing account for integration tests");

			if (!loadConfig()) {
				throw new IllegalStateException("Unable to load integration test settings");
			}

			String username = config.getProperty("username");
			ValidationHelper.checkForEmpty(username);
			String password = config.getProperty("password");
			ValidationHelper.checkForEmpty(password);
			String url = config.getProperty("url");
			ValidationHelper.checkForEmpty(url);

			adminClient = new AuthSureClient(url, username, password);
		}
	}

	public static Properties getConfig() throws IOException {
		init();
		return config;
	}

	public static AuthSureClient getAdminClient() throws IOException {
		init();
		return adminClient;
	}

}
