package server.externalAI.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Configuration class for external AI API settings.
 *
 * @property baseUrl The base URL of the external AI API.
 * @property apiKey The API key used for authentication with the external AI API.
 * @property endpoints The endpoints used for making requests to the external AI API.
 */
@Configuration
@ConfigurationProperties(prefix = "external-api")
data class ExternalAIApiConfig(
    var baseUrl: String = "",
    var apiKey: String = "",
    var endpoints: Endpoints = Endpoints()
) {
    /**
     * Data class representing the endpoints for the external AI API.
     *
     * @property processRequest The endpoint for processing AI requests.
     */
    data class Endpoints(
        var processRequest: String = "",
    )
}