package com.gdeat.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

/**
 * Configuration class for setting up WebClient with custom settings.
 *
 * @property aiServerConfig The configuration for the external AI Server, providing the base URL and other settings.
 */
@Configuration
class WebClientConfig(
    private val aiServerConfig: AIServerConfig
) {

    /**
     * Creates and provides a WebClient bean configured with custom settings.
     *
     * This WebClient instance is configured with a base URL from the provided configuration
     *
     * @return A configured WebClient instance.
     */
    @Bean
    fun webClient(): WebClient {
        return WebClient.builder().baseUrl(aiServerConfig.url).build()
    }
}