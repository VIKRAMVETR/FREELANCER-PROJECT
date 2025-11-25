package com.freelancenexus.userservice.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * GeminiConfig
 *
 * <p>Configuration class for integrating with the Google Gemini API.
 * Manages API credentials, endpoint configuration, and client setup.
 * All properties are injected from application configuration files.</p>
 *
 * @since 1.0
 */
@Configuration
public class GeminiConfig {

    /**
     * API key for authenticating requests to the Gemini API.
     * Loaded from property: {@code gemini.api.key}
     */
    @Value("${gemini.api.key}")
    private String apiKey;

    /**
     * Base URL for the Gemini API endpoint.
     * Loaded from property: {@code gemini.api.url}
     */
    @Value("${gemini.api.url}")
    private String apiUrl;

    /**
     * Gemini model identifier to use for API requests
     * (e.g., "gemini-1.5-pro", "gemini-1.5-flash").
     * Loaded from property: {@code gemini.api.model}
     */
    @Value("${gemini.api.model}")
    private String model;

    /**
     * Request timeout in milliseconds.
     * Loaded from property: {@code gemini.api.timeout}
     * Default: 30000 ms (30 seconds)
     */
    @Value("${gemini.api.timeout:30000}")
    private int timeout;

    /**
     * Maximum number of retries for failed API requests.
     * Loaded from property: {@code gemini.api.max-retries}
     * Default: 3
     */
    @Value("${gemini.api.max-retries:3}")
    private int maxRetries;

    /**
     * Create and configure a WebClient bean for communicating with the Gemini API.
     *
     * <p>The client is pre-configured with the API base URL and standard JSON content-type header.</p>
     *
     * @return a configured {@link WebClient} instance for Gemini API calls
     */
    @Bean
    public WebClient geminiWebClient() {
        return WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxRetries() {
		return maxRetries;
	}

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}
    
    
}