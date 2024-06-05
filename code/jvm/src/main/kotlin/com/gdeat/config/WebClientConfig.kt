package com.gdeat.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    private val config: ExternalAIApiConfig
) {
    @Bean
    fun webClient(builder: WebClient.Builder): WebClient {
        return builder.baseUrl(config.baseUrl).build()
    }
}