package br.com.labs.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import co.elastic.apm.attach.ElasticApmAttacher;

@Configuration
@ConfigurationProperties(prefix = "elastic.apm")
public class ElasticApmConfig {

	private static final String SERVER_URL_KEY = "server_url";
	private String serverUrl;

	private static final String SERVICE_NAME_KEY = "service_name";
	private String serviceName;

	private static final String SECRET_TOKEN_KEY = "secret_token";
	private String secretToken;

	private static final String ENVIRONMENT_KEY = "environment";
	private String environment;

	private static final String APPLICATION_PACKAGES_KEY = "application_packages";
	private String applicationPackages;

	private static final String LOG_LEVEL_KEY = "log_level";
	private String logLevel;

	@PostConstruct
	public void init() {

		Map<String, String> apmProps = new HashMap<>(6);
		apmProps.put(SERVER_URL_KEY, serverUrl);
		apmProps.put(SERVICE_NAME_KEY, serviceName);
		apmProps.put(SECRET_TOKEN_KEY, secretToken);
		apmProps.put(ENVIRONMENT_KEY, environment);
		apmProps.put(APPLICATION_PACKAGES_KEY, applicationPackages);
		apmProps.put(LOG_LEVEL_KEY, logLevel);

		ElasticApmAttacher.attach(apmProps);
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getSecretToken() {
		return secretToken;
	}

	public void setSecretToken(String secretToken) {
		this.secretToken = secretToken;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getApplicationPackages() {
		return applicationPackages;
	}

	public void setApplicationPackages(String applicationPackages) {
		this.applicationPackages = applicationPackages;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public static String getServerUrlKey() {
		return SERVER_URL_KEY;
	}

	public static String getServiceNameKey() {
		return SERVICE_NAME_KEY;
	}

	public static String getSecretTokenKey() {
		return SECRET_TOKEN_KEY;
	}

	public static String getEnvironmentKey() {
		return ENVIRONMENT_KEY;
	}

	public static String getApplicationPackagesKey() {
		return APPLICATION_PACKAGES_KEY;
	}

	public static String getLogLevelKey() {
		return LOG_LEVEL_KEY;
	}

}
