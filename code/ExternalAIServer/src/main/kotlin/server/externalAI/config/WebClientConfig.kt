package server.externalAI.config

import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

/**
 * Configuration class for setting up WebClient with custom settings.
 *
 * @property config The configuration for the external AI API, providing the base URL and other settings.
 */
@Configuration
class WebClientConfig(
    private val config: ExternalAIApiConfig
) {
    /**
     * Creates and provides a WebClient.Builder bean.
     *
     * @return A WebClient.Builder instance.
     */
    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
    }

    /**
     * Creates and provides a WebClient bean configured with custom settings.
     *
     * This WebClient instance is configured with a base URL from the provided configuration and
     * includes custom timeout settings for read and write operations.
     *
     * @param builder A WebClient.Builder instance.
     * @return A configured WebClient instance.
     */
    @Bean
    fun webClient(builder: WebClient.Builder): WebClient {
        val httpClient = HttpClient.create()
            .responseTimeout(Duration.ofMinutes(RES_TIME_MINUTES))
            .doOnConnected { conn ->
                conn.addHandlerLast(ReadTimeoutHandler(READ_WRITE_TIMEOUT))
                conn.addHandlerLast(WriteTimeoutHandler(READ_WRITE_TIMEOUT))
            }

        return builder.baseUrl(config.baseUrl)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }

    companion object{
        private const val RES_TIME_MINUTES: Long = 4
        private const val READ_WRITE_TIMEOUT: Int = 240
    }
}