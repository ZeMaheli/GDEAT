package com.gdeat.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Configuration class for external AI Server settings.
 *
 * @property url The base URL of the external AI Server.
 * @property endpoints The endpoints used for making requests to the external AI Server.
 */
@Configuration
@ConfigurationProperties(prefix = "ai-server")
data class AIServerConfig(
    var url: String = "",
    var endpoints: Endpoints = Endpoints()
) {
    /**
     * Data class representing the endpoints for the external AI Server.
     *
     * @property process The endpoint for processing AI requests.
     */
    data class Endpoints(
        var process: String = "",
    )
}