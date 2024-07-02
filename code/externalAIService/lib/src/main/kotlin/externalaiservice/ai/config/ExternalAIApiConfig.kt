package externalaiservice.ai.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "external-api")
data class ExternalAIApiConfig(
    var baseUrl: String = "",
    var apiKey: String = "",
    var endpoints: Endpoints = Endpoints()
) {
    data class Endpoints(
        var processRequest: String = "",
    )
}